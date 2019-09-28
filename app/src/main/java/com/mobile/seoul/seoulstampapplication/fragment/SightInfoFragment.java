package com.mobile.seoul.seoulstampapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.seoul.seoulstampapplication.R;
import com.mobile.seoul.seoulstampapplication.enums.Sight;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.SIGHT_KEY;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SightInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SightInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SightInfoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @BindView(R.id.info_name)
    TextView infoName;
    @BindView(R.id.sight_info)
    TextView sightInfo;
    @BindView(R.id.info_time)
    TextView infoTime;
    @BindView(R.id.btn_location)
    Button locationBtn;
    @BindView(R.id.btn_web)
    Button webBtn;
    @BindView(R.id.btn_tel)
    Button telBtn;
    @BindView(R.id.info_main_image)
    ImageView infoMainImage;


    private OnFragmentInteractionListener mListener;

    public SightInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SightInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SightInfoFragment newInstance(String param1, String param2) {
        SightInfoFragment fragment = new SightInfoFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sight_info, container, false);
        ButterKnife.bind(this, view);
        Sight sight = Sight.valueOf(getActivity().getIntent().getStringExtra(SIGHT_KEY));
        Locale lan = getResources().getConfiguration().locale;

        String str = lan.getLanguage();

        switch (str) {
            case "ko":
                infoName.setText(getString(sight.getKoreaName()));
                locationBtn.setText(getString(sight.getKorLocation()));
                sightInfo.setText(getString(sight.getKoreaInfo()));
                infoTime.setText(getString(sight.getKoreanOprationTime()));
                break;
            default:
                infoName.setText(getString(sight.getEnglishName()));
                locationBtn.setText(getString(sight.getEnLocation()));
                sightInfo.setText(getString(sight.getEnglishInfo()));
                infoTime.setText(getString(sight.getEnglishOprationTime()));
                break;
        }

        infoMainImage.setImageResource(sight.getDrawableId());
        webBtn.setText(getString(sight.getHomePage()));
        webBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(sight.getHomePage())));
            startActivity(intent);
        });
        telBtn.setText(getString(sight.getTel()));
        telBtn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + getString(sight.getTel())));
            startActivity(intent);
        });

        locationBtn.setOnClickListener(v -> {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse(getString(sight.getMapUrl())));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            startActivity(intent);
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
