package com.mobile.seoul.seoulstampapplication.activity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mobile.seoul.seoulstampapplication.R;
import com.mobile.seoul.seoulstampapplication.adapter.ViewPagerAdapter;
import com.mobile.seoul.seoulstampapplication.fragment.SightColorFragment;
import com.mobile.seoul.seoulstampapplication.fragment.SightInfoFragment;
import com.mobile.seoul.seoulstampapplication.view_pager.SightViewPager;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.mobile.seoul.seoulstampapplication.constant.SightConstant.SIGHT_KEY;

public class SightActivity extends AppCompatActivity implements SightColorFragment.OnFragmentInteractionListener, SightInfoFragment.OnFragmentInteractionListener {

    private TabLayout tabLayout;
    private SightViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight);

        Log.i("sightKey 확인", getIntent().getStringExtra(SIGHT_KEY));

        tabLayout = findViewById(R.id.layout_tab);
        Arrays.asList(Tab.values()).forEach(tab -> {
            tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView(tab.name())));
        });

        viewPager = findViewById(R.id.pager_content);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);

//        viewPager.requestDisallowInterceptTouchEvent(true);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

    }

    private View createTabView(String tabName) {
        View tabView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab, null);
        TextView textView = tabView.findViewById(R.id.tab_name);
        textView.setText(tabName);
        return tabView;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Getter
    @AllArgsConstructor
    enum Tab {
        INFO,
        COLOR
    }
}
