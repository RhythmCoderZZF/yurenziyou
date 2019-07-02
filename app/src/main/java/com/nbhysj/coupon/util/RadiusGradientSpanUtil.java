package com.nbhysj.coupon.util;

import android.text.SpannableStringBuilder;
import android.text.Spanned;

import com.nbhysj.coupon.widget.LinearGradientFontSpan;

/**
 * @auther：hysj created on 2019/3/18
 * description：渐变字工具类
 */
public class RadiusGradientSpanUtil {

    public static SpannableStringBuilder getRadiusGradientSpan(String string, int startColor, int endColor) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        LinearGradientFontSpan span = new LinearGradientFontSpan(startColor, endColor);
        spannableStringBuilder.setSpan(span, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableStringBuilder;

    }
}
