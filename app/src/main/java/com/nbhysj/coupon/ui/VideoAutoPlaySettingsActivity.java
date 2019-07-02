package com.nbhysj.coupon.ui;

import android.os.Bundle;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.ToolbarHelper;

/**
 * @auther：hysj created on 2019/03/02
 * description：视频自动播放设置
 */
public class VideoAutoPlaySettingsActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_auto_play_settings;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(VideoAutoPlaySettingsActivity.this, getResources().getString(R.string.str_video_auto_play_settings), R.mipmap.nav_ico_back_black);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
