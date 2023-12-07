package com.example.movie_booking_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.movie_booking_application.Adapter.MoviesAdapter;
import com.example.movie_booking_application.Animator.ItemClickedAnimator;
import com.example.movie_booking_application.Model.Movies;
import com.google.android.material.carousel.CarouselStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselOnScrollListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<Movies> moviesList;
    private MoviesAdapter adapter;
    private ImageCarousel carousel;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.movie_recycler);
        carousel = findViewById(R.id.carousel);

        moviesList= new ArrayList<>();
        initRecycler();
    }
    private void initRecycler(){
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        ItemClickedAnimator clickedItemAnimator = new ItemClickedAnimator();
        recyclerView.setItemAnimator(clickedItemAnimator);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Available_Movies");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                        Movies movies1 = dataSnapshot.getValue(Movies.class);
                        url = movies1.getImageUrl();
                        moviesList.add(movies1);
                        Log.e("MyApp","mainUrl"+url);
                        carousel.addData(new CarouselItem(url));

                    }
                    adapter = new MoviesAdapter(moviesList);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MyApp","Error "+error.getMessage());
            }
        });
    }
}