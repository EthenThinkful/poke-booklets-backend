package com.ethenthinkful.pokemonapijectapi.dto;

public class UserDataRequest {
    private int id;
    private String userName;
	private String password;
	private String profilePic;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return this.profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    
}
