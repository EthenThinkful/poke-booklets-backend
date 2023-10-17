package com.ethenthinkful.pokemonapijectapi.model;

import java.sql.Timestamp;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="userdata")
public class UserData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String profilePic;
	private String nickName;

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="userdata") //could delete fetch=FetchType.EAGER because it could lead to more database queries resulting in performance issues 
	private List<CardData> cardData;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public List<CardData> getCardData() {
		return cardData;
	}

	public void setCardData(List<CardData> cardData) {
		this.cardData = cardData;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
