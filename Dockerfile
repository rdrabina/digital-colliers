FROM openjdk:8-jdk-alpine
ADD target/bank-application.jar app.jar
EXPOSE 9090
CMD java -jar app.jar
