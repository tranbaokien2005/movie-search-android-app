package com.example.assigntwo.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assigntwo.R;
import com.example.assigntwo.model.FavoriteMovie;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder> {

    private final List<FavoriteMovie> favoriteMovies = new ArrayList<>();

    // Interface để bắt sự kiện click vào item
    public interface OnMovieClickListener {
        void onMovieClick(FavoriteMovie movie);
    }

    private OnMovieClickListener listener;

    public void setOnMovieClickListener(OnMovieClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavoriteMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new FavoriteMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieViewHolder holder, int position) {
        FavoriteMovie movie = favoriteMovies.get(position);

        holder.titleTextView.setText(movie.getTitle());
        holder.yearTextView.setText(movie.getYear());
        holder.studioTextView.setText("Studio: " + movie.getStudio());

        Glide.with(holder.posterImageView.getContext())
                .load(movie.getPosterUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error_image)
                .into(holder.posterImageView);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMovieClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteMovies.size();
    }

    // Gọi mỗi lần load lại danh sách
    public void setFavoriteMovies(List<FavoriteMovie> movies) {
        favoriteMovies.clear();
        favoriteMovies.addAll(movies);
        notifyDataSetChanged();
    }

    // ✅ Trả về movie tại vị trí được chọn (dùng cho swipe delete)
    public FavoriteMovie getMovieAt(int position) {
        return favoriteMovies.get(position);
    }

    public static class FavoriteMovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, yearTextView, studioTextView;
        ImageView posterImageView;

        public FavoriteMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movieTitle);
            yearTextView = itemView.findViewById(R.id.movieYear);
            studioTextView = itemView.findViewById(R.id.movieStudio);
            posterImageView = itemView.findViewById(R.id.moviePoster);
        }
    }
}
