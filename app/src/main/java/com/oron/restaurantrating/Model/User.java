package com.oron.restaurantrating.Model;

public class User {
    String firstName;
    String lastName;
    String image;
    String userId;

    public User() {
    }

    public User(String firstName, String lastName, String image, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
