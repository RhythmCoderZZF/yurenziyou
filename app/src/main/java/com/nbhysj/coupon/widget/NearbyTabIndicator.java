package com.nbhysj.coupon.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.HomePageResponse;

import java.util.List;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NearbyTabIndicator extends HorizontalScrollView implements ViewPager.OnPageChangeListener {
    private LinearLayout myLinearLayout;
    private LinearLayout.LayoutParams params;
    /**
     * 我们实现了ViewPager.OnPageChangeListener接口，因为我们要监听ViewPager的滑页行为，从而去改变导航栏的状态
     * 所以我们也可以看到，MyIndicator持有ViewPager的引用，如果我们还想要监听ViewPager怎么办呢？
     * 这里我为MyIndicator提供了一个接口，与OnPageChangeListener方法一样调用
     **/

    MyOnPageChangeListener mListener;
    MyTabView tabView;
    private Context mContext;

    public interface MyOnPageChangeListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }

    private int oldSelected;

    //为了让TextView能点击，我为每个TextView都设置了OnClickListener,
    //就是获得目标标题栏的index，然后调用setCurrentItem()就可以了,这样就实现了点击滑动的效果，点击标题栏，Viewpager也会跟着翻页。
    private final OnClickListener mTabClickListener = new OnClickListener() {
        public void onClick(View view) {
            tabView = (MyTabView) view;
            final int newSelected = tabView.index;
            if (mListener != null) mListener.onPageSelected(newSelected);
            setCurrentItem(newSelected);
        }
    };

    public NearbyTabIndicator(Context context) {
        super(context);
        init(context);
    }

    public NearbyTabIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NearbyTabIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        setHorizontalScrollBarEnabled(false);//隐藏自带的滚动条
        //添加linearLayout
        myLinearLayout = new LinearLayout(mContext);
        params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        myLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        params.setMargins(0, 0, 0, 0);
        addView(myLinearLayout, params);
    }

    public void initTab(List<HomePageResponse.SmallTagEntity> labelList, int textSize) {
        // myLinearLayout.removeAllViews();
        // int count = mAdapter.getCount();
        int labelNum = labelList.size();
        for (int i = 0; i < labelNum; i++) {
            addTab(i, labelList.get(i).getTitle(), textSize);
        }
        requestLayout();
    }

    public void setmTabSelector(int position) {

        setCurrentItem(position);
    }

    public void setCurrentItem(int item) {

        final int tabCount = myLinearLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {//遍历标题，改变选中的背景
            final View child = myLinearLayout.getChildAt(i);
            MyTabView view = (MyTabView) myLinearLayout.getChildAt(i);
            final boolean isSelected = (i == item);
            child.setSelected(isSelected);
            if (isSelected) {
                //     animation(child);
                animateToTab(item);//动画效果

                child.setBackgroundResource(R.drawable.bg_recommend_tablayout_select);
                view.setTextColor(getContext().getResources().getColor(R.color.white));
                    /*if(tabView != null)
                    {
                        tabView.setTextColor(getContext().getResources().getColor(R.color.white));
                    }*/
            } else {
                //animation2(child);
                child.setBackgroundResource(R.drawable.bg_recommend_tablayout_unselect);
                child.getBackground().setAlpha(80);
                view.setTextColor(getContext().getResources().getColor(R.color.black));
                   /* if(tabView != null) {
                        tabView.setTextColor(getContext().getResources().getColor(R.color.black));
                    }*/
            }
        }
    }

    /**
     * 代码添加顶部的TextView
     *
     * @param index
     * @param text
     */
    private void addTab(int index, CharSequence text, int textSize) {
        MyTabView tabView = new MyTabView(getContext());
        tabView.index = index;
        tabView.setFocusable(true);
        tabView.setOnClickListener(mTabClickListener);
        tabView.setText(text);
        tabView.setTextSize(textSize);
        tabView.setTextColor(getContext().getResources().getColor(R.color.txt_font_black2));
        tabView.setPadding(dp2px(20), dp2px(6), dp2px(20), dp2px(6));
        tabView.setBackgroundResource(R.drawable.bg_recommend_tablayout_unselect);
        tabView.getBackground().setAlpha(80);
        params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        myLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        params.setMargins(dp2px(7), 0, 0, 0);
        myLinearLayout.addView(tabView, params);
        myLinearLayout.setBackgroundColor(getContext().getResources().getColor(R.color.white));
    }

    /**
     * 被选中的动画
     *
     * @param view
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("NewApi")
    private void animation(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f);
        ObjectAnimator fade = ObjectAnimator.ofFloat(view, "alpha", 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(scaleX).with(scaleY).with(fade);
        animSet.setDuration(500);
        animSet.start();
    }

    /**
     * 没选中的动画
     *
     * @param view
     */
    private void animation2(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.8f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.8f);
        ObjectAnimator fade = ObjectAnimator.ofFloat(view, "alpha", 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(scaleX).with(scaleY).with(fade);
        animSet.setDuration(500);
        animSet.start();
    }


    private Runnable mTabSelector;

    private void animateToTab(final int position) {
        final View tabView = myLinearLayout.getChildAt(position);
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
        mTabSelector = new Runnable() {
            public void run() {
                final int scrollPos = tabView.getLeft() - (getWidth() - tabView.getWidth()) / 2;//计算要滑动到的位置
                smoothScrollTo(scrollPos, 0);
                mTabSelector = null;
            }
        };
        post(mTabSelector);    //在主线程执行动画
    }

    public void setMyOnPageChangeListener(MyOnPageChangeListener listener) {
        mListener = listener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mListener != null)
            mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        if (mListener != null) mListener.onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mListener != null) mListener.onPageScrollStateChanged(state);
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}