package com.mobile.seoul.seoulstampapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.seoul.seoulstampapplication.activity.BarcodeActivity;
import com.mobile.seoul.seoulstampapplication.activity.SightActivity;
import com.mobile.seoul.seoulstampapplication.service.SqlLiteDatabaseFactory;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.mobile.seoul.seoulstampapplication.MainActivity.Sight.BANPO_BRIDGE;
import static com.mobile.seoul.seoulstampapplication.MainActivity.Sight.CHEONGGYECHEON;
import static com.mobile.seoul.seoulstampapplication.MainActivity.Sight.COEX;
import static com.mobile.seoul.seoulstampapplication.MainActivity.Sight.HANGANG;
import static com.mobile.seoul.seoulstampapplication.MainActivity.Sight.HANOK;
import static com.mobile.seoul.seoulstampapplication.MainActivity.Sight.LOTTE_TOWER;
import static com.mobile.seoul.seoulstampapplication.MainActivity.Sight.NAMSAN;
import static com.mobile.seoul.seoulstampapplication.MainActivity.Sight.STAR_LOAD;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.SELECT_SIGHT_LOCK_BY_ID;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openDB();
        initView();

    }

    private void openDB() {
        sqLiteDatabase = SqlLiteDatabaseFactory.getSqLiteDatabase(this);
    }

    @SuppressLint("NewApi")
    private void initView() {
        Arrays.asList(Sight.values()).forEach(
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

    private void nextActivity(boolean checkSightLock, Sight sight) {
        // TODO: sightLock에 따라 sight에 맞는 다음 액티비티 load, checkSightLock이 false sight에 맞는 Activity, true 시 바코드 Activity load
        if (checkSightLock) {
            startActivity(new Intent(getApplicationContext(), BarcodeActivity.class));
            return;
        }
        startActivity(new Intent(getApplicationContext(), SightActivity.class));
    }

    private boolean checkSightLock(Sight sight) {
        // TODO: sql lite로 내부 DB 사용해서 열었는지 아닌지 체크
        Cursor c = sqLiteDatabase.rawQuery(String.format(SELECT_SIGHT_LOCK_BY_ID, sight.getDbId()), null);
        return (c.getString(c.getColumnIndex("isLocked")) == "Y") ? true : false;
    }

    @Getter
    @AllArgsConstructor
    enum Sight {
        NAMSAN(R.id.namsan_sight_btn, 1),
        CHEONGGYECHEON(R.id.cheonggyecheon_sight_btn, 2),
        HANOK(R.id.hanok_sight_btn, 3),
        HANGANG(R.id.hangang_sight_btn, 4),
        COEX(R.id.coex_sight_btn, 5),
        STAR_LOAD(R.id.starload_sight_btn, 6),
        LOTTE_TOWER(R.id.lottetower_sight_btn, 7),
        BANPO_BRIDGE(R.id.banpobridge_sight_btn, 8);
        private int buttonId;
        private int dbId;
    }
}
