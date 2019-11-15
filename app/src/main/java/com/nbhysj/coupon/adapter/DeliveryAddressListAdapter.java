package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.DeliveryAddressTagBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by hysj on 2019/02/20.
 * description: 收件地址适配器
 */

public class DeliveryAddressListAdapter extends RecyclerView.Adapter<DeliveryAddressListAdapter.ViewHolder> {

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    private Context mContext;
    private List<DeliveryAddressTagBean> deliveryAddressTagList;

    public DeliveryAddressListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setDeliveryAddressTagList(List<DeliveryAddressTagBean> deliveryAddressTagList) {

        this.deliveryAddressTagList = deliveryAddressTagList;
    }

    @Override
    public DeliveryAddressListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_delivery_address_tag_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeliveryAddressListAdapter.ViewHolder holder, int position) {

        try {
            DeliveryAddressTagBean deliveryAddressTag = deliveryAddressTagList.get(position);
            holder.mTvDeliveryAddressTag.setText(deliveryAddressTag.getDeliveryAddressTag());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        //注意，空布局---> mEmptyType = 1 显示1个布局
        //            ---> mEmptyType = 0 所有布局都不显示的
        //   return dataList != null ? dataList.size() + mEmptyType : mEmptyType;
        return deliveryAddressTagList.size();
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

        //收件地址
        @BindView(R.id.tv_delivery_address_tag)
        TextView mTvDeliveryAddressTag;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
