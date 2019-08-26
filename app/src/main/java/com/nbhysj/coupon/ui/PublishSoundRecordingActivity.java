package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.library.banner.BannerLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.PublishPictureSoundRecordAdapter;
import com.nbhysj.coupon.oss.audio.AudioRecorder;
import com.nbhysj.coupon.oss.audio.FileUtils;
import com.nbhysj.coupon.service.RecordingService;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.EncryptedSignatureUtil;
import com.nbhysj.coupon.widget.wavelineview.WaveLineView;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/02
 * description：发布图片录音(声音胶囊)
 */
public class PublishSoundRecordingActivity extends BaseActivity {
    //需要发布的图片
    @BindView(R.id.banner_publish_picture)
    BannerLayout mBannerLayout;
    //声音播放进度
    @BindView(R.id.rlyt_sound_play_speed_progress)
    RelativeLayout mRlytSoundPlaySpeedProgress;
    //录音按钮
    @BindView(R.id.ibtn_sound_recording)
    ImageButton mImgSoundRecording;
    //录音提示
    @BindView(R.id.tv_sound_recording_tips)
    TextView mTvSoundRecordingTips;
    //音频录入||播放 进度
    @BindView(R.id.progress_bar_parent_audio)
    ProgressBar mProgressAudioParentBar;
    //音频播放按钮
    @BindView(R.id.ibtn_sound_media_play)
    ImageButton mIbtnSoundMediaPlay;
    //删除音频按钮
    @BindView(R.id.ibtn_delete_sound_recording)
    ImageButton mIbtnDeleteSoundRecording;
    //帖子完成
    @BindView(R.id.rlyt_publish_note)
    RelativeLayout mRlytPublishNote;
    //音频时长
    @BindView(R.id.tv_audio_duration)
    TextView mTvAudioDuration;

    @BindView(R.id.wave_line_view_audio_frequency)
    WaveLineView mWaveLineViewAudioFrequency;

    private MediaPlayer mediaPlayer;

    AudioRecorder audioRecorder;
    StringBuffer stringBuffer = new StringBuffer();
    private int delaytime = 60;
    private int TIME_COUNT_CODE_MSG = 0;
    private Timer mTimer;
    //Handler handler;
    private List<String> publishPictureUrlList;
    private String mAudiofileName;
    private boolean isAudioDelete = false;
    private TimerTask mTimerTask = null;
    private Handler mHandler = null;
    private static long count = 1;
    private boolean isPause = false;
    private static int delay = 1000;  //1s
    private static int period = 1000;  //1s

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_publish_sound_recording;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

  /*      ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }*/

        publishPictureUrlList = getIntent().getStringArrayListExtra("publishPictureUrlList");
        PublishPictureSoundRecordAdapter soundRecordAdapter = new PublishPictureSoundRecordAdapter(PublishSoundRecordingActivity.this);
        soundRecordAdapter.setPublishPictureUrlList(publishPictureUrlList);
        mBannerLayout.setAdapter(soundRecordAdapter);

        mRlytSoundPlaySpeedProgress.getBackground().setAlpha(80);

