package com.nbhysj.coupon.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @auther：hysj created on 2019/5/28
 * description：自定义viewPager
 */
public class ScrollViewPager extends ViewPager {
    float x, y;

    public ScrollViewPager(Context context) {
        super(context);
    }

    public ScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                return super.onInterceptHoverEvent(event);
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x - event.getX()) > Math.abs(y - event.getY()))
                    return true;
                else return false;
            case MotionEvent.ACTION_UP:
                return super.onInterceptHoverEvent(event);
        }
        return super.onInterceptHoverEvent(event);
    }
}