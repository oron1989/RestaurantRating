package com.oron.restaurantrating.Model;

public class Restaurant {

    private String restaurantName;
    private String city;
    private String inspectionDate;
    private String restaurantId;

    public Restaurant() {
    }

    public Restaurant(String restaurantName, String city, String timestamp, String restaurantId) {
        this.restaurantName = restaurantName;
        this.city = city;
        this.inspectionDate = timestamp;
        this.restaurantId = restaurantId;
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
}
