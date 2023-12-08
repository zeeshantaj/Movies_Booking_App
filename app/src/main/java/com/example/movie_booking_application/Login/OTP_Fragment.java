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

public class OTP_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otp, container, false);

        Button next = view.findViewById(R.id.next2);


        next.setOnClickListener(v -> {
            LoginActivity.setFragment(new Setup_Profile_Fragment(),getActivity());

        });

        return view;
    }
}
