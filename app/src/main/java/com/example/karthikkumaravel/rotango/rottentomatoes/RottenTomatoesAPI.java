package com.example.karthikkumaravel.rotango.rottentomatoes;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.karthikkumaravel.rotango.MyApplication;
import com.example.karthikkumaravel.rotango.R;

/**
 * Created by karthikkumaravel on 8/3/14.
 */
public class RottenTomatoesAPI {

    public static final String TAG = "RottenTomatoesAPI";

    private static final String API_TOKEN = "23au9g7xqgh2n5vaqc7gu5hc";

    public void fetchDataList(Response.Listener<String> onResponse, Response.ErrorListener onError) {
        String urlToFetch = MyApplication.getContext().getResources().getString(R.string.data_list,
                API_TOKEN);

        StringRequest request = new StringRequest(Request.Method.GET, urlToFetch,
                onResponse,
                onError
        );


        MyApplication.getVolleyManager().addToRequestQueue(request);
    }

    public void fetchData(String url, Response.Listener<String> onResponse, Response.ErrorListener onError) {

        if (url != null) {
            url += "?apikey=" + API_TOKEN;

            Log.d(TAG, "url: " + url);

            StringRequest request = new StringRequest(Request.Method.GET, url,
                    onResponse,
                    onError
            );


            MyApplication.getVolleyManager().addToRequestQueue(request);
        }
    }
}
