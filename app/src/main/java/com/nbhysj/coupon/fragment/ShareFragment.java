package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.JsonArray;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ShareAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.SharePlatformEnum;
import com.nbhysj.coupon.contract.MineContract;
import com.nbhysj.coupon.dialog.MinePostOprateDialog;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.dialog.ShareOprateDialog;
import com.nbhysj.coupon.framework.Net;
import com.nbhysj.coupon.model.MineModel;
import com.nbhysj.coupon.model.request.PostDeleteRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MineCollectionAllResponse;
import com.nbhysj.coupon.model.response.MineCollectionDetailResponse;
import com.nbhysj.coupon.model.response.MinePostZanListResponse;
import com.nbhysj.coupon.model.response.MyPostShareBean;
import com.nbhysj.coupon.model.response.MyPostShareResponse;
import com.nbhysj.coupon.model.response.ZanAndCollectionResponse;
import com.nbhysj.coupon.pay.wechat.PayConstants;
import com.nbhysj.coupon.presenter.MinePresenter;
import com.nbhysj.coupon.ui.GeneratePicturesActivity;
import com.nbhysj.coupon.ui.MainActivity;
import com.nbhysj.coupon.ui.MyBusinessCardActivity;
import com.nbhysj.coupon.ui.PostRecommendDetailActivity;
import com.nbhysj.coupon.ui.ReportActivity;
import com.nbhysj.coupon.util.DonwloadSaveImgUtil;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.nbhysj.coupon.util.blurbehind.OnBlurCompleteListener;
import com.nbhysj.coupon.view.MyRecycleView;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

import static com.alibaba.fastjson.util.IOUtils.UTF8;

/**
 * @auther：hysj created on 2019/04/14
 * description：分享
 */
public class ShareFragment extends BaseFragment<MinePresenter, MineModel> implements MineContract.View {

    @BindView(R.id.rv_share)
    RecyclerView mRvShare;

    //暂无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    private List<MyPostShareResponse> myPostShareList;
    private ShareAdapter shareAdapter;

    private boolean visibleToUser;

    static Bitmap bitmap = null;

    private static IWXAPI api;

    private static int imageWidth, imageHight;

    private MinePostOprateDialog minePostOprateDialog;

    OprateDialog oprateDialog;

    private int mGroupPosition;
    private int mChildPosition;

    //图片
    public static String photoUrl;

    private static int postId;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_share;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView(View v) {
        EventBus.getDefault().register(this);

        api = WXAPIFactory.createWXAPI(getActivity(), PayConstants.APP_ID, false);

        if (myPostShareList == null) {

            myPostShareList = new ArrayList<>();
        } else {
            myPostShareList.clear();
        }

        getMyPostShareList();
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置布局管理器
        mRvShare.setLayoutManager(layoutManager);

        shareAdapter = new ShareAdapter(getActivity(), new ShareAdapter.ShareListener() {
            @Override
            public void setShareListener(int mPostId, int groupPosition, int childPosition) {
                mGroupPosition = groupPosition;
                mChildPosition = childPosition;

                if (minePostOprateDialog == null) {
                    minePostOprateDialog = new MinePostOprateDialog(getActivity(), new MinePostOprateDialog.OnSharePlatformItemClickListener() {
                        @Override
                        public void onSharePlatformItemClick(SHARE_MEDIA sharePlatform) {
                            try {

                                String sharePlatformStr = sharePlatform.toString();
                                List<MyPostShareBean> myPostShareBeanList = myPostShareList.get(groupPosition).getMyPosts();
                                photoUrl = myPostShareBeanList.get(mChildPosition).getPhoto();
                                String wechatFriend = SharePlatformEnum.WECHAT_FRIEND.getValue();
                                if(sharePlatformStr.equals(wechatFriend)){

                                    postId = myPostShareBeanList.get(mChildPosition).getId();
                                    new Thread(saveFileRunnable).start();
                                } else {

                                    thirdShare(sharePlatform, photoUrl);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onPostDeleteItemClick() {
                            if (oprateDialog == null) {
                                oprateDialog = new OprateDialog(getActivity()).builder().setTitle(getResources().getString(R.string.str_sure_to_delete_the_post));
                                oprateDialog.setNegativeButton(getResources().getString(R.string.str_cancel), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                });
                                oprateDialog.setPositiveButton(getResources().getString(R.string.str_confirm), getResources().getColor(R.color.color_blue2), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        List<MyPostShareBean> myPostShareBeanList = myPostShareList.get(groupPosition).getMyPosts();
                                        if(myPostShareBeanList != null && myPostShareBeanList.size() > 0)
                                        {
                                            int mPostId = myPostShareBeanList.get(mChildPosition).getId();
                                            deletePost(mPostId);
                                        }
                                    }
                                });
                            }
                            oprateDialog.show();
                        }

                        @Override
                        public void onGeneratePictureItemClick() {
                            List<MyPostShareBean> myPostShareBeanList = myPostShareList.get(groupPosition).getMyPosts();
                            MyPostShareBean myPostShareBean = myPostShareBeanList.get(mChildPosition);
                            photoUrl = myPostShareBean.getPhoto();
                            String content = myPostShareBean.getContent();
                            BlurBehind.getInstance().execute(getActivity(), new OnBlurCompleteListener() {
                                @Override
                                public void onBlurComplete() {

                                    Intent intent = new Intent(getActivity(), GeneratePicturesActivity.class);
                                    intent.putExtra("imageUrl",photoUrl);
                                    intent.putExtra("postId",postId);
                                    intent.putExtra("content",content);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                                    startActivity(intent);
                                }
                            });
                        }

                      /*  @Override
                        public void onPostReportItemClick() {

                            Intent intent = new Intent();
                            intent.setClass(getActivity(), ReportActivity.class);
                            intent.putExtra("reportFlag", 0);   //举报帖子
                            intent.putExtra("postsId", mPostId);
                            getActivity().startActivity(intent);

                        }*/
                    }).builder().setCancelable(true).setCanceledOnTouchOutside(true);
                    minePostOprateDialog.show();
                } else {
                    minePostOprateDialog.show();
                }

            }
        });
        shareAdapter.setShareList(myPostShareList);
        mRvShare.setAdapter(shareAdapter);
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
                WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
                miniProgramObj.webpageUrl = "http:192.168.1.140:8083/"; // 兼容低版本的网页链接
                miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;// 正式版:0，测试版:1，体验版:2
                miniProgramObj.userName = "gh_8f591c4ee659";     // 小程序原始id
                miniProgramObj.path = "pages/travel/travel?id=" + postId; //小程序页面路径；对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"
                WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
                msg.title = "鱼人自游";                    // 小程序消息title
                msg.description = "帖子分享";               // 小程序消息desc
                msg.thumbData = compressImage(thumbBmp);                      // 小程序消息封面图片，小于128k

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("miniProgram");
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前只支持会话
                api.sendReq(req);

                if (bitmap != null) {
                    bitmap.recycle();
                    bitmap = null;
                }

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

    private void thirdShare(SHARE_MEDIA platform, String photoUrl) {
        String webUrl = Net.H5_YURENZIYOU_DOWNLOAD_GUIDE_PAGE_URL;
        UMImage image = new UMImage(getActivity(), photoUrl);                    //资源文件
        UMWeb umWeb = new UMWeb(webUrl, getActivity().getResources().getString(R.string.app_name), "鱼人自游是宁波海洋世界旗下一站式旅游服务平台,产品及服务覆盖门票预订,景点评价及景点打折门票查询,酒店预订,美食推荐、还有更详细的旅游攻略.", image); //URL 标题 描述 封面图
        new ShareAction(getActivity())
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
          Toast.makeText(getActivity(), "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void initData() {

    }

    @Override
    public void getMinePostZanListResult(BackResult<MinePostZanListResponse> res) {

    }

    @Override
    public void getMyPostShareListResult(ResponseBody response) {
        dismissProgressDialog();
        String json = getResponseBody(response);
        JSONObject jsonObject = JSONObject.parseObject(json);
        int code = jsonObject.getInteger("code");
        String msg = jsonObject.getString("msg");
        switch (code) {
            case Constants.SUCCESS_CODE:
                try {

                    JSONArray data = jsonObject.getJSONArray("data");
                    String JSONStr = JSON.toJSONString(data);
                    myPostShareList = JSON.parseObject(JSONStr, new TypeReference<List<MyPostShareResponse>>() {
                    });

                    if (myPostShareList != null) {
                        mRlytNoData.setVisibility(View.GONE);
                        shareAdapter.setShareList(myPostShareList);
                        shareAdapter.notifyDataSetChanged();
                    } else {

                        mRlytNoData.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:

                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(msg));
                break;
        }
    }

    @Override
    public void getMineCollectionAllListResult(ResponseBody res) {

    }

    @Override
    public void getMineCollectionDetailResult(BackResult<MineCollectionDetailResponse> res) {

    }

    @Override
    public void collectionMchBatchDeleteContentResult(BackResult res) {

    }

    @Override
    public void collectionPostsBatchDeleteResult(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    public void getMyPostShareList() {

        if (validateInternet()) {

            mPresenter.getMyPostShareList();
        }
    }

    //帖子删除
    public void deletePost(int mPostId) {

        if (validateInternet()) {

            PostDeleteRequest postDeleteRequest = new PostDeleteRequest();
            postDeleteRequest.setPostsId(mPostId);
            mPresenter.deletePost(postDeleteRequest);
        }
    }

    @Override
    public void deletePostResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    MyPostShareResponse myPostShareResponse = myPostShareList.get(mGroupPosition);
                    List<MyPostShareBean> myPostShareChildList = myPostShareResponse.getMyPosts();
                    MyPostShareBean myPostShareBean = myPostShareChildList.get(mChildPosition);
                    myPostShareChildList.remove(myPostShareBean);
                    shareAdapter.setShareList(myPostShareList);
                    shareAdapter.notifyDataSetChanged();

                    if (minePostOprateDialog != null) {
                        minePostOprateDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getZanMsgListResult(BackResult<ZanAndCollectionResponse> res) {

    }

    @Override
    public void getCollectionMsgListResult(BackResult<ZanAndCollectionResponse> res) {

    }

    @Override
    public void lazyInitView(View view) {

    }

    public byte[] getImage(String srcPath) {

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

        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
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
        imageWidth = bitmapImage.getWidth();
        imageHight = bitmapImage.getHeight();
        return baos.toByteArray();
    }

    public static String getResponseBody(ResponseBody responseBody) {

        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE); // Buffer the entire body.
        } catch (IOException e) {
            e.printStackTrace();
        }
        Buffer buffer = source.buffer();

        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                e.printStackTrace();
            }
        }
        return buffer.clone().readString(charset);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visibleToUser = isVisibleToUser;
    }

    @Subscribe
    public void onEvent(String mineFragmentRefresh) {

            if (mineFragmentRefresh.equals("shareFragmentRefresh")) {
                myPostShareList.clear();
                shareAdapter.notifyDataSetChanged();
                showProgressDialog(getActivity());
                getMyPostShareList();
            }
    }

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
