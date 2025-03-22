package com.example.assigntwo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assigntwo.model.Movie;
import com.example.assigntwo.model.MovieResponse;
import com.example.assigntwo.network.MovieApiService;
import com.example.assigntwo.network.RetrofitClient;
import com.example.assigntwo.BuildConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsViewModel extends ViewModel {
    private final MutableLiveData<Movie> movie = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public LiveData<Movie> getMovie() {
        return movie;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void fetchMovieDetails(String imdbID) {
        isLoading.setValue(true);
        MovieApiService apiService = RetrofitClient.getRetrofitInstance().create(MovieApiService.class);
        apiService.getMovieDetails(BuildConfig.OMDB_API_KEY, imdbID).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    movie.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                isLoading.setValue(false);
            }
        });
    }
}
