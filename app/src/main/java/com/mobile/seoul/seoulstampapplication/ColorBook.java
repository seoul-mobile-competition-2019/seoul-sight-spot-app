package com.mobile.seoul.seoulstampapplication;

import android.app.Application;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.mobile.seoul.seoulstampapplication.dagger.AppComponent;
import com.mobile.seoul.seoulstampapplication.dagger.AppModule;
import com.mobile.seoul.seoulstampapplication.dagger.DaggerAppComponent;
import com.mobile.seoul.seoulstampapplication.utils.Constants;
import com.mobile.seoul.seoulstampapplication.utils.CrashReportingTree;
import com.mobile.seoul.seoulstampapplication.utils.ImageLoaderUtil;
import com.mobile.seoul.seoulstampapplication.utils.PreferenceUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import timber.log.Timber;

public class ColorBook extends Application {
    private static ColorBook colorBookInstance;
    private AppComponent appComponent;

    public static ColorBook getInstance() {
        return colorBookInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        Log.i("colorBook확인", "colorBook확인");
        super.onCreate();

        colorBookInstance = this;

        //injecting dependencies
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);

        //Preferences
        PreferenceUtils preferenceUtils = getInstance().getAppComponent().providePreferenceUtils();

        //region #Timber
        if (Constants.IS_DEBUG_ENABLE) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
        //endregion

        //Set value when app load first time
        if (!preferenceUtils.getBoolean(Constants.PreferenceConstant.IS_FIRST_TIME)) {
            //Set Music On
            //Set App Run First Time
            preferenceUtils.setBoolean(Constants.PreferenceConstant.IS_FIRST_TIME, true);
            //Set Default Selected Color
            preferenceUtils.setInteger(Constants.PreferenceConstant.SELECTED_COLOR, ContextCompat.getColor(this, R.color.ms_paint_color_03));
        }
        initImageLoader();
    }

    public void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(100 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoaderUtil.getInstance().init(config);
    }
}
