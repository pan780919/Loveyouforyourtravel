import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by JackPan on 2018/5/20.
 */

public class MySharedPrefernces {
    public static final String  NAME = "MySharedPrefernces";

    //首頁-是否第一次使用
    public static final String KEY_IS_BUY = "isBuy";
    public static void saveIsBuyed(Context context, boolean isBuyed) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putBoolean(KEY_IS_BUY, isBuyed).apply();
    }

    public static boolean getIsBuyed(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getBoolean(KEY_IS_BUY, false);
    }


    public static final String KEY_IS_TOKEN= "isToken";
    public static void saveIsToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_IS_TOKEN, token).apply();
    }

    public static String getIsToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_IS_TOKEN, "");
    }




    public static final String KEY_IS_NAME= "isName";
    public static void saveIsName(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_IS_NAME, token).apply();
    }

    public static String getIsName(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_IS_NAME, "");
    }

    public static final String KEY_IS_DATA ="isData";
    public static void saveIsData(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_IS_DATA, token).apply();
    }

    public static String getIsData(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_IS_DATA, "");
    }



    public static final String KEY_IS_PHONE ="isPhone";
    public static void saveIsPhone(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putString(KEY_IS_PHONE, token).apply();
    }

    public static String getIsPhone(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getString(KEY_IS_PHONE, "");
    }

}
