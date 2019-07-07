package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MyBusinessCardOprateAdapter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/06/17
 * description：行程助手编辑
 */
public class TravelAssistantEditActivity extends BaseActivity {

    //出游时间
    @BindView(R.id.tv_travel_start_time)
    TextView mTvTravelStartTime;

    //结束时间
    @BindView(R.id.tv_travel_end_time)
    TextView mTvTravelEndTime;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_travel_assisant_edit;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        //沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        BlurBehind.getInstance()
                .withAlpha(90)
                .withFilterColor(Color.parseColor("#000000"))
                .setBackground(TravelAssistantEditActivity.this);

      /*  ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }*/


    }

    @Override
    public void initData() {


    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.tv_travel_start_time,R.id.tv_travel_end_time,R.id.llyt_my_travel_detail_item,R.id.img_travel_assistant_edit_close})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.tv_travel_start_time:

                 toActivityForResult(CalendarActivity.class,0);
                break;
            case R.id.tv_travel_end_time:
                  toActivityForResult(CalendarActivity.class,0);
                break;
            case R.id.llyt_my_travel_detail_item:

                TravelAssistantEditActivity.this.finish();

                break;
            case R.id.img_travel_assistant_edit_close:

                TravelAssistantEditActivity.this.finish();

                break;
                default:break;
        }
    }
}
