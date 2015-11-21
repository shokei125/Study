package com.example.student.study.sqlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by student on 15/11/21.
 */
public class CreateProductHelper extends SQLiteOpenHelper {

    public CreateProductHelper(Context context) {
        super(context, "sample", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            String sql = "CREATE TABLE [Users] (" +
                    "[id] INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "[name] VARCHAR(20)," +
                    "[add] VARCHAR(100)," +
                    "[tel] VARCHAR(20)" +
                    ");";
            db.execSQL(sql);
        } catch (Exception e) {
            //noop
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
