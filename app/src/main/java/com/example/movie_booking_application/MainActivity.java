package com.example.movie_booking_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.movie_booking_application.Adapter.MoviesAdapter;
import com.example.movie_booking_application.Model.Movies;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private Movies movies;
    private List<Movies> moviesList;
    private MoviesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.movie_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        Movies movies1 = new Movies("the dark knoght rises", "fhaspoifhoidashfoihafsdoihdgoifayhsoifhasdpoihgfiposahdifg", "fdlkhfdajlkdhafsl");
        Movies movies2 = new Movies("the dark knoght rises", "fhaspoifhoidashfoihafsdoihdgoifayhsoifhasdpoihgfiposahdifg", "fdlkhfdajlkdhafsl");
        Movies movies3 = new Movies("the dark knoght rises", "fhaspoifhoidashfoihafsdoihdgoifayhsoifhasdpoihgfiposahdifg", "fdlkhfdajlkdhafsl");
        Movies movies4 = new Movies("the dark knoght rises", "fhaspoifhoidashfoihafsdoihdgoifayhsoifhasdpoihgfiposahdifg", "fdlkhfdajlkdhafsl");
        Movies movies5 = new Movies("the dark knoght rises", "fhaspoifhoidashfoihafsdoihdgoifayhsoifhasdpoihgfiposahdifg", "fdlkhfdajlkdhafsl");
        Movies movies6 = new Movies("the dark knoght rises", "fhaspoifhoidashfoihafsdoihdgoifayhsoifhasdpoihgfiposahdifg", "fdlkhfdajlkdhafsl");

        moviesList = new ArrayList<>();
        moviesList.add(movies1);
        moviesList.add(movies2);
        moviesList.add(movies3);
        moviesList.add(movies4);
        moviesList.add(movies5);
        moviesList.add(movies6);
        adapter = new MoviesAdapter(moviesList);
        recyclerView.setAdapter(adapter);
    }

}