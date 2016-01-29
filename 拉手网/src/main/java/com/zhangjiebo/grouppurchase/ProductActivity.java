package com.zhangjiebo.grouppurchase;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

public class ProductActivity extends Activity {

    private PullToRefreshScrollView mProduct_scrollView;
    private ImageView mProduct_img01;
    private LinearLayout mProduct_linear;
    private ScrollView mRefreshableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mProduct_scrollView = (PullToRefreshScrollView) findViewById(R.id.product_scrollView);
        mProduct_img01 = (ImageView) findViewById(R.id.product_img01);
        mProduct_linear = (LinearLayout) findViewById(R.id.product_linear);
        mRefreshableView = mProduct_scrollView.getRefreshableView();
//        mRefreshableView.setOnScrollChangeListener();
        mRefreshableView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    Log.e("mProduct_scrollView", "mProduct_scrollView" + mRefreshableView.getScrollY());
                    if (mRefreshableView.getScrollY() > mProduct_img01.getMeasuredHeight()) {
                        mProduct_linear.setVisibility(View.VISIBLE);
                    } else {
                        mProduct_linear.setVisibility(View.GONE);
                    }
//                }else(){}
                return false;
            }
        });
//        Log.e("mProduct_scrollView.getVerticalScrollbarPosition()", "mProduct_scrollView.getVerticalScrollbarPosition()");
    }

//    @Override
//    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//        Log.e("scrollY", "scrollY "+scrollY);
//        if (scrollY>mProduct_img01.getMeasuredHeight()){
//            mProduct_linear.setVisibility(View.VISIBLE);
//        }else{
//            mProduct_linear.setVisibility(View.GONE);
//
//        }
//    }
}
