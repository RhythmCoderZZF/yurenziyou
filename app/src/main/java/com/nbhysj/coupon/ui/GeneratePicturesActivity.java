package com.nbhysj.coupon.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.GeneratePictureOprateAdapter;
import com.nbhysj.coupon.adapter.ScenicSpotMchDestinationAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.SharePlatformEnum;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.framework.Net;
import com.nbhysj.coupon.model.UserInfoModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.GeneratePictureMenuBean;
import com.nbhysj.coupon.model.response.MyCardResponse;
import com.nbhysj.coupon.model.response.ShoppingMallMenuBean;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.pay.wechat.PayConstants;
import com.nbhysj.coupon.presenter.UserInfoPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.graphics.Color.BLACK;
import static com.nbhysj.coupon.util.DonwloadSaveImgUtil.mBitmap;

/**
 * @auther：hysj created on 2019/05/22
 * description：图片生成
 */
public class GeneratePicturesActivity extends BaseActivity<UserInfoPresenter, UserInfoModel> implements UserInfoContract.View {

    //头像
    @BindView(R.id.image_avatar)
    CircleImageView mImgAvatar;
    //名片查看取消
    @BindView(R.id.img_my_business_card_cancel)
    ImageView mImgBusinessCardCancel;

    @BindView(R.id.rv_generate_picture)
    RecyclerView mRvGeneratePicture;

    @BindView(R.id.img_post)
    ImageView mImgPost;

    //标题
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    private GeneratePictureOprateAdapter generatePictureOprateAdapter;

    List<GeneratePictureMenuBean> generatePictureMenuList;

    private Bitmap bitmap;

    private static String photoUrl;

    private static IWXAPI api;

    //帖子id
    private static int mPostId;

    private String content;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_generate_picture;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        BlurBehind.getInstance()
                .withAlpha(90)
                .withFilterColor(Color.parseColor("#000000"))
                .setBackground(GeneratePicturesActivity.this);
        api = WXAPIFactory.createWXAPI(this, PayConstants.APP_ID, false);
        photoUrl = getIntent().getStringExtra("imageUrl");
        mPostId = getIntent().getIntExtra("postId",0);
        content = getIntent().getStringExtra("content");

        GlideUtil.loadImage(GeneratePicturesActivity.this,photoUrl,mImgPost);
        mTvTitle.setText(content);
    }

    @Override
    public void initData() {


        if (generatePictureMenuList == null) {

            generatePictureMenuList = new ArrayList<>();
        } else {

            generatePictureMenuList.clear();
        }

        generatePictureMenuList = getGeneratePictureMenuList();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GeneratePicturesActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvGeneratePicture.setLayoutManager(linearLayoutManager);
        generatePictureOprateAdapter = new GeneratePictureOprateAdapter(GeneratePicturesActivity.this, new GeneratePictureOprateAdapter.GeneratePictureOprateListener() {
            @Override
            public void setGeneratePictureCallback(int position,SHARE_MEDIA sharePlatform) {

                if(position == 0) {
                    if (!TextUtils.isEmpty(photoUrl)) {
                        new Thread(saveFileRunnable).start();
                    }
                } else{
                    String sharePlatformStr = sharePlatform.toString();
                    String wechatFriend = SharePlatformEnum.WECHAT_FRIEND.getValue();
                    if (sharePlatformStr.equals(wechatFriend)) {

                        new Thread(wechatShareRunnable).start();

                    } else {

                        thirdShare(sharePlatform, photoUrl);
                    }

                }
            }
        });
        generatePictureOprateAdapter.setGeneratePictureOprateList(generatePictureMenuList);
        mRvGeneratePicture.setAdapter(generatePictureOprateAdapter);

        mImgBusinessCardCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GeneratePicturesActivity.this.finish();
            }
        });
    }
    private void thirdShare(SHARE_MEDIA platform, String photoUrl) {
        String webUrl = Net.H5_YURENZIYOU_DOWNLOAD_GUIDE_PAGE_URL;
        UMImage image = new UMImage(GeneratePicturesActivity.this, photoUrl);                    //资源文件
        UMWeb umWeb = new UMWeb(webUrl, GeneratePicturesActivity.this.getResources().getString(R.string.app_name), "鱼人自游是宁波海洋世界旗下一站式旅游服务平台,产品及服务覆盖门票预订,景点评价及景点打折门票查询,酒店预订,美食推荐、还有更详细的旅游攻略.", image); //URL 标题 描述 封面图
        new ShareAction(GeneratePicturesActivity.this)
                .setPlatform(platform)//传入平台
                .withText("HiVideo")//标题
                .withMedia(umWeb)
                .setCallback(shareListener)//回调监听器
                .share();
    }
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            //   Toast.makeText(getActivity(), "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(GeneratePicturesActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(GeneratePicturesActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
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

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(GeneratePicturesActivity.this, Constants.getResultMsg(msg));
    }

    //show我的二维码
    public void showQRCode(String url) {
        int qrWidth = (int) (DensityUtil.getDensity(GeneratePicturesActivity.this) * 240);
        Bitmap qrCode = null;
        try {
            qrCode = createQRCode(url, qrWidth);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        //mImgQrCodeMyBusinessCard.setImageBitmap(qrCode);
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

    public List<GeneratePictureMenuBean> getGeneratePictureMenuList() {

        GeneratePictureMenuBean saveToAlbum = new GeneratePictureMenuBean();
        saveToAlbum.setTitle("保存到相册");
        saveToAlbum.setIcon(R.mipmap.icon_save_to_album);

        GeneratePictureMenuBean wechatCircle = new GeneratePictureMenuBean();
        wechatCircle.setTitle("微信");
        wechatCircle.setIcon(R.mipmap.icon_generate_picture_wechat_share);

        GeneratePictureMenuBean wechatFriendsCircle = new GeneratePictureMenuBean();
        wechatFriendsCircle.setTitle("朋友圈");
        wechatFriendsCircle.setIcon(R.mipmap.icon_wechat_circle_of_friends);

        GeneratePictureMenuBean qqShare = new GeneratePictureMenuBean();
        qqShare.setTitle("QQ");
        qqShare.setIcon(R.mipmap.icon_generate_picture_qq_share);

        GeneratePictureMenuBean qqZoneShare = new GeneratePictureMenuBean();
        qqZoneShare.setTitle("QQ空间");
        qqZoneShare.setIcon(R.mipmap.icon_generate_picture_qq_zone_share);

        GeneratePictureMenuBean weiboShare = new GeneratePictureMenuBean();
        weiboShare.setTitle("微博");
        weiboShare.setIcon(R.mipmap.icon_generate_picture_weibo_share);

        generatePictureMenuList.add(saveToAlbum);
        generatePictureMenuList.add(wechatCircle);
        generatePictureMenuList.add(wechatFriendsCircle);
        generatePictureMenuList.add(qqShare);
        generatePictureMenuList.add(qqZoneShare);
        generatePictureMenuList.add(weiboShare);

        return generatePictureMenuList;


    }

    private static Runnable saveFileRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(photoUrl);
                //打开输入流
                InputStream inputStream = url.openStream();
                //对网上资源进行下载转换位图图片
                Bitmap bmp = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 500, 500, true);
                bmp.recycle();
                saveImageToGallery(mTopActivity,thumbBmp);

                //saveFile(mBitmap);
                //   mSaveMessage = "图片保存成功！";
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //   messageHandler.sendMessage(messageHandler.obtainMessage());
        }
    };

    private static Runnable wechatShareRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(photoUrl);
                //打开输入流
                InputStream inputStream = url.openStream();
                //对网上资源进行下载转换位图图片
                Bitmap bmp = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 500, 500, true);
                bmp.recycle();
                WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
                miniProgramObj.webpageUrl = "http:192.168.1.140:8083/"; // 兼容低版本的网页链接
                miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;// 正式版:0，测试版:1，体验版:2
                miniProgramObj.userName = "gh_8f591c4ee659";     // 小程序原始id
                miniProgramObj.path = Net.POST_TRAVEL_MINIPTOGRAM_URL + mPostId; //小程序页面路径；对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"
                WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
                msg.title = "鱼人自游";                    // 小程序消息title
                msg.description = "帖子分享";               // 小程序消息desc
                msg.thumbData = compressImage(thumbBmp);                      // 小程序消息封面图片，小于128k

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("miniProgram");
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前只支持会话
                api.sendReq(req);


                //saveFile(mBitmap);
                //   mSaveMessage = "图片保存成功！";
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //   messageHandler.sendMessage(messageHandler.obtainMessage());
        }
    };

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public Bitmap getImageBitmap(String srcPath) {

        try {
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            //开始读入图片，此时把options.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true;
            bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

            newOpts.inJustDecodeBounds = false;
            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
            float hh = 800f;// 这里设置高度为800f
            float ww = 480f;// 这里设置宽度为480f
            // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            int be = 1;// be=1表示不缩放
            if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
                be = (int) (newOpts.outWidth / ww);
            } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
                be = (int) (newOpts.outHeight / hh);
            }
            if (be <= 0)
                be = 1;
            newOpts.inSampleSize = be;// 设置缩放比例
            //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

            // String type = newOpts.outMimeType;
         /*   if (TextUtils.isEmpty(type)) {
                type = "未能识别的图片";
            } else {
                type = type.substring(6, type.length());
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;//压缩好比例大小后再进行质量压缩
    }



    private static byte[] compressImage(Bitmap bitmapImage) {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            int options = 50;
            while (baos.toByteArray().length / 1024 > 100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
                baos.reset();//重置baos即清空baos
                options -= 10;//每次都减少10
                bitmapImage.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中

            }
            //ByteArrayInputStream isBm = new ByteArrayInputStream());//把压缩后的数据baos存放到ByteArrayInputStream中
            //   bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    /**
     * 保存图片到相册
     */
    public static void saveImageToGallery(Context mContext, Bitmap bitmap) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            return;
        }
        // 首先保存图片
        File appDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsoluteFile();
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            mTopActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTopActivity.showToast(mContext,"保存成功");
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(mContext.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } // 最后通知图库更新

        mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + "")));
    }
}
