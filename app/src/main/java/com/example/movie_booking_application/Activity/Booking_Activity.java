package com.example.movie_booking_application.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.example.movie_booking_application.R;

public class Booking_Activity extends AppCompatActivity {
    private ImageView add,minus;
    private TextView incrementText,title,des;
    private ImageView movieImage;
    private int count = 1;
    private Toolbar toolbar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        movieImage = findViewById(R.id.movie_img_book);
        title = findViewById(R.id.movie_title_book);
        des = findViewById(R.id.movie_des_book);
        add = findViewById(R.id.addBtn);
        minus = findViewById(R.id.minusBtn);
        incrementText = findViewById(R.id.incrementTxt);

        add.setOnClickListener(v -> {
            count++;
            incrementText.setText(String.valueOf(count));
        });
        minus.setOnClickListener(v -> {
            if (count > 1){
                count--;
                incrementText.setText(String.valueOf(count));
            }
        });
        setDetails();
        setToolbar();
    }
    private void setDetails(){
        Intent intent = getIntent();
        String url = intent.getStringExtra("imageUrl");
        String titleStr = intent.getStringExtra("title");
        String desStr = intent.getStringExtra("des");

        title.setText(titleStr);
        des.setText(desStr);
        Glide.with(this)
                .load(url)
                .into(movieImage);
    }

    private void setToolbar(){
        toolbar = findViewById(R.id.bookToolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
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
