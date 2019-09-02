package com.nbhysj.coupon.util;

import android.content.Context;
import android.util.DisplayMetrics;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther：hysj created at 2019/01/23
 * description：
 */

public class Tools {

    //dip 转 px
    public static int dip2px(Context context, double d) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (d * scale + 0.5f);
    }

    //px 转 dip
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int px = (int) (dpValue * (metrics.densityDpi / 160f));
        return px;
    }

    public static boolean isContainNumber(String nameOrIdCard) {

        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(nameOrIdCard);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 身份证隐藏最后四位
     *
     * @param idCardNumber
     * @return
     */
    public static String getIDCard(String idCardNumber) {
        String idCardStr = idCardNumber.substring(idCardNumber.length() - 4, idCardNumber.length());
        for (int i = 0; i < idCardStr.length(); i++) {
            idCardStr = idCardStr.replace(String.valueOf(idCardStr.charAt(i)), "*");
        }

        String idCardNumberResult = idCardNumber.substring(0, idCardNumber.length() - 4) + idCardStr;

        return idCardNumberResult;
    }

    /**
     * 手机号隐藏中间四位
     *
     * @param phoneNumber
     * @return
     */
    public static String getPhone(String phoneNumber) {

        String phone = phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");

        return phone;
    }


    /**
     * 获取格式化的保留一位数的数
     */
    public static String getFormatDecimalPoint(double dataValue) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");//构造方法的字符格式这里如果小数不足2位,会以0补足. .00
        return decimalFormat.format(dataValue);
    }

    /**
     * 获取格式化的保留两位数的数
     */
    public static String getTwoDecimalPoint(double dataValue) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足. .00
        return decimalFormat.format(dataValue);
    }
}
