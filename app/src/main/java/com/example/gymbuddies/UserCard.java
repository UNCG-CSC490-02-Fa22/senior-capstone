package com.example.gymbuddies;

public class UserCard {
    private String userId;
    private String name;
    private String profileImg;

    public UserCard(String userId, String name, String profileImg) {
        this.userId = userId;
        this.name = name;
        this.profileImg = profileImg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}
