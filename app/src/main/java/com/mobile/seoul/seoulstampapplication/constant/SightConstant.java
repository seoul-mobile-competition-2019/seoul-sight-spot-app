package com.mobile.seoul.seoulstampapplication.constant;

import com.mobile.seoul.seoulstampapplication.enums.Table;

public class SightConstant {

    public static final String DB_NAME = "seoul_sight";
    public static final String SIGHT_KEY = "sight";
    public static final String COLOR_IMAGE_PATH_KEY = "colorImagePathKey";
    public static final int BARCODE_REQUEST_CODE = 999;
    public static final String BARCODE_RESULT_KEY = "barcode";

    public static final String SELECT_SIGHT_LOCK_BY_ID = "SELECT is_locked FROM " + Table.SIGHT.name() + " WHERE id=%s";
    public static final String CREATE_SIGHT_TABLE = "CREATE TABLE IF NOT EXISTS " + Table.SIGHT.name() + " (is_locked VARCHAR(10), id VARCHAR(10) );";
    public static final String INSERT_SIGHT_INIT_DATA = "INSERT INTO " + Table.SIGHT.name() + "(is_locked, id) VALUES ('Y', %d)";
    public static final String UPDATE_SIGHT_IS_LOCKED = "UPDATE " + Table.SIGHT.name() + " SET is_locked='N' WHERE id=%d";
}
