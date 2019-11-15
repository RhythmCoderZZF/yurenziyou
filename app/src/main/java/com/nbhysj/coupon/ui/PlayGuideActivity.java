package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.PlayGuideMoreListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.TourProjectTypeEnum;
import com.nbhysj.coupon.contract.ScenicSpotContract;
import com.nbhysj.coupon.model.ScenicSpotModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.NetFriendAlbumResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.TourGuideBean;
import com.nbhysj.coupon.presenter.ScenicSpotPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.widget.TabIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/05/08
 * description：游玩指南
 */
public class PlayGuideActivity extends BaseActivity<ScenicSpotPresenter, ScenicSpotModel> implements ScenicSpotContract.View {
    @BindView(R.id.indicator)
    TabIndicator mTabIndicator;
    @BindView(R.id.rv_play_guide)
    RecyclerView mRvPlayGuide;
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;

    private PlayGuideMoreListAdapter playGuideMoreListAdapter;
    private List<TourGuideBean> mTourGuideList;
    //商户id
    private int mchId;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_play_guide;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setHeadBar(PlayGuideActivity.this, getResources().getString(R.string.str_play_guide), R.mipmap.icon_left_arrow_black, "");

        mchId = getIntent().getIntExtra("mchId", 0);
        if (mTourGuideList == null) {

            mTourGuideList = new ArrayList<>();
        } else {
            mTourGuideList.clear();
        }
    }

    @Override
    public void initData() {

        getTourGuideList("");

        LinearLayoutManager layoutManager = new LinearLayoutManager(PlayGuideActivity.this);
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvPlayGuide.setLayoutManager(layoutManager);
        playGuideMoreListAdapter = new PlayGuideMoreListAdapter(PlayGuideActivity.this);
        playGuideMoreListAdapter.setPlayGuideMoreList(mTourGuideList);
        mRvPlayGuide.setAdapter(playGuideMoreListAdapter);

        List<String> labelList3 = new ArrayList();
        labelList3.add("全部");
        labelList3.add("景点");
        labelList3.add("收费项目");
        labelList3.add("表演活动");
        mTabIndicator.notifyAddTab(labelList3, 13);
        mTabIndicator.setmTabSelector(0);

        mTabIndicator.setMyOnPageChangeListener(new TabIndicator.MyOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTourGuideList.clear();
                if (position == 0) {
                    getTourGuideList("");
                } else {
                    String tourGuideType = TourProjectTypeEnum.getEnumByKey(position - 1);
                    getTourGuideList(tourGuideType);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getScenicSpotHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findScenicByCateResult(BackResult<ScenicSpotResponse> res) {

    }

    @Override
    public void getScenicBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

    }

    @Override
    public void getMchAlbumListResult(BackResult<MchAlbumResponse> res) {

    }

    @Override
    public void getNetFriendAlbumListResult(BackResult<NetFriendAlbumResponse> res) {

    }

    @Override
    public void getMchDetailsResult(BackResult<MchDetailsResponse> res) {

    }

    @Override
    public void mchCollectionResult(BackResult<MchCollectionResponse> res) {

    }

    @Override
    public void queryMchCouponListResult(BackResult<List<MchCouponResponse>> res) {

    }

    @Override
    public void getTourGuideListResult(BackResult<List<TourGuideBean>> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    mRlytNoData.setVisibility(View.GONE);
                    List<TourGuideBean> tourGuideList = res.getData();
                    mTourGuideList.addAll(tourGuideList);
                    if (tourGuideList.size() == 0) {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    }
                    playGuideMoreListAdapter.setPlayGuideMoreList(mTourGuideList);
                    playGuideMoreListAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(PlayGuideActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getCouponResult(BackResult<CouponsGetBean> res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(PlayGuideActivity.this, Constants.getResultMsg(msg));
    }

    public void getTourGuideList(String tourGuideType) {

        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("mchId", String.valueOf(mchId));
        if (!TextUtils.isEmpty(tourGuideType)) {
            hashMap.put("type", tourGuideType);
        }
        mPresenter.getTourGuideList(hashMap);
    }
}
