package com.mobile.seoul.seoulstampapplication.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class SightConstant {

    public static final String DB_NAME = "seoul_sight";

    public static final String SELECT_SIGHT_LOCK_BY_ID = "SELECT isLocked FROM " + Table.SIGHT.name() + " WHERE id=%s";

    @Getter
    @AllArgsConstructor
    public enum Table {
        SIGHT, SIGHT_INFO, SIGHT_COLOR;
    }
}
