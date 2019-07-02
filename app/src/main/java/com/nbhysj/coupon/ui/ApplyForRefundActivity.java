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
 * @auther：hysj created on 2019/05/24
 * description：申请退款
 */
public class ApplyForRefundActivity extends BaseActivity {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.rv_refund_reason_select)
    RecyclerView mRvRefundReasonSelect;
    @BindView(R.id.tv_refund_num)
    TextView mTvRefundNum;
    private List<String> mRefundReasonSelectList;
    private int refundNum = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_apply_for_refund;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeadBar(ApplyForRefundActivity.this, getResources().getString(R.string.str_apply_for_refund), R.mipmap.icon_left_arrow_black, "");

        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ApplyForRefundActivity.this);
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
