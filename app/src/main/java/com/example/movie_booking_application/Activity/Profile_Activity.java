package com.example.movie_booking_application.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movie_booking_application.Adapter.TicketAdapter;
import com.example.movie_booking_application.Model.BookingModel;
import com.example.movie_booking_application.R;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.channels.Receive;

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

        Intent intent = getIntent();
        String name = intent.getStringExtra("personName");
        String imageUrl = intent.getStringExtra("imageUrl");
        profileImage = findViewById(R.id.profileImage);
        profileName = findViewById(R.id.profileName);


        recyclerView = findViewById(R.id.profileRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        ticketList = new ArrayList<>();
        adapter = new TicketAdapter(ticketList);


        setToolbar();
    }
    private void initRecycler(){

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