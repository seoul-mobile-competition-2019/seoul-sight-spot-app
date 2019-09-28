package com.mobile.seoul.seoulstampapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.mobile.seoul.seoulstampapplication.R;
import com.mobile.seoul.seoulstampapplication.adapter.ViewPagerAdapter;
import com.mobile.seoul.seoulstampapplication.enums.Sight;
import com.mobile.seoul.seoulstampapplication.fragment.OnBackPressedListener;
import com.mobile.seoul.seoulstampapplication.fragment.SightColorFragment;
import com.mobile.seoul.seoulstampapplication.fragment.SightInfoFragment;
import com.mobile.seoul.seoulstampapplication.view_pager.SightViewPager;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBackPressed() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList != null) {
            fragmentList.stream()
                    .filter(fragment -> fragment instanceof OnBackPressedListener)
                    .forEach(item -> ((OnBackPressedListener) item).onBackPressed());
        }
        super.onBackPressed();
    }
}
