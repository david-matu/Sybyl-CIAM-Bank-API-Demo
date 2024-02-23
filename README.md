This is simple Spring Boot REST API based on Spring Boot 2.7.6.SNAPSHOT and supports Java 11.

To run, 
1. Clone this resposity and run ``maven install``
2. Navigate to the *target* directory and run ``java -jar <app-name>.0.0.1-SNAPSHOT.jar`` You can access the API via ``http://<your-domain>:9099``
For example, in localhost: [http://localhost:9099/bank/customers] (http://localhost:9099/bank/customers)

Database:
3. This API consists of H2 embedded database which can be accessed via [http://localhost:8080/h2-console] (http://localhost:8080/h2-console)
If you decide to use a non-embedded database, revise database connection settings in ``<app-root-directory>/pom.xml`` to suit your database family. Pick ``src/main/resources/schema.sql`` as your database script. Update your database username and password in the ``src/main/resources/application.yml`` file
4. Test the API via a tool like Postman. Use this url to see the Swagger documentation: [http://localhost:9099/swagger-ui/index.html] (http://localhost:9099/swagger-ui/index.html)
