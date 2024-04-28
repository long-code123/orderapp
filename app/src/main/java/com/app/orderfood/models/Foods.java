package com.app.orderfood.models;

public class Foods {
    private long id;
    private int categoryId;
    private String description;
    private boolean bestFood;
    private int locationId;
    private double price;
    private String imagePath;
    private int priceId;
    private double star;
    private int timeId;
    private int timeValue;
    private String title;
    private int numberInCart;


    public Foods(long id, int categoryId, String description, boolean bestFood, int locationId, double price, String imagePath, int priceId, double star, int timeId, int timeValue, String title, int numberInCart) {
        this.id = id;
        this.categoryId = categoryId;
        this.description = description;
        this.bestFood = bestFood;
        this.locationId = locationId;
        this.price = price;
        this.imagePath = imagePath;
        this.priceId = priceId;
        this.star = star;
        this.timeId = timeId;
        this.timeValue = timeValue;
        this.title = title;
        this.numberInCart = numberInCart;
    }

    public Foods() {
    }

    @Override
    public String toString() {
        return title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBestFood() {
        return bestFood;
    }

    public void setBestFood(boolean bestFood) {
        this.bestFood = bestFood;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public int getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(int timeValue) {
        this.timeValue = timeValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
