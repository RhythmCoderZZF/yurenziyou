package com.nbhysj.coupon;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import com.nbhysj.coupon.greendao.DaoMaster;
import com.nbhysj.coupon.greendao.DaoSession;
import com.nbhysj.coupon.oss.audio.AndroidAudioConverter;
import com.nbhysj.coupon.oss.audio.ILoadCallback;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import cn.jiguang.verifysdk.api.JVerificationInterface;


/**
 * @auther：hysj created at 2019/01/23
 * description：全局应用类
 */
public class BasicApplication extends Application {

    private static BasicApplication sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;

        initImageLoader();
        initGreenDao();

        JVerificationInterface.setDebugMode(true);
        JVerificationInterface.init(this);
        SharedPreferencesUtils.getInstance(getAppContext(), SharedPreferencesUtils.FILE_NAME);

        UMConfigure.init(this, "5c7b23993fc1950ee60010fb", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(true);
        //友盟相关平台配置。注意友盟官方新文档中没有这项配置，但是如果不配置会吊不起来相关平台的授权界面
        PlatformConfig.setWeixin("wx0e89ba9a3fc95e1e", "0cd5a7bd60a45a679d646ce8861f6655");//微信APPID和AppSecret
        PlatformConfig.setQQZone("101425689", "6375e4ecf2e858c3e643a151244220bc");//QQAPPID和AppSecret
        PlatformConfig.setSinaWeibo("2613993565", "1fe67d4e057992dc7f5d52cd9a4721e8", "http://www.baidu.com");//微博

        AndroidAudioConverter.load(this, new ILoadCallback() {
            @Override
            public void onSuccess() {
                // Great!
            }

            @Override
            public void onFailure(Exception error) {
                // FFmpeg is not supported by device
                error.printStackTrace();
            }
        });
    }

    public static Context getAppContext() {

        return sApp;
    }

    public static Resources getAppResources() {

        return sApp.getResources();
    }


    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "murloc.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;

    public DaoSession getDaoSession() {
        return daoSession;
    }

    private void initImageLoader() {
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);

    }
}
