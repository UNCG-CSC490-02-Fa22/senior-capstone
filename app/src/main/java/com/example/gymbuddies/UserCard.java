package com.example.gymbuddies;

public class UserCard {
    private String userId;
    private String name;
    private String profileImg;
    private String age;

    public UserCard(String userId, String name, String profileImg) {
        this.userId = userId;
        this.name = name;
        this.profileImg = profileImg;
        //this.age = age;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}
