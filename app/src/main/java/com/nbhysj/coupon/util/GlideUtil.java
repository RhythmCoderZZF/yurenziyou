package com.nbhysj.coupon.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.blurbehind.BitmapBlurTransformation;
import com.nbhysj.coupon.widget.glide.CornersTransform;
import com.nbhysj.coupon.widget.glide.GlideApp;
import com.nbhysj.coupon.widget.glide.GlideRoundCornersTransUtils;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.nbhysj.coupon.widget.glide.GlideRoundedCornersTransform;
import com.nbhysj.coupon.widget.glide.RoundedCornersTransform;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * @auther：hysj created on 2019/3/18
 * description：glide 图片加载工具类
 */
public class GlideUtil {

    /**
     * 圆角图片处理
     *
     * @param imageUrl
     */
    public static void loadCornersTransformImage(Context mContext, String imageUrl, int radius, ImageView view) {
        RequestOptions myOptions = new RequestOptions()
                .transform(new GlideRoundTransform(mContext, radius));
        GlideApp.with(mContext)
                .load(imageUrl)
                .dontAnimate()
                .placeholder(mContext.getResources().getDrawable(R.mipmap.icon_placeholder_image))
                .apply(myOptions)
                .into(view);
    }

    public static void loadImage(Context mContext, String imageUrl, ImageView image) {
        GlideApp.with(mContext)
                .load(imageUrl)
                .placeholder(mContext.getResources().getDrawable(R.mipmap.icon_placeholder_image))
                .into(image);
    }

    public static void loadNativeImage(Context mContext, int imageFile, ImageView image) {
        GlideApp.with(mContext)
                .load(imageFile)
                .placeholder(mContext.getResources().getDrawable(R.mipmap.icon_placeholder_image))
                .into(image);
    }


    public static void loadCircleImage(Context context, String imageUrl, CircleImageView image) {
        Glide.with(context)
                .load(imageUrl)
                .into(image);
    }


    public static void loadImageWithProportion(Context context, String imageUrl, int photoWidth, int photoHeight, ImageView image) {
        GlideApp.with(context)
                .load(imageUrl)
                .override(photoWidth, photoHeight)
                .into(image);
    }


    private static void loadImage(String url, ImageView image, RequestOptions requestOptions) {
        Glide.with(image.getContext())
                .load(url)
                .apply(requestOptions)
                .into(image);
    }

    private static void loadImage(byte[] bytes, ImageView image, RequestOptions requestOptions) {
        Glide.with(image.getContext())
                .asBitmap()
                .load(bytes)
                .apply(requestOptions)
                .into(image);
    }

    public static void loadCircleImage(String url, ImageView image) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.circleCrop();
        loadImage(url, image, requestOptions);
    }

/*    public static void loadRoundedCornersImage(String url, ImageView image, float radius) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new RoundedCorners((int) (dp2px(radius) + 0.5f)));
        loadImage(url,image,requestOptions);
    }*/

    public static void loadBlurImage(Context mContext, String imageUrl, ImageView image) {

        Glide.with(mContext).load(imageUrl).
                apply(bitmapTransform(new BlurTransformation(0)))
                .into(image);
    }

    public static void loadBlurImageUrl(Context mContext, String imageUrl, ImageView image) {
        Glide.with(mContext)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(new BitmapBlurTransformation(25,8)))
                .into(image);
    }



    public static void loadRoundedCornersImage(String url, ImageView image, float radius,
                                               GlideRoundedCornersTransform.CornerType cornerType) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.optionalTransform(new GlideRoundedCornersTransform((int) (dp2px
                (radius) + 0.5f),
                cornerType));
        loadImage(url, image, requestOptions);
    }

    public static void loadRoundedCornersImage(Bitmap bm, ImageView image, float radius,
                                               GlideRoundedCornersTransform.CornerType cornerType) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.optionalTransform(new GlideRoundedCornersTransform((int) (dp2px
                (radius) + 0.5f),
                cornerType));
        loadImage(Bitmap2Bytes(bm), image, requestOptions);
    }

    /**
     * 把Bitmap转Byte
     */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static float dp2px(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getDisplayMetrics());
    }

    public static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }
}
