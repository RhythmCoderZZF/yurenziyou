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

import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.RoomNumSelectModelWheelAdapter;
import com.nbhysj.coupon.adapter.VehicleSelectionModelWheelAdapter;
import com.nbhysj.coupon.model.response.CarTypeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther：hysj created on 2019/08/26
 * description：选择房间数量型号
 */
public class RoomNumberSelectDialog extends DialogFragment {
    private Context context;
    private View view;

    private VehicleUseSelectModelListener vehicleUseSelectModelListener;

    //房间数选择
    private int mItemSelectRoomNum = 1;

    public RoomNumberSelectDialog() {

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

        view = LayoutInflater.from(context).inflate(R.layout.layout_room_num_select_dialog, null);
        RelativeLayout mRlytRoomNumSelectModel = view.findViewById(R.id.rlyt_room_num_selection_model);
        ImageView mImgRoomNumSelectCancel = view.findViewById(R.id.img_room_num_select_cancel);       //房间数量选择
        TextView mTvRoomNumSelectConfirm = view.findViewById(R.id.tv_room_num_select_confirm);     //确定
        WheelView mWheelViewRoomNumSelectModel = view.findViewById(R.id.wheelview_room_num_select_confirm);

        RoomNumSelectModelWheelAdapter roomNumSelectModelWheelAdapter = new RoomNumSelectModelWheelAdapter();
        mWheelViewRoomNumSelectModel.setAdapter(roomNumSelectModelWheelAdapter);// 设置日期的显示数据
        mWheelViewRoomNumSelectModel.setTextSize(18);//滚轮文字大小
        mWheelViewRoomNumSelectModel.setCurrentItem(0);// 初始化时显示的数据
        mWheelViewRoomNumSelectModel.setGravity(Gravity.CENTER);
        mWheelViewRoomNumSelectModel.setCyclic(false);

        mImgRoomNumSelectCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

            }
        });

        mRlytRoomNumSelectModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        mWheelViewRoomNumSelectModel.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

                mItemSelectRoomNum = index;

            }
        });

        mTvRoomNumSelectConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vehicleUseSelectModelListener.setVehicleUseSelectModelCallBack(mItemSelectRoomNum);
                dismiss();
            }
        });
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //KeyBoardUtils.closeKeybord((Activity) context);
        super.show(manager, tag);
    }

    //车辆选择型号
    public interface VehicleUseSelectModelListener {

        void setVehicleUseSelectModelCallBack(int mItemSelectPosition);
    }

}
