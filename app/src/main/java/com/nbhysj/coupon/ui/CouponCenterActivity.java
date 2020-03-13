package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.CouponCenterListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.CouponListContract;
import com.nbhysj.coupon.dialog.CouponDescriptionDialog;
import com.nbhysj.coupon.model.CouponListModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CouponListBean;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.model.response.UseCouponTicketResponse;
import com.nbhysj.coupon.presenter.CouponListPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CouponCenterActivity extends BaseActivity<CouponListPresenter, CouponListModel> implements CouponListContract.View {

    //票券中心
    @BindView(R.id.rv_coupon_center)
    RecyclerView mRvCouponCenter;

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    private int mPageNo = 1;
    private int mPageSize = 10;
    private CouponDescriptionDialog couponDescriptionDialog;
    private CouponCenterListAdapter couponCenterListAdapter;

    //优惠券数据
    private List<CouponsBean> couponAllList;
    private boolean isOnLoadMore = false;

    //总条数
    private int mTotalPageCount;

    private int mCouponId;

    private int mPosition;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_ticket_center;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(CouponCenterActivity.this, getResources().getString(R.string.str_ticket_center), R.mipmap.icon_left_arrow_black);

        if(couponAllList == null){

            couponAllList = new ArrayList<>();

        } else {

            couponAllList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CouponCenterActivity.this) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };

        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvCouponCenter.setLayoutManager(linearLayoutManager);
        couponCenterListAdapter = new CouponCenterListAdapter(CouponCenterActivity.this, new CouponCenterListAdapter.CouponToUseListener() {
            @Override
            public void setCouponToUseCallback(int position,int couponId) {
                mCouponId = couponId;
                mPosition = position;
                receiveCoupon();
            }

            @Override
            public void setCouponDesCallback(String intro) {
                if (!TextUtils.isEmpty(intro))
                {
                    if (couponDescriptionDialog == null) {
                        couponDescriptionDialog = new CouponDescriptionDialog(CouponCenterActivity.this).builder().setContent(intro);
                    }
                    couponDescriptionDialog.show();
                }
            }
        });
        couponCenterListAdapter.setCouponList(couponAllList);
        mRvCouponCenter.setAdapter(couponCenterListAdapter);

    }

    @Override
    public void initData() {

        showProgressDialog(CouponCenterActivity.this);
        getCouponCenterList();

       mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPageNo = 1;
                        couponAllList.clear();
                        couponCenterListAdapter.notifyDataSetChanged();
                        showProgressDialog(CouponCenterActivity.this);
                        getCouponCenterList();

                    }
                }, 100);
            }
        });

        /* mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isOnLoadMore = true;
                        try {
                            if (mTotalPageCount == couponAllList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getCouponCenterList();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 200);
            }
        });*/
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void useCouponTicketResult(BackResult<UseCouponTicketResponse> res) {

    }

    @Override
    public void getCouponListResult(BackResult<CouponListBean> res) {

    }

    @Override
    public void getCouponCenterResult(BackResult<List<CouponsBean>> res) {
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

                        couponAllList.clear();
                        couponCenterListAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    List<CouponsBean> couponList = res.getData();

                    if(couponList != null)
                    {
                        if (couponList.size() == 0) {
                            mRlytNoData.setVisibility(View.VISIBLE);

                        } else {
                            mRlytNoData.setVisibility(View.GONE);
                        }
                    }

                    if (couponList != null)
                    {
                        couponAllList.addAll(couponList);
                    }

                    couponCenterListAdapter.setCouponList(couponAllList);
                    couponCenterListAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(CouponCenterActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getCouponResult(BackResult<CouponsGetBean> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    CouponsGetBean couponsGetBean = res.getData();
                    int canGetAgainStatus = couponsGetBean.getCanGetAgainStatus();
                    CouponsBean couponsBean = couponAllList.get(mPosition);
                    couponsBean.setCanGetAgainStatus(canGetAgainStatus);
                    couponCenterListAdapter.setCouponList(couponAllList);
                    couponCenterListAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(CouponCenterActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void couponExchangeResult(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {
        if (mSmartRefreshLayout != null)
        {
            mSmartRefreshLayout.finishRefresh();
        }

        dismissProgressDialog();
        showToast(CouponCenterActivity.this, Constants.getResultMsg(msg));
    }

    public void getCouponCenterList() {

        if (validateInternet())
        {
            mPresenter.getCouponCenter();
        }
    }

    /**
     * 获取优惠券列表
     */
    public void receiveCoupon(){

        if (validateInternet()) {

            showProgressDialog(CouponCenterActivity.this);
            mDialog.setTitle("正在领取...");
            mPresenter.getCoupon(mCouponId);
        }
    }
}
