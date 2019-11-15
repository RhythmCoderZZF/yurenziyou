package com.nbhysj.coupon.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.AuthorityProhibitListAdapter;
import com.nbhysj.coupon.model.response.AuthorityVerificationBean;

import java.util.List;

/**
 * create by fqq at 2018/07/18
 * description: 权限检测
 */
public class AuthorityAllProhibitDialog {
    /**
     * 上下文
     **/
    private Context context;

    /**
     * 提示提交弹框
     **/
    private Dialog dialog;

    /**
     * 内容
     */
    private RecyclerView mRvAuthorityVerification;

    /**
     * 标题
     */
    private TextView mTvTitle;

    /**
     * 控件布局
     **/
    private LinearLayout layoutBg;

    /**
     * 弹框占屏幕大小尺寸
     **/
    private Display display;

    private AuthorityProhibitListAdapter authorityProhibitListAdapter;

    private RelativeLayout mRlytAuthorityVerification, mRlytDeniedPermissionExit;

    /**
     * 构造器
     *
     * @param context
     */
    public AuthorityAllProhibitDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    /**
     * 弹框创建
     *
     * @return
     */
    public AuthorityAllProhibitDialog builder(List<AuthorityVerificationBean> authorityProhibitList) {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_authority_all_prohibit, null);

        // 获取自定义Dialog布局中的控件
        layoutBg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        mRvAuthorityVerification = (RecyclerView) view.findViewById(R.id.rv_authority_verification);
        mRlytAuthorityVerification = (RelativeLayout) view.findViewById(R.id.rlyt_open_authority);
        mRlytDeniedPermissionExit = (RelativeLayout) view.findViewById(R.id.rlyt_denied_permission_exit);

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        // 设置布局管理器
        mRvAuthorityVerification.setLayoutManager(layoutManager);

        authorityProhibitListAdapter = new AuthorityProhibitListAdapter(context);
        authorityProhibitListAdapter.setAuthorityVerificationList(authorityProhibitList);
        mRvAuthorityVerification.setAdapter(authorityProhibitListAdapter);

        mTvTitle = (TextView) view.findViewById(R.id.txt_title);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        layoutBg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.80), LayoutParams.WRAP_CONTENT));

        return this;
    }

    /**
     * 设置标题
     */
    public AuthorityAllProhibitDialog setContent() {


        return this;
    }

    /**
     * 设置标题
     */
    public AuthorityAllProhibitDialog setTitle(String title) {

        if (title.equals("")) {
            mTvTitle.setVisibility(View.GONE);
        } else {
            mTvTitle.setVisibility(View.VISIBLE);
            mTvTitle.setText(title);
        }
        return this;
    }

    /**
     * 设置布局
     *
     * @param view
     * @return
     */
    public AuthorityAllProhibitDialog setView(View view) {

        dialog.setContentView(view);

        return this;
    }

    /**
     * 设置是否可以点击消失
     *
     * @param cancel
     * @return
     */
    public AuthorityAllProhibitDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * @param listener
     * @return
     */
    public AuthorityAllProhibitDialog setOpenAuthorityVerification(final OnClickListener listener) {
        mRlytAuthorityVerification.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    /**
     * @param listener
     * @return
     */
    public AuthorityAllProhibitDialog setDeniedPermissionExit(final OnClickListener listener) {
        mRlytDeniedPermissionExit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    /**
     * 弹框显示
     */
    public void show() {
        dialog.show();
    }
}
