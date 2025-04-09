package com.example.assigntwo.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assigntwo.R;
import com.example.assigntwo.model.FavoriteMovie;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddMovieActivity extends AppCompatActivity {

    private EditText etTitle, etYear, etStudio, etPosterUrl, etRating;
    private Button btnSaveMovie, btnCancel;
    private FirebaseFirestore db;
    private String movieId = null; // ✅ Kiểm tra xem đang Thêm hay Sửa

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        // Ánh xạ view
        etTitle = findViewById(R.id.etTitle);
        etYear = findViewById(R.id.etYear);
        etStudio = findViewById(R.id.etStudio);
        etPosterUrl = findViewById(R.id.etPosterUrl);
        etRating = findViewById(R.id.etRating);
        btnSaveMovie = findViewById(R.id.btnSaveMovie);
        btnCancel = findViewById(R.id.btnCancel); // ✅ Thêm nút Cancel

        db = FirebaseFirestore.getInstance();

        // ✅ Nếu đang ở chế độ chỉnh sửa (update)
        if (getIntent() != null && getIntent().hasExtra("movie_id")) {
            movieId = getIntent().getStringExtra("movie_id");
            etTitle.setText(getIntent().getStringExtra("title"));
            etYear.setText(getIntent().getStringExtra("year"));
            etStudio.setText(getIntent().getStringExtra("studio"));
            etPosterUrl.setText(getIntent().getStringExtra("posterUrl"));
            etRating.setText(getIntent().getStringExtra("rating"));
            btnSaveMovie.setText("Update Movie");
        }

        // ✅ Lưu hoặc cập nhật movie
        btnSaveMovie.setOnClickListener(v -> {
            if (TextUtils.isEmpty(etTitle.getText()) ||
                    TextUtils.isEmpty(etYear.getText()) ||
                    TextUtils.isEmpty(etStudio.getText()) ||
                    TextUtils.isEmpty(etPosterUrl.getText())) {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (movieId != null) {
                updateMovie();
            } else {
                addNewMovie();
            }
        });

        // ✅ Xử lý nút Cancel
        btnCancel.setOnClickListener(v -> {
            finish(); // Đóng activity nếu người dùng bấm Cancel
        });
    }

    private void addNewMovie() {
        FavoriteMovie movie = new FavoriteMovie(
                etTitle.getText().toString().trim(),
                etStudio.getText().toString().trim(),
                etRating.getText().toString().trim(),
                etPosterUrl.getText().toString().trim(),
                etYear.getText().toString().trim()
        );

        db.collection("favorite_movies")
                .add(movie)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Movie added successfully", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error adding movie: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void updateMovie() {
        Map<String, Object> updated = new HashMap<>();
        updated.put("title", etTitle.getText().toString().trim());
        updated.put("year", etYear.getText().toString().trim());
        updated.put("studio", etStudio.getText().toString().trim());
        updated.put("posterUrl", etPosterUrl.getText().toString().trim());
        updated.put("rating", etRating.getText().toString().trim());

        db.collection("favorite_movies").document(movieId)
                .update(updated)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "Movie updated", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error updating movie: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
