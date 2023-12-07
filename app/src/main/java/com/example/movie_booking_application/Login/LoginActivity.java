package com.example.movie_booking_application.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

import com.example.movie_booking_application.R;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;
    private static Fragment currentFragment;

    private static Button num,otp,setup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        num = findViewById(R.id.numberBtn);
        otp = findViewById(R.id.otpBtn);
        setup = findViewById(R.id.setupBtn);

        fragmentManager = getSupportFragmentManager();
        currentFragment = fragmentManager.findFragmentById(R.id.login_frameLayout);
        currentFragment = new Number_Fragment();

        setFragment(currentFragment,this);
    }
    public static void setColor(Context context) {
        Drawable drawable = num.getBackground();
        Drawable drawableDefault = num.getBackground();

        DrawableCompat.setTint(drawable, context.getResources().getColor(R.color.startColor));
        DrawableCompat.setTint(drawableDefault, context.getResources().getColor(R.color.black));

        if (currentFragment instanceof Number_Fragment) {
            num.setBackground(drawable);
        } else {
            num.setBackground(drawableDefault);
        }
        if (currentFragment instanceof OTP_Fragment){
            otp.setBackground(drawable);
        }
        else {
            otp.setBackground(drawableDefault);
        }
        if (currentFragment instanceof Setup_Profile_Fragment){
            setup.setBackground(drawable);
        }
        else {
            setup.setBackground(drawableDefault);
        }

    }

    public static void setFragment(Fragment fragment, Context context) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.login_frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        currentFragment = fragment;
        setColor(context);
    }
}