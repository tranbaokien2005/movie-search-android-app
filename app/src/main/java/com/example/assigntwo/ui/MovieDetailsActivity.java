package com.example.assigntwo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.assigntwo.R;
import com.example.assigntwo.databinding.ActivityMovieDetailsBinding;
import com.example.assigntwo.viewmodel.MovieDetailsViewModel;

public class MovieDetailsActivity extends AppCompatActivity {
    private ActivityMovieDetailsBinding binding;
    private MovieDetailsViewModel movieDetailsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Khởi tạo ViewModel
        movieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);

        // Xử lý sự kiện bấm nút Back
        binding.btnBack.setOnClickListener(v -> finish());

        // Nhận IMDb ID từ Intent
        String imdbID = getIntent().getStringExtra("imdbID");
        if (imdbID != null) {
            movieDetailsViewModel.fetchMovieDetails(imdbID);
        } else {
            Toast.makeText(this, "Movie ID not found!", Toast.LENGTH_SHORT).show();
            finish();
        }

        movieDetailsViewModel.getMovie().observe(this, movie -> {
            if (movie != null) {
                binding.tvTitle.setText(movie.getTitle());
                binding.tvYear.setText(movie.getYear());
                binding.tvRating.setText("IMDb: " + movie.getRating());
                binding.tvPlot.setText(movie.getPlot());

                Glide.with(this)
                        .load(movie.getPosterUrl())
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.error_image)
                        .into(binding.ivPoster);
            }
        });

        movieDetailsViewModel.getIsLoading().observe(this, isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });
    }
}
