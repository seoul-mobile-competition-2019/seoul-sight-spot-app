package com.mobile.seoul.seoulstampapplication.service;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.mobile.seoul.seoulstampapplication.constant.SightConstant;
import com.mobile.seoul.seoulstampapplication.enums.Sight;
import com.mobile.seoul.seoulstampapplication.enums.Table;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.SELECT_SIGHT_LOCK_BY_ID;

public class SqlLiteDatabaseService {

    private static SQLiteDatabase sQLiteDatabase = null;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static SQLiteDatabase getSqLiteDatabase(AppCompatActivity appCompatActivity) {
        if (sQLiteDatabase != null) {
            return sQLiteDatabase;
        }

        sQLiteDatabase = appCompatActivity.openOrCreateDatabase(SightConstant.DB_NAME, MODE_PRIVATE, null);
        SqlLiteDatabaseService.dropSightTable();
        if(!existSightTable()) {
            sQLiteDatabase.execSQL(SightConstant.CREATE_SIGHT_TABLE);
            Arrays.asList(Sight.values()).forEach(
                    sight -> {
                        sQLiteDatabase.execSQL(String.format(SightConstant.INSERT_SIGHT_INIT_DATA, sight.getDbId()));
                    }
            );
        }
        return sQLiteDatabase;
    }

    public static void dropSightTable() {
        sQLiteDatabase.execSQL(String.format("DROP TABLE SIGHT"));
    }

    private static boolean existSightTable() {
        Cursor cursor = sQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + Table.SIGHT.name() + "'", null);
        return (cursor.getCount() == 1) ? true: false;
    }
}
