package com.nbhysj.coupon.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.DestinationAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.HotTagsTypeEnum;
import com.nbhysj.coupon.contract.DestinationContract;
import com.nbhysj.coupon.model.DestinationModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CarH5UrlResponse;
import com.nbhysj.coupon.model.response.DestinationCityResponse;
import com.nbhysj.coupon.model.response.DestinationResponse;
import com.nbhysj.coupon.model.response.HotScenicSpotResponse;
import com.nbhysj.coupon.model.response.HotTagsTopicBean;
import com.nbhysj.coupon.presenter.DestinationPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/08
 * description：目的地搜索
 */
public class DestinationSearchActivity extends BaseActivity<DestinationPresenter, DestinationModel> implements DestinationContract.View {

    //历史标签
    @BindView(R.id.flowlayout_historical_label)
    TagFlowLayout mTagHistoryLabel;
    //目的地
    @BindView(R.id.rv_destination)
    RecyclerView mRvDestination;
    //景点标签
    @BindView(R.id.flowlayout_scenic_spot_label)
    TagFlowLayout mTagScenicSpotLabel;
    //酒店标签
    @BindView(R.id.flowlayout_destination_hotel_label)
    TagFlowLayout mTagDestinationLabel;
    //亲子互动标签
    @BindView(R.id.flowlayout_parent_child_interaction_label)
    TagFlowLayout mTagParentChildInteractionLabel;
    //清除历史记录
    @BindView(R.id.image_history_record_clear)
    ImageView mImgHistoryRecordClear;

    private DestinationAdapter destinationAdapter;

    private List<DestinationCityResponse> hotTagsTopicList;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_destination_search;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        if(hotTagsTopicList == null){

            hotTagsTopicList = new ArrayList<>();
        } else {
            hotTagsTopicList.clear();
        }

        GridLayoutManager layoutManager = new GridLayoutManager(DestinationSearchActivity.this, 5);
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvDestination.setLayoutManager(layoutManager);
        destinationAdapter = new DestinationAdapter(DestinationSearchActivity.this);
        destinationAdapter.setDestinationList(hotTagsTopicList);
        mRvDestination.setAdapter(destinationAdapter);

    }
    @Override
    public void initData() {
        getHotTagsTopicList();
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void findMchBycityNameResult(BackResult<HotScenicSpotResponse> res) {

    }

    @Override
    public void getDestinationHomePageResult(BackResult<DestinationResponse> res) {

    }

    @Override
    public void getDestinationCityTagsListResult(BackResult<List<DestinationCityResponse>> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    hotTagsTopicList = res.getData();
                    destinationAdapter.setDestinationList(hotTagsTopicList);
                    destinationAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(DestinationSearchActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(DestinationSearchActivity.this, Constants.getResultMsg(msg));

    }

    @OnClick({R.id.image_history_record_clear, R.id.rlyt_back,R.id.ll_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_history_record_clear:

                showToast(DestinationSearchActivity.this, "clear");

                break;
            case R.id.rlyt_back:

                finish();

                break;
            case R.id.ll_search:
                toActivity(HomePageSearchActivity.class);
                break;
            default:
                break;
        }
    }

    //获取热门标签(主题搜索 热门标签)
    public void getHotTagsTopicList() {

        if (validateInternet()) {

            mPresenter.getDestinationCityTagsList(HotTagsTypeEnum.CITY.getValue());
        }
    }

}
