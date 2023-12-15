package com.example.movie_booking_application.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.format.DateFormat;
import android.transition.Fade;
import android.util.Log;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.example.movie_booking_application.Fragments.Booking_Fragment;
import com.example.movie_booking_application.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.units.qual.C;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;

public class Booking_Activity extends AppCompatActivity {
    private ImageView add,minus;
    private TextView incrementText,title,des;
    private ImageView movieImage;
    private int count = 1;
    private Toolbar toolbar;
    private Button confirmBtn;
    private RadioGroup radioGroup;
    private String selectedText,imageUrl;
    private Button selectSeatBtn;
    private String titleStr,desStr,selectedSeatNumber;
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
        selectSeatBtn = findViewById(R.id.selectSeatBtn);


        Intent intent = getIntent();
        imageUrl = intent.getStringExtra("imageUrl");
        titleStr = intent.getStringExtra("title");
        desStr = intent.getStringExtra("des");


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

        selectSeatBtn.setOnClickListener(v -> {
            Intent intent1 = new Intent(this,SeatSelectActivity.class);
            intent1.putExtra("imageUrl",imageUrl);
            intent1.putExtra("title",titleStr);
            intent1.putExtra("des",desStr);
            startActivity(intent1);
        });

        setDetails();
        setToolbar();
        bookTicket();
    }
    private void setDetails(){

//        Intent intent = getIntent();
//        imageUrl = intent.getStringExtra("imageUrl");
//        String titleStr = intent.getStringExtra("title");
//        String desStr = intent.getStringExtra("des");



// Get SharedPreferences instance
//        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//
//// Save data to SharedPreferences
//        editor.putString("imageUrl", imageUrl);
//        editor.putString("title", titleStr);
//        editor.putString("des", desStr);
//        editor.apply();
//
//
//        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//
//        imageUrl = sharedPreferences.getString("imageUrl", "");
//        titleStr = sharedPreferences.getString("title", "");
//        desStr = sharedPreferences.getString("des", "");


        title.setText(titleStr);
        des.setText(desStr);
        Glide.with(this)
                .load(imageUrl)
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
        ConstraintLayout constraintLayout = findViewById(R.id.constraintContainer);


        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Booking_Request").child(uid);


        TextView seatsNumber = findViewById(R.id.seatNumberText);
        Intent intent = getIntent();
        selectedSeatNumber = intent.getStringExtra("selectedSeats");
        seatsNumber.setText("Seat Number: "+selectedSeatNumber);

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


            if (selectedSeatNumber == null){
                Toast.makeText(this, "Please Select seat", Toast.LENGTH_SHORT).show();
                return;
            }



            SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

            Calendar c = Calendar.getInstance();

            String curr_date
                    = DateFormat.format(c.getTime());


            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("movieTitle",title.getText().toString());
            hashMap.put("imageUrl",imageUrl);
            hashMap.put("timing",selectedText);
            hashMap.put("seatNo",selectedSeatNumber);
            hashMap.put("person",incrementText.getText().toString());
            hashMap.put("currentTime",curr_date);

            long currentTimeMillis = System.currentTimeMillis();
            String child = String.valueOf(currentTimeMillis);

            reference.child(child).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    confirmBtn.setEnabled(false);
                    Toast.makeText(Booking_Activity.this, "Booking Request sent", Toast.LENGTH_SHORT).show();

                    Snackbar snackbar = Snackbar.make(constraintLayout, "", Snackbar.LENGTH_INDEFINITE);
                    View customSnackbarView = getLayoutInflater().inflate(R.layout.custom_snake_bar, null);
                    snackbar.getView().setBackgroundColor(Color.TRANSPARENT); // Make Snackbar background transparent
                    @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                    snackbarLayout.addView(customSnackbarView, 0);

// Find the buttons in the custom layout
                    Button btnView = customSnackbarView.findViewById(R.id.btnView);
                    Button btnDismiss = customSnackbarView.findViewById(R.id.btnDismiss);

// Set click listeners for the buttons
                    btnView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Booking_Activity.this, Profile_Activity.class));
                            snackbar.dismiss(); // Dismiss the snackbar if needed
                        }
                    });

                    btnDismiss.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            snackbar.dismiss(); // Dismiss the snackbar if needed
                            // Your negative action logic here
                        }
                    });

                    snackbar.show();
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
