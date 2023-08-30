package com.ethenthinkful.pokemonapijectapi.repos;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ethenthinkful.pokemonapijectapi.model.CardData;


public interface CardDataRepository extends JpaRepository<CardData, Integer> {

    // List<CardData> findByUserDataIdAndPokemonCardOrderByMeasuredDateTime(int userDataId, String pokemonCard);
    
}