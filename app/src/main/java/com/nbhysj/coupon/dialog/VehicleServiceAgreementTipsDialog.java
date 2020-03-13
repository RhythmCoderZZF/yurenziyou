package com.nbhysj.coupon.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.view.JudgeNestedScrollView;
import com.nbhysj.coupon.view.ReadScrollView;

/**
 * created by hysj on 2018/10/30.
 * description: 操作对话框
 */
public class VehicleServiceAgreementTipsDialog {
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
    private TextView mTvVihicleIntro;

    /**
     * 上下文
     **/
    private ImageView imgLine;

    private ImageView mImgCouponDesCancel;

    private TextView mTvAlreadyRead;

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

    private VehicleServiceAgreementListener vehicleServiceAgreementListener;

    private JudgeNestedScrollView mScrollViewVehicleServiceAgreement;
    /**
     * 构造器
     *
     * @param context
     */
    public VehicleServiceAgreementTipsDialog(Context context,VehicleServiceAgreementListener vehicleServiceAgreementListener) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        this.vehicleServiceAgreementListener = vehicleServiceAgreementListener;
    }

    /**
     * 弹框创建
     *
     * @return
     */
    public VehicleServiceAgreementTipsDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_vehicle_service_agreement, null);

        // 获取自定义Dialog布局中的控件
        layoutBg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        mScrollViewVehicleServiceAgreement = view.findViewById(R.id.scroll_view_vehicle_service_agreement);
        mImgCouponDesCancel = view.findViewById(R.id.img_coupon_des_cancel);
        mTvVihicleIntro = view.findViewById(R.id.tv_coupon_intro);
        mTvAlreadyRead = view.findViewById(R.id.tv_already_read);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        layoutBg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LayoutParams.WRAP_CONTENT));

        mImgCouponDesCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                vehicleServiceAgreementListener.setDialogDismissCallback();
                dialogDismiss();
            }
        });

        layoutBg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                vehicleServiceAgreementListener.setDialogDismissCallback();
                dialogDismiss();
            }
        });
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
                    mTvAlreadyRead.setBackgroundResource(R.drawable.bg_blue_green_gradient_radius_twenty);
                }
            }

        });

        mTvAlreadyRead.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isCanOnclickAlreadyRead){

                vehicleServiceAgreementListener.setVehicleServiceAgreementCallback();
                }else {

                    vehicleServiceAgreementListener.setVehicleAgreementUnreadCompleteCallback();
                }

            }
        });
        return this;
    }

    /**
     * 设置内容
     */
    public VehicleServiceAgreementTipsDialog setContent(String content) {
        mTvVihicleIntro.setText(Html.fromHtml(content.replace("</br>","<br>")));

        return this;
    }

    /**
     * 设置标题
     */
    public VehicleServiceAgreementTipsDialog setTitle(String title) {

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
    public VehicleServiceAgreementTipsDialog setView(View view) {

        dialog.setContentView(view);

        return this;
    }

    /**
     * 设置是否可以点击消失
     *
     * @param cancel
     * @return
     */
    public VehicleServiceAgreementTipsDialog setCancelable(boolean cancel) {
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
    public VehicleServiceAgreementTipsDialog setNegativeButton(String text, int color,
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
        isCanOnclickAlreadyRead = false;
    }


    /**
     */
    public void setVehicleAgreementAlreadyRead() {
        //setLayout();
        //  mTvAlreadyRead.setBackgroundResource(R.drawable.bg_rect_gray_shape);
        isCanOnclickAlreadyRead = true;
        dialog.dismiss();
    }


    /**
     * 弹框显示
     */
    public void show() {
        //setLayout();
        mTvAlreadyRead.setBackgroundResource(R.drawable.bg_rect_gray_shape);
        mScrollViewVehicleServiceAgreement.scrollTo(0,0);
        dialog.show();
    }

    public interface VehicleServiceAgreementListener{

        void setVehicleServiceAgreementCallback();

        void setVehicleAgreementUnreadCompleteCallback();

        void setDialogDismissCallback();
    }
}
