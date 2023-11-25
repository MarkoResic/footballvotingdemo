FROM eclipse-temurin:17-jdk-alpine

EXPOSE 8080

COPY ./build/libs/footballvotingdemo-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "footballvotingdemo-0.0.1-SNAPSHOT.jar"]