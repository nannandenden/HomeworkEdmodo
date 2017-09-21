package com.android.nanden.homeworkedmodo;

import android.app.Application;

import okhttp3.OkHttpClient;

/**
 * App
 */

public class App extends Application {

    private static OkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        client = new OkHttpClient();

    }
    // Make sure not to have more than one instance of OkHttpClient since it's a singleton class
    public static OkHttpClient getClient() {
        return client;
    }

}
