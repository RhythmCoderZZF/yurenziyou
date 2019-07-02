package com.nbhysj.coupon.oss;


import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSAuthCredentialsProvider;

public class OSSWrapper {

    private static final OSSWrapper WRAPPER = new OSSWrapper();
    private OSSClient mClient = null;
    private static final String STS_INFO_URL = "http://192.168.1.189:8085/api/sts";
    private static final String OSS_ENDPOINT = "http://sts.cn-hangzhou.aliyuncs.com";

    private OSSWrapper() {
        OSSAuthCredentialsProvider authCredentialsProvider = new OSSAuthCredentialsProvider(STS_INFO_URL);
        mClient = new OSSClient(OSSApplication.getInstance(), OSS_ENDPOINT, authCredentialsProvider);
    }

    public static OSSWrapper sharedWrapper() {
        return WRAPPER;
    }

    public OSSClient getClient() {
        return mClient;
    }
}
