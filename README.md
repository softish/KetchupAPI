# KetchupAPI

This is the REST API for the ketchup system.

## Getting started - development

### Configuring persistence
To enable the application to connect to a database 
create an `application.properties` file under
`src/main/resources/`

Set the first line to `spring.profiles.active=base` 
and specify the datasource credentials for the following properties:

    spring.datasource.url
    spring.datasource.username
    spring.datasource.password

