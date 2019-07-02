package com.nbhysj.coupon.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.nbhysj.coupon.R;

/**
 * @auther：hysj created on 2019/2/24
 * description：我的页面背景
 */
public class TestView extends View {

    private int heigth;
    private int width;
    private Paint mPaint;
    private Point startPoint;
    private Point endPoint;
    private Point assistPoint;

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        heigth = dm.heightPixels;
        width = dm.widthPixels;

        mPaint.setStrokeWidth(3.f);
        mPaint.setAntiAlias(true);
        mPaint.setColor(R.drawable.bg_recommend_tablayout_select);
        mPaint.setStyle(Paint.Style.FILL);
        //canvas.drawCircle(width/2,heigth/2,100,paint);*/

    /*    float x = getWidth() / 8;
        float y = getHeight() / 4;

        RectF oval = new RectF( x, y,
                getWidth() - x, getHeight() - y);

       // canvas.drawArc(oval,-90,120,false,mPaint);
        canvas.drawRect(oval,mPaint);*/

        Path mPath = new Path();

        mPaint.setColor(getResources().getColor(R.color.color_blue));
        mPaint.setStrokeWidth(6);//设置画笔的粗细

        startPoint = new Point(0, heigth / 3 + 100);//设置起点（可以自定义）
        endPoint = new Point(width, heigth / 3 + 100);//设置终点（可以自己定）
        assistPoint = new Point(width / 2, heigth / 3 + heigth / 7 + 100);//设置中间辅助切线点
        mPaint.setAntiAlias(true);// 抗锯齿
        mPaint.setDither(true);// 防抖动,

        //画笔重置
        mPath.reset();
        // 起点
        mPath.moveTo(startPoint.x, startPoint.y);
        // 开始画贝塞尔曲线
        mPath.quadTo(assistPoint.x, assistPoint.y, endPoint.x, endPoint.y);
        //mPath.cubicTo(0,0,assistPoint.x, assistPoint.y, endPoint.x, endPoint.y);
        // 画路径
        /**
         * 多色渐变
         */

        int colorStart = getResources().getColor(R.color.color_blue);
        int colorEnd = getResources().getColor(R.color.color_green);
        LinearGradient backGradient = null;
        if (1 == 0) {
            backGradient = new LinearGradient(0, 0, 0, heigth / 3 + 100, new int[]{colorStart, colorEnd}, null, Shader.TileMode.CLAMP);
        } else {
            backGradient = new LinearGradient(0, 0, width, heigth / 3 + 100, colorStart, colorEnd, Shader.TileMode.CLAMP);
        }
        mPaint.setShader(backGradient);
        canvas.drawPath(mPath, mPaint);
        RectF rectF = new RectF(0, 0, width, heigth / 3 + 100);

        canvas.drawRect(rectF, mPaint);
    }
}
