package com.jiaotong.aiton.myapplication121403;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private float downY;// 按下时的点
    private float viewYdown;// 按下时View的位置
    private boolean lastSlidePull = false;// 最后一次滑动的方向
    private RelativeLayout mWoyaohua;
    private RelativeLayout mHehe_rela;
    private int mMHehe_relaMeasuredHeight;
    private ListView mListView;
    private RelativeLayout mTouch_rela;
    private int mTouch_relaHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setLisenter();
    }

    private void setLisenter() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0) {
                    //已滚动到最顶部
                    mTouch_rela.setVisibility(View.VISIBLE);
                } else {
                    mTouch_rela.setVisibility(View.GONE);

                }
            }
        });
        mTouch_rela.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float touchY = event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY = touchY;
                        viewYdown = mWoyaohua.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float offset = (event.getY() - downY);// 滑动距离
                        float posY = viewYdown + offset;// 计算可能的位置
                        if (mMHehe_relaMeasuredHeight < posY && posY < mTouch_relaHeight * 2 / 3 + mMHehe_relaMeasuredHeight) {

                            mWoyaohua.setY(posY);
                        }

                        if (offset > 0) {// pull to hide
                            lastSlidePull = true;

                        } else {// push to show
                            lastSlidePull = false;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        // 使用动画滑动到指定位置
                        if (lastSlidePull) {
                            mWoyaohua.setY(mTouch_relaHeight * 2 / 3 + mMHehe_relaMeasuredHeight);
                            mTouch_rela.setY(mTouch_relaHeight * 2 / 3 + mMHehe_relaMeasuredHeight);
//                    slideToHide();
                        } else {
//                    slideToShow();
                            mTouch_rela.setVisibility(View.GONE);
                            mWoyaohua.setY(0 + mMHehe_relaMeasuredHeight);
                            mTouch_rela.setY(0 + mMHehe_relaMeasuredHeight);
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new MyAdapter());
        mTouch_rela = (RelativeLayout) findViewById(R.id.touch_rela);
        mWoyaohua = (RelativeLayout) findViewById(R.id.woyaohua);
        mHehe_rela = (RelativeLayout) findViewById(R.id.hehe_rela);
        //测量标题的高度
        mHehe_rela.measure(0, 0);
        mMHehe_relaMeasuredHeight = mHehe_rela.getMeasuredHeight();
        //测量覆盖布局的高度
        ViewTreeObserver vto = mTouch_rela.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mTouch_rela.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mTouch_relaHeight = mTouch_rela.getHeight();
                //初始化布局的位置
                mWoyaohua.setY(mTouch_relaHeight * 2 / 3 + mMHehe_relaMeasuredHeight);
                mTouch_rela.setY(mTouch_relaHeight * 2 / 3 + mMHehe_relaMeasuredHeight);
            }
        });
    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflate = getLayoutInflater().inflate(R.layout.listview_item, null);
            TextView list_item_tv = (TextView) inflate.findViewById(R.id.list_item_tv);
            list_item_tv.setText(position + "");
            return inflate;
        }
    }

    /**
     * 使用ValueAnimator将rl_left以动画的形式弹入到界面
     *
     *
     * 需要滑动动画的自己去研究 我是看不懂了 看懂了说一声
     */

    private void slideToHide() {
        float startY = mWoyaohua.getY();
        ValueAnimator valueAnimator = ValueAnimator.ofInt((int) startY, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int offset = (Integer) animation.getAnimatedValue();
                mWoyaohua.setY(offset + mTouch_relaHeight / 2);
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
//        float fraction = Math.abs(startY / mScreenHeight/2);
        valueAnimator.setDuration(1000);
        valueAnimator.start();
    }

    /**
     * 使用ValueAnimator将rl_left以动画的形式弹出去
     */
    private void slideToShow() {
        float startY = mWoyaohua.getY();
        ValueAnimator valueAnimator = ValueAnimator.ofInt((int) startY, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int offset = (Integer) animation.getAnimatedValue();
                mWoyaohua.setY(offset + mMHehe_relaMeasuredHeight);
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        float fraction = Math.abs((mTouch_relaHeight + startY) * 1000 / mTouch_relaHeight);
        valueAnimator.setDuration(1000);
        valueAnimator.start();
    }
}
