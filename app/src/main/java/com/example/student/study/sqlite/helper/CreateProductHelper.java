package com.example.student.study.sqlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.student.study.sqlite.UserHelper;

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
            //db.execSQL("DROP TABLE Users;");
            db.execSQL(UserHelper.createSQL);
            db.execSQL("INSERT INTO Users(name,address,tel) values('test1','saitama','080-1111-1111');");
        } catch (Exception e) {
            //noop
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users;");
        onCreate(db);
    }
}
