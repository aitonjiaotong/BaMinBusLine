package com.jiaotong.aiton.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private RelativeLayout rlRoot;
    private ImageView ivMove;
    private int topTitleHeight;
    private TextView tvTips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        rlRoot.setOnTouchListener(this);
        ivMove = (ImageView) findViewById(R.id.iv_move);
        tvTips = (TextView) findViewById(R.id.tv_tips);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int[] locations = new int[2];
                tvTips.getLocationInWindow(locations);
                topTitleHeight = locations[1];
                break;
            case MotionEvent.ACTION_MOVE:
                moveViewByLayout(ivMove, (int) event.getRawX(),
                        (int) event.getRawY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    /**
     * 修改view的位置
     *
     * @param view
     * @param rawX
     * @param rawY
     */
    private void moveViewByLayout(View view, int rawX, int rawY) {
        int left = rawX - ivMove.getWidth() / 2;
        int top = rawY  -topTitleHeight- ivMove.getHeight() / 2;
        int width = left + view.getWidth();
        int height = top + view.getHeight();
        view.layout(left, top, width, height);
    }
}
