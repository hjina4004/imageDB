package com.example.jina.imagedb;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "imagedb.mo3";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getImages() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"0 _id", "src", "key"};
        String sqlTables = "tbl_image";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

        c.moveToFirst();
        return c;
    }
}
