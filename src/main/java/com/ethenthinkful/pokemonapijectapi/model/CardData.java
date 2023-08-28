package com.ethenthinkful.pokemonapijectapi.model;

import java.sql.Timestamp;
import java.util.List;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.persistence.OneToMany;

@Entity
@Table(name="carddata")
public class CardData{
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int userDataId;
    @Column(columnDefinition = "string[]") // Mapping to PostgreSQL string array
    private List<String> pokemonCard;
    private int order;
    private Timestamp measuredDateTime;
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userdata_id",nullable=false)
	@JsonIgnore
	private UserData userData;

    public int id(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int userDataId(){
        return userDataId;
    }

    public void setUserDataId(int userDataId){
        this.userDataId = userDataId;
    }

    public List<String> pokemonCard(){
        return pokemonCard;
    }

    public void setPokemonCard(List<String> pokemonCard){
        this.pokemonCard = pokemonCard;
    }

    public int order(){
        return order;
    }

    public void setOrder(int order){
        this.order = order;
    }

    public Timestamp measuredDateTime(){
        return measuredDateTime;
    }

    public void setMeasuredDateTime(Timestamp measuredDateTime){
        this.measuredDateTime = measuredDateTime;
    }

    public UserData userData(){
        return userData;
    }

    public void setUserData(UserData userData){
        this.userData = userData;
    }

}