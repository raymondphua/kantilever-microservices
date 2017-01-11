FROM java:8

COPY api-gateway/target/api-gateway.jar app.jar

CMD java -Dserver.port=11130 -Deureka.client.service-url.defaultZone=http://localhost:11140/eureka -jar app.jar

EXPOSE 11130