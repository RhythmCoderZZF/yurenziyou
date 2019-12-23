package com.nbhysj.coupon.ui;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.FrequentlyUsedInfoContract;
import com.nbhysj.coupon.dialog.SelectAddressDialog;
import com.nbhysj.coupon.model.FrequentlyUsedInfoModel;
import com.nbhysj.coupon.model.request.RecipientsInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ContactsInfoResponse;
import com.nbhysj.coupon.model.response.DeliveryAddressTagBean;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.model.response.RecipientsBean;
import com.nbhysj.coupon.model.response.RecipientsInfoResponse;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;
import com.nbhysj.coupon.presenter.FrequentlyUsedInfoPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.widget.ToggleButton;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/02
 * description：收件人信息
 */
public class AddRecipientInfoActivity extends BaseActivity<FrequentlyUsedInfoPresenter, FrequentlyUsedInfoModel> implements FrequentlyUsedInfoContract.View {

    private String provinceCode;
    private String cityCode;
    private String areaCode;
    @BindView(R.id.tv_area)
    TextView mTvArea;
    //收货人
    @BindView(R.id.edt_recipient_info)
    EditText mEdtRecipientInfo;
    //收件人号码
    @BindView(R.id.edt_recipient_phone)
    EditText mEdtRecipientPhone;
    //收件人标签
    @BindView(R.id.flowlayout_address_label)
    TagFlowLayout mTagFlowDeliveryAddressLabel;
    //地址详情
    @BindView(R.id.edt_address_detail)
    EditText mEdtAddressDetail;
    @BindView(R.id.btn_toggle_default_address_setting)
    ToggleButton mToggleBtnDefaultAddressSetting;
    @BindView(R.id.tv_toolbar_right)
    TextView mTvToolbarRight;

    private List<DeliveryAddressTagBean> deliveryAddressTagList;

    private RecipientsBean recipientsBean;

    private TagAdapter tagAdapter;

    //1设为默认0否
    private int defaultStatus;

    //标签
    private String addressTag;
    private int userId;

