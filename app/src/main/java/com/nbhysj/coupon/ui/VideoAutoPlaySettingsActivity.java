package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/02
 * description：视频自动播放设置
 */
public class VideoAutoPlaySettingsActivity extends BaseActivity {

    //视频自动设置4G和WIFI
    @BindView(R.id.img_video_auto_play_4g_and_wifi)
    ImageView mImgVideoAutoPlay4GAndWifi;
    //视频自动设置4G和
    @BindView(R.id.img_video_auto_play_wifi)
    ImageView mImgVideoAutoPlayWifi;
    @Override
    public int getLayoutId() {
        return R.layout.activity_video_auto_play_settings;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, -131077);
        ToolbarHelper.setBar(VideoAutoPlaySettingsActivity.this, getResources().getString(R.string.str_video_auto_play_settings), R.mipmap.nav_ico_back_black);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.rlyt_video_auto_play_4g_and_wifi,R.id.rlyt_video_auto_play_wifi})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rlyt_video_auto_play_4g_and_wifi:
                mImgVideoAutoPlay4GAndWifi.setImageResource(R.mipmap.icon_user_agreement_select);
                mImgVideoAutoPlayWifi.setImageResource(R.mipmap.icon_user_agreement_unselect);
                break;
            case R.id.rlyt_video_auto_play_wifi:
                mImgVideoAutoPlayWifi.setImageResource(R.mipmap.icon_user_agreement_select);
                mImgVideoAutoPlay4GAndWifi.setImageResource(R.mipmap.icon_user_agreement_unselect);
                break;
                default:break;
        }
    }
}
