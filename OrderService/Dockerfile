FROM java:8

COPY api-gateway/target/order-service.jar app.jar

CMD java -Dserver.port=11113 -Deureka.client.service-url.defaultZone=http://$EUREKA_SERVER/eureka -Deureka.instance.prefer-ip-address=true -Deureka.instance.ip-address=$HOST_IP_ADRESS  -Deureka.instance.non-secure-port=$HOST_PORT -jar app.jar

EXPOSE 11113