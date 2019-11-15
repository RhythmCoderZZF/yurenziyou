package com.nbhysj.coupon.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.nbhysj.coupon.R;

/**
 * created by hysj on 2018/10/30.
 * description: 操作对话框
 */
public class CouponDescriptionDialog {
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
     * 标题
     */
    private TextView mTvTitle;

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
    private TextView mTvCouponIntro;

    /**
     * 上下文
     **/
    private ImageView imgLine;

    private ImageView mImgCouponDesCancel;

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

    /**
     * 构造器
     *
     * @param context
     */
    public CouponDescriptionDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    /**
     * 弹框创建
     *
     * @return
     */
    public CouponDescriptionDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_coupon_description, null);

        // 获取自定义Dialog布局中的控件
        layoutBg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        mImgCouponDesCancel = view.findViewById(R.id.img_coupon_des_cancel);
        mTvCouponIntro = view.findViewById(R.id.tv_coupon_intro);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        layoutBg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.75), LayoutParams.WRAP_CONTENT));

        mImgCouponDesCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        layoutBg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });
        return this;
    }

    /**
     * 设置内容
     */
    public CouponDescriptionDialog setContent(String content) {

        mTvCouponIntro.setText(content);

        return this;
    }

    /**
     * 设置标题
     */
    public CouponDescriptionDialog setTitle(String title) {

        if (title.equals("")) {
            mTvTitle.setVisibility(View.GONE);
        } else {
            mTvTitle.setVisibility(View.VISIBLE);
            mTvTitle.setText(title);
        }
        return this;
    }

    /**
     * 设置布局
     *
     * @param view
     * @return
     */
    public CouponDescriptionDialog setView(View view) {

        dialog.setContentView(view);

        return this;
    }

    /**
     * 设置是否可以点击消失
     *
     * @param cancel
     * @return
     */
    public CouponDescriptionDialog setCancelable(boolean cancel) {
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
    public CouponDescriptionDialog setNegativeButton(String text, int color,
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
                dialog.dismiss();
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
     * 弹框显示
     */
    public void show() {
        //setLayout();
        dialog.show();
    }
}
