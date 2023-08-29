package com.ethenthinkful.pokemonapijectapi.model;

import java.sql.Timestamp;
//import java.util.ArrayList;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="carddata")
public class CardData{
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private int userDataId;
    // @Column(name = "pokemoncard", columnDefinition = "TEXT[]") 
    // private ArrayList<String> pokemonCard;
    //@Column(name = "pokemoncard", columnDefinition = "TEXT") 
    private String pokemonCard;  
    private Timestamp measuredDateTime;

    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userdata_id",nullable=false)
	@JsonIgnore
    // @Column(name="user_data")
	private UserData userdata;

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

    // public List<String> pokemonCard(){
    //     return pokemonCard;
    // }

    // public void setPokemonCard(List<String> pokemonCard){
    //     this.pokemonCard = pokemonCard;
    // }

    public String pokemonCard(){
        return pokemonCard;
    }
    
    public void setPokemonCard(String pokemonCard){
        this.pokemonCard = pokemonCard;
    }

    public Timestamp measuredDateTime(){
        return measuredDateTime;
    }

    public void setMeasuredDateTime(Timestamp measuredDateTime){
        this.measuredDateTime = measuredDateTime;
    }

    public UserData userdata(){
        return userdata;
    }

    public void setUserData(UserData userdata){
        this.userdata = userdata;
    }

}