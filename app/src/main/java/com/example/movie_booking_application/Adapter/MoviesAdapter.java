package com.example.movie_booking_application.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_booking_application.Activity.Booking_Activity;
import com.example.movie_booking_application.Model.Movies;
import com.example.movie_booking_application.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
   private List<Movies> moviesList;

    public MoviesAdapter(List<Movies> moviesList) {
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.avail_movie_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {
        Movies movies = moviesList.get(position);
        holder.title.setText(movies.getTitle());
        holder.des.setText(movies.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Booking_Activity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,des;
        private ImageView imageUrl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            des = itemView.findViewById(R.id.movie_des);
            imageUrl = itemView.findViewById(R.id.movie_img);
        }
    }
}
