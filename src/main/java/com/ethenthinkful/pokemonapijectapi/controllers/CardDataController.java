package com.ethenthinkful.pokemonapijectapi.controllers;

import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Comparator;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

	// get rid of n characters at beginning and end of string method
	public String characterRemove(String x) {
		int n = 23; // Number of characters to remove from the beginning and end
		String result = x.substring(n);
		return result;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~new implement~~~~~~~~~~~~~~~~~~~~~~~~

	public static String generateRandomString(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("Length must be greater than zero.");
		}
		// Define the characters allowed in the random string
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		// Create a Random object to generate random indices
		Random random = new Random();
		// Create a StringBuilder to build the random string
		StringBuilder randomString = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			// Generate a random index to select a character from the characters string
			int randomIndex = random.nextInt(characters.length());
			// Append the randomly selected character to the StringBuilder
			randomString.append(characters.charAt(randomIndex));
		}
		return randomString.toString();
	}

	@PutMapping("/cards")
	public CardData verify(@RequestBody CardDataRequest request) throws IOException {
		System.out.println("YOU HAVE HIT THE UPDATE METHOD FOR VERIFYING A CARD");
		int cardSlot = request.getCardSlot();
		cardSlot = cardSlot - 1;
		UserData user = userDataRepository.findById(request.getUserDataId()).get();
		List<CardData> cardList = user.getCardData().stream()
		.sorted(Comparator.comparing(CardData::getId))
		.collect(Collectors.toList());
		CardData cardToVerify = cardList.get(cardSlot);
		cardToVerify.setVerified(true);
		String img = request.getLuhthang();
		String editedImg = characterRemove(img);
		byte[] imageBytes = Base64.getDecoder().decode(editedImg);
		String filename = generateRandomString(50) + ".png";
		Path basePath = Path.of(System.getProperty("user.dir"));
		Path filePath = basePath.resolve(filename);
		Files.write(filePath, imageBytes);
		cardToVerify.setLuhThang(filename);
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
	public List<CardData> getSingleCardData(@PathVariable("userDataId") int userDataId) throws IOException {
		List<CardData> cardDataList = cardDataRepository.findByUserdataId(userDataId);
		for (CardData obj : cardDataList) {
			String currLuhThang = obj.getLuhthang(); // Modify the value as per your requirement
			if (currLuhThang != null) {
				Path basePath = Path.of(System.getProperty("user.dir"));
				Path filePath = basePath.resolve(currLuhThang);
				byte[] imageBytes = Files.readAllBytes(filePath);
				String base64String = Base64.getEncoder().encodeToString(imageBytes);
				String fullLuhThang = "data:image/jpeg;base64," + base64String;
				obj.setLuhThang(fullLuhThang);
			}
		}
		return cardDataList;
	}

}