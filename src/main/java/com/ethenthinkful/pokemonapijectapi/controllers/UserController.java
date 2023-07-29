package com.ethenthinkful.pokemonapijectapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ethenthinkful.pokemonapijectapi.model.User;
import com.ethenthinkful.pokemonapijectapi.repos.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

	private UserRepository repository;

	@Autowired
	UserController(UserRepository repository) {

		this.repository = repository;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getUsers() {
		return repository.findAll();
	}

	@RequestMapping(value = "/users/{id}")
	public User getUser(@PathVariable("id") int id) {
		return repository.findById(id).get();
	}
	

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User saveUser(@RequestBody User user) {
		return repository.save(user);
	}

}
