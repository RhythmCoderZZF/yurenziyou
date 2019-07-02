package com.nbhysj.coupon.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
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
import android.view.ViewGroup;
import android.widget.CheckBox;
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
import com.nbhysj.coupon.view.MyRecycleView;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
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
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

import static com.nbhysj.coupon.oss.Config.STS_SERVER_URL;
import static com.nbhysj.coupon.oss.audio.FileUtils.AUDIO_WAV_BASEPATH;

/**
 * @auther：hysj created on 2019/03/02
 * description：分享发布
 */
public class PublishNoteActivity extends BaseActivity<PublishPostPresenter, PublishPostModel> implements PublishPostContract.View {
    //声音录入
    private int REQUEST_CODE_SOUND_PHOTO_RECORD = 0;
    //照片选取返回code
    private int REQUEST_CODE_NOTE_PHOTO = 1;
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

    private int imageUploadNum;

    private String mAudiofileName;

    private Set<Integer> selectPosSet;

    private List<String> selectTagList;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_publish_note;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        if (photos == null) {

            photos = new ArrayList<>();
        } else {
            photos.clear();
        }

        if (selectTagList == null) {
            selectTagList = new ArrayList();
        } else {
            selectTagList.clear();
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRvNotePicture.setNestedScrollingEnabled(false);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvNotePicture.setLayoutManager(linearLayoutManager);

        notePictureItemAdapter = new NotePictureItemAdapter(PublishNoteActivity.this);
        //  selectedPhotos.add("");
        //selectedPhotos.add("");
        notePictureItemAdapter.setNotePictureList(selectedPhotos, isPhotoSelect, null);
        notePictureItemAdapter.setNotePictureListener(new NotePictureItemAdapter.NotePictureListener() {
            @Override
            public void setNotePictureListener(int position, boolean isSelectPictureDelete) {

                if (photos.size() > 0) {

                    if (position == 0) {

                        showToast(PublishNoteActivity.this, "声音胶囊");

                    } else if (position == 1) {
                        isPhotoSelect = true;
                        getNotePhotoPicker(REQUEST_CODE_NOTE_PHOTO, 9, selectedPhotos.size());
                    } else {
                        PhotoPreview.builder()
                                .setPhotos(selectedPhotos)
                                .setCurrentItem(position - 2)
                                .start(PublishNoteActivity.this);
                    }
                } else {

                    if (position == 0) {
                        isPhotoSelect = true;
                        //getNativeVideo();
                        getNotePhotoPicker(REQUEST_CODE_NOTE_PHOTO, 9, selectedPhotos.size());
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
                imageUploadNum++;
                int imageNum = photos.size();
                if (imageUploadNum == imageNum) {
                    publishNote();
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

                showToast(PublishNoteActivity.this, "121");
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);

    }

    public void getNotePhotoPicker(final int type, final int max, final int size) {
        AndPermission.with(PublishNoteActivity.this)
                .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

                        PhotoPicker.builder()
                                .setPhotoCount(9)
                                .setShowCamera(true)
                                .setPreviewEnabled(false)
                                .setSelected(selectedPhotos)
                                .start(PublishNoteActivity.this);
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


            if (resultCode == -1 &&
                    (requestCode == 233 || requestCode == 233)) {
                ArrayList<String> photos = null;
                if (data != null) {
                    photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                }

                Intent intent = new Intent();
                intent.setClass(PublishNoteActivity.this, PublishSoundRecordingActivity.class);
                intent.putStringArrayListExtra("publishPictureUrlList", (ArrayList<String>) photos);
                startActivityForResult(intent, REQUEST_CODE_SOUND_PHOTO_RECORD);
            } else if (resultCode == -1 &&
                    (requestCode == 666 || requestCode == 666)) {
                if (data != null) {
                    photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                }
                selectedPhotos.clear();
                selectedPhotos.addAll(photos);
                notePictureItemAdapter.setNotePictureList(selectedPhotos, isPhotoSelect, mAudiofileName);
                mRvNotePicture.setAdapter(notePictureItemAdapter);
            } else if (requestCode == REQUEST_CODE_SOUND_PHOTO_RECORD && resultCode == RESULT_OK) {
                photos = data.getStringArrayListExtra("publishPictureUrlList");
                mAudiofileName = data.getStringExtra("mAudiofileName");
                selectedPhotos.clear();
              /* selectedPhotos.add("");
                selectedPhotos.add("");*/
                selectedPhotos.addAll(photos);
                notePictureItemAdapter.setNotePictureList(selectedPhotos, isPhotoSelect, mAudiofileName);
                notePictureItemAdapter.notifyDataSetChanged();
            } else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_NATIVE_VIDEO) {//本地视频的
                selectedPhotos.clear();
                Uri uriVideo = data.getData();
                localVideoPath = BitmapUtils.INSTANCE.getPhotoPathFromContentUri(this, uriVideo);
                selectedPhotos.add(localVideoPath);
                notePictureItemAdapter.setNotePictureList(selectedPhotos, isPhotoSelect, null);
                notePictureItemAdapter.notifyDataSetChanged();
                AsynTaskGifEncoder();
            } else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_MERCHANT_SELECT) {

                MerchantListResponse.MerchantEntity merchantEntity = (MerchantListResponse.MerchantEntity) data.getSerializableExtra("merchant");
                String mchName = merchantEntity.getMchName();
                mTvMerchantName.setText(mchName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.rlyt_location, R.id.rlyt_add_tag, R.id.iv_back, R.id.rlyt_push_note})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlyt_location:

                toActivityForResult(PublishLocationSearchActivity.class, REQUEST_CODE_MERCHANT_SELECT);
                break;
            case R.id.rlyt_add_tag:
                toActivityForResult(MoreHotTagTopicActivity.class, REQUEST_CODE_TAG_SELECT);
                break;
            case R.id.iv_back:
                NoteSaveExitPromptDialog actionSheetDialog = new NoteSaveExitPromptDialog(PublishNoteActivity.this).builder();
                actionSheetDialog.addSheetItem("保存草稿", NoteSaveExitPromptDialog.SheetItemColor.Blue, new NoteSaveExitPromptDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {

                    }
                });
                actionSheetDialog.addSheetItem("放弃编辑", NoteSaveExitPromptDialog.SheetItemColor.Blue, new NoteSaveExitPromptDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        PublishNoteActivity.this.finish();
                    }
                }).show();
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

                showProgressDialog(PublishNoteActivity.this);
                resourceInfoEntityList.clear();
                imageUploadNum = 0;
                String objectName = "";
                byte[] imageByte = null;
                for (int i = 0; i < photos.size(); i++) {
                    String selectPhotoUrl = photos.get(i);
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
                    }
                    mService.asyncUploadFile(objectName, UploadFileTypeEnum.IMAGE.getValue(), imageByte);
                    //publishNote(resourceInfoEntityList);
                }
                publishPostRequest.setResource(resourceInfoEntityList);


