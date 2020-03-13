package com.nbhysj.coupon.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.framework.Net;
import com.nbhysj.coupon.ui.PhoneQuickLoginActivity;
import com.nbhysj.coupon.ui.UserRegistrationActivity;
import com.nbhysj.coupon.ui.WebActivity;
import com.nbhysj.coupon.view.JudgeNestedScrollView;

/**
 * created by hysj on 2019/10/30.
 * description: 用户协议和隐私政策操作对话框
 */
public class UserPrivacyAgreementDialog {
    /**
     * 上下文
     **/
    private Context context;

    /**
     * 提示提交弹框
     **/
    private Dialog dialog;

    /**
     * 内容
     */
    private TextView mTvContent;

    /**
     * 不同意用户协议
     */
    private TextView mTvDisagreeUserPrivacy;

    /**
     * 同意用户协议
     */
    private TextView mTvAgreementAndContinue;


    /**
     * 控件布局
     **/
    private LinearLayout layoutBg;

    /**
     * 取消
     **/
    private TextView btnNeg;

    /**
     * 确定
     **/
    private TextView mTvVihicleIntro;

    /**
     * 弹框占屏幕大小尺寸
     **/
    private Display display;

    /**
     * 确定按钮
     **/
    private boolean showPosBtn = false;

    /**
     * 取消按钮
     **/
    private boolean showNegBtn = false;

    private boolean isCanOnclickAlreadyRead = false;

    private UserPrivacyAgreementListener userPrivacyAgreementListener;

    private JudgeNestedScrollView mScrollViewVehicleServiceAgreement;

