package com.android.nanden.homeworkedmodo;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.text.ParseException;
import java.util.Date;

/**
 * Utils-
 */

public class Utils {
    private static final String LOG_TAG = Utils.class.getSimpleName();

    /**
     * For checking the internet connection
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context
                .CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork!=null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * For parsing returned date string
     * @param time
     * @return
     */
    public static String parseDate(String time) {
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        String outputPattern = "MMM dd, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            Log.d(LOG_TAG, e.getMessage());
        }
        return str;
    }
}
