package com.nbhysj.coupon.ui;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSAuthCredentialsProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.NotePictureItemAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.HotTagsTypeEnum;
import com.nbhysj.coupon.common.Enum.UploadFileTypeEnum;
import com.nbhysj.coupon.common.Enum.UploadTypeEnum;
import com.nbhysj.coupon.contract.PublishPostContract;
import com.nbhysj.coupon.dialog.NoteSaveExitPromptDialog;
import com.nbhysj.coupon.model.PublishPostModel;
import com.nbhysj.coupon.model.request.PublishPostRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HotTagsTopicBean;
import com.nbhysj.coupon.model.response.MerchantListResponse;
import com.nbhysj.coupon.model.response.TagTopicSearchResponse;
import com.nbhysj.coupon.model.response.TopicResponse;
import com.nbhysj.coupon.oss.Config;
import com.nbhysj.coupon.oss.gifencoder.BitmapExtractor;
import com.nbhysj.coupon.oss.gifencoder.GIFEncoder;
import com.nbhysj.coupon.oss.service.OssService;
import com.nbhysj.coupon.oss.view.UIDisplayer;
import com.nbhysj.coupon.presenter.PublishPostPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.BitmapUtils;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.EncryptedSignatureUtil;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.nbhysj.coupon.util.blurbehind.OnBlurCompleteListener;
import com.nbhysj.coupon.view.MyRecycleView;
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
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import butterknife.BindView;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPreview;

/**
 * @auther：hysj created on 2019/03/02
 * description：分享发布
 */
public class PublishNoteActivity extends BaseActivity<PublishPostPresenter, PublishPostModel> implements PublishPostContract.View {
    //声音录入
    private int REQUEST_CODE_SOUND_PHOTO_RECORD = 0;
    //照片选取返回code
    private int REQUEST_CODE_POST_PHOTO = 23;
    //上传本地视频
    private int REQUEST_CODE_NATIVE_VIDEO = 2;
    //商户地址选择
    private int REQUEST_CODE_MERCHANT_SELECT = 3;
    //更多标签选择
    private int REQUEST_CODE_TAG_SELECT = 4;

    //发布按钮
    @BindView(R.id.rlyt_push_note)
    RelativeLayout mRlytPushNote;
    //帖子列表
    @BindView(R.id.rv_note_picture)
    MyRecycleView mRvNotePicture;
    //内容
    @BindView(R.id.edt_profile)
    EditText mEdtProfile;
    //简介
    @BindView(R.id.tv_profile_length)
    TextView mTvProfileLength;
    //商户选择
    @BindView(R.id.tv_merchant_name)
    TextView mTvMerchantName;
    //热门标签
    @BindView(R.id.flowlayout_hot_label)
    TagFlowLayout mTagHotLabel;
    private NotePictureItemAdapter notePictureItemAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    //OSS的上传下载
    private OssService mService;

    //负责所有的界面更新
    private UIDisplayer mUIDisplayer;
    StringBuffer stringBuffer = new StringBuffer();
    private ArrayList<String> photos;

    //判断视频或者图片选择
    private boolean isPhotoSelect;

    //本地视频地址
    private String localVideoPath;

    //gif
    private String mGifFileName;
    private String mGifFilePath;

    private String uploadFileType;

    private int imageWidth, imageHight;
    PublishPostRequest publishPostRequest = new PublishPostRequest();
    List<PublishPostRequest.ResourceInfoEntity> resourceInfoEntityList = new ArrayList<>();

    //图片上传数量 或者 视频+gif图片上传标识
    private int fileUploadNum;

    private String mAudiofileUrl;

    private Set<Integer> selectPosSet;

    private List<Integer> selectTopicIdList;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    //经度
    private String mLatitude = "";
    //纬度
    private String mLongitude = "";

    //商户id
    private int mchId;

    long duration;

    //商户名字
    private String mchName;

    //声音胶囊弹框选择
    private NoteSaveExitPromptDialog noteSaveExitPromptDialog;

    //拍照||从相册里选择 视频拍摄
    private NoteSaveExitPromptDialog cameraCapturePromptDialog;

    TagAdapter tagAdapter;

    List<HotTagsTopicBean> hotTagsTopicResponse;

    //音频播放
    private MediaPlayer mediaPlayer;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_publish_note;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initLocation();
        if (photos == null) {

            photos = new ArrayList<>();
        } else {
            photos.clear();
        }

