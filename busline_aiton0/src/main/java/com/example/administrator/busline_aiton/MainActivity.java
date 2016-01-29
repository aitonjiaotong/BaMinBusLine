package com.example.administrator.busline_aiton;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.busline_aition_fragment.CollectionFragment;
import com.example.administrator.busline_aition_fragment.MeFragment;
import com.example.administrator.busline_aition_fragment.NearbyFragment;
import com.example.administrator.busline_aition_fragment.TransferFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        View tab_menu_layout_nearby = getLayoutInflater().inflate(R.layout.tab_menu_layout_nearby, null);
        View tab_menu_layout_collection = getLayoutInflater().inflate(R.layout.tab_menu_layout_collection, null);
        View tab_menu_layout_transfer = getLayoutInflater().inflate(R.layout.tab_menu_layout_transfer, null);
        View tab_menu_layout_me = getLayoutInflater().inflate(R.layout.tab_menu_layout_me, null);

        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator(tab_menu_layout_nearby), NearbyFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator(tab_menu_layout_collection), CollectionFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("custom").setIndicator(tab_menu_layout_transfer), TransferFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("throttle").setIndicator(tab_menu_layout_me), MeFragment.class, null);

    }

}
