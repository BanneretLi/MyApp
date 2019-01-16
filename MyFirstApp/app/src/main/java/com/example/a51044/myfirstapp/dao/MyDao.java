package com.example.a51044.myfirstapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a51044.myfirstapp.helper.MyHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/714:27<p>
 * <p>更改时间：2019/1/714:27<p>
 * <p>版本号：1<p>
 */
public class MyDao {
    private SQLiteDatabase db;

    public MyDao(Context context) {
        MyHelper he = new MyHelper(context);
        db = he.getWritableDatabase();
    }

    public void addSql(String content) {
        ContentValues va = new ContentValues();
        va.put("content", content);
        db.insert("firstapp", null, va);
    }

    public void delSql() {
        db.execSQL("delete from firstapp");
    }

    public List<String> selSql() {
        List<String> mList = new ArrayList<>();
        Cursor myCursor = db.query("firstapp", null, null, null, null, null, null);
        while (myCursor.moveToNext()) {
            String content = myCursor.getString(myCursor.getColumnIndex("content"));
            mList.add(content);
        }
        return mList;
    }

}
