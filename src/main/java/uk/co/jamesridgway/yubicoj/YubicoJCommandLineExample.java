package uk.co.jamesridgway.yubicoj;

import com.yubico.client.v2.VerificationResponse;
import com.yubico.client.v2.YubicoClient;
import com.yubico.client.v2.exceptions.YubicoValidationFailure;
import com.yubico.client.v2.exceptions.YubicoVerificationException;

/**
 * This is a basic example of how to use yubico-j is a command line application.
 */
public class YubicoJCommandLineExample {

    /**
     * Main application method.
     *
     * Given the clientId, secretKey and OTP that are supplied via command line arguments, this application will
     * then attempt to validate the OTP.
     *
     * @param args arguments
     */
    public static void main(final String[] args) {
        // API Client ID
        Integer clientId = null;
        // API Secret Key
        String secretKey = null;
        // YubiKey OTP
        String otp = null;

        // Validate and load arguments
        if (args.length == 3) {
            clientId = new Integer(Integer.parseInt(args[0]));
            secretKey = args[1];
            otp = args[2];
        } else {
            throw new IllegalArgumentException("Expected 3 arguments: <clientId> <secretKey> <OTP>");
        }

        // Create the Yubico APi client
        final YubicoClient yubicoClient = YubicoClient.getClient(clientId, secretKey);

        try {
            // Verify the OTP
            final VerificationResponse verificationResponse = yubicoClient.verify(otp);

            // Determine the response
            if (verificationResponse.isOk()) {
                // Valid OTP
                System.out.println("Response:" + verificationResponse);
                System.out.println("Yubikey ID: " + verificationResponse.getPublicId());
            } else {
                // Replayed or Bad OTP
                System.err.println("Verification error: " + verificationResponse);
                System.err.print("Status is: " + verificationResponse.getStatus());
            }

        } catch (YubicoVerificationException e) {
            System.err.println("Error verifying OTP: " + e.getMessage());
        } catch (YubicoValidationFailure e) {
            System.err.println("OTP validation failure: " + e.getMessage());
        }

    }

}
