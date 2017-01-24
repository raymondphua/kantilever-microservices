FROM java:8

COPY target/discovery-server.jar app.jar

CMD java -Dserver.port=11140 -Deureka.instance.hostname=$HOST_NAME -jar app.jar

EXPOSE 11140