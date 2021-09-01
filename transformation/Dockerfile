FROM openjdk:11

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw install

ENTRYPOINT ["java", "-jar", "./target/transformation-1.0.0.jar"]
