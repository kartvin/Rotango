package com.example.karthikkumaravel.rotango.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by karthikkumaravel on 8/3/14.
 */
public class MovieTypesModel implements BaseModel {

    public String boxOfficeUrl;

    public String inTheaters;

    public String openingUrl;

    public String upcoming;

    // ----

    private static final String LINKS = "links";

    private static final String BOX_OFFICE = "box_office";

    private static final String IN_THEATERS = "in_theaters";

    private static final String OPENING = "opening";

    private static final String UPCOMING = "upcoming";

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
                if (linksObject.has(BOX_OFFICE)) {
                    boxOfficeUrl = linksObject.optString(BOX_OFFICE);
                }

                if (linksObject.has(IN_THEATERS)) {
                    inTheaters = linksObject.optString(IN_THEATERS);
                }

                if (linksObject.has(OPENING)) {
                    openingUrl = linksObject.optString(OPENING);
                }

                if (linksObject.has(UPCOMING)) {
                    upcoming = linksObject.optString(UPCOMING);
                }
            }
        }
    }
}
