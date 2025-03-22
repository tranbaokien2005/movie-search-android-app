package com.example.assigntwo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.assigntwo.databinding.ActivityMovieSearchBinding;
import com.example.assigntwo.viewmodel.MovieViewModel;
import com.example.assigntwo.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchActivity extends AppCompatActivity {
    private ActivityMovieSearchBinding binding;
    private MovieViewModel movieViewModel;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        movieAdapter = new MovieAdapter(this, new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(movieAdapter);

        binding.searchButton.setOnClickListener(v -> {
            String query = binding.searchEditText.getText().toString().trim();
            if (!query.isEmpty()) {
                movieViewModel.searchMovies(query);
            } else {
                Toast.makeText(this, "Please enter a movie name", Toast.LENGTH_SHORT).show();
            }
        });

        movieViewModel.getMovies().observe(this, this::updateUI);
        movieViewModel.getIsLoading().observe(this, isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });
    }

    private void updateUI(List<Movie> movies) {
        if (movies.isEmpty()) {
            Toast.makeText(this, "No movies found!", Toast.LENGTH_SHORT).show();
        }
        movieAdapter.updateMovies(movies);
    }
}
