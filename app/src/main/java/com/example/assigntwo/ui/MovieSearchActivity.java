package com.example.assigntwo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigntwo.BuildConfig;
import com.example.assigntwo.R;
import com.example.assigntwo.model.MovieResponse;
import com.example.assigntwo.network.MovieApiService;
import com.example.assigntwo.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;

public class MovieSearchActivity extends AppCompatActivity {
    private EditText searchEditText;
    private Button searchButton;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);

        movieAdapter = new MovieAdapter(this, new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);

        searchButton.setOnClickListener(v -> {
            String query = searchEditText.getText().toString().trim();
            if (!query.isEmpty()) {
                searchMovies(query);
            }
        });
    }

    private void searchMovies(String query) {
        progressBar.setVisibility(View.VISIBLE);
        MovieApiService apiService = RetrofitClient.getRetrofitInstance().create(MovieApiService.class);
        apiService.searchMovies(BuildConfig.OMDB_API_KEY, query).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    movieAdapter.updateMovies(response.body().getMovies());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
