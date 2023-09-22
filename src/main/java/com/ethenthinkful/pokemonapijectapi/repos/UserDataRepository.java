package com.ethenthinkful.pokemonapijectapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ethenthinkful.pokemonapijectapi.model.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {
    //---------------------------------new implement---------------------------------
    UserData findByUserName(String userName);
    //---------------------------------end new implement---------------------------------
}
