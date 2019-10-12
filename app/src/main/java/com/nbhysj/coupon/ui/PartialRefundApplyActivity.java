package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.OrderRefundInitAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.OrderRefundContract;
import com.nbhysj.coupon.model.OrderRefundModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.OrderAllRefundInitResponse;
import com.nbhysj.coupon.model.response.OrderRefundDetailResponse;
import com.nbhysj.coupon.model.response.OrderRefundInitResponse;
import com.nbhysj.coupon.presenter.OrderRefundPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/09/27
 * description：部分退款申请
 */
public class PartialRefundApplyActivity extends BaseActivity<OrderRefundPresenter, OrderRefundModel> implements OrderRefundContract.View {

    //商品全部退款
    @BindView(R.id.rv_order_all_refund)
    RecyclerView mRvOrderAllRefund;


    private List<OrderRefundInitResponse> orderRefundIniteList;
    @Override
    public int getLayoutId() {

        return R.layout.activity_all_refund_apply;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        if(orderRefundIniteList == null){

            orderRefundIniteList = new ArrayList<>();
        }else {
            orderRefundIniteList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PartialRefundApplyActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvOrderAllRefund.setLayoutManager(linearLayoutManager);
        OrderRefundInitAdapter orderRefundInitAdapter = new OrderRefundInitAdapter(PartialRefundApplyActivity.this);
        orderRefundInitAdapter.setOrderAllRefundList(orderRefundIniteList);
        mRvOrderAllRefund.setAdapter(orderRefundInitAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void getOrderPartialRefundDataInitResult(BackResult<OrderRefundInitResponse> res) {

    }

    @Override
    public void orderPartialRefundSubmitResult(BackResult res) {

    }

    @Override
    public void getOrderAllRefundDataInitResult(BackResult<OrderAllRefundInitResponse> res) {

    }

    @Override
    public void orderAllRefundSubmitResult(BackResult res) {

    }

    @Override
    public void getOrderRefundDetailResult(BackResult<OrderRefundDetailResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(mContext, Constants.getResultMsg(msg));
    }
}
