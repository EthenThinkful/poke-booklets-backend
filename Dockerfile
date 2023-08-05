FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /pokemonapijectapi
COPY . /pokemonapijectapi/
RUN mvn clean package

FROM openjdk:17-alpine
WORKDIR /pokemonapijectapi
COPY --from=build /pokemonapijectapi/target/*.jar /pokemonapijectapi/Pokemonapijectapi-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Pokemonapijectapi-0.0.1-SNAPSHOT.jar"]