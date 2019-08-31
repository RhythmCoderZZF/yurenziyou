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
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.adapter.NumericWheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.VehicleUseHoursWheelAdapter;
import com.nbhysj.coupon.adapter.VehicleUseMinuteWheelAdapter;
import com.nbhysj.coupon.adapter.VehicleUseWheelAdapter;
import com.nbhysj.coupon.model.response.VehicleUseTimeResponse;
import com.nbhysj.coupon.util.DateUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @auther：hysj created on 2019/08/28
 * description：用车(选择用车时间)
 */
public class VehicleUseTimeSelectDialog extends DialogFragment {
    private Context context;
    private View view;
    private VehicleUseTimeSelectListener vehicleUseTimeSelectListener;
    private String[] hoursArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
    private String[] minuteArray = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51"
            , "52", "53", "54", "55", "56", "57", "58", "59", "60"};
    private String vehicleUseDate;
    private String vehicleUseDateStr;
    private String hourStr;
    private String minuteStr;
    private StringBuffer stringBuffer = new StringBuffer();
    public VehicleUseTimeSelectDialog() {

    }

    public void setVehicleUseTimeSelectListener(VehicleUseTimeSelectListener vehicleUseTimeSelectListener) {

        this.vehicleUseTimeSelectListener = vehicleUseTimeSelectListener;
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

        view = LayoutInflater.from(context).inflate(R.layout.layout_vehicle_use_time_dialog, null);
        RelativeLayout mRlytVehicleUseTime = view.findViewById(R.id.rlyt_vehicle_use_time);
        ImageView mImgVehicleUseCancel = view.findViewById(R.id.img_vehicle_use_cancel);       //取消用车
        TextView mTvVehicleUseTimeConfirm = view.findViewById(R.id.tv_vehicle_use_time_confirm);     //确定用车时间
        WheelView mWheelViewDate = view.findViewById(R.id.wheel_view_date);
        WheelView mWheelViewHours = view.findViewById(R.id.wheel_view_hours);
        WheelView mWheelViewMinute = view.findViewById(R.id.wheel_view_minute);
        List<VehicleUseTimeResponse> vehicleUseTimeList = DateUtil.getVehicleUseTime();

        VehicleUseWheelAdapter dateVehicleUseWheelAdapter = new VehicleUseWheelAdapter(vehicleUseTimeList);
        mWheelViewDate.setAdapter(dateVehicleUseWheelAdapter);// 设置日期的显示数据
        mWheelViewDate.setTextSize(16);//滚轮文字大小
        mWheelViewDate.setCurrentItem(0);// 初始化时显示的数据
        mWheelViewDate.setGravity(Gravity.CENTER);
        mWheelViewDate.setCyclic(false);


        VehicleUseHoursWheelAdapter hoursVehicleUseWheelAdapter = new VehicleUseHoursWheelAdapter(Arrays.asList(hoursArray));
        mWheelViewHours.setAdapter(hoursVehicleUseWheelAdapter);// 设置月显示数据
        mWheelViewHours.setTextSize(16);//滚轮文字大小
        mWheelViewHours.setCurrentItem(0);// 初始化时显示的数据
        mWheelViewHours.setGravity(Gravity.CENTER);
        mWheelViewMinute.setCyclic(true);

        VehicleUseMinuteWheelAdapter minuteVehicleUseWheelAdapter = new VehicleUseMinuteWheelAdapter(Arrays.asList(minuteArray));
        mWheelViewMinute.setAdapter(minuteVehicleUseWheelAdapter);// 设置月显示数据
        mWheelViewMinute.setTextSize(16);//滚轮文字大小
        mWheelViewMinute.setCurrentItem(0);// 初始化时显示的数据
        mWheelViewMinute.setGravity(Gravity.CENTER);
        mWheelViewMinute.setCyclic(true);

        vehicleUseDateStr = vehicleUseTimeList.get(0).getDate();
        hourStr = hoursArray[0];
        minuteStr = minuteArray[0];

      /*  VehicleUseWheelAdapter minuteVehicleUseWheelAdapter = new VehicleUseWheelAdapter(vehicleUseTime);
        mWheelViewDate.setAdapter(minuteVehicleUseWheelAdapter);// 设置月显示数据
        mWheelViewDate.setTextSize(16);//滚轮文字大小
        mWheelViewDate.setCurrentItem(0);// 初始化时显示的数据
        mWheelViewDate.setGravity(Gravity.CENTER);
        mWheelViewDate.setCyclic(true);
*/

        mWheelViewDate.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

                vehicleUseDateStr = vehicleUseTimeList.get(index).getDate();
            }
        });

        mWheelViewHours.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

                hourStr = hoursArray[index];
            }
        });

        mWheelViewMinute.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

                minuteStr = minuteArray[index];
            }
        });

        mImgVehicleUseCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });

        //确认车辆使用
        mTvVehicleUseTimeConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuffer.setLength(0);
                stringBuffer.append(vehicleUseDateStr);
                stringBuffer.append(" ");
                if(hourStr.length() == 1)
                {
                    hourStr = "0" + hourStr;
                }
                stringBuffer.append(hourStr);
                stringBuffer.append(":");
                stringBuffer.append(minuteStr);
                stringBuffer.append(":00");
                vehicleUseTimeSelectListener.setVehicleUseConfirmCallBack(stringBuffer.toString());
                dismiss();
            }
        });

        mRlytVehicleUseTime.setOnClickListener(new View.OnClickListener() {
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

    public interface VehicleUseTimeSelectListener {

        void setVehicleUseConfirmCallBack(String vehicleUseDateStr);
    }
}
