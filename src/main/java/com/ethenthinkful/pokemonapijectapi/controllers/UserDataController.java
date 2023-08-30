package com.ethenthinkful.pokemonapijectapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ethenthinkful.pokemonapijectapi.model.UserData;
import com.ethenthinkful.pokemonapijectapi.repos.UserDataRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserDataController {
	private UserDataRepository repository;
	
	UserDataController(UserDataRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/users")
	public List<UserData> getUsers() {
		System.out.println("Hello! you've hit /users (before the query)");
		return repository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public UserData getUser(@PathVariable("id") int id) {
		return repository.findById(id).get();
	}

	@PostMapping("/users")
	public UserData saveUser(@RequestBody UserData user) {
		return repository.save(user);
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
