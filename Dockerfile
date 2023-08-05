FROM openjdk:17-oracle
LABEL maintainers="PokemonAPIject"
ADD target/Pokemonapijectapi-0.0.1-SNAPSHOT.jar pokemonapijectapi.jar
ENTRYPOINT ["java","-jar","Pokemonapijectapi.jar"]