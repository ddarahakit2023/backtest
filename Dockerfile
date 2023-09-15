FROM openjdk:11.0.16
ADD build/libs/web-0.0.1-SNAPSHOT.jar /web-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","/web-0.0.1-SNAPSHOT.jar"]