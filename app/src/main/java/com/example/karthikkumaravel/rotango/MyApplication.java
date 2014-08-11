package com.example.karthikkumaravel.rotango;

import android.app.Application;
import android.content.Context;

import com.example.karthikkumaravel.rotango.events.OnDataLoaded;
import com.example.karthikkumaravel.rotango.rottentomatoes.RottenTomatoesAPI;
import com.example.karthikkumaravel.rotango.volley.VolleyManager;

/**
 * Created by karthikkumaravel on 8/3/14.
 */
public class MyApplication extends Application {

    private static Context sContext;

    private static VolleyManager sVolleyManager;

    private static RottenTomatoesAPI sRottenTomatoesAPI;

    private static OnDataLoaded sOnDataLoaded;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();

        sVolleyManager = new VolleyManager();

        sRottenTomatoesAPI = new RottenTomatoesAPI();
    }

    public static Context getContext() {
        return sContext;
    }

    public static VolleyManager getVolleyManager() {

        return sVolleyManager;
    }

    public static RottenTomatoesAPI getRottenTomatoesAPI() {

        return sRottenTomatoesAPI;
    }

    public static void setOnDataLoaded(OnDataLoaded onDataLoaded) {

        sOnDataLoaded = onDataLoaded;
    }

    public static void sendDataLoadedEvent() {
        if (sOnDataLoaded != null) {
            sOnDataLoaded.onDataLoad();
        }
    }


}
