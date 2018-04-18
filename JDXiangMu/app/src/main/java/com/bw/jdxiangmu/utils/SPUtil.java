package com.bw.jdxiangmu.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lenovo on 2018/4/12.
 */

public class SPUtil {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SPUtil(Context context, String fileName) {
        preferences = context.getSharedPreferences(fileName, context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 删除SP里指定key对应的数据项
     * @param key
     */
    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 获取SP数据里指定key对应的value。如果key不存在，则返回默认值defValue。
     * @param key
     * @param defValue
     * @return
     */
    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return preferences.getFloat(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }

    /**
     * 判断SP是否包含特定key的数据
     * @param key
     * @return
     */
    public boolean contains(String key){
        return preferences.contains(key);
    }


}
