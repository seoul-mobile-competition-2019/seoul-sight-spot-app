package com.mobile.seoul.seoulstampapplication.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.mobile.seoul.seoulstampapplication.ColorBook;
import com.mobile.seoul.seoulstampapplication.R;
import com.mobile.seoul.seoulstampapplication.activity.SightActivity;
import com.mobile.seoul.seoulstampapplication.model.AsynImageLoader;
import com.mobile.seoul.seoulstampapplication.model.SaveImageAsyn;
import com.mobile.seoul.seoulstampapplication.photoview.ColourImageView;
import com.mobile.seoul.seoulstampapplication.photoview.PhotoViewAttacher;
import com.mobile.seoul.seoulstampapplication.utils.Constants;
import com.mobile.seoul.seoulstampapplication.utils.PreferenceUtils;
import com.mobile.seoul.seoulstampapplication.utils.ShareImageUtil;
import com.mobile.seoul.seoulstampapplication.view.MyProgressDialog;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.COLOR_IMAGE_PATH_KEY;

public class SightColorFragment extends Fragment implements OnBackPressedListener{

    protected final PreferenceUtils preferenceUtils = ColorBook.getInstance().getAppComponent().providePreferenceUtils();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.tv_save)
    AppCompatTextView tvSave;
    @BindView(R.id.tv_share)
    AppCompatTextView tvShare;
    @BindView(R.id.iv_selected_color)
    AppCompatImageView ivSelectedColor;
    @BindView(R.id.civ_canvas)
    ColourImageView civCanvas;
    @BindView(R.id.tl_ms_paint_color_table)
    TableLayout tlMsPaintColorTable;

    private FragmentManager fragmentManager;
    private SightActivity sightActivity;
    private String mParam1;
    private String mParam2;
    private String url;
    private AppCompatImageView currentColor;
    private PhotoViewAttacher photoViewAttacher;
    private OnFragmentInteractionListener mListener;
    private Bitmap cachedBitmap;
    private boolean onBackPress = false;

    public SightColorFragment() {
    }

    @OnClick({R.id.tv_share, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                Log.i("저장 확인", "저장");
                saveToLocal();
                break;
            case R.id.tv_share:
                Log.i("공유 확인", "공유");
                shareImage();
                break;

        }
    }

    private void saveToLocal() {
        MyProgressDialog.show(sightActivity, null, "Image saved to local");
        SaveImageAsyn saveImageAsyn = new SaveImageAsyn();
        saveImageAsyn.execute(civCanvas.getmBitmap(), url.hashCode());
        saveImageAsyn.setOnSaveSuccessListener(path -> {
            MyProgressDialog.DismissDialog();
            if (path == null) {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to save the image. Please try again!", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(getActivity().getApplicationContext(), String.format("Your Paint has been saved in：%1$s", path), Toast.LENGTH_SHORT);
            }
        });
    }

    private void shareImage() {
        MyProgressDialog.show(sightActivity, null, "Image saved to local");
        SaveImageAsyn saveImageAsyn = new SaveImageAsyn();
        saveImageAsyn.execute(civCanvas.getmBitmap(), url.hashCode());
        saveImageAsyn.setOnSaveSuccessListener(path -> {
            MyProgressDialog.DismissDialog();
            if (path == null) {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to save the image. Please try again!", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(getActivity().getApplicationContext(), String.format("Your Paint has been saved in：%1$s", path), Toast.LENGTH_SHORT);
                ShareImageUtil.getInstance(sightActivity).shareImage(path);
            }
        });
    }

    public static SightColorFragment newInstance(String param1, String param2) {
        SightColorFragment fragment = new SightColorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sight_color, container, false);
        ButterKnife.bind(this, view);
        initialization();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SightActivity) {
            sightActivity = (SightActivity) context;
        }

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onBackPressed() {
        saveToLocal();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void initialization() {
        fragmentManager = sightActivity.getSupportFragmentManager();

        setAllViewsSelected();

        currentColor = ivSelectedColor;
        ivSelectedColor.setImageDrawable(new ColorDrawable(preferenceUtils.getInteger(Constants.PreferenceConstant.SELECTED_COLOR)));
        changeSelectedColor(currentColor);

        for (int i = 0; i < tlMsPaintColorTable.getChildCount(); i++) {
            for (int j = 0; j < ((TableRow) tlMsPaintColorTable.getChildAt(i)).getChildCount(); j++) {
                if (((TableRow) tlMsPaintColorTable.getChildAt(i)).getChildAt(j) instanceof AppCompatButton) {
                    ((TableRow) tlMsPaintColorTable.getChildAt(i)).getChildAt(j).setOnClickListener(v -> {
                        int color = ((ColorDrawable) v.getBackground()).getColor();
                        changeSelectedColor(color);
                    });
                    ((AppCompatButton) ((TableRow) tlMsPaintColorTable.getChildAt(i)).getChildAt(j)).setHeight(((TableRow) tlMsPaintColorTable.getChildAt(i)).getChildAt(j).getWidth());
                }
            }
        }
        url = getActivity().getIntent().getStringExtra(COLOR_IMAGE_PATH_KEY);
        Log.i("url 확인", url);
        loadLargeImage();
    }

    private void changeSelectedColor(int color) {
        civCanvas.setColor(color);
        currentColor.setImageDrawable(new ColorDrawable(color));
    }

    private void changeSelectedColor(ImageView currentColor) {
        civCanvas.setColor(((ColorDrawable) currentColor.getDrawable()).getColor());
    }

    private void setAllViewsSelected() {
        tvSave.setSelected(true);
        tvShare.setSelected(true);
    }

    private void loadLargeImage() {
        MyProgressDialog.show(sightActivity, null, "Initial Paints");
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                AsynImageLoader.showLagreImageAsynWithAllCacheOpen(civCanvas, url, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {
                        //
                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {
                        MyProgressDialog.DismissDialog();
                        Toast.makeText(getActivity().getApplicationContext(), "Failed to load an image. Please try again!", Toast.LENGTH_SHORT).show();
                        fragmentManager.popBackStackImmediate();
                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                        photoViewAttacher = new PhotoViewAttacher(civCanvas, bitmap);
                        //rotate screen load bitmap saved in the savestates
                        if (cachedBitmap != null) {
                            civCanvas.setImageBT(cachedBitmap);
                        } else {
                            openSaveImage(url.hashCode());
                        }
                        MyProgressDialog.DismissDialog();
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {
                        MyProgressDialog.DismissDialog();
                        Toast.makeText(getActivity().getApplicationContext(), "Failed to load an image. Please try again!", Toast.LENGTH_SHORT).show();
                        fragmentManager.popBackStackImmediate();
                    }
                });
            }
        }.execute();
    }

    private void openSaveImage(int hashCode) {
        try {
            String root = Environment.getExternalStorageDirectory().getPath() + "/" + getString(R.string.app_name) + "/";
            String path = root + hashCode + ".png";
            File file = new File(path);
            if (!file.exists()) {
                throw new Exception("open image failed");
            }
            Bitmap bMap = BitmapFactory.decodeFile(path);
            civCanvas.setImageBT(bMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
