package com.ethenthinkful.pokemonapijectapi.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

public class CardDataRequest {
	private String pokemonCard;
	private int userDataId;
	private boolean verified;
	private int cardSlot;
	private String luhthang;

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

	public boolean getVerified(){
        return verified;
    }

    public void setVerified(boolean verified){
        this.verified = verified;
    }

	public int getCardSlot() {
		return cardSlot;
	}

	public void setCardSlot(int cardSlot) {
		this.cardSlot = cardSlot;
	}

	public String getLuhthang() {
        return luhthang;
    }

    public void setLuhThang(String luhthang) {
        this.luhthang = luhthang;
    }

}