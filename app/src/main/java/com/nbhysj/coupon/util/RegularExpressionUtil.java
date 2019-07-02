package com.nbhysj.coupon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther：hysj created at 2019/01/23
 * description：正则工具类
 */

public class RegularExpressionUtil {

    public static String getMatcherNumber(String msg) {

        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(msg);
        String diagit = m.replaceAll("").trim();
        return diagit;
    }
}
