package com.nbhysj.coupon.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @auther：hysj created on 2020/1/12
 * description：
 */
public class ReadScrollView extends ScrollView {
    private int mScrollY = 0;
    private boolean mClampedY = false;
    public ReadScrollView(Context context) {
        super(context);
    }

    public ReadScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReadScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 监听变化---一般用不到
     * @param scrollX
     * @param scrollY
     * @param clampedX
     * @param clampedY
     */
//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        super.onScrollChanged(l, t, oldl, oldt);
//        if (scrollChangedListener != null){
//            scrollChangedListener.onScrollChanged(l,t,oldl,oldt);
//        }
////        DebugUtil.d("l==" + l +"  t==" + t +"  oldl==" + oldl + "  oldt=="+oldt);
//    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        //scrollY=位置0和最底y坐标;clampedY=是否到顶部或者底部
        mScrollY = scrollY;
        mClampedY = clampedY;
//        DebugUtil.d("======l==onOverScrolled"+"   scrollY="+scrollY+"  clampedY="+clampedY );
    }

    /**
     * 刷新到顶部和底部
     */
    @Override
    public void stopNestedScroll() {
        super.stopNestedScroll();
        if (mClampedY){
             mClampedY = false;
            if (mScrollY == 0){
                scrollChangedListener.onScrollChangedTop();
            }else {
                scrollChangedListener.onScrollChangedBottom();
            }
        }
    }

    OnScrollChangedListener scrollChangedListener;

    public void setScrollChangedListener(OnScrollChangedListener scrollChangedListener) {
        this.scrollChangedListener = scrollChangedListener;
    }

    /**
     *
     */
    public interface OnScrollChangedListener{
        //到达顶部
        void onScrollChangedTop();
        //到达底部
        void onScrollChangedBottom();
        //监听变化
//        void onScrollChanged(int l,int t,int oldl,int oldt);
    }
}