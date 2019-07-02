package com.nbhysj.coupon.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.nbhysj.coupon.R;

/**
 * @auther：hysj created on 2019/6/3
 * description：
 */
public class RoundCornersImageView extends AppCompatImageView {

    private Context mContext;
    //默认的边框的宽度
    private int mBorderThickness = 1;
    // 如果只有其中一个有值，则只画一个圆形边框
    private int mBorderOutsideColor = 0xffffff;
    private int mBorderInsideColor = 0xffffff;
    //默认颜色
    private final int defaultColor = 0x000000;
    // 控件默认长、宽
    private int defaultWidth = 0;
    private int defaultHeight = 0;
    //默认圆角
    private int roundWidth = 5;
    private int roundHeight = 5;
    //默认展示圆  为true展示圆角
    private boolean mIsRoundCorners = false;
    private Paint mPaint;
    private Paint mPaint2;

    public RoundCornersImageView(Context context) {
        super(context);
        mContext = context;
    }

    public RoundCornersImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setCustomAttributes(attrs);
    }

    public RoundCornersImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setCustomAttributes(attrs);
    }


    /**
     * 自定义属性
     *
     * @param attrs
     */
    private void setCustomAttributes(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs,
                R.styleable.roundCornersImageView);
        //圈的宽度
        mBorderThickness = a.getDimensionPixelSize(
                R.styleable.roundCornersImageView_border_thickness, 2);
        //获取外层圈颜色
        mBorderOutsideColor = a
                .getColor(R.styleable.roundCornersImageView_border_outside_color,
                        defaultColor);
        //获取内层圈颜色
        mBorderInsideColor = a.getColor(
                R.styleable.roundCornersImageView_border_inside_color, defaultColor);
        //获取圆角
        roundWidth = a.getDimensionPixelSize(R.styleable.roundCornersImageView_roundWidth, roundWidth);
        roundHeight = a.getDimensionPixelSize(R.styleable.roundCornersImageView_roundHeight, roundHeight);
        //设置是否展示圆角
        mIsRoundCorners = a.getBoolean(R.styleable.roundCornersImageView_is_round_corners, mIsRoundCorners);
        if (mIsRoundCorners) {
            mPaint = new Paint();
            mPaint.setColor(mContext.getResources().getColor(R.color.white));
            mPaint.setAntiAlias(true);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));//SrcATop、DstATop
            mPaint2 = new Paint();
            mPaint2.setXfermode(null);
        }
    }

    //绘制控件
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        //判断宽高
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        this.measure(0, 0);
        if (drawable.getClass() == NinePatchDrawable.class)
            return;

        if (drawable instanceof ColorDrawable)
            return;

        //获取bitmap
