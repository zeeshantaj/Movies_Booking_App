package com.example.movie_booking_application.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.movie_booking_application.FragmentUtils.FragmentUtils;
import com.example.movie_booking_application.R;

public class Booking_Fragment extends Fragment {

    public Booking_Fragment() {
        // Required empty public constructor
    }
    private Button selectSeatBtn;
    private ImageView add,minus;
    private TextView incrementText,title,des;
    private ImageView movieImage;
    private int count = 1;
    private Toolbar toolbar;
    private Button confirmBtn;
    private RadioGroup radioGroup;
    private String selectedText,imageUrl;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String titleStr,desStr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking_, container, false);

        movieImage = view.findViewById(R.id.movie_img_book);
        title = view.findViewById(R.id.movie_title_book);
        des = view.findViewById(R.id.movie_des_book);
        add = view.findViewById(R.id.addBtn);
        minus = view.findViewById(R.id.minusBtn);
        incrementText = view.findViewById(R.id.incrementTxt);
        radioGroup = view.findViewById(R.id.radioGroup);
        confirmBtn = view.findViewById(R.id.confirmBtn);
        selectSeatBtn = view.findViewById(R.id.selectSeatBtn);

        return view;
    }
}