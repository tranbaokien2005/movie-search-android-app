package com.example.assigntwo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigntwo.R;
import com.example.assigntwo.model.FavoriteMovie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMovieListActivity extends AppCompatActivity {

    private static final int ADD_MOVIE_REQUEST = 1;

    private RecyclerView recyclerView;
    private FavoriteMovieAdapter adapter;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movie_list);

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recyclerViewFavorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavoriteMovieAdapter();
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        loadFavoriteMovies();

        // FloatingActionButton để thêm phim
        FloatingActionButton btnAddMovie = findViewById(R.id.btnAddMovie);
        btnAddMovie.setOnClickListener(v -> {
            Intent intent = new Intent(FavoriteMovieListActivity.this, AddMovieActivity.class);
            startActivityForResult(intent, ADD_MOVIE_REQUEST);
        });

        // Click item để chỉnh sửa
        adapter.setOnMovieClickListener(movie -> {
            Intent intent = new Intent(FavoriteMovieListActivity.this, EditMovieActivity.class);
            intent.putExtra("movieId", movie.getId());
            startActivityForResult(intent, ADD_MOVIE_REQUEST);
        });

        // Swipe để xoá
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                FavoriteMovie movie = adapter.getMovieAt(position);

                if (movie.getId() != null) {
                    db.collection("favorite_movies").document(movie.getId())
                            .delete()
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(FavoriteMovieListActivity.this, "Deleted: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                                loadFavoriteMovies(); // Refresh list
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(FavoriteMovieListActivity.this, "Delete failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                }
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void loadFavoriteMovies() {
        db.collection("favorite_movies")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<FavoriteMovie> movies = new ArrayList<>();
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        FavoriteMovie movie = doc.toObject(FavoriteMovie.class);
                        if (movie != null) {
                            movie.setId(doc.getId()); // Lưu ID
                            movies.add(movie);
                        }
                    }
                    adapter.setFavoriteMovies(movies);
                    Log.d("Firestore", "Loaded " + movies.size() + " favorite movies");
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error loading favorite movies", e);
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_MOVIE_REQUEST && resultCode == RESULT_OK) {
            loadFavoriteMovies();
        }
    }
}
