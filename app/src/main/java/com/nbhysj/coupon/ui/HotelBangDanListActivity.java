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
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.HotelBangDanListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HotelContract;
import com.nbhysj.coupon.model.HotelModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.HotelMchDetailsResponse;
import com.nbhysj.coupon.model.response.HotelOrderInitResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCateListResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.QueryByTicketResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.UseCouponTicketResponse;
import com.nbhysj.coupon.presenter.HotelPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/06/6
 * description：酒店榜单列表
 */
public class HotelBangDanListActivity extends BaseActivity<HotelPresenter, HotelModel> implements HotelContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //景点热门榜
    @BindView(R.id.rv_scenic_spot_bangdan)
    RecyclerView mRvScenicSpotBangdan;
    @BindView(R.id.rlyt_fine_food_header)
    RelativeLayout mRlytFineFoodHeader;
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.iv_back)
    ImageView mImgBack;
    //美食收藏
    @BindView(R.id.img_fine_food_collection)
    ImageView mImgFineFoodCollection;
    //美食转发
    @BindView(R.id.img_fine_food_forward)
    ImageView mImgFineFoodForward;
    private HotelBangDanListAdapter hotelBangDanListAdapter;
    private LinearLayoutManager scenicSpotsLinearLayoutManager;
    //酒店列表
    List<MchTypeBean> mHotelList;
    View header;
    private ImageView mImgFineFoodHeader;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_scenic_spot_bangdan_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        if (mHotelList == null) {

            mHotelList = new ArrayList<>();
        } else {
            mHotelList.clear();
        }

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

        scenicSpotsLinearLayoutManager = new LinearLayoutManager(HotelBangDanListActivity.this);
        scenicSpotsLinearLayoutManager.setOrientation(scenicSpotsLinearLayoutManager.VERTICAL);
        mRvScenicSpotBangdan.setLayoutManager(scenicSpotsLinearLayoutManager);
        hotelBangDanListAdapter = new HotelBangDanListAdapter(HotelBangDanListActivity.this);
        hotelBangDanListAdapter.setHotelBangDanList(mHotelList);
        mRvScenicSpotBangdan.setAdapter(hotelBangDanListAdapter);
        setHeader(mRvScenicSpotBangdan);
    }


    private void setHeader(RecyclerView view) {
        header = LayoutInflater.from(this).inflate(R.layout.layout_fine_food_header_item, view, false);
        mImgFineFoodHeader = header.findViewById(R.id.img_fine_food_header);
    }

    @Override
    public void initData() {
        getHotelBangDanRankingList();
        mRvScenicSpotBangdan.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //找到即将移出屏幕Item的position,position是移出屏幕item的数量
                int position = scenicSpotsLinearLayoutManager.findFirstVisibleItemPosition();
                //根据position找到这个Item
                View firstVisiableChildView = scenicSpotsLinearLayoutManager.findViewByPosition(position);
                //获取Item的高
                int itemHeight = firstVisiableChildView.getHeight();
                //算出该Item还未移出屏幕的高度
                int itemTop = firstVisiableChildView.getTop();
                //position移出屏幕的数量*高度得出移动的距离
                int iposition = position * itemHeight;
                //减去该Item还未移出屏幕的部分可得出滑动的距离
                int iResult = iposition - itemTop;
                if (iResult < 300) {
                    float scale = (float) (iResult) / 300;
                    float alpha = (255 * scale);
                    mRlytFineFoodHeader.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    mToolbarSpace.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    mImgFineFoodCollection.setImageResource(R.mipmap.icon_white_fine_food_collection);
                    mImgFineFoodForward.setImageResource(R.mipmap.icon_white_forward);
                    mImgBack.setImageResource(R.mipmap.icon_left_arrow_white);

                } else {
                    mRlytFineFoodHeader.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    mToolbarSpace.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    mImgFineFoodCollection.setImageResource(R.mipmap.icon_black_fine_food_collection);
                    mImgFineFoodForward.setImageResource(R.mipmap.icon_black_forward);
                    mImgBack.setImageResource(R.mipmap.icon_left_arrow_black);
                }
            }


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisiableItemPosition = scenicSpotsLinearLayoutManager.findFirstVisibleItemPosition();
            }
        });

        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // isOnLoadMore = true;
                        try {
                           /* if (mTotalCount == merchantEntityList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                getMerchantList();
                            }*/
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        refreshLayout.finishLoadMore();
                    }
                }, 200);
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }


    @Override
    public void getHotelBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    List<MchTypeBean> scenicSpotList = res.getData().getMchs();
                    mHotelList.addAll(scenicSpotList);
                    hotelBangDanListAdapter.setHotelBangDanList(mHotelList);
                    hotelBangDanListAdapter.notifyDataSetChanged();

                    MchBangDanRankingResponse.BannerEntity banner = res.getData().getBanner();
                    if (banner != null) {
                        String photoUrl = banner.getPhoto();
                        GlideUtil.loadImage(mContext, photoUrl, mImgFineFoodHeader);
                    }
                    hotelBangDanListAdapter.setHeaderView(header);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(HotelBangDanListActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getHotelHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findHotelByCateResult(BackResult<ScenicSpotResponse> res) {

    }

    @Override
    public void getHotelMchDetailResult(BackResult<HotelMchDetailsResponse> res) {

    }

    @Override
    public void getHotelHomestayOrderInitResult(BackResult<HotelOrderInitResponse> res) {

    }

    @Override
    public void hotelHomestayOrderSubmitResult(BackResult<OrderSubmitResponse> res) {

    }

    @Override
    public void mchCollectionResult(BackResult<MchCollectionResponse> res) {

    }

    @Override
    public void queryByTicketResult(BackResult<QueryByTicketResponse> res) {

    }

    @Override
    public void useCouponTicketResult(BackResult<UseCouponTicketResponse> res) {

    }

    @Override
    public void queryMchCouponListResult(BackResult<List<MchCouponResponse>> res) {

    }

    @Override
    public void getCouponResult(BackResult<CouponsGetBean> res) {

    }

    @Override
    public void getHotelListByCateIdResult(BackResult<MchCateListResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(HotelBangDanListActivity.this,Constants.getResultMsg(msg));
    }

    @OnClick({R.id.iv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
    //获取酒店榜单列表
    public void getHotelBangDanRankingList() {

        if (validateInternet()) {

            mPresenter.getHotelBangDanRanking(Constants.CITY_CODE);
        }
    }
}
