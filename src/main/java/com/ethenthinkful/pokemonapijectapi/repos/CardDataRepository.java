package com.ethenthinkful.pokemonapijectapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ethenthinkful.pokemonapijectapi.model.CardData;

//import java.util.ArrayList;

import java.util.List;


public interface CardDataRepository extends JpaRepository<CardData, Integer> {
    List<CardData> findByUserDataId(int userDataId);
    List<CardData> findByUserDataIdAndPokemonCardOrderByMeasuredDateTime(int userDataId, String pokemonCard);
}