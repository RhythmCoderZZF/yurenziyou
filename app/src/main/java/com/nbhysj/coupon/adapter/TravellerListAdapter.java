package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MessageBean;
import com.nbhysj.coupon.model.response.TravellerBean;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by hysj on 2019/03/15.
 * description: 旅客列表适配器
 */

public class TravellerListAdapter extends RecyclerView.Adapter<TravellerListAdapter.ViewHolder> {

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    private Context mContext;
    private List<TravellerBean> travellerList;
    private TravellerInfoItemListener travellerInfoItemListener;

    public TravellerListAdapter(Context mContext, TravellerInfoItemListener travellerInfoItemListener) {
        this.mContext = mContext;
        this.travellerInfoItemListener = travellerInfoItemListener;
    }

    public void setTravellerInfoList(List<TravellerBean> travellerList) {

        this.travellerList = travellerList;
    }

    @Override
    public TravellerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_traveller_info_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TravellerListAdapter.ViewHolder holder, int position) {

        try {

            TravellerBean travellerInfo = travellerList.get(position);
            holder.mTvTravellerName.setText(travellerInfo.getRealname());
            holder.mTvCertificateType.setText(travellerInfo.getIdentityType());
            holder.mTvIdentificationCard.setText(travellerInfo.getIdentityNo());

            holder.mLlytTravellerInfoItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    travellerInfoItemListener.setTravellerInfoItemListener(travellerInfo);
                }
            });

            holder.mLlytTravellerInfoItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    travellerInfoItemListener.setTravellerInfoDeleteListener(travellerInfo.getId());
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
        return travellerList.size();
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
        //旅客名
        @BindView(R.id.tv_traveller_name)
        TextView mTvTravellerName;
        //证件类型
        @BindView(R.id.tv_certificate_type)
        TextView mTvCertificateType;
        //证件号
        @BindView(R.id.tv_identification_card)
        TextView mTvIdentificationCard;
        @BindView(R.id.llyt_traveller_info_item)
        LinearLayout mLlytTravellerInfoItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface TravellerInfoItemListener {

        void setTravellerInfoItemListener(TravellerBean travellerInfo);

        void setTravellerInfoDeleteListener(int travellerId);
    }
}
