package com.example.movie_booking_application.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movie_booking_application.R;

public class Seat_Fragment extends Fragment {

    public Seat_Fragment() {
        // Required empty public constructor
    }
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seat_, container, false);
    }
    private void initializeBottomSheet() {

        // init the bottom sheet behavior
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet);

        // change the state of the bottom sheet
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        // change the state of the bottom sheet
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        // set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (isAdded()) {
                    transitionBottomSheetBackgroundColor(slideOffset);
                    animateBottomSheetArrows(slideOffset);
                }
            }
        });
    }

    private void transitionBottomSheetBackgroundColor(float slideOffset) {
        int colorFrom = getResources().getColor(R.color.colorAccent);
        int colorTo = getResources().getColor(R.color.colorAccentAlpha60);
        mBottomSheet.setBackgroundColor(interpolateColor(slideOffset,
                colorFrom, colorTo));
    }

    private void animateBottomSheetArrows(float slideOffset) {
        mLeftArrow.setRotation(slideOffset * -180);
        mRightArrow.setRotation(slideOffset * 180);
    }

    // Helper method to interpolate colors
    private int interpolateColor(float fraction, int startValue, int endValue) {
        int startA = (startValue >> 24) & 0xff;
        int startR = (startValue >> 16) & 0xff;
        int startG = (startValue >> 8) & 0xff;
        int startB = startValue & 0xff;
        int endA = (endValue >> 24) & 0xff;
        int endR = (endValue >> 16) & 0xff;
        int endG = (endValue >> 8) & 0xff;
        int endB = endValue & 0xff;
        return ((startA + (int) (fraction * (endA - startA))) << 24) |
                ((startR + (int) (fraction * (endR - startR))) << 16) |
                ((startG + (int) (fraction * (endG - startG))) << 8) |
                ((startB + (int) (fraction * (endB - startB))));
    }
}