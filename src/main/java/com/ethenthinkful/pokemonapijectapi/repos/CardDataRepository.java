package com.ethenthinkful.pokemonapijectapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ethenthinkful.pokemonapijectapi.model.CardData;

import java.util.List;

public interface CardDataRepository extends JpaRepository<CardData, Long> {
List<CardData> findByUserDataId(int userDataId);    
}