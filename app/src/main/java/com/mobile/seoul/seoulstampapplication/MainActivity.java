package com.mobile.seoul.seoulstampapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.seoul.seoulstampapplication.activity.BarcodeActivity;
import com.mobile.seoul.seoulstampapplication.activity.SightActivity;
import com.mobile.seoul.seoulstampapplication.constant.SightConstant;
import com.mobile.seoul.seoulstampapplication.service.SqlLiteDatabaseFactory;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.SELECT_SIGHT_LOCK_BY_ID;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.Sight.BANPO_BRIDGE;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.Sight.CHEONGGYECHEON;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.Sight.COEX;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.Sight.HANGANG;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.Sight.HANOK;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.Sight.LOTTE_TOWER;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.Sight.NAMSAN;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.Sight.STAR_LOAD;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openDB();
        initView();

    }

    @SuppressLint("NewApi")
    private void openDB() {
        sqLiteDatabase = SqlLiteDatabaseFactory.getSqLiteDatabase(this);
    }

    @SuppressLint("NewApi")
    private void initView() {
        Arrays.asList(SightConstant.Sight.values()).forEach(
                btn -> {
                    switch (btn) {
                        case NAMSAN:
                            findViewById(btn.getButtonId()).setOnClickListener(view -> nextActivity(checkSightLock(NAMSAN), NAMSAN));
                            break;
                        case CHEONGGYECHEON:
                            findViewById(btn.getButtonId()).setOnClickListener(view -> nextActivity(checkSightLock(CHEONGGYECHEON), CHEONGGYECHEON));
                            break;
                        case HANOK:
                            findViewById(btn.getButtonId()).setOnClickListener(view -> nextActivity(checkSightLock(HANOK), HANOK));
                            break;
                        case HANGANG:
                            findViewById(btn.getButtonId()).setOnClickListener(view -> nextActivity(checkSightLock(HANGANG), HANGANG));
                            break;
                        case COEX:
                            findViewById(btn.getButtonId()).setOnClickListener(view -> nextActivity(checkSightLock(COEX), COEX));
                            break;
                        case STAR_LOAD:
                            findViewById(btn.getButtonId()).setOnClickListener(view -> nextActivity(checkSightLock(STAR_LOAD), STAR_LOAD));
                            break;
                        case LOTTE_TOWER:
                            findViewById(btn.getButtonId()).setOnClickListener(view -> nextActivity(checkSightLock(LOTTE_TOWER), LOTTE_TOWER));
                            break;
                        case BANPO_BRIDGE:
                            findViewById(btn.getButtonId()).setOnClickListener(view -> nextActivity(checkSightLock(BANPO_BRIDGE), BANPO_BRIDGE));
                            break;
                    }
                }
        );
    }

    private void nextActivity(boolean checkSightLock, SightConstant.Sight sight) {
        // TODO: sightLock에 따라 sight에 맞는 다음 액티비티 load, checkSightLock이 false sight에 맞는 Activity, true 시 바코드 Activity load

        Log.i("checkSightLock 확인", String.valueOf(checkSightLock));
        if (checkSightLock) {
//            startActivity(new Intent(getApplicationContext(), BarcodeActivity.class));
            startActivity(new Intent(getApplicationContext(), SightActivity.class));
            return;
        }
        startActivity(new Intent(getApplicationContext(), SightActivity.class));
    }

    private boolean checkSightLock(SightConstant.Sight sight) {
        Cursor c = sqLiteDatabase.rawQuery(String.format(SELECT_SIGHT_LOCK_BY_ID, sight.getDbId()), null);
        c.moveToNext();
        return StringUtils.equals(c.getString(c.getColumnIndex("is_locked")), "Y") ? true : false;
    }
}
