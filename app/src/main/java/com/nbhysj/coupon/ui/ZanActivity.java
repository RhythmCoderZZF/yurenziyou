package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ZanAdapter;
import com.nbhysj.coupon.adapter.ZanAndCollectionAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MessageContract;
import com.nbhysj.coupon.contract.MineContract;
import com.nbhysj.coupon.model.MessageModel;
import com.nbhysj.coupon.model.MineModel;
import com.nbhysj.coupon.model.response.AttentionResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.BroadcastResponse;
import com.nbhysj.coupon.model.response.CommentAndAnswerResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.MessageResponse;
import com.nbhysj.coupon.model.response.MineCollectionDetailResponse;
import com.nbhysj.coupon.model.response.MinePostZanListResponse;
import com.nbhysj.coupon.model.response.UserFansFollowResponse;
import com.nbhysj.coupon.model.response.UserFollowResponse;
import com.nbhysj.coupon.model.response.ZanAndCollectionBean;
import com.nbhysj.coupon.model.response.ZanAndCollectionResponse;
import com.nbhysj.coupon.presenter.MessagePresenter;
import com.nbhysj.coupon.presenter.MinePresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.ResponseBody;

/**
 * @auther：hysj created on 2019/11/13
 * description：赞和收藏
 */
public class ZanActivity extends BaseActivity<MinePresenter, MineModel> implements MineContract.View {

    //收到的评论
    @BindView(R.id.rv_zan_and_collection)
    RecyclerView mRvZanAndCollection;

    private List<ZanAndCollectionBean> mZanAndCollectionList;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    private int mPageNo = 1;
    private int mPageSize = 10;

    private ZanAdapter zanAdapter;

    private boolean isOnLoadMore = false;

    //总条数
    private int mTotalPageCount;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_zan_and_collection;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(ZanActivity.this, getResources().getString(R.string.str_zan), R.mipmap.nav_ico_back_black);
        if (mZanAndCollectionList == null) {
            mZanAndCollectionList = new ArrayList<>();

        } else {
            mZanAndCollectionList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ZanActivity.this);
        mRvZanAndCollection.setLayoutManager(linearLayoutManager);
        zanAdapter = new ZanAdapter(ZanActivity.this);
        zanAdapter.setZanList(mZanAndCollectionList);
        mRvZanAndCollection.setAdapter(zanAdapter);
    }

    @Override
    public void initData() {

        showProgressDialog(ZanActivity.this);
        getZanAndCollection();

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPageNo = 1;
                        mZanAndCollectionList.clear();
                        zanAdapter.notifyDataSetChanged();
                        showProgressDialog(ZanActivity.this);
                        getZanAndCollection();

                    }
                }, 100);
            }
        });

        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isOnLoadMore = true;
                        try {
                            if (mTotalPageCount == mZanAndCollectionList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getZanAndCollection();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 200);
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void getMyPostShareListResult(ResponseBody res) {

    }

    @Override
    public void getMinePostZanListResult(BackResult<MinePostZanListResponse> res) {

    }

    @Override
    public void getMineCollectionAllListResult(ResponseBody res) {

    }

    @Override
    public void getMineCollectionDetailResult(BackResult<MineCollectionDetailResponse> res) {

    }

    @Override
    public void collectionMchBatchDeleteContentResult(BackResult res) {

    }

    @Override
    public void collectionPostsBatchDeleteResult(BackResult res) {

    }

    @Override
    public void getZanMsgListResult(BackResult<ZanAndCollectionResponse> res) {
        dismissProgressDialog();
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    if (isOnLoadMore) {

                        mSmartRefreshLayout.finishLoadMore();

                    } else {

                        mZanAndCollectionList.clear();
                        zanAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }
                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<ZanAndCollectionBean> zanAndCollectionList = res.getData().getResult();

                    if (mTotalPageCount == 0)
                    {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if (zanAndCollectionList != null)
                    {
                        mZanAndCollectionList.addAll(zanAndCollectionList);
                    }

                    zanAdapter.setZanList(mZanAndCollectionList);
                    zanAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(ZanActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getCollectionMsgListResult(BackResult<ZanAndCollectionResponse> res) {

    }


    @Override
    public void showMsg(String msg) {

        if(mSmartRefreshLayout != null){

            mSmartRefreshLayout.finishRefresh();
        }
        dismissProgressDialog();
        showToast(ZanActivity.this, Constants.getResultMsg(msg));
    }


    public void getZanAndCollection() {

        if (validateInternet()) {
            mPresenter.getZanMsgList(mPageNo, mPageSize);
        }
    }
}
