package com.nbhysj.coupon.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FineFoodListAdapter;
import com.nbhysj.coupon.adapter.MerchentAlbumItemAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.ScenicSpotContract;
import com.nbhysj.coupon.model.ScenicSpotModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MerchantAlbumResponse;
import com.nbhysj.coupon.model.response.MerchantListResponse;
import com.nbhysj.coupon.model.response.NetFriendAlbumResponse;
import com.nbhysj.coupon.model.response.ScenicBangDanRankingResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.TourGuideBean;
import com.nbhysj.coupon.presenter.ScenicSpotPresenter;
import com.nbhysj.coupon.ui.FineGoodListActivity;
import com.nbhysj.coupon.ui.ScenicSpotDestinationActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.ui.ScenicSpotsAlbumActivity;
import com.nbhysj.coupon.view.ColorFlipPagerTitleView;
import com.nbhysj.coupon.view.MyRecycleView;
import com.nbhysj.coupon.view.RecyclerScrollView;
import com.nbhysj.coupon.view.ScenicSpotDetailBannerView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/05/06
 * description：商家相册
 */
public class MerchantAlbumFragment extends BaseFragment<ScenicSpotPresenter, ScenicSpotModel> implements ScenicSpotContract.View {
    @BindView(R.id.flowlayout_merchant_album)
    TagFlowLayout mTagFlowMerchantAlbum;
    //商家相册
    @BindView(R.id.rv_merchant_album)
    MyRecycleView mRvMerchantAlbum;
    @BindView(R.id.scrollview)
    RecyclerScrollView mRvScrollview;
    private List<MchAlbumResponse.CateWithPhotosEntity> merchantAlbumResponseList;
    LinearLayoutManager layoutManager;
    MerchentAlbumItemAdapter merchentAlbumItemAdapter;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    private String[] mTitles = new String[]{"明星动物3", "馆内环境4", "表演活动2", "其他"};
    private List<String> mDataList = Arrays.asList(mTitles);
    //商户id
    private int mchId;

    public MerchantAlbumFragment() {
        // Required empty public constructor
    }

    public static MerchantAlbumFragment newInstance(int mchId) {
        MerchantAlbumFragment fragment = new MerchantAlbumFragment();
        Bundle args = new Bundle();
        args.putInt("mchId", mchId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_merchant_album;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);

    }

    @Override
    public void initView(View v) {

        mchId = getActivity().getIntent().getIntExtra("mchId", 0);
        if (merchantAlbumResponseList == null) {

            merchantAlbumResponseList = new ArrayList<>();
        } else {
            merchantAlbumResponseList.clear();
        }

/*
        List<String> fineFoodTagList = new ArrayList<>();
        fineFoodTagList.add("明星动物4");
        fineFoodTagList.add("馆内环境4");
        fineFoodTagList.add("表演活动2");
        fineFoodTagList.add("其他2");*/

        initMagicIndicator();

    }

    @Override
    public void initData() {
        getMchAlbum();

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvMerchantAlbum.setLayoutManager(layoutManager);
        merchentAlbumItemAdapter = new MerchentAlbumItemAdapter(getActivity());
        merchentAlbumItemAdapter.setMerchantAlbumList(merchantAlbumResponseList);
        mRvMerchantAlbum.setAdapter(merchentAlbumItemAdapter);
    }

    @Override
    public void getScenicSpotHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findScenicByCateResult(BackResult<ScenicSpotResponse> res) {

    }

    @Override
    public void getScenicBangDanRankingResult(BackResult<ScenicBangDanRankingResponse> res) {

    }

    @Override
    public void getMchDetailsResult(BackResult<MchDetailsResponse> res) {

    }

    @Override
    public void getTourGuideListResult(BackResult<List<TourGuideBean>> res) {

    }

    @Override
    public void getMchAlbumListResult(BackResult<MchAlbumResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    MchAlbumResponse mchDetailsResponse = res.getData();
                    List<MchAlbumResponse.CatePhotoNumsEntity> catePhotoNumsList = mchDetailsResponse.getCatePhotoNums();
                    List<MchAlbumResponse.CateWithPhotosEntity> cateWithPhotosEntity = mchDetailsResponse.getCateWithPhotos();

                    TagAdapter tagAdapter = new TagAdapter<MchAlbumResponse.CatePhotoNumsEntity>(catePhotoNumsList) {
                        @Override
                        public View getView(FlowLayout parent, int position, MchAlbumResponse.CatePhotoNumsEntity catePhotoNumsEntity) {
                            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_merchant_album,
                                    mTagFlowMerchantAlbum, false);
                            TextView tv = view.findViewById(R.id.tv_flowlayout);
                            String title = catePhotoNumsEntity.getTitle();
                            int num = catePhotoNumsEntity.getNum();
                            tv.setText(title + num);

                            return view;
                        }
                    };
                    mTagFlowMerchantAlbum.setMaxSelectCount(1);
                    mTagFlowMerchantAlbum.setAdapter(tagAdapter);


                    mTagFlowMerchantAlbum.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                        @Override
                        public boolean onTagClick(View view, int position, FlowLayout parent) {
                            String content = "";
                            Set<Integer> selectPosSet = mTagFlowMerchantAlbum.getSelectedList();
                            Iterator it = selectPosSet.iterator();
                            while (it.hasNext()) {
                                int index = (int) it.next();
                                //content = options[index];
                                // MoveToPosition(layoutManager,index);


                            }
                            return true;
                        }
                    });
                    tagAdapter.setSelectedList(0);


                    merchentAlbumItemAdapter.setMerchantAlbumList(cateWithPhotosEntity);
                    merchentAlbumItemAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getNetFriendAlbumListResult(BackResult<NetFriendAlbumResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }
    }

    /* RecyclerView 移动到当前位置，
     *
     * @param manager  设置RecyclerView对应的manager
     * @param n  要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }

    @Override
    public void lazyInitView(View view) {


    }


    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(getActivity(), R.color.color_text_gray24));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(getActivity(), R.color.black));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mRvScrollview.smoothScrollTo(0, 2835);
                        // viewPager.setCurrentItem(index, false);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 4));
                indicator.setLineWidth(UIUtil.dip2px(context, 18));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(getActivity(), R.color.color_high_light_green));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        // ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    public void getMchAlbum() {

        if (validateInternet()) {

            mPresenter.getMchAlbumList(mchId);
        }
    }
}
