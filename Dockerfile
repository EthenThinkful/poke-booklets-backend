FROM openjdk:17-oracle
VOLUME /tmp
LABEL key="PokemonAPIject"
ADD target/Pokemonapijectapi-0.0.1-SNAPSHOT.jar Pokemonapijectapi.jar
ENTRYPOINT ["java","-jar","Pokemonapijectapi.jar"]