package com.android.nanden.homeworkedmodo.client;

import com.android.nanden.homeworkedmodo.App;
import com.android.nanden.homeworkedmodo.Constants;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * AssignmentClient
 */

public class AssignmentClient {

    public void getAssignments(Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_UR_ASSIGNMENT).newBuilder();
        urlBuilder.addQueryParameter(Constants.ACCESS_TOKEN, Constants.ACCESS_TOKEN_ASSIGNMENT);
        String url = urlBuilder.build().toString();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        App.getClient().newCall(request).enqueue(callback);
    }

}
