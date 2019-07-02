package com.nbhysj.coupon.ui;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.DestinationAdapter;
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
public class DestinationSearchActivity extends BaseActivity {

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

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_destination_search;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        List<String> historyLabelList = new ArrayList<>();
        historyLabelList.add("宁波海洋世界");
        historyLabelList.add("有猫的咖啡店");
        historyLabelList.add("蛋糕甜点");
        historyLabelList.add("美食");
        historyLabelList.add("附近美术馆");
        historyLabelList.add("赏花好去处");
        historyLabelList.add("亲子互动");

        mTagHistoryLabel.setAdapter(new TagAdapter<String>(historyLabelList) {

            @Override
            public View getView(FlowLayout parent, int position, String historyLabel) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_gray_frame,
                        mTagHistoryLabel, false);
                tagName.setText(historyLabel);
                return tagName;
            }
        });

        mTagHistoryLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                //    showToast(DestinationSearchActivity.this,historyLabelList.get(position));
                toActivity(ScenicSpotDestinationActivity.class);
                return false;
            }
        });

        List<String> destinationList = new ArrayList<>();
        destinationList.add("北仑");
        destinationList.add("镇海");
        destinationList.add("余姚");
        destinationList.add("慈溪");
        destinationList.add("象山");
        destinationList.add("宁海");
        destinationList.add("奉化");
        destinationList.add("海曙");
        destinationList.add("江北");
        destinationList.add("鄞州");

        GridLayoutManager layoutManager = new GridLayoutManager(DestinationSearchActivity.this, 5);
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvDestination.setLayoutManager(layoutManager);
        DestinationAdapter destinationAdapter = new DestinationAdapter(DestinationSearchActivity.this);
        destinationAdapter.setDestinationList(destinationList);
        mRvDestination.setAdapter(destinationAdapter);


        List<String> scenicSpotLabelList = new ArrayList<>();
        scenicSpotLabelList.add("宁波海洋世界");
        scenicSpotLabelList.add("有猫的咖啡店");
        scenicSpotLabelList.add("蛋糕甜点");
        scenicSpotLabelList.add("美食");
        scenicSpotLabelList.add("附近美术馆");
        scenicSpotLabelList.add("赏花好去处");
        scenicSpotLabelList.add("亲子互动");

        mTagScenicSpotLabel.setAdapter(new TagAdapter<String>(scenicSpotLabelList) {

            @Override
            public View getView(FlowLayout parent, int position, String historyLabel) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_gray_frame,
                        mTagHistoryLabel, false);
                tagName.setText(historyLabel);
                return tagName;
            }
        });

        List<String> scenicHotelLabelList = new ArrayList<>();
        scenicHotelLabelList.add("南苑环球酒店");
        scenicHotelLabelList.add("东港喜来登");
        scenicHotelLabelList.add("宁波万豪");

        mTagDestinationLabel.setAdapter(new TagAdapter<String>(scenicHotelLabelList) {

            @Override
            public View getView(FlowLayout parent, int position, String historyLabel) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_gray_frame,
                        mTagDestinationLabel, false);
                tagName.setText(historyLabel);
                return tagName;
            }
        });

        List<String> parentChildInteractionLabelList = new ArrayList<>();
        parentChildInteractionLabelList.add("奉化奶油草莓采摘");
        parentChildInteractionLabelList.add("溪口水蜜桃");
        parentChildInteractionLabelList.add("手工年糕");
        parentChildInteractionLabelList.add("农家乐采摘园");
        parentChildInteractionLabelList.add("罗门环球城");
        parentChildInteractionLabelList.add("赏花好去处");
        parentChildInteractionLabelList.add("亲子互动");

        mTagParentChildInteractionLabel.setAdapter(new TagAdapter<String>(parentChildInteractionLabelList) {

            @Override
            public View getView(FlowLayout parent, int position, String historyLabel) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_gray_frame,
                        mTagParentChildInteractionLabel, false);
                tagName.setText(historyLabel);
                return tagName;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.image_history_record_clear, R.id.rlyt_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_history_record_clear:

                showToast(DestinationSearchActivity.this, "clear");

                break;
            case R.id.rlyt_back:

                finish();

                break;
            default:
                break;
        }

    }
}
