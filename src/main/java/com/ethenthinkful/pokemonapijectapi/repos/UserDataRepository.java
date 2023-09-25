package com.ethenthinkful.pokemonapijectapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ethenthinkful.pokemonapijectapi.model.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {
    //---------------------------------new implement---------------------------------
    UserData findByUserNameAndPassword(String userName, String password);
    boolean existsByUserName(String userName);
    //---------------------------------end new implement---------------------------------
}
