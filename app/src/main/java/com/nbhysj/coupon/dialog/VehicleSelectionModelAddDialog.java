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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.services.help.Tip;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.VehicleSelectionModelWheelAdapter;
import com.nbhysj.coupon.adapter.VehicleUseWheelAdapter;
import com.nbhysj.coupon.model.response.CarTypeBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @auther：hysj created on 2019/08/26
 * description：选择车辆型号
 */
public class VehicleSelectionModelAddDialog extends DialogFragment {
    private Context context;
    private View view;

    private VehicleUseSelectModelListener vehicleUseSelectModelListener;

   // private String[] carTypeArray = {"新能源", "舒适型", "豪华型", "商务型"};
    private List<CarTypeBean> carTypeList = null;

    private CarTypeBean carTypeBean;
    public VehicleSelectionModelAddDialog() {

    }

    public void setVehicleUseSelectListener(VehicleUseSelectModelListener vehicleUseSelectModelListener) {

        this.vehicleUseSelectModelListener = vehicleUseSelectModelListener;
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

        if (carTypeList == null) {

            carTypeList = new ArrayList<>();
        } else {

            carTypeList.clear();
        }

        CarTypeBean newEnergyCarType = new CarTypeBean();
        newEnergyCarType.setCarTypeCode(2);
        newEnergyCarType.setCarTypeName("新能源");

        CarTypeBean comfortableType = new CarTypeBean();
        comfortableType.setCarTypeCode(3);
        comfortableType.setCarTypeName("舒适型");


        CarTypeBean LuxuryCarType = new CarTypeBean();
        LuxuryCarType.setCarTypeCode(4);
        LuxuryCarType.setCarTypeName("豪华型");


        CarTypeBean businessCarType = new CarTypeBean();
        businessCarType.setCarTypeCode(5);
        businessCarType.setCarTypeName("商务型");

        carTypeList.add(newEnergyCarType);
        carTypeList.add(comfortableType);
        carTypeList.add(LuxuryCarType);
        carTypeList.add(businessCarType);

        view = LayoutInflater.from(context).inflate(R.layout.layout_vehicle_selection_model_dialog, null);
        RelativeLayout mRlytVehicleUseSelectionModel = view.findViewById(R.id.rlyt_vehicle_use_selection_model);
        ImageView mImgVehicleUseCancel = view.findViewById(R.id.img_vehicle_use_cancel);       //取消用车
        TextView mTvVehicleUseConfirm = view.findViewById(R.id.tv_vehicle_use_confirm);     //确定
        WheelView mWheelViewVehicleSelectionModel = view.findViewById(R.id.wheel_view_vehicle_selection_model);

        VehicleSelectionModelWheelAdapter selectionModelWheelAdapter = new VehicleSelectionModelWheelAdapter(carTypeList);
        mWheelViewVehicleSelectionModel.setAdapter(selectionModelWheelAdapter);// 设置日期的显示数据
        mWheelViewVehicleSelectionModel.setTextSize(18);//滚轮文字大小
        mWheelViewVehicleSelectionModel.setCurrentItem(0);// 初始化时显示的数据
        mWheelViewVehicleSelectionModel.setGravity(Gravity.CENTER);
        mWheelViewVehicleSelectionModel.setCyclic(false);

        mImgVehicleUseCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });

        mRlytVehicleUseSelectionModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        mWheelViewVehicleSelectionModel.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if(carTypeList != null)
                {
                    carTypeBean = carTypeList.get(index);
                }
            }
        });

        mTvVehicleUseConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vehicleUseSelectModelListener.setVehicleUseSelectModelCallBack(carTypeBean);
                dismiss();
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

    //车辆选择型号
    public interface VehicleUseSelectModelListener {

        void setVehicleUseSelectModelCallBack(CarTypeBean carType);
    }

}
