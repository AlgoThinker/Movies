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
package com.example.tusharsharma.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private String[] mMoviesData;

    private final Context context;

    private ImageView mMoviesImageView;


    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    private final MoviesAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    interface MoviesAdapterOnClickHandler {
        void onClick(int position);
    }

    /**
     * Creates a MoviesAdapter.
     *
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    MoviesAdapter(Context context, MoviesAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
        this.context = context ;
    }

    /**
     * Cache of the children views for a movies list item.
     */
    class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        MoviesAdapterViewHolder(View view) {
            super(view);
            mMoviesImageView = (ImageView) view.findViewById(R.id.image_poster_single);
            view.setOnClickListener(this);
        }

        /**
         * This gets called by the child views during a click.
         *
         * @param v The View that was clicked
         */

        @Override
        public void onClick(View v) {

            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(adapterPosition);

        }
    }


    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.mvies_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapterViewHolder moviesAdapterViewHolder, int position) {

        String moviePosterPath = mMoviesData[position];
        String posterImageUrl = context.getString(R.string.poster_image_url_main_view);
        Picasso.with(context).load(posterImageUrl + moviePosterPath).into(mMoviesImageView);

    }

    @Override
    public int getItemCount() {
        if (null == mMoviesData) return 0;
        return mMoviesData.length;
    }


    void setMoviesData(String[] weatherData) {

        mMoviesData = weatherData;
        notifyDataSetChanged();

    }
}