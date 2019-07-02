package com.nbhysj.coupon.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.nbhysj.coupon.R;

/**
 * @auther：hysj created on 2019/02/24
 * description：我的背景
 */
public class MyView extends View {
    private Paint mPaint;
    private Path mPath;
    private Point startPoint;
    private Point endPoint;
    private Point assistPoint;
    private int screenWidth;
    private int screenHeigh;

    public MyView(Context context) {
        super(context);

    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取屏幕的宽高
        getScreenArgs();
        initContext();
    }

    private void getScreenArgs() {

        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenHeigh = dm.heightPixels;
        screenWidth = dm.widthPixels;
    }

    private void initContext() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setStrokeWidth(6);//设置画笔的粗细

        startPoint = new Point(0, 0);//设置起点（可以自定义）
        endPoint = new Point(screenWidth, 0);//设置终点（可以自己定）
        assistPoint = new Point(screenWidth / 2, screenHeigh / 10);//设置中间辅助切线点
        mPaint.setAntiAlias(true);// 抗锯齿
        mPaint.setDither(true);// 防抖动,更清晰
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画笔重置
        mPath.reset();
        // 起点
        mPath.moveTo(startPoint.x, startPoint.y);
        // 开始画贝塞尔曲线
        mPath.quadTo(assistPoint.x, assistPoint.y, endPoint.x, endPoint.y);
        // 画路径
        canvas.drawPath(mPath, mPaint);
    }
}
