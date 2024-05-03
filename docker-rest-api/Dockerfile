FROM amazoncorretto:17-alpine

COPY target/hello*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]