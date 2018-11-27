package mv.com.qrscanner;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preference {
    static final String Result ="Hasil";
    static final String Status = "Status_logged_in";

    public static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    public static void setHasilScan(Context context, String hasil){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(Result, hasil);
        editor.apply();
    }
    public static String getHasilScan(Context context){
        return getSharedPreference(context).getString(Result,"Missing");
    }
    public static void setLoggedInStatus(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(Status,status);
        editor.apply();
    }
    public static boolean getLoggedInStatus(Context context){
        return getSharedPreference(context).getBoolean(Status,false);
    }
    public static void clearLoggedInUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(Result);
        editor.remove(Status);
        editor.apply();
    }
}
