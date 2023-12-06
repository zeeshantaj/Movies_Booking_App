package com.example.movie_booking_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;

import com.example.movie_booking_application.Adapter.MoviesAdapter;
import com.example.movie_booking_application.Model.Movies;
import com.google.android.material.carousel.CarouselStrategy;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselOnScrollListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private Movies movies;
    private List<Movies> moviesList;
    private MoviesAdapter adapter;
    private ImageCarousel carousel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.movie_recycler);
        carousel = findViewById(R.id.carousel);
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
        getSlider();

    }

    private void getSlider(){
        carousel.addData(new CarouselItem("https://www.yashrajfilms.com/images/default-source/movies/tiger-3/tiger-3_banner.jpg?sfvrsn=c63bdfcc_0"));
        carousel.addData(new CarouselItem("https://www.yashrajfilms.com/images/default-source/default-album/tgif_main-page.jpg?sfvrsn=de26dfcc_0"));
        carousel.addData(new CarouselItem("https://i0.wp.com/3.bp.blogspot.com/-AzNL9sB_8YU/U1pfKjnzUII/AAAAAAAAAMQ/cUF6BMCEwMU/s1600/edge_of_tomorrow_banner-poster+(2).jpg"));
        carousel.addData(new CarouselItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVD9q011bzmUGR0-j2x06cERhZQXe_dWkLwA&usqp=CAU"));
        carousel.addData(new CarouselItem("https://www.scifinow.co.uk/wp-content/uploads/2016/04/warcraft_ver19_xlg-616x303.jpg"));
        carousel.addData(new CarouselItem("https://collider.com/wp-content/uploads/dark-knight-rises-movie-poster-banner-batman.jpg"));
    }
}