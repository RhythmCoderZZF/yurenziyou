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
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.request.CarsBean;
import com.nbhysj.coupon.model.response.CarTypeBean;
import com.nbhysj.coupon.model.response.EstimatedPriceResponse;

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
    //用车确认
    TextView mTvVehicleUseConfirm;
    //用车确认
    TextView mTvVehicleUseSure;

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

    //预估价格，单位分
    int duration;

    //预估价格，单位分
    double price;

    //预估金额的key，一口价订单会对预估金额做校验，priceKey有效期为5分钟，如果priceKey过期或出发地经纬度有变化必须重新预估
    String priceKey;

    //开始地点
    private String startAddressName;

    //结束地点
    private String endAddressName;

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
        mTvVehicleUseExpenses = view.findViewById(R.id.tv_vehicle_use_expenses); //用车费用
        mTvVehicleUseConfirm = view.findViewById(R.id.tv_vehicle_use_confirm);
        mTvVehicleUseSure = view.findViewById(R.id.tv_vehicle_use_sure);


        mTvVehicleUseCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vehicleUseSelectListener.setVehicleUseCancelCallBack();
                dismiss();

            }
        });
        //0:我的位置 1:您要去的地方(目的地) 2:用车时间 3:选择车辆型号
        mRlytMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vehicleUseSelectListener.setVehicleUseSelectFlagCallBack(0);
            }
        });
        //0:我的位置 1:您要去的地方(目的地) 2:用车时间 3:选择车辆型号
        mRlytDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vehicleUseSelectListener.setVehicleUseSelectFlagCallBack(1);
            }
        });
        mRlytTravelTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vehicleUseSelectListener.setVehicleUseSelectFlagCallBack(2);
            }
        });

        mRlytVehicleModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vehicleUseSelectListener.setVehicleUseSelectFlagCallBack(3);

            }
        });

        mTvVehicleUseSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVehicleUseConfirm();
            }
        });

        mTvVehicleUseConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getVehicleUseConfirm();
            }
        });
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    //设置用车1.开始位置和2.结束位置
    public void setVehicleUseAddressData(LatLonPoint latLonPoint, String addressName, int mVehicleUseOprateFlag) {
        if (mVehicleUseOprateFlag == 0) {

            mTvMyLocation.setText(addressName);

            if (latLonPoint != null) {
                double startLongitude = latLonPoint.getLongitude();
                double startLatitude = latLonPoint.getLatitude();
                mStartLg = String.valueOf(startLongitude);
                mStartLt = String.valueOf(startLatitude);
            } else {

                vehicleUseSelectListener.setAddressPOISearchCallBack(addressName, mVehicleUseOprateFlag);
            }

        } else if (mVehicleUseOprateFlag == 1) {
            mTvDestination.setText(addressName);

            if (latLonPoint != null) {
                double endLongitude = latLonPoint.getLongitude();
                double endLatitude = latLonPoint.getLatitude();
                mEndLg = String.valueOf(endLongitude);
                mEndLt = String.valueOf(endLatitude);
            } else {

                vehicleUseSelectListener.setAddressPOISearchCallBack(addressName, mVehicleUseOprateFlag);
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
        price = estimatedPrice.getPrice();
        // double estimatedPriceDouble = estimatedPrice.getPrice() * 100;
        //price = (int) (estimatedPriceDouble);
        duration = estimatedPrice.getDuration();
        priceKey = estimatedPrice.getPriceKey();

        mTvVehicleUseExpenses.setText(String.valueOf(price));
    }

    //评估用车价格
    public void evaluateVehicleUsageFee() {
        startAddressName = mTvMyLocation.getText().toString();  //我的位置
        endAddressName = mTvDestination.getText().toString();   //目的地
        String travelTime = mTvTravelTime.getText().toString();  //出行时间
        String vehicleModel = mTvVehicleModel.getText().toString(); //车辆类型选择

        if (!TextUtils.isEmpty(startAddressName) && !TextUtils.isEmpty(endAddressName) && !TextUtils.isEmpty(travelTime) && !TextUtils.isEmpty(vehicleModel) && mCarType != 0) {

            vehicleUseSelectListener.setEvaluateVehicleUsageFeeCallBack(mDepartureTime, mStartLg, mStartLt, mEndLg, mEndLt, mCarType);
        }
    }


    public interface VehicleUseSelectListener {

        //如果检索没有经纬度  再通过POI检索
        void setAddressPOISearchCallBack(String addressName, int vehicleUseOprateFlag);

        //用车费用预估
        void setEvaluateVehicleUsageFeeCallBack(String departureTime, String startLg, String startLt, String endLg, String endLt, int carTyp);

        //0:我的位置 1:您要去的地方(目的地) 2:用车时间 3:选择车辆型号
        void setVehicleUseSelectFlagCallBack(int vehicleUseOprateFlag);

        void setVehicleUseConfirmCallBack(CarsBean carsBean);

        //未选择完整相应提示
        void setVehicleUseParamEmptyCallBack(String message);

        //车辆使用取消
        void setVehicleUseCancelCallBack();
    }

    //组装cars字段参数
    public CarsBean getCarsBean() {
        CarsBean carsBean = new CarsBean();
        carsBean.setCarType(mCarType);
        carsBean.setDepartureTime(mDepartureTime);
        carsBean.setStartLg(mStartLg);
        carsBean.setStartLt(mStartLt);
        carsBean.setEndLg(mEndLg);
        carsBean.setEndLt(mEndLt);
        carsBean.setStartName(startAddressName);
        carsBean.setEndName(endAddressName);
        carsBean.setPrice(price);
        carsBean.setPriceKey(priceKey);
        carsBean.setDuration(duration);

        return carsBean;
    }

    public void getVehicleUseConfirm() {

        startAddressName = mTvMyLocation.getText().toString();
        endAddressName = mTvDestination.getText().toString();
        String travelTime = mTvTravelTime.getText().toString();
        String vehicleModel = mTvVehicleModel.getText().toString();

        if (TextUtils.isEmpty(startAddressName)) {
            vehicleUseSelectListener.setVehicleUseParamEmptyCallBack("请选择您当前位置");
            return;
        }

        if (TextUtils.isEmpty(endAddressName)) {
            vehicleUseSelectListener.setVehicleUseParamEmptyCallBack("请选择目的地");
            return;
        }

        if (TextUtils.isEmpty(travelTime)) {
            vehicleUseSelectListener.setVehicleUseParamEmptyCallBack("请选择出行时间");
            return;
        }

        if (TextUtils.isEmpty(vehicleModel)) {
            vehicleUseSelectListener.setVehicleUseParamEmptyCallBack("请选择车辆类型");
            return;
        }

        CarsBean carsBean = getCarsBean();
        vehicleUseSelectListener.setVehicleUseConfirmCallBack(carsBean);
        dismiss();

    }
}
