package com.example.tusharsharma.movies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tusharsharma.movies.utilities.MoviesDetails;
import com.example.tusharsharma.movies.utilities.NetworkUtils;
import com.example.tusharsharma.movies.utilities.MoviesJsonUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler{



    //TODO   Enter your API Key here , assign it to the String , apiKey

    private final String apiKey = "API KEY TO BE ENTERED" ;

    private final String uRLPopular = "https://api.themoviedb.org/3/movie/popular?api_key="+apiKey+"&language=en-US";

    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_movies);

        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        setTitle(R.string.most_popular_string);
        recyclerViewOps();

        /*
         * The ProgressBar that will indicate to the user that we are loading data. It will be
         * hidden when no data is loading.
         */
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadMovieData(uRLPopular);

    }


    private void recyclerViewOps(){

        GridLayoutManager layoutManager
                = new GridLayoutManager(this,2);

        mRecyclerView.setLayoutManager(layoutManager);


        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        mRecyclerView.setHasFixedSize(true);

        mMoviesAdapter = new MoviesAdapter(getApplicationContext(),this);

        mRecyclerView.setAdapter(mMoviesAdapter);

    }
    private void loadMovieData(String url) {

        showMoviesDataView();

        new FetchMovieTask().execute(url);
    }

    @Override
    public void onClick(int position) {

        Context context = this;
        Class destinationClass = MoviesDetailsActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, Integer.toString(position));
        startActivity(intentToStartDetailActivity);

    }

    private void showMoviesDataView() {
        /* First, make sure the error is invisible */
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        /* Then, make sure the weather data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private class FetchMovieTask extends AsyncTask<String, Void, MoviesDetails> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected MoviesDetails doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }

            String url = params[0];

            URL properURL = null;
            try {
                properURL = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(properURL);

                return MoviesJsonUtils
                        .getSimpleMoviesStringsFromJson(jsonMovieResponse);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(MoviesDetails moviesDetailsLocal) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);

           String[] posterUrls = MoviesDetails.getImageUrl();
            showMoviesDataView();
            mMoviesAdapter.setMoviesData(posterUrls);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.most_popular) {
            mMoviesAdapter.setMoviesData(null);

            setTitle(getString(R.string.most_popular_string));
            recyclerViewOps();

            loadMovieData(uRLPopular);
            return true;
        }

        if (id == R.id.top_rated) {
            mMoviesAdapter.setMoviesData(null);
            setTitle(getString(R.string.top_rated));
            recyclerViewOps();

            String uRLTopRated = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + apiKey + "&language=en-US";

            loadMovieData(uRLTopRated);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}

