package com.nbhysj.coupon.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.RefundReasonSelectAdapter;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/09/26
 * description：申请退款
 */
public class PartialApplyForRefundActivity extends BaseActivity {
    @BindView(R.id.rv_refund_reason_select)
    RecyclerView mRvRefundReasonSelect;
    @BindView(R.id.tv_refund_num)
    TextView mTvRefundNum;
    private List<String> mRefundReasonSelectList;
    private int refundNum = 1;
    private int orderGoodsId;
    private String goodsType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_apply_for_refund;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeadBar(PartialApplyForRefundActivity.this, getResources().getString(R.string.str_apply_for_refund), R.mipmap.icon_left_arrow_black, "");

        orderGoodsId = getIntent().getIntExtra("orderGoodsId",0);  //子订单id
        goodsType = getIntent().getStringExtra("goodsType");  //商品类型

        if (mRefundReasonSelectList == null) {

            mRefundReasonSelectList = new ArrayList<>();
        } else {

            mRefundReasonSelectList.clear();
        }

        mRefundReasonSelectList.add("个人计划有变");
        mRefundReasonSelectList.add("商户无法接待");
        mRefundReasonSelectList.add("其他网站购买");
        mRefundReasonSelectList.add("产品定错(时间,数量等)");
        mRefundReasonSelectList.add("价格不够优惠");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PartialApplyForRefundActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvRefundReasonSelect.setLayoutManager(linearLayoutManager);

        RefundReasonSelectAdapter refundReasonSelectAdapter = new RefundReasonSelectAdapter();
        refundReasonSelectAdapter.setRefundReasonList(mRefundReasonSelectList);
        mRvRefundReasonSelect.setAdapter(refundReasonSelectAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.ibtn_reduce_refund_num, R.id.ibtn_add_refund_num})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibtn_reduce_refund_num:

                if (refundNum > 0) {
                    refundNum--;
                    mTvRefundNum.setText(String.valueOf(refundNum));
                }
                break;
            case R.id.ibtn_add_refund_num:

                refundNum++;
                mTvRefundNum.setText(String.valueOf(refundNum));

                break;
            default:
                break;
        }

    }

}
