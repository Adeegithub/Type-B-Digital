# How to Run the Application
1. Clone the repostory
2. Open the Directory through the IDE (IntelliJ is Preffered)
3. Locate the Main Spring Boot Application Class `TypeBDigitalApplication`
4. Click on the Run icon or Press `ctrl + shift + R` in mac.

# How to Run the Tests
1. Locate the Test Class `NameControllerTest`
2. Click on the Play Icon in Left or Press `ctrl + shift + R` in mac.

## Alertnative Methods
1. If you have Maven installed globally, run this command in the project directory : `mvn spring-boot:run`
2. To run the tests, run this command in the project directory : `mvn test`

## Assumptions
This assesment is done on the assumption, that the users who invoke the endpoints are authorized users since security such as Oauth2 and OpenId is not implemented.