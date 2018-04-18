package com.bw.jdxiangmu.shouye.activity.bean;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2018/3/16.
 */

public class SQLBean extends SQLiteOpenHelper {

    String sql = "create table user(id integer primary key autoincrement,name varchar(64))";

    public SQLBean(Context context) {
        super(context, "jiajilong.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
