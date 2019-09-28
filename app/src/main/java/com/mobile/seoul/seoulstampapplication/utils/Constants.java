package com.mobile.seoul.seoulstampapplication.utils;

public class Constants {

    public static final boolean IS_DEBUG_ENABLE = true;

    //public static final boolean IS_AD_MOB_ENABLE = true;

    public static final String SKU_REMOVE_ADS = "com.progressio.colorbook.removeads";

    public interface ColorBookConstant {
        //Images list grid size
        int IMAGES_LIST_GRID_SIZE = 2;
        //Interstitial Ad Display count on back press
        int DISPLAY_INTERSTITIAL_AD_COUNT = 5;

        String BITMAP = "bitmap";
        String TEXT_PLAIN = "text/html";

        String TERMS_CONDITION_URL = "https://firebasestorage.googleapis.com/v0/b/progressioproduct.appspot.com/o/ColorBook%2Fterms_and_conditions.html?alt=media&token=2cd0518c-0953-4fa0-88bf-5d5fe324fd3e";
        String PRIVACY_POLICY_URL = "https://firebasestorage.googleapis.com/v0/b/progressioproduct.appspot.com/o/ColorBook%2Fprivacy_policy.html?alt=media&token=8c0421cb-eb22-4c00-bf88-bb96a20ec922";
    }

    public interface PreferenceConstant {
        String SELECTED_COLOR = "SELECTED_COLOR";
        String IS_FIRST_TIME = "IS_FIRST_TIME";
        String BACK_PRESS_COUNT_INTERSTITIAL_AD = "BACK_PRESS_COUNT_INTERSTITIAL_AD";
        String IS_AD_REMOVED = "IS_AD_REMOVED";
    }

    public interface BundleExtra {
        String CATEGORY = "category";
        String IMAGES = "images";
    }

    public interface Delay {
        int MIN_TIME_BETWEEN_CLICKS = 300; //in ms
    }

    public interface Category {
        String ANIMALS = "Animals";
        String BIRDS = "Birds";
        String FLOWERS = "Flowers";
        String FRUITS = "Fruits";
        String INSECTS = "Insects";
        String PRINCESS = "Princess";
        String SEA_ANIMALS = "Sea Animals";
        String SHAPES = "Shapes";
        String VEGETABLES = "Vegetables";
        String VEHICLES = "Vehicles";
    }

    public interface DynamicLinkConstant {
        String DEEP_LINK_SCHEMA = "https";
        String DEEP_LINK_HOST = "progressiotechnolab.com";
        String WEBSITE = "https://progressio.page.link/colorbook";
        String DOMAIN_URI_PREFIX = "https://progressio.page.link";
    }
}
