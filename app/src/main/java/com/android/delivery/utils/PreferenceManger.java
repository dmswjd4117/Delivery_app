package com.android.delivery.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManger {
    public static final String PREFERENCES_NAME = "preference";

    private  static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void setString(Context context, String key, String value){
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences preferences = getPreferences(context);
        String value = preferences.getString(key, "");
        return value;
    }

}
