package com.nbhysj.coupon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;

import java.util.List;

/**
 * @author hysj created at 2018/05/24.
 * description : 退款原因适配器
 */
public class RefundReasonSelectAdapter extends RecyclerView.Adapter<RefundReasonSelectAdapter.ViewHolder> {

    List<String> mRefundReasonList;

    private int mPosition;

    private RefundReasonSelectListener refundReasonSelectListener;

    private boolean isInitRefundReason = true;
    public RefundReasonSelectAdapter(RefundReasonSelectListener refundReasonSelectListener) {
        this.refundReasonSelectListener = refundReasonSelectListener;
    }

    public void setRefundReasonList(List<String> mRefundReasonList) {

        this.mRefundReasonList = mRefundReasonList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_refund_reason_select_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            String refundReason = mRefundReasonList.get(itemPosition);
            holder.mTvRefundReason.setText(refundReason);

            holder.mRlytRefundReasonSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mPosition = itemPosition;
                 //
                    refundReasonSelectListener.setRefundReasonSelectListener(refundReason);


                    notifyDataSetChanged();
                    isInitRefundReason = false;
                }
            });

            if (itemPosition == mPosition && !isInitRefundReason) {

                holder.mImgRefundReasonSelect.setBackgroundResource(R.mipmap.icon_refund_reason_select);

            } else {

                holder.mImgRefundReasonSelect.setBackgroundResource(R.mipmap.icon_refund_reason_unselect);

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mRefundReasonList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout mRlytRefundReasonSelect;

        //退款原因
        public TextView mTvRefundReason;

        public ImageView mImgRefundReasonSelect;

        public ViewHolder(View itemView) {
            super(itemView);

            mRlytRefundReasonSelect = itemView.findViewById(R.id.rlyt_refund_reason_select_item);
            mTvRefundReason = itemView.findViewById(R.id.tv_refund_reason);
            mImgRefundReasonSelect = itemView.findViewById(R.id.img_refund_reason_select);

        }
    }

    public interface RefundReasonSelectListener{

        void setRefundReasonSelectListener(String reason);
    }
}
