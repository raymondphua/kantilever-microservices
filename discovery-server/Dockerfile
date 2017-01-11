FROM java:8

COPY target/discovery-server.jar app.jar

CMD java -Dserver.port=11140 -jar app.jar

EXPOSE 11140