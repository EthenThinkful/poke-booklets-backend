package com.ethenthinkful.pokemonapijectapi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ethenthinkful.pokemonapijectapi.dto.CardDataRequest;
import com.ethenthinkful.pokemonapijectapi.model.CardData;
import com.ethenthinkful.pokemonapijectapi.model.UserData;
import com.ethenthinkful.pokemonapijectapi.repos.CardDataRepository;
import com.ethenthinkful.pokemonapijectapi.repos.UserDataRepository;
import com.ethenthinkful.pokemonapijectapi.service.CardService;


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
		// System.out.println("Hello! you've hit /cards!");
		UserData user = userDataRepository.findById(request.getUserDataId()).get();
		CardData cardData = new CardData();
		cardData.setPokemonCard(request.getPokemonCard());
		cardData.setUserData(user);
		return cardDataRepository.save(cardData);
	}

	// @GetMapping("/cards/{userDataId}/{pokemonCard}")
	// public List<CardData> getSingleCardData(@PathVariable("userDataId") int userDataId, @PathVariable("pokemonCard") String pokemonCard) {
	// 	System.out.println("Hello! you've hit /cards!/userID (before the query)");
	// 	List<CardData> cardData = cardDataRepository.findByUserDataIdAndPokemonCardOrderByMeasuredDateTime(userDataId, pokemonCard);
	// 	System.out.println("Hello! you've hit /cards!/userID (after the query)");
	// 	return cardData;
	// }

	@RequestMapping(value="/cards/{userDataId}", method=RequestMethod.GET)
	public List<CardData> getSingleCardData(@PathVariable("userDataId") int userDataId) {
		//return CardService.getCardById(userDataId);

		List<CardData> cardDataList = cardDataRepository.findByUserdataId(userDataId);
		// System.out.println("Hello! you've hit /cards!/userID (after the query)");
		// System.out.println(cardDataList);
		// //System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardData));
		return cardDataList;

	}

}