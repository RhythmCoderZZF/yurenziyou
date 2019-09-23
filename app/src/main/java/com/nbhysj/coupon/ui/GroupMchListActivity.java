package com.nbhysj.coupon.ui;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.GroupMchListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.GroupMchContract;
import com.nbhysj.coupon.model.GroupMchModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.GroupMchDetailsResponse;
import com.nbhysj.coupon.model.response.GroupMchResponse;
import com.nbhysj.coupon.presenter.GroupMchPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.widget.GroupMchTabIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/09/22
 * description：组合列表
 */
public class GroupMchListActivity extends BaseActivity<GroupMchPresenter, GroupMchModel> implements GroupMchContract.View {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    //组合
    @BindView(R.id.rv_combination)
    RecyclerView mRvCombination;
    //组合tag列表
    @BindView(R.id.indicator)
    GroupMchTabIndicator mTabIndicator;

    @BindView(R.id.tv_title_tag)
    TextView mTvTitleTag;
    //组合头部banner
    @BindView(R.id.img_combination_header)
    ImageView mImgGroupHeaderBanner;
    List<GroupMchResponse.TagsEntity> tagList;

    private GroupMchListAdapter groupMchListAdapter;

    private List<GroupMchResponse.PackageVOSEntity> packageVOSEntityList;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_group_mch_list;
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

        //组合列表
        if(packageVOSEntityList == null){

            packageVOSEntityList = new ArrayList<>();
        } else {
            packageVOSEntityList.clear();
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GroupMchListActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvCombination.setLayoutManager(linearLayoutManager);
        groupMchListAdapter = new GroupMchListAdapter(GroupMchListActivity.this);
        groupMchListAdapter.setGroupMchList(packageVOSEntityList);
        mRvCombination.setAdapter(groupMchListAdapter);

    }

    @Override
    public void initData() {
        getGroupMchHomePage();
    }

    @Override
    public void getGroupMchHomePageResult(BackResult<GroupMchResponse> res) {

        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    GroupMchResponse groupMchResponse = res.getData();
                    String bannerUrl = groupMchResponse.getBanner();
                    GlideUtil.loadImage(GroupMchListActivity.this, bannerUrl, mImgGroupHeaderBanner);
                    tagList = groupMchResponse.getTags();
                    List<GroupMchResponse.GroupEntity> groupVOSEntityList = groupMchResponse.getGroup();
                    packageVOSEntityList = groupVOSEntityList.get(0).getPackageVOS();
                    groupMchListAdapter.setGroupMchList(packageVOSEntityList);
                    groupMchListAdapter.notifyDataSetChanged();
                    mTabIndicator.initTab(tagList, 13);
                    mTabIndicator.setmTabSelector(0);

                    mTabIndicator.setMyOnPageChangeListener(new GroupMchTabIndicator.MyOnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position)
                        {
                            String title = tagList.get(position).getTitle();
                            mTvTitleTag.setText(title);
                            GroupMchResponse.GroupEntity groupEntity = groupVOSEntityList.get(position);
                            packageVOSEntityList = groupEntity.getPackageVOS();
                            groupMchListAdapter.setGroupMchList(packageVOSEntityList);
                            groupMchListAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(GroupMchListActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getGroupMchDetailResult(BackResult<GroupMchDetailsResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(GroupMchListActivity.this, Constants.getResultMsg(msg));
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.iv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                GroupMchListActivity.this.finish();
                break;
            default:
                break;

        }
    }

    public void getGroupMchHomePage() {

        if (validateInternet()) {
            showProgressDialog(GroupMchListActivity.this);
            mPresenter.getGroupMchHomePage();
        }
    }
}
