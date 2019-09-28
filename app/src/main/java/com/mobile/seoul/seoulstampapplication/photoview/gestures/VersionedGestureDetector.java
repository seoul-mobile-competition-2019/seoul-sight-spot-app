package com.mobile.seoul.seoulstampapplication.photoview.gestures;

import android.content.Context;

public final class VersionedGestureDetector {

    public static GestureDetector newInstance(Context context, OnGestureListener listener) {
        GestureDetector detector;
        detector = new FroyoGestureDetector(context);
        detector.setOnGestureListener(listener);
        return detector;
    }
}