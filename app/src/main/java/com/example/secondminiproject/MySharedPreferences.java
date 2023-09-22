package com.example.secondminiproject;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {

    private static final String SHARED_PREF_NAME = "MyAppSharedPreferences";
    private static final String KEY_HIDE_AD = "hideAd";
    private static final String KEY_LAST_AD_SHOWN_TIME = "lastAdShownTime";

    // SharedPreferences에 "오늘하루 안보기" 정보를 저장하는 메서드
    public static void setHideAd(Context context, boolean hideAd) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_HIDE_AD, hideAd);
        // "오늘하루 안보기" 버튼을 클릭한 시간을 저장합니다.
        if (hideAd) {
            long currentTimeMillis = System.currentTimeMillis();
            editor.putLong(KEY_LAST_AD_SHOWN_TIME, currentTimeMillis);
        }
        editor.apply();
    }

    // SharedPreferences에서 "오늘하루 안보기" 정보를 가져오는 메서드
    public static boolean getHideAd(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_HIDE_AD, false);
    }

    // 광고를 표시해야 하는지 여부를 확인하는 메서드
    public static boolean shouldShowAd(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        boolean hideAd = sharedPreferences.getBoolean(KEY_HIDE_AD, false);
        if (hideAd) {
            long lastAdShownTime = sharedPreferences.getLong(KEY_LAST_AD_SHOWN_TIME, 0);
            long currentTimeMillis = System.currentTimeMillis();

            // 현재 시간과 "오늘하루 안보기" 버튼을 클릭한 시간을 비교하여 2분(120,000 밀리초)이 지났는지 확인합니다.
            if (currentTimeMillis - lastAdShownTime >= 30000) {
                // 2분이 지났으므로 광고를 표시합니다.
                return true;
            }else{
                return false;
            }
        }
        return true;
    }
}
