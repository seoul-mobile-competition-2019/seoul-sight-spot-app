package com.mobile.seoul.seoulstampapplication.enums;


import com.mobile.seoul.seoulstampapplication.R;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sight {
    NAMSAN(R.id.namsan_sight_btn, 1, "http://www.heritage.go.kr/heritage/cul/qrCodeLink.jsp?VdkVgwKey=11,02230000,11&pageNo=5_2_1_0&queryText=*"),
    CHEONGGYECHEON(R.id.cheonggyecheon_sight_btn, 2, ""),
    HANOK(R.id.hanok_sight_btn, 3, ""),
    HANGANG(R.id.hangang_sight_btn, 4, ""),
    COEX(R.id.coex_sight_btn, 5, ""),
    STAR_LOAD(R.id.starload_sight_btn, 6, ""),
    LOTTE_TOWER(R.id.lottetower_sight_btn, 7, ""),
    BANPO_BRIDGE(R.id.banpobridge_sight_btn, 8, "");
    private int buttonId;
    private int dbId;
    private String qrCodeUrl;
}