//        Bitmap bt = ((BitmapDrawable) drawable).getBitmap();
        //因为图标是没有实例的 BitmapDrawable，所以上面的不能获取bitmap
        Bitmap bt = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvasBit = new Canvas(bt);
        //如果需要展示圆角的话直接为左右上下各个角截取就可以
        if (mIsRoundCorners) {
            super.onDraw(canvasBit);
            drawLiftUp(canvasBit);
            drawRightUp(canvasBit);
            drawLiftDown(canvasBit);
            drawRightDown(canvasBit);
            canvas.drawBitmap(bt, 0, 0, mPaint2);
            bt.recycle();
        } else {
            drawable.setBounds(0, 0, canvasBit.getWidth(), canvasBit.getHeight());
            drawable.draw(canvasBit);
            Bitmap bitmap = bt.copy(Bitmap.Config.ARGB_8888, true);
            //赋值控件宽高
            if (defaultWidth == 0) {
                defaultWidth = getWidth();

            }
            if (defaultHeight == 0) {
                defaultHeight = getHeight();
            }
            int radius = 0;
            //判断两个边框的颜色
            // 定义画两个边框，分别为外圆边框和内圆边框 必须先画外框  不然内框会被覆盖
            if (mBorderInsideColor != defaultColor && mBorderOutsideColor != defaultColor) {
                radius = (defaultWidth < defaultHeight ? defaultWidth : defaultHeight) / 2 - 2 * mBorderThickness;
                // 画外圆 设置外框的宽度位置比内框多mBorderThickness的一半
                drawCircleBorder(canvas, radius + mBorderThickness, mBorderOutsideColor);
                // 画内圆
                drawCircleBorder(canvas, radius + mBorderThickness / 2, mBorderInsideColor);
            } else if (mBorderInsideColor != defaultColor && mBorderOutsideColor == defaultColor) {// 定义画一个边框
                radius = (defaultWidth < defaultHeight ? defaultWidth
                        : defaultHeight) / 2 - mBorderThickness;
                drawCircleBorder(canvas, radius + mBorderThickness / 2, mBorderInsideColor);
            } else if (mBorderInsideColor == defaultColor && mBorderOutsideColor != defaultColor) {// 定义画一个边框
                radius = (defaultWidth < defaultHeight ? defaultWidth
                        : defaultHeight) / 2 - mBorderThickness;
                drawCircleBorder(canvas, radius + mBorderThickness / 2, mBorderOutsideColor);
            } else {//没有边框
                radius = (defaultWidth < defaultHeight ? defaultWidth
                        : defaultHeight) / 2;
            }
            //获取剪切后的bitmap
            Bitmap croppedRoundBitmap = getCroppedRoundBitmap(bitmap, radius);
            canvas.drawBitmap(croppedRoundBitmap, defaultWidth / 2 - radius, defaultHeight / 2 - radius, null);
        }
    }

    /**
     * 剪切左上角
     *
     * @param canvas
     */
    private void drawLiftUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, roundHeight);
        path.lineTo(0, 0);
        path.lineTo(roundWidth, 0);
        path.arcTo(new RectF(0, 0, roundWidth * 2, roundHeight * 2), -90, -90);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    /**
     * 剪切左下角
     *
     * @param canvas
     */
    private void drawLiftDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, getHeight() - roundHeight);
        path.lineTo(0, getHeight());
        path.lineTo(roundWidth, getHeight());
        path.arcTo(new RectF(0, getHeight() - roundHeight * 2, 0 + roundWidth * 2, getHeight()), 90, 90);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    /**
     * 剪切右下角
     *
     * @param canvas
     */
    private void drawRightDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth() - roundWidth, getHeight());
        path.lineTo(getWidth(), getHeight());
        path.lineTo(getWidth(), getHeight() - roundHeight);
        path.arcTo(new RectF(getWidth() - roundWidth * 2, getHeight() - roundHeight * 2, getWidth(), getHeight()), 0, 90);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    /**
     * 剪切右上角
     *
     * @param canvas
     */
    private void drawRightUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth(), roundHeight);
        path.lineTo(getWidth(), 0);
        path.lineTo(getWidth() - roundWidth, 0);
        path.arcTo(new RectF(getWidth() - roundWidth * 2, 0, getWidth(), 0 + roundHeight * 2), -90, 90);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    /**
     * 边缘画圆
     */
    public void drawCircleBorder(Canvas canvas, int radius, int color) {
        Paint paint = new Paint();
        /* 去锯齿 */
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(color);
        /* 设置paint的外框宽度 */
//        paint.setStrokeWidth(10.0f);
        canvas.drawCircle(defaultWidth / 2, defaultHeight / 2, radius, paint);
    }

    /**
     * 获取裁剪后的圆形图片
     *
     * @param radius 半径
     */
    public Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {
        Bitmap scaledSrcBmp;
        int diameter = radius * 2;
        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        int squareWidth = 0, squareHeight = 0;
        int x = 0, y = 0;
        Bitmap squareBitmap;
        if (bmpHeight > bmpWidth) {// 高大于宽
            squareWidth = squareHeight = bmpWidth;
            x = 0;
            y = (bmpHeight - bmpWidth) / 2;
            // 截取正方形图片
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);
        } else if (bmpHeight < bmpWidth) {// 宽大于高
            squareWidth = squareHeight = bmpHeight;
            x = (bmpWidth - bmpHeight) / 2;
            y = 0;
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);
        } else {
            squareBitmap = bmp;
        }

        if (squareBitmap.getWidth() != diameter
                || squareBitmap.getHeight() != diameter) {
            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter,
                    diameter, true);

        } else {
            scaledSrcBmp = squareBitmap;
        }
        Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(scaledSrcBmp.getWidth() / 2,
                scaledSrcBmp.getHeight() / 2, scaledSrcBmp.getWidth() / 2,
                paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);
        bmp = null;
        squareBitmap = null;
        scaledSrcBmp = null;
        return output;
    }

}

