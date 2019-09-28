package com.mobile.seoul.seoulstampapplication.model;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.mobile.seoul.seoulstampapplication.ColorBook;
import com.mobile.seoul.seoulstampapplication.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveImageAsyn extends AsyncTask {

    private OnSaveFinishListener onSaveFinishListener;
    private String path;
    private String name;

    @Override
    protected Object doInBackground(Object[] objects) {
        Bitmap bmp = (Bitmap) objects[0];
        path = Environment.getExternalStorageDirectory().getPath() + "/" + ColorBook.getInstance().getString(R.string.app_name) + "/";
        File dir = new File(path);
        if (!dir.exists())
            dir.mkdirs();
        name = objects[1] + ".png";
        File file = new File(dir, name);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            out.close();
            return "SUCCESS";
        } catch (Exception e) {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
            return "FAILED";
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if ("SUCCESS".equals(o)) {
            if (onSaveFinishListener != null) {
                onSaveFinishListener.onSaveFinish(path + name);
            }
        } else {
            if (onSaveFinishListener != null) {
                onSaveFinishListener.onSaveFinish(null);
            }
        }
    }

    public interface OnSaveFinishListener {
        /**
         * @param path if null save failed
         */
        void onSaveFinish(String path);
    }

    public OnSaveFinishListener getOnSaveFinishListener() {
        return onSaveFinishListener;
    }

    public void setOnSaveSuccessListener(OnSaveFinishListener onSaveFinishListener) {
        this.onSaveFinishListener = onSaveFinishListener;
    }
}
