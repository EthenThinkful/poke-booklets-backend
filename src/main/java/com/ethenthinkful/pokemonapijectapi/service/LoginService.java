package com.ethenthinkful.pokemonapijectapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // Add this import

import com.ethenthinkful.pokemonapijectapi.model.UserData;
import com.ethenthinkful.pokemonapijectapi.repos.UserDataRepository; // Add this import

@Service
public class LoginService {
    @Autowired
    private UserDataRepository userDataRepository;

    public UserData findByUsername(String userName) {
        UserData userData = userDataRepository.findByUserName(userName);
        return userData;
    }

    // Optionally, you can include the password check as well
    // public UserData findByUsernameAndPassword(String username, String password) {
    //     UserData user = userDataRepository.findByUsername(username);
    //     if (user != null && user.getPassword().equals(password)) {
    //         return user;
    //     } else {
    //         return null;
    //     }
    // }
}