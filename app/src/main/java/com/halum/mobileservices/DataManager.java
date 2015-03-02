package com.halum.mobileservices;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import static com.halum.mobileservices.Constants.*;

/**
 * Created by Halum on 1/23/2015.
 */
public class DataManager{
    private static SharedPreferences database;
    private static SharedPreferences.Editor databaseEditor;

    private static void setOperatorStatus(Context context, String operator, Boolean value){
        database = PreferenceManager.getDefaultSharedPreferences(context);
        databaseEditor = database.edit();
        try {
            databaseEditor.putBoolean(operator, value);
            databaseEditor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean operaotrStatus(Context context, String operator){
        database = PreferenceManager.getDefaultSharedPreferences(context);
        Boolean value = false;
        try {
            value = database.getBoolean(operator, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static boolean isAppRunningFirstTime(Context context){
        database = PreferenceManager.getDefaultSharedPreferences(context);
        Boolean value = false;
        try {
            value = database.getBoolean("FirstTime", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !value;
    }

    public static void firstTimeRunOver(Context context){
        database = PreferenceManager.getDefaultSharedPreferences(context);
        databaseEditor = database.edit();
        try {
            databaseEditor.putBoolean("FirstTime", true);
            databaseEditor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateActvieOperatorsList(Context context){
        ACTIVE_OPERATORS.clear();
        for (int i = 0; i < OPERATORS.size(); ++i) {
            if(isAppRunningFirstTime(context)){
                ACTIVE_OPERATORS.add(OPERATORS.get(i));
                setOperatorStatus(context, OPERATORS.get(i), true);
            }
            else if (operaotrStatus(context, OPERATORS.get(i))) {
                ACTIVE_OPERATORS.add(OPERATORS.get(i));
            }
        }
    }
}
