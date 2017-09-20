package com.android.nanden.homeworkedmodo.client;

import com.android.nanden.homeworkedmodo.App;
import com.android.nanden.homeworkedmodo.Constants;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by nanden on 9/19/17.
 */

public class AssignmentClient {

    public AssignmentClient() {
    }

    public void getAssignments(Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_UR_ASSIGNMENT).newBuilder();
        urlBuilder.addQueryParameter("access_token", Constants.ACCESS_TOKEN_ASSIGNMENT);
        String url = urlBuilder.build().toString();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        App.getClient().newCall(request).enqueue(callback);
    }

}
