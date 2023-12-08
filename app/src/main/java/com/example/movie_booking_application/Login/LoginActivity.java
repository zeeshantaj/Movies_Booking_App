package com.example.movie_booking_application.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

import com.example.movie_booking_application.MainActivity;
import com.example.movie_booking_application.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;
    private static Fragment currentFragment;

    private static Button num, otp, setup;
    private int backPressCount;
    private SharedViewModel sharedViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        num = findViewById(R.id.numberBtn);
        otp = findViewById(R.id.otpBtn);
        setup = findViewById(R.id.setupBtn);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null){
            startActivity(new Intent(this, MainActivity.class));
        }
        else {
            fragmentManager = getSupportFragmentManager();
            currentFragment = fragmentManager.findFragmentById(R.id.login_frameLayout);
            currentFragment = new Number_Fragment();
            setFragment(currentFragment, this);
        }

    }

    public static void setColor(Context context) {
//        Drawable drawableActiveNumber = num.getCompoundDrawablesRelative()[0].mutate(); // Get drawableStart for numberBtn
//        Drawable drawableDefaultNumber = num.getCompoundDrawablesRelative()[0].mutate(); // Create a copy for default state
//
//        DrawableCompat.setTint(drawableActiveNumber, context.getResources().getColor(R.color.startColor));
//        DrawableCompat.setTint(drawableDefaultNumber, context.getResources().getColor(R.color.lightBlue));
//
//        if (currentFragment instanceof Number_Fragment) {
//            num.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableActiveNumber, null, null, null);
//            Log.e("MyApp", "instanceofNumber");
//        } else {
//            num.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableDefaultNumber, null, null, null);
//            Log.e("MyApp", "numElse");
//        }
//
//        Drawable drawableActiveOTP = otp.getCompoundDrawablesRelative()[0].mutate(); // Get drawableStart for otpBtn
//        Drawable drawableDefaultOTP = otp.getCompoundDrawablesRelative()[0].mutate(); // Create a copy for default state
//
//        DrawableCompat.setTint(drawableActiveOTP, context.getResources().getColor(R.color.startColor));
//        DrawableCompat.setTint(drawableDefaultOTP, context.getResources().getColor(R.color.lightBlue));
//
//        if (currentFragment instanceof OTP_Fragment) {
//            otp.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableActiveOTP, null, null, null);
//            Log.e("MyApp", "instanceofOTP");
//        } else {
//            otp.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableDefaultOTP, null, null, null);
//            Log.e("MyApp", "otpElse");
//        }
//        Drawable drawableActiveSetup = setup.getCompoundDrawablesRelative()[0].mutate(); // Get drawableStart for otpBtn
//        Drawable drawableDefaultSetup = setup.getCompoundDrawablesRelative()[0].mutate(); // Create a copy for default state
//
//        DrawableCompat.setTint(drawableActiveSetup, context.getResources().getColor(R.color.startColor));
//        DrawableCompat.setTint(drawableDefaultSetup, context.getResources().getColor(R.color.lightBlue));
//
//        if (currentFragment instanceof Setup_Profile_Fragment) {
//            setup.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableActiveSetup, null, null, null);
//            Log.e("MyApp", "instanceofOTP");
//        } else {
//            setup.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableDefaultSetup, null, null, null);
//            Log.e("MyApp", "otpElse");
//        }
        // todo working fine
//        Drawable drawableActiveNumber = ContextCompat.getDrawable(context, R.drawable.baseline_numbers_24).mutate(); // Get drawable for numberBtn
//        Drawable drawableDefaultNumber = ContextCompat.getDrawable(context, R.drawable.baseline_numbers_24).mutate(); // Create a copy for default state
//        DrawableCompat.setTint(drawableActiveNumber, ContextCompat.getColor(context, R.color.startColor));
//        DrawableCompat.setTint(drawableDefaultNumber, ContextCompat.getColor(context, R.color.lightBlue));
//
//        // OTP_Fragment drawable tint handling
//        Drawable drawableActiveOTP = ContextCompat.getDrawable(context, R.drawable.baseline_textsms_24).mutate(); // Get drawable for otpBtn
//        Drawable drawableDefaultOTP = ContextCompat.getDrawable(context, R.drawable.baseline_textsms_24).mutate(); // Create a copy for default state
//        DrawableCompat.setTint(drawableActiveOTP, ContextCompat.getColor(context, R.color.startColor));
//        DrawableCompat.setTint(drawableDefaultOTP, ContextCompat.getColor(context, R.color.lightBlue));
//
//
//        Drawable drawableActiveSetup = ContextCompat.getDrawable(context, R.drawable.setup).mutate(); // Get drawable for otpBtn
//        Drawable drawableDefaultSetup = ContextCompat.getDrawable(context, R.drawable.setup).mutate(); // Create a copy for default state
//        DrawableCompat.setTint(drawableActiveSetup, ContextCompat.getColor(context, R.color.startColor));
//        DrawableCompat.setTint(drawableDefaultSetup, ContextCompat.getColor(context, R.color.lightBlue));
//
//        if (currentFragment instanceof Number_Fragment) {
//            num.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableActiveNumber, null, null, null);
//            otp.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableDefaultOTP, null, null, null);
//            setup.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableDefaultSetup, null, null, null);
//            Log.e("MyApp", "instanceofNumber");
//        }
//        if (currentFragment instanceof OTP_Fragment) {
//            num.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableDefaultNumber, null, null, null);
//            otp.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableActiveOTP, null, null, null);
//            setup.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableDefaultSetup, null, null, null);
//            Log.e("MyApp", "instanceofOTP");
//        }
//        if (currentFragment instanceof Setup_Profile_Fragment) {
//            num.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableDefaultNumber, null, null, null);
//            otp.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableDefaultOTP, null, null, null);
//            setup.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableActiveSetup, null, null, null);
//            Log.e("MyApp", "instanceofSetup");
//        }
//        if (currentFragment instanceof Number_Fragment) {
//            num.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableActiveNumber, null, null, null);
//            Log.e("MyApp", "instanceofNumber");
//        } else {
//            num.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableDefaultNumber, null, null, null);
//            Log.e("MyApp", "numElse");
//        }
//        // Similar logic for other fragments and buttons...
//        if (currentFragment instanceof OTP_Fragment) {
//            otp.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableActiveOTP, null, null, null);
//            Log.e("MyApp", "instanceofOTP");
//        } else {
//            otp.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableDefaultOTP, null, null, null);
//            Log.e("MyApp", "otpElse");
//        }
//        // Setup_Fragment drawable tint handling
//        if (currentFragment instanceof Setup_Profile_Fragment) {
//            setup.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableActiveSetup, null, null, null);
//
//            Log.e("MyApp", "instanceofSetup");
//        } else {
//            setup.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableDefaultSetup, null, null, null);
//            Log.e("MyApp", "SetupElse");
//        }

        Drawable drawableNumber = ContextCompat.getDrawable(context, R.drawable.number).mutate();
        Drawable drawableOTP = ContextCompat.getDrawable(context, R.drawable.otp).mutate();
        Drawable drawableSetup = ContextCompat.getDrawable(context, R.drawable.setup).mutate();

        int colorActive = ContextCompat.getColor(context, R.color.startColor);
        int colorDefault = ContextCompat.getColor(context, R.color.lightBlue);

        if (currentFragment instanceof Number_Fragment) {
            setButtonDrawableTint(num, drawableNumber, colorActive);
            setButtonDrawableTint(otp, drawableOTP, colorDefault);
            setButtonDrawableTint(setup, drawableSetup, colorDefault);
        } else if (currentFragment instanceof OTP_Fragment) {
            setButtonDrawableTint(num, drawableNumber, colorDefault);
            setButtonDrawableTint(otp, drawableOTP, colorActive);
            setButtonDrawableTint(setup, drawableSetup, colorDefault);
        } else if (currentFragment instanceof Setup_Profile_Fragment) {
            setButtonDrawableTint(num, drawableNumber, colorDefault);
            setButtonDrawableTint(otp, drawableOTP, colorDefault);
            setButtonDrawableTint(setup, drawableSetup, colorActive);
        }
    }

    public static void setFragment(Fragment fragment, Context context) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.login_frameLayout, fragment);
        if (!(fragment instanceof Number_Fragment)) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
        currentFragment = fragment;
        setColor(context);
    }

    private static void setButtonDrawableTint(Button button, Drawable drawable, int color) {
        DrawableCompat.setTint(drawable, color);
        button.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null);
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager != null && fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();

            // Increment back press count
            backPressCount++;

            if (backPressCount % 2 == 0) {
                currentFragment = new Number_Fragment();
            } else {
                currentFragment = new OTP_Fragment();
            }

            setFragment(currentFragment, this);
            setColor(this);
        } else {
            super.onBackPressed();
        }
    }
}