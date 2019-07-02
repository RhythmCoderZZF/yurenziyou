package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.HotTagsTopicBean;

import java.util.List;

public class OrderDetailProblemAdapter extends RecyclerView.Adapter<OrderDetailProblemAdapter.CardHolder> {

    private List<String> orderDetailProblemList;
    private Context mContent;

    public OrderDetailProblemAdapter(Context mContent) {
        this.mContent = mContent;
    }

    public void setOrderDetailProblemList(List<String> orderDetailProblemList) {

        this.orderDetailProblemList = orderDetailProblemList;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_detail_problem_item, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {
        String orderDetailProblem = orderDetailProblemList.get(position);
        holder.mTvOrderDetailProblem.setText(orderDetailProblem);
    }

    @Override
    public int getItemCount() {
        return orderDetailProblemList.size();
    }

    static class CardHolder extends RecyclerView.ViewHolder {
        TextView mTvOrderDetailProblem;

        public CardHolder(View itemView) {
            super(itemView);
            mTvOrderDetailProblem = itemView.findViewById(R.id.tv_order_detail_problem);
        }
    }
}
