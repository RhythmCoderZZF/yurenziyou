package com.nbhysj.coupon.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MyBusinessCardOprateAdapter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/06/17
 * description：行程助手编辑
 */
public class TravelAssistantEditActivity extends BaseActivity {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.rv_my_bussiness_card)
    RecyclerView mRvBussinessCard;
    @BindView(R.id.image_avatar)
    GlideImageView mImgAvatar;
    @BindView(R.id.img_my_business_card_cancel)
    ImageView mImgBusinessCardCancel;
    List<String> myBusinessCardOprateList;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_my_business_card;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
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


        mImgAvatar.loadCircle("http://hbimg.b0.upaiyun.com/783a3c8ba4d6790e19aff987d3f1d226fdc422cc24b81-4OSAFX_fw658");
    }

    @Override
    public void initData() {

        if (myBusinessCardOprateList == null) {

            myBusinessCardOprateList = new ArrayList<>();

        } else {

            myBusinessCardOprateList = new ArrayList<>();
        }

        myBusinessCardOprateList.add("保存到相册");
        myBusinessCardOprateList.add("微信");
        myBusinessCardOprateList.add("朋友圈");
        myBusinessCardOprateList.add("朋友圈");
        myBusinessCardOprateList.add("微博");
        myBusinessCardOprateList.add("QQ");
        myBusinessCardOprateList.add("QQ空间");
        myBusinessCardOprateList.add("更多");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TravelAssistantEditActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvBussinessCard.setLayoutManager(linearLayoutManager);
        MyBusinessCardOprateAdapter destinationScenicSpotsAdapter = new MyBusinessCardOprateAdapter(TravelAssistantEditActivity.this);
        destinationScenicSpotsAdapter.setMyBusinessCardOprateList(myBusinessCardOprateList);
        mRvBussinessCard.setAdapter(destinationScenicSpotsAdapter);

        mImgBusinessCardCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TravelAssistantEditActivity.this.finish();
            }
        });
    }

    @Override
    public void initPresenter() {

    }
}
