package com.mobile.seoul.seoulstampapplication.photoview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

// TODO: 13-04-2019 - remove this dialog
public class ProgressLoading extends Dialog {
    private static ProgressLoading dialog;

    public ProgressLoading(Context context) {
        super(context);
    }

    /**
     * press back button can dismiss progressdialog
     *
     * @param context
     * @param cancelable
     */
    public static void show(Context context, Boolean cancelable) {
        dialog = new ProgressLoading(context);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(cancelable);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        FrameLayout detail_layout = new FrameLayout(context);
        ProgressBar progressBar = new ProgressBar(context);
        detail_layout.addView(progressBar);
        dialog.setContentView(detail_layout);
        dialog.getWindow()
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    public static void DismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void setOndismissListener(OnDismissListener ondismissListener) {
        dialog.setOnDismissListener(ondismissListener);
    }


}
