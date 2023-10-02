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
	private String password;
	private String profilePic;

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

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
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

}
