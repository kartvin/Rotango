package com.example.karthikkumaravel.rotango.model;

import org.json.JSONObject;

/**
 * Created by karthikkumaravel on 8/3/14.
 */
public class Posters {

    public String thumbnail;

    public String profile;

    public String detailed;

    public String original;

    public void parseAndUpdate(JSONObject jsonObject) {
        if (jsonObject != null) {
            thumbnail = jsonObject.optString("thumbnail");

            profile = jsonObject.optString("profile");

            detailed = jsonObject.optString("detailed");

            original = jsonObject.optString("original");
        }
    }
}
