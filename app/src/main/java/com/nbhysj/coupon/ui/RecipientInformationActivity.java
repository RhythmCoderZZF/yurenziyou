package com.nbhysj.coupon.ui;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.DeliveryAddressListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.FrequentlyUsedInfoContract;
import com.nbhysj.coupon.dialog.SelectAddressDialog;
import com.nbhysj.coupon.model.FrequentlyUsedInfoModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ContactsInfoResponse;
import com.nbhysj.coupon.model.response.DeliveryAddressTagBean;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.model.response.RecipientsInfoResponse;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;
import com.nbhysj.coupon.presenter.FrequentlyUsedInfoPresenter;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/02
 * description：收件人信息
 */
public class RecipientInformationActivity extends BaseActivity<FrequentlyUsedInfoPresenter, FrequentlyUsedInfoModel> implements FrequentlyUsedInfoContract.View {

    private String provinceCode;
    private String cityCode;
    private String areaCode;
    @BindView(R.id.tv_area)
    TextView mTvArea;
    //收件人标签
    @BindView(R.id.rv_delivery_address_tag)
    RecyclerView mRvDeliveryAddressTag;
    private List<DeliveryAddressTagBean> deliveryAddressTagList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recipient_information;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(RecipientInformationActivity.this, getResources().getString(R.string.str_recipient_information), R.mipmap.nav_ico_back_black, getResources().getString(R.string.str_save));
    }

    @Override
    public void initData() {
        if (deliveryAddressTagList == null) {

            deliveryAddressTagList = new ArrayList<>();
        } else {
            deliveryAddressTagList.clear();
        }

        DeliveryAddressTagBean homeTagBean = new DeliveryAddressTagBean();
        homeTagBean.setDeliveryAddressTag("家");

        DeliveryAddressTagBean companyTagBean = new DeliveryAddressTagBean();
        companyTagBean.setDeliveryAddressTag("公司");

        DeliveryAddressTagBean schoolBean = new DeliveryAddressTagBean();
        schoolBean.setDeliveryAddressTag("学校");

        deliveryAddressTagList.add(homeTagBean);
        deliveryAddressTagList.add(companyTagBean);
        deliveryAddressTagList.add(schoolBean);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecipientInformationActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvDeliveryAddressTag.setLayoutManager(linearLayoutManager);

        DeliveryAddressListAdapter deliveryAddressListAdapter = new DeliveryAddressListAdapter(RecipientInformationActivity.this);
        deliveryAddressListAdapter.setDeliveryAddressTagList(deliveryAddressTagList);
        mRvDeliveryAddressTag.setAdapter(deliveryAddressListAdapter);
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.rlyt_regional_choice})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlyt_regional_choice:
                showProgressDialog(RecipientInformationActivity.this);
                mDialog.setTitle("");
                getRecipientsAddress();
                break;
            default:
                break;
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
    public void getRecipientsAddressResult(BackResult<List<RecipientAddressResponse>> res) {

        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                List<RecipientAddressResponse> recipientAddressResponseList = res.getData();

                SelectAddressDialog pop = new SelectAddressDialog(recipientAddressResponseList);
                // pop.newInstance(recipientAddressResponseList);
                pop.setSelectAddresFinish(new SelectAddresFinish() {

                    @Override
                    public void finish(String pCode, String cCode, String aCode) {
                        //选中地址完成
                        provinceCode = pCode;
                        cityCode = cCode;
                        areaCode = aCode;
               /* if(StringUtils.isNoEmpty(tCode)){
                    townCode = tCode;
                    String addr = AddressManager.newInstance().getAddress(pCode, cCode, aCode,tCode);
                    adress.setText(addr);
                }else {
                    String addr = AddressManager.newInstance().getAddress(pCode, cCode, aCode);
                    adress.setText(addr);
                }*/

                        //  String addr = AddressManager.newInstance().getAddress(pCode, cCode, aCode);
                        //adress.setText(addr);
                        if (!TextUtils.isEmpty(provinceCode) && TextUtils.isEmpty(cityCode) && TextUtils.isEmpty(areaCode)) {
                            mTvArea.setText(provinceCode);
                        } else if (!TextUtils.isEmpty(provinceCode) && !TextUtils.isEmpty(cityCode) && TextUtils.isEmpty(areaCode)) {
                            mTvArea.setText(provinceCode + cityCode);
                        } else if (!TextUtils.isEmpty(provinceCode) && !TextUtils.isEmpty(cityCode) && !TextUtils.isEmpty(areaCode)) {
                            mTvArea.setText(provinceCode + cityCode + areaCode);
                        }
                    }

                });

                pop.show(getFragmentManager(), "address");
                break;
            default:
                showToast(RecipientInformationActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getRecipientsInfoListResult(BackResult<RecipientsInfoResponse> res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(RecipientInformationActivity.this, Constants.getResultMsg(msg));
    }

    public interface SelectAddresFinish {
        void finish(String provinceName, String cityName, String areaName);
    }

    public void getRecipientsAddress() {

        if (validateInternet()) {

            mPresenter.getRecipientsAddress();
        }
    }
}
