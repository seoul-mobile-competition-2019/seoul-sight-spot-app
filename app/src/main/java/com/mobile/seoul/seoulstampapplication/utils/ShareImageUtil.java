package com.mobile.seoul.seoulstampapplication.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.content.FileProvider;

import com.mobile.seoul.seoulstampapplication.BuildConfig;
import com.mobile.seoul.seoulstampapplication.R;

import java.io.File;

public class ShareImageUtil {
    private static Context context;
    private static ShareImageUtil shareImageUtil;

    private static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    private ShareImageUtil() {
    }

    public static ShareImageUtil getInstance(Context mContext) {
        context = mContext;
        if (shareImageUtil == null) {
            shareImageUtil = new ShareImageUtil();
        }
        return shareImageUtil;
    }

    /**
     * Share image
     *
     * @param path - image path
     */
    public void shareImage(String path) {
        File file = new File(path);
        Uri uri = FileProvider.getUriForFile(context, AUTHORITY, file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, "Share"));
    }
}
