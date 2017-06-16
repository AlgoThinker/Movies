package com.example.tusharsharma.movies;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tusharsharma.movies.utilities.MoviesDetails;
import com.squareup.picasso.Picasso;

public class MoviesDetailsActivity extends AppCompatActivity {


    private String mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);


        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                mPosition = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);

                Log.v(getString(R.string.position_value_string), mPosition);

            }
        }

        int positionInt = Integer.parseInt(mPosition);

        ImageView imageViewThumbnailImage = (ImageView) findViewById(R.id.iv_thumbnail_movie);

        TextView textViewOriginalTitle = (TextView) findViewById(R.id.tv_original_title);

        TextView textViewReleaseDate = (TextView) findViewById(R.id.release_date);

        TextView textViewVoteAverage = (TextView) findViewById(R.id.tv_vote_average);

        TextView textViewMoviePlot = (TextView) findViewById(R.id.movie_plot);


        setTitle(getString(R.string.movie_details));


        String[] movieTitles = MoviesDetails.getMovieTitle();

        String movieTitle = movieTitles[positionInt];

        textViewOriginalTitle.setText(movieTitle);

        textViewOriginalTitle.setBackgroundColor(Color.parseColor(getString(R.string.movie_title_background_color)));


        String[] thumbnailImages = MoviesDetails.getImageUrl();

        String imageUrl = thumbnailImages[positionInt];

        String posterImageUrl = getString(R.string.thumbnail_poster_image_url);

        Picasso.with(this).load(posterImageUrl + imageUrl).into(imageViewThumbnailImage);


        String[] releaseDates = MoviesDetails.getParsedReleasedDateData();

        String releaseDate = releaseDates[positionInt];

        textViewReleaseDate.setText(releaseDate);


        String[] voteAverages = MoviesDetails.getParsedRatingData();

        String voteAverage = voteAverages[positionInt];

        textViewVoteAverage.setText(String.format("%s/10", voteAverage));


        String[] moviePlots = MoviesDetails.getMoviePlotData();

        String moviePlot = moviePlots[positionInt];

        textViewMoviePlot.setText(moviePlot);


    }

}
