This Spring Boot app is the backend to a parkinglot app.
To run the app open a cmd, navigate to the root of the project via command line and execute the command "mvn spring-boot:run".
To be honest, I build this app on an old computer and the cmd doesn't work here, so if the above line doesn't work, just open the project in your favourite java code editor and run MyAppApplication.java from there (you will need Spring Boot for that).

Once the app is running, you can:

- view the database on http://localhost:8080/h2 (assuming H2 is installed on your pc). 
username: sa
password: 1234

- use Postman for get, post and put requests on http://localhost:8080.

