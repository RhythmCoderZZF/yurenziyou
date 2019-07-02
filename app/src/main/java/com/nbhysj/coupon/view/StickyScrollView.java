package com.nbhysj.coupon.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.nbhysj.coupon.R;

import java.util.LinkedList;
import java.util.List;

public class StickyScrollView extends NestedScrollView {
    private static final String STICKY = "sticky";
    private View mCurrentStickyView;
    private Drawable mShadowDrawable;
    private List<View> mStickyViews;
    private int mStickyViewTopOffset;
    private int defaultShadowHeight = 0;
    private float density;
    private boolean redirectTouchToStickyView;
    private int slop;
    private int touch;
    private int lastScrollY;
    private OnScrollListener onScrollListener;
    private Runnable mInvalidataRunnable = new Runnable() {

        @Override
        public void run() {
            if (mCurrentStickyView != null) {
                int left = mCurrentStickyView.getLeft();
                int top = mCurrentStickyView.getTop();
                int right = mCurrentStickyView.getRight();
                int bottom = getScrollY() + (mCurrentStickyView.getHeight() + mStickyViewTopOffset);

                invalidate(left, top, right, bottom);
            }

            postDelayed(this, 16);


        }
    };


    public StickyScrollView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
        setSlop(context);
    }

    public StickyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mShadowDrawable = context.getResources().getDrawable(R.drawable.bg_rect_dark_gray_shape);
        mStickyViews = new LinkedList<View>();
        density = context.getResources().getDisplayMetrics().density;
        setSlop(context);
    }

    /**
     * �ҵ�����tag��View
     *
     * @param viewGroup
     */
    private void findViewByStickyTag(ViewGroup viewGroup) {
        int childCount = ((ViewGroup) viewGroup).getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = viewGroup.getChildAt(i);

            if (getStringTagForView(child).contains(STICKY)) {
                mStickyViews.add(child);
            }

            if (child instanceof ViewGroup) {
                findViewByStickyTag((ViewGroup) child);
            }
        }

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            findViewByStickyTag((ViewGroup) getChildAt(0));
        }
        showStickyView();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        showStickyView();
    }

    /**
     *
     */
    private void showStickyView() {
        View curStickyView = null;
        View nextStickyView = null;

        for (View v : mStickyViews) {
            int topOffset = v.getTop() - getScrollY();

            if (topOffset <= 0) {
                if (curStickyView == null || topOffset > curStickyView.getTop() - getScrollY()) {
                    curStickyView = v;
                }
            } else {
                if (nextStickyView == null || topOffset < nextStickyView.getTop() - getScrollY()) {
                    nextStickyView = v;
                }
            }
        }

        if (curStickyView != null) {
            mStickyViewTopOffset = nextStickyView == null ? 0 : Math.min(0, nextStickyView.getTop() - getScrollY() - curStickyView.getHeight());
            mCurrentStickyView = curStickyView;
            post(mInvalidataRunnable);
        } else {
            mCurrentStickyView = null;
            removeCallbacks(mInvalidataRunnable);

        }

    }


    private String getStringTagForView(View v) {
        Object tag = v.getTag();
        return String.valueOf(tag);
    }


    /**
     * ��sticky������
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mCurrentStickyView != null) {
            //�ȱ�������
            canvas.save();
            //������ԭ���ƶ���(0, getScrollY() + mStickyViewTopOffset)
            canvas.translate(0, getScrollY() + mStickyViewTopOffset);

            if (mShadowDrawable != null) {
                int left = 0;
                int top = mCurrentStickyView.getHeight() + mStickyViewTopOffset;
                int right = mCurrentStickyView.getWidth();
                int bottom = top + (int) (density * defaultShadowHeight + 0.5f);
                mShadowDrawable.setBounds(left, top, right, bottom);
                mShadowDrawable.draw(canvas);
            }


            canvas.clipRect(0, mStickyViewTopOffset, mCurrentStickyView.getWidth(), mCurrentStickyView.getHeight());

            mCurrentStickyView.draw(canvas);

            //��������ԭ�����
            canvas.restore();
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            redirectTouchToStickyView = true;
        }

        if (redirectTouchToStickyView) {
            redirectTouchToStickyView = mCurrentStickyView != null;

            if (redirectTouchToStickyView) {
                redirectTouchToStickyView = ev.getY() <= (mCurrentStickyView
                        .getHeight() + mStickyViewTopOffset)
                        && ev.getX() >= mCurrentStickyView.getLeft()
                        && ev.getX() <= mCurrentStickyView.getRight();
            }
        }

        if (redirectTouchToStickyView) {
            ev.offsetLocation(0, -1 * ((getScrollY() + mStickyViewTopOffset) - mCurrentStickyView.getTop()));
        }
        return super.dispatchTouchEvent(ev);
    }


    private boolean hasNotDoneActionDown = true;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (redirectTouchToStickyView) {
            ev.offsetLocation(0, ((getScrollY() + mStickyViewTopOffset) - mCurrentStickyView.getTop()));
        }

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            hasNotDoneActionDown = false;
        }

        if (hasNotDoneActionDown) {
            MotionEvent down = MotionEvent.obtain(ev);
            down.setAction(MotionEvent.ACTION_DOWN);
            super.onTouchEvent(down);
            hasNotDoneActionDown = false;
        }

        if (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_CANCEL) {
            hasNotDoneActionDown = true;
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (onScrollListener != null) {
                onScrollListener.onScroll(lastScrollY = this.getScrollY());
            }
        }
		/*if (ev.getAction() == MotionEvent.ACTION_UP) {
			handler.sendMessageDelayed(handler.obtainMessage(), 20);
		}*/
        return super.onTouchEvent(ev);
    }

    /**
     * 设置滚动接口
     *
     * @param onScrollListener
     */
    public void setScrolListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;

    }

    /**
     * 是否intercept当前的触摸事件
     *
     * @param ev 触摸事件
     * @return true：调用onMotionEvent()方法，并完成滑动操作
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //  保存当前touch的纵坐标值
                touch = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                //  滑动距离大于slop值时，返回true
                if (Math.abs((int) ev.getRawY() - touch) > slop) return true;
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }


    /**
     * 获取相应context的touch slop值（即在用户滑动之前，能够滑动的以像素为单位的距离）
     *
     * @param context ScrollView对应的context
     */
    private void setSlop(Context context) {
        slop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    /**
     * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中
     */
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int scrollY = StickyScrollView.this.getScrollY();
            if (onScrollListener != null) {
                onScrollListener.onScroll(scrollY);
            }
            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
            if (lastScrollY != scrollY) {
                lastScrollY = scrollY;
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
            }

        }
    };

    /**
     * 重写onTouchEvent， 当用户的手在MyScrollView上面的时候，
     * 直接将MyScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
     * MyScrollView可能还在滑动，所以当用户抬起手我们隔20毫秒给handler发送消息，在handler处理
     * MyScrollView滑动的距离
     */
	/*@Override
	public boolean onTouchEvent(MotionEvent ev) {

		return super.onTouchEvent(ev);
	}
*/
    public interface OnScrollListener {
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         *
         * @param scrollY 、
         */
        public void onScroll(int scrollY);
    }


}
