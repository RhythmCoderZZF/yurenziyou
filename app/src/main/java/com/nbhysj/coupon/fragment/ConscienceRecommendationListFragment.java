package com.nbhysj.coupon.fragment;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ConscienceRecommendationListAdapter;
import com.nbhysj.coupon.adapter.MyOrderListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.OrderListContract;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.model.OrderListModel;
import com.nbhysj.coupon.model.request.OrderCancelRequest;
import com.nbhysj.coupon.model.request.OrderDeleteRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.ConscienceRecommendationBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.UserOrderListResponse;
import com.nbhysj.coupon.presenter.OrderListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * created by hysj on 2018/03/29.
 * description: 良心推荐
 */
public class ConscienceRecommendationListFragment extends BaseFragment{
    //我的订单列表
    @BindView(R.id.rv_conscience_recommendation)
    RecyclerView mRvConscienceRecommendation;
    //暂无订单数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoOrderData;
    int mTotalPageCount;
    private boolean isOnLoadMore = false;
    private MyOrderListAdapter myOrderListAdapter;
    List<ConscienceRecommendationBean> conscienceRecommendationList;

    public static ConscienceRecommendationListFragment newInstance(String content) {
        ConscienceRecommendationListFragment fragment = new ConscienceRecommendationListFragment();

        fragment.mContent = content;

        return fragment;
    }

    private String mContent = "fragmentpage";

    public void setConscienceRecommendationList(List<ConscienceRecommendationBean> conscienceRecommendationList) {

        this.conscienceRecommendationList = conscienceRecommendationList;

         /*RxBus.$().(this, "my tag", new RxBus.Callback<String>() {
             @Override
             public void onEvent(String s) {
                 Log.e("eventTag", s);
             }
         });*/

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_conscience_recommendation_list;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(View v) {

        if(conscienceRecommendationList != null)
        {
            mRlytNoOrderData.setVisibility(View.GONE);
            // 创建一个线性布局管理器
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRvConscienceRecommendation.setLayoutManager(linearLayoutManager);

            ConscienceRecommendationListAdapter conscienceRecommendationListAdapter = new ConscienceRecommendationListAdapter(getActivity());
            conscienceRecommendationListAdapter.setConscienceRecommendationList(conscienceRecommendationList);
            mRvConscienceRecommendation.setAdapter(conscienceRecommendationListAdapter);
        } else {

            mRlytNoOrderData.setVisibility(View.VISIBLE);
        }
      /*  myOrderListAdapter = new MyOrderListAdapter(getActivity(), new MyOrderListAdapter.MyOrderListener() {
            @Override
            public void setMyOrderListener(UserOrderListResponse.OrderTypeEntity orderTypeEntity) {

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
            public void setOrderPendingPaymentListener(UserOrderListResponse.OrderTypeEntity orderTypeEntity) {  //待付款

            }
        });
        myOrderListAdapter.setMyOrderList(orderAllList);
        mRvMyOrderList.setAdapter(myOrderListAdapter);*/
    }

    @Override
    public void initData() {

    }

    @Override
    public void lazyInitView(View view) {
    //    showProgressDialog(getActivity());
      //  getOrderList();
    }
}
