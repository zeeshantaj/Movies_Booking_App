package com.example.movie_booking_application.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.example.movie_booking_application.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.units.qual.C;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Booking_Activity extends AppCompatActivity {
    private ImageView add,minus;
    private TextView incrementText,title,des;
    private ImageView movieImage;
    private int count = 1;
    private Toolbar toolbar;
    private Button confirmBtn;
    private RadioGroup radioGroup;
    String selectedText;
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
        radioGroup = findViewById(R.id.radioGroup);
        confirmBtn = findViewById(R.id.confirmBtn);

        add.setOnClickListener(v -> {
            add.setEnabled(true);
            count++;
            incrementText.setText(String.valueOf(count));
            if (count == 100){
                Toast.makeText(this, "Limit Exceed", Toast.LENGTH_SHORT).show();
                add.setEnabled(false);
            }
        });
        minus.setOnClickListener(v -> {
            if (count > 1){
                count--;
                incrementText.setText(String.valueOf(count));
            }
        });
        confirmBtn.setOnClickListener(v -> {

        });
        setDetails();
        setToolbar();
        bookTicket();
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
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(androidx.appcompat.R.id.action_bar_container),false);
        fade.excludeTarget(android.R.id.statusBarBackground,true);
        fade.excludeTarget(android.R.id.navigationBarBackground,true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
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

    private void bookTicket(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Booking_Request").child(uid);

        confirmBtn.setOnClickListener(v -> {
            int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

            if (selectedRadioButtonId != -1){
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                // Get the text of the selected RadioButton
                selectedText = selectedRadioButton.getText().toString();
            }
            else {
                Toast.makeText(this, "Please Select Time of Show ", Toast.LENGTH_SHORT).show();
                return;
            }

            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            String currentTime = String.valueOf(date);
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("MovieTitle",title.getText().toString());
            hashMap.put("timing",selectedText);
            hashMap.put("person",incrementText.getText().toString());
            hashMap.put("currentTime",currentTime);
            reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    confirmBtn.setEnabled(false);
                    Toast.makeText(Booking_Activity.this, "Booking Request sent", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    confirmBtn.setEnabled(true);
                    Toast.makeText(Booking_Activity.this, "Error "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        });
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
