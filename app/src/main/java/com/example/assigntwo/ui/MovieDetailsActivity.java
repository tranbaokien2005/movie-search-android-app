package com.example.assigntwo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.assigntwo.R;
import com.example.assigntwo.model.Movie;
import com.example.assigntwo.network.MovieApiService;
import com.example.assigntwo.network.RetrofitClient;
import com.example.assigntwo.BuildConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {
    private ImageView posterImageView;
    private TextView titleTextView, yearTextView, ratingTextView, plotTextView;
    private ImageButton btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Ánh xạ View từ XML
        btnBack = findViewById(R.id.btnBack);
        posterImageView = findViewById(R.id.ivPoster);
        titleTextView = findViewById(R.id.tvTitle);
        yearTextView = findViewById(R.id.tvYear);
        ratingTextView = findViewById(R.id.tvRating);
        plotTextView = findViewById(R.id.tvPlot);

        // Xử lý sự kiện bấm nút Back
        btnBack.setOnClickListener(v -> finish());

        // Nhận IMDb ID từ Intent
        String imdbID = getIntent().getStringExtra("imdbID");
        if (imdbID != null) {
            fetchMovieDetails(imdbID);
        }
    }

    private void fetchMovieDetails(String imdbID) {
        MovieApiService apiService = RetrofitClient.getRetrofitInstance().create(MovieApiService.class);
        apiService.getMovieDetails(BuildConfig.OMDB_API_KEY, imdbID).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Movie movie = response.body();
                    titleTextView.setText(movie.getTitle());
                    yearTextView.setText(movie.getYear());
                    ratingTextView.setText("IMDb: " + movie.getRating());
                    plotTextView.setText(movie.getPlot());

                    Glide.with(MovieDetailsActivity.this)
                            .load(movie.getPosterUrl())
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.error_image)
                            .into(posterImageView);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
