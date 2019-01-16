package com.example.a51044.myfirstapp.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/714:28<p>
 * <p>更改时间：2019/1/714:28<p>
 * <p>版本号：1<p>
 */
public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context) {
        super(context, "app", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists firstapp(id Integer primary key autoincrement,content text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
