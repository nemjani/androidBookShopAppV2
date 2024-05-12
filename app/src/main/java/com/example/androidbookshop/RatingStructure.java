package com.example.androidbookshop;

public class RatingStructure {
    private String userEmail;
    private int rating;

    public RatingStructure() {
        // Üres konstruktor szükséges Firestore adatmodellhez
    }

    public RatingStructure(String userEmail, int rating) {
        this.userEmail = userEmail;
        this.rating = rating;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
