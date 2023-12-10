package com.example.movie_booking_application.Model;

public class BookingModel {
    private String movieTitle,imageUrl,timing,person,currentTime;

    public BookingModel() {

    }

    public BookingModel(String movieTitle, String imageUrl, String timing, String person, String currentTime) {
        this.movieTitle = movieTitle;
        this.imageUrl = imageUrl;
        this.timing = timing;
        this.person = person;
        this.currentTime = currentTime;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
