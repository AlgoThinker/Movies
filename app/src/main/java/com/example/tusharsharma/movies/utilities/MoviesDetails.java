package com.example.tusharsharma.movies.utilities;


public class MoviesDetails {

    private static MoviesDetails instance = null;
    private MoviesDetails() {
        // Exists only to defeat instantiation.
    }

    static MoviesDetails getInstance() {
        if(instance == null) {
            instance = new MoviesDetails();
        }
        return instance;
    }


    private static String[] mImageUrl ;

    private static String[] parsedRatingData;

    private static String[] parsedReleasedDateData;

    private static String[] moviePlotData;

    private static String[] movieTitle;


    public static String[] getParsedRatingData() {
        return parsedRatingData;
    }

    public static String[] getParsedReleasedDateData() {
        return parsedReleasedDateData;
    }

    public static String[] getMoviePlotData() {
        return moviePlotData;
    }

    public static String[] getMovieTitle() {
        return movieTitle;
    }

    public static String[] getImageUrl(){
        return mImageUrl;
    }


    static void setImageUrl(String[] mImageUrl) {
        MoviesDetails.mImageUrl = mImageUrl;
    }

    static void setParsedRatingData(String[] parsedRatingData) {
        MoviesDetails.parsedRatingData = parsedRatingData;
    }

    static void setParsedReleasedDateData(String[] parsedReleasedDateData) {
        MoviesDetails.parsedReleasedDateData = parsedReleasedDateData;
    }

    static void setMoviePlotData(String[] moviePlotData) {
        MoviesDetails.moviePlotData = moviePlotData;
    }

    static void setMovieTitle(String[] movieTitle) {
        MoviesDetails.movieTitle = movieTitle;
    }



}
