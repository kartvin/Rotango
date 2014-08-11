package com.example.karthikkumaravel.rotango.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by karthikkumaravel on 8/3/14.
 */
public class MovieItemList implements BaseModel{

    public final ArrayList<MovieItem> movieItems = new ArrayList<MovieItem>();


    @Override
    public void parseAndUpdate(String response) {
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();

            return;
        }

        JSONArray moviesArray = jsonObject.optJSONArray("movies");

        if (moviesArray != null) {

            for (int i=0; i<moviesArray.length(); i++) {
                MovieItem movieItem = new MovieItem();

                try {
                    movieItem.parseAndUpdate(moviesArray.getJSONObject(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                    continue;
                }

                movieItems.add(movieItem);
            }
        }
    }
}
