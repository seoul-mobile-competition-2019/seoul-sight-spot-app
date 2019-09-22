package com.mobile.seoul.seoulstampapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mobile.seoul.seoulstampapplication.activity.BarcodeActivity;
import com.mobile.seoul.seoulstampapplication.activity.SightActivity;
import com.mobile.seoul.seoulstampapplication.enums.Sight;
import com.mobile.seoul.seoulstampapplication.service.SqlLiteDatabaseService;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.BARCODE_REQUEST_CODE;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.BARCODE_RESULT_KEY;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.SELECT_SIGHT_LOCK_BY_ID;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.SIGHT_KEY;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.UPDATE_SIGHT_IS_LOCKED;
import static com.mobile.seoul.seoulstampapplication.enums.Sight.BANPO_BRIDGE;
import static com.mobile.seoul.seoulstampapplication.enums.Sight.CHEONGGYECHEON;
import static com.mobile.seoul.seoulstampapplication.enums.Sight.COEX;
import static com.mobile.seoul.seoulstampapplication.enums.Sight.HANGANG;
import static com.mobile.seoul.seoulstampapplication.enums.Sight.HANOK;
import static com.mobile.seoul.seoulstampapplication.enums.Sight.LOTTE_TOWER;
import static com.mobile.seoul.seoulstampapplication.enums.Sight.NAMSAN;
import static com.mobile.seoul.seoulstampapplication.enums.Sight.STAR_LOAD;

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
        sqLiteDatabase = SqlLiteDatabaseService.getSqLiteDatabase(this);
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
        if (checkSightLock) {
            Intent intent = new Intent(getApplicationContext(), BarcodeActivity.class);
            intent.putExtra(SIGHT_KEY, sight.name());
            startActivityForResult(intent, BARCODE_REQUEST_CODE);
            return;
        }
        startActivity(createSightIntent(sight));
    }

    private boolean checkSightLock(Sight sight) {
        Cursor c = sqLiteDatabase.rawQuery(String.format(SELECT_SIGHT_LOCK_BY_ID, sight.getDbId()), null);
        c.moveToNext();
        return StringUtils.equals(c.getString(c.getColumnIndex("is_locked")), "Y") ? true : false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == BARCODE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data.getBooleanExtra(BARCODE_RESULT_KEY, false)) {
                Sight sight = Sight.valueOf(data.getStringExtra(SIGHT_KEY));
                sqLiteDatabase.execSQL(String.format(UPDATE_SIGHT_IS_LOCKED, sight.getDbId()));
                startActivity(createSightIntent(sight));
                return;
            }
            Toast.makeText(this, "Qr 코드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private Intent createSightIntent(Sight sight) {
        Intent intent = new Intent(getApplicationContext(), SightActivity.class);
        intent.putExtra(SIGHT_KEY, sight.name());
        return intent;
    }
}