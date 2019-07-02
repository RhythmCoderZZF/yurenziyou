package com.nbhysj.coupon.ui;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.FrequentlyUsedInfoContract;
import com.nbhysj.coupon.model.FrequentlyUsedInfoModel;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ContactsInfoResponse;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.model.response.RecipientsInfoResponse;
import com.nbhysj.coupon.model.response.TravellerBean;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;
import com.nbhysj.coupon.presenter.FrequentlyUsedInfoPresenter;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.widget.ToggleButton;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/10
 * description：添加和修改旅客信息
 */
public class AddTravellerInfoActivity extends BaseActivity<FrequentlyUsedInfoPresenter, FrequentlyUsedInfoModel> implements FrequentlyUsedInfoContract.View {

    //中文名字
    @BindView(R.id.edt_chinese_name)
    EditText mEdtChineseName;
    //手机号码
    @BindView(R.id.edt_phone)
    EditText mEdtPhone;
    //身份证号码
    @BindView(R.id.edt_identity_card_number)
    EditText mEdtIdentityCardNumber;
    //是否设置为本人
    @BindView(R.id.btn_toggle_set_up_as_self)
    ToggleButton mBtnToggleSetUpAsSelf;
    //保存
    @BindView(R.id.tv_save)
    TextView mTvSave;
    //是否设置本人
    private int mSelfStatus = 0;  //0:非本人 1:设置为本人

    private TravellerBean travellerBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_adding_passenger_information;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(AddTravellerInfoActivity.this, getResources().getString(R.string.str_adding_passenger_information), R.mipmap.nav_ico_back_black);
        userId = getSharedPreferencesUserId();
        travellerBean = (TravellerBean) getIntent().getSerializableExtra("travellerBean");
        if (travellerBean != null) {
            String realName = travellerBean.getRealname();
            String mobile = travellerBean.getMobile();
            String identityNo = travellerBean.getIdentityNo();
            int selfStatus = travellerBean.getSelfStatus();
            mEdtChineseName.setText(realName);
            mEdtPhone.setText(mobile);
            mEdtIdentityCardNumber.setText(identityNo);
            if (selfStatus == 0) {
                mBtnToggleSetUpAsSelf.setToggleOff();
            } else if (selfStatus == 1) {
                mBtnToggleSetUpAsSelf.setToggleOn();
            }
            mSelfStatus = selfStatus;
        }
    }

    @Override
    public void initData() {

        mBtnToggleSetUpAsSelf.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean isOnToggleStatus) {

                if (isOnToggleStatus) {
                    mSelfStatus = 1;
                } else {
                    mSelfStatus = 0;
                }
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getUserTravellerListResult(BackResult<TravellerInfoResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    showToast(AddTravellerInfoActivity.this, getResources().getString(R.string.str_save_success));
                    setResult(RESULT_OK);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(AddTravellerInfoActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void addUserTravellerResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    showToast(AddTravellerInfoActivity.this, getResources().getString(R.string.str_save_success));
                    setResult(RESULT_OK);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(AddTravellerInfoActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void updateUserTravellerResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    showToast(AddTravellerInfoActivity.this, getResources().getString(R.string.str_update_success));
                    setResult(RESULT_OK);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(AddTravellerInfoActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void deleteUserTravellerResult(BackResult res) {

    }

    @Override
    public void getRecipientsInfoListResult(BackResult<RecipientsInfoResponse> res) {

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

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(AddTravellerInfoActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.tv_save})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_save:
                AddTravellerInfo();
                break;
            default:
                break;
        }
    }

    //添加旅客
    public void AddTravellerInfo() {

        if (validateInternet()) {

            String chineseName = mEdtChineseName.getText().toString().trim();
            String phoneNum = mEdtPhone.getText().toString().trim();
            String identityCardNumber = mEdtIdentityCardNumber.getText().toString().trim();

            //请填写中文名
            if (TextUtils.isEmpty(chineseName)) {

                showToast(AddTravellerInfoActivity.this, getResources().getString(R.string.str_input_chinese_name));
                return;
            }
            //请填写手机号
            if (TextUtils.isEmpty(phoneNum)) {

                showToast(AddTravellerInfoActivity.this, getResources().getString(R.string.str_input_phone));
                return;
            }

            //请填写证件号码
            if (TextUtils.isEmpty(identityCardNumber)) {

                showToast(AddTravellerInfoActivity.this, getResources().getString(R.string.str_input_identification_number));
                return;
            }

            TravellerInfoRequest addTravellerRequest = new TravellerInfoRequest();
            addTravellerRequest.setUserId(userId);
            addTravellerRequest.setRealname(chineseName);
            addTravellerRequest.setIdentityNo(identityCardNumber);
            addTravellerRequest.setMobile(phoneNum);
            addTravellerRequest.setSelfStatus(mSelfStatus);
            addTravellerRequest.setIdentityType("身份证");
            showProgressDialog(AddTravellerInfoActivity.this);
            mDialog.setTitle(getResources().getString(R.string.str_saving));

            if (travellerBean == null) {
                mPresenter.addUserTraveller(addTravellerRequest);
            } else {
                addTravellerRequest.setId(travellerBean.getId());
                mPresenter.updateUserTraveller(addTravellerRequest);
            }
        }
    }
}
