package com.example.androidbookshop;

public class ShoppingItem {
    private String id;
    private String name;
    private String info;
    private String price;
    private Float ratedInfo;
    private int imageResource;
    private int cartedCount;

    public ShoppingItem(String name, String info, String price, Float ratedInfo, int imageResource, int cartedCount) {
        this.name = name;
        this.info = info;
        this.price = price;
        this.ratedInfo = ratedInfo;
        this.imageResource = imageResource;
        this.cartedCount = cartedCount;
    }

    public ShoppingItem() {}

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getPrice() {
        return price;
    }

    public Float getRatedInfo() {
        return ratedInfo;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getCartedCount() {return cartedCount;}

    public String _getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
