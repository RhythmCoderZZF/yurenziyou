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
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.R.dimen;
import com.nbhysj.coupon.adapter.MyBannerAdapter;
import com.nbhysj.coupon.adapter.ScenicSpotDetailBannerAdapter;
import com.nbhysj.coupon.model.response.BannerUrlBO;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @auther：hysj created on 2019/4/17
 * description：banner 控件
 */
public class MyBannerView extends FrameLayout {
    private static final int MSG_LOOP = 1000;
    private static long LOOP_INTERVAL = 5000L;
    private LinearLayout mLinearPosition = null;
    private ViewPager mViewPager = null;
    private MyBannerView.BannerHandler mBannerHandler = null;
    private BannerViewListener bannerViewListener;
    private Context mContext;
    private List<ImageView> mImageViewList;
    private DataSetObserver mDataObserver = new DataSetObserver() {
        public void onChanged() {
            super.onChanged();
            // ScenicSpotDetailBannerView.this.updateLinearPosition();
        }

        public void onInvalidated() {
            super.onInvalidated();
        }
    };

    public MyBannerView(Context context) {
        super(context);
        this.init();
    }

    public MyBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public void startLoop(boolean flag) {
        if (flag) {
            if (this.mBannerHandler == null) {
                this.mBannerHandler = new MyBannerView.BannerHandler(this);
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
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        this.mViewPager.setLayoutParams(layoutParams);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                //ScenicSpotDetailBannerView.this.updateLinearPosition();
                int curPos = mViewPager.getCurrentItem();
                int i = curPos % mImageViewList.size();
                int currentItem = i + 1;
                bannerViewListener.setBannerViewListener(currentItem);
            }

            public void onPageScrollStateChanged(int state) {
            }
        });
        /*this.mViewPager.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case 0:
                        if (ScenicSpotDetailBannerView.this.mBannerHandler != null && ScenicSpotDetailBannerView.this.mBannerHandler.hasMessages(1000)) {
                            ScenicSpotDetailBannerView.this.mBannerHandler.removeMessages(1000);
                        }
                        break;
                    case 1:
                        if (ScenicSpotDetailBannerView.this.mBannerHandler != null) {
                            ScenicSpotDetailBannerView.this.mBannerHandler.sendEmptyMessageDelayed(1000, ScenicSpotDetailBannerView.LOOP_INTERVAL);
                        }
                }

                return false;
            }
        });*/
    }

    private void initLinearPosition() {
        this.mLinearPosition = new LinearLayout(this.getContext());
        this.mLinearPosition.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.bottomMargin = this.getResources().getDimensionPixelSize(dimen.dimen_9dp);
        layoutParams.leftMargin = this.getResources().getDimensionPixelSize(dimen.dimen_9dp);
        layoutParams.gravity = Gravity.LEFT | Gravity.BOTTOM;
        this.mLinearPosition.setPadding(16, 0, 0, 16);
        this.mLinearPosition.setLayoutParams(layoutParams);
    }

    public void setAdapter(PagerAdapter adapter) {
        this.mViewPager.setAdapter(adapter);
        adapter.registerDataSetObserver(this.mDataObserver);
        //this.updateLinearPosition();
    }

    public void initBannerView(Context mContext, List<ImageView> imageViewList, List<String> bannerUrlList, BannerViewListener bannerViewListener) {
        mImageViewList = imageViewList;
        this.mContext = mContext;
        this.bannerViewListener = bannerViewListener;
        if (bannerUrlList != null && bannerUrlList.size() != 0) {
            MyBannerAdapter bannerAdapter = new MyBannerAdapter(mContext, mImageViewList, bannerUrlList);
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
        private WeakReference<MyBannerView> weakReference = null;

        public BannerHandler(MyBannerView bannerView) {
            super(Looper.getMainLooper());
            this.weakReference = new WeakReference(bannerView);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.weakReference != null) {
                MyBannerView bannerView = (MyBannerView) this.weakReference.get();
                if (bannerView != null && bannerView.mViewPager != null && bannerView.mViewPager.getAdapter() != null && bannerView.mViewPager.getAdapter().getCount() > 0) {
                    int curPos = bannerView.mViewPager.getCurrentItem();
                    curPos = (curPos + 1) % bannerView.mViewPager.getAdapter().getCount();
                    bannerView.mViewPager.setCurrentItem(curPos);
                    this.sendEmptyMessageDelayed(1000, MyBannerView.LOOP_INTERVAL);
                } else {
                    this.sendEmptyMessageDelayed(1000, MyBannerView.LOOP_INTERVAL);
                }
            }
        }
    }

    public interface BannerViewListener {

        void setBannerViewListener(int curPos);
    }
}

