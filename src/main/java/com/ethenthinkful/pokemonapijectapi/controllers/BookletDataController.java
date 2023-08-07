package com.ethenthinkful.pokemonapijectapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ethenthinkful.pokemonapijectapi.model.BookletData;
import com.ethenthinkful.pokemonapijectapi.repos.BookletDataRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BookletDataController {
	private BookletDataRepository bookletDataRepository;
	
	BookletDataController(BookletDataRepository bookletDataRepository) {
		this.bookletDataRepository = bookletDataRepository;
	}
	
	@RequestMapping(value = "/booklet", method = RequestMethod.POST, consumes = {"*/*"})
	public BookletData saveBookletData(@RequestBody BookletData bookletData) {
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
	
	@RequestMapping(value = "/booklet/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBookletData(@PathVariable("id") int id) {
		// Check if the entity exists in the database
        if (!bookletDataRepository.existsById(id)) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        }

        // If the entity exists, delete it
        bookletDataRepository.deleteById(id);

        return new ResponseEntity<>("Entity deleted successfully", HttpStatus.OK);
    }
}
