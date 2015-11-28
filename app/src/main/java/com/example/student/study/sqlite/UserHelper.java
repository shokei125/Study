package com.example.student.study.sqlite;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

/**
 * Created by student on 15/11/28.
 */
public class UserHelper extends AbstractSQLite {

    private String _table = "Users";
    private String _colID = "id";
    private String _colName = "name";
    private String _colAddress = "address";
    private String _colTel = "tel";
    public static final String createSQL = "CREATE TABLE [Users] (" +
            "[id] INTEGER PRIMARY KEY AUTOINCREMENT," +
            "[name] VARCHAR(20)," +
            "[address] VARCHAR(100)," +
            "[tel] VARCHAR(20)" +
            ");";

    public UserHelper(Context context) {
        super(context);
    }

    public boolean insert(String name, String address, String tel) {
        ContentValues value = new ContentValues();
        value.put("name", name);
        value.put("address", address);
        value.put("tel", tel);
        try {
            sqliteDatabase.insert(_table, null, value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public

    public boolean update() {
        return true;
    }

    public boolean delete() {
        return true;
    }
}
