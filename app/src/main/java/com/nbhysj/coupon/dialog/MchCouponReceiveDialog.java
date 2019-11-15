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
import com.nbhysj.coupon.adapter.ChooseAlbumsCollectionAdapter;
import com.nbhysj.coupon.adapter.MchCouponReceiveListAdapter;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.model.response.FavoritesBean;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/15
 * description：商户优惠券领取
 */
public class MchCouponReceiveDialog extends DialogFragment {
    private Context context;
    private View view;
    private List<MchCouponResponse> mchCouponReceiveList;
    MchCouponReceiveListener mchCouponReceiveListener;
    MchCouponReceiveListAdapter mchCouponReceiveListAdapter;
    RecyclerView mRvCouponReceive;
    Dialog dialog;
    //暂无数据
    private TextView mTvNoData;
    //暂无数据
    RelativeLayout mRlytNoData;

    SmartRefreshLayout mSmartRefreshLayout;
    public MchCouponReceiveDialog() {

    }

    @SuppressLint("ValidFragment")
    public MchCouponReceiveDialog(List<MchCouponResponse> mchCouponReceiveList, MchCouponReceiveListener mchCouponReceiveListener) {

        this.mchCouponReceiveListener = mchCouponReceiveListener;
        this.mchCouponReceiveList = mchCouponReceiveList;
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
        view = LayoutInflater.from(context).inflate(R.layout.layout_coupon_receive_dialog, null);
        mRvCouponReceive = view.findViewById(R.id.rv_coupon_receive);
       // TextView mTvNewAlbum = view.findViewById(R.id.tv_new_album);
        mRlytNoData = view.findViewById(R.id.rlyt_no_data); //暂无数据
        mSmartRefreshLayout = view.findViewById(R.id.refresh_layout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvCouponReceive.setLayoutManager(linearLayoutManager);

        mchCouponReceiveListAdapter = new MchCouponReceiveListAdapter(context, new MchCouponReceiveListAdapter.CouponReceiveListener() {
            @Override
            public void setCouponReceiveCallback(int groupPosition, int childPosition, CouponsBean couponsBean)
            {
                mchCouponReceiveListener.setMchCouponReceiveCallback(groupPosition,childPosition,couponsBean);
            }
        });
        mchCouponReceiveListAdapter.setCouponList(mchCouponReceiveList);
        mRvCouponReceive.setAdapter(mchCouponReceiveListAdapter);
        if(mchCouponReceiveList != null && mchCouponReceiveList.size() > 0){

            mRlytNoData.setVisibility(View.GONE);
        } else{
            mRlytNoData.setVisibility(View.VISIBLE);
        }

/*
        mTvNewAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chooseAlbumsCollectionListener.setNewAlbumCollectionListener();
            }
        });*/

        RelativeLayout mRlytNewTourists = view.findViewById(R.id.rlyt_new_tourists);
        mRlytNewTourists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mchCouponReceiveListener.setCouponListRefreshListener(refreshLayout);
                    }
                }, 100);
            }
        });

     /*   mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {

                mchCouponReceiveListener.setCouponListRefreshListener(refreshLayout);

            }
        });*/
    }

    public void setAlbumCollectList(List<MchCouponResponse> mchCouponReceiveList){

        this.mchCouponReceiveList = mchCouponReceiveList;
        if(mchCouponReceiveList != null && mchCouponReceiveList.size() > 0){

            mRlytNoData.setVisibility(View.GONE);
        } else{
            mRlytNoData.setVisibility(View.VISIBLE);
        }
        mchCouponReceiveListAdapter.setCouponList(mchCouponReceiveList);
        mchCouponReceiveListAdapter.notifyDataSetChanged();
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

    public void setSmartRefreshLayoutRefreshFinish() {
        //KeyBoardUtils.closeKeybord((Activity) context);

        if(mSmartRefreshLayout != null){

            mchCouponReceiveList.clear();
            mchCouponReceiveListAdapter.notifyDataSetChanged();
            mSmartRefreshLayout.finishRefresh();
            mSmartRefreshLayout.setNoMoreData(false);
        }
    }

        public interface MchCouponReceiveListener {

            void setMchCouponReceiveCallback(int groupPosition,int childPosition,CouponsBean couponsBean);

            void setCouponListRefreshListener(RefreshLayout refreshLayout);
    }
}
