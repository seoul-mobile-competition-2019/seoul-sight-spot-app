package com.mobile.seoul.seoulstampapplication.constant;

import com.mobile.seoul.seoulstampapplication.R;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class SightConstant {

    public static final String DB_NAME = "seoul_sight";

    public static final String SELECT_SIGHT_LOCK_BY_ID = "SELECT is_locked FROM " + Table.SIGHT.name() + " WHERE id=%s";
    public static final String CREATE_SIGHT_TABLE = "CREATE TABLE IF NOT EXISTS " + SightConstant.Table.SIGHT.name() + " (is_locked VARCHAR(10), id VARCHAR(10) );";
    public static final String INSERT_SIGHT_INIT_DATA = "INSERT INTO " + SightConstant.Table.SIGHT.name() + "(is_locked, id) VALUES ('Y', %d)";

    @Getter
    @AllArgsConstructor
    public enum Table {
        SIGHT, SIGHT_INFO, SIGHT_COLOR;
    }

    @Getter
    @AllArgsConstructor
    public enum Sight {
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
