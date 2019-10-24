package com.nbhysj.coupon.ui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.DestinationScenicSpotsAdapter;
import com.nbhysj.coupon.adapter.MyBusinessCardOprateAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MineContract;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.MineModel;
import com.nbhysj.coupon.model.UserInfoModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MineCollectionAllResponse;
import com.nbhysj.coupon.model.response.MineCollectionDetailResponse;
import com.nbhysj.coupon.model.response.MinePostZanListResponse;
import com.nbhysj.coupon.model.response.MyCardResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.presenter.MinePresenter;
import com.nbhysj.coupon.presenter.UserInfoPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.nbhysj.coupon.view.GlideImageView;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;

import static android.graphics.Color.BLACK;

/**
 * @auther：hysj created on 2019/05/22
 * description：我的名片
 */
public class MyBusinessCardActivity extends BaseActivity<UserInfoPresenter, UserInfoModel> implements UserInfoContract.View {

    //头像
    @BindView(R.id.image_avatar)
    CircleImageView mImgAvatar;
    //名片查看取消
    @BindView(R.id.img_my_business_card_cancel)
    ImageView mImgBusinessCardCancel;
    //昵称
    @BindView(R.id.tv_nickname)
    TextView mTvNickName;
    //简介
    @BindView(R.id.tv_profile)
    TextView mTvProfile;

    //粉丝数
    @BindView(R.id.tv_fans_num)
    TextView mTvFansNum;

    //关注数
    @BindView(R.id.tv_follow_num)
    TextView mTvFollowNum;

    //收藏数
    @BindView(R.id.tv_collection_num)
    TextView mTvCollectionNum;

    //点赞数
    @BindView(R.id.tv_zan_num)
    TextView mTvZanNum;

    //我的
    @BindView(R.id.img_my_business_card_qr_code)
    ImageView mImgQrCodeMyBusinessCard;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_my_business_card;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        BlurBehind.getInstance()
                .withAlpha(90)
                .withFilterColor(Color.parseColor("#000000"))
                .setBackground(MyBusinessCardActivity.this);
        getMyCard();
    }

    @Override
    public void initData() {

        mImgBusinessCardCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyBusinessCardActivity.this.finish();
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void getUserInfoResult(BackResult<UserInfoResponse> res) {

    }

    @Override
    public void updateInformationResult(BackResult res) {

    }

    @Override
    public void userLogoutResult(BackResult res) {

    }

    @Override
    public void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res) {

    }

    @Override
    public void getMyCardResult(BackResult<MyCardResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    MyCardResponse myCardResponse = res.getData();
                    String nickname = myCardResponse.getNickname();
                    String avatarUrl = myCardResponse.getAvater();      //用户头像
                    String profile = myCardResponse.getProfile();   //简介
                    int collectionNum = myCardResponse.getCollectionNum(); //收藏数
                    int fansNum = myCardResponse.getFansNum(); //粉丝数
                    int zanNum = myCardResponse.getZanNum();//赞数
                    int followNum = myCardResponse.getFollowNum();
                    String url = myCardResponse.getUrl();

                    String fansNumStr = String.valueOf(fansNum); //粉丝数
                    String collectionNumStr = String.valueOf(collectionNum); //收藏数
                    String zanNumStr = String.valueOf(zanNum); //点赞数
                    String followNumStr = String.valueOf(followNum); //关注数


                    mTvNickName.setText(nickname);
                    mTvProfile.setText(profile);
                    GlideUtil.loadImage(MyBusinessCardActivity.this, avatarUrl, mImgAvatar);
                    mTvFansNum.setText(fansNumStr);
                    mTvZanNum.setText(zanNumStr);
                    mTvCollectionNum.setText(collectionNumStr);
                    mTvFollowNum.setText(followNumStr);

                    if (!TextUtils.isEmpty(url))
                    {
                        showQRCode(url);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(MyBusinessCardActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(MyBusinessCardActivity.this, Constants.getResultMsg(msg));
    }

    public void getMyCard(){

        if(validateInternet()){
            showProgressDialog(MyBusinessCardActivity.this);
            int userId = getSharedPreferencesUserId();
            mPresenter.getMyCard(userId);
        }
    }

    //show我的二维码
    public void showQRCode(String url) {
        int qrWidth = (int) (DensityUtil.getDensity(MyBusinessCardActivity.this) * 240);
        Bitmap qrCode = null;
        try {
            qrCode = createQRCode(url, qrWidth);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        mImgQrCodeMyBusinessCard.setImageBitmap(qrCode);
    }

    public static Bitmap createQRCode(String url, int widthAndHeight)
            throws WriterException {
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix matrix = new MultiFormatWriter().encode(url,
                BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);

        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];
        //画黑点
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = BLACK; //0xff000000
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
