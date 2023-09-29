package com.ethenthinkful.pokemonapijectapi.controllers;

import java.util.List;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		UserData user = userDataRepository.findById(request.getUserDataId()).get();
		CardData cardData = new CardData();
		cardData.setPokemonCard(request.getPokemonCard());
		cardData.setUserData(user);
		return cardDataRepository.save(cardData);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~new implement~~~~~~~~~~~~~~~~~~~~~~~~
	// get rid of n characters at beginning and end of string method
	public String characterRemove(String x) {
		int n = 23; // Number of characters to remove from the beginning and end
		String result = x.substring(n);
		return result;
	}

	@PutMapping("/cards")
	public CardData verify(@RequestBody CardDataRequest request) throws IOException {
		System.out.println("YOU HAVE HIT THE UPDATE METHOD FOR VERIFYING A CARD");
		int cardSlot = request.getCardSlot();
		cardSlot = cardSlot - 1;
		UserData user = userDataRepository.findById(request.getUserDataId()).get();
		List<CardData> cardList = user.getCardData();
		CardData cardToVerify = cardList.get(cardSlot);
		cardToVerify.setVerified(true);
		String img = request.getLuhthang();
		cardToVerify.setLuhThang(img);
		CardData savedCard = userDataRepository.save(cardToVerify);
		return savedCard;
	}
	// ~~~~~~~~~~~~~~~~~~~~~~~~end new implement~~~~~~~~~~~~~~~~~~~~~~~~

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

	@RequestMapping(value = "/cards/{userDataId}", method = RequestMethod.GET)
	public List<CardData> getSingleCardData(@PathVariable("userDataId") int userDataId) {
		// return CardService.getCardById(userDataId);
		List<CardData> cardDataList = cardDataRepository.findByUserdataId(userDataId);
		// System.out.println("Hello! you've hit /cards!/userID (after the query)");
		// System.out.println(cardDataList);
		// //System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardData));
		return cardDataList;

	}

}