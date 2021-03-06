
package com.nbhysj.coupon.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.BannerUrlBO;
import com.nbhysj.coupon.widget.glide.GlideApp;

/**
 * @author: hysj created on 2019/02/18
 * description: ViewPager实现的轮播图广告自定义视图，既支持自动轮播页面也支持手势滑动切换页面
 */
public class SlideShowView extends FrameLayout {
    /**
     * 线程更新
     **/
    protected static final int MSG_UPDATE_IMAGE = 1;
    /**
     * 延迟时间
     **/
    protected static final long MSG_DELAY = 3000;
    /**
     * 轮播图图片数量
     **/
    private int count;
    /**
     * 轮播图图片数量
     **/
    private int dotCount;
    /**
     * 自定义轮播图的资源
     **/
    private List<BannerUrlBO> bannerList;
    /**
     * 放轮播图片的ImageView 的list
     **/
    private List<ImageView> imageViewsList;
    /**
     * 放圆点的View的list
     **/
    private List<View> dotViewsList;
    /**
     * viewPager
     **/
    private ViewPager viewPager;
    /**
     * 当前轮播页
     **/
    private int currentItem = 0;
    /**
     * 上下文
     **/
    private Context context;

    /**
     * 倒计时
     **/
    private static Timer timer;
    /**
     * 倒计时任务
     **/
    private static TimerTask timerTask;
    /**
     * 是否重启倒计时
     **/
    boolean stat = false;
    /**
     * Handler
     **/
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    viewPager.setCurrentItem(currentItem);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * SlideShowView 构造器
     *
     * @param context
     */
    public SlideShowView(Context context) {
        this(context, null);
    }

    /**
     * SlideShowView 构造器
     *
     * @param context
     * @param attrs
     */
    public SlideShowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * SlideShowView 构造器
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initData();
    }

    /**
     * 初始化相关Data
     */
    private void initData() {
        imageViewsList = new ArrayList<ImageView>();
        dotViewsList = new ArrayList<View>();
    }

    /**
     * 开始Viewpaer 轮播
     */
    public void startViewPager() {
        timer = new Timer();
        timerTask = new TimerTask() {
            public void run() {
                handler.sendEmptyMessage(MSG_UPDATE_IMAGE);
            }
        };
        timer.schedule(timerTask, MSG_DELAY, MSG_DELAY);
    }

    /**
     * 销毁线程
     */
    public void destroyView() {
        if (timer != null) {
            timer.cancel();
        }
        if (timerTask != null) {
            timer.cancel();
        }
    }

    /**
     * 初始化Views等UI
     */
    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public void initUI(List<BannerUrlBO> list) {
        imageViewsList.clear();
        dotViewsList.clear();
        initData();
        this.bannerList = list;
        dotCount = list.size();
        count = list.size();
        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this,
                true);
        LinearLayout dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();
        for (int i = 0; i < count; i++) {
            BannerUrlBO banner = bannerList.get(i);
            ImageView view = new ImageView(context);
            // view.setTag(Constant.URLIMG + banner.getImage());
            //	if (i == 0)// 给一个默认图
            view.setScaleType(ScaleType.FIT_XY);
            imageViewsList.add(view);
        }

        for (int i = 0; i < dotCount; i++) {

            ImageView dotView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 4;
            params.rightMargin = 4;
            if (i == 0) {
                dotView.setBackgroundResource(R.mipmap.icon_point_select);
            } else {
                dotView.setBackgroundResource(R.mipmap.icon_point_unselect);
            }
            dotLayout.addView(dotView, params);
            dotViewsList.add(dotView);
        }


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setFocusable(true);
        viewPager.setAdapter(new MyPagerAdapter());
        //TODO
        if (list.size() != 3) {

            viewPager.setOffscreenPageLimit(2);
        }
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
        currentItem = Integer.MAX_VALUE % Integer.MAX_VALUE;
        viewPager.setCurrentItem(currentItem, false);
        if (count > 1) {
            //   startViewPager();
        }
    }

    /**
     * 填充ViewPager的页面适配器
     */
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(View container, int position, Object object) {
            //((ViewPager) container).removeView(imageViewsList.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {

		/*	position %= count;
            if (position < 0) {
				position = count + position;
			}*/
        /*	position %= count;
            final int pos = position;*/
            ImageView view = imageViewsList.get(position % count);
            // 如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
            ViewParent vp = view.getParent();
            if (vp != null) {
                ViewGroup parent = (ViewGroup) vp;
                parent.removeView(view);
            }
            ((ViewGroup) container).addView(view);

            GlideApp.with(context)
                    .load(bannerList.get(position % count).getUrl())
                    .placeholder(R.mipmap.icon_placeholder_image)
                    .error(R.mipmap.icon_placeholder_image)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .override(Target.SIZE_ORIGINAL)
                    .into(view);
            /*view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (bannerList.get(pos).getUrl() != null) {
						if(isOpenDrawer) {
							
						Intent intent = new Intent();
						intent.setAction("android.intent.action.VIEW");
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
						Uri content_url = Uri.parse(bannerList.get(pos).getUrl());
						intent.setData(content_url);
						context.startActivity(intent);
					  }
					}
				}
			});*/
            return view;
        }

        @Override
        public int getCount() {
            // return imageViewsList.size();
		/*	if(count > 1){
				return Integer.MAX_VALUE;
			} else if (count == 1){
				
				return 1;
			} else {
				
				return 0;
			}*/
            if (imageViewsList.size() < 1) {
                return 0;
            }
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }

    /**
     * ViewPager的监听器 当ViewPager中页面的状态发生改变时调用
     */
    private class MyPageChangeListener implements OnPageChangeListener {
        private int a;

        @Override
        public void onPageScrollStateChanged(int state) {
           /* if (count > 1) {
                if (state == 1) {

                  //  timer.cancel();

                    // timerTask.cancel();
                    stat = true;
                }
                if (state == 0) {
                    if (stat) {
                        startViewPager();
                    }
                    stat = false;
                }
            }*/
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int pos) {
            currentItem = pos;
            a = pos % dotCount;
            for (int i = 0; i < dotCount; i++) {
                if (i == a) {
                    ((View) dotViewsList.get(a))
                            .setBackgroundResource(R.mipmap.icon_point_select);
                } else {
                    ((View) dotViewsList.get(i))
                            .setBackgroundResource(R.mipmap.icon_point_unselect);
                }
            }
        }
    }

    /**
     * 销毁ImageView资源，回收内存
     */
    public void destoryBitmaps() {
        for (int i = 0; i < count; i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                drawable.setCallback(null);
            }
            imageView = null;
        }
    }
}