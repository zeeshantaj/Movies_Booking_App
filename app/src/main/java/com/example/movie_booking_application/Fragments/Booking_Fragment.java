package com.example.movie_booking_application.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.movie_booking_application.R;

public class Booking_Fragment extends Fragment {

    public Booking_Fragment() {
        // Required empty public constructor
    }
    private Button selectSeatBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking_, container, false);


        selectSeatBtn = view.findViewById(R.id.selectSeatBtn);

        selectSeatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}