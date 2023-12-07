package com.example.movie_booking_application.FragmentUtils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.movie_booking_application.Login.Number_Fragment;

public class FragmentUtils {
    public static void setFragment(FragmentManager fragmentManager, int fragmentContainer, Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(fragmentContainer,fragment);

        if (!(fragment instanceof Number_Fragment)){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
