package com.ethenthinkful.pokemonapijectapi.controllers;

import java.util.List;
import java.util.*;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.ethenthinkful.pokemonapijectapi.model.UserData;
import com.ethenthinkful.pokemonapijectapi.repos.UserDataRepository;
import com.ethenthinkful.pokemonapijectapi.model.CardData;
import com.ethenthinkful.pokemonapijectapi.repos.CardDataRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserDataController {
	private UserDataRepository UserDataRepository;
	
	UserDataController(UserDataRepository UserDataRepository) {
		this.UserDataRepository = UserDataRepository;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST, consumes = {"*/*"})
	public UserData saveUserData(@RequestBody UserData userData) {
		return UserDataRepository.save(userData);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserData> getUsers() {
		return UserDataRepository.findAll();
	}

	@RequestMapping(value = "/users/{id}", method=RequestMethod.GET)
	public UserData getUserData(@PathVariable("id") int id) {
		return UserDataRepository.findById(id).get();
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUserData(@PathVariable("id") int id) {
		// Check if the entity exists in the database
        if (!UserDataRepository.existsById(id)) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        }

        // If the entity exists, delete it
        UserDataRepository.deleteById(id);

        return new ResponseEntity<>("Entity deleted successfully", HttpStatus.OK);
    }
}
