package com.nbhysj.coupon.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.services.help.Tip;
import com.nbhysj.coupon.R;

/**
 * @auther：hysj created on 2019/08/26
 * description：用车(填写用车信息)
 */
public class VehicleUseAddDialog extends DialogFragment {
    private Context context;
    private View view;
    //我的位置
    TextView mTvMyLocation;
    //目的地
    TextView mTvDestination;
    //用车时间
    TextView mTvTravelTime;
    //选择车辆型号
    TextView mTvVehicleModel;
    private VehicleUseSelectListener vehicleUseSelectListener;

    public VehicleUseAddDialog() {

    }

    public void setVehicleUseSelectListener(VehicleUseSelectListener vehicleUseSelectListener) {

        this.vehicleUseSelectListener = vehicleUseSelectListener;
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

        view = LayoutInflater.from(context).inflate(R.layout.layout_vehicle_use_add_dialog, null);
        TextView mTvVehicleUseCancel = view.findViewById(R.id.tv_vehicle_use_cancel);       //取消用车
        RelativeLayout mRlytMyLocation = view.findViewById(R.id.rlyt_my_location);          //我的位置
        mTvMyLocation = view.findViewById(R.id.tv_my_location);                    //我的位置
        RelativeLayout mRlytDestination = view.findViewById(R.id.rlyt_destination);         //目的地
        mTvDestination = view.findViewById(R.id.tv_destination);                   //目的地
        RelativeLayout mRlytTravelTime = view.findViewById(R.id.rlyt_your_travel_time);     //出行时间
        mTvTravelTime = view.findViewById(R.id.tv_your_travel_time);               //出行时间
        RelativeLayout mRlytVehicleModel = view.findViewById(R.id.rlyt_vehicle_model);      //车辆型号
        mTvVehicleModel = view.findViewById(R.id.tv_vehicle_model);                //车辆型号


        mTvVehicleUseCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });
        //0:我的位置 1:您要去的地方(目的地) 2:用车时间 3:选择车辆型号
        mRlytMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vehicleUseSelectListener.setVehicleUseCallBack(0);
            }
        });
        //0:我的位置 1:您要去的地方(目的地) 2:用车时间 3:选择车辆型号
        mRlytDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vehicleUseSelectListener.setVehicleUseCallBack(1);
            }
        });
        mRlytTravelTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vehicleUseSelectListener.setVehicleUseCallBack(2);
            }
        });

        mRlytVehicleModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vehicleUseSelectListener.setVehicleUseCallBack(3);
            }
        });

      /*  mLlytScenicSpotAdd.setOnClickListener(new View.OnClickListener() {
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
        });*/

    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //KeyBoardUtils.closeKeybord((Activity) context);
        super.show(manager, tag);
    }

    public interface VehicleUseSelectListener {

        void setVehicleUseCallBack(int vehicleUseOprateFlag);
    }

    public void setVehicleUseAddressData(Tip tip, int mVehicleUseOprateFlag){
        if(mVehicleUseOprateFlag == 0)
        {
            String name = tip.getName();
            mTvMyLocation.setText(name);
        } else if(mVehicleUseOprateFlag == 1){
            String name = tip.getName();
            mTvDestination.setText(name);
        }
    }
}
