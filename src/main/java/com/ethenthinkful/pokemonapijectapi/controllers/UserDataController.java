package com.ethenthinkful.pokemonapijectapi.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.ethenthinkful.pokemonapijectapi.service.LoginService;

import jakarta.servlet.http.HttpServletResponse;

import com.ethenthinkful.pokemonapijectapi.dto.CardDataRequest;
import com.ethenthinkful.pokemonapijectapi.dto.UserDataRequest;
import com.ethenthinkful.pokemonapijectapi.model.CardData;
import com.ethenthinkful.pokemonapijectapi.model.UserData;
import com.ethenthinkful.pokemonapijectapi.repos.UserDataRepository;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserDataController {
	@Autowired
	private LoginService loginService;

	private UserDataRepository repository;

	UserDataController(UserDataRepository repository) {
		this.repository = repository;
	}

	//used for displaying all users & their booklets on the home page
	@GetMapping("/users")
	public List<UserData> getUsers() throws IOException {
		System.out.println("Hello! you've hit /users (before the query)");
		 List<UserData> repositories = repository.findAll();
		 for (UserData obj : repositories) {
			String pfp = obj.getProfilePic(); // Modify the value as per your requirement
			if (pfp != null) {
				Path basePath = Path.of(System.getProperty("user.dir"));
				Path filePath = basePath.resolve(pfp);
				byte[] imageBytes = Files.readAllBytes(filePath);
				String base64String = Base64.getEncoder().encodeToString(imageBytes);
				String pfpString = "data:image/jpeg;base64," + base64String;
				obj.setProfilePic(pfpString);
			}
		}
		 return repositories;
	}

	@GetMapping("/users/{id}")
	public UserData getUser(@PathVariable("id") int id) {
		return repository.findById(id).get();
	}

	@GetMapping("/userss")
	public ResponseEntity<UserData> getExampleData(
			@RequestParam String user) {
		UserData userData = loginService.findByUsername(user);
		if (userData == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseEntity<UserData> userResponse = new ResponseEntity<>(userData, HttpStatus.OK);
		// System.out.println(userResponse);
		return userResponse;
	}

	//create new user
	@PostMapping("/users")
	public ResponseEntity<String> saveUser(@RequestBody UserData user) {
		if (repository.existsByUserName(user.getuserName())) {
			System.out.println("This user is already taken!");
			ResponseEntity<String> responseEntity = ResponseEntity.badRequest().body("This Username is taken.");
			return responseEntity;
		}
		repository.save(user);
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Welcome to Poke Booklets!", HttpStatus.OK);
		return responseEntity;
	}

	public String characterRemove(String x) {
		int n = 23; // Number of characters to remove from the beginning and end
		String result = x.substring(n);
		return result;
	}

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

	@PutMapping("/users")
	public UserData saveProfilePic(@RequestBody UserDataRequest request) throws IOException {
		System.out.println("You hit /users to update a profile pic!");
		UserData user = repository.findById(request.getId()).get();
		String pfp = request.getProfilePic();
		String editedPfp = characterRemove(pfp);
		byte[] imageBytes = Base64.getDecoder().decode(editedPfp);
		String filename = generateRandomString(50) + ".png";
		Path basePath = Path.of(System.getProperty("user.dir"));
		Path filePath = basePath.resolve(filename);
		Files.write(filePath, imageBytes);
		user.setProfilePic(filename);
		UserData savedUser = repository.save(user);
		return savedUser;
	}

	@PutMapping("/userss")
	public UserData updateNickName(@RequestBody UserDataRequest request) {
		UserData user = repository.findById(request.getId()).get();
		user.setNickName(request.getNickName());
		UserData savedUser = repository.save(user);
		return savedUser;
		// if (repository.existsByUserName(user.getuserName())) {
		// 	System.out.println("This user exists, now lets update it's nickname!");
		// 	repository.save(user);
		// 	ResponseEntity<String> responseEntity = new ResponseEntity<>("nickname updated successfully!", HttpStatus.OK);
		// 	return responseEntity;
		// }
		// ResponseEntity<String> responseEntity = ResponseEntity.badRequest().body("This Username does not exist therefore you cannot update it's nickname.");
		// return responseEntity;
	}

	@RequestMapping(value = "/userss/{userDataId}", method = RequestMethod.GET) 
	public UserData getProfilePic(@PathVariable("userDataId") int userDataId) throws IOException {
		UserData user = repository.findById(userDataId).get();
			String pfp = user.getProfilePic();
			if (pfp != null) {
				Path basePath = Path.of(System.getProperty("user.dir"));
				Path filePath = basePath.resolve(pfp);
				byte[] imageBytes = Files.readAllBytes(filePath);
				String base64String = Base64.getEncoder().encodeToString(imageBytes);
				String fullPfp = "data:image/jpeg;base64," + base64String;
				user.setProfilePic(fullPfp);
			}
		return user;
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUserData(@PathVariable("id") int id) {
		// Check if the entity exists in the database
		if (!repository.existsById(id)) {
			return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
		}

		// If the entity exists, delete it
		repository.deleteById(id);

		return new ResponseEntity<>("Entity deleted successfully", HttpStatus.OK);
	}
}
