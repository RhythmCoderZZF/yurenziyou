package com.nbhysj.coupon.widget.wavelineview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.Tools;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static com.umeng.commonsdk.statistics.AnalyticsConstants.LOG_TAG;


public class ExpandButtonLayout extends RelativeLayout implements Animation.AnimationListener {

    private RelativeLayout mRootView;
    private ImageView mImgAudioFrequency;
    private MyLinearLayout mLinearLayout;
    private TextView mTvSoundDuration;
    private WaveLineView mWaveLineView;
    AnimatorSet animatorSet;
    MyCountDownTimer mc;
    //音频时长
    private long mSoundDuringTime;
    private MediaPlayer mMediaPlayer = null;
    //音频文件
    private String mAudioFileUrl;
    public ExpandButtonLayout(Context context) {
        super(context);
        init(context, null);
    }

    public ExpandButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ExpandButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_button_expand, this, true);
        mRootView = (RelativeLayout) findViewById(R.id.rlyt_view_expand);
        mImgAudioFrequency = (ImageView) findViewById(R.id.img_audio_frequency);
        mLinearLayout = (MyLinearLayout) mRootView.findViewById(R.id.mLinearLayout);
        mTvSoundDuration = (TextView) findViewById(R.id.tv_sound_duration);
        mWaveLineView = (WaveLineView) findViewById(R.id.wave_line_view_audio_frequency);

        ViewTreeObserver vto = mLinearLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                allHeight = getHeight();
                mLinearLayoutWidth = mLinearLayout.getWidth();
                Log.e("DEBUG", "Width=" + mLinearLayoutWidth + ",allHeight=" + allHeight);
                savePaddingLeft = mLinearLayout.getPaddingLeft();
                savePaddingRight = mLinearLayout.getPaddingRight();
                ViewGroup.LayoutParams vglp = mLinearLayout.getLayoutParams();
                if (vglp instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginVglp = (ViewGroup.MarginLayoutParams) vglp;
                    saveMarginLeft = marginVglp.leftMargin;
                    saveMarginRight = marginVglp.rightMargin;
                    Log.e("DEBUG", "vglp saveMarginLeft=" + saveMarginLeft + " saveMarginRight=" + saveMarginRight);
                }
                mLinearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                initBackGround();
            }
        });

        mRootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                toggle();
            }
        });

        // timeCount.start();

      /*  CountDownTimer countDownTimer = new CountDownTimer(80000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) { // 计时过程显示

       *//*         SimpleDateFormat formatter = new SimpleDateFormat("m:ss");//初始化Formatter的转换格式。
                formatter.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
                String hms = formatter.format(millisUntilFinished);*//*
                int time = (int)millisUntilFinished / 1000;
                mTvSoundDuration.setText(String.valueOf(time));
            }

            @Override
            public void onFinish(long millisUntilFinished) { // 计时完毕时触发

                close();
            }
        };

        countDownTimer.start();*/
    }


    private int duration = 800;
    private boolean isExpand = false;
    private boolean isAnimation = false;
    private int savePaddingLeft = 0;
    private int savePaddingRight = 0;
    private int saveMarginLeft = 0;
    private int saveMarginRight = 0;
    private int mLinearLayoutWidth = 0;
    private int allHeight = 0;

    /**
     * 设置圆角背景
     */
    private void initBackGround() {
        int[] colors = {0xFF0DDDF6, 0xFF1DEB96};
        //GradientDrawable gd = new GradientDrawable();//创建drawable
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);
        //圆角半径等于高度的一半,合成之后是一个完整的圆
        drawable.setStroke(2, Color.WHITE);//设置宽度为10px的红色描边
        drawable.setCornerRadius(allHeight / 2f);
        drawable.getPadding(new Rect(0, 0, 0, 0));
        setBackground(drawable);
    }

    /**
     * 关闭暂停
     */
    public void close() {
        //timeCount.cancel();
        mWaveLineView.setVisibility(View.GONE);
        if(mc != null) {
            mc.cancel();
        }
        stopPlaying();
/*
        AnimationSet animationSet = new AnimationSet(true);

*/

        //  animatorSet.setDuration(duration);
     /*   RotateAnimation rotateAnimation = new RotateAnimation(360, 270,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(rotateAnimation);*/
       /* Animation scaleAnimation = new ScaleAnimation(1f, 1.25f, 1f, 1.25f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(scaleAnimation);
        imageView.startAnimation(animationSet);*/

        ObjectAnimator animator1 = ObjectAnimator.ofInt(mLinearLayout, "width", mLinearLayoutWidth + 150, mLinearLayoutWidth);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mTvSoundDuration, "translationX", 140f, 0);
      /*  ObjectAnimator animator2 = ObjectAnimator.ofInt(mLinearLayout, "paddingLeft", savePaddingLeft, savePaddingLeft + 30);
        ObjectAnimator animator3 = ObjectAnimator.ofInt(mLinearLayout, "paddingRight", savePaddingRight, savePaddingRight + 30);
        ObjectAnimator animator4 = ObjectAnimator.ofInt(mLinearLayout, "marginLeft", saveMarginLeft, saveMarginLeft + 30);
        ObjectAnimator animator5 = ObjectAnimator.ofInt(mLinearLayout, "marginRight", saveMarginRight, saveMarginRight + 30);*/
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, animator2);
        animatorSet.setDuration(duration).start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                System.out.print("onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                System.out.print("onAnimationEnd");
                mImgAudioFrequency.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


    }

    /**
     * 打开语音
     */
    public void open() {
        mc = new MyCountDownTimer(mSoundDuringTime * 1000, 1000);
        mc.start();
        mImgAudioFrequency.setVisibility(View.INVISIBLE);

    /*    RotateAnimation rotateAnimation = new RotateAnimation(270, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(rotateAnimation);*/
      /*  Animation scaleAnimation = new ScaleAnimation(1.25f, 1f, 1.25f, 1f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        animationSet.addAnimation(scaleAnimation);
        mTvSoundDuration.startAnimation(animationSet);*/

        //AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofInt(mLinearLayout, "width", mLinearLayoutWidth, mLinearLayoutWidth + 150);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mTvSoundDuration, "translationX", 0f, 140f);

     /*   ObjectAnimator animator2 = ObjectAnimator.ofInt(mLinearLayout, "paddingLeft", 0, savePaddingLeft);
        ObjectAnimator animator3 = ObjectAnimator.ofInt(mLinearLayout, "paddingRight", 0, savePaddingRight);
        ObjectAnimator animator4 = ObjectAnimator.ofInt(mLinearLayout, "marginLeft", 0, saveMarginLeft);
        ObjectAnimator animator5 = ObjectAnimator.ofInt(mLinearLayout, "marginRight", 0, saveMarginRight)*/
        ;
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1);
        animatorSet.playTogether(animator2);
        animatorSet.setDuration(duration).start();
        mWaveLineView.setVisibility(View.VISIBLE);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

                //  Toast.makeText()
                System.out.print("onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animator) {


                mWaveLineView.startAnim();
                System.out.print("onAnimationEnd");

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        getMediaPlayer(mAudioFileUrl);
    }

    public void toggle() {
        if (!isAnimation) {
            if (isExpand) {
                close();

                isExpand = false;
            } else {
                open();
                isExpand = true;
            }
        }
    }

    public void setAudioFileUrl(String audioFileUrl){

        if(!TextUtils.isEmpty(audioFileUrl))
        {
            mAudioFileUrl = audioFileUrl;
            mSoundDuringTime = Tools.getSoundDuring(mAudioFileUrl);
            mTvSoundDuration.setText(String.valueOf(mSoundDuringTime) +"″");
        }

    }
    @Override
    public void onAnimationStart(Animation animation) {
        isAnimation = true;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        isAnimation = false;
        // isExpand = !isExpand;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    /* 定义一个倒计时的内部类 */
 /*   abstract class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish(long millisUntilFinished) {// 计时完毕时触发
            // Toast.makeText(MainActivity.this,"结束",Toast.LENGTH_SHORT).show();
            close();
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            SimpleDateFormat formatter = new SimpleDateFormat("m:ss");//初始化Formatter的转换格式。
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
            String hms = formatter.format(millisUntilFinished);
            mTvSoundDuration.setText(hms);
        }
    }
*/

    public abstract class CountDownTimer {

        /**
         * Millis since epoch when alarm should stop.
         */
        private final long mMillisInFuture;

        /**
         * The interval in millis that the user receives callbacks
         */
        private final long mCountdownInterval;

        private long mMillisFinished = 0;

        private long mElapsedRealtime;

        /**
         * boolean representing if the timer was cancelled
         */
        private boolean mCancelled = false;

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish(long millisUntilFinished)}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CountDownTimer(long millisInFuture, long countDownInterval) {
            mMillisInFuture = millisInFuture;
            mCountdownInterval = countDownInterval;
        }

        /**
         * Cancel the countdown.
         */
        public synchronized final void cancel() {
            mCancelled = true;
            mHandler.removeMessages(MSG);
        }

        /**
         * Stop the countdown.
         */
        public synchronized final CountDownTimer stop() {
            mHandler.removeMessages(MSG);

            long elapsedRealtime = mElapsedRealtime;
            mElapsedRealtime = SystemClock.elapsedRealtime();
            final long millis = mElapsedRealtime - elapsedRealtime;
            mMillisFinished += millis;

            onTick(mMillisFinished);
            return this;
        }

        /**
         * Start the countdown.
         */
        public synchronized final CountDownTimer start() {

            mCancelled = false;
            if (mMillisInFuture <= mMillisFinished) {
                onFinish(mMillisFinished);
                return this;
            }
            mElapsedRealtime = SystemClock.elapsedRealtime();
            mHandler.sendMessage(mHandler.obtainMessage(MSG));
            return this;
        }

        /**
         * 获得当前的时间long.
         */
        public synchronized final long getNowTime() {
            return mMillisFinished + SystemClock.elapsedRealtime() - mElapsedRealtime;
        }

        /**
         * Callback fired on regular interval.
         *
         * @param millisUntilFinished The amount of time until finished.
         */
        public abstract void onTick(long millisUntilFinished);

        /**
         * Callback fired when the time is up.
         */
        public abstract void onFinish(long millisUntilFinished);


        private static final int MSG = 1;


        // handles counting down
        private Handler mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                synchronized (CountDownTimer.this) {
                    if (mCancelled) {
                        return;
                    }
                    long elapsedRealtime = mElapsedRealtime;
                    mElapsedRealtime = SystemClock.elapsedRealtime();
                    final long millis = mElapsedRealtime - elapsedRealtime;
                    mMillisFinished += millis;

                    if (mMillisInFuture <= mMillisFinished) {
                        onFinish(mMillisFinished);
                    } else {
                        onTick(mMillisFinished);

                        long delay = mMillisInFuture - mMillisFinished;
                        if (delay > mCountdownInterval) {

                            // take into account user's onTick taking time to execute
                            delay = mElapsedRealtime + mCountdownInterval - SystemClock.elapsedRealtime() - mMillisFinished % mCountdownInterval;

                        }

                        // special case: user's onTick took more than interval to
                        // complete, skip to next interval
                        while (delay < 0)
                            delay += mCountdownInterval;

                        sendMessageDelayed(obtainMessage(MSG), delay);
                    }
                }
            }
        };
    }


    /**
     * 继承 CountDownTimer 防范
     *
     * 重写 父类的方法 onTick() 、 onFinish()
     */

    class MyCountDownTimer extends CountDownTimer {
        /**
         *
         * @param millisInFuture
         *            表示以毫秒为单位 倒计时的总数
         *
         *            例如 millisInFuture=1000 表示1秒
         *
         * @param countDownInterval
         *            表示 间隔 多少微秒 调用一次 onTick 方法
         *
         *            例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         *
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish(long millisUntilFinished) {
           /* SimpleDateFormat formatter = new SimpleDateFormat("s");//初始化Formatter的转换格式。
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
            String hms = formatter.format(millisUntilFinished);
            mTvSoundDuration.setText(hms);*/
            isExpand = false;
            close();
            mTvSoundDuration.setText(String.valueOf(mSoundDuringTime) +"″");
            stopPlaying();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.i("MainActivity", millisUntilFinished + "");
            int time = (int)millisUntilFinished / 1000;
            int remaindTime = (int)mSoundDuringTime - time;
            mTvSoundDuration.setText(String.valueOf(remaindTime) +"s");
        }
    }


    public void getMediaPlayer(String audioFileUrl) {
        mMediaPlayer = new MediaPlayer();

        try {
            mMediaPlayer.setDataSource(audioFileUrl);
            mMediaPlayer.prepare();

            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                }
            });

        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlaying();
            }
        });
    }


    public void stopPlaying() {

        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;

        }
    }

}
