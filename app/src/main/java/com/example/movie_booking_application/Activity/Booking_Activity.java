package com.example.movie_booking_application.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.example.movie_booking_application.R;

public class Booking_Activity extends AppCompatActivity {
    private ImageView add,minus;
    private TextView incrementText,title,des;
    private ImageView movieImage;
    private int count = 1;
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
}
