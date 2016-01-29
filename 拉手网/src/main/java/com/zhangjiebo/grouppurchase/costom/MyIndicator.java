package com.zhangjiebo.grouppurchase.costom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.zhangjiebo.grouppurchase.R;

public class MyIndicator extends View {

    private Paint mPaint;
    private Paint mPaint1;
    private int mRadius;
    private float mOffost;
    private int mCirclrNum;
    private int bgColor;
    private int fgColor;

    public MyIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyIndicator);
        mCirclrNum = typedArray.getInt(R.styleable.MyIndicator_IndicatorNum, 4);
        mRadius = typedArray.getInt(R.styleable.MyIndicator_IndicatorRadius, 30);
        bgColor = typedArray.getColor(R.styleable.MyIndicator_IndicatorBgColor, Color.RED);
        fgColor = typedArray.getColor(R.styleable.MyIndicator_IndicatorFgColor,Color.BLUE);
        initPaint();
    }
    public float getOffost() {
        return mOffost;
    }

    public void setOffost(int position,float offost) {
        position=position%mCirclrNum;
        if (position==mCirclrNum-1){
            offost=0;
        }
        mOffost=mRadius*3*position+offost*mRadius*3;
        invalidate();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(bgColor);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setColor(fgColor);
        mPaint1.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        for (int i = 0; i < mCirclrNum; i++) {

            canvas.drawCircle(mRadius+3+mRadius*3*i,mRadius+3, mRadius,mPaint);
        }
        canvas.drawCircle(mRadius+3+mOffost,mRadius+3,mRadius,mPaint1);
    }

}