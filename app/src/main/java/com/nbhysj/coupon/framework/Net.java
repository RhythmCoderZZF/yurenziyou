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

    //下载引导页
    String H5_YURENZIYOU_DOWNLOAD_GUIDE_PAGE_URL = "http://h5.yurenziyou.com/guidance";


    /***********  小程序分享(帖子和商户)  ***********/
    //帖子 游记
    String POST_TRAVEL_MINIPTOGRAM_URL = "pages/travel/travel?id=";

    //景点
    String MCH_SCENIC_MINIPTOGRAM_URL = "pages/scenic/scenic?id=";

    //名宿
    String MCH_HOMESTAY_MINIPTOGRAM_URL = "pages/bigname/bigname?id=";

    //酒店
    String MCH_HOTEL_MINIPTOGRAM_URL = "pages/hotel/hotel?id=";

    //美食
    String MCH_FOODS_MINIPTOGRAM_URL = "pages/foods/foods?id=";

    //组合详情
    String COMBINATION_MINIPTOGRAM_URL = "pages/combination/combination?id=";
}
