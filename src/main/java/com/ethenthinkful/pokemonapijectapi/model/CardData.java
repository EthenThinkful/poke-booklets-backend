package com.ethenthinkful.pokemonapijectapi.model;

import java.sql.Timestamp;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

//grabbing local image
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Entity
@Table(name = "carddata")
public class CardData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String pokemonCard;
    private Timestamp measuredDateTime;
    private boolean verified;
    private String luhthang;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userdata_id", nullable = false)
    @JsonIgnore
    private UserData userdata;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPokemonCard() {
        return pokemonCard;
    }

    public void setPokemonCard(String pokemonCard) {
        this.pokemonCard = pokemonCard;
    }

    public Timestamp getMeasuredDateTime() {
        return measuredDateTime;
    }

    public void setMeasuredDateTime(Timestamp measuredDateTime) {
        this.measuredDateTime = measuredDateTime;
    }

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public UserData getUserdata() {
        return userdata;
    }

    public void setUserData(UserData userdata) {
        this.userdata = userdata;
    }

    public String getLuhthang() throws IOException {
        // String fileName = luhthang;
        // Path basePath = Path.of(System.getProperty("user.dir"));
        // Path filePath = basePath.resolve(fileName);
        // byte[] imageBytes = Files.readAllBytes(filePath);
        return luhthang;
    }

    public void setLuhThang(String luhthang) {
        this.luhthang = luhthang;
    }

}