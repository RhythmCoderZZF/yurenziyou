package com.nbhysj.coupon.framework;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nbhysj.coupon.BasicApplication;
import com.nbhysj.coupon.BuildConfig;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.ui.BaseActivity;
import com.nbhysj.coupon.util.EncryptedSignatureUtil;
import com.nbhysj.coupon.util.MD5Util;
import com.nbhysj.coupon.util.NetWorkUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;

import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static android.support.constraint.Constraints.TAG;

/**
 * created by hysj at 2019/01/23.
 * description : Okhttp网络访问层
 */
public class Api {

    public static final String HTTP_CACHE_DIR = "turnstile_cache";

    /**
     * 环境基地址
     */
    public static final String BASE_URL = Net.BASE_URL;

    public Retrofit retrofit;
    SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();

    /**
     * 网络请求接口包装类，可细分多个Service
     **/
    public ApiService apiService;
    public static final long DEFAULT_TIMEOUT = 20;

    private String timestamp;
    private String sign;
    private OkHttpClient okHttpClient;

    /**
     * 在访问HttpMethods时创建延迟加载的单例
     **/
    private static class SingletonHolder {

        private static final Api INSTANCE = new Api();
    }

    // 获取单例
    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private Api() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // http请求Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        builder.sslSocketFactory(createSSLSocketFactory());
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        // 网络请求的缓存机制，无网络也能显示数据
        File cacheFile = new File(BasicApplication.getAppContext().getCacheDir(), HTTP_CACHE_DIR);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        builder.addNetworkInterceptor(new HttpCacheInterceptor());

        // 保持请求是同一个cookie
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        // 其他设置
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)// 错误重连
                .addInterceptor(headerInterceptor)// 请求头
//                .addInterceptor(addQueryParameterInterceptor)// 公共参数
                .cache(cache);// 缓存目录
