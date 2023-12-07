FROM eclipse-temurin:17-jdk-alpine

EXPOSE 8443

COPY ./build/libs/footballvotingdemo-1.0.0.jar /usr/app/
COPY ./src/main/resources/keystore/keystore.p12 /usr/app/keystore/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "footballvotingdemo-1.0.0.jar"]