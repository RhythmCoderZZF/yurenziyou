package com.nbhysj.coupon.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nbhysj.coupon.R;


/**
 * 加载对话框
 */

public class LoadingDialog extends ProgressDialog {

    private TextView mTvLoadDialogTitle;

    /**
     * 加载框构造方法
     *
     * @param context
     */
    public LoadingDialog(Context context) {
        super(context, R.style.MyDialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        mTvLoadDialogTitle = (TextView) findViewById(R.id.tv_load_dialog);
    }

    /**
     * 设置标题
     */
    public LoadingDialog setTitle(String title) {

        if (title.equals("")) {
            mTvLoadDialogTitle.setVisibility(View.GONE);
        } else {
            mTvLoadDialogTitle.setVisibility(View.VISIBLE);
            mTvLoadDialogTitle.setText(title);
        }
        return this;
    }

}
