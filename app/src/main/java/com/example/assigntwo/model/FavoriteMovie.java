package com.example.assigntwo.model;

public class FavoriteMovie {
    private String id;
    private String title;
    private String studio;
    private String rating;
    private String posterUrl;
    private String year; // ✅ Added

    public FavoriteMovie() {
        // Required for Firestore
    }

    public FavoriteMovie(String title, String studio, String rating, String posterUrl, String year) {
        this.title = title;
        this.studio = studio;
        this.rating = rating;
        this.posterUrl = posterUrl;
        this.year = year;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getStudio() { return studio; }
    public void setStudio(String studio) { this.studio = studio; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public String getPosterUrl() { return posterUrl; }
    public void setPosterUrl(String posterUrl) { this.posterUrl = posterUrl; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
}
