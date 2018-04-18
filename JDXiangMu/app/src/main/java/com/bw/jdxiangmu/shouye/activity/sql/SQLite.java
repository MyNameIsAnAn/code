package com.bw.jdxiangmu.shouye.activity.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2018/3/24.
 */

public class SQLite extends SQLiteOpenHelper {

    public SQLite(Context context) {
        super(context, "zhi.db", null, 1); // 第2个参数 数据库的名字，第3个参数用默认的CusorFactory,第3个参数数据库版本号
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建名字为user的表主键自动增长aid,title,desc都是字符串类型
        String sql = "CREATE TABLE sousuo(personid integer primary key autoincrement, ss varchar(20)) ";
        //通过sql语句创建表
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