        audioRecorder = AudioRecorder.getInstance();
    }

    @Override
    public void initData() {

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == TIME_COUNT_CODE_MSG) {
                    int i = 1000;
                    long time = count * i;
                    CharSequence sysTimeStr = DateFormat.format("mm:ss", time);
                    mTvAudioDuration.setText(String.valueOf(sysTimeStr + "/" + "00:30"));

                    int progressDurTime = (int)time / 1000;
                    mProgressAudioParentBar.setProgress(progressDurTime);
                }
            }
        };

        mImgSoundRecording.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(PublishSoundRecordingActivity.this, RecordingService.class);
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    //  showToast(PublishSoundRecordingActivity.this,"按下");
                    if (audioRecorder.getStatus() == AudioRecorder.Status.STATUS_NO_READY) {
                        //初始化录音
                        // String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                        startTimer();
                        //   mAudiofileName = getFileName();
                        // audioRecorder.createDefaultAudio(mAudiofileName);
                        //audioRecorder.startRecord(null);

                        // showTimer();
                        File folder = new File(Environment.getExternalStorageDirectory() + "/SoundRecorder");
                        if (!folder.exists()) {
                            //folder /SoundRecorder doesn't exist, create the folder
                            folder.mkdir();
                        }
                        startService(intent);
                    } else if (audioRecorder.getStatus() == AudioRecorder.Status.STATUS_PAUSE) {
                        audioRecorder.startRecord(null);
                    }
                    mTvSoundRecordingTips.setText("长按录音");

                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    //showToast(PublishSoundRecordingActivity.this,"松开");
                    stopTimer();
                    stopService(intent);
                    mIbtnSoundMediaPlay.setVisibility(View.VISIBLE);
                    mIbtnDeleteSoundRecording.setVisibility(View.VISIBLE);
                    mTvSoundRecordingTips.setText("长按录音");
                  /*  if (audioRecorder.getStatus() == AudioRecorder.Status.STATUS_START) {
                        //暂停录音
                        audioRecorder.pauseRecord();
                        mTvSoundRecordingTips.setText("按住继续录音");
                        audioRecorder.release();
                        stopTimer();
                    }*/
                }
                return false;
            }
        });

        mWaveLineViewAudioFrequency.startAnim();

    }

    @Override
    public void initPresenter() {

    }

    public void startTimer() {
        if (mTimer == null) {
            mTimer = new Timer();
        }

        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    Log.i("time", "count: " + String.valueOf(count));
                    sendMessage(TIME_COUNT_CODE_MSG);

                    do {
                        try {
                            Log.i("time", "sleep(1000)...");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    } while (isPause);

                    count++;
                }
            };
        }

        if (mTimer != null && mTimerTask != null)
            mTimer.schedule(mTimerTask, delay, period);
    }

    public void sendMessage(int id) {
        if (mHandler != null) {
            Message message = Message.obtain(mHandler, id);
            mHandler.sendMessage(message);
        }
    }

    public void stopTimer() {

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
        count = 0;

    }

    public String getFileName() {

        stringBuffer.setLength(0);
        stringBuffer.append("audio/");
        String date = DateUtil.getTime(new Date(), DateUtil.sDateYMDFormat);
        stringBuffer.append(date + "/");
        String uuid = EncryptedSignatureUtil.getUUID();
        stringBuffer.append(uuid);
        return stringBuffer.toString();
    }

 /*   private void showTimer() {
        mTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                delaytime--;
                Message msg = new Message();
                msg.what = VERIFY_CODE_MSG;
                handler.sendMessage(msg);
            }
        };
        mTimer.schedule(timerTask, 1, 1000);
    }*/

    @OnClick({R.id.rlyt_publish_note, R.id.ibtn_sound_media_play, R.id.ibtn_delete_sound_recording})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlyt_publish_note:

                Intent intent = new Intent();
                intent.putStringArrayListExtra("publishPictureUrlList", (ArrayList<String>) publishPictureUrlList);
                intent.putExtra("mAudiofileName", mAudiofileName);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.ibtn_sound_media_play:
                try {
                    mIbtnSoundMediaPlay.setBackgroundResource(R.mipmap.icon_audio_playing);
                    mediaPlayer = new MediaPlayer();
                    //    String fileBasePath = FileUtils.getWavFileAbsolutePath(mAudiofileName);
                    SharedPreferences sharePreferences = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
                    final String filePath = sharePreferences.getString("audio_path", "");
                    long duration = sharePreferences.getLong("duration", 0);

                   // mProgressAudioParentBar
                    System.out.print(filePath);
                    System.out.print(duration);

                    mediaPlayer.setDataSource(filePath);
                    mediaPlayer.prepare();

                    int mediaDuration = mediaPlayer.getDuration();

                    String ms = timeParse(mediaDuration);

                    System.out.print(ms + "");
                    mediaPlayer.start();

                    updateSeekBar();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.ibtn_delete_sound_recording:
                try {

                    if (isAudioDelete == false) {
                        mIbtnDeleteSoundRecording.setBackgroundResource(R.mipmap.icon_publish_media_sound_delete);
                        isAudioDelete = true;
                    } else {
                        mIbtnDeleteSoundRecording.setBackgroundResource(R.mipmap.icon_publish_media_sound_close);
                        mIbtnSoundMediaPlay.setVisibility(View.GONE);
                        mIbtnDeleteSoundRecording.setVisibility(View.GONE);
                        isAudioDelete = false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            default:
                break;
        }
    }

    /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发

        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示

        }
    }


    /**
     * Android 音乐播放器应用里，读出的音乐时长为 long 类型以毫秒数为单位，例如：将 234736 转化为分钟和秒应为 03:55 （包含四舍五入）
     *
     * @param duration 音乐时长
     * @return
     */
    public static String timeParse(long duration) {
        String time = "";
        long minute = duration / 60000;
        long seconds = duration % 60000;
        long second = Math.round((float) seconds / 1000);
        if (minute < 10) {
            time += "0";
        }
        time += minute + ":";
        if (second < 10) {
            time += "0";
        }
        time += second;
        return time;

    }

    //updating mSeekBar
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if(mediaPlayer != null)
            {
                int mCurrentPosition = mediaPlayer.getCurrentPosition();
                mProgressAudioParentBar.setProgress(mCurrentPosition / 1000);

                long minutes = TimeUnit.MILLISECONDS.toMinutes(mCurrentPosition);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(mCurrentPosition)
                        - TimeUnit.MINUTES.toSeconds(minutes);

                mTvAudioDuration.setText(String.format("%02d:%02d", minutes, seconds) + "/" +  "00:30");

                updateSeekBar();
            }
        }
    };

    private void updateSeekBar()
    {
        mHandler.postDelayed(mRunnable, 1000);
    }
}
