package com.ethenthinkful.pokemonapijectapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // Add this import

import com.ethenthinkful.pokemonapijectapi.model.UserData;
import com.ethenthinkful.pokemonapijectapi.repos.UserDataRepository; // Add this import

@Service
public class LoginService {
    @Autowired
    private UserDataRepository userDataRepository;

    // public UserData findByUsername(String userName, String password) {
    //     UserData userData = userDataRepository.findByUserName(userName, password);
    //     return userData;
    // }

    // username and password check
    public UserData findByUsernameAndPassword(String username, String password) {
        UserData user = userDataRepository.findByUserNameAndPassword(username, password);
        if (user != null && user.getpassword().equals(password)) {
            return user;
        } else {
            return null; 
        }
    }
}