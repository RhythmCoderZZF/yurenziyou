package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.OrderRefundProgressBean;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/10/10.
 * description : 订单退款进程配器
 */
public class OrderRefundProgressDetailAdapter extends RecyclerView.Adapter<OrderRefundProgressDetailAdapter.ViewHolder> {

    List<OrderRefundProgressBean> orderRefundProgressList;
    private Context mContext;

    public OrderRefundProgressDetailAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setOrderRefundProgressList(List<OrderRefundProgressBean> orderRefundProgressList) {

        this.orderRefundProgressList = orderRefundProgressList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_refund_detail_progress_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            OrderRefundProgressBean orderRefundProgressBean = orderRefundProgressList.get(itemPosition);
            String refundProgressDesc = orderRefundProgressBean.getRefundProgressDesc();
            String refundProgressTime = orderRefundProgressBean.getRefundProgressTime();

            holder.mTvRefundProgress.setText(refundProgressDesc);
            holder.mTvRefundProgressNodeTime.setText(refundProgressTime);

            if(itemPosition == orderRefundProgressList.size() - 1){

                holder.mViewLine.setVisibility(View.GONE);
                holder.mImgShapeRoundGradient.setBackgroundResource(R.mipmap.icon_payment_method_check_true);
            } else {
                holder.mViewLine.setVisibility(View.VISIBLE);
                holder.mImgShapeRoundGradient.setBackgroundResource(R.mipmap.icon_shape_round_refund_node);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return orderRefundProgressList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //退款进程
        @BindView(R.id.tv_refund_progress)
        TextView mTvRefundProgress;

        //退款进程节点时间
        @BindView(R.id.tv_refund_progress_node_time)
        TextView mTvRefundProgressNodeTime;

        @BindView(R.id.view_line)
        View mViewLine;

        @BindView(R.id.img_shape_round_gradient)
        ImageView mImgShapeRoundGradient;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
