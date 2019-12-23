package com.nbhysj.coupon.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import java.util.List;

/**
 * @auther：hysj created on 2019/5/7
 * description：新增游客
 */
public class NewTouristsDialog extends DialogFragment {
    private Context context;
    private View view;

    public NewTouristsDialog() {

    }

    @SuppressLint("ValidFragment")
    public NewTouristsDialog(List<RecipientAddressResponse> recipientAddressResponseList) {

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
        view = LayoutInflater.from(context).inflate(R.layout.layout_new_tourists_dialog, null);
        RecyclerView mRvNewTourists = view.findViewById(R.id.rv_new_tourists);
        RelativeLayout mRlytNewTourists = view.findViewById(R.id.rlyt_new_tourists);
        mRlytNewTourists.setOnClickListener(new View.OnClickListener() {
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
}
