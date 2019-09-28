package com.mobile.seoul.seoulstampapplication.activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.seoul.seoulstampapplication.R;
import com.mobile.seoul.seoulstampapplication.enums.Sight;
import com.mobile.seoul.seoulstampapplication.service.SqlLiteDatabaseService;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.SELECT_SIGHT_LOCK_BY_ID;
import static com.mobile.seoul.seoulstampapplication.enums.Sight.NAMSAN;

public class StampActivity extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);
        openDB();
        initView();
    }

    @SuppressLint("NewApi")
    private void initView() {
        Arrays.asList(Sight.values()).forEach(
                sight -> {
                    View view;
                    boolean flag;
                    switch (sight) {
                        case NAMSAN:
                            view = findViewById(sight.getStampId());
                            setOpenBackground(sight, view);
                            break;
                        case CHEONGGYECHEON:
                            view = findViewById(sight.getStampId());
                            setOpenBackground(sight, view);
                            break;
                        case HANOK:
                            view = findViewById(sight.getStampId());
                            setOpenBackground(sight, view);
                            break;
                        case HANGANG:
                            view = findViewById(sight.getStampId());
                            setOpenBackground(sight, view);
                            break;
                        case COEX:
                            view = findViewById(sight.getStampId());
                            setOpenBackground(sight, view);
                            break;
                        case STAR_LOAD:
                            view = findViewById(sight.getStampId());
                            setOpenBackground(sight, view);
                            break;
                        case LOTTE_TOWER:
                            view = findViewById(sight.getStampId());
                            setOpenBackground(sight, view);
                            break;
                        case BANPO_BRIDGE:
                            view = findViewById(sight.getStampId());
                            setOpenBackground(sight, view);
                            break;
                        case GYEONBOK:
                            view = findViewById(sight.getStampId());
                            setOpenBackground(sight, view);
                            break;
                        case DUKSOO:
                            view = findViewById(sight.getStampId());
                            setOpenBackground(sight, view);
                            break;
                    }
                }
        );
    }

    private void setOpenBackground(Sight sight, View view) {
        if (checkSightLock(sight) == false) {
            ((ImageView)view).setImageResource(sight.getStampDrawableId());
        }
    }

    private boolean checkSightLock(Sight sight) {
        Cursor c = sqLiteDatabase.rawQuery(String.format(SELECT_SIGHT_LOCK_BY_ID, sight.getDbId()), null);
        c.moveToNext();
        return StringUtils.equals(c.getString(c.getColumnIndex("is_locked")), "Y") ? true : false;
    }

    @SuppressLint("NewApi")
    private void openDB() {
        sqLiteDatabase = SqlLiteDatabaseService.getSqLiteDatabase(this);
    }
}
