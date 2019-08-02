package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.PostCollectionAdapter;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/08/01
 * description: 帖子收藏
 */
public class PostCollectionActivity extends BaseActivity {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    //帖子收藏
    @BindView(R.id.rv_post_collection)
    RecyclerView mRvPostCollection;

    private int mPage = 1;

    @Override
    public int getLayoutId()
    {
        return R.layout.activity_post_collection;
    }

    @Override
    public void initView(Bundle savedInstanceState)
    {
        ToolbarHelper.setBar(PostCollectionActivity.this,getResources().getString(R.string.str_collection),R.mipmap.icon_left_arrow_black);

        LinearLayoutManager layoutManager = new LinearLayoutManager(PostCollectionActivity.this);
        mRvPostCollection.setLayoutManager(layoutManager);
        PostCollectionAdapter postCollectionAdapter = new PostCollectionAdapter(PostCollectionActivity.this);
        //postCollectionAdapter.setPostCollectionList("");
        mRvPostCollection.setAdapter(postCollectionAdapter);
    }

    @Override
    public void initData() {
        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //isOnRefresh = true;
                        mPage = 1;
                     /*   mchOrdersResponseList.clear();
                        mOrderListItemAdapter.notifyDataSetChanged();
                        showProgressDialog(getActivity());
                        getOrderList();*/

                    }
                }, 100);
            }
        });

        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        //   isOnLoadMore = true;
                        try {
                          /*  if (mTotalPageCount == mchOrdersResponseList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                getOrderList();
                            }*/
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 200);
            }
        });
    }

    @Override
    public void initPresenter() {

    }
}
