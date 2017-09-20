package com.android.nanden.homeworkedmodo.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.android.nanden.homeworkedmodo.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment POJP
 */

public class Assignment implements Parcelable {

    private static final String LOG_TAG = Assignment.class.getSimpleName();

    private String title;
    private String dueDate;
    private String description;
    private int id;

    public Assignment(JSONObject jsonObject) {
        try {
            this.title = jsonObject.getString(Constants.TITLE);
            this.dueDate = jsonObject.getString(Constants.DUE_AT);
            this.description = jsonObject.getString(Constants.DESCRIPTION);
            this.id = jsonObject.getInt(Constants.ID);
        } catch (JSONException e) {
            Log.d(LOG_TAG, "JSONException: " + e.getMessage());
        }
    }

    public static List<Assignment> fromJsonArray(JSONArray jsonArray) {
        List<Assignment> assignments = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                assignments.add(new Assignment(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                Log.d(LOG_TAG, "JSONException: " + e.getMessage());
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

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    protected Assignment(Parcel in) {
        title = in.readString();
        dueDate = in.readString();
        description = in.readString();
        id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(dueDate);
        parcel.writeString(description);
        parcel.writeInt(id);
    }

    public static final Creator<Assignment> CREATOR = new Creator<Assignment>() {
        @Override
        public Assignment createFromParcel(Parcel in) {
            return new Assignment(in);
        }

        @Override
        public Assignment[] newArray(int size) {
            return new Assignment[size];
        }
    };
}
