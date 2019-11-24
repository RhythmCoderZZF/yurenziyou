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
import com.nbhysj.coupon.model.request.OrderCancelRequest;
import com.nbhysj.coupon.model.request.OrderDeleteRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.UserOrderListResponse;
import com.nbhysj.coupon.presenter.OrderListPresenter;
import com.nbhysj.coupon.ui.AllRefundApplyActivity;
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

import static android.app.Activity.RESULT_OK;

/**
 * created by hysj on 2019/10/29.
 * description: 我的订单
 */
public class MyAllOrderListFragment extends BaseFragment<OrderListPresenter, OrderListModel> implements OrderListContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //我的订单列表
    @BindView(R.id.rv_my_order)
    RecyclerView mRvMyOrderList;
    //暂无订单数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoOrderData;
    private List<UserOrderListResponse.OrderTypeEntity> orderAllList;
    private int mPage = 1;
    private int mPageSize = 10;
    int mTotalPageCount;
    private boolean isOnLoadMore = false;
    private MyOrderListAdapter myOrderListAdapter;
    UserOrderListResponse.OrderTypeEntity mOrderTypeEntity;
    //订单删除弹框
    OprateDialog orderDeleteOprateDialog;
    //订单取消弹框
    OprateDialog orderCancelOprateDialog;

    private final static int ORDER_ALL_REFUND_REQUEST_CODE = 0;

    private boolean visibleToUser;
    public static MyAllOrderListFragment newInstance(String content) {
        MyAllOrderListFragment fragment = new MyAllOrderListFragment();

        fragment.mContent = content;

        return fragment;
    }

    private String mContent = "fragmentpage";

   /* @Override
    public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
                mContent = savedInstanceState.getString(KEY_CONTENT);
          }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
         super.onSaveInstanceState(outState);
         outState.putString(KEY_CONTENT, mContent);
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
    public void initView(View v) {

        if (orderAllList == null) {

            orderAllList = new ArrayList<>();
        } else {

            orderAllList.clear();
        }

        // 创建一个线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvMyOrderList.setLayoutManager(linearLayoutManager);

        myOrderListAdapter = new MyOrderListAdapter(getActivity(), new MyOrderListAdapter.MyOrderListener() {
            @Override
            public void setOrderDeleteListener(int position,UserOrderListResponse.OrderTypeEntity orderTypeEntity) {

                mOrderTypeEntity = orderTypeEntity;
                if(orderDeleteOprateDialog == null)
                {
                    orderDeleteOprateDialog = new OprateDialog(getActivity()).builder().setTitle(getResources().getString(R.string.str_sure_to_delete_the_order));
                    orderDeleteOprateDialog.setNegativeButton(getResources().getString(R.string.str_cancel), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                    orderDeleteOprateDialog.setPositiveButton(getResources().getString(R.string.str_confirm), getResources().getColor(R.color.color_blue2), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            orderDelete();
                        }
                    });
                }
                orderDeleteOprateDialog.show();
            }


            @Override
            public void setGroupOrderCommentListener(String orderNo) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), OrderGroupEvaluateActivity.class);
                intent.putExtra("orderNo", orderNo);
                getActivity().startActivity(intent);
            }

            @Override
            public void setPartialOrderCommentListener(int orderGoodsId) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), OrderEvaluateActivity.class);
                intent.putExtra("orderGoodsId", orderGoodsId);
                startActivityForResult(intent,0);
            }

            @Override
            public void setOrderCancelListener(UserOrderListResponse.OrderTypeEntity orderTypeEntity) {
                mOrderTypeEntity = orderTypeEntity;
                if(orderCancelOprateDialog == null)
                {
                    orderCancelOprateDialog = new OprateDialog(getActivity()).builder().setTitle(getResources().getString(R.string.str_sure_to_cancel_the_order));
                    orderCancelOprateDialog.setNegativeButton(getResources().getString(R.string.str_cancel), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                    orderCancelOprateDialog.setPositiveButton(getResources().getString(R.string.str_confirm), getResources().getColor(R.color.color_blue2), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            orderCancel();
                        }
                    });


                }
                orderCancelOprateDialog.show();
            }

            @Override
            public void setOrderAllRefundListener(String orderNo) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), AllRefundApplyActivity.class);
                intent.putExtra("orderNo",orderNo);
                startActivityForResult(intent,ORDER_ALL_REFUND_REQUEST_CODE);
            }
        });
        myOrderListAdapter.setMyOrderList(orderAllList);
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
                        isOnLoadMore = false;
                        mPage = 1;
                        orderAllList.clear();
                        myOrderListAdapter.notifyDataSetChanged();
                        showProgressDialog(getActivity());
                        getOrderList();

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
                            if (mTotalPageCount == orderAllList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                getOrderList();
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

        if (orderAllList != null)
        {
            orderAllList.clear();
        }
        showProgressDialog(getActivity());
        getOrderList();
    }

    @Override
    public void getUserOrderListResult(BackResult<UserOrderListResponse> res) {
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

                        orderAllList.clear();
                        myOrderListAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }
                    UserOrderListResponse orderListResponse = res.getData();
                    BasePaginationResult paginationResult = orderListResponse.getPage();
                    mTotalPageCount = paginationResult.getPageCount();

                    if (mTotalPageCount == 0)
                    {
                        mRlytNoOrderData.setVisibility(View.VISIBLE);
                    } else {
                        mRlytNoOrderData.setVisibility(View.GONE);
                    }

                    List<UserOrderListResponse.OrderTypeEntity> orderTypeList = orderListResponse.getResult();

                    if (orderTypeList != null)
                    {
                        orderAllList.addAll(orderTypeList);
                    }

                    myOrderListAdapter.setMyOrderList(orderAllList);
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
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    orderAllList.remove(mOrderTypeEntity);
                    if (orderAllList.size() > 0) {
                        mRlytNoOrderData.setVisibility(View.GONE);

                    } else {

                        mRlytNoOrderData.setVisibility(View.VISIBLE);
                    }
                    myOrderListAdapter.setMyOrderList(orderAllList);
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
    public void deleteOrderResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                        orderAllList.remove(mOrderTypeEntity);
                    if (orderAllList.size() > 0) {
                        mRlytNoOrderData.setVisibility(View.GONE);

                    } else {

                        mRlytNoOrderData.setVisibility(View.VISIBLE);
                    }
                    myOrderListAdapter.setMyOrderList(orderAllList);
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
    public void getAwaitGoingOrderListResult(BackResult<UserOrderListResponse> res) {

    }

    @Override
    public void getAwaitCommentOrderListResult(BackResult<UserOrderListResponse> res) {

    }

    @Override
    public void getAwaitRefundOrderListResult(BackResult<UserOrderListResponse> res) {

    }

    /**
     * 获取订单列表
     */
    public void getOrderList() {

        if (validateInternet()) {
            mPresenter.getUserOrderList(mPage, mPageSize);
        }

    }

    /**
     * 删除订单
     */
    public void orderDelete() {

        if (validateInternet()) {
            mDialog.setTitle(getResources().getString(R.string.str_order_deleting));
            String orderNo = mOrderTypeEntity.getOrderNo();
            OrderDeleteRequest deleteOrderRequest = new OrderDeleteRequest();
            deleteOrderRequest.setOrderNo(orderNo);
            mPresenter.deleteOrder(deleteOrderRequest);
        }
    }


    /**
     * 取消订单
     */
    public void orderCancel() {
        if (validateInternet()) {
            mDialog.setTitle(getResources().getString(R.string.str_order_canceling));
            String orderNo = mOrderTypeEntity.getOrderNo();
            OrderCancelRequest cancelOrderRequest = new OrderCancelRequest();
            cancelOrderRequest.setOrderNo(orderNo);
            mPresenter.cancelOrder(cancelOrderRequest);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            switch (requestCode) {
                case ORDER_ALL_REFUND_REQUEST_CODE:

                    isOnLoadMore = false;
                    mPage = 1;
                    orderAllList.clear();
                    if(myOrderListAdapter != null)
                    {
                        myOrderListAdapter.notifyDataSetChanged();
                    }
                    showProgressDialog(getActivity());
                    getOrderList();

                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visibleToUser = isVisibleToUser;
    }



    @Subscribe
    public void onEvent(String commentOprate) {

            if(visibleToUser) {
                getOrderList();
            }
    }
}
