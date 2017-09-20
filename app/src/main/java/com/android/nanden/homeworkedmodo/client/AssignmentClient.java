package com.android.nanden.homeworkedmodo.client;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by nanden on 9/19/17.
 */

public class AssignmentClient {

    private static final String BASE_URL = "https://api.edmodo.com/assignments";
    //access_token
    private static final String ACCESS_TOKEN =
            "12e7eaf1625004b7341b6d681fa3a7c1c551b5300cf7f7f3a02010e99c84695d";

    private OkHttpClient client;

    public AssignmentClient() {
        this.client = new OkHttpClient();
    }

    public void getAssignments(Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("access_token", ACCESS_TOKEN);
        String url = urlBuilder.build().toString();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
