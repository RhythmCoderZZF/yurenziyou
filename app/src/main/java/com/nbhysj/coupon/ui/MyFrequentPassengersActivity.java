package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.TravellerListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.FrequentlyUsedInfoContract;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.model.FrequentlyUsedInfoModel;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.ContactsInfoResponse;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.model.response.RecipientsInfoResponse;
import com.nbhysj.coupon.model.response.TravellerBean;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;
import com.nbhysj.coupon.presenter.FrequentlyUsedInfoPresenter;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/08
 * description：我的常用旅客
 */
public class MyFrequentPassengersActivity extends BaseActivity<FrequentlyUsedInfoPresenter, FrequentlyUsedInfoModel> implements FrequentlyUsedInfoContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //旅客列表
    @BindView(R.id.rv_frequent_travellers)
    RecyclerView mRvFrequentTravellersList;
    private int ADD_TRAVELLER_REQUEST_CODE = 0;
    //总数
    private int mTotalCount;
    private boolean isOnLoadMore = false;
    private List<TravellerBean> mTravellerList;
    private TravellerListAdapter travellerListAdapter;
    private int mPage = 1;
    private int mPageSize = 10;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_frequent_passengers;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(MyFrequentPassengersActivity.this, getResources().getString(R.string.str_my_frequent_passengers), R.mipmap.nav_ico_back_black);

        if (mTravellerList == null) {
            mTravellerList = new ArrayList<>();
        } else {
            mTravellerList.clear();
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyFrequentPassengersActivity.this);
        mRvFrequentTravellersList.setLayoutManager(linearLayoutManager);
        travellerListAdapter = new TravellerListAdapter(MyFrequentPassengersActivity.this, new TravellerListAdapter.TravellerInfoItemListener() {
            @Override
            public void setTravellerInfoItemListener(TravellerBean travellerInfo) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("travellerBean", travellerInfo);
                Intent mIntent = new Intent();
                mIntent.putExtras(bundle);
                mIntent.setClass(MyFrequentPassengersActivity.this, AddTravellerInfoActivity.class);
                startActivityForResult(mIntent, ADD_TRAVELLER_REQUEST_CODE);
            }

            @Override
            public void setTravellerInfoDeleteListener(int travellerId) {

                oprateTips(travellerId);
            }
        });
        travellerListAdapter.setTravellerInfoList(mTravellerList);
        mRvFrequentTravellersList.setAdapter(travellerListAdapter);
    }

    @Override
    public void initData() {

        getTravellerList();

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPage = 1;
                        getTravellerList();

                        isOnLoadMore = false;
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
                            if (mTotalCount == mTravellerList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                getTravellerList();
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

        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.llyt_add_traveller})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llyt_add_traveller:

                toActivityForResult(AddTravellerInfoActivity.class, ADD_TRAVELLER_REQUEST_CODE);

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TRAVELLER_REQUEST_CODE && resultCode == RESULT_OK) {
            mTravellerList.clear();
            travellerListAdapter.notifyDataSetChanged();
            mPage = 1;
            getTravellerList();
        }
    }

    @Override
    public void getUserTravellerListResult(BackResult<TravellerInfoResponse> res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                if (isOnLoadMore) {

                    mSmartRefreshLayout.finishLoadMore();
                } else {

                    mTravellerList.clear();
                    travellerListAdapter.notifyDataSetChanged();
                    mSmartRefreshLayout.finishRefresh();
                    mSmartRefreshLayout.setNoMoreData(false);
                }
                TravellerInfoResponse travellerInfoResponse = res.getData();
                List<TravellerBean> travellerList = travellerInfoResponse.getResult();
                BasePaginationResult paginationResult = travellerInfoResponse.getPage();
                mTotalCount = paginationResult.getPageCount();
                if (travellerList != null) {
                    mTravellerList.addAll(travellerList);
                }
                travellerListAdapter.setTravellerInfoList(travellerList);
                travellerListAdapter.notifyDataSetChanged();
                break;
            default:
                mSmartRefreshLayout.finishRefresh();
                showToast(MyFrequentPassengersActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void addUserTravellerResult(BackResult res) {

    }

    @Override
    public void updateUserTravellerResult(BackResult res) {

    }

    @Override
    public void getUserContactsListResult(BackResult<ContactsInfoResponse> res) {

    }

    @Override
    public void addUserContactsResult(BackResult res) {

    }

    @Override
    public void updateUserContactsResult(BackResult res) {

    }

    @Override
    public void deleteUserContactsResult(BackResult res) {

    }

    @Override
    public void getRecipientsInfoListResult(BackResult<RecipientsInfoResponse> res) {

    }

    @Override
    public void getRecipientsAddressResult(BackResult<List<RecipientAddressResponse>> res) {

    }

    @Override
    public void deleteUserTravellerResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                mTravellerList.clear();
                travellerListAdapter.notifyDataSetChanged();
                mPage = 1;
                getTravellerList();
                break;
            default:
                showToast(MyFrequentPassengersActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(MyFrequentPassengersActivity.this, Constants.getResultMsg(msg));
    }

    //获取旅客列表
    public void getTravellerList() {

        if (validateInternet()) {
            userId = getSharedPreferencesUserId();
            mPresenter.getUserTravellerList(userId, mPage, mPageSize);
        }
    }

    //删除旅客
    public void deleteTraveller(int travellerId) {
        if (validateInternet()) {
            showProgressDialog(MyFrequentPassengersActivity.this);
            mDialog.setTitle("");
            DeleteTravellerInfoRequest deleteTravellerInfoRequest = new DeleteTravellerInfoRequest();
            deleteTravellerInfoRequest.setId(travellerId);
            mPresenter.deleteUserTraveller(deleteTravellerInfoRequest);
        }
    }

    public void oprateTips(int travellerId) {

        OprateDialog oprateDialog = new OprateDialog(MyFrequentPassengersActivity.this).builder().setTitle("确认要删除旅客吗？");
        oprateDialog.setNegativeButton(getResources().getString(R.string.str_cancel), new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        oprateDialog.setPositiveButton(getResources().getString(R.string.str_delete), getResources().getColor(R.color.color_blue2), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTraveller(travellerId);
            }
        });

        oprateDialog.show();
    }
}
