FROM java:8
EXPOSE 8080
ADD /build/libs/KetchupAPI-0.1.0.jar ketchupApi.jar
RUN sh -c 'touch /ketchupApi.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /ketchupApi.jar" ]