             /*   if(isPhotoSelect){

                } else {
                    String objectName = getVideoFileName(localVideoPath);
                    mService.asyncPutImage(objectName, UploadFileTypeEnum.VIDEO.getValue(), localVideoPath);

                    String mGifFileName = getFileName(UploadFileTypeEnum.GIF.getValue(), null) + ".gif";
                    mService.asyncPutImage(mGifFileName, UploadFileTypeEnum.GIF.getValue(), mGifFilePath);
                }*/
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

        credentialProvider = new OSSAuthCredentialsProvider(STS_SERVER_URL);

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
                for (int i = 1; i < bitmaps.size(); i++) {
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

                    System.out.print("112");
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
    public void getHotTagsTopicListResult(BackResult<List<HotTagsTopicBean>> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    List<HotTagsTopicBean> hotTagsTopicResponse = res.getData();
                    TagAdapter tagAdapter = new TagAdapter<HotTagsTopicBean>(hotTagsTopicResponse) {
                        @Override
                        public View getView(FlowLayout parent, int position, HotTagsTopicBean hotTagsTopicEntity) {
                            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_check_frame,
                                    mTagHotLabel, false);
                            selectTagList.clear();
                            TextView tv = view.findViewById(R.id.tv_flowlayout);
                            String title = hotTagsTopicEntity.getTitle();
                            tv.getBackground().setAlpha(99);
                            tv.setText(title);

                          /*  tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                 //   setSelectedList(position);
                                    selectPosSet = mTagHotLabel.getSelectedList();

                                }
                            });*/

                            return view;
                        }
                    };
                    mTagHotLabel.setAdapter(tagAdapter);

                    mTagHotLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                        @Override
                        public boolean onTagClick(View view, int position, FlowLayout parent) {
                            selectTagList.clear();

                            //view.setVisibility(View.GONE);
                            selectPosSet = mTagHotLabel.getSelectedList();
                            Iterator it = selectPosSet.iterator();
                            while (it.hasNext()) {
                                int index = (int) it.next();
                                String title = hotTagsTopicResponse.get(index).getTitle();
                                selectTagList.add(title);

                            }
                            Toast.makeText(PublishNoteActivity.this, selectTagList.toString(), Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                    mTagHotLabel.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                        @Override
                        public void onSelected(Set<Integer> selectPosSet) {

                            showToast(PublishNoteActivity.this, selectPosSet.toString());

                        }
                    });

                    // mTagHotLabel.getSelectedList();
                  /*  FlowLayoutTagAdapter flowLayoutTagAdapter = new FlowLayoutTagAdapter(PublishNoteActivity.this);
                    flowLayoutTagAdapter.setHotTagsTopicList(hotTagsTopicList);
                    mTagHotLabel.setAdapter(flowLayoutTagAdapter);*/
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

            if (TextUtils.isEmpty(profile)) {

                showToast(PublishNoteActivity.this, "请输入内容");
                return;
            }

            publishPostRequest.setContent(profile);
            publishPostRequest.setIntro("123");
            publishPostRequest.setLatitude("40.0");
            publishPostRequest.setLongitude("40.0");
            List<Integer> mchIds = new ArrayList<>();
            mchIds.add(1);
            publishPostRequest.setMchIds(mchIds);

            publishPostRequest.setSelectedLatitude("40.0");
            publishPostRequest.setSelectedLongitude("40.0");
            publishPostRequest.setResource(resourceInfoEntityList);
            List<Integer> topicIds = new ArrayList<>();
            topicIds.add(1);
            publishPostRequest.setTopicIds(topicIds);

            mPresenter.publishPost(publishPostRequest);
        }
    }

    //获取热门标签
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
}
