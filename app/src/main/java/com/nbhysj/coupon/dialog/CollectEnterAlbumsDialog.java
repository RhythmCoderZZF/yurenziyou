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
import com.nbhysj.coupon.adapter.RecommendDeliciousFoodAdapter;
import com.nbhysj.coupon.model.response.CollectionAlbumListResponse;
import com.nbhysj.coupon.model.response.FavoritesBean;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.ui.RecommendFoodLookMoreActivity;
import com.nbhysj.coupon.ui.StrategyActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther：hysj created on 2019/10/15
 * description：收藏专辑
 */
public class CollectEnterAlbumsDialog extends DialogFragment {
    private Context context;
    private View view;
    private List<FavoritesBean> collectionAlbumList;
    ChooseAlbumsCollectionListener chooseAlbumsCollectionListener;
    ChooseAlbumsCollectionAdapter chooseAlbumsCollectionAdapter;
    RecyclerView mRvCollectEnterAlbums;
    Dialog dialog;
    //暂无数据
    private TextView mTvNoData;
    //暂无数据
    RelativeLayout mRlytNoData;

    SmartRefreshLayout mSmartRefreshLayout;
    public CollectEnterAlbumsDialog() {

    }

    @SuppressLint("ValidFragment")
    public CollectEnterAlbumsDialog(List<FavoritesBean> collectionAlbumList,ChooseAlbumsCollectionListener chooseAlbumsCollectionListener) {

        this.chooseAlbumsCollectionListener = chooseAlbumsCollectionListener;
        this.collectionAlbumList = collectionAlbumList;


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
        view = LayoutInflater.from(context).inflate(R.layout.layout_collect_enter_albums_dialog, null);
        mRvCollectEnterAlbums = view.findViewById(R.id.rv_collect_enter_albums);
        TextView mTvNewAlbum = view.findViewById(R.id.tv_new_album);
        mRlytNoData = view.findViewById(R.id.rlyt_no_data); //暂无数据
        mTvNoData = view.findViewById(R.id.tv_nodata); //暂无数据
        mSmartRefreshLayout = view.findViewById(R.id.refresh_layout);
        mTvNoData.setText("暂无收藏数据");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvCollectEnterAlbums.setLayoutManager(linearLayoutManager);

        chooseAlbumsCollectionAdapter = new ChooseAlbumsCollectionAdapter(context,new ChooseAlbumsCollectionAdapter.ChooseAlbumsCollectionListener() {
            @Override
            public void setChooseAlbumsCollectionListener(FavoritesBean collectionAlbum) {

                chooseAlbumsCollectionListener.setChooseAlbumsCollectionListener(collectionAlbum);

            }
        });
        chooseAlbumsCollectionAdapter.setChooseAlbumsCollectionList(collectionAlbumList);
        mRvCollectEnterAlbums.setAdapter(chooseAlbumsCollectionAdapter);
        if(collectionAlbumList != null && collectionAlbumList.size() > 0){

            mRlytNoData.setVisibility(View.GONE);
        } else{
            mRlytNoData.setVisibility(View.VISIBLE);
        }


        mTvNewAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chooseAlbumsCollectionListener.setNewAlbumCollectionListener();
            }
        });

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

                        chooseAlbumsCollectionListener.setNewAlbumOnRefreshListener();
                    }
                }, 100);
            }
        });

        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {

                chooseAlbumsCollectionListener.setNewAlbumOnLoadMoreListener(refreshLayout);

            }
        });
    }

    public void setAlbumCollectList(List<FavoritesBean> collectionAlbumList){

        this.collectionAlbumList = collectionAlbumList;
        if(collectionAlbumList != null && collectionAlbumList.size() > 0){

            mRlytNoData.setVisibility(View.GONE);
        } else{
            mRlytNoData.setVisibility(View.VISIBLE);
        }
        chooseAlbumsCollectionAdapter.setChooseAlbumsCollectionList(collectionAlbumList);
        chooseAlbumsCollectionAdapter.notifyDataSetChanged();
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

            collectionAlbumList.clear();
            chooseAlbumsCollectionAdapter.notifyDataSetChanged();
            mSmartRefreshLayout.finishRefresh();
            mSmartRefreshLayout.setNoMoreData(false);
        }
    }

        public interface ChooseAlbumsCollectionListener {

        void setChooseAlbumsCollectionListener(FavoritesBean collectionAlbum);

        void setNewAlbumCollectionListener();

        void setNewAlbumOnRefreshListener();

        void setNewAlbumOnLoadMoreListener(RefreshLayout refreshLayout);

    }
}
