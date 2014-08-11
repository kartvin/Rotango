package com.example.karthikkumaravel.rotango.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.karthikkumaravel.rotango.MyApplication;

/**
 * Created by karthikkumaravel on 8/3/14.
 */
public class VolleyManager {

    public static final String TAG = "VolleyManager";

    private RequestQueue mRequestQueue = Volley.newRequestQueue(MyApplication.getContext());

    public void addToRequestQueue(Request request) {
        if (mRequestQueue != null) {
            mRequestQueue.add(request);
        }
    }
}
