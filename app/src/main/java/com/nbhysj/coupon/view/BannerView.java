package com.nbhysj.coupon.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.BannerAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import com.nbhysj.coupon.R.dimen;
import com.nbhysj.coupon.model.response.BannerUrlBO;

/**
 * @auther：hysj created on 2019/4/17
 * description：banner 控件
 */
public class BannerView extends FrameLayout {
    private static final int MSG_LOOP = 1000;
    private static long LOOP_INTERVAL = 5000L;
    private LinearLayout mLinearPosition = null;
    private ViewPager mViewPager = null;
    private BannerView.BannerHandler mBannerHandler = null;
    private List<ImageView> viewList;
    private int viewSize;
    private Context mContext;
    private DataSetObserver mDataObserver = new DataSetObserver() {
        public void onChanged() {
            super.onChanged();
            BannerView.this.updateLinearPosition();
        }

        public void onInvalidated() {
            super.onInvalidated();
        }
    };

    public BannerView(Context context) {
        super(context);
        this.init();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public void startLoop(boolean flag) {
        if (flag) {
            if (this.mBannerHandler == null) {
                this.mBannerHandler = new BannerView.BannerHandler(this);
            }

            this.mBannerHandler.sendEmptyMessageDelayed(1000, LOOP_INTERVAL);
        } else if (this.mBannerHandler != null && this.mBannerHandler.hasMessages(1000)) {
            this.mBannerHandler.removeMessages(1000);
        }

    }

    private void init() {
        this.initViewPager();
        this.initLinearPosition();
        this.addView(this.mViewPager);
        this.addView(this.mLinearPosition);
    }

    private void initViewPager() {
        this.mViewPager = new ViewPager(this.getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        this.mViewPager.setLayoutParams(layoutParams);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                BannerView.this.updateLinearPosition();
            }

            public void onPageScrollStateChanged(int state) {
            }
        });
        this.mViewPager.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case 0:
                        if (BannerView.this.mBannerHandler != null && BannerView.this.mBannerHandler.hasMessages(1000)) {
                            BannerView.this.mBannerHandler.removeMessages(1000);
                        }
                        break;
                    case 1:
                        if (BannerView.this.mBannerHandler != null) {
                            BannerView.this.mBannerHandler.sendEmptyMessageDelayed(1000, BannerView.LOOP_INTERVAL);
                        }
                }

                return false;
            }
        });
        //this.mViewPager.setScroll(true);
    }

    private void initLinearPosition() {
        this.mLinearPosition = new LinearLayout(this.getContext());
        this.mLinearPosition.setOrientation(LinearLayout.HORIZONTAL);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.bottomMargin = this.getResources().getDimensionPixelSize(dimen.dimen_9dp);
        layoutParams.leftMargin = this.getResources().getDimensionPixelSize(dimen.dimen_9dp);
        layoutParams.gravity = Gravity.LEFT | Gravity.BOTTOM;
        this.mLinearPosition.setPadding(16, 0, 0, 16);
        this.mLinearPosition.setLayoutParams(layoutParams);
    }

    public void setAdapter(PagerAdapter adapter) {
        this.mViewPager.setAdapter(adapter);
        adapter.registerDataSetObserver(this.mDataObserver);
        this.updateLinearPosition();
    }

    private void updateLinearPosition() {
        if (this.viewList != null && this.viewList.size() != 0) {
            int curPos;
            if (this.mLinearPosition.getChildCount() != this.viewSize) {
                curPos = this.mLinearPosition.getChildCount() - this.viewSize;
                boolean needAdd = curPos < 0;
                curPos = Math.abs(curPos);

                for (int i = 0; i < curPos; ++i) {
                    if (needAdd) {
                        ImageView img = new ImageView(this.getContext());
                        android.widget.LinearLayout.LayoutParams layoutParams = new android.widget.LinearLayout.LayoutParams(-2, -2);
                        layoutParams.rightMargin = this.getResources().getDimensionPixelOffset(dimen.dp_5);
                        img.setLayoutParams(layoutParams);
                        img.setBackgroundResource(R.mipmap.icon_banner_indicator_unselect);
                        this.mLinearPosition.addView(img);
                    } else {
                        this.mLinearPosition.removeViewAt(0);
                    }
                }
            }

            curPos = this.mViewPager.getCurrentItem();

            for (int i = 0; i < this.mLinearPosition.getChildCount(); ++i) {
                if (i == curPos % this.viewSize) {
                    this.mLinearPosition.getChildAt(i).setBackgroundResource(R.mipmap.icon_banner_indicator_select);
                } else {
                    this.mLinearPosition.getChildAt(i).setBackgroundResource(R.mipmap.icon_banner_indicator_unselect);
                }
            }
        }

    }

    public void setViewList(Context mContext, List<String> bannerUrlList) {
        if (viewList == null) {
            viewList = new ArrayList<ImageView>();
        } else {
            this.viewList.clear();
        }
        if (bannerUrlList.size() > 0) {

            for (int i = 0; i < bannerUrlList.size(); i++) {
                ImageView image = new ImageView(mContext);
                image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                //设置显示格式
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                viewList.add(image);
            }
        }
        this.mContext = mContext;
        if (viewList != null && viewList.size() != 0) {
            this.viewSize = viewList.size();
            BannerAdapter bannerAdapter = new BannerAdapter(mContext, viewList, bannerUrlList);
            this.setAdapter(bannerAdapter);
        }
    }

    public void setTransformAnim(boolean flag) {
        if (flag) {
            this.mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
                private static final float MIN_SCALE = 0.75F;

                public void transformPage(View view, float position) {
                    int pageWidth = view.getWidth();
                    if (position < -1.0F) {
                        view.setRotation(0.0F);
                    } else if (position <= 1.0F) {
                        float mRot;
                        if (position < 0.0F) {
                            mRot = 20.0F * position;
                            view.setPivotX((float) view.getMeasuredWidth() * 0.5F);
                            view.setPivotY((float) view.getMeasuredHeight());
                            view.setRotation(mRot);
                        } else {
                            mRot = 20.0F * position;
                            view.setPivotX((float) view.getMeasuredWidth() * 0.5F);
                            view.setPivotY((float) view.getMeasuredHeight());
                            view.setRotation(mRot);
                        }
                    } else {
                        view.setRotation(0.0F);
                    }

                }
            });
        }

    }

    public static void setLoopInterval(long loopInterval) {
        LOOP_INTERVAL = loopInterval;
    }

    private static class BannerHandler extends Handler {
        private WeakReference<BannerView> weakReference = null;

        public BannerHandler(BannerView bannerView) {
            super(Looper.getMainLooper());
            this.weakReference = new WeakReference(bannerView);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.weakReference != null) {
                BannerView bannerView = (BannerView) this.weakReference.get();
                if (bannerView != null && bannerView.mViewPager != null && bannerView.mViewPager.getAdapter() != null && bannerView.mViewPager.getAdapter().getCount() > 0) {
                    int curPos = bannerView.mViewPager.getCurrentItem();
                    curPos = (curPos + 1) % bannerView.mViewPager.getAdapter().getCount();
                    bannerView.mViewPager.setCurrentItem(curPos);
                    this.sendEmptyMessageDelayed(1000, BannerView.LOOP_INTERVAL);
                } else {
                    this.sendEmptyMessageDelayed(1000, BannerView.LOOP_INTERVAL);
                }
            }
        }
    }
}

