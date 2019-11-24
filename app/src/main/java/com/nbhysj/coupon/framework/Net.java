package com.nbhysj.coupon.framework;

/**
 * @auther：hysj created at 2019/09/23
 * description：请求路径
 */
public interface Net {
    //String BASE_URL = "http://192.168.1.223:8085/";   //测试
   //String BASE_URL = "http://192.168.1.189:8085/";   //测试
    //String BASE_URL = "http://fx.nbhysj.com/";   //测试
    String BASE_URL = "http://api.nbhysj.com/";   //生产环境

    //版本更新
    String APP_UPDATE_URL = BASE_URL+ "api/postsVersion/find";

    //用户协议
    String USER_AGREEMENT_URL = "http://h5.yurenziyou.com/serve";

    //隐私政策
    String PRAVACY_POLICY_URL = "http://h5.yurenziyou.com/policy";
}
