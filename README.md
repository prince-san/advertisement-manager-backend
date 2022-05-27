# advertisement-manager-backend

For booting up the application you'll need Docker and Java 17 (with JAVA_HOME set to it). Boot up MySQL database by:
`docker-compose up`.
And start app itself by:
`.\mvnw spring-boot:run`.
Don't forget to set `permitted.origin` propety in application.properties file to ip addres where frontend is deployed to let it work properly.
