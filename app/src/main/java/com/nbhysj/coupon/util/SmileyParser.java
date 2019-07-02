package com.nbhysj.coupon.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.TypedValue;

import com.nbhysj.coupon.R;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式转换条目中图文混排的工具类
 */

public class SmileyParser {
    /*
     * 单例模式 1文字资源，图片资源 2.使用正则表达式进行匹配文字 3.把edittext当中整体的内容匹配正则表达式一次
     * 4.SpannableStringBuilder 进行替换
     */
    private static SmileyParser sInstance;

    public static SmileyParser getInstance() {
        return sInstance;
    }

    public static void init(Context context) {
        sInstance = new SmileyParser(context);
    }

    private final Context mContext;
    public String[] arrTextEMoji;
    public String[] arrTextCaiCai;

    // 正则表达式
    private final Pattern mPattern;

    // String 图片字符串 Integer表情
    private final HashMap<String, Integer> mSmileyToRes;

    // arrays里面的表情内容
    public static final int EMOJI_SMILEY_TEXTS = R.array.custom_smiley_texts;
    public static final int CAICAI_SMILEY_TEXTS = R.array.caicai_smiley_texts;

    private SmileyParser(Context context) {
        mContext = context;
        // 获取表情文字资源
        arrTextEMoji = mContext.getResources().getStringArray(
                EMOJI_SMILEY_TEXTS);
        arrTextCaiCai = mContext.getResources().getStringArray(
                CAICAI_SMILEY_TEXTS);
        // 获取表情ID与表情图标的Map
        mSmileyToRes = buildSmileyToRes();
        // 获取构建的正则表达式
        mPattern = buildPattern();
    }

    /**
     * 第2类所有表情的资源
     */
    public static final int[] CAICAI_SMILEY_RES_IDS = {R.mipmap.am,
            R.mipmap.aw, R.mipmap.bhb, R.mipmap.bs, R.mipmap.bz,
            R.mipmap.ch, R.mipmap.dbq, R.mipmap.dk, R.mipmap.dt,
            R.mipmap.fd, R.mipmap.gg, R.mipmap.gl, R.mipmap.gxfc,
            R.mipmap.gz, R.mipmap.hc, R.mipmap.hj, R.mipmap.hl,
            R.mipmap.hx, R.mipmap.jiayou, R.mipmap.jinya, R.mipmap.kl,
            R.mipmap.lcx, R.mipmap.mg, R.mipmap.ok, R.mipmap.q,
            R.mipmap.qb, R.mipmap.qq, R.mipmap.se, R.mipmap.sj,
            R.mipmap.sk, R.mipmap.sq, R.mipmap.sx, R.mipmap.tst,
            R.mipmap.tx, R.mipmap.wl, R.mipmap.wq, R.mipmap.wx,
            R.mipmap.xe, R.mipmap.xv, R.mipmap.yun, R.mipmap.yw,
            R.mipmap.zj

    };

    /**
     * 使用HashMap的key-value的形式来影射表情的ID和图片资源
     *
     * @return
     */
    private HashMap<String, Integer> buildSmileyToRes() {

        if (CAICAI_SMILEY_RES_IDS.length != arrTextCaiCai.length) {
            throw new IllegalStateException("ID和图片不匹配");
        }

        HashMap<String, Integer> smileyToRes = new HashMap<String, Integer>();

        for (int i = 0; i < arrTextCaiCai.length; i++) {
            // 图片名称作为key值，图片资源ID作为value值
            smileyToRes.put(arrTextCaiCai[i], CAICAI_SMILEY_RES_IDS[i]);
        }

        return smileyToRes;
    }

    /**
     * 构建正则表达式,用来找到我们所要使用的图片
     *
     * @return
     */
    private Pattern buildPattern() {
        StringBuilder patternString = new StringBuilder(
                (arrTextEMoji.length + arrTextCaiCai.length) * 3);
        patternString.append('(');
        for (String s : arrTextEMoji) {
            patternString.append(Pattern.quote(s));
            patternString.append('|');
        }

        for (String s : arrTextCaiCai) {
            patternString.append(Pattern.quote(s));
            patternString.append('|');
        }
        patternString.replace(patternString.length() - 1,
                patternString.length(), ")");
        // 把String字符串编译成正则表达式()
        // ([调皮]|[调皮]|[调皮])
        return Pattern.compile(patternString.toString());
    }

    /**
     * 根据文本替换成图片
     *
     * @param text 对应表情
     * @return 一个表示图片的序列
     */
    public CharSequence addSmileySpans(CharSequence text) {
        // 把文字替换为对应图片
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        // 判断提取工具类（按照正则表达式）
        Matcher matcher = mPattern.matcher(text);
        while (matcher.find()) {
            // 获取对应表情的图片id
            int resId = mSmileyToRes.get(matcher.group());
            // 替换制定字符
            builder.setSpan(new ImageSpan(mContext, resId), matcher.start(),
                    matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }

    /**
     * 根据文本替换成图片，int width, int height 可控制表情大小
     *
     * @param text 对应表情
     * @return 一个表示图片的序列
     */

    public CharSequence addSmileySpansReSize(CharSequence text, int width,
                                             int height) {
        // 把文字替换为对应图片
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        // 判断提取工具类（按照正则表达式）
        Matcher matcher = mPattern.matcher(text);
        while (matcher.find()) {
            // 获取对应表情的图片id
            int resId = mSmileyToRes.get(matcher.group());
            // 替换制定字符
            builder.setSpan(
                    new ImageSpan(mContext, decodeSampledBitmapFromResource(
                            mContext.getResources(), resId,
                            SmileyParser.dp2px(mContext, width),
                            SmileyParser.dp2px(mContext, height))), matcher
                            .start(), matcher.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        return builder;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res,
                                                         int resId, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inDensity = res.getDisplayMetrics().densityDpi;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
}

