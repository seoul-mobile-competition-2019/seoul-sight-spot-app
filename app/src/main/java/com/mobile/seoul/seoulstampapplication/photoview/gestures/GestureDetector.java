package com.mobile.seoul.seoulstampapplication.photoview.gestures;

import android.view.MotionEvent;

public interface GestureDetector {

    boolean onTouchEvent(MotionEvent ev);

    boolean isScaling();

    boolean isDragging();

    void setOnGestureListener(OnGestureListener listener);

}
