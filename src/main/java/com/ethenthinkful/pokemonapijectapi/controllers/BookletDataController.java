package com.ethenthinkful.pokemonapijectapi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ethenthinkful.pokemonapijectapi.dto.BookletDataRequest;
import com.ethenthinkful.pokemonapijectapi.model.BookletData;
import com.ethenthinkful.pokemonapijectapi.model.User;
import com.ethenthinkful.pokemonapijectapi.repos.BookletDataRepository;
import com.ethenthinkful.pokemonapijectapi.repos.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BookletDataController {
	private BookletDataRepository bookletDataRepository;
	private UserRepository userRepository;

	BookletDataController(BookletDataRepository bookletDataRepository, UserRepository userRepository) {
		this.bookletDataRepository = bookletDataRepository;
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/booklet", method = RequestMethod.POST)
	public BookletData saveBookletData(@RequestBody BookletDataRequest request) {
		User user = userRepository.findById(request.getUserId()).get();
		BookletData bookletData = new BookletData();
		bookletData.setCardOne(request.getCardOne());
		bookletData.setCardTwo(request.getCardTwo());
		bookletData.setCardThree(request.getCardThree());
		bookletData.setCardFour(request.getCardFour());
		bookletData.setCardFive(request.getCardFive());
		bookletData.setCardSix(request.getCardSix());
		bookletData.setUser(user);
		return bookletDataRepository.save(bookletData);
	}
	
	@RequestMapping(value = "/booklet", method = RequestMethod.GET)
	public List<BookletData> getBooklets() {
		return bookletDataRepository.findAll();
	}

	@RequestMapping(value = "/booklet/{id}")
	public BookletData getBookletData(@PathVariable("id") int id) {
		return bookletDataRepository.findById(id).get();
	}
}
