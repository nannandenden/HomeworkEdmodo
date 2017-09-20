package com.android.nanden.homeworkedmodo.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Assignment {

    private static final String LOG_TAG = Assignment.class.getSimpleName();

    private String title;
    private String dueDate;

    public Assignment(JSONObject jsonObject) {
        try {
            this.title = jsonObject.getString("title");
            this.dueDate = jsonObject.getString("due_at");
        } catch (JSONException e) {
            Log.d(LOG_TAG, e.getMessage());
        }
    }

    public static List<Assignment> fromJsonArray(JSONArray jsonArray) {
        List<Assignment> assignments = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                assignments.add(new Assignment(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                Log.d(LOG_TAG, e.getMessage());
            }
        }
        return assignments;
    }

    public String getTitle() {
        return title;
    }

    public String getDueDate() {
        return dueDate;
    }
}
