package com.mobile.seoul.seoulstampapplication.service;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.mobile.seoul.seoulstampapplication.constant.SightConstant.Table;
import com.mobile.seoul.seoulstampapplication.constant.SightConstant;

import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;

public class SqlLiteDatabaseFactory {

    private static SQLiteDatabase sQLiteDatabase = null;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static SQLiteDatabase getSqLiteDatabase(AppCompatActivity appCompatActivity) {
        if (sQLiteDatabase != null) {
            return sQLiteDatabase;
        }
        sQLiteDatabase = appCompatActivity.openOrCreateDatabase(SightConstant.DB_NAME, MODE_PRIVATE, null);
        if(!existSightTable()) {
            sQLiteDatabase.execSQL(SightConstant.CREATE_SIGHT_TABLE);
            Arrays.asList(SightConstant.Sight.values()).forEach(
                    sight -> {
                        sQLiteDatabase.execSQL(String.format(SightConstant.INSERT_SIGHT_INIT_DATA, sight.getDbId()));
                    }
            );
        }
        return sQLiteDatabase;
    }

    private static boolean existSightTable() {
        Cursor cursor = sQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + Table.SIGHT.name() + "'", null);
        return (cursor.getCount() == 1) ? true: false;
    }
}
