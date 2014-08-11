package com.example.karthikkumaravel.rotango.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.karthikkumaravel.rotango.DataCache;
import com.example.karthikkumaravel.rotango.MyApplication;
import com.example.karthikkumaravel.rotango.R;
import com.example.karthikkumaravel.rotango.adapter.MovieListAdapter;
import com.example.karthikkumaravel.rotango.events.OnDataLoaded;
import com.example.karthikkumaravel.rotango.model.DataModel;
import com.example.karthikkumaravel.rotango.model.MovieItemList;
import com.example.karthikkumaravel.rotango.model.MovieModel;
import com.example.karthikkumaravel.rotango.model.MovieTypesModel;

/**
 * Created by karthikkumaravel on 8/3/14.
 */
public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";

    private ListView mListView;

    private MovieListAdapter mMovieListAdapter;

    private ProgressBar mProgressBar;

    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(mMovieListAdapter = new MovieListAdapter());

        searchBar = (EditText) findViewById(R.id.editText);

        MyApplication.setOnDataLoaded(new OnDataLoaded() {
            @Override
            public void onDataLoad() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mProgressBar != null) {
                            mProgressBar.setVisibility(View.GONE);

                            mMovieListAdapter.notifyDataSetChanged();
                        }

                        Log.d(TAG, "size: " + DataCache.getInstance().getMovieItemList().movieItems.size());
                    }
                });
            }
        });


        /**
         * Enabling Search Filter
         * */
        searchBar.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                mMovieListAdapter.filterArray(cs.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        initializeApp();
    }

    private void initializeApp() {
        MyApplication.getRottenTomatoesAPI().fetchDataList (
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DOH", "response: " + response);

                        DataModel dataModel = new DataModel();
                        dataModel.parseAndUpdate(response);

                        fetchLists(dataModel.listsUrl);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }

    private void fetchLists(String url) {
        MyApplication.getRottenTomatoesAPI().fetchData(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "fetchLists: " + response);

                        MovieModel movieModel = new MovieModel();
                        movieModel.parseAndUpdate(response);

                        fetchMovies(movieModel.moviesUrl);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }

    private void fetchMovies(String url) {
        MyApplication.getRottenTomatoesAPI().fetchData(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "fetchMovies: " + response);

                        MovieTypesModel movieTypesModel = new MovieTypesModel();
                        movieTypesModel.parseAndUpdate(response);

                        fetchInTheatres(movieTypesModel.inTheaters);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }

    private void fetchInTheatres(String url) {
        MyApplication.getRottenTomatoesAPI().fetchData(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "fetchInTheatres: " + response);

                        MovieItemList movieItemList = new MovieItemList();
                        movieItemList.parseAndUpdate(response);

                        DataCache.getInstance().getMovieItemList().movieItems.addAll(movieItemList.movieItems);
                        MyApplication.sendDataLoadedEvent();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
    }



}
