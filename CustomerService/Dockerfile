FROM java:8

COPY api-gateway/target/customer-service.jar app.jar

CMD java -Dserver.port=11110 -Deureka.client.service-url.defaultZone=http://localhost:11140/eureka -jar app.jar

EXPOSE 11110