package com.example.karthikkumaravel.rotango.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.karthikkumaravel.rotango.DataCache;
import com.example.karthikkumaravel.rotango.R;
import com.example.karthikkumaravel.rotango.adapter.MovieDetailsAdapter;

/**
 * Created by karthikkumaravel on 8/9/14.
 */
public class MovieDetailsActivity extends Activity {

    public static final String TAG = "MainActivity";

    private ListView mListView;

    private MovieDetailsAdapter mMovieDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie_details);
        mListView =  (ListView) findViewById(R.id.details_list_view);
        Intent myIntent = getIntent();
        int  position =  myIntent.getIntExtra("movieItemObj",0);

        mMovieDetailsAdapter = new  MovieDetailsAdapter();
        mMovieDetailsAdapter.setMovieItem(DataCache.getInstance().getMovieItemList().movieItems.get(position));
        mListView.setAdapter(mMovieDetailsAdapter);

    }

}
