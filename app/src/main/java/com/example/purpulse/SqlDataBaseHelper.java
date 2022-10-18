package com.example.purpulse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SqlDataBaseHelper extends SQLiteOpenHelper {
    private static final String DataBaseName = "db";
    private static final int DataBaseVersion = 4;

    public SqlDataBaseHelper(@Nullable Context context, @Nullable String name,
                             @Nullable SQLiteDatabase.CursorFactory factory, int version, String TableName) {
        super(context, DataBaseName, factory, DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SqlTable = "CREATE TABLE IF NOT EXISTS Users (" +
                "name TEXT not null," +
                "account TEXT not null," +
                "password TEXT not null," +
                "email TEXT not null," +
                "birthday TEXT not null," +
                "gender TEXT not null," +
                "height TEXT not null," +
                "weight TEXT not null" +
                ")";
        Log.d("test","test");
        sqLiteDatabase.execSQL(SqlTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String SQL = "DROP TABLE IF EXISTS Users";
        sqLiteDatabase.execSQL(SQL);
        onCreate(sqLiteDatabase);
    }
}
