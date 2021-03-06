package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.RecipientsBean;
import com.nbhysj.coupon.model.response.TravellerBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by hysj on 2019/03/15.
 * description: 收件人列表适配器
 */

public class RecipientListAdapter extends RecyclerView.Adapter<RecipientListAdapter.ViewHolder> {

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    private Context mContext;
    private List<RecipientsBean> recipientsList;
    public RecipientInfoItemListener recipientInfoItemListener;

    public RecipientListAdapter(Context mContext, RecipientInfoItemListener recipientInfoItemListener) {
        this.mContext = mContext;
        this.recipientInfoItemListener = recipientInfoItemListener;
    }

    public void setRecipientInfoList(List<RecipientsBean> recipientsList) {

        this.recipientsList = recipientsList;
    }

    @Override
    public RecipientListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_recipient_info_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipientListAdapter.ViewHolder holder, int position) {

        try {

            RecipientsBean recipientInfo = recipientsList.get(position);
            String consignee = recipientInfo.getConsignee();
            String address = recipientInfo.getAddress();
            if(!TextUtils.isEmpty(consignee))
            {
                holder.mTvConsigneeName.setText(consignee);
            }
            holder.mTvConsigneeAddress.setText(address);

            holder.mLlytRecipientInfoItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    recipientInfoItemListener.setRecipientInfoItemListener(recipientInfo);
                }
            });

            holder.mLlytRecipientInfoItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    recipientInfoItemListener.setRecipientInfoDeleteListener(recipientInfo.getId());
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        //注意，空布局---> mEmptyType = 1 显示1个布局
        //            ---> mEmptyType = 0 所有布局都不显示的
        //   return dataList != null ? dataList.size() + mEmptyType : mEmptyType;
        return recipientsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mEmptyType == EMPTY_VIEW) {
            //空布局的类型
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //收件人名字
        @BindView(R.id.tv_consignee_name)
        TextView mTvConsigneeName;
        //收件人地址
        @BindView(R.id.tv_consignee_address)
        TextView mTvConsigneeAddress;
        //收件人
        @BindView(R.id.llyt_recipient_info_item)
        LinearLayout mLlytRecipientInfoItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface RecipientInfoItemListener {

        void setRecipientInfoItemListener(RecipientsBean recipientsBean);

        void setRecipientInfoDeleteListener(int recipientId);
    }
}
