package com.nbhysj.coupon.ui;


import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.view.GlideImageView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class OneClickLoginActivity extends BaseActivity {

    //用户头像
    @BindView(R.id.image_user_avatar)
    GlideImageView mImageUserAvatar;
    //用户协议
    @BindView(R.id.tv_user_agreement)
    TextView mTvUserAgreement;
    //注册
    @BindView(R.id.tv_toolbar_right)
    TextView mTvUserRegister;

    @Override
    public int getLayoutId() {
        return R.layout.activity_one_click_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(OneClickLoginActivity.this, getResources().getString(R.string.str_onekey_login), getResources().getString(R.string.str_register));

        mTvUserRegister.setOnClickListener(v -> {

            toActivity(UserRegistrationActivity.class);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    @Override
    public void initData() {

        mImageUserAvatar.loadCircle("http://img2.imgtn.bdimg.com/it/u=2060761043,284284863&fm=26&gp=0.jpg");
        mTvUserAgreement.setText(Html.fromHtml("登录即表示接受鱼人自游的" + "<font color='#00C7DA'>" + "《用户协议》" + "</font>" + "和" + "<font color='#00C7DA'>" + "《中国移动认证服务条款》" + "</font>"));
    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.tv_login, R.id.rlyt_weixin, R.id.rlyt_message, R.id.rlyt_qq, R.id.rlyt_sina_weibo})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                toActivity(MainActivity.class);
                break;
            case R.id.rlyt_qq:

                oprateTips("QQ", SHARE_MEDIA.QQ);

                break;
            case R.id.rlyt_weixin:

                oprateTips("微信", SHARE_MEDIA.WEIXIN);

                break;
            case R.id.rlyt_sina_weibo:

                oprateTips("新浪微博", SHARE_MEDIA.SINA);

                break;
            case R.id.rlyt_message:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    public void oprateTips(String platform, SHARE_MEDIA share_media) {

        OprateDialog oprateDialog = new OprateDialog(OneClickLoginActivity.this).builder().setTitle("鱼人自游想要打开" + platform);
        oprateDialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        oprateDialog.setPositiveButton("打开", getResources().getColor(R.color.color_blue2), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMShareAPI.get(OneClickLoginActivity.this).doOauthVerify(OneClickLoginActivity.this, share_media, authListener);
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
            String openid = data.get("openid");
            String unionid = data.get("unionid");
            // thirdPartyLogin(openid, unionid);

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
}
