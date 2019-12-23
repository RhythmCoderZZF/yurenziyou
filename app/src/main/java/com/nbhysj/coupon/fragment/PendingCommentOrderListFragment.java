package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MyOrderListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.OrderListContract;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.model.OrderListModel;
import com.nbhysj.coupon.model.request.OrderDeleteRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.UserOrderListResponse;
import com.nbhysj.coupon.presenter.OrderListPresenter;
import com.nbhysj.coupon.ui.OrderEvaluateActivity;
import com.nbhysj.coupon.ui.OrderGroupEvaluateActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * created by hysj on 2018/09/27.
 * description: 我的订单(待评论列表)
 */
public class PendingCommentOrderListFragment extends BaseFragment<OrderListPresenter, OrderListModel> implements OrderListContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //我的订单列表
    @BindView(R.id.rv_my_order)
    RecyclerView mRvMyOrderList;
    //暂无订单数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoOrderData;
    private List<UserOrderListResponse.OrderTypeEntity> pendingTravelOrderTypeList;
    private int mPage = 1;
    private int mPageSize = 10;
    private MyOrderListAdapter myOrderListAdapter;
    UserOrderListResponse.OrderTypeEntity mOrderTypeEntity;
    int mTotalPageCount;
    private boolean isOnLoadMore = false;

    private boolean visibleToUser;

    public static PendingCommentOrderListFragment newInstance(String content) {
        PendingCommentOrderListFragment fragment = new PendingCommentOrderListFragment();

        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_order_list;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView(View v) {

        if (pendingTravelOrderTypeList == null) {

            pendingTravelOrderTypeList = new ArrayList<>();
        } else {

            pendingTravelOrderTypeList.clear();
        }

        // 创建一个线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvMyOrderList.setLayoutManager(linearLayoutManager);

        myOrderListAdapter = new MyOrderListAdapter(getActivity(), new MyOrderListAdapter.MyOrderListener() {
            @Override
            public void setOrderDeleteListener(int position, UserOrderListResponse.OrderTypeEntity orderTypeEntity) {

                mOrderTypeEntity = orderTypeEntity;
                OprateDialog oprateDialog = new OprateDialog(getActivity()).builder().setTitle(getResources().getString(R.string.str_sure_to_delete_the_order));
                oprateDialog.setNegativeButton(getResources().getString(R.string.str_cancel), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                oprateDialog.setPositiveButton(getResources().getString(R.string.str_confirm), getResources().getColor(R.color.color_blue2), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        orderDelete();
                    }
                });

                oprateDialog.show();
            }


            @Override
            public void setOrderCancelListener(int position,UserOrderListResponse.OrderTypeEntity orderTypeEntity) {  //待付款

                mOrderTypeEntity = orderTypeEntity;
                OprateDialog oprateDialog = new OprateDialog(getActivity()).builder().setTitle(getResources().getString(R.string.str_sure_to_cancel_the_order));
                oprateDialog.setNegativeButton(getResources().getString(R.string.str_cancel), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                oprateDialog.setPositiveButton(getResources().getString(R.string.str_confirm), getResources().getColor(R.color.color_blue2), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //order();
                    }
                });

                oprateDialog.show();

            }

            @Override
            public void setOrderAllRefundListener(String orderNo)
            {

            }

            @Override
            public void setGroupOrderCommentListener(String orderNo)
            {
                Intent intent = new Intent();
                intent.setClass(getActivity(), OrderGroupEvaluateActivity.class);
                intent.putExtra("orderNo", orderNo);
                startActivity(intent);
            }

            @Override
            public void setPartialOrderCommentListener(int orderGoodsId) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), OrderEvaluateActivity.class);
                intent.putExtra("orderGoodsId", orderGoodsId);
                startActivity(intent);
            }
        });
        myOrderListAdapter.setMyOrderList(pendingTravelOrderTypeList);
        mRvMyOrderList.setAdapter(myOrderListAdapter);
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
                        mPage = 1;
                        pendingTravelOrderTypeList.clear();
                        myOrderListAdapter.notifyDataSetChanged();
                        // showProgressDialog(getActivity());
                        getPendingCommentOrderList();

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
                        isOnLoadMore = true;
                        try {
                            if (mTotalPageCount == pendingTravelOrderTypeList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                getPendingCommentOrderList();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 200);
            }
        });


    }

    @Override
    public void showMsg(String msg) {
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
        private int itemSpace;
        private int itemNum;

        /**
         * @param itemSpace item间隔
         * @param itemNum   每行item的个数
         */
        public RecyclerItemDecoration(int itemSpace, int itemNum) {
            this.itemSpace = itemSpace;
            this.itemNum = itemNum;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = itemSpace;
                /*if (parent.getChildLayoutPosition(view)%itemNum == 0){  //parent.getChildLayoutPosition(view) 获取view的下标
                    outRect.left = 0;
                } else {
                    outRect.left = itemSpace;
                }*/
            outRect.left = itemSpace;
            outRect.right = itemSpace;
            //outRect.right = itemSpace;
        }
    }

    @Override
    public void lazyInitView(View view) {
        if (pendingTravelOrderTypeList != null) {
            pendingTravelOrderTypeList.clear();
        }
        showProgressDialog(getActivity());
        getPendingCommentOrderList();
    }

    @Override
    public void getUserOrderListResult(BackResult<UserOrderListResponse> res) {

    }

    @Override
    public void getAwaitGoingOrderListResult(BackResult<UserOrderListResponse> res) {

    }

    @Override
    public void getAwaitCommentOrderListResult(BackResult<UserOrderListResponse> res) {
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

                        pendingTravelOrderTypeList.clear();
                        myOrderListAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }
                    UserOrderListResponse orderListResponse = res.getData();
                    BasePaginationResult paginationResult = orderListResponse.getPage();
                    mTotalPageCount = paginationResult.getPageCount();

                    if (mTotalPageCount == 0) {
                        mRlytNoOrderData.setVisibility(View.VISIBLE);
                    } else {
                        mRlytNoOrderData.setVisibility(View.GONE);
                    }

                    List<UserOrderListResponse.OrderTypeEntity> orderTypeList = orderListResponse.getResult();

                    if (orderTypeList != null) {
                        pendingTravelOrderTypeList.addAll(orderTypeList);
                    }

                    myOrderListAdapter.setMyOrderList(pendingTravelOrderTypeList);
                    myOrderListAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getAwaitRefundOrderListResult(BackResult<UserOrderListResponse> res) {

    }

    @Override
    public void deleteOrderResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    pendingTravelOrderTypeList.remove(mOrderTypeEntity);
                    if (pendingTravelOrderTypeList.size() > 0) {
                        mRlytNoOrderData.setVisibility(View.GONE);

                    } else {

                        mRlytNoOrderData.setVisibility(View.VISIBLE);
                    }
                    myOrderListAdapter.setMyOrderList(pendingTravelOrderTypeList);
                    myOrderListAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getPendingOrdersListResult(BackResult<UserOrderListResponse> res) {

    }

    @Override
    public void cancelOrderResult(BackResult res) {

    }

    /**
     * 获取订单列表
     */
    public void getPendingCommentOrderList() {

        if (validateInternet()) {

            mPresenter.getAwaitCommentOrderList(mPage, mPageSize);
        }
    }

    /**
     * 删除订单
     */
    public void orderDelete() {

        if (validateInternet()) {
            showProgressDialog(getActivity());
            mDialog.setTitle(getResources().getString(R.string.str_order_deleting));
            String orderNo = mOrderTypeEntity.getOrderNo();
            OrderDeleteRequest deleteOrderRequest = new OrderDeleteRequest();
            deleteOrderRequest.setOrderNo(orderNo);
            mPresenter.deleteOrder(deleteOrderRequest);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visibleToUser = isVisibleToUser;
    }

    @Subscribe
    public void onEvent(String commentOprate) {
        if (visibleToUser) {
            getPendingCommentOrderList();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
