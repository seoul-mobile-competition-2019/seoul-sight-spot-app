package com.mobile.seoul.seoulstampapplication.enums;


import com.mobile.seoul.seoulstampapplication.R;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sight {
    NAMSAN(R.id.namsan_sight_btn, 1, "http://www.heritage.go.kr/heritage/cul/qrCodeLink.jsp?VdkVgwKey=11,02230000,11&pageNo=5_2_1_0&queryText=*",
            R.drawable.img_ntower, "assets://sight/Namsan.png", R.id.sight_stamp_ntower
    , R.drawable.namsan_o, R.string.namsan_kor_name, R.string.namsan_en_name, R.string.namsan_tel
    ,R.string.namsan_kor_location, R.string.namsan_en_location, R.string.namsan_homepage
            , R.string.namsan_kor_info, R.string.namsan_en_info
    , R.string.namsan_kor_oprationtime,  R.string.namsan_en_oprationtime, R.string.namsan_map_url),
    CHEONGGYECHEON(R.id.cheonggyecheon_sight_btn, 2, "http://www.heritage.go.kr/heritage/cul/qrCodeLink.jsp?VdkVgwKey=11,02230000,11&pageNo=5_2_1_0&queryText=*",
            R.drawable.img_cheong, "assets://sight/CHEONGGYECHEON.png", R.id.sight_stamp_cheong
            , R.drawable.cheonggyecheon_o, R.string.cheonggyecheon_kor_name, R.string.cheonggyecheon_en_name, R.string.cheonggyecheon_tel
    , R.string.cheonggyecheon_kor_location, R.string.cheonggyecheon_en_location, R.string.cheonggyecheon_homepage, R.string.cheonggyecheon_kor_info, R.string.cheonggyecheon_en_info
    , R.string.cheonggyecheon_kor_oprationtime, R.string.cheonggyecheon_en_oprationtime, R.string.cheonggyecheon_map_url),
    HANOK(R.id.hanok_sight_btn, 3, "http://www.heritage.go.kr/heritage/cul/qrCodeLink.jsp?VdkVgwKey=11,02230000,11&pageNo=5_2_1_0&queryText=*",
            R.drawable.img_nam, "assets://sight/HANOK.png", R.id.sight_stamp_hanok
            , R.drawable.hanok_o, R.string.hanok_kor_name, R.string.hanok_en_name, R.string.hanok_tel, R.string.hanok_kor_location, R.string.hanok_en_location
    , R.string.hanok_homepage, R.string.hanok_kor_info, R.string.hanok_en_info
            , R.string.hanok_kor_oprationtime, R.string.hanok_en_oprationtime
    , R.string.hanok_map_url),

    HANGANG(R.id.hangang_sight_btn, 4, "http://www.heritage.go.kr/heritage/cul/qrCodeLink.jsp?VdkVgwKey=11,02230000,11&pageNo=5_2_1_0&queryText=*",
            R.drawable.img_yeoeui, "assets://sight/HANGANG.png", R.id.sight_stamp_hangang
            , R.drawable.hangang_o, R.string.hangang_kor_name, R.string.hangang_en_name, R.string.hangang_tel, R.string.hangang_kor_location, R.string.hangang_en_location, R.string.hangang_homepage
    , R.string.hangang_kor_info, R.string.hangang_en_info, R.string.hangang_kor_oprationtime, R.string.hangang_en_oprationtime, R.string.hangang_map_url),
    COEX(R.id.coex_sight_btn, 5, "http://www.heritage.go.kr/heritage/cul/qrCodeLink.jsp?VdkVgwKey=11,02230000,11&pageNo=5_2_1_0&queryText=*",
            R.drawable.img_sm, "assets://sight/COEX.png", R.id.sight_stamp_smtown
            , R.drawable.smtown_o, R.string.coex_kor_name, R.string.coex_en_name, R.string.coex_tel, R.string.coex_kor_location, R.string.coex_en_location, R.string.coex_homepage
    , R.string.coex_kor_info, R.string.coex_en_info
            , R.string.coex_kor_oprationtime, R.string.coex_en_oprationtime, R.string.hangang_map_url),
    STAR_LOAD(R.id.starload_sight_btn, 6, "http://www.heritage.go.kr/heritage/cul/qrCodeLink.jsp?VdkVgwKey=11,02230000,11&pageNo=5_2_1_0&queryText=*",
            R.drawable.img_kstar, "assets://sight/STAR_LOAD.png", R.id.sight_stamp_kstar
            , R.drawable.kstar_o, R.string.kstar_kor_name, R.string.kstar_en_name, R.string.kstar_tel, R.string.kstar_kor_location, R.string.kstar_en_location, R.string.kstar_homepage,
            R.string.kstar_kor_info, R.string.kstar_en_info, R.string.kstar_kor_oprationtime, R.string.kstar_en_oprationtime, R.string.kstar_map_url),
    LOTTE_TOWER(R.id.lottetower_sight_btn, 7, "http://www.heritage.go.kr/heritage/cul/qrCodeLink.jsp?VdkVgwKey=11,02230000,11&pageNo=5_2_1_0&queryText=*",
            R.drawable.img_lotte, "assets://sight/LOTTE_TOWER.png", R.id.sight_stamp_lotte
            , R.drawable.lotte_o, R.string.lotte_kor_name, R.string.lotte_en_name, R.string.lotte_tel, R.string.lotte_kor_location, R.string.lotte_en_location, R.string.lotte_homepage
    , R.string.lotte_kor_info, R.string.lotte_en_info, R.string.lotte_kor_oprationtime, R.string.lotte_en_oprationtime, R.string.lotte_map_url),
    BANPO_BRIDGE(R.id.banpobridge_sight_btn, 8, "http://www.heritage.go.kr/heritage/cul/qrCodeLink.jsp?VdkVgwKey=11,02230000,11&pageNo=5_2_1_0&queryText=*",
            R.drawable.img_sebit, "assets://sight/BANPO_BRIDGE.png", R.id.sight_stamp_sebit
            , R.drawable.sebit_o, R.string.banpo_kor_name, R.string.banpo_en_name, R.string.banpo_tel, R.string.banpo_kor_location, R.string.banpo_en_location, R.string.banpo_homepage,
            R.string.banpo_kor_info, R.string.banpo_en_info, R.string.banpo_kor_oprationtime, R.string.banpo_en_oprationtime, R.string.banpo_map_url),
    GYEONBOK(R.id.gyungbok_sight_btn, 9, "http://www.heritage.go.kr/heritage/cul/qrCodeLink.jsp?VdkVgwKey=11,02230000,11&pageNo=5_2_1_0&queryText=*",
                 R.drawable.img_gyeong, "assets://sight/GYEONBOK.png", R.id.sight_stamp_gyeongbok
            , R.drawable.gyungbok_o, R.string.gyungbok_kor_name, R.string.gyungbok_en_name, R.string.gyungbok_tel, R.string.gyungbok_kor_location, R.string.gyungbok_en_location, R.string.gyungbok_homepage,
            R.string.gyungbok_kor_info, R.string.gyungbok_en_info, R.string.gyungbok_kor_oprationtime, R.string.gyungbok_en_oprationtime
    , R.string.gyungbok_map_url),
    DUKSOO(R.id.duk_sight_btn, 10, "http://www.heritage.go.kr/heritage/cul/qrCodeLink.jsp?VdkVgwKey=11,02230000,11&pageNo=5_2_1_0&queryText=*",
                 R.drawable.img_duk, "assets://sight/DUKSOO.png", R.id.sight_stamp_duksoo
            , R.drawable.duksoo_o, R.string.duksoo_kor_name, R.string.duksoo_en_name, R.string.duksoo_tel, R.string.duksoo_kor_location, R.string.duksoo_en_location, R.string.duksoo_homepage,
            R.string.duksoo_kor_info, R.string.duksoo_en_info, R.string.duksoo_kor_oprationtime, R.string.duksoo_en_oprationtime, R.string.duksoo_map_url)
    ;
    private int buttonId;
    private int dbId;
    private String qrCodeUrl;
    private int drawableId;
    private String assestsPath;
    private int stampId;
    private int stampDrawableId;
    private int koreaName;
    private int englishName;
    private int tel;
    private int korLocation;
    private int enLocation;
    private int homePage;
    private int koreaInfo;
    private int englishInfo;
    private int koreanOprationTime;
    private int englishOprationTime;
    private int mapUrl;
}
