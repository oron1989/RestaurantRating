package com.oron.restaurantrating.Model;

public class Restaurant {

    private String restaurantName;
    private String city;
    private String inspectionDate;
    private String restaurantId;
    private String score;
    private String grade;
    private String userId;

    public Restaurant() {
    }

    public Restaurant(String restaurantName, String city, String timestamp, String restaurantId) {
        this.restaurantName = restaurantName;
        this.city = city;
        this.inspectionDate = timestamp;
        this.restaurantId = restaurantId;
    }

    public Restaurant(String restaurantName, String city, String inspectionDate, String restaurantId, String score, String grade, String userId) {
        this.restaurantName = restaurantName;
        this.city = city;
        this.inspectionDate = inspectionDate;
        this.restaurantId = restaurantId;
        this.score = score;
        this.grade = grade;
        this.userId = userId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
