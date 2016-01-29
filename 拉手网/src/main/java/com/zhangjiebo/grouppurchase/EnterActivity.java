package com.zhangjiebo.grouppurchase;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangjiebo.grouppurchase.fragment.EnterFragment01;
import com.zhangjiebo.grouppurchase.fragment.EnterFragment02;

public class EnterActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mEnter_viewpager;
    private TextView mEnter_tv1;
    private TextView mEnter_tv2;
    private int mColors01;
    private int mColors02;
    private TextView mRegeister_enter;
    private ImageView mLashou_progress;


    private int offset = 0;// 动画图片偏移量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter);

        mEnter_viewpager = (ViewPager) findViewById(R.id.enter_viewpager);
        RelativeLayout quick_enter = (RelativeLayout) findViewById(R.id.quick_enter);
        RelativeLayout account_enter = (RelativeLayout) findViewById(R.id.account_enter);
        mRegeister_enter = (TextView) findViewById(R.id.regeister_enter);
        mEnter_tv1 = (TextView) findViewById(R.id.enter_tv1);
        mEnter_tv2 = (TextView) findViewById(R.id.enter_tv2);
        mColors01 = getResources().getColor(R.color.lashou_text);
        mColors02 = getResources().getColor(R.color.enter_text_color_unselect);
        mEnter_tv1.setTextColor(mColors01);
        mEnter_tv2.setTextColor(mColors02);
        quick_enter.setOnClickListener(this);
        account_enter.setOnClickListener(this);
        mRegeister_enter.setOnClickListener(this);
        mEnter_viewpager.setAdapter(new MyViewPager(getSupportFragmentManager()));
        InitImageView();
    }

    /**
     * 初始化动画
     */
    private void InitImageView() {
        mLashou_progress = (ImageView) findViewById(R.id.lashou_progress);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        Log.e("screenW", "InitImageView " + screenW);
        offset = screenW/ 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        mLashou_progress.setImageMatrix(matrix);// 设置动画初始位置
    }

    @Override
    public void onClick(View v) {

        Animation animation = null;
        switch (v.getId()){
            case R.id.quick_enter:
                mEnter_viewpager.setCurrentItem(0);
//                R.color.lashou_text
                mEnter_tv1.setTextColor(mColors01);
                mEnter_tv2.setTextColor(mColors02);
                animation = new TranslateAnimation(offset, 0, 0, 0);

                break;
            case R.id.account_enter:
                mEnter_viewpager.setCurrentItem(1);
                mEnter_tv2.setTextColor(mColors01);
                mEnter_tv1.setTextColor(mColors02);
                animation = new TranslateAnimation(0, offset, 0, 0);

                break;
            case R.id.regeister_enter:
                startActivity(new Intent(EnterActivity.this,RegisterActivity.class));
                break;
        }
        animation.setFillAfter(true);// True:图片停在动画结束位置
        animation.setDuration(300);
        mLashou_progress.startAnimation(animation);

    }

    class MyViewPager extends FragmentPagerAdapter {

        public MyViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            if (position==0){

                EnterFragment01 enterFragment01 = new EnterFragment01();
                return enterFragment01;
            }else if(position==1){

                EnterFragment02 enterFragment02 = new EnterFragment02();
                return enterFragment02;

            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