        if (selectTopicIdList == null) {
            selectTopicIdList = new ArrayList();
        } else {
            selectTopicIdList.clear();
        }

        if(hotTagsTopicResponse == null){
            hotTagsTopicResponse = new ArrayList<>();
        } else {
            hotTagsTopicResponse.clear();
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRvNotePicture.setNestedScrollingEnabled(false);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvNotePicture.setLayoutManager(linearLayoutManager);

        notePictureItemAdapter = new NotePictureItemAdapter(PublishNoteActivity.this);
        //  selectedPhotos.add("");
        //selectedPhotos.add("");
        notePictureItemAdapter.setNotePictureList(selectedPhotos, isPhotoSelect, null, 0);
        notePictureItemAdapter.setNotePictureListener(new NotePictureItemAdapter.NotePictureListener() {
            @Override
            public void setNotePictureListener(int position, boolean isSelectPictureDelete) {

                if (photos.size() > 0) {

                    if (position == 0) {

                        if(noteSaveExitPromptDialog == null)
                        {
                            noteSaveExitPromptDialog = new NoteSaveExitPromptDialog(PublishNoteActivity.this).builder();

                                noteSaveExitPromptDialog.addSheetItem(getResources().getString(R.string.str_video_play), NoteSaveExitPromptDialog.SheetItemColor.Gray, new NoteSaveExitPromptDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {

                                        if (!TextUtils.isEmpty(mAudiofileUrl)) {
                                            try {
                                                mediaPlayer = new MediaPlayer();
                                                mediaPlayer.setDataSource(mAudiofileUrl);
                                                mediaPlayer.prepare();
                                                mediaPlayer.start();

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                            noteSaveExitPromptDialog.addSheetItem(getResources().getString(R.string.str_edit), NoteSaveExitPromptDialog.SheetItemColor.Gray, new NoteSaveExitPromptDialog.OnSheetItemClickListener() {
                                @Override
                                public void onClick(int which) {

                                }
                            });
                            noteSaveExitPromptDialog.addSheetItem(getResources().getString(R.string.str_delete), NoteSaveExitPromptDialog.SheetItemColor.Red, new NoteSaveExitPromptDialog.OnSheetItemClickListener() {
                                @Override
                                public void onClick(int which) {

                                    mAudiofileUrl = null;
                                    notePictureItemAdapter.setNotePictureList(selectedPhotos, isPhotoSelect, mAudiofileUrl, 0);
                                    notePictureItemAdapter.notifyDataSetChanged();
                                }
                            });
                            noteSaveExitPromptDialog.setSheetItems();
                        }

                        noteSaveExitPromptDialog.show();

                    } else if (position == 1) {

                        isPhotoSelect = true;

                        cameraCapturePromptDialog();
                    } else {
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(position - 2)
                                .start(PublishNoteActivity.this);
                       // getNotePhotoPicker();

                    }
                } else {

                    if (position == 0) {
                      //  isPhotoSelect = true;
                        //getNativeVideo();
                        cameraCapturePromptDialog();
                    }
                }
            }
        });
        mRvNotePicture.setAdapter(notePictureItemAdapter);
    }

    @Override
    public void initData() {
        getHotTagsTopicList();
        mUIDisplayer = new UIDisplayer(this, new UIDisplayer.UIDisPlayerListener() {
            @Override
            public void setUIDisPlayerListener() {
                fileUploadNum++;
                if(isPhotoSelect) {

                    int imageNum = photos.size();
                    if (fileUploadNum == imageNum)
                    {
                        publishNote();
                    }
                } else {
                    if(fileUploadNum == 2)
                    {
                        publishNote();
                    }
                }

            }
        });

        mService = initOSS(Config.OSS_ENDPOINT, Config.BUCKET_NAME, mUIDisplayer, UploadTypeEnum.AVATAR.getKey(), String.valueOf(userId));
        //设置上传的callback地址，目前暂时只支持putObject的回调
        mService.setCallbackAddress(Config.OSS_CALLBACK_URL);

        mEdtProfile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int nicknameLength = charSequence.length();
                mTvProfileLength.setText(nicknameLength + "/500");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mTagHotLabel.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);

    }

    public void getNotePhotoPicker(Set<MimeType> mimeTypes,boolean capture) {
        AndPermission.with(PublishNoteActivity.this)
                .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

                        Matisse.from(PublishNoteActivity.this)
                                .choose(mimeTypes, false)
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (resultCode == RESULT_OK &&
                    requestCode == REQUEST_CODE_POST_PHOTO) {   //从相册中选取图片
                List<String> filePathList = null;
                if (data != null) {
                    filePathList = Matisse.obtainPathResult(data);
                }

                if(isPhotoSelect)  //照片拍摄或者选择
                {
                    Intent intent = new Intent();
                    intent.setClass(PublishNoteActivity.this, PublishSoundRecordingActivity.class);
                    intent.putStringArrayListExtra("publishPictureUrlList", (ArrayList<String>) filePathList);
                    startActivityForResult(intent, REQUEST_CODE_SOUND_PHOTO_RECORD);
                } else {   //本地视频选择

                    selectedPhotos.clear();
                    localVideoPath = filePathList.get(0);
                   /* File file = new File(filePath);
                    Uri uriVideo = file.get*/
                  //  localVideoPath = BitmapUtils.INSTANCE.getPhotoPathFromContentUri(this, uriVideo);
                    selectedPhotos.add(localVideoPath);
                    notePictureItemAdapter.setNotePictureList(selectedPhotos, isPhotoSelect, null, 0);
                    notePictureItemAdapter.notifyDataSetChanged();
                    AsynTaskGifEncoder();
                }

            } else if (resultCode == -1 &&
                    (requestCode == 666 || requestCode == 666)) {
                if (data != null) {
                    //photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                }
                selectedPhotos.clear();
                selectedPhotos.addAll(photos);
                notePictureItemAdapter.setNotePictureList(selectedPhotos, isPhotoSelect, mAudiofileUrl, 0);
                mRvNotePicture.setAdapter(notePictureItemAdapter);
            } else if (requestCode == REQUEST_CODE_SOUND_PHOTO_RECORD && resultCode == RESULT_OK) {
                isPhotoSelect = true;
                photos = data.getStringArrayListExtra("publishPictureUrlList");
                // mAudiofileName = data.getStringExtra("mAudiofileName");
                SharedPreferences sharePreferences = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
                mAudiofileUrl = sharePreferences.getString("audio_path", "");
                duration = sharePreferences.getLong("duration", 0);
                int audiofileLength = (int) duration / 1000;
                selectedPhotos.clear();
              /* selectedPhotos.add("");
                selectedPhotos.add("");*/
                selectedPhotos.addAll(photos);
                notePictureItemAdapter.setNotePictureList(selectedPhotos, isPhotoSelect, mAudiofileUrl, audiofileLength);
                notePictureItemAdapter.notifyDataSetChanged();
            } else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_NATIVE_VIDEO) {//本地视频的

            } else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_MERCHANT_SELECT) {

                MerchantListResponse.MerchantEntity merchantEntity = (MerchantListResponse.MerchantEntity) data.getSerializableExtra("merchant");
                mchName = merchantEntity.getMchName();
                mchId = merchantEntity.getMchId();
                mTvMerchantName.setText(mchName);
            } else if(resultCode == RESULT_OK && requestCode == REQUEST_CODE_TAG_SELECT){
                Bundle bundle= data.getExtras();
                HotTagsTopicBean hotTagsTopicBean = (HotTagsTopicBean) bundle.getSerializable("topicResponse");
                if(hotTagsTopicBean != null){

                    hotTagsTopicResponse.add(hotTagsTopicBean);
                }

                tagAdapter.notifyDataChanged();

                if(hotTagsTopicResponse != null && hotTagsTopicResponse.size() > 0)
                {
                    for(int i = 0; i < hotTagsTopicResponse.size();i++){
                        int id = hotTagsTopicResponse.get(i).getId();
                        for(int j = 0;j < selectTopicIdList.size(); j++)
                        {
                            int selectTopicId = selectTopicIdList.get(j);
                            if (id == selectTopicId)
                            {
                                tagAdapter.setSelectedList(i);
                            }
                        }
                }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.rlyt_my_location, R.id.rlyt_add_tag, R.id.iv_back, R.id.rlyt_push_note})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlyt_my_location:

                BlurBehind.getInstance().execute(PublishNoteActivity.this, new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {

                        toActivityForResult(PublishLocationSearchActivity.class, REQUEST_CODE_MERCHANT_SELECT);
                    }
                });

                break;
            case R.id.rlyt_add_tag:
                BlurBehind.getInstance().execute(PublishNoteActivity.this, new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {

                        toActivityForResult(MoreHotTagTopicActivity.class, REQUEST_CODE_TAG_SELECT);
                    }
                });

                break;
            case R.id.iv_back:

                PublishNoteActivity.this.finish();
                break;
            case R.id.rlyt_push_note:

              /*  for (int i = 0; i < photos.size(); i++) {
                    String selectPhotoUrl = photos.get(i);
                    String objectName = getFileName(selectPhotoUrl);
                    mService.asyncPutImage(objectName, selectPhotoUrl);
                }
                String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + AUDIO_WAV_BASEPATH + "0880450f1e4e4e799d6f8ce335c49050.wav";
                String objectName = getAudioFileName(fileBasePath);
                mService.asyncUploadAuidio(objectName, fileBasePath);*/

                //String objectName = getVideoFileName(localVideoPath);
                //mService.asyncUploadAuidio(objectName, localVideoPath);

                // String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "1553772900962.gif";
                //String objectName = getFileName("1553772900962.gif");

                if (validateInternet()) {
                    String profile = mEdtProfile.getText().toString().trim();

                    if (TextUtils.isEmpty(profile)) {

                        showToast(PublishNoteActivity.this, "请输入内容");
                        return;
                    }
                    showProgressDialog(PublishNoteActivity.this);

                    resourceInfoEntityList.clear();
                    fileUploadNum = 0;
                    if (isPhotoSelect) {
                        String objectName = "";
                        byte[] imageByte = null;
                        for (int i = 0; i < photos.size(); i++) {
                            String selectPhotoUrl = photos.get(i);
                            objectName = getFileName(UploadFileTypeEnum.IMAGE.getValue(), selectPhotoUrl);
                            imageByte = getImage(selectPhotoUrl);
                            PublishPostRequest.ResourceInfoEntity resourceInfoEntity = new PublishPostRequest().new ResourceInfoEntity();
                            if (uploadFileType.equals(UploadFileTypeEnum.IMAGE.getValue()))
                            {
                                resourceInfoEntity.setType(UploadFileTypeEnum.IMAGE.getKey());
                                resourceInfoEntity.setWidth(imageWidth);
                                resourceInfoEntity.setHeight(imageHight);
                                resourceInfoEntity.setUrl(objectName);
                                resourceInfoEntity.setSort(i);
                                resourceInfoEntityList.add(resourceInfoEntity);
                            }
                            mService.asyncUploadIamge(objectName, UploadFileTypeEnum.IMAGE.getValue(), imageByte);
                            //publishNote(resourceInfoEntityList);
                        }

                        if (!TextUtils.isEmpty(mAudiofileUrl))
                        {
                            String audioName = getFileName(UploadFileTypeEnum.AUDIO.getValue(), mAudiofileUrl);
                            PublishPostRequest.ResourceInfoEntity resourceInfoEntity = new PublishPostRequest().new ResourceInfoEntity();
                            resourceInfoEntity.setType(UploadFileTypeEnum.AUDIO.getKey());
                            resourceInfoEntity.setUrl(audioName);
                            resourceInfoEntity.setDuration(duration);
                            resourceInfoEntityList.add(resourceInfoEntity);
                            mService.asyncPutFile(audioName, UploadFileTypeEnum.AUDIO.getValue(), mAudiofileUrl);
                        }
                        publishPostRequest.setResource(resourceInfoEntityList);

                    } else {

                        Bitmap bitmap = BitmapUtils.getVideoThumbnail(localVideoPath);
                        imageHight = bitmap.getHeight();
                        imageWidth = bitmap.getWidth();

                        //视频
                        PublishPostRequest.ResourceInfoEntity resourceInfoEntity = new PublishPostRequest().new ResourceInfoEntity();
                        String objectName = getVideoFileName(localVideoPath);
                        resourceInfoEntity.setType(UploadFileTypeEnum.VIDEO.getKey());
                        resourceInfoEntity.setUrl(objectName);
                        resourceInfoEntityList.add(resourceInfoEntity);
                        mService.asyncPutFile(objectName, UploadFileTypeEnum.VIDEO.getValue(), localVideoPath);

                        //gif
                        PublishPostRequest.ResourceInfoEntity resourceInfoGif = new PublishPostRequest().new ResourceInfoEntity();
                        String mGifFileName = getFileName(UploadFileTypeEnum.GIF.getValue(), null) + ".gif";
                        resourceInfoGif.setType(UploadFileTypeEnum.GIF.getKey());
                        resourceInfoGif.setUrl(mGifFileName);
                        resourceInfoEntityList.add(resourceInfoGif);
                        mService.asyncPutFile(mGifFileName, UploadFileTypeEnum.GIF.getValue(), mGifFilePath);
                    }
                }
                break;
            default:
                break;
        }
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
        return new OssService(oss, bucket, displayer, PublishNoteActivity.this);

    }


  /*  public String getFileName(String selectPhoto) {

        stringBuffer.setLength(0);
        stringBuffer.append("images/");
        String date = DateUtil.getTime(new Date(), DateUtil.sDateYMDFormat);
        stringBuffer.append(date + "/");
        String uuid = EncryptedSignatureUtil.getUUID();
        stringBuffer.append(uuid);
        File file = new File(selectPhoto);
        String fileName = file.getName();
        stringBuffer.append(fileName);

        return stringBuffer.toString();
    }*/

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

    public String getVideoFileName(String selectVideoFile) {

        stringBuffer.setLength(0);
        stringBuffer.append("video/");
        String date = DateUtil.getTime(new Date(), DateUtil.sDateYMDFormat);
        stringBuffer.append(date + "/");
        String uuid = EncryptedSignatureUtil.getUUID();
        stringBuffer.append(uuid);
        File file = new File(selectVideoFile);
        String fileName = file.getName();
        stringBuffer.append(fileName);

        return stringBuffer.toString();
    }

    /**
     * 本地视频
     */
    public void getNativeVideo() {
        Intent intent = new Intent();
        intent.setType("video/*"); //选择视频 （mp4 3gp 是android支持的视频格式）
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_CODE_NATIVE_VIDEO);
    }

    public void AsynTaskGifEncoder() {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                showProgressDialog(PublishNoteActivity.this);
                mDialog.setTitle("");
            }

            @Override
            protected Void doInBackground(Void... params) {

                BitmapExtractor extractor = new BitmapExtractor();
                extractor.setFPS(3);
                extractor.setScope(0, 3);
                extractor.setSize(540, 960);
                List<Bitmap> bitmaps = extractor.createBitmaps(PublishNoteActivity.this, localVideoPath);

                mGifFileName = String.valueOf(System.currentTimeMillis()) + ".gif";
                mGifFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + mGifFileName;

                GIFEncoder encoder = new GIFEncoder();
                encoder.init(bitmaps.get(0));
                encoder.start(mGifFilePath);
                for (int i = 1; i < bitmaps.size(); i++)
                {
                    encoder.addFrame(bitmaps.get(i));
                }
                encoder.finish();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                dismissProgressDialog();
             /*   tip.setText(R.string.building_complete);
                selectVideo.setText(R.string.select_video);
                Toast.makeText(getApplicationContext(), "存储路径" + filePath, Toast.LENGTH_LONG).show();*/
            }
        }.execute();
    }

    @Override
    public void createTopicResult(BackResult res) {

    }

    @Override
    public void topicSearchResult(BackResult<TagTopicSearchResponse> res) {

    }

    @Override
    public void publishPostResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    showToast(PublishNoteActivity.this, "发布成功");
                    PublishNoteActivity.this.finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(PublishNoteActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getHotTagsTopicListResult(BackResult<List<HotTagsTopicBean>> res) {  //主题标签
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    hotTagsTopicResponse = res.getData();
                    tagAdapter = new TagAdapter<HotTagsTopicBean>(hotTagsTopicResponse) {
                        @Override
                        public View getView(FlowLayout parent, int position, HotTagsTopicBean hotTagsTopicEntity) {
                            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_post_topic,
                                    mTagHotLabel, false);

                            TextView tv = view.findViewById(R.id.tv_flowlayout);
                            String title = hotTagsTopicEntity.getTitle();
                            tv.getBackground().setAlpha(99);
                            tv.setText(title);

                            return view;
                        }
                    };
                    mTagHotLabel.setAdapter(tagAdapter);

                    mTagHotLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                        @Override
                        public boolean onTagClick(View view, int position, FlowLayout parent) {
                            selectTopicIdList.clear();

                            //view.setVisibility(View.GONE);
                            selectPosSet = mTagHotLabel.getSelectedList();
                            Iterator it = selectPosSet.iterator();
                            while (it.hasNext()) {
                                int index = (int) it.next();
                                int topicId = hotTagsTopicResponse.get(index).getId();
                                selectTopicIdList.add(topicId);

                            }
                          //  Toast.makeText(PublishNoteActivity.this, selectTopicIdList.toString(), Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                    mTagHotLabel.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                        @Override
                        public void onSelected(Set<Integer> selectPosSet) {

                            //showToast(PublishNoteActivity.this, selectPosSet.toString());

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(PublishNoteActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getMerchantListResult(BackResult<MerchantListResponse> res) {


    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(PublishNoteActivity.this, Constants.getResultMsg(msg));
    }

    /**
     * 发布帖子
     */
    public void publishNote() {

        if (validateInternet()) {
            String profile = mEdtProfile.getText().toString().trim();

            publishPostRequest.setContent(profile);
            publishPostRequest.setLatitude(mLatitude);
            publishPostRequest.setLongitude(mLongitude);
            publishPostRequest.setMchIds(mchId);
            publishPostRequest.setSelectedLatitude(mLatitude);
            publishPostRequest.setSelectedLongitude(mLongitude);
            publishPostRequest.setResource(resourceInfoEntityList);

            publishPostRequest.setTopicIds(selectTopicIdList);

            mPresenter.publishPost(publishPostRequest);
        }
    }

    //获取热门标签(主题搜索 热门标签)
    public void getHotTagsTopicList() {

        if (validateInternet()) {

            mPresenter.getHotTagsTopicList(HotTagsTypeEnum.TOPIC.getValue());
        }
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


    /**
     * 初始化定位
     */
    private void initLocation() {

        //初始化client
        locationClient = new AMapLocationClient(PublishNoteActivity.this);
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);

        startLocation();
    }


    /**
     * 默认的定位参数
     *
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    /**
     * 开始定位
     */
    private void startLocation() {
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }


    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                mLongitude = String.valueOf(location.getLongitude());
                mLatitude = String.valueOf(location.getLatitude());

             /*   StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if(location.getErrorCode() == 0){
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //定位完成的时间
                    sb.append("定位时间: " + MapUtils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }
                sb.append("***定位质量报告***").append("\n");
                sb.append("* WIFI开关：").append(location.getLocationQualityReport().isWifiAble() ? "开启":"关闭").append("\n");
                sb.append("* GPS状态：").append(getGPSStatusString(location.getLocationQualityReport().getGPSStatus())).append("\n");
                sb.append("* GPS星数：").append(location.getLocationQualityReport().getGPSSatellites()).append("\n");
                sb.append("* 网络类型：" + location.getLocationQualityReport().getNetworkType()).append("\n");
                sb.append("* 网络耗时：" + location.getLocationQualityReport().getNetUseTime()).append("\n");
                sb.append("****************").append("\n");
                //定位之后的回调时间
                sb.append("回调时间: " + MapUtils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");

                //解析定位结果，
                String result = sb.toString();*/
            } else {
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(noteSaveExitPromptDialog != null)
        {
            noteSaveExitPromptDialog = null;
        }

        if(cameraCapturePromptDialog != null)
        {
            cameraCapturePromptDialog = null;
        }
    }

    public void cameraCapturePromptDialog(){

        if(cameraCapturePromptDialog == null)
        {
            cameraCapturePromptDialog = new NoteSaveExitPromptDialog(PublishNoteActivity.this).builder();

            cameraCapturePromptDialog.addSheetItem(getResources().getString(R.string.str_take_a_picture), NoteSaveExitPromptDialog.SheetItemColor.Gray, new NoteSaveExitPromptDialog.OnSheetItemClickListener() {
                @Override
                public void onClick(int which) {

                    isPhotoSelect = true;
                    getNotePhotoPicker(MimeType.ofImage(),true);
                }
            });
            cameraCapturePromptDialog.addSheetItem(getResources().getString(R.string.str_video_shooting), NoteSaveExitPromptDialog.SheetItemColor.Gray, new NoteSaveExitPromptDialog.OnSheetItemClickListener() {
                @Override
                public void onClick(int which) {

                    isPhotoSelect = false;
                    showToast(PublishNoteActivity.this,"此功能正在开发中...");
                }
            });
            cameraCapturePromptDialog.addSheetItem(getResources().getString(R.string.str_select_local_video), NoteSaveExitPromptDialog.SheetItemColor.Gray, new NoteSaveExitPromptDialog.OnSheetItemClickListener() {
                @Override
                public void onClick(int which) {

                    isPhotoSelect = false;
                    getNotePhotoPicker(MimeType.ofVideo(),false);
                }
            });

            cameraCapturePromptDialog.setSheetItems();
        }
        cameraCapturePromptDialog.show();
    }
}
