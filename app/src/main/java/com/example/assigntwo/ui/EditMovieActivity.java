package com.example.assigntwo.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assigntwo.R;
import com.example.assigntwo.model.FavoriteMovie;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditMovieActivity extends AppCompatActivity {

    private EditText etTitle, etYear, etStudio, etPosterUrl, etRating;
    private Button btnUpdateMovie, btnCancel;
    private FirebaseFirestore db;

    private String movieId; // Firestore document ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        // Get passed movieId
        movieId = getIntent().getStringExtra("movieId");

        // Bind views
        etTitle = findViewById(R.id.etTitle);
        etYear = findViewById(R.id.etYear);
        etStudio = findViewById(R.id.etStudio);
        etPosterUrl = findViewById(R.id.etPosterUrl);
        etRating = findViewById(R.id.etRating);
        btnUpdateMovie = findViewById(R.id.btnUpdateMovie);
        btnCancel = findViewById(R.id.btnCancel); // ✅ cancel button

        db = FirebaseFirestore.getInstance();

        // Load movie info
        loadMovieDetails();

        btnUpdateMovie.setOnClickListener(v -> updateMovie());

        btnCancel.setOnClickListener(v -> finish()); // ✅ close screen if cancel
    }

    private void loadMovieDetails() {
        db.collection("favorite_movies")
                .document(movieId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    FavoriteMovie movie = documentSnapshot.toObject(FavoriteMovie.class);
                    if (movie != null) {
                        etTitle.setText(movie.getTitle());
                        etYear.setText(movie.getYear());
                        etStudio.setText(movie.getStudio());
                        etPosterUrl.setText(movie.getPosterUrl());
                        etRating.setText(movie.getRating());
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to load movie", Toast.LENGTH_SHORT).show();
                    finish();
                });
    }

    private void updateMovie() {
        String title = etTitle.getText().toString().trim();
        String year = etYear.getText().toString().trim();
        String studio = etStudio.getText().toString().trim();
        String posterUrl = etPosterUrl.getText().toString().trim();
        String rating = etRating.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(year) ||
                TextUtils.isEmpty(studio) || TextUtils.isEmpty(posterUrl)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        FavoriteMovie updatedMovie = new FavoriteMovie(title, studio, rating, posterUrl, year);
        updatedMovie.setId(movieId);

        DocumentReference docRef = db.collection("favorite_movies").document(movieId);
        docRef.set(updatedMovie)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Movie updated successfully", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Update failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
