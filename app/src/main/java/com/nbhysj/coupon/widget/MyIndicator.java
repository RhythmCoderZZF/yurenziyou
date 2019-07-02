package com.nbhysj.coupon.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nbhysj.coupon.R;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MyIndicator extends HorizontalScrollView implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
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
    int newSelected;
    private boolean isIndicatorTabClick = true;

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
            oldSelected = mViewPager.getCurrentItem();
            newSelected = tabView.index;
            //if (mListener != null) mListener.onPageSelected(newSelected);
            setCurrentItem(newSelected, true);
        }
    };

    public MyIndicator(Context context) {
        super(context);
        init(context);
    }

    public MyIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);

    }

    private void init(Context context) {
        this.mContext = context;
        setHorizontalScrollBarEnabled(false);//隐藏自带的滚动条
        //添加linearLayout
        myLinearLayout = new LinearLayout(context);
        params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        myLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        params.setMargins(20, 0, 30, 0);
        addView(myLinearLayout, params);

        setMyOnPageChangeListener(mListener);
    }

    public void setViewPager(ViewPager viewPager) {
        setViewPager(viewPager, 0);
    }

    @SuppressWarnings("deprecation")
    public void setViewPager(ViewPager viewPager, int initPos) {
        if (mViewPager == viewPager) {
            return;
        }
        if (mViewPager != null) {
            mViewPager.setOnPageChangeListener(null);
        }
        final PagerAdapter adapter = viewPager.getAdapter();
        if (adapter == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        mViewPager = viewPager;
        viewPager.setOnPageChangeListener(this);
        notifyDataSetChanged();

    }

    public void notifyDataSetChanged() {
        myLinearLayout.removeAllViews();
        PagerAdapter mAdapter = mViewPager.getAdapter();
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            addTab(i, mAdapter.getPageTitle(i));
        }
        requestLayout();
        setCurrentItem(newSelected, true);
    }

    /**
     * 代码添加顶部的TextView
     *
     * @param index
     * @param text
     */
    private void addTab(int index, CharSequence text) {
        MyTabView tabView = new MyTabView(getContext());
        tabView.index = index;
        tabView.setFocusable(true);
        tabView.setOnClickListener(mTabClickListener);
        tabView.setText(text);
        tabView.setTextSize(13);
        tabView.setTextColor(getContext().getResources().getColor(R.color.black));
        tabView.setPadding(25, 8, 25, 8);
        tabView.setBackgroundResource(R.drawable.bg_recommend_tablayout_unselect);
        tabView.getBackground().setAlpha(80);
        params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        myLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        params.setMargins(20, 0, 20, 0);
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

    public void setCurrentItem(int item, boolean isIndicatorClick) {
        if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        int mSelectedTabIndex = item;
        if (isIndicatorClick) {
            mViewPager.setCurrentItem(mSelectedTabIndex);
        }
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

        setCurrentItem(position, false);
        if (mListener != null)
            mListener.onPageSelected(position);
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