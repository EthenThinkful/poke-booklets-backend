package com.ethenthinkful.pokemonapijectapi.service;

import java.util.List;
import com.ethenthinkful.pokemonapijectapi.model.CardData;;

public interface CardService {
    List<CardData> getCardById(int id);
}
