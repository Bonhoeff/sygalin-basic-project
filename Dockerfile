FROM maven:3-openjdk-17 as builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
ENV USER_NAME_DB root
ENV USER_PWD_DB root
ENV JWT_KEY_API sygalin_sygalin_
ENV DATABASE_URL jdbc:mysql://172.17.0.2:3306/
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:17-alpine
LABEL authors="dietrich guemkan"
COPY --from=builder /usr/src/app/target/basic-project-0.1.jar /usr/app/basic-project-0.1.jar
ENV USER_NAME_DB root
ENV USER_PWD_DB root
ENV JWT_KEY_API sygalin_sygalin_
ENV DATABASE_URL jdbc:mysql://172.17.0.2:3306/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/app/basic-project-0.1.jar"]