package com.android.nanden.homeworkedmodo.client;

import com.android.nanden.homeworkedmodo.App;
import com.android.nanden.homeworkedmodo.Constants;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * StudentClient
 */

public class StudentClient {

    public void getStudents(String id, Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL_SUBMISSION).newBuilder();
        urlBuilder.addQueryParameter(Constants.ASSIGNMENT_ID, id);
        urlBuilder.addQueryParameter(Constants.ACCESS_TOKEN, Constants.ACCESS_TOKEN_SUBMISSION);
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        App.getClient().newCall(request).enqueue(callback);
    }

}
