package com.example.jabs.loginwithpostparameter.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class SecurePreferences {

    public static void savePreferences(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences("appData", Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void savePreferences(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences("appData", Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();

    }

    public static void savePreferences(Activity context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences("appData", Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();

    }

    public static String getStringPreference(Context context, String key) {
        String value = "";
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences("appData", Context.MODE_PRIVATE);
            value = preferences.getString(key, "");
        }
        return value;
    }

    public static int getIntegerPreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences("appData", Context.MODE_PRIVATE);
        int value = preferences.getInt(key, 0);
        return value;
    }

    public static boolean getBooleanPreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences("appData", Context.MODE_PRIVATE);
        boolean value = preferences.getBoolean(key, false);
        return value;
    }

    public static boolean isOnline(final Context ctx) {
        final ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null) {
            return ni.isConnectedOrConnecting();
        } else {
            return false;
        }
    }

    public static void clearUserData(Context activity) {
        SharedPreferences preferences = activity.getSharedPreferences("appData", Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public static String getToken(Context mContext) {
        SharedPreferences preferences = mContext.getSharedPreferences("appData", Context.MODE_PRIVATE);
        return preferences.getString("access_token", "");
    }

    public static String getUserType(Context mContext) {
        SharedPreferences preferences = mContext.getSharedPreferences("appData", Context.MODE_PRIVATE);
        return preferences.getString("user_type", "");
    }

    public static void toastmsg(Context mContext, String responce) {
        Toast.makeText(mContext, responce, Toast.LENGTH_SHORT).show();
    }

    public static String getUserID(Context mcontext) {
        SharedPreferences preferences = mcontext.getSharedPreferences("appData", Context.MODE_PRIVATE);
        return preferences.getString("user_id", "");
    }


    public static String encodeString(String encode) {
        try {
            return URLEncoder.encode(encode, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decodeString(String decode) {
        try {
            return URLDecoder.decode(decode, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
