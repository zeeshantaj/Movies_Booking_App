package com.example.movie_booking_application.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.movie_booking_application.Animator.ShakeAnimation;
import com.example.movie_booking_application.MainActivity;
import com.example.movie_booking_application.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class Setup_Profile_Fragment extends Fragment {
    private FirebaseAuth auth;
    private DatabaseReference reference;
    private UploadTask uploadTask;
    private StorageReference imageRef,storageRef;
    private FirebaseStorage storage;
    private CircleImageView profileImage;
    private TextInputEditText userName;
    private Button setup;
    private Uri selectedImageUri;
    private String uid;
    private ProgressBar progressBar;
    //private LoginCallBack loginCallBack;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_setup, container, false);
        setup = view.findViewById(R.id.next3);
        profileImage = view.findViewById(R.id.setupProfileImage);
        userName = view.findViewById(R.id.userNameEditText);
        progressBar = view.findViewById(R.id.setupProgress);


        auth = FirebaseAuth.getInstance();
        uid = auth.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        String imageName = "image_" + uid + ".jpg";
        imageRef = storageRef.child("UserImages/"+ imageName);
        uploadPlaceHolder();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileImage.setOnClickListener((v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imageLauncher.launch(intent);
        }));
        setup.setOnClickListener((v -> {
            String name = userName.getText().toString();
            HashMap<String, String> value = new HashMap<>();
            if (!name.isEmpty()){
                setup.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        imageRef.getDownloadUrl().addOnSuccessListener(uir -> {

                            String image = uir.toString();
                            value.put("name", name);
                            value.put("image", image);
                            value.put("associatedID", uid);

                            reference.setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getActivity(), "User created successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(),MainActivity.class);

                                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.putString("shareName",name);
                                    editor.putString("shareImageUrl",image);
                                    editor.apply();

                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    ShakeAnimation.setAnimation(getActivity(),profileImage);
                                    ShakeAnimation.setAnimation(getActivity(),userName);
                                    progressBar.setVisibility(View.GONE);
                                    setup.setEnabled(true);
                                    Toast.makeText(getActivity(), "Error "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    Log.e("MyApp", "Error " + e.getLocalizedMessage());
                                }
                            });

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.GONE);
                                ShakeAnimation.setAnimation(getActivity(),profileImage);
                                ShakeAnimation.setAnimation(getActivity(),userName);
                                setup.setEnabled(true);
                                Toast.makeText(getActivity(), "Error "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("MyApp", "Error " + e.getLocalizedMessage());
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        ShakeAnimation.setAnimation(getActivity(),profileImage);
                        ShakeAnimation.setAnimation(getActivity(),userName);
                        setup.setEnabled(true);
                        Log.e("MyApp", "Error " + e.getLocalizedMessage());
                        Toast.makeText(getActivity(), "Error "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
            else {
                setup.setEnabled(true);
                ShakeAnimation.setAnimation(getActivity(),profileImage);
                ShakeAnimation.setAnimation(getActivity(),userName);

                Toast.makeText(getActivity(), "Name field is require", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void uploadPlaceHolder(){
        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.place_holder);
        Bitmap bitmap = drawable.getBitmap();

        // Create a file from the Bitmap (for temporary storage)
        File file = new File(getActivity().getCacheDir(), "profile_img.png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();

            // Get the URI of the created file
            Uri placeholderUri = Uri.fromFile(file);

            // Set the image to profileImage
            profileImage.setImageURI(placeholderUri);

            // Upload the placeholder image to Firebase Storage
            uploadTask = imageRef.putFile(placeholderUri);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ActivityResultLauncher<Intent> imageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    profileImage.setImageURI(selectedImageUri);
                    uploadTask = imageRef.putFile(selectedImageUri);

                }

            });
}
