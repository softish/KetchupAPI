# KetchupAPI

This is the REST API for the ketchup system. 
It acts as a backend for the 
[KetchupDesktop](https://github.com/softish/KetchupDesktop) pomodoro timer 
and [KetchupDashboard](https://github.com/softish/KetchupDashboard) panel.

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

### Running in development environment
Run the main method in class `app.Application`.

### Packaging
To package `jar`, run gradle task `gradle bootRepackage`. 