//                .cookieJar(new JavaNetCookieJar(cookieManager));
        //保持cookie
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BasicApplication.getAppContext()));
        okHttpClient = builder.cookieJar(cookieJar).build();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)// okhttp的实现
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// RxJava支持
                .addConverterFactory(GsonConverterFactory.create(gson))// Gson支持
                .baseUrl(BASE_URL)// 请求地址
                .build();

        apiService = retrofit.create(ApiService.class);// 可使用ServiceFactory细分接口服务
    }

    // 添加请求头
    Interceptor headerInterceptor = chain -> {
        synchronized (this) {
            Context mContext = BasicApplication.getAppContext();
            String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
            //String tokenType = (String) SharedPreferencesUtil.get(mContext, SharedPreferencesUtil.TOKEN_TYPE, "");

            //   parameters.clear();

            // String userId = (String) SharedPreferencesUtil.get(mContext, SharedPreferencesUtil.USER_ID, "");
            //String userType = (String) SharedPreferencesUtil.get(mContext, SharedPreferencesUtil.LOGIN_USER_TYPE, "");

            Request originalRequest = chain.request();
            Map<String, String> parameters = new HashMap<String, String>();
            String stringA = "";

          /*  if (requestBody instanceof FormBody) {
                FormBody formBody = (FormBody) requestBody;
                for (int i = 0; i < formBody.size(); i++) {
                    String value = URLDecoder.decode(formBody.encodedValue(i), "utf-8");//解码URL
                    parameters.put(formBody.encodedName(i), value);
                    LogUtil.e(new Gson().toJson(parameters));
                }
            }*/
            String method = originalRequest.method();
            if (method.equals("POST") || method.equals("PUT")) {
                RequestBody requestBody = originalRequest.body();
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                Charset charset = Charset.forName("UTF-8");
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(charset);
                }
                String paramsStr = buffer.readString(charset);

                Map<String, String> mapObj = JSONObject.parseObject(paramsStr, Map.class);
                for (String key : mapObj.keySet()) {
                    //map.keySet()返回的是所有key的值
                    parameters.put(key, mapObj.get(key));

                }
            } else if (method.equals("GET")) {

                Set<String> stringSet = originalRequest.url().queryParameterNames();
                Iterator<String> it = stringSet.iterator();
                while (it.hasNext()) {
                    String str = it.next();
                    List<String> value = originalRequest.url().queryParameterValues(str);
                    parameters.put(str, value.get(0));
                }
            }
            //Map<String,Object> map = new HashMap();

            // Tools.MapToAsciiString();

            //  String parameterKey = Constants.parameterEncryptKey;
            long timestamp = new Date().getTime() / 1000;
            String uuid = EncryptedSignatureUtil.getUUID();
            String nonce = MD5Util.md5(timestamp + uuid);
            String mStringSignTemp = timestamp + token + nonce;

            String curVer = (String) SharedPreferencesUtils.getData("version", "");

            if (parameters.size() > 0) {

                //stringA = EncryptedSignatureUtil.createSign(parameters);
                sign = EncryptedSignatureUtil.createSign(parameters, mStringSignTemp);
            }
            Log.d("参数>>>>>>>>>.", stringA + "url" + originalRequest.url() + "timestamp>>>>>>>>>>>>>>>>>>>>>>>>." + timestamp + "originalRequestUrl" + originalRequest.url() + "mStringSignTemp>>" + mStringSignTemp + "sign>>" + sign);
            Request request = originalRequest.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", token)
                    //.addHeader("sign", sign)
                    .addHeader("timestamp", timestamp + "")
                    .addHeader("nonce", nonce)
                    .addHeader("curVer", curVer)
                    .addHeader("os", "android")
                    .build();

            return chain.proceed(request);
        }
    };

    class HttpCacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.isNetConnected(BasicApplication.getAppContext())) {
                request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            int code = response.code();
            Log.d(TAG, "response.code=" + response.code());
            if (code == Constants.UNAUTHORIZED_401) {     //根据和服务端的约定判断token过期
                // BaseActivity.mTopActivity.onReLogin("");
           /*     Log.d(TAG, "自动刷新Token,然后重新请求数据");
                //同步请求方式，获取最新的Token
                String newToken = getNewToken();
                if(TextUtils.isEmpty(newToken)){

                    return null;
                } else {

                    //使用新的Token，创建新的请求
                    SharedPreferencesUtil.put(BasicApplication.getAppContext(), SharedPreferencesUtil.TOKEN, newToken);
                    Request newRequest = chain.request()
                            .newBuilder()
                            .header("Content-Type", "application/json")
                            .header("Authorization", "Bearer " + newToken)
                            .build();
                    //重新请求
                    return chain.proceed(newRequest);
                }*/

            }
            if (NetWorkUtil.isNetConnected(BasicApplication.getAppContext())) {
                // 有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                // int maxAge = 0;
                return response.newBuilder()
                        .header("Cache-Control", cacheControl)//"public, max-age="+maxAge
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28;// 2419200
                return response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    private String getNewToken() throws IOException {
        //     通过获取token的接口，同步请求接口
        OkHttpClient httpClient = new OkHttpClient();
        Context mContext = BasicApplication.getAppContext();
        String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
        String timeline = String.valueOf(System.currentTimeMillis() / 1000);
        FormBody formBody = new FormBody
                .Builder()
                .build();

        Request request1 = new Request.Builder().url(BASE_URL + "/api/upToken").post(formBody).addHeader("Authorization", "Bearer " + token).build();
        Call call = httpClient.newCall(request1);
        Response response = call.execute();
        Gson gson = new Gson();
        String json = response.body().string();
           /* AllTokenBean allTokenBean = gson.fromJson(json, AllTokenBean.class);
            String access_token = allTokenBean.getData().getAccess_token();
            String token_type = allTokenBean.getData().getToken_type();*/
        return "";
    }

    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    public class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

}
