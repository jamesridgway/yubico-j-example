# yubico-j-example
This project is a basic example of how to use the Yubico Java library. This was built with version 3.0.0 of `yubico-validation-client2`.

## Running with Maven
1. Goto https://upgrade.yubico.com/getapikey/ to get an API key and secret if you do not already have one
1. Run `mvn clean install`
2. From inside the `target` directory run: ``java -jar yubico-j-example-1.0-SNAPSHOT.jar 123 xyz otp`` replacing '123' with your API key, 'xyz' with your secret key and 'otp' with the OTP to validate.
