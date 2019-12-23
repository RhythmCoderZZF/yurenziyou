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
import com.nbhysj.coupon.adapter.FineFoodBangDanListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.FineFoodContract;
import com.nbhysj.coupon.model.FineFoodModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FineFoodCommentInitResponse;
import com.nbhysj.coupon.model.response.FoodRecommendListResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCateListResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MchFoodDetailResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.presenter.FineFoodPresenter;
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
 * @auther：hysj created on 2019/09/16
 * description：美食榜单列表
 */
public class FineFoodBangDanListActivity extends BaseActivity<FineFoodPresenter, FineFoodModel> implements FineFoodContract.View {
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
    //互动榜单收藏
    @BindView(R.id.img_fine_food_collection)
    ImageView mImgFineFoodCollection;
    //互动榜单转发
    @BindView(R.id.img_fine_food_forward)
    ImageView mImgFineFoodForward;
    private FineFoodBangDanListAdapter fineFoodBangDanListAdapter;
    private LinearLayoutManager scenicSpotsLinearLayoutManager;
    //景点列表
    List<MchTypeBean> mRecreationList;
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

        if (mRecreationList == null) {

            mRecreationList = new ArrayList<>();
        } else {
            mRecreationList.clear();
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

        scenicSpotsLinearLayoutManager = new LinearLayoutManager(FineFoodBangDanListActivity.this);
        scenicSpotsLinearLayoutManager.setOrientation(scenicSpotsLinearLayoutManager.VERTICAL);
        mRvScenicSpotBangdan.setLayoutManager(scenicSpotsLinearLayoutManager);
        fineFoodBangDanListAdapter = new FineFoodBangDanListAdapter(FineFoodBangDanListActivity.this);
        fineFoodBangDanListAdapter.setFineFoodList(mRecreationList);
        mRvScenicSpotBangdan.setAdapter(fineFoodBangDanListAdapter);
        setHeader(mRvScenicSpotBangdan);
    }


    private void setHeader(RecyclerView view) {
        header = LayoutInflater.from(this).inflate(R.layout.layout_fine_food_header_item, view, false);
        mImgFineFoodHeader = header.findViewById(R.id.img_fine_food_header);
    }

    @Override
    public void initData() {
        getFoodBangDanRankingList();
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
    public void getFineFoodHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findFoodByCateResult(BackResult<ScenicSpotResponse> res) {

    }

    @Override
    public void mchCollectionResult(BackResult<MchCollectionResponse> res) {

    }

    @Override
    public void getFoodCommentIndexResult(BackResult<FineFoodCommentInitResponse> res) {

    }

    @Override
    public void fineFoodCommentResult(BackResult res) {

    }

    @Override
    public void findFoodsListByCateIdResult(BackResult<MchCateListResponse> res) {

    }

    @Override
    public void getFoodBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    mRecreationList.clear();
                    List<MchTypeBean> scenicSpotList = res.getData().getMchs();
                    if(scenicSpotList != null && scenicSpotList.size() > 0) {
                        mRecreationList.addAll(scenicSpotList);
                        fineFoodBangDanListAdapter.setFineFoodList(mRecreationList);
                        fineFoodBangDanListAdapter.notifyDataSetChanged();
                    }
                    MchBangDanRankingResponse.BannerEntity banner = res.getData().getBanner();
                    if (banner != null) {
                        String photoUrl = banner.getPhoto();
                        GlideUtil.loadImage(mContext, photoUrl, mImgFineFoodHeader);
                    }
                    fineFoodBangDanListAdapter.setHeaderView(header);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(FineFoodBangDanListActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(FineFoodBangDanListActivity.this,Constants.getResultMsg(msg));
    }

    @Override
    public void getFoodDetailResult(BackResult<MchFoodDetailResponse> res) {

    }

    @Override
    public void getGoodsFoodRecommendList(BackResult<FoodRecommendListResponse> res) {

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

    //获取美食榜单列表
    public void getFoodBangDanRankingList() {

        if (validateInternet()) {

            mPresenter.getFoodBangDanRanking(Constants.CITY_CODE);
        }
    }
}
