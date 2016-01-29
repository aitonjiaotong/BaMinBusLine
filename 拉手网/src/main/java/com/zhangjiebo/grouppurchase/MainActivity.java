package com.zhangjiebo.grouppurchase;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.zhangjiebo.grouppurchase.fragment.Fragment01;
import com.zhangjiebo.grouppurchase.fragment.Fragment02;
import com.zhangjiebo.grouppurchase.fragment.Fragment03;
import com.zhangjiebo.grouppurchase.fragment.Fragment04;

public class MainActivity extends FragmentActivity {




    private int[] imgRes = new int[]{
            R.drawable.ic_tab_artists_selector,
            R.drawable.ic_tab_albums_selector,
            R.drawable.ic_tab_songs_selector,
            R.drawable.ic_tab_playlists_selector
    };
    private String[] tabsItem = new String[]{
            "首页",
            "周边",
            "我的拉手",
            "更多"
    };
    private Class[] fragment = new Class[]{
            Fragment01.class,
            Fragment02.class,
            Fragment03.class,
            Fragment04.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTabHost tabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtab);
        for (int i = 0; i <imgRes.length; i++) {
            View inflate = getLayoutInflater().inflate(R.layout.tabs_item, null);
            ImageView tabs_img = (ImageView) inflate.findViewById(R.id.tabs_img);
            TextView tabs_text = (TextView) inflate.findViewById(R.id.tabs_text);
            tabs_img.setImageResource(imgRes[i]);
            tabs_text.setText(tabsItem[i]);
            tabHost.addTab(tabHost.newTabSpec(tabsItem[i]).setIndicator(inflate), fragment[i],null);
        }


    }


}
