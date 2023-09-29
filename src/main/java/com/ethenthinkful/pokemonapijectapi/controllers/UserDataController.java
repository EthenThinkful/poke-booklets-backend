package com.ethenthinkful.pokemonapijectapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.ethenthinkful.pokemonapijectapi.service.LoginService;

import jakarta.servlet.http.HttpServletResponse;

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
	public List<UserData> getUsers() {
		System.out.println("Hello! you've hit /users (before the query)");
		return repository.findAll();
	}

	@GetMapping("/users/{id}")
	public UserData getUser(@PathVariable("id") int id) {
		return repository.findById(id).get();
	}

	// ---------------------------------new
	// implement---------------------------------
	@GetMapping("/userss")
	public ResponseEntity<UserData> getExampleData(
			@RequestParam String user, @RequestParam String password) {
		UserData userData = loginService.findByUsernameAndPassword(user, password);
		if (userData == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseEntity<UserData> userResponse = new ResponseEntity<>(userData, HttpStatus.OK);
		// System.out.println(userResponse);
		return userResponse;
	}
	// ---------------------------------end new
	// implement---------------------------------

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
