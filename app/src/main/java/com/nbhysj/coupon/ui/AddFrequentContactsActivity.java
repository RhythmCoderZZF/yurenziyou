package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.FrequentlyUsedInfoContract;
import com.nbhysj.coupon.model.FrequentlyUsedInfoModel;
import com.nbhysj.coupon.model.request.ContactsInfoRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ContactsBean;
import com.nbhysj.coupon.model.response.ContactsInfoResponse;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.model.response.RecipientsInfoResponse;
import com.nbhysj.coupon.model.response.TravellerBean;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;
import com.nbhysj.coupon.presenter.FrequentlyUsedInfoPresenter;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/08
 * description：添加常用联系人信息
 */
public class AddFrequentContactsActivity extends BaseActivity<FrequentlyUsedInfoPresenter, FrequentlyUsedInfoModel> implements FrequentlyUsedInfoContract.View {

    //真实姓名
    @BindView(R.id.edt_realname)
    EditText mEdtRealname;
    //微信
    @BindView(R.id.edt_wechat)
    EditText mEdtWechat;
    //手机号
    @BindView(R.id.edt_phone)
    EditText mEdtPhone;
    //邮箱号
    @BindView(R.id.edt_email_number)
    EditText mEdtEmailNumber;

    private ContactsBean contactsBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_frequent_contacts;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(AddFrequentContactsActivity.this, getResources().getString(R.string.str_add_common_contacts), R.mipmap.nav_ico_back_black, getResources().getString(R.string.str_save));
    }

    @Override
    public void initData() {
        userId = getSharedPreferencesUserId();
        contactsBean = (ContactsBean) getIntent().getSerializableExtra("contactBean");
        if (contactsBean != null) {
            String realName = contactsBean.getRealname();
            String mobile = contactsBean.getMobile();
            String wechatNo = contactsBean.getWechat();
            String emailNo = contactsBean.getEmail();
            mEdtRealname.setText(realName);
            mEdtPhone.setText(mobile);
            mEdtWechat.setText(wechatNo);
            mEdtEmailNumber.setText(emailNo);
        }
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
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
    public void getRecipientsAddressResult(BackResult<List<RecipientAddressResponse>> res) {

    }

    @Override
    public void addUserContactsResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    showToast(AddFrequentContactsActivity.this, getResources().getString(R.string.str_save_success));
                    setResult(RESULT_OK);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(AddFrequentContactsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void updateUserContactsResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    showToast(AddFrequentContactsActivity.this, getResources().getString(R.string.str_save_success));
                    setResult(RESULT_OK);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(AddFrequentContactsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void deleteUserContactsResult(BackResult res) {

    }

    @Override
    public void getRecipientsInfoListResult(BackResult<RecipientsInfoResponse> res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(AddFrequentContactsActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.tv_toolbar_right})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_toolbar_right:
                AddTravellerInfo();
                break;
            default:
                break;
        }
    }

    //添加旅客
    public void AddTravellerInfo() {

        if (validateInternet()) {

            String mRealname = mEdtRealname.getText().toString().trim();
            String phoneNum = mEdtPhone.getText().toString().trim();
            String wechatNum = mEdtWechat.getText().toString().trim();
            String emailNum = mEdtEmailNumber.getText().toString().trim();

            //请填写真实
            if (TextUtils.isEmpty(mRealname)) {

                showToast(AddFrequentContactsActivity.this, getResources().getString(R.string.str_input_realname));
                return;
            }

            //请填写微信号
            if (TextUtils.isEmpty(wechatNum)) {

                showToast(AddFrequentContactsActivity.this, getResources().getString(R.string.str_input_wechat_number));
                return;
            }
            //请填写手机号
            if (TextUtils.isEmpty(phoneNum)) {

                showToast(AddFrequentContactsActivity.this, getResources().getString(R.string.str_input_phone));
                return;
            }

            //请填写邮箱
            if (TextUtils.isEmpty(emailNum)) {

                showToast(AddFrequentContactsActivity.this, getResources().getString(R.string.str_input_email));
                return;
            }

            ContactsInfoRequest contactsInfoRequest = new ContactsInfoRequest();
            contactsInfoRequest.setUserId(userId);
            contactsInfoRequest.setRealname(mRealname);
            contactsInfoRequest.setMobile(phoneNum);
            contactsInfoRequest.setWechat(wechatNum);
            contactsInfoRequest.setEmail(emailNum);
            showProgressDialog(AddFrequentContactsActivity.this);
            mDialog.setTitle(getResources().getString(R.string.str_saving));

            if (contactsBean == null) {
                //新增联系人
                mPresenter.addUserContacts(contactsInfoRequest);
            } else {
                //修改联系人
                contactsInfoRequest.setId(contactsBean.getId());
                mPresenter.updateUserContacts(contactsInfoRequest);
            }
        }
    }
}
