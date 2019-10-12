package com.nbhysj.coupon.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
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

    public static long getSoundDuring(String mUri)
    {
        MediaPlayer mediaPlayer = new MediaPlayer();
        long duration = 0;
        try {
            mediaPlayer.setDataSource(mUri);
            mediaPlayer.prepare();
            int mediaPlayerDuration = mediaPlayer.getDuration() ;
            Double durationTime = new Double(mediaPlayerDuration);
            double timeDouble = durationTime / 1000;
            duration = Math.round(timeDouble);
            /*if (0 != duration) {
                //更新 seekbar 长度
                int s = duration / 1000;
                //设置文件时长，单位 "分:秒" 格式
                String total = s / 60 + ":" + s % 60;
                //记得释放资源
                mediaPlayer.release();
            }*/
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return duration;
    }


    /*
     * 是否为浮点数？double或float类型。
     * @param str 传入的字符串。
     * @return 是浮点数返回true,否则返回false。
     */
    public static boolean isDoubleOrFloat(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }
}
