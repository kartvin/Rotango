package com.example.karthikkumaravel.rotango.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by karthikkumaravel on 8/3/14.
 */
public class DataModel implements BaseModel {

    public String listsUrl;

    public String moviesUrl;

    // --- parser logic

    private static final String LINKS = "links";

    private static final String LISTS = "lists";

    private static final String MOVIES = "movies";

    @Override
    public void parseAndUpdate(String response) {
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();

            return;
        }

        if (jsonObject.has(LINKS)) {
            JSONObject linksObject;

            linksObject = jsonObject.optJSONObject(LINKS);

            if (linksObject != null) {
                if (linksObject.has(LISTS)) {
                    listsUrl = linksObject.optString(LISTS);
                }

                if (linksObject.has(MOVIES)) {
                    moviesUrl = linksObject.optString(MOVIES);
                }
            }
        }
    }
}
