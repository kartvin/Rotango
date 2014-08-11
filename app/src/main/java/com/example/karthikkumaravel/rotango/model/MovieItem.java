package com.example.karthikkumaravel.rotango.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by karthikkumaravel on 8/3/14.
 */
public class MovieItem implements Serializable {

    public String id;

    public String title;

    public int year;

    public String mpaa_rating;

    public int runtime;

    public String critics_consensus;

    public String[] release_dates;

    public MovieRating movieRating;

    public String synopsis;

    public Posters posters;

    public Cast abridged_cast;

    public Links links;

    public void parseAndUpdate(JSONObject movieObject) {
        id = movieObject.optString("id");
        title = movieObject.optString("title");
        year = movieObject.optInt("year");
        mpaa_rating = movieObject.optString("mpaa_rating");
        runtime = movieObject.optInt("runtime");
        critics_consensus = movieObject.optString("critics_consensus");

        // id = movieObject.optString("id"); //TODO release date

        //id = movieObject.optString("id"); //TODO ratings

        synopsis = movieObject.optString("synopsis");

        posters = new Posters();
        posters.parseAndUpdate(movieObject.optJSONObject("posters"));
    }
}
