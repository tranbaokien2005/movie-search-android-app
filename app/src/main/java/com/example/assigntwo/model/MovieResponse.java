package com.example.assigntwo.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieResponse {
    @SerializedName("Search")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }
}
