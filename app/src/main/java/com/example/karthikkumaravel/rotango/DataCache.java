package com.example.karthikkumaravel.rotango;

import com.example.karthikkumaravel.rotango.model.MovieItemList;

/**
 * Created by karthikkumaravel on 8/3/14.
 */
public class DataCache {

    private static final DataCache sInstance = new DataCache();

    public static DataCache getInstance() {

        return sInstance;
    }

    private final MovieItemList mMovieItemList = new MovieItemList();

    public MovieItemList getMovieItemList() {

        return mMovieItemList;
    }

}
