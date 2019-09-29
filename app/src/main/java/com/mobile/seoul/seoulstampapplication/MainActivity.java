package com.mobile.seoul.seoulstampapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.mobile.seoul.seoulstampapplication.activity.BarcodeActivity;
import com.mobile.seoul.seoulstampapplication.activity.SightActivity;
import com.mobile.seoul.seoulstampapplication.activity.StampActivity;
import com.mobile.seoul.seoulstampapplication.enums.Sight;
import com.mobile.seoul.seoulstampapplication.service.SqlLiteDatabaseService;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.BARCODE_REQUEST_CODE;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.BARCODE_RESULT_KEY;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.COLOR_IMAGE_PATH_KEY;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.SELECT_SIGHT_LOCK_BY_ID;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.SIGHT_KEY;
import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.UPDATE_SIGHT_IS_LOCKED;
import static com.mobile.seoul.seoulstampapplication.enums.Sight.BANPO_BRIDGE;
import static com.mobile.seoul.seoulstampapplication.enums.Sight.CHEONGGYECHEON;
import static com.mobile.seoul.seoulstampapplication.enums.Sight.COEX;
import static com.mobile.seoul.seoulstampapplication.enums.Sight.DUKSOO;
import static com.mobile.seoul.seoulstampapplication.enums.Sight.GYEONBOK;
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
                    View view;
                    switch (btn) {
                        case NAMSAN:
                            view = findViewById(btn.getButtonId());
                            setOpenBackground(NAMSAN, view);
                            view.setOnClickListener(item -> nextActivity(checkSightLock(NAMSAN), NAMSAN));
                            break;
                        case CHEONGGYECHEON:
                            view = findViewById(btn.getButtonId());
                            setOpenBackground(CHEONGGYECHEON, view);
                            view.setOnClickListener(item -> nextActivity(checkSightLock(CHEONGGYECHEON), CHEONGGYECHEON));
                            break;
                        case HANOK:
                            view = findViewById(btn.getButtonId());
                            setOpenBackground(HANOK, view);
                            view.setOnClickListener(item -> nextActivity(checkSightLock(HANOK), HANOK));
                            break;
                        case HANGANG:
                            view = findViewById(btn.getButtonId());
                            setOpenBackground(HANGANG, view);
                            view.setOnClickListener(item -> nextActivity(checkSightLock(HANGANG), HANGANG));
                            break;
                        case COEX:
                            view = findViewById(btn.getButtonId());
                            setOpenBackground(COEX, view);
                            view.setOnClickListener(item -> nextActivity(checkSightLock(COEX), COEX));
                            break;
                        case STAR_LOAD:
                            view = findViewById(btn.getButtonId());
                            setOpenBackground(STAR_LOAD, view);
                            view.setOnClickListener(item -> nextActivity(checkSightLock(STAR_LOAD), STAR_LOAD));
                            break;
                        case LOTTE_TOWER:
                            view = findViewById(btn.getButtonId());
                            setOpenBackground(LOTTE_TOWER, view);
                            view.setOnClickListener(item -> nextActivity(checkSightLock(LOTTE_TOWER), LOTTE_TOWER));
                            break;
                        case BANPO_BRIDGE:
                            view = findViewById(btn.getButtonId());
                            setOpenBackground(BANPO_BRIDGE, view);
                            view.setOnClickListener(item -> nextActivity(checkSightLock(BANPO_BRIDGE), BANPO_BRIDGE));
                            break;
                        case GYEONBOK:
                            view = findViewById(btn.getButtonId());
                            setOpenBackground(GYEONBOK, view);
                            view.setOnClickListener(item -> nextActivity(checkSightLock(GYEONBOK), GYEONBOK));
                            break;
                        case DUKSOO:
                            view = findViewById(btn.getButtonId());
                            setOpenBackground(DUKSOO, view);
                            view.setOnClickListener(item -> nextActivity(checkSightLock(DUKSOO), DUKSOO));
                            break;
                    }
                }
        );
        findViewById(R.id.stamp_btn).setOnClickListener(view -> {
            startActivity(new Intent (MainActivity.this, StampActivity.class));
        });
    }

    private void setOpenBackground(Sight sight, View view) {
        if (checkSightLock(sight) == false) {
            Log.i("lockOpen확인", sight.name());
            ((ImageButton)view).setImageResource(sight.getDrawableId());
        }
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
                initView();
                startActivity(createSightIntent(sight));
                return;
            }
            Toast.makeText(this, "Qr 코드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private Intent createSightIntent(Sight sight) {
        Intent intent = new Intent(getApplicationContext(), SightActivity.class);
        intent.putExtra(COLOR_IMAGE_PATH_KEY, sight.getAssestsPath());
        intent.putExtra(SIGHT_KEY, sight.name());
        return intent;
    }
}