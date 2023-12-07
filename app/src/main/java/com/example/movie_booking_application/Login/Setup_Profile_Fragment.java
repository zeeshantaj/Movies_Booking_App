package com.example.movie_booking_application.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.movie_booking_application.R;

public class Setup_Profile_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_setup, container, false);
        Button next = view.findViewById(R.id.next3);

        next.setOnClickListener(v -> {

            //LoginActivity.setFragment(new OTP_Fragment(),getActivity());
        });

        return view;
    }
}
