package com.nbhysj.coupon.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.CouponSelectListAdapter;
import com.nbhysj.coupon.adapter.MchCouponReceiveListAdapter;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/15
 * description：商户优惠券领取
 */
public class CouponSelectDialog extends DialogFragment {
    private Context context;
    private View view;
    private List<CouponsBean> mchCouponList;
    CouponSelectListener couponSelectListener;
    CouponSelectListAdapter couponSelectListAdapter;
    RecyclerView mRvCouponSelect;
    Dialog dialog;
    SmartRefreshLayout mSmartRefreshLayout;
    TextView mTvCouponSelectClose;
    private RelativeLayout mRlytCouponSelect;
    public CouponSelectDialog() {

    }

    @SuppressLint("ValidFragment")
    public CouponSelectDialog(List<CouponsBean> mchCouponList, CouponSelectListener couponSelectListener) {

        this.couponSelectListener = couponSelectListener;
        this.mchCouponList = mchCouponList;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        context = getActivity();
        initView();
        dialog = new Dialog(context, R.style.CustomDatePickerDialog);
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

        view = LayoutInflater.from(context).inflate(R.layout.layout_coupon_select_list_dialog, null);
        mRvCouponSelect = view.findViewById(R.id.rv_coupon_select);
        mTvCouponSelectClose = view.findViewById(R.id.tv_coupon_select_close);
        mRlytCouponSelect = view.findViewById(R.id.rlyt_coupon_select);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvCouponSelect.setLayoutManager(linearLayoutManager);

        couponSelectListAdapter = new CouponSelectListAdapter(context, new CouponSelectListAdapter.CouponSelectListener() {
            @Override
            public void setCouponSelectCallback(int couponId,boolean isCouponSelect,String couponTitle) {

                couponSelectListener.setCouponSelectCallback(couponId,isCouponSelect,couponTitle);
              //  dismiss();

            }
        });
        couponSelectListAdapter.setCouponList(mchCouponList);
        mRvCouponSelect.setAdapter(couponSelectListAdapter);

        mTvCouponSelectClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        mRlytCouponSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

    }

    public void setCouponSelectList(List<CouponsBean> couponList){

        this.mchCouponList = couponList;
        couponSelectListAdapter.setCouponList(mchCouponList);
        couponSelectListAdapter.notifyDataSetChanged();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //KeyBoardUtils.closeKeybord((Activity) context);
        super.show(manager, tag);
    }

    public void show() {
        //KeyBoardUtils.closeKeybord((Activity) context);

       dialog.show();
    }

    public void dismiss() {
        //KeyBoardUtils.closeKeybord((Activity) context);

        dialog.dismiss();
    }

    public void setSmartRefreshLayoutLoadMoreFinish() {
        //KeyBoardUtils.closeKeybord((Activity) context);

       if(mSmartRefreshLayout != null){

           mSmartRefreshLayout.finishLoadMore();
       }
    }
/*
    public void setSmartRefreshLayoutRefreshFinish() {
        //KeyBoardUtils.closeKeybord((Activity) context);

        if(mSmartRefreshLayout != null){

            collectionAlbumList.clear();
            chooseAlbumsCollectionAdapter.notifyDataSetChanged();
            mSmartRefreshLayout.finishRefresh();
            mSmartRefreshLayout.setNoMoreData(false);
        }
    }*/

        public interface CouponSelectListener {

            void setCouponSelectCallback(int couponId,boolean isCouponSelect,String couponTitle);

            void setCouponListRefreshListener(RefreshLayout refreshLayout);
    }
}
