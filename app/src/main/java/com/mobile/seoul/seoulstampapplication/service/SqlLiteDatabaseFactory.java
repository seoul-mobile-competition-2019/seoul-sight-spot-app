package com.mobile.seoul.seoulstampapplication.service;


import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.seoul.seoulstampapplication.constant.SightConstant;

import static android.content.Context.MODE_PRIVATE;

public class SqlLiteDatabaseFactory {

    private static SQLiteDatabase sQLiteDatabase = null;

    public static SQLiteDatabase getSqLiteDatabase(AppCompatActivity appCompatActivity) {
        if (sQLiteDatabase != null) {
            return sQLiteDatabase;
        }
        sQLiteDatabase = appCompatActivity.openOrCreateDatabase(SightConstant.DB_NAME, MODE_PRIVATE, null);
        return sQLiteDatabase;
    }
}
