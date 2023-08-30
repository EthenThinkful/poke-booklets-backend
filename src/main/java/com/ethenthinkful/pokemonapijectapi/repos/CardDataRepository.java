package com.ethenthinkful.pokemonapijectapi.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ethenthinkful.pokemonapijectapi.model.CardData;

@Repository
public interface CardDataRepository extends JpaRepository<CardData, Integer> {

    List<CardData> findByUserdataId(int userDataId);
    // List<CardData> findByUserDataIdAndPokemonCardOrderByMeasuredDateTime(int userDataId, String pokemonCard);
    
}