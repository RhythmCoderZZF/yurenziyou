package com.nbhysj.coupon.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiSearch;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CarTypeBean;
import com.nbhysj.coupon.model.response.EstimatedPriceResponse;
import com.nbhysj.coupon.util.Tools;

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
    //用车费用合计
    TextView mTvVehicleUseExpenses;

    private VehicleUseSelectListener vehicleUseSelectListener;
    //约车时间
    private String mDepartureTime;

    //起点经度
    private String mStartLg;
    //起点纬度
    private String mStartLt;
    //终点经度
    private String mEndLg;
    //终点纬度
    private String mEndLt;

    //车类型
    private int mCarType;

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
        RelativeLayout mRlytVehicleUseAdd = view.findViewById(R.id.rlyt_vehicle_use_add);
        TextView mTvVehicleUseCancel = view.findViewById(R.id.tv_vehicle_use_cancel);       //取消用车
        RelativeLayout mRlytMyLocation = view.findViewById(R.id.rlyt_my_location);          //我的位置
        mTvMyLocation = view.findViewById(R.id.tv_my_location);                    //我的位置
        RelativeLayout mRlytDestination = view.findViewById(R.id.rlyt_destination);         //目的地
        mTvDestination = view.findViewById(R.id.tv_destination);                   //目的地
        RelativeLayout mRlytTravelTime = view.findViewById(R.id.rlyt_your_travel_time);     //出行时间
        mTvTravelTime = view.findViewById(R.id.tv_your_travel_time);               //出行时间
        RelativeLayout mRlytVehicleModel = view.findViewById(R.id.rlyt_vehicle_model);      //车辆型号
        mTvVehicleModel = view.findViewById(R.id.tv_vehicle_model);                //车辆型号
        TextView mTvVehicleUseExpenses = view.findViewById(R.id.tv_vehicle_use_expenses); //用车费用


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

        mRlytVehicleUseAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //KeyBoardUtils.closeKeybord((Activity) context);
        super.show(manager, tag);
    }

    //设置用车1.开始位置和2.结束位置
    public void setVehicleUseAddressData(LatLonPoint latLonPoint, String addressName,int mVehicleUseOprateFlag) {
        if (mVehicleUseOprateFlag == 0) {

            mTvMyLocation.setText(addressName);

            if (latLonPoint != null) {
                double startLongitude = latLonPoint.getLongitude();
                double startLatitude = latLonPoint.getLatitude();
                mStartLg = String.valueOf(startLongitude);
                mStartLt = String.valueOf(startLatitude);
            } else {

                vehicleUseSelectListener.setAddressPOISearchCallBack(addressName,mVehicleUseOprateFlag);
            }

        } else if (mVehicleUseOprateFlag == 1) {
            mTvDestination.setText(addressName);

            if (latLonPoint != null)
            {
                double endLongitude = latLonPoint.getLongitude();
                double endLatitude = latLonPoint.getLatitude();
                mEndLg = String.valueOf(endLongitude);
                mEndLt = String.valueOf(endLatitude);
            } else {

                vehicleUseSelectListener.setAddressPOISearchCallBack(addressName,mVehicleUseOprateFlag);
            }
        }
        evaluateVehicleUsageFee();
    }

    //3.设置用车开出行时间
    public void setVehicleUseTravelTime(String travelTime) {
        if (!TextUtils.isEmpty(travelTime)) {
            mDepartureTime = travelTime;
            mTvTravelTime.setText(travelTime);
            evaluateVehicleUsageFee();
        }
    }

    //设置车辆型号
    public void setVehicleModelSelect(CarTypeBean carTypeBean) {
        String carTypeName = carTypeBean.getCarTypeName();
        if (!TextUtils.isEmpty(carTypeName)) {
            mTvVehicleModel.setText(carTypeName);
            mCarType = carTypeBean.getCarTypeCode();
            evaluateVehicleUsageFee();
        }
    }


    //设置用车费用合计
    public void setTotalVehicleExpenses(EstimatedPriceResponse estimatedPrice) {
        String price = estimatedPrice.getPrice();

        if (!TextUtils.isEmpty(price)) {
            mTvVehicleUseExpenses.setText(Tools.getTwoDecimalPoint(price));
        }
    }

    //评估用车价格
    public void evaluateVehicleUsageFee() {
        String location = mTvMyLocation.getText().toString();
        String destination = mTvDestination.getText().toString();
        String travelTime = mTvTravelTime.getText().toString();
        String vehicleModel = mTvVehicleModel.getText().toString();

        if (!TextUtils.isEmpty(location) && !TextUtils.isEmpty(destination) && !TextUtils.isEmpty(travelTime) && !TextUtils.isEmpty(vehicleModel) && mCarType != 0) {

            vehicleUseSelectListener.setEvaluateVehicleUsageFeeCallBack(mDepartureTime, mStartLg, mStartLt, mEndLg, mEndLt, mCarType);
        }
    }


    public interface VehicleUseSelectListener {

        void setAddressPOISearchCallBack(String addressName,int vehicleUseOprateFlag);

        void setEvaluateVehicleUsageFeeCallBack(String departureTime, String startLg, String startLt, String endLg, String endLt, int carTyp);

        void setVehicleUseCallBack(int vehicleUseOprateFlag);
    }


}
