package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.RecipientListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.FrequentlyUsedInfoContract;
import com.nbhysj.coupon.model.FrequentlyUsedInfoModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ContactsInfoResponse;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.model.response.RecipientsInfoResponse;
import com.nbhysj.coupon.model.response.TravellerBean;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;
import com.nbhysj.coupon.presenter.FrequentlyUsedInfoPresenter;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/02
 * description: 收件人列表
 */
public class RecipientListActivity extends BaseActivity<FrequentlyUsedInfoPresenter, FrequentlyUsedInfoModel> implements FrequentlyUsedInfoContract.View {
    //收件人列表
    @BindView(R.id.rv_recipient)
    RecyclerView mRvRecipicentList;
    private List<TravellerBean> mRecipientsList;
    private RecipientListAdapter recipientListAdapter;
    private int ADD_TRAVELLER_REQUEST_CODE = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recipient_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(RecipientListActivity.this, getResources().getString(R.string.str_recipient_list), R.mipmap.nav_ico_back_black);

        if (mRecipientsList == null) {
            mRecipientsList = new ArrayList<>();
        } else {
            mRecipientsList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecipientListActivity.this);
        mRvRecipicentList.setLayoutManager(linearLayoutManager);
        /*recipientListAdapter = new RecipientListAdapter(RecipientListActivity.this, new TravellerListAdapter.TravellerInfoItemListener() {
            @Override
            public void setTravellerInfoItemListener(TravellerBean travellerInfo) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("travellerBean", travellerInfo);
                Intent mIntent = new Intent();
                mIntent.putExtras(bundle);
                mIntent.setClass(RecipientListActivity.this, AddTravellerInfoActivity.class);
                startActivityForResult(mIntent, ADD_TRAVELLER_REQUEST_CODE);
            }

            @Override
            public void setTravellerInfoDeleteListener(int travellerId) {

                oprateTips(travellerId);
            }
        });*/
        recipientListAdapter.setRecipientInfoList(mRecipientsList);
        mRvRecipicentList.setAdapter(recipientListAdapter);
    }

    @Override
    public void initData() {

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

    }

    @Override
    public void getRecipientsAddressResult(BackResult<List<RecipientAddressResponse>> res) {

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


}
