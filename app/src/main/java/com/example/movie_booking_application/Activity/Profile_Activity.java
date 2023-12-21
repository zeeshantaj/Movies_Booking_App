package com.example.movie_booking_application.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movie_booking_application.Adapter.MoviesAdapter;
import com.example.movie_booking_application.Adapter.TicketAdapter;
import com.example.movie_booking_application.MainActivity;
import com.example.movie_booking_application.Model.BookingModel;
import com.example.movie_booking_application.Model.Movies;
import com.example.movie_booking_application.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Profile_Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private TicketAdapter adapter;
    private List<BookingModel> ticketList;
    private RecyclerView recyclerView;
    private ImageView profileImage;
    private TextView profileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImage = findViewById(R.id.profileImage);
        profileName = findViewById(R.id.profileName);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

// Retrieve data using keys
        String name = sharedPreferences.getString("shareName", ""); // Default value "" if key not found
        String imageUrl = sharedPreferences.getString("shareImageUrl", ""); // Default value "" if key not found


        Glide.with(this)
                .load(imageUrl)
                .into(profileImage);
        profileName.setText(name);

        ticketList = new ArrayList<>();
        setToolbar();
        initRecycler();
    }
    private void initRecycler(){
        recyclerView = findViewById(R.id.profileRecycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // Using PagerSnapHelper for one-item-at-a-time scrolling
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(layoutManager);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Booking_Request").child(uid);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ticketList.clear();
                if (snapshot.exists()) {
                  for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                      //String key = String.valueOf(dataSnapshot.getKey());
                      String key = dataSnapshot.getKey();
                      Log.e("MyApp","keys ->"+dataSnapshot.getKey());

                      BookingModel model = dataSnapshot.getValue(BookingModel.class);
                      model.setTicketId(key);
                      ticketList.add(model);
                  }
                  adapter = new TicketAdapter(ticketList);
                  recyclerView.setAdapter(adapter);
                  adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MyApp", "Error " + error.getMessage());
                Toast.makeText(Profile_Activity.this, "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setToolbar(){
        toolbar = findViewById(R.id.bookedToolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.inflateMenu(R.menu.main_manu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            getOnBackPressedDispatcher().onBackPressed();

            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}