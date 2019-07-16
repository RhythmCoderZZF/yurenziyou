package com.nbhysj.coupon.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.BannerUrlBO;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.view.HotelDetailSupplementBannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther：hysj created on 2019/5/13
 * description：行程助手添加
 */
public class TravelAssistantAddDialog extends DialogFragment {
    private Context context;
    private View view;

    private TravelAssistantAddListener travelAssistantAddListener;

    public TravelAssistantAddDialog() {

    }

    public void setTravelAssistantAddListener(TravelAssistantAddListener travelAssistantAddListener) {

        this.travelAssistantAddListener = travelAssistantAddListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        context = getActivity();
        initView();
        Dialog dialog = new Dialog(context, R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        // 设置宽度为屏宽、靠近屏幕底部
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
      /*  window.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,

                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);*/
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    private void initView() {

        view = LayoutInflater.from(context).inflate(R.layout.layout_travel_assistant_add_dialog, null);
        RelativeLayout mRlytview = view.findViewById(R.id.rlyt_travel_assistant_add);
        LinearLayout mLlytScenicSpotAdd = view.findViewById(R.id.llyt_scenic_spot_add);    //添加景点
        LinearLayout mLlytOneDayAdd = view.findViewById(R.id.llyt_add_one_day);            //增加一天
        LinearLayout mLlytFineFoodAdd = view.findViewById(R.id.llyt_fine_food_add);        //添加美食
        LinearLayout mLlytHotelHomestayAdd = view.findViewById(R.id.llyt_hotel_homestay_add); //添加酒店民宿
        LinearLayout mLlytInteractionAdd = view.findViewById(R.id.llyt_add_interaction);   //添加互动
        LinearLayout mLlytTrafficAdd = view.findViewById(R.id.llyt_traffic_add);           //添加交通
        LinearLayout mLlytRemarks = view.findViewById(R.id.llyt_remarks_add);              //添加备注

        mRlytview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });

        mLlytScenicSpotAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                travelAssistantAddListener.travelAssistantAddListener(0);
                dismiss();
            }
        });

        mLlytFineFoodAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                travelAssistantAddListener.travelAssistantAddListener(1);
                dismiss();
            }
        });

        mLlytHotelHomestayAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                travelAssistantAddListener.travelAssistantAddListener(2);
                dismiss();
            }
        });

        mLlytInteractionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                travelAssistantAddListener.travelAssistantAddListener(3);
                dismiss();
            }
        });

        mLlytTrafficAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                travelAssistantAddListener.travelAssistantAddListener(4);
                dismiss();

            }
        });

        mLlytOneDayAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                travelAssistantAddListener.travelAssistantAddListener(5);
                dismiss();
            }
        });

        mLlytRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                travelAssistantAddListener.travelAssistantAddListener(6);
                dismiss();
            }
        });

    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //KeyBoardUtils.closeKeybord((Activity) context);
        super.show(manager, tag);
    }

    public interface TravelAssistantAddListener {

        void travelAssistantAddListener(int position);
    }
}
