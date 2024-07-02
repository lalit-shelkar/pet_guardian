package com.petguardian.Model;

public class ProductModelClass {
    private String name;
    private double rating;
    private String img;
    private String price;
    private String category;

    public ProductModelClass(String name,
            double rating,
            String img,
            String price,
            String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.rating = rating;
        this.img = img;

    }

    // Getters
    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public String getImg() {
        return img;
    }

    public String getPrice() {
        return price;
    }

    public String getCatgory() {
        return category;
    }

}
