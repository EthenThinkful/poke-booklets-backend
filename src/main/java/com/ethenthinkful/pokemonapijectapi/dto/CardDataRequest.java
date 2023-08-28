package com.ethenthinkful.pokemonapijectapi.dto;

import java.util.List;

public class CardDataRequest {
	private List<String> pokemonCard;
	private int userDataId;

	public List<String> pokemonCard(){
        return pokemonCard;
    }

	public void setPokemonCard(List<String> pokemonCard){
        this.pokemonCard = pokemonCard;
    }

	public int getUserDataId() {
		return userDataId;
	}

	public void setUserDataId(int userDataId) {
		this.userDataId = userDataId;
	}
}