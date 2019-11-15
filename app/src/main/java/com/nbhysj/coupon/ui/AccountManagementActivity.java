package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.ThirdPartyLoginTypeEnum;
import com.nbhysj.coupon.contract.AccountManagementContract;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.model.AccountManagementModel;
import com.nbhysj.coupon.model.request.ThirdPartyLoginRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.SerializableThirdPartyMap;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.presenter.AccountmanagementPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.RadiusGradientSpanUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/08
 * description：账号管理
 */
public class AccountManagementActivity extends BaseActivity<AccountmanagementPresenter, AccountManagementModel> implements AccountManagementContract.View {

    //微博账号绑定
    @BindView(R.id.tv_weibo_account)
    TextView mTvWeiboAccountBind;
    //手机号绑定
    @BindView(R.id.tv_bind_phone)
    TextView mTvPhoneAccountBind;
    //邮箱绑定
    @BindView(R.id.tv_bind_email)
    TextView mTvEmailAccountBind;
    //邮箱绑定
    @BindView(R.id.tv_wechat_account_bind)
    TextView mTvWechatAccountBind;
    //邮箱绑定
    @BindView(R.id.tv_qq_account_bind)
    TextView mTvQQAccountBind;

    private ThirdPartyLoginStatusResponse thirdPartyLoginStatusResponse;

    //等三方登录类型入口(1.QQ 2.wechat 3.微博)
    private String mThirdPartyLoginType;

    //第三方信息Map
    Map<String, String> thirdPartyData;

    //第三方绑定请求code
    private int THIRD_PARTY_LOGIN_REQUEST_CODE = 0;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_account_management;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(AccountManagementActivity.this, getResources().getString(R.string.str_account_management), R.mipmap.nav_ico_back_black);
        // mTvAccount.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(getResources().getString(R.string.str_bind_immediately),0xFF1DEB96, 0xFF0DDDF6));
    }

    @Override
    public void initData() {
        thirdPartyLoginStatusResponse = (ThirdPartyLoginStatusResponse) getIntent().getSerializableExtra("thirdPartyLoginStatusResponse");
        // System.out.print(thirdPartyLoginStatusResponse);
        boolean isMobileBind = thirdPartyLoginStatusResponse.isMOBILE();
        boolean isQQBind = thirdPartyLoginStatusResponse.isQQ();
        boolean isEmailBind = thirdPartyLoginStatusResponse.isEMAIL();
        boolean isWechatBind = thirdPartyLoginStatusResponse.isWECHAT();
        boolean isWeiboBind = thirdPartyLoginStatusResponse.isWEIBO();

        //是否手机绑定
        if (!isMobileBind) {

            mTvPhoneAccountBind.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(getResources().getString(R.string.str_bind_immediately), 0xFF1DEB96, 0xFF0DDDF6));
        } else {
            mTvPhoneAccountBind.setText(getResources().getString(R.string.str_bound));
            mTvPhoneAccountBind.setTextColor(getResources().getColor(R.color.button_grey));
        }
        //是否qq绑定
        if (!isQQBind) {

            mTvQQAccountBind.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(getResources().getString(R.string.str_bind_immediately), 0xFF1DEB96, 0xFF0DDDF6));
        } else {
            mTvQQAccountBind.setText(getResources().getString(R.string.str_bound));
            mTvQQAccountBind.setTextColor(getResources().getColor(R.color.button_grey));
        }
        //是否邮箱绑定
        if (!isEmailBind) {

            mTvEmailAccountBind.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(getResources().getString(R.string.str_bind_immediately), 0xFF1DEB96, 0xFF0DDDF6));
        } else {
            mTvEmailAccountBind.setText(getResources().getString(R.string.str_bound));
            mTvEmailAccountBind.setTextColor(getResources().getColor(R.color.button_grey));
        }
        //是否微信绑定
        if (!isWechatBind) {

            mTvWechatAccountBind.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(getResources().getString(R.string.str_bind_immediately), 0xFF1DEB96, 0xFF0DDDF6));
        } else {
            mTvWechatAccountBind.setText(getResources().getString(R.string.str_bound));
            mTvWechatAccountBind.setTextColor(getResources().getColor(R.color.button_grey));
        }
        //是否微博绑定
        if (!isWeiboBind) {

            mTvWeiboAccountBind.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(getResources().getString(R.string.str_bind_immediately), 0xFF1DEB96, 0xFF0DDDF6));
        } else {
            mTvWeiboAccountBind.setText(getResources().getString(R.string.str_bound));
            mTvWeiboAccountBind.setTextColor(getResources().getColor(R.color.button_grey));
        }
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.rlyt_logout, R.id.rlyt_update_password, R.id.tv_qq_account_bind,R.id.tv_wechat_account_bind})
    public void onclick(View view) {
        {
            switch (view.getId()) {
                case R.id.rlyt_logout:

                    toActivityWithFinish(LoginActivity.class, null);

                    break;
                case R.id.rlyt_update_password:

                    toActivity(UpdatePasswordActivity.class);

                    break;
                case R.id.tv_qq_account_bind:
                    //QQ绑定
                    mThirdPartyLoginType = ThirdPartyLoginTypeEnum.QQ.getValue();
                    oprateTips(getResources().getString(R.string.str_qq), SHARE_MEDIA.QQ);

                    break;
                case R.id.tv_wechat_account_bind:
                    //微信绑定
                    mThirdPartyLoginType = ThirdPartyLoginTypeEnum.WECHAT.getValue();
                    oprateTips(getResources().getString(R.string.str_wechat), SHARE_MEDIA.WEIXIN);

                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res) {

    }

    @Override
    public void getUserInfoResult(BackResult<UserInfoResponse> res) {

    }

    @Override
    public void getThirdPartyLoginCreateUserResult(BackResult res) {

    }

    @Override
    public void getRegisterVerifyCodeResult(BackResult res) {

    }

    @Override
    public void thirdPartyLoginResult(BackResult<LoginResponse> res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.THIRD_PARTY_LOGIN_FOR_UNBOUND_CODE://未绑定手机号
                dismissProgressDialog();
                SerializableThirdPartyMap thirdPartyMap = new SerializableThirdPartyMap();
                thirdPartyMap.setMap(thirdPartyData);
                Bundle bundle = new Bundle();
                Intent intent = new Intent();
                intent.setClass(AccountManagementActivity.this, BindPhoneActivity.class);
                bundle.putSerializable("thirdPartyMap", thirdPartyMap);
                bundle.putString("thirdPartyLoginType", mThirdPartyLoginType);
                bundle.putInt("bind", 0);
                intent.putExtras(bundle);
                startActivityForResult(intent, THIRD_PARTY_LOGIN_REQUEST_CODE);
                break;
            default:
                dismissProgressDialog();
                showToast(AccountManagementActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(AccountManagementActivity.this, Constants.getResultMsg(msg));
    }

    public void getThirdPartyLoginStatus() {

        if (validateInternet()) {
            userId = getSharedPreferencesUserId();
            mPresenter.getThirdPartyLoginStatus(userId);
        }
    }

    public void oprateTips(String platform, SHARE_MEDIA share_media) {

        OprateDialog oprateDialog = new OprateDialog(AccountManagementActivity.this).builder().setTitle("鱼人自游想要打开" + platform);
        oprateDialog.setNegativeButton(getResources().getString(R.string.str_cancel), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        oprateDialog.setPositiveButton("打开", getResources().getColor(R.color.color_blue2), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMShareAPI.get(AccountManagementActivity.this).doOauthVerify(AccountManagementActivity.this, share_media, authListener);
            }
        });

        oprateDialog.show();
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //SocializeUtils.safeShowDialog(dialog);
            //  Toast.makeText(OneClickLoginActivity.this, "onStart", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            thirdPartyData = data;
            String openid = data.get("openid");
            String unionid = data.get("unionid");
            thirdPartyLogin(openid, unionid);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            // SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(mContext, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            // SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    /**
     * 第三方登录
     *
     * @param openid
     * @param unionid
     */
    public void thirdPartyLogin(String openid, String unionid) {

        if (validateInternet()) {
            ThirdPartyLoginRequest thirdPartyLoginRequest = new ThirdPartyLoginRequest();
            thirdPartyLoginRequest.setLoginType(mThirdPartyLoginType);
            if (!TextUtils.isEmpty(openid)) {
                thirdPartyLoginRequest.setOpenid(openid);
            }
            if (!TextUtils.isEmpty(unionid)) {
                thirdPartyLoginRequest.setUnionid(unionid);
            }
            showProgressDialog(AccountManagementActivity.this);
            mDialog.setTitle("");
            mPresenter.thirdPartyLogin(thirdPartyLoginRequest);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == THIRD_PARTY_LOGIN_REQUEST_CODE && resultCode == RESULT_OK) {

            String qq = ThirdPartyLoginTypeEnum.QQ.getValue();
            String wechat = ThirdPartyLoginTypeEnum.WECHAT.getValue();
            String weibo = ThirdPartyLoginTypeEnum.WEIBO.getValue();

            if (mThirdPartyLoginType.equals(qq))
            {
                mTvQQAccountBind.setText(getResources().getString(R.string.str_bound));
                mTvQQAccountBind.setTextColor(getResources().getColor(R.color.button_grey));

            } else if (mThirdPartyLoginType.equals(wechat))
            {
                mTvWechatAccountBind.setText(getResources().getString(R.string.str_bound));
                mTvWechatAccountBind.setTextColor(getResources().getColor(R.color.button_grey));
            } else if (mThirdPartyLoginType.equals(weibo))
            {
                mTvWeiboAccountBind.setText(getResources().getString(R.string.str_bound));
                mTvWeiboAccountBind.setTextColor(getResources().getColor(R.color.button_grey));
            }
        }

        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
