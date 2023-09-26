package com.ethenthinkful.pokemonapijectapi.dto;

public class CardDataRequest {
	private String pokemonCard;
	private int userDataId;

    public String getPokemonCard(){
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