package com.mobile.seoul.seoulstampapplication.dagger;

import android.content.Context;

import com.google.gson.Gson;
import com.mobile.seoul.seoulstampapplication.ColorBook;
import com.mobile.seoul.seoulstampapplication.utils.GsonUtils;
import com.mobile.seoul.seoulstampapplication.utils.NetworkUtils;
import com.mobile.seoul.seoulstampapplication.utils.PermissionUtils;
import com.mobile.seoul.seoulstampapplication.utils.PreferenceUtils;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(ColorBook colorBook);

    //required dependencies
    Context provideContext();

    NetworkUtils provideNetworkUtils();

    Gson provideGson();

    Bus provideBus();

    PreferenceUtils providePreferenceUtils();

    GsonUtils provideMasterGson();

    PermissionUtils providePermissionUtils();
}