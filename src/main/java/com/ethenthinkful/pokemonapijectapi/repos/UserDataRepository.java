package com.ethenthinkful.pokemonapijectapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ethenthinkful.pokemonapijectapi.model.CardData;
import com.ethenthinkful.pokemonapijectapi.model.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {
    UserData findByUserNameAndPassword(String userName, String password);
    boolean existsByUserName(String userName);
    //---------------------------------new implement---------------------------------
    CardData save(CardData cardToVerify);
    //---------------------------------end new implement---------------------------------
}