    private int tagIndex;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_add_recipient_information;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(AddRecipientInfoActivity.this, getResources().getString(R.string.str_recipient_information), R.mipmap.icon_left_arrow_black, getResources().getString(R.string.str_save));
    }

    @Override
    public void initData() {
        userId = getSharedPreferencesUserId();
        recipientsBean = (RecipientsBean) getIntent().getSerializableExtra("recipientsBean");
        if (recipientsBean != null) {
            String realName = recipientsBean.getConsignee();
            String mobile = recipientsBean.getMobile();
            String address = recipientsBean.getAddress();
            provinceCode = recipientsBean.getProvince();
            cityCode = recipientsBean.getCity();
            areaCode = recipientsBean.getCounty();
            defaultStatus = recipientsBean.getDefaultStatus();
            mEdtRecipientInfo.setText(realName);
            mEdtRecipientPhone.setText(mobile);
            mTvArea.setText(provinceCode + cityCode + areaCode);
            mEdtAddressDetail.setText(address);
            if(defaultStatus == 1) {
                mToggleBtnDefaultAddressSetting.setToggleOn();
            } else if(defaultStatus == 0){
                mToggleBtnDefaultAddressSetting.setToggleOff();
            }
            String tag = recipientsBean.getTag();
            if(tag.equals("家")){
                tagIndex = 0;
                addressTag = "家";
            } else if(tag.equals("公司")){
                tagIndex = 1;
                addressTag = "公司";
            } else if(tag.equals("学校")){
                tagIndex = 2;
                addressTag = "学校";
            }
            mTvToolbarRight.setText(getResources().getString(R.string.str_save));
        } else {
            mTvToolbarRight.setText(getResources().getString(R.string.str_add));
        }
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
        addressTag = deliveryAddressTagList.get(0).getDeliveryAddressTag();
        tagAdapter = new TagAdapter<DeliveryAddressTagBean>(deliveryAddressTagList) {
            @Override
            public View getView(FlowLayout parent, int position, DeliveryAddressTagBean option) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_post_topic,
                        mTagFlowDeliveryAddressLabel, false);
                TextView tv = view.findViewById(R.id.tv_flowlayout);
                String deliveryAddressTag = option.getDeliveryAddressTag();
                tv.setText(deliveryAddressTag);
                return view;
            }
        };
        mTagFlowDeliveryAddressLabel.setMaxSelectCount(1);
        mTagFlowDeliveryAddressLabel.setAdapter(tagAdapter);

        mTagFlowDeliveryAddressLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String content = "";
                Set<Integer> selectPosSet = mTagFlowDeliveryAddressLabel.getSelectedList();
                Iterator it = selectPosSet.iterator();
                while (it.hasNext()) {
                    int index = (int) it.next();

                    DeliveryAddressTagBean deliveryAddressTagBean = deliveryAddressTagList.get(index);
                    String address = deliveryAddressTagBean.getDeliveryAddressTag();
                    addressTag = address;
                }
                return true;
            }
        });
        tagAdapter.setSelectedList(tagIndex);

        mToggleBtnDefaultAddressSetting.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean isOnCheck) {

                if(isOnCheck){

                    defaultStatus = 1;

                } else {

                    defaultStatus = 0;

                }
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.rlyt_regional_choice})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlyt_regional_choice:
                showProgressDialog(AddRecipientInfoActivity.this);
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
                showToast(AddRecipientInfoActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getRecipientsInfoListResult(BackResult<RecipientsInfoResponse> res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(AddRecipientInfoActivity.this, Constants.getResultMsg(msg));
    }

    public interface SelectAddresFinish {
        void finish(String provinceName, String cityName, String areaName);
    }

    public void getRecipientsAddress() {

        if (validateInternet()) {

            mPresenter.getRecipientsAddress();
        }
    }

    @Override
    public void addRecipientsInfoResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    showToast(AddRecipientInfoActivity.this, getResources().getString(R.string.str_save_success));
                    setResult(RESULT_OK);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(AddRecipientInfoActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void updateRecipientsInfoResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    showToast(AddRecipientInfoActivity.this, getResources().getString(R.string.str_update_success));
                    setResult(RESULT_OK);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(AddRecipientInfoActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void deleteRecipientsResult(BackResult res) {

    }

    @OnClick({R.id.tv_toolbar_right})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_toolbar_right:
                AddRecipientInfo();
                break;
            default:
                break;
        }
    }

    //添加收件人
    public void AddRecipientInfo() {

        if (validateInternet()) {

            String mRealname = mEdtRecipientInfo.getText().toString().trim();
            String phoneNum = mEdtRecipientPhone.getText().toString().trim();
            String addressDetail = mEdtAddressDetail.getText().toString().trim();
            //请填写真实
            if (TextUtils.isEmpty(mRealname))
            {
                showToast(AddRecipientInfoActivity.this, getResources().getString(R.string.str_input_realname));
                return;
            }

            //请填写手机号
            if (TextUtils.isEmpty(phoneNum))
            {
                showToast(AddRecipientInfoActivity.this, getResources().getString(R.string.str_input_phone));
                return;
            }

            RecipientsInfoRequest recipientsInfoRequest = new RecipientsInfoRequest();
            recipientsInfoRequest.setUserId(userId);
            recipientsInfoRequest.setConsignee(mRealname);
            recipientsInfoRequest.setMobile(phoneNum);
            recipientsInfoRequest.setProvince(provinceCode); //省
            recipientsInfoRequest.setCity(cityCode); //市
            recipientsInfoRequest.setCounty(areaCode);  //区
            recipientsInfoRequest.setDefaultStatus(defaultStatus);
            recipientsInfoRequest.setTag(addressTag);
            recipientsInfoRequest.setAddress(addressDetail);
            showProgressDialog(AddRecipientInfoActivity.this);
            mDialog.setTitle(getResources().getString(R.string.str_saving));

            if (recipientsBean == null) {
                //新增联系人
                mPresenter.addRecipientsInfo(recipientsInfoRequest);
            } else {
                //修改联系人
                recipientsInfoRequest.setId(recipientsBean.getId());
                mPresenter.updateRecipientsInfo(recipientsInfoRequest);
            }
        }
    }
}
