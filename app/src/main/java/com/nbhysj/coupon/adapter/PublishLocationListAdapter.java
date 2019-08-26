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
import com.nbhysj.coupon.model.response.MerchantListResponse;
import com.nbhysj.coupon.model.response.MessageBean;
import com.nbhysj.coupon.model.response.PublishRecommendLocation;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by hysj on 2019/03/18.
 * description: 商户位置列表适配器
 */

public class PublishLocationListAdapter extends RecyclerView.Adapter<PublishLocationListAdapter.ViewHolder> {

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    private Context mContext;
    private List<MerchantListResponse.MerchantEntity> publishRecommendLocationList;

    private MerchantSelectListener merchantSelectListener;

    public PublishLocationListAdapter(Context mContext, MerchantSelectListener merchantSelectListener) {
        this.mContext = mContext;
        this.merchantSelectListener = merchantSelectListener;
    }

    public void setPublishRecommendLocationList(List<MerchantListResponse.MerchantEntity> publishRecommendLocationList) {

        this.publishRecommendLocationList = publishRecommendLocationList;
    }

    @Override
    public PublishLocationListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_publish_location_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PublishLocationListAdapter.ViewHolder holder, int position) {

        try {
            MerchantListResponse.MerchantEntity merchantEntity = publishRecommendLocationList.get(position);
            holder.mTvLocationTitle.setText(merchantEntity.getMchName());
           // holder.mTvSpecificLocation.setText(merchantEntity.getAddress());
            holder.mLlytMerchantItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    merchantSelectListener.setMerchantSelectListener(merchantEntity);

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
        return publishRecommendLocationList.size();
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
        @BindView(R.id.image_message_type)
        ImageView mImgMessageType;
        @BindView(R.id.tv_location_title)
        TextView mTvLocationTitle;
        @BindView(R.id.tv_specific_location)
        TextView mTvSpecificLocation;
        @BindView(R.id.llyt_merchant_item)
        LinearLayout mLlytMerchantItem;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface MerchantSelectListener {

        void setMerchantSelectListener(MerchantListResponse.MerchantEntity merchant);
    }
}
