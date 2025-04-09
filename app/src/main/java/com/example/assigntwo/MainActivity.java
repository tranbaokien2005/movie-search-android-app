package com.example.assigntwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnFavoriteMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Áp dụng insets
        View rootView = findViewById(android.R.id.content);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Mở FavoriteMovieListActivity (nếu bạn có nút để mở)
        btnFavoriteMovies = findViewById(R.id.btnFavoriteMovies);
        btnFavoriteMovies.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, com.example.assigntwo.ui.FavoriteMovieListActivity.class);
            startActivity(intent);
        });
    }
}
