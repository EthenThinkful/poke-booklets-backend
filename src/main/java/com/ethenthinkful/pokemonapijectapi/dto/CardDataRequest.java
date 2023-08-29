package com.ethenthinkful.pokemonapijectapi.dto;

import java.util.ArrayList;

public class CardDataRequest {
	// private List<String> pokemonCard;
	private String pokemonCard;
	private int userDataId;

    public String pokemonCard(){
		return pokemonCard;
	}

	public void setPokemonCard(String pokemonCard){
		this.pokemonCard = pokemonCard;
	}

	public int getUserDataId() {
		return userDataId;
	}

	public void setUserDataId(int userDataId) {
		this.userDataId = userDataId;
	}
}