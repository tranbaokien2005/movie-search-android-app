package com.example.assigntwo.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assigntwo.R;
import com.example.assigntwo.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final List<Movie> movieList;
    private final Context context;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = new ArrayList<>(movieList); // ✅ Khởi tạo danh sách để tránh lỗi NullPointerException
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        // Hiển thị thông tin phim
        holder.titleTextView.setText(movie.getTitle());
        holder.yearTextView.setText(movie.getYear());
        holder.studioTextView.setText("Studio: " + movie.getStudio()); // ✅ Hiển thị studio

        // Hiển thị poster với Glide
        Glide.with(holder.posterImageView.getContext())
                .load(movie.getPosterUrl())
                .placeholder(R.drawable.placeholder) // Hình ảnh chờ khi tải
                .error(R.drawable.error_image) // Hình ảnh lỗi
                .into(holder.posterImageView);

        // Xử lý sự kiện click vào phim
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MovieDetailsActivity.class);
            intent.putExtra("imdbID", movie.getImdbID());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    // ✅ Thêm phương thức updateMovies() để cập nhật danh sách phim
    public void updateMovies(List<Movie> newMovies) {
        movieList.clear();
        movieList.addAll(newMovies);
        notifyDataSetChanged();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, yearTextView, studioTextView; // ✅ Đảm bảo TextView studio tồn tại
        ImageView posterImageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movieTitle);
            yearTextView = itemView.findViewById(R.id.movieYear);
            studioTextView = itemView.findViewById(R.id.movieStudio); // ✅ Đảm bảo item_movie.xml có id này
            posterImageView = itemView.findViewById(R.id.moviePoster);
        }
    }
}
