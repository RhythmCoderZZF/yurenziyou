package com.nbhysj.coupon.ui;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.BuildConfig;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.contract.LoginContract;
import com.nbhysj.coupon.dialog.AuthorityAllProhibitDialog;
import com.nbhysj.coupon.dialog.AuthorityVerificationDialog;
import com.nbhysj.coupon.dialog.UserPrivacyAgreementDialog;
import com.nbhysj.coupon.dialog.UserPrivacyNoAgreementDialog;
import com.nbhysj.coupon.dialog.VehicleServiceAgreementTipsDialog;
import com.nbhysj.coupon.framework.Net;
import com.nbhysj.coupon.model.LoginModel;
import com.nbhysj.coupon.model.response.AuthorityVerificationBean;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.presenter.LoginPresenter;
import com.nbhysj.coupon.util.DownloadAPK;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.StreamUtil;
import com.nbhysj.coupon.util.permission.PermissionListener;
import com.nbhysj.coupon.util.permission.PermissionUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.umeng.socialize.Config.appName;

public class LoadingActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {
    //版本code
    private int mLocalVersionCode;
    //版本名
    private String mLocalVersionName;

    private String mVersionName;
    private int mVersionCode;

    private int mVersionForce = 0;
    private String mVersionDesc;
    private AlertDialog versionUpdateAlertDialog;
    private List<AuthorityVerificationBean> authorityVerificationList;
    private List<AuthorityVerificationBean> authorityProhibitList;

    private ProgressDialog pb;

    private String mDownloadUrl;
    /**
     * url地址出错状态码
     */
    protected static final int URL_ERROR = 102;
    protected static final int IO_ERROR = 103;
    protected static final int JSON_ERROR = 104;
    protected static final int UPDATE_VERSION = 100;
    /**
     * 进入应用程序主界面状态码
     */
    protected static final int ENTER_HOME = 101;

    private UserPrivacyAgreementDialog userPrivacyNoAgreementDialog;

    //是否是第一次进入APP
    private boolean initFirst = true;

    @Override
    public void getLoginVerifyCodeResult(BackResult res) {

    }

    @Override
    public void getLoginSaltResult(BackResult res) {

    }

    @Override
    public void loginResult(BackResult<LoginResponse> res) {

    }

    @Override
    public void getUserInfoResult(BackResult<UserInfoResponse> res) {

    }

