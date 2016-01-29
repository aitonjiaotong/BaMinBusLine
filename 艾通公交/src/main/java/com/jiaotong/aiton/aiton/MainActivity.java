package com.jiaotong.aiton.aiton;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiaotong.aiton.aiton.fragment.FragmentCollection;
import com.jiaotong.aiton.aiton.fragment.FragmentMe;
import com.jiaotong.aiton.aiton.fragment.FragmentNearby;
import com.jiaotong.aiton.aiton.fragment.FragmentTransfer;

public class MainActivity extends FragmentActivity {

    private int[] imgRes = new int[]{
            R.mipmap.icon_tab_nearby,
            R.mipmap.icon_tab_collection,
            R.mipmap.icon_tab_transfer,
            R.mipmap.icon_tab_me,
    };
    private String[] tabsItem = new String[]{
            "附近",
            "常用",
            "路线",
            "我"
    };
    private Class[] fragment = new Class[]{
            FragmentNearby.class,
            FragmentCollection.class,
            FragmentTransfer.class,
            FragmentMe.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();



    }

    private void initUI() {
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
