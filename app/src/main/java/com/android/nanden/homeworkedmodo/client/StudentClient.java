package com.android.nanden.homeworkedmodo.client;

import com.android.nanden.homeworkedmodo.App;
import com.android.nanden.homeworkedmodo.Constants;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by nanden on 9/19/17.
 */

public class StudentClient {

    public StudentClient() {
    }

    public void getStudents(String id, Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL_SUBMISSION).newBuilder();
        urlBuilder.addQueryParameter("assignment_id", id);
        urlBuilder.addQueryParameter("access_token", Constants.ACCESS_TOKEN_SUBMISSION);
        String url = urlBuilder.build().toString();
        System.out.println("url: " + url);
        Request request = new Request.Builder().url(url).build();
        App.getClient().newCall(request).enqueue(callback);
    }



}
