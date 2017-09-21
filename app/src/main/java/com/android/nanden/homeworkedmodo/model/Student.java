package com.android.nanden.homeworkedmodo.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.android.nanden.homeworkedmodo.Constants;
import com.android.nanden.homeworkedmodo.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Student POJO
 */

public class Student implements Parcelable{
    private static final String LOG_TAG = Student.class.getSimpleName();
    private String firstName;
    private String lastName;
    private String avatar;
    private String content;
    private String submitDate;

    public Student(JSONObject jsonObject) {
        try {
            this.firstName = jsonObject.getJSONObject(Constants.CREATOR).getString(Constants
                    .FIRST_NAME);
            this.lastName = jsonObject.getJSONObject(Constants.CREATOR).getString(Constants
                    .LAST_NAME);
            this.avatar = jsonObject.getJSONObject(Constants.CREATOR).getJSONObject(Constants
                    .AVATARS).getString
                    (Constants.LARGE);
            this.content = jsonObject.getString(Constants.CONTENT);
            this.submitDate = jsonObject.getString(Constants.SUBMIT_AT);
        } catch (JSONException e) {
            Log.d(LOG_TAG, "JSONException: " + e.getMessage());
        }

    }

    public static List<Student> fromJsonArray(JSONArray jsonArray) {
        List<Student> result = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                result.add(new Student(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                Log.d(LOG_TAG, "JSONException: " + e.getMessage());
            }
        }
        return result;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getAvatar() {
        return avatar;
    }
    public String getSubmitDate() {
        return Utils.parseDate(submitDate);
    }

    public String getContent() {
        return content;
    }

    protected Student(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        avatar = in.readString();
        content = in.readString();
        submitDate = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(avatar);
        parcel.writeString(content);
        parcel.writeString(submitDate);
    }
}
