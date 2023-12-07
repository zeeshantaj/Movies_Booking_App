package com.example.movie_booking_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie_booking_application.Adapter.MoviesAdapter;
import com.example.movie_booking_application.Animator.ItemClickedAnimator;
import com.example.movie_booking_application.Model.Movies;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
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
    private ShimmerFrameLayout shimmerLayout;
    private final Handler shimmerHandler = new Handler(Looper.getMainLooper());
    private static final int SHIMMER_DELAY_MS = 1000; // Adjust the delay as needed
    private TextView availTxt;
    private MotionLayout motionLayout;

    private final Runnable stopShimmerRunnable = new Runnable() {
        @Override
        public void run() {
            stopShimmer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.movie_recycler);
        carousel = findViewById(R.id.carousel);
        shimmerLayout = findViewById(R.id.shimmerLayout);
        availTxt = findViewById(R.id.availTxt);
        motionLayout = findViewById(R.id.motionLayout);

        //shimmerLayout.setVisibility(View.VISIBLE);
        // shimmerLayout.startShimmer();

//        availTxt.setVisibility(View.GONE);

        startShimmer();
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
                    stopShimmer();
//                    shimmerLayout.stopShimmer();
//                    shimmerLayout.setVisibility(View.GONE);
                    //availTxt.setVisibility(View.VISIBLE);
                    adapter = new MoviesAdapter(moviesList,MainActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                shimmerLayout.stopShimmer();
//                shimmerLayout.setVisibility(View.GONE);
                //availTxt.setVisibility(View.VISIBLE);
                stopShimmer();
                Toast.makeText(MainActivity.this, "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MyApp","Error "+error.getMessage());
            }
        });

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(androidx.appcompat.R.id.action_bar_container),false);
        fade.excludeTarget(android.R.id.statusBarBackground,true);
        fade.excludeTarget(android.R.id.navigationBarBackground,true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

    }
    private void startShimmer() {
        shimmerLayout.setVisibility(View.VISIBLE);
        shimmerLayout.startShimmer();
        // Schedule a delayed call to stopShimmer() after a certain duration
        shimmerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopShimmer();
            }
        }, SHIMMER_DELAY_MS);
    }

    private void stopShimmer() {
        shimmerLayout.stopShimmer();
        shimmerLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        shimmerHandler.removeCallbacks(stopShimmerRunnable);

    }
}