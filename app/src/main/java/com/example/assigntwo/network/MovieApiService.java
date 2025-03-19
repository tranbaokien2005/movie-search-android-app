package com.example.assigntwo.network;

import com.example.assigntwo.model.Movie;
import com.example.assigntwo.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("/")
    Call<MovieResponse> searchMovies(
            @Query("apikey") String apiKey,
            @Query("s") String query
    );

    @GET("/")
    Call<Movie> getMovieDetails(
            @Query("apikey") String apiKey,
            @Query("i") String imdbID
    );
}
