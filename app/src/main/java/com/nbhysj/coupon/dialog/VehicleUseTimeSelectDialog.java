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
import com.bigkoo.pickerview.lib.WheelView;
import com.nbhysj.coupon.R;

/**
 * @auther：hysj created on 2019/08/28
 * description：用车(选择用车时间)
 */
public class VehicleUseTimeSelectDialog extends DialogFragment {
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
    private VehicleUseTimeSelectListener vehicleUseTimeSelectListener;

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
        TextView mTvVehicleUseCancel = view.findViewById(R.id.tv_vehicle_use_cancel);       //取消用车
        TextView mTvVehicleUseConfirm = view.findViewById(R.id.tv_vehicle_use_confirm);     //确定用车
        WheelView mWheelViewDate = view.findViewById(R.id.wheel_view_date);
        WheelView mWheelViewHours = view.findViewById(R.id.wheel_view_hours);
        WheelView mWheelViewMinute = view.findViewById(R.id.wheel_view_minute);

        mTvVehicleUseCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });

        //确认车辆使用
        mTvVehicleUseConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vehicleUseTimeSelectListener.setVehicleUseConfirmCallBack();
            }
        });
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //KeyBoardUtils.closeKeybord((Activity) context);
        super.show(manager, tag);
    }

    public interface VehicleUseTimeSelectListener {

        void setVehicleUseConfirmCallBack();
    }
}
