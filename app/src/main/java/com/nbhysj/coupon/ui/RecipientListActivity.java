package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.RecipientListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.FrequentlyUsedInfoContract;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.model.FrequentlyUsedInfoModel;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.ContactsInfoResponse;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.model.response.RecipientsBean;
import com.nbhysj.coupon.model.response.RecipientsInfoResponse;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;
import com.nbhysj.coupon.presenter.FrequentlyUsedInfoPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/02
 * description: 收件人列表
 */
public class RecipientListActivity extends BaseActivity<FrequentlyUsedInfoPresenter, FrequentlyUsedInfoModel> implements FrequentlyUsedInfoContract.View {
    //收件人列表
    @BindView(R.id.rv_recipient)
    RecyclerView mRvRecipicentList;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    private List<RecipientsBean> mRecipientsList;
    private RecipientListAdapter recipientListAdapter;
    private int ADD_RECIPIENT_REQUEST_CODE = 0;
    private int mPage = 1;
    private int mPageSize = 10;
    //总数
    private int mTotalCount;
    private boolean isOnLoadMore = false;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_recipient_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(RecipientListActivity.this, getResources().getString(R.string.str_recipient_list), R.mipmap.icon_left_arrow_black);
        getRecipientList();
        if (mRecipientsList == null) {
            mRecipientsList = new ArrayList<>();
        } else {
            mRecipientsList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecipientListActivity.this);
        mRvRecipicentList.setLayoutManager(linearLayoutManager);
        recipientListAdapter = new RecipientListAdapter(RecipientListActivity.this, new RecipientListAdapter.RecipientInfoItemListener() {
            @Override
            public void setRecipientInfoItemListener(RecipientsBean recipientsBean) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("recipientsBean", recipientsBean);
                Intent mIntent = new Intent();
                mIntent.putExtras(bundle);
                mIntent.setClass(RecipientListActivity.this, AddRecipientInfoActivity.class);
                startActivityForResult(mIntent, ADD_RECIPIENT_REQUEST_CODE);
            }

            @Override
            public void setRecipientInfoDeleteListener(int recipientId) {
                 oprateTips(recipientId);
            }
        });

        recipientListAdapter.setRecipientInfoList(mRecipientsList);
        mRvRecipicentList.setAdapter(recipientListAdapter);
    }

    @Override
    public void initData() {

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPage = 1;
                        getRecipientList();

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
                            if (mTotalCount == mRecipientsList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                getRecipientList();
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
    public void getUserTravellerListResult(BackResult<TravellerInfoResponse> res) {

    }

    @Override
    public void addUserTravellerResult(BackResult res) {

    }

    @Override
    public void updateUserTravellerResult(BackResult res) {

    }

    @Override
    public void deleteUserTravellerResult(BackResult res) {

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
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                if (isOnLoadMore) {

                    mSmartRefreshLayout.finishLoadMore();
                } else {

                    mRecipientsList.clear();
                    recipientListAdapter.notifyDataSetChanged();
                    mSmartRefreshLayout.finishRefresh();
                    mSmartRefreshLayout.setNoMoreData(false);
                }
                RecipientsInfoResponse travellerInfoResponse = res.getData();
                List<RecipientsBean> travellerList = travellerInfoResponse.getResult();
                BasePaginationResult paginationResult = travellerInfoResponse.getPage();
                mTotalCount = paginationResult.getPageCount();
                if (travellerList != null) {
                    mRecipientsList.addAll(travellerList);
                }
                recipientListAdapter.setRecipientInfoList(travellerList);
                recipientListAdapter.notifyDataSetChanged();
                break;
            default:
                mSmartRefreshLayout.finishRefresh();
                showToast(RecipientListActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getRecipientsAddressResult(BackResult<List<RecipientAddressResponse>> res) {

    }

    @Override
    public void addRecipientsInfoResult(BackResult res) {

    }

    @Override
    public void updateRecipientsInfoResult(BackResult res) {

    }

    @Override
    public void deleteRecipientsResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                mRecipientsList.clear();
                recipientListAdapter.notifyDataSetChanged();
                getRecipientList();
                break;
            default:
                showToast(RecipientListActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @OnClick({R.id.llyt_add_recipient})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llyt_add_recipient:

                toActivityForResult(AddRecipientInfoActivity.class, ADD_RECIPIENT_REQUEST_CODE);

                break;
            default:
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(RecipientListActivity.this, Constants.getResultMsg(msg));
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    //获取旅客列表
    public void getRecipientList() {
        if (validateInternet()) {
            userId = getSharedPreferencesUserId();
            String mobile = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_MOBILE, 0);
            mPresenter.getRecipientsInfoList(userId, mobile, mPage, mPageSize);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_RECIPIENT_REQUEST_CODE && resultCode == RESULT_OK) {
            mRecipientsList.clear();
            recipientListAdapter.notifyDataSetChanged();
            mPage = 1;
            getRecipientList();
        }
    }

    public void oprateTips(int recipientId) {

        OprateDialog oprateDialog = new OprateDialog(RecipientListActivity.this).builder().setTitle("确认要删除收件人吗？");
        oprateDialog.setNegativeButton(getResources().getString(R.string.str_cancel), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        oprateDialog.setPositiveButton(getResources().getString(R.string.str_delete), getResources().getColor(R.color.color_blue2), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRecipient(recipientId);
            }
        });
        oprateDialog.show();
    }

    //删除旅客
    public void deleteRecipient(int recipientId) {
        if (validateInternet()) {
            showProgressDialog(RecipientListActivity.this);
            mDialog.setTitle("");
            DeleteTravellerInfoRequest deleteTravellerInfoRequest = new DeleteTravellerInfoRequest();
            deleteTravellerInfoRequest.setId(recipientId);
            mPresenter.deleteRecipientsInfo(deleteTravellerInfoRequest);
        }
    }
}
