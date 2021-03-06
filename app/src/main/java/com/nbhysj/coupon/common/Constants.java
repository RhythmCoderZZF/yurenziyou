package com.nbhysj.coupon.common;


import com.nbhysj.coupon.BasicApplication;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.RegularExpressionUtil;

/**
 * @auther：hysj created at 2019/01/23
 * description：常量类
 */
public class Constants {
    public static final String APP_ID = "wx5359dc62a45ef0b3";
    //请求pwd加盐
    public static final String saltEncryptKey = "nbhysj_";

    public static final int CITY_CODE = 330200;  //城市Id

    public static final int UNAUTHORIZED_401 = 401;  //token过期

    public static final int SUCCESS_CODE = 10000;      // http访问成功

    public static final int THIRD_PARTY_LOGIN_FOR_UNBOUND_CODE = 11001; //第三方未绑定

    public static final int USER_NOT_LOGIN_CODE = 10100; //未登录

    public static final int NO_MORE_LOAD_CODE = -1;      //暂无数据

    /************** h5标题 ************************/
    //用车
    public static final String VEHICLE_USE_H5_TITLE = "用车";
    //房源详情
    public static final String HOUSE_INFO_H5_TITEL = "房源详情";
    //更多设备
    public static final String All_FACILITY_H5_TITEL = "更多设备";
    //攻略
    public static final String STRATEGY_H5_TITEL = "攻略";
    //购买须知
    public static final String ORDER_PURCHASE_INSTRUCTIONS_H5_TITEL = "购买须知";
    //订房必读
    public static final String RESERVATION_MUST_BE_READ_H5_TITEL = "订房必读";
    //设施详情
    public static final String All_FACILITY_DETAIL_H5_TITEL = "设施详情";

    //用户协议
    public static final String USER_AGREEMENT_H5_TITEL = "用户协议";
    //隐私政策
    public static final String PRAVACY_POLICY_H5_TITEL = "隐私政策";

    /**********************************************/

    /********************    支付code    ********************/
    public static final String PAYMENT_SUCCESS_CODE_ALIPAY = "9000";

    public static final String PAYMENT_CANCEL_CODE_ALIPAY = "6001";

    public static final String CONNECT_TIMED_OUT = "timeout";

    public static final String HTTP_UNAUTHORIZED_401 = "HTTP 401 Unauthorized";

    public static final String BROCAST_ACTION_TRIP_ASSISANT = "android.intent.action.trip.assisant";

    public static final String BROADCAST_ACTION_MAIN_BACK = "android.intent.action.main.back";

    public static final String BROADCAST_ACTION_ARG_OPRATE = "android.intent.action.oprate";

    public static final String BROADCAST_ACTION_BACK_MY_COLLECTION = "android.intent.action.my.collection";

    public static final String BROADCAST_ACTION_BACK_SHOPPING_MALL = "android.intent.action.back.shopping.mall";

    public static final String BROADCAST_ACTION_BACK_HOME_PAGE = "android.intent.action.back.homepage";

    public static final String BROADCAST_ACTION_BACK_MINE = "android.intent.action.back.mine";

    //高德POI搜索
    public static final String EXTRA_TIP = "extraTip";
    public static final String KEY_WORDS_NAME = "KeyWord";

    public static String DEFAULT_CITY = "宁波";

    public static String getResultMsg(String msg) {

        if (msg != null) {

            if (msg.contains(CONNECT_TIMED_OUT) || msg.toLowerCase().contains("failed to connect")) {
                return "服务器连接超时";

            } else if (msg.contains("HTTP") && !msg.equals("HTTP 401 Unauthorized")) {   //404 502 500
                String numberStr = RegularExpressionUtil.getMatcherNumber(msg);
                return "服务器连接错误(" + numberStr + ")";

            } else if (msg.equals("HTTP 401 Unauthorized")) {
                return BasicApplication.getAppResources().getString(R.string.str_token_invalid);
            } else {
                return msg;
            }
        }
        return msg;
    }

}
