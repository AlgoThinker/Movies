/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.tusharsharma.movies.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.tusharsharma.movies.utilities.MoviesDetails.*;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class MoviesJsonUtils {


    public static MoviesDetails getSimpleMoviesStringsFromJson(String movieJsonStr) throws JSONException{


        String[] parsedMovieData ;

        String[] parsedRatingData ;

        String[] parsedReleasedDateData ;

        String[] moviePlotData ;

        String[] movieTitle ;

        JSONObject moviesJson = new JSONObject(movieJsonStr);

        JSONArray movieArray = moviesJson.getJSONArray("results");

        parsedMovieData = new String[movieArray.length()];
        parsedRatingData = new String[movieArray.length()];
        parsedReleasedDateData = new String[movieArray.length()];
        moviePlotData = new String[movieArray.length()];
        movieTitle = new String[movieArray.length()];


        for (int i = 0; i < movieArray.length(); i++) {

            String posterPath;
            String rating ;
            String releaseDate ;
            String moviePlot ;
            String title ;


            JSONObject moviesObject = movieArray.getJSONObject(i);

            posterPath = moviesObject.getString("poster_path");
            rating = moviesObject.getString("vote_average");
            releaseDate = moviesObject.getString("release_date");
            moviePlot = moviesObject.getString("overview");
            title = moviesObject.getString("title");


            parsedMovieData[i] = posterPath;
            parsedRatingData[i]=rating;
            parsedReleasedDateData[i]=releaseDate;
            moviePlotData[i]=moviePlot;
            movieTitle[i]=title;

        }

        MoviesDetails moviesDetails = getInstance();

        setImageUrl(parsedMovieData);
        setMoviePlotData(moviePlotData);
        setMovieTitle(movieTitle);
        setParsedRatingData(parsedRatingData);
        setParsedReleasedDateData(parsedReleasedDateData);

        return moviesDetails;

    }

}