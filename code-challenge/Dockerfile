FROM openjdk:8-jdk

WORKDIR /opt/app

COPY target/*.jar /opt/app/booking-service.jar

ENTRYPOINT ["java","-jar","booking-service.jar"]