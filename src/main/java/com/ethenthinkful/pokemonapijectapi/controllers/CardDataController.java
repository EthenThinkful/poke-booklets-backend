package com.ethenthinkful.pokemonapijectapi.controllers;

import java.util.ArrayList;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ethenthinkful.pokemonapijectapi.dto.CardDataRequest;
import com.ethenthinkful.pokemonapijectapi.model.UserData;
import com.ethenthinkful.pokemonapijectapi.repos.UserDataRepository;

import com.ethenthinkful.pokemonapijectapi.model.CardData;
import com.ethenthinkful.pokemonapijectapi.repos.CardDataRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CardDataController {
	private CardDataRepository cardDataRepository;
	private UserDataRepository userDataRepository;

	CardDataController(CardDataRepository cardDataRepository, UserDataRepository userDataRepository) {
		this.cardDataRepository = cardDataRepository;
		this.userDataRepository = userDataRepository;
	}

	@PostMapping("/cards")
	public CardData saveCardData(@RequestBody CardDataRequest request) {
		UserData userData = userDataRepository.findById(request.getUserDataId()).get();
		CardData cardData = new CardData();
		cardData.setPokemonCard(request.pokemonCard());
		cardData.setUserData(userData);
		return cardDataRepository.save(cardData);
	}

	@GetMapping("/cards/{userDataId}")
	public List<CardData> getCardData(@PathVariable("userDataId") int userDataId) {
		return cardDataRepository.findByUserDataId(userDataId);
	}
}