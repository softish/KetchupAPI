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

### Running in IDE
Run the main method in class `app.Application`.

Program argument `--port=<some-port>` may be supplied to override the default 
port specified in [application-base.properties](./src/main/resources/application-base.properties).
More information about the subject can be found 
[here](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-properties-and-configuration.html).

### Running *jar*
Package the project according to [packaging](./README.md#packaging).

Run with `java -jar KetchupAPI-<version-number>.jar`

### Running with gradle
Run the gradle task `bootRun`.

### Running with docker
Package the project according to [packaging](./README.md#packaging).

Build image with `docker build -t <image-name> .` from the project root.

Run `docker run <image-name>`. 

Options can be added after `run`. 
To redirect the port, use the option `-p hostPort:containerPort` where *containerPort* is set to 8080 in the [Dockerfile](./Dockerfile).
To detach container from terminal use the option `-d`.
If the container does not stop on `ctrl+c`, run `docker ps` and `docker stop <CONTAINER ID>`.

### Packaging
To package `jar`, run gradle task `gradle bootRepackage`. 
The output is available under the `/build/libs/` directory.