    /**
     * 构造器
     *
     * @param context
     */
    public UserPrivacyAgreementDialog(Context context, UserPrivacyAgreementListener userPrivacyAgreementListener) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        this.userPrivacyAgreementListener = userPrivacyAgreementListener;
    }

    /**
     * 弹框创建
     *
     * @return
     */
    public UserPrivacyAgreementDialog builder() {

        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_user_privacy_agreement, null);

        // 获取自定义Dialog布局中的控件
        layoutBg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        mScrollViewVehicleServiceAgreement = view.findViewById(R.id.scroll_view_user_privacy_agreement);
        mTvVihicleIntro = view.findViewById(R.id.tv_user_privacy_intro);
        mTvDisagreeUserPrivacy = view.findViewById(R.id.tv_disagree_user_privacy);
        mTvAgreementAndContinue = view.findViewById(R.id.tv_agreement_and_continue);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        layoutBg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LayoutParams.WRAP_CONTENT));
        mTvAgreementAndContinue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                userPrivacyAgreementListener.setUserPrivacyAgreementCallback();
            }
        });

        mTvDisagreeUserPrivacy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                userPrivacyAgreementListener.setUserPrivacyNoAgreementCallback();
            }
        });
     /*   layoutBg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                userPrivacyAgreementListener.setDialogDismissCallback();
                dialogDismiss();
            }
        });*/
      /*  mScrollViewVehicleServiceAgreement.setScrollChangedListener(new ReadScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChangedTop() {

            }

            @Override
            public void onScrollChangedBottom() {
                mTvAlreadyRead.setBackgroundResource(R.drawable.bg_blue_green_gradient_radius_twenty);

            }
        });*/

        mScrollViewVehicleServiceAgreement.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
             /*   if (scrollY > oldScrollY) {
                    Log.i("TAG", "Scroll DOWN");
                }
                if (scrollY < oldScrollY) {
                    Log.i("TAG", "Scroll UP");
                }

                if (scrollY == 0) {
                    Log.i("TAG", "TOP SCROLL");
                }*/

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    Log.i("TAG", "BOTTOM SCROLL");
                    isCanOnclickAlreadyRead = true;
                }
            }

        });

    /*    mTvAlreadyRead.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isCanOnclickAlreadyRead){

                vehicleServiceAgreementListener.setVehicleServiceAgreementCallback();
                }else {

                    vehicleServiceAgreementListener.setVehicleAgreementUnreadCompleteCallback();
                }

            }
        });*/
        return this;
    }

    /**
     * 设置内容
     */
    public UserPrivacyAgreementDialog setContent(String content) {
    /*    String originText = "#重磅消息#近日谷歌放出Android N的第二个开发者预览版(Developer Preview)";
        SpannableStringBuilder spannable = new SpannableStringBuilder(originText);
        //设置文字的前景色
      //  spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.color_blu4)),14,20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //这个一定要记得设置，不然点击不生效
        mTvVihicleIntro.setMovementMethod(LinkMovementMethod.getInstance());
        SpannableString clickSpan = new SpannableString(content);
        spannable.append(clickSpan);
        clickSpan.setSpan(new PrivacyPolicyClick(),14,20 , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的前景色
      //  spannable.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.color_blu4)),21,27,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //这个一定要记得设置，不然点击不生效
       // mTvVihicleIntro.setMovementMethod(LinkMovementMethod.getInstance());
        //spannable.setSpan(new AgreementClick(),23,26 , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvVihicleIntro.setText(Html.fromHtml(clickSpan.toString()));
*/

        mTvVihicleIntro.setText(Html.fromHtml(content));
        mTvVihicleIntro.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence str = mTvVihicleIntro.getText();
        if (str instanceof Spannable) {
            int end = str.length();
            Spannable sp = (Spannable) mTvVihicleIntro.getText();  //构建Spannable对象、继承Spanned、Spanned对象继承CharSequener
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);  //找出text中的a标签
            //SpannableStringBuilder、SpannableString对象跟String对象差不多、只是比String对象多setSpan，
            //可以给字符串设置样式、大小、背景色...而 SpannableStringBuilder跟SpannableString的关系就跟String跟StringBuffer关系一样
            SpannableStringBuilder style = new SpannableStringBuilder(str);
            style.clearSpans();//should clear old spans
            PrivacyPolicyClick privacyPolicy = new PrivacyPolicyClick();
            AgreementClick agreementClick =  new AgreementClick();
            //设置样式其中参数what是具体样式的实现对象，start则是该样式开始的位置，end对应的是样式结束的位置，
            // 参数 flags，定义在Spannable中的常量
            style.setSpan(agreementClick, 57, 71, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            style.setSpan(privacyPolicy, 72, 86, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            style.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.color_text_blue)), 57, 71, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            style.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.color_text_blue)), 72, 86, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


            UnderlineSpan underlineSpan = new UnderlineSpan();
            style.setSpan(underlineSpan, 57, 71, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            style.setSpan(underlineSpan, 72, 86, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

            mTvVihicleIntro.setText(style);
        }
        return this;

    }

    public class PrivacyPolicyClick extends ClickableSpan {
        @Override
        public void onClick(View widget) {
            //            跳转隐私政策网址
            Intent intent = new Intent();
            intent.putExtra("url", Net.PRAVACY_POLICY_URL);
            intent.putExtra("title", Constants.PRAVACY_POLICY_H5_TITEL);
            intent.setClass(context, WebActivity.class);
            context.startActivity(intent);

        }

        @Override
        public void updateDrawState(TextPaint ds) {
//            ds.setColor(ds.linkColor);
//            ds.setUnderlineText(true);
        }
    }

    public class AgreementClick extends ClickableSpan {
        @Override
        public void onClick(View widget) {
            // 跳转用户协议网址
            Intent intent = new Intent();
            intent.putExtra("url", Net.USER_AGREEMENT_URL);
            intent.putExtra("title", Constants.USER_AGREEMENT_H5_TITEL);
            intent.setClass(context, WebActivity.class);
            context.startActivity(intent);

        }
    }

    /**
     * 设置标题
     */
    public UserPrivacyAgreementDialog setTitle(String title) {

       /* if (title.equals("")) {
            mTvTitle.setVisibility(View.GONE);
        } else {
            mTvTitle.setVisibility(View.VISIBLE);
            mTvTitle.setText(title);
        }*/
        return this;
    }

    /**
     * 设置布局
     *
     * @param view
     * @return
     */
    public UserPrivacyAgreementDialog setView(View view) {

        dialog.setContentView(view);

        return this;
    }

    /**
     * 设置是否可以点击消失
     *
     * @param cancel
     * @return
     */
    public UserPrivacyAgreementDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 监听确定按钮
     *
     * @param text
     * @param listener
     * @return
     */
  /*  public CouponDescriptionDialog setPositiveButton(String text, int color,
                                                     final OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btnPos.setText("确定");
        } else {

            btnPos.setTextColor(color);
            btnPos.setText(text);
        }
        btnPos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }*/

    /**
     * 监听取消按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public UserPrivacyAgreementDialog setNegativeButton(String text, int color,
                                                        final OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btnNeg.setText("取消");
        } else {
            btnNeg.setTextColor(color);
            btnNeg.setText(text);
        }
        btnNeg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialogDismiss();
            }
        });
        return this;
    }

    /**
     * 设置默认布局
     */
   /* private void setLayout() {

        if (!showPosBtn && !showNegBtn) {
            btnPos.setText("确定");
            btnPos.setVisibility(View.VISIBLE);
            btnPos.setBackgroundResource(R.drawable.alertdialog_single_selector);
            btnPos.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btnPos.setVisibility(View.VISIBLE);
            btnPos.setBackgroundResource(R.drawable.alertdialog_right_selector);
            btnNeg.setVisibility(View.VISIBLE);
            btnNeg.setBackgroundResource(R.drawable.alertdialog_left_selector);
            imgLine.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btnPos.setVisibility(View.VISIBLE);
            btnPos.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }

        if (!showPosBtn && showNegBtn) {
            btnNeg.setVisibility(View.VISIBLE);
            btnNeg.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }
    }*/

    /**
     * 弹框隐藏
     */
    public void dialogDismiss() {
        //setLayout();
        //  mTvAlreadyRead.setBackgroundResource(R.drawable.bg_rect_gray_shape);
        dialog.dismiss();
    }


    /**
     */
    public void setVehicleAgreementAlreadyRead() {
        //setLayout();
        //  mTvAlreadyRead.setBackgroundResource(R.drawable.bg_rect_gray_shape);
        dialog.dismiss();
    }


    /**
     * 弹框显示
     */
    public void show() {
        //setLayout();
        //  mTvAlreadyRead.setBackgroundResource(R.drawable.bg_rect_gray_shape);
        dialog.show();
    }

    public interface UserPrivacyAgreementListener {

        void setUserPrivacyAgreementCallback();

        void setUserPrivacyNoAgreementCallback();

    }
}
