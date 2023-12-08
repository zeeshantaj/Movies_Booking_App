package com.example.movie_booking_application.Login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.movie_booking_application.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import in.aabhasjindal.otptextview.OtpTextView;

public class OTP_Fragment extends Fragment {
    private OtpTextView otpTextView;
    private Button nextBtn;

    private FirebaseAuth auth;
    private TextView timer,resendTxt;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otp, container, false);

         nextBtn = view.findViewById(R.id.next2);
        auth = FirebaseAuth.getInstance();
        otpTextView = view.findViewById(R.id.otp_view);
        timer = view.findViewById(R.id.timer);
        progressBar = view.findViewById(R.id.otpProgress);
        resendTxt = view.findViewById(R.id.resendTxt);

        resendTxt.setVisibility(View.GONE);
        long time = 90000;
        CountDownTimer countDownTimer = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);

            }
            @Override
            public void onFinish() {
                resendTxt.setVisibility(View.VISIBLE);
            }
        }.start();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getVerificationId().observe(getViewLifecycleOwner(),verificationId->{
            if (verificationId != null) {
                // Use the verificationId in this fragment
                Log.e("MyApp", "verificationIdInOTPFragment before  clicked->  " + verificationId);
                // You can now proceed with the verification using this verificationId

                nextBtn.setOnClickListener((v -> {
                    String otp=otpTextView.getOTP();

                    if (otp.length()==6){
                        if (!verificationId.isEmpty()){
                            progressBar.setVisibility(View.VISIBLE);
                            Toast.makeText(getActivity(), "otp"+otp, Toast.LENGTH_SHORT).show();
                            Log.e("MyApp", "verificationIdInOTPFragment ->  " + verificationId);
                            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
                            auth.signInWithCredential(credential)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            LoginActivity.setFragment(new Setup_Profile_Fragment(),getActivity());
//                                            FragmentManager manager= getActivity().getSupportFragmentManager();
//                                            FragmentUtils.setFragment(manager,R.id.login_frameLayout,new Profile_Setup_Fragment());
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(getActivity(), "Error "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                            Log.e("MyApp", "error" + e.getLocalizedMessage());
                                        }
                                    });
                        }
                        else {
                            Toast.makeText(getActivity(), "Verification Id is empty", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }
                    else {
                        Toast.makeText(getActivity(), "Fill OTP", Toast.LENGTH_SHORT).show();
                    }
                }));

            }
            else {
                Toast.makeText(getActivity(), "Verification null", Toast.LENGTH_SHORT).show();
                Log.e("MyApp", "verificationNull");
            }
        });
    }
}
