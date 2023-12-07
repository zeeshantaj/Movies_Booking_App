package com.example.movie_booking_application.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie_booking_application.Activity.Booking_Activity;
import com.example.movie_booking_application.Model.Movies;
import com.example.movie_booking_application.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
   private List<Movies> moviesList;

    private Context context;

    public MoviesAdapter(List<Movies> moviesList, Context context ) {
        this.moviesList = moviesList;
        this.context = context;
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
        holder.itemView.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Scale down the clicked item
                    view.setScaleX(0.9f);
                    view.setScaleY(0.9f);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    // Restore the original size when touch is released or canceled
                    view.setScaleX(1.0f);
                    view.setScaleY(1.0f);
                    break;
            }
            return false;
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrlText = moviesList.get(position).getImageUrl();
                String title = moviesList.get(position).getTitle();
                String des = moviesList.get(position).getDescription();

                Intent intent = new Intent(v.getContext(), Booking_Activity.class);

                intent.putExtra("des",des);
                intent.putExtra("title",title);
                intent.putExtra("imageUrl",imageUrlText);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) v.getContext(),
                        holder.imageUrl,   // Shared element: image view in the RecyclerView item
                        ViewCompat.getTransitionName(holder.imageUrl)); // Use transition name if needed


                v.getContext().startActivity(intent,options.toBundle());
            }
        });
        Glide.with(holder.itemView.getContext())
                .load(movies.getImageUrl())
                .into(holder.imageUrl);
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
