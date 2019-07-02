package com.nbhysj.coupon.ui;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.CombinationListAdapter;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.widget.NearbyTabIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/28
 * description：组合列表
 */
public class CombinationListActivity extends BaseActivity {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    //组合
    @BindView(R.id.rv_combination)
    RecyclerView mRvCombination;

    @BindView(R.id.indicator)
    NearbyTabIndicator mTabIndicator;

    @BindView(R.id.tv_title_tag)
    TextView mTvTitleTag;
    List<HomePageResponse.SmallTagEntity> tagList;
    private CombinationListAdapter combinationListAdapter;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_combination_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }

        if (tagList == null) {

            tagList = new ArrayList<>();

        } else {

            tagList.clear();
        }

        ImageView mImgFineFoodHeader = (ImageView) findViewById(R.id.img_combination_header);

        Glide.with(mContext)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555913878204&di=c240220bb8301fb4afda212125f2bc69&imgtype=0&src=http%3A%2F%2Fb.zol-img.com.cn%2Fdesk%2Fbizhi%2Fimage%2F1%2F960x600%2F1354097420734.jpg")
                .into(mImgFineFoodHeader);


        HomePageResponse.SmallTagEntity smallTagEntity = new HomePageResponse().new SmallTagEntity();
        smallTagEntity.setTitle("精选");

        HomePageResponse.SmallTagEntity smallTagEntity1 = new HomePageResponse().new SmallTagEntity();
        smallTagEntity1.setTitle("赏花");

        HomePageResponse.SmallTagEntity smallTagEntity2 = new HomePageResponse().new SmallTagEntity();
        smallTagEntity2.setTitle("主题乐园");

        HomePageResponse.SmallTagEntity smallTagEntity3 = new HomePageResponse().new SmallTagEntity();
        smallTagEntity3.setTitle("春游踏青");

        HomePageResponse.SmallTagEntity smallTagEntity4 = new HomePageResponse().new SmallTagEntity();
        smallTagEntity4.setTitle("古镇古村");

        tagList.add(smallTagEntity);
        tagList.add(smallTagEntity1);
        tagList.add(smallTagEntity2);
        tagList.add(smallTagEntity3);
        tagList.add(smallTagEntity4);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CombinationListActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvCombination.setLayoutManager(linearLayoutManager);
        combinationListAdapter = new CombinationListAdapter(CombinationListActivity.this);
        mRvCombination.setAdapter(combinationListAdapter);
        // setLayoutHeader(mRvCombination);
        mTabIndicator.initTab(tagList, 13);
        mTabIndicator.setmTabSelector(0);

        //  int mTagId = tagList.get(0).getId();
        mTabIndicator.setMyOnPageChangeListener(new NearbyTabIndicator.MyOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String title = tagList.get(position).getTitle();
                mTvTitleTag.setText(title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

   /* public void setLayoutHeader(RecyclerView view){

        View header = LayoutInflater.from(this).inflate(R.layout.layout_combination_list_header_item, view, false);


        mTabIndicator = (NearbyTabIndicator)header.findViewById(R.id.indicator);


        combinationListAdapter.setHeaderView(header);

    }*/

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.iv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                CombinationListActivity.this.finish();
                break;
            default:
                break;

        }

    }
}
