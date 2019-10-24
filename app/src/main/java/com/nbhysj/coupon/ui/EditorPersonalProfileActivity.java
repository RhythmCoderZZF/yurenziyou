package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.UserInfoModel;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MyCardResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.presenter.UserInfoPresenter;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/08
 * description：编辑个人简介
 */
public class EditorPersonalProfileActivity extends BaseActivity<UserInfoPresenter, UserInfoModel> implements UserInfoContract.View {

    //个人简介限制长度
    @BindView(R.id.tv_profile_length)
    TextView mTvProfileLength;
    //简介输入
    @BindView(R.id.edt_profile)
    EditText mEdtPersonalProfile;
    //保存
    @BindView(R.id.tv_save)
    TextView mTvSave;
    //简介
    private String personalProfile;

    @Override
    public int getLayoutId() {
        return R.layout.activity_editor_personal_profile;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        userId = getSharedPreferencesUserId();
        ToolbarHelper.setBar(EditorPersonalProfileActivity.this, getResources().getString(R.string.str_edit_personal_profile), R.mipmap.nav_ico_back_black);
    }

    @Override
    public void initData() {
        String profile = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_PROFILE, "");

        if (!TextUtils.isEmpty(profile)) {

            mEdtPersonalProfile.setText(profile);
            mTvProfileLength.setText(profile.length() + "/50");
            checkIsInputFill();
        }
        mEdtPersonalProfile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int profileLength = charSequence.length();
                mTvProfileLength.setText(profileLength + "/ 50");
                checkIsInputFill();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getUserInfoResult(BackResult<UserInfoResponse> res) {

    }


    @Override
    public void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res) {

    }

    @Override
    public void updateInformationResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_PROFILE, personalProfile);
                    showToast(EditorPersonalProfileActivity.this, getResources().getString(R.string.str_save_success));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(EditorPersonalProfileActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getMyCardResult(BackResult<MyCardResponse> res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(EditorPersonalProfileActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.tv_save})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_save:
                if (validateInternet()) {
                    personalProfile = mEdtPersonalProfile.getText().toString().trim();
                    if (TextUtils.isEmpty(personalProfile)) {

                        showToast(EditorPersonalProfileActivity.this, getResources().getString(R.string.str_input_personal_profile));
                        return;
                    }

                    updatePersonalProfile(personalProfile);
                }
                break;
            default:
                break;
        }
    }

    //修改个人简介
    public void updatePersonalProfile(String profile) {

        if (validateInternet()) {

            UpdateUserInfoRequest updateUserInfoRequest = new UpdateUserInfoRequest();
            updateUserInfoRequest.setId(userId);
            updateUserInfoRequest.setProfile(profile);
            showProgressDialog(EditorPersonalProfileActivity.this);
            mDialog.setTitle("");
            mPresenter.updateInformation(updateUserInfoRequest);
        }
    }

    @Override
    public void userLogoutResult(BackResult res) {

    }

    //校验输入信息是否填写完成
    public void checkIsInputFill() {

        personalProfile = mEdtPersonalProfile.getText().toString().trim();
        if (TextUtils.isEmpty(personalProfile)) {

            mTvSave.setBackgroundResource(R.drawable.bg_rect_gray_shape);
        } else {
            mTvSave.setBackgroundResource(R.drawable.btn_oprate_bg);
        }
    }
}
