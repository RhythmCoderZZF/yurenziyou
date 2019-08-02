package com.nbhysj.coupon.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Date;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/06/18
 * description：我的回答(提问)列表
 */
public class AskQuestionListFragment extends BaseFragment{
    private static final String ORDER_STATUS_CODE = "orderStatus";
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;

    private int mPage = 1;
    int mTotalPageCount;
    private boolean hasLoaded = false;

    private boolean isCreated = false;

    private boolean isVisibleToUser = false;
    public String TAG = "AskQuestionListFragment.this";

    public AskQuestionListFragment() {
        // Required empty public constructor

    }

    public static AskQuestionListFragment newInstance(String orderStatus) {

        AskQuestionListFragment fragment = new AskQuestionListFragment();
        Bundle args = new Bundle();
        args.putString(ORDER_STATUS_CODE, orderStatus);
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


            long time = new Date().getTime();

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_follow_sub_list;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }


    @Override
    protected void initView(View v) {



        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
      /*  mRvOrderListView.setLayoutManager(linearLayoutManager);
        mOrderListItemAdapter = new OrderListSubItemAdapter(getActivity());
        mOrderListItemAdapter.setOrderList(mchOrdersResponseList);
        mOrderListItemAdapter.setOrderListItemListener(this);
        mRvOrderListView.setAdapter(mOrderListItemAdapter);*/

        isCreated = true;
        lazyLoad();
    }

    @Override
    public void initData() {
       // getOrderList();
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

   /* @Override
    public void getMchOrdersListResult(BackResult<List<MchOrdersResponse>> res) {

        dismissProgressDialog();
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    if (isOnLoadMore) {

                        mSmartRefreshLayout.finishLoadMore();
                    } else {

                        mchOrdersResponseList.clear();
                        mOrderListItemAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    List<MchOrdersResponse> mchOrdersList = res.getData();
                    mTotalPageCount = res.getTotalNumber();

                    if (mTotalPageCount == 0) {
                        mRlytNoData.setVisibility(View.VISIBLE);
                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }
                    if (mchOrdersList != null) {
                        mchOrdersResponseList.addAll(mchOrdersList);
                    }

                    if (mPage == 1 && isOnRefresh) {

                        mOrderListItemAdapter.setOrderList(mchOrdersResponseList);
                        mRvOrderListView.setAdapter(mOrderListItemAdapter);
                    } else {

                        mOrderListItemAdapter.setOrderList(mchOrdersResponseList);
                        mOrderListItemAdapter.notifyDataSetChanged();
                    }
                    isOnRefresh = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_RELOGIN_CODE:
                onReLogin(getResources().getString(R.string.str_token_invalid));
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }

    }

   */

    @Override
    public void lazyInitView(View view) {
      /*  showProgressDialog(getActivity());
        mchOrdersResponseList.clear();
       // mOrderListItemAdapter.notifyDataSetChanged();
        isOnRefresh = true;
        isOnLoadMore = false;
        mPage = 1;
        getOrderList();*/
    }


    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if (Constants.BROCAST_ACTION_TRIP_ASSISANT.equals(action)) {

                // showToast(getActivity(),"111");
            }
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisibleToUser = isVisibleToUser;//注：关键步骤
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoad();
        }
    }

    private void lazyLoad() {
        if (!isVisibleToUser || hasLoaded || !isCreated) {
            return;
        }

        showProgressDialog(getActivity());
      //  mchOrdersResponseList.clear();
        // mOrderListItemAdapter.notifyDataSetChanged();
        //isOnRefresh = true;
        //isOnLoadMore = false;
        mPage = 1;
       // getOrderList();
        // lazyInitView();
        //hasLoaded = true;//注：关键步骤，确保数据只加载一次
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);


    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        OrderMainFragment orderMainFragment = (MainActivity)context;
        orderMainFragment.updateActivityUIFromFragment(this);
    }*/


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
