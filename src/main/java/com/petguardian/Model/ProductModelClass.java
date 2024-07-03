package com.petguardian.Model;

public class ProductModelClass {
    private String _id;
    private String name;
    private double rating;
    private String img;
    private String price;
    private String category;

    public ProductModelClass(String _id, String name, double rating, String img, String price, String category) {
        this._id = _id;
        this.name = name;
        this.rating = rating;
        this.img = img;
        this.price = price;
        this.category = category;
    }

    // Getters
    public String _id() {
        return _id;
    }

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

    public String getCategory() {
        return category;
    }
}
