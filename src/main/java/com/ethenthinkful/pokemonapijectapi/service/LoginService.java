package com.ethenthinkful.pokemonapijectapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // Add this import

import com.ethenthinkful.pokemonapijectapi.model.UserData;
import com.ethenthinkful.pokemonapijectapi.repos.UserDataRepository; // Add this import

@Service
public class LoginService {
    @Autowired
    private UserDataRepository userDataRepository;

    // username check
    public UserData findByUsername(String username) {
        UserData user = userDataRepository.findByUserName(username);
        if (user != null) {
            return user;
        } else {
            return null; 
        }
    }
}