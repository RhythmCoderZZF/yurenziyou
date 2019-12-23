package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ContactsListAdapter;
import com.nbhysj.coupon.adapter.TravellerListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.FrequentlyUsedInfoContract;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.model.FrequentlyUsedInfoModel;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.ContactsBean;
import com.nbhysj.coupon.model.response.ContactsInfoResponse;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.model.response.RecipientsInfoResponse;
import com.nbhysj.coupon.model.response.TravellerBean;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;
import com.nbhysj.coupon.presenter.FrequentlyUsedInfoPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
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
 * @auther：hysj created on 2019/03/15
 * description：常用联系人列表
 */
public class FrequentContactsActivity extends BaseActivity<FrequentlyUsedInfoPresenter, FrequentlyUsedInfoModel> implements FrequentlyUsedInfoContract.View {
    //旅客列表
    @BindView(R.id.rv_frequent_contacts)
    RecyclerView mRvFrequentContactsList;
    @BindView(R.id.llyt_add_contacts)
    LinearLayout mLlytAddConacts;
    private int ADD_TRAVELLER_REQUEST_CODE = 0;
    //总数
    private int mTotalCount;
    private List<ContactsBean> mTravellerList;
    private ContactsListAdapter contactsListAdapter;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_my_frequent_contacts;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(FrequentContactsActivity.this, getResources().getString(R.string.str_common_contacts), R.mipmap.nav_ico_back_black);
        if (mTravellerList == null) {
            mTravellerList = new ArrayList<>();
        } else {
            mTravellerList.clear();
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FrequentContactsActivity.this);
        mRvFrequentContactsList.setLayoutManager(linearLayoutManager);
        contactsListAdapter = new ContactsListAdapter(FrequentContactsActivity.this, new ContactsListAdapter.ContactsInfoItemListener() {
            @Override
            public void setContactsInfoItemListener(ContactsBean contactsBean) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("contactBean", contactsBean);
                Intent mIntent = new Intent();
                mIntent.putExtras(bundle);
                mIntent.setClass(FrequentContactsActivity.this, AddFrequentContactsActivity.class);
                startActivityForResult(mIntent, ADD_TRAVELLER_REQUEST_CODE);
            }

            @Override
            public void setContactsInfoDeleteListener(int travellerId) {

                oprateTips(travellerId);
            }
        });
        contactsListAdapter.setContactsInfoList(mTravellerList);
        mRvFrequentContactsList.setAdapter(contactsListAdapter);
    }

    @Override
    public void initData() {

        getContactsList();
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.llyt_add_contacts})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llyt_add_contacts:

                toActivityForResult(AddFrequentContactsActivity.class, ADD_TRAVELLER_REQUEST_CODE);

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
            contactsListAdapter.notifyDataSetChanged();
            getContactsList();
        }
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
    public void getRecipientsInfoListResult(BackResult<RecipientsInfoResponse> res) {

    }

    @Override
    public void addRecipientsInfoResult(BackResult res) {

    }

    @Override
    public void updateRecipientsInfoResult(BackResult res) {

    }

    @Override
    public void deleteRecipientsResult(BackResult res) {

    }

    @Override
    public void getUserContactsListResult(BackResult<ContactsInfoResponse> res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:

                ContactsInfoResponse contactsInfoResponse = res.getData();
                List<ContactsBean> contactsList = contactsInfoResponse.getResult();
                BasePaginationResult paginationResult = contactsInfoResponse.getPage();
                mTotalCount = paginationResult.getPageCount();
                if (mTotalCount < 5) {
                    mLlytAddConacts.setVisibility(View.VISIBLE);
                } else {
                    mLlytAddConacts.setVisibility(View.GONE);
                }
                contactsListAdapter.setContactsInfoList(contactsList);
                contactsListAdapter.notifyDataSetChanged();
                break;
            default:
                showToast(FrequentContactsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void addUserContactsResult(BackResult res) {

    }

    @Override
    public void updateUserContactsResult(BackResult res) {

    }

    @Override
    public void getRecipientsAddressResult(BackResult<List<RecipientAddressResponse>> res) {

    }

    @Override
    public void deleteUserContactsResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                mTravellerList.clear();
                contactsListAdapter.notifyDataSetChanged();
                getContactsList();
                break;
            default:
                showToast(FrequentContactsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(FrequentContactsActivity.this, Constants.getResultMsg(msg));
    }

    //获取联系人列表
    public void getContactsList() {

        if (validateInternet()) {
            userId = getSharedPreferencesUserId();
            mPresenter.getUserContactsList(userId);
        }
    }

    //删除旅客
    public void deleteContacts(int travellerId) {
        if (validateInternet()) {
            showProgressDialog(FrequentContactsActivity.this);
            mDialog.setTitle("");
            DeleteTravellerInfoRequest deleteTravellerInfoRequest = new DeleteTravellerInfoRequest();
            deleteTravellerInfoRequest.setId(travellerId);
            mPresenter.deleteUserContacts(deleteTravellerInfoRequest);
        }
    }

    public void oprateTips(int travellerId) {

        OprateDialog oprateDialog = new OprateDialog(FrequentContactsActivity.this).builder().setTitle("确认要删除联系人吗？");
        oprateDialog.setNegativeButton(getResources().getString(R.string.str_cancel), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        oprateDialog.setPositiveButton(getResources().getString(R.string.str_delete), getResources().getColor(R.color.color_blue2), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteContacts(travellerId);
            }
        });

        oprateDialog.show();
    }
}