package com.example.movie_booking_application.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movie_booking_application.R;

public class Booking_Activity extends AppCompatActivity {
    private ImageView add,minus;
    private TextView incrementText;
    private int count = 1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

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
    }
}