    @Override
    public void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res) {

    }

    @Override
    public void thirdPartyLoginResult(BackResult<LoginResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public int getLayoutId() {

        return R.layout.activity_loading;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        initFirst = SharedPreferencesUtils.getInitFirst(LoadingActivity.this);
    }

    @Override
    public void initData() {
        mLocalVersionName = getCurrentVersionName();
        mLocalVersionCode = getLocalVersionCode();
        //检查wifi网络下载app
        if (validateInternet()) {
            checkVersion(Net.APP_UPDATE_URL);
        } else {
            setNetwork();
        }
    }

    private void checkVersion(final String urlUpdate) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                long startTime = System.currentTimeMillis();
                try {
                    URL url = new URL(urlUpdate);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(2000);
                    connection.setReadTimeout(2000);
                    if (connection.getResponseCode() == 200) {
                        InputStream is = connection.getInputStream();
                        String json = StreamUtil.streamToString(is);
                        JSONObject jsonObject = new JSONObject(json);
                        JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                        mVersionName = jsonObjectData.getString("version");
                        mVersionCode = jsonObjectData.getInt("versionCode");
                        mVersionDesc = jsonObjectData.getString("updateNote");
                        mDownloadUrl = jsonObjectData.getString("downloadApkUrl");
                        mVersionForce = jsonObjectData.getInt("updateStatus");
                        if (mLocalVersionCode < mVersionCode && SharedPreferencesUtils.getSkippedVersionCode(LoadingActivity.this) < mVersionCode) {
                            msg.what = UPDATE_VERSION;
                            SharedPreferencesUtils.setVersionName(getApplicationContext(), mVersionName);
                        } else {
                            msg.what = ENTER_HOME;
                        }
                    } else {
                        msg.what = ENTER_HOME;
                    }
                } catch (MalformedURLException e) {
                    msg.what = URL_ERROR;
                } catch (IOException e) {
                    msg.what = IO_ERROR;
                } catch (JSONException e) {
                    msg.what = JSON_ERROR;
                } finally {
                    long endTime = System.currentTimeMillis();
                    if (endTime - startTime < 2000) {
                        try {
                            Thread.sleep(2000 - (endTime - startTime));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    mHandler.sendMessage(msg);
                }
            }
        }).start();

    }


    private Handler mHandler = new Handler() {
        @Override
        //alt+ctrl+向下箭头,向下拷贝相同代码
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_VERSION:
                    //提示更新信息
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    },1000);
                    showUpdateDialog();
                    showUserPrivacyDialog();

                    break;
                case ENTER_HOME:
                    showUserPrivacyDialog();
                    break;
                case URL_ERROR:
                    showUserPrivacyDialog();
                    break;
                case IO_ERROR:
                    showUserPrivacyDialog();
                    break;
                case JSON_ERROR:
                    showUserPrivacyDialog();
                    break;
            }
        }
    };


    /**
     * 弹出对话框,提示用户更新
     */

    protected void showUpdateDialog() {

        final View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_dialog_update, null);
        TextView tv_description = (TextView) dialogView.findViewById(R.id.tv_dialog_description);
        TextView tv_dialog_confirm = (TextView) dialogView.findViewById(R.id.tv_dialog_confirm);
        TextView tv_dialog_cancal = (TextView) dialogView.findViewById(R.id.tv_dialog_cancel);
        TextView tv_dialog_skip = (TextView) dialogView.findViewById(R.id.tv_dialog_skip);
        LinearLayout ll_hide_in_force = (LinearLayout) dialogView.findViewById(R.id.ll_hide_in_force);
        versionUpdateAlertDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(mVersionForce == 1 ? false : true)
                .create();

        versionUpdateAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                requestPermission();
                dialog.dismiss();
            }
        });
        if (mVersionDesc.trim().length() > 0) {
            tv_description.setText(mVersionDesc);
        }
        if (mVersionForce == 1) {
            ll_hide_in_force.setVisibility(View.GONE);
        } else {
            ll_hide_in_force.setVisibility(View.VISIBLE);
        }
        tv_dialog_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   SharedPreferencesUtil.setSkippedVersionCode(LoadingActivity.this, mVersionCode);
                requestPermission();
                versionUpdateAlertDialog.dismiss();
            }
        });
        tv_dialog_cancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
                versionUpdateAlertDialog.dismiss();
            }
        });
        tv_dialog_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //下载apk,apk链接地址,downloadUrl
                pb = new ProgressDialog(LoadingActivity.this);
                pb.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pb.setCancelable(false);
                pb.setCanceledOnTouchOutside(false);
                pb.setTitle("安装包下载中。。。");
                pb.setProgressDrawable(getResources().getDrawable(R.drawable.progress_drawable));
                pb.show();
                String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator + "yurenziyou.apk";
                File file = new File(path);
                if (file.exists()) {
                    file.delete();
                }
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                versionUpdateAlertDialog.dismiss();
                downloadApk(path);
            }
        });

        /*tv_dialog_confirm.setOnClickListener(new View.OnClViewPagerickListener() {
            @Override
            public void onClick(View v) {
                //下载apk,apk链接地址,downloadUrl
                pb = new ProgressDialog(LoadingActivity.this);
                pb.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pb.setCancelable(false);
                pb.setCanceledOnTouchOutside(false);
                pb.setTitle("安装包下载中。。。");
                pb.setProgressDrawable(getResources().getDrawable(R.drawable.progress_drawable));
                pb.show();
                String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator + "fxq.apk";
                File file = new File(path);
                if (file.exists()) {
                    file.delete();
                }
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                alertDialog.dismiss();
                downloadApk(path);
            }
        });*/
    }

    private void requestPermission() {

        if (authorityVerificationList == null) {
            authorityVerificationList = new ArrayList<>();
        } else {
            authorityVerificationList.clear();
        }
        String permissionStr[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA};
        //找出所有未授权的权限
        for (String permission : permissionStr) {
            if (ContextCompat.checkSelfPermission(LoadingActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

                if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AuthorityVerificationBean writeStorageAuthority = new AuthorityVerificationBean();
                    writeStorageAuthority.setImage(R.mipmap.icon_stroge);
                    writeStorageAuthority.setAuthorityName(getResources().getString(R.string.str_storage_permission));
                    writeStorageAuthority.setAuthorityDescription(getResources().getString(R.string.str_storage_authority_description));
                    authorityVerificationList.add(writeStorageAuthority);
                }

                if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    AuthorityVerificationBean locationAuthority = new AuthorityVerificationBean();
                    locationAuthority.setImage(R.mipmap.icon_location);
                    locationAuthority.setAuthorityName(getResources().getString(R.string.str_location_permission));
                    locationAuthority.setAuthorityDescription(getResources().getString(R.string.str_location_authority_description));
                    authorityVerificationList.add(locationAuthority);
                }

                if (permission.equals(Manifest.permission.READ_PHONE_STATE)) {
                    AuthorityVerificationBean readPhoneAuthority = new AuthorityVerificationBean();
                    readPhoneAuthority.setImage(R.mipmap.icon_phone);
                    readPhoneAuthority.setAuthorityName(getResources().getString(R.string.str_read_phone_state_permission));
                    readPhoneAuthority.setAuthorityDescription(getResources().getString(R.string.str_read_phone_state_authority_description));
                    authorityVerificationList.add(readPhoneAuthority);
                }

                if (permission.equals(Manifest.permission.CAMERA)) {
                    AuthorityVerificationBean cameraAuthority = new AuthorityVerificationBean();
                    cameraAuthority.setImage(R.mipmap.icon_camera);
                    cameraAuthority.setAuthorityName(getResources().getString(R.string.str_camra_permission));
                    cameraAuthority.setAuthorityDescription(getResources().getString(R.string.str_camra_authority_description));
                    authorityVerificationList.add(cameraAuthority);
                }
            }
        }
        if (authorityVerificationList.size() > 0) {
            new AuthorityVerificationDialog(LoadingActivity.this).builder(authorityVerificationList).setOpenAuthorityVerification(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPermission();
                }
            }).setDeniedPermissionExit(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.exit(0);
                }
            }).setCancelable(false).show();
        } else {


            if (versionUpdateAlertDialog != null) {
                versionUpdateAlertDialog.show();
            } else {


                toActivity(MainActivity.class);

            }
           /* if (alertDialog == null) {
                lastScopeNum = 0;
                getModelAllVersions();
            } else {
                alertDialog.show();
            }*/
        }
    }

    private void setNetwork() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("网络不可用");
        builder.setMessage("请设置网络");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    protected void downloadApk(final String path) {
        DownloadAPK downloadAPK = new DownloadAPK();
        downloadAPK.setOnDownloadListener(new DownloadAPK.OnDownloadListener() {
            @Override
            public void onStart(int fileSize) {
                pb.setMax(fileSize);
            }

            @Override
            public void onProgress(int downloadedSize) {
                pb.setProgress(downloadedSize);
            }

            @Override
            public void onFinished(boolean isFinished) {
                pb.dismiss();
                //   installApk(new File(path));
                getInstallIntent(path);

                //installApk(new File(path));
            }
        });
        downloadAPK.download(mDownloadUrl, path);

    }

    @Override
    public void initPresenter() {

    }

    public void openPermission() {

        /**
         * 点击检查 相机、打电话 权限
         */
        PermissionUtil permissionUtil = new PermissionUtil(LoadingActivity.this);
        permissionUtil.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                new PermissionListener() {
                    @Override
                    public void onGranted() {
                        //所有权限都已经授权
                        if (versionUpdateAlertDialog == null) {
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    //showUserPrivacyDialog();
                                    toActivity(MainActivity.class);
                                }
                            }, 800);
                        } else {
                            versionUpdateAlertDialog.show();
                        }
                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {
                        //Toast第一个被拒绝的权限
                        requestPermission();

                    }

                    @Override
                    public void onShouldShowRationale(List<String> deniedPermission) {
                        //Toast第一个勾选不在提示的权限
                        // Toast.makeText(LoadingActivity.this, "这个权限" + deniedPermission.get(0)+"勾选了不在提示，要像用户解释为什么需要这权限", Toast.LENGTH_LONG).show();
                        if (authorityProhibitList == null) {
                            authorityProhibitList = new ArrayList<>();
                        } else {
                            authorityProhibitList.clear();
                        }
                        for (String permission : deniedPermission) {
                            if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                                AuthorityVerificationBean locationAuthority = new AuthorityVerificationBean();
                                locationAuthority.setAuthorityName(getResources().getString(R.string.str_location_permission));
                                authorityProhibitList.add(locationAuthority);
                            }

                            if (permission.equals(Manifest.permission.READ_PHONE_STATE)) {
                                AuthorityVerificationBean readPhoneAuthority = new AuthorityVerificationBean();
                                readPhoneAuthority.setAuthorityName(getResources().getString(R.string.str_read_phone_state_permission));
                                authorityProhibitList.add(readPhoneAuthority);
                            }

                            if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                AuthorityVerificationBean writeStorageAuthority = new AuthorityVerificationBean();
                                writeStorageAuthority.setAuthorityName(getResources().getString(R.string.str_storage_permission));
                                authorityProhibitList.add(writeStorageAuthority);
                            }

                            if (permission.equals(Manifest.permission.CAMERA)) {
                                AuthorityVerificationBean cameraAuthority = new AuthorityVerificationBean();
                                cameraAuthority.setAuthorityName(getResources().getString(R.string.str_camra_permission));
                                authorityProhibitList.add(cameraAuthority);
                            }
                        }

                        if (authorityProhibitList.size() > 0) {
                            new AuthorityAllProhibitDialog(LoadingActivity.this).builder(authorityProhibitList).setOpenAuthorityVerification(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    goAppDetailSettingIntent(LoadingActivity.this);
                                    LoadingActivity.this.finish();
                                }
                            }).setDeniedPermissionExit(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    System.exit(0);
                                }
                            }).setCancelable(false).show();
                        }
                    }
                });
    }

    public static void goAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(localIntent);

    }


    private Intent getInstallIntent(String fileName) {

        // String fileName = savePath + appName + ".apk";
       /* String fileName = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "yurenziyou.apk";*/
        Uri uri = null;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            if (Build.VERSION.SDK_INT >= 24) {//7.0 Android N
                //com.xxx.xxx.fileprovider为上述manifest中provider所配置相同
                uri = FileProvider.getUriForFile(mContext, "com.nbhysj.coupon.fileprovider", new File(fileName));

                intent.setAction(Intent.ACTION_INSTALL_PACKAGE);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//7.0以后，系统要求授予临时uri读取权限，安装完毕以后，系统会自动收回权限，该过程没有用户交互
            } else {//7.0以下
                uri = Uri.fromFile(new File(fileName));
                intent.setAction(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            startActivity(intent);
            return intent;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return intent;
    }

    /**
     * 安装 apk 文件
     *
     * @param apkFile apk 文件
     */
    private void installApk(File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //放在此处
        //由于没有在Activity环境下启动Activity,所以设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri apkUri = null;
        //判断版本是否是 7.0 及 7.0 以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            apkUri = FileProvider.getUriForFile(LoadingActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", apkFile);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            apkUri = Uri.fromFile(apkFile);
        }

        intent.setDataAndType(apkUri,
                "application/vnd.android.package-archive");
        startActivity(intent);
    }


    public void showUserPrivacyDialog() {
        /*if (mVehicleServiceAgreementDialog == null)
        {
            mVehicleServiceAgreementDialog = new VehicleServiceAgreementDialog(new VehicleServiceAgreementDialog.PurchaseInstructionsListener() {
                @Override
                public void setPurchaseInstructionsCallback(MchGoodsBean mchGoodsBean) {
                    String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
                    if (!TextUtils.isEmpty(token)) {



                    } else {

                        onReLogin("");
                    }
                }
            },"http://wwww.baidu.com");
        }
        mVehicleServiceAgreementDialog.show(getFragmentManager(), "用车服务协议");*/
        if (initFirst) {
            if (userPrivacyNoAgreementDialog == null) {
                userPrivacyNoAgreementDialog = new UserPrivacyAgreementDialog(LoadingActivity.this, new UserPrivacyAgreementDialog.UserPrivacyAgreementListener() {
                    @Override
                    public void setUserPrivacyAgreementCallback() {
                        SharedPreferencesUtils.setInitFirst(LoadingActivity.this, false);
                        userPrivacyNoAgreementDialog.dialogDismiss();
                        requestPermission();
                    }

                    @Override
                    public void setUserPrivacyNoAgreementCallback() {

                        showToast(LoadingActivity.this, "请同意并接受《用户协议》和《隐私政策》全部条款后再使用鱼人自游APP");
                    }
                }).builder().setCancelable(false).setContent("<p>欢迎使用鱼人自游APP。我们非常重视您</p><p>的用户权益与个人信息的保护，在您使用</p><p>鱼人自游APP服务前,请认真阅读" + "<font color='#4895F2'>《“鱼人</font></p><font color='#4895F2'>自游”用户协议》</font>和<font color='#4895F2'>《“鱼人</font><font color='#4895F2'>自游”隐私政<p>策》</font>全部条款。我们将通过上述协议向您</p>说明我们如何为您提供服务并保障您的用<p>户权益，如何收集、使用、保存、共享和</p><p>保护您的相关信息，以及我们为您提供的</p><p>访问、更正、删除和申诉您信息相关问题</p><p>的方式。我们会严格按照您的授权，在上</p><p>述协议约定的范围内收集、存储和使用您</p><p>的注册信息、设备信息、位置信息、日志</p><p>信息、语音信息或其他经您授权的信息。</p><p>您点击“同意并继续”视为您已同意上述协</p><p>议的全部内容。</p>");
            }
            userPrivacyNoAgreementDialog.show();
        } else {
            requestPermission();
        }
    }
}
