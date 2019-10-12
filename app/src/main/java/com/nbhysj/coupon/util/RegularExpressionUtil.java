package com.nbhysj.coupon.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther：hysj created at 2019/01/23
 * description：正则工具类
 */

public class RegularExpressionUtil {
    private static final Pattern PATTERN = java.util.regex.Pattern.compile("^[0-9]{1,2}+(\\\\.[0-9]{2})?$");

    public static String getMatcherNumber(String msg) {

        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(msg);
        String diagit = m.replaceAll("").trim();
        return diagit;
    }

    /**
     * 判断是否保留了两位小数
     *
     * @param str
     * @return
     */
    public static boolean isTwoDecimalRemain(String str) {
        boolean isValid = false;
        String limitEx = "^[0-9]{1,2}+(\\.[0-9]{2})?$";

        Pattern pattern = Pattern.compile(limitEx);
        Matcher m = pattern.matcher(str);
        if (m.matches()) {

            isValid = true;
        }

        if (!str.contains(".")) {
            isValid = false;
        }
        return isValid;
    }
}
