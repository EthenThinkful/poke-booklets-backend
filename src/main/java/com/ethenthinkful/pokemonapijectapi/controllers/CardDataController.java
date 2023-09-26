package com.ethenthinkful.pokemonapijectapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ethenthinkful.pokemonapijectapi.dto.CardDataRequest;
import com.ethenthinkful.pokemonapijectapi.model.CardData;
import com.ethenthinkful.pokemonapijectapi.model.UserData;
import com.ethenthinkful.pokemonapijectapi.repos.CardDataRepository;
import com.ethenthinkful.pokemonapijectapi.repos.UserDataRepository;


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
		System.out.println("OMFG YOU HIT ME BRUH");
		UserData user = userDataRepository.findById(request.getUserDataId()).get();
		CardData cardData = new CardData();
		cardData.setPokemonCard(request.getPokemonCard());
		cardData.setUserData(user);
		return cardDataRepository.save(cardData);
	}

	@DeleteMapping("/cards/{id}")
	public ResponseEntity<String> deleteCardData(@PathVariable("id") int id) {
		System.out.println("OW THAT HURT YOU HIT MY DELETE");
		// Check if the entity exists in the database
        if (!cardDataRepository.existsById(id)) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        }

        // If the entity exists, delete it
        cardDataRepository.deleteById(id);

        return new ResponseEntity<>("Entity deleted successfully", HttpStatus.OK);
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