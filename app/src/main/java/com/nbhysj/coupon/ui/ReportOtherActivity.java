package com.nbhysj.coupon.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSAuthCredentialsProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.OrderCommentPictureAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.UploadFileTypeEnum;
import com.nbhysj.coupon.common.Enum.UploadTypeEnum;
import com.nbhysj.coupon.contract.ReportContract;
import com.nbhysj.coupon.model.ReportModel;
import com.nbhysj.coupon.model.request.PostReportRequest;
import com.nbhysj.coupon.model.request.PublishPostRequest;
import com.nbhysj.coupon.model.request.UserReportRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.oss.Config;
import com.nbhysj.coupon.oss.service.OssService;
import com.nbhysj.coupon.oss.view.UIDisplayer;
import com.nbhysj.coupon.presenter.ReportPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.EncryptedSignatureUtil;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.widget.glide.GifSizeFilter;
import com.nbhysj.coupon.widget.glide.Glide4Engine;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/11/25
 * description：举报其他
 */
public class ReportOtherActivity extends BaseActivity<ReportPresenter, ReportModel> implements ReportContract.View {

    //举报照片
    @BindView(R.id.rv_report_picture)
    RecyclerView mRvReportPicture;

    //举报原因
    @BindView(R.id.edt_report_reason)
    EditText mEdtReportReason;

    //举报内容长度
    @BindView(R.id.tv_report_content_length)
    TextView mTvReportContentLength;

    private OrderCommentPictureAdapter orderEvaluateAdapter;

    //OSS的上传下载
    private OssService mService;

    //负责所有的界面更新
    private UIDisplayer mUIDisplayer;

    //图片上传数量 或者 视频+gif图片上传标识
    private int fileUploadNum;

    //举报照片选择
    private ArrayList<String> selectedPhotosList;

    private int imageWidth, imageHight;

    //上传照片选择
    private List<String> uploadPhotoSelect;

    private String uploadFileType;

    StringBuffer stringBuffer = new StringBuffer();

    List<PublishPostRequest.ResourceInfoEntity> resourceInfoEntityList;

    //照片选取返回code
    private int REQUEST_CODE_POST_PHOTO = 23;

    private int reportFlag;

    //帖子id
    private int mPostId;

    //用户id
    private int mUserId;

    //举报理由
    private String reportReason;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_report_other;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(ReportOtherActivity.this,getResources().getString(R.string.str_report),R.mipmap.icon_left_arrow_black);
        reportFlag = getIntent().getIntExtra("reportFlag",0);
        mPostId = getIntent().getIntExtra("postId",0);
        mUserId = getIntent().getIntExtra("userId",0);

        if(resourceInfoEntityList == null){

            resourceInfoEntityList = new ArrayList();
        } else {

            resourceInfoEntityList.clear();
        }

        if (selectedPhotosList == null) {

            selectedPhotosList = new ArrayList<>();
        } else {
            selectedPhotosList.clear();
        }

        if(uploadPhotoSelect == null){

            uploadPhotoSelect = new ArrayList<>();

        } else {
            uploadPhotoSelect.clear();
        }
    }

    @Override
    public void initData() {
        mUIDisplayer = new UIDisplayer(this, new UIDisplayer.UIDisPlayerListener() {
            @Override
            public void setUIDisPlayerListener() {
                fileUploadNum++;
                int imageNum = selectedPhotosList.size();
                if (fileUploadNum == imageNum) {

                    reportContentSubmit();

                }
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(ReportOtherActivity.this, 3);
        gridLayoutManager.setOrientation(gridLayoutManager.VERTICAL);
        mRvReportPicture.addItemDecoration(new RecyclerViewItemDecoration(Tools.dip2px(ReportOtherActivity.this, 10)));
        mRvReportPicture.setLayoutManager(gridLayoutManager);
        orderEvaluateAdapter = new OrderCommentPictureAdapter(ReportOtherActivity.this, new OrderCommentPictureAdapter.OrderCommentPictureListener() {
            @Override
            public void setPhotoSelectListener(int position) {

                getNotePhotoPicker(MimeType.ofImage(), true);
            }

            @Override
            public void setPhotoDeleteListener(int position) {
                String photo = selectedPhotosList.get(position);
                selectedPhotosList.remove(photo);
                if (selectedPhotosList != null) {
                    orderEvaluateAdapter.setOrderCommentPictureList(selectedPhotosList);
                    orderEvaluateAdapter.notifyDataSetChanged();
                }
            }
        });
        orderEvaluateAdapter.setOrderCommentPictureList(selectedPhotosList);
        mRvReportPicture.setAdapter(orderEvaluateAdapter);

        mService = initOSS(Config.OSS_ENDPOINT, Config.BUCKET_NAME, mUIDisplayer, UploadTypeEnum.AVATAR.getKey(), String.valueOf(userId));
        //设置上传的callback地址，目前暂时只支持putObject的回调
        mService.setCallbackAddress(Config.OSS_CALLBACK_URL);

        mEdtReportReason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int nicknameLength = charSequence.length();
                mTvReportContentLength.setText(nicknameLength + "/200");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    public OssService initOSS(String endpoint, String bucket, UIDisplayer displayer, String uploadType, String userId) {

//        移动端是不安全环境，不建议直接使用阿里云主账号ak，sk的方式。建议使用STS方式。具体参
//        https://help.aliyun.com/document_detail/31920.html
//        注意：SDK 提供的 PlainTextAKSKCredentialProvider 只建议在测试环境或者用户可以保证阿里云主账号AK，SK安全的前提下使用。具体使用如下
//        主账户使用方式
//        String AK = "******";
//        String SK = "******";
//        credentialProvider = new PlainTextAKSKCredentialProvider(AK,SK)
//        以下是使用STS Sever方式。
//        如果用STS鉴权模式，推荐使用OSSAuthCredentialProvider方式直接访问鉴权应用服务器，token过期后可以自动更新。
//        详见：https://help.aliyun.com/document_detail/31920.html
//        OSSClient的生命周期和应用程序的生命周期保持一致即可。在应用程序启动时创建一个ossClient，在应用程序结束时销毁即可。


        OSSCredentialProvider credentialProvider;
        //使用自己的获取STSToken的类

        credentialProvider = new OSSAuthCredentialsProvider(Config.STS_SERVER_URL);

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider, conf);
        OSSLog.enableLog();
        return new OssService(oss, bucket, displayer, ReportOtherActivity.this);

    }

    public void getNotePhotoPicker(Set<MimeType> mimeTypes, boolean capture) {
        AndPermission.with(ReportOtherActivity.this)
                .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

                        Matisse.from(ReportOtherActivity.this)
                                .choose(mimeTypes, true)
                                .countable(true)
                                .capture(capture)
                                .captureStrategy(
                                        new CaptureStrategy(true, "com.nbhysj.coupon.fileprovider"))
                                .maxSelectable(9)
                                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .gridExpectedSize(
                                        getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                // for glide-V3
//                                            .imageEngine(new GlideEngine())
                                // for glide-V4
                                .imageEngine(new Glide4Engine())
                                .setOnSelectedListener(new OnSelectedListener() {
                                    @Override
                                    public void onSelected(
                                            @NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                                        // DO SOMETHING IMMEDIATELY HERE
                                        Log.e("onSelected", "onSelected: pathList=" + pathList);

                                    }
                                })
                                .originalEnable(true)
                                .maxOriginalSize(10)
                                .setOnCheckedListener(new OnCheckedListener() {
                                    @Override
                                    public void onCheck(boolean isChecked) {
                                        // DO SOMETHING IMMEDIATELY HERE
                                        Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                                    }
                                })
                                .forResult(REQUEST_CODE_POST_PHOTO);
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        Uri packageURI = Uri.parse("package:" + getPackageName());
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        // Toast.makeText(MainActivity.this, "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                    }
                }).start();
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    public void asyncUploadIamge() {

        String objectName = "";
        byte[] imageByte = null;
        for (int i = 0; i < selectedPhotosList.size(); i++) {
            String selectPhotoUrl = selectedPhotosList.get(i);
            objectName = getFileName(UploadFileTypeEnum.IMAGE.getValue(), selectPhotoUrl);
            imageByte = getImage(selectPhotoUrl);
            PublishPostRequest.ResourceInfoEntity resourceInfoEntity = new PublishPostRequest().new ResourceInfoEntity();
            if (uploadFileType.equals(UploadFileTypeEnum.IMAGE.getValue())) {
                resourceInfoEntity.setType(UploadFileTypeEnum.IMAGE.getKey());
                resourceInfoEntity.setWidth(imageWidth);
                resourceInfoEntity.setHeight(imageHight);
                resourceInfoEntity.setUrl(objectName);
                resourceInfoEntity.setSort(i);
                resourceInfoEntityList.add(resourceInfoEntity);
                uploadPhotoSelect.add(objectName);
            }
            mService.asyncUploadIamge(objectName, UploadFileTypeEnum.IMAGE.getValue(), imageByte);
        }
    }

    public String getFileName(String uploadFileType, String uploadFile) {
        this.uploadFileType = uploadFileType;
        stringBuffer.setLength(0);
        stringBuffer.append(uploadFileType + "/");
        String date = DateUtil.getTime(new Date(), DateUtil.sDateYMDFormat);
        stringBuffer.append(date + "/");
        String uuid = EncryptedSignatureUtil.getUUID();
        stringBuffer.append(uuid);

        if (!TextUtils.isEmpty(uploadFile)) {
            File file = new File(uploadFile);
            String fileName = file.getName();
            stringBuffer.append(fileName);
        }
        return stringBuffer.toString();
    }

    public byte[] getImage(String srcPath) {
        Bitmap bitmap = null;
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

    private byte[] compressImage(Bitmap bitmapImage) {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            int options = 50;
            while (baos.toByteArray().length / 1024 > 500) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (resultCode == RESULT_OK &&
                    requestCode == REQUEST_CODE_POST_PHOTO) {   //从相册中选取图片
                List<String> filePathList = null;
                if (data != null) {

                    filePathList = Matisse.obtainPathResult(data);
                    selectedPhotosList.addAll(filePathList);
                    orderEvaluateAdapter.setOrderCommentPictureList(selectedPhotosList);
                    orderEvaluateAdapter.notifyDataSetChanged();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postReportResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    showToast(ReportOtherActivity.this,"帖子举报成功");
                    setResult(RESULT_OK);
                    ReportOtherActivity.this.finish();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ReportOtherActivity.this, Constants.getResultMsg(res.getMsg()));
        }
    }

    @Override
    public void userReportResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    showToast(ReportOtherActivity.this,"用户举报成功");
                    setResult(RESULT_OK);
                    ReportOtherActivity.this.finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ReportOtherActivity.this, Constants.getResultMsg(res.getMsg()));
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @OnClick({R.id.tv_report_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_report_submit:

                reportSubmit();

                break;
            default:
                break;

        }
    }

    //举报提交
    public void reportSubmit() {

        if (validateInternet()) {

            reportReason = mEdtReportReason.getText().toString();
            //String orderCommentDes = mEdtOrderCommentDes.getText().toString();

            if (TextUtils.isEmpty(reportReason))
            {
                showToast(ReportOtherActivity.this, "请填写举报理由");
                return;
            }

            showProgressDialog(ReportOtherActivity.this);
            resourceInfoEntityList.clear();
            uploadPhotoSelect.clear();
            if(selectedPhotosList.size() > 0) {
                fileUploadNum = 0;
                asyncUploadIamge();
            } else {
                reportContentSubmit();
            }

        }
    }
    /**
     * 为RecyclerView增加间距
     * 预设2列，如果是3列，则左右值不同
     */
    public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
        private int space = 0;
        private int pos;

        public RecyclerViewItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            outRect.top = space;

            //该View在整个RecyclerView中位置。
            pos = parent.getChildAdapterPosition(view);

            //取模

            //两列的左边一列
            if (pos % 2 == 0) {
                outRect.left = space;
                outRect.right = space / 2;
            }

            //两列的右边一列
            if (pos % 2 == 1) {
                outRect.left = space / 2;
                outRect.right = space;
            }
        }
    }

 public void reportContentSubmit(){

        if(validateInternet())
        {
            if(reportFlag == 0)  //帖子举报
            {
                PostReportRequest postReportRequest = new PostReportRequest();
                postReportRequest.setPostsId(mPostId);
                postReportRequest.setReason(reportReason);
                if(uploadPhotoSelect.size() > 0)
                {
                    postReportRequest.setImages(uploadPhotoSelect);
                }
                mPresenter.postReport(postReportRequest);

            } else if(reportFlag == 1){  //用户举报

                UserReportRequest userReportRequest = new UserReportRequest();
                userReportRequest.setUserId(mUserId);
                userReportRequest.setReason(reportReason);
                if(uploadPhotoSelect.size() > 0)
                {
                    userReportRequest.setImages(uploadPhotoSelect);
                }
                mPresenter.userReport(userReportRequest);
            }
        }
     }
}
