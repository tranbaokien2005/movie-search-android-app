package com.example.assigntwo.model;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("imdbID")
    private String imdbID;

    @SerializedName("Poster")
    private String posterUrl;

    @SerializedName("Plot")
    private String plot;

    @SerializedName("imdbRating")
    private String rating;

    @SerializedName("Production")
    private String studio;

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getPlot() {
        return plot != null ? plot : "Plot not available"; // Avoid null
    }

    public String getRating() {
        return rating != null ? rating : "N/A";
    }

    public String getStudio() {
        return studio != null ? studio : "Unknown Studio";
    }
}
