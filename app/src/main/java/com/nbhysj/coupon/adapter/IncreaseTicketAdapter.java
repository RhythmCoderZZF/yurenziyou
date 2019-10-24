package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.TicketEntranceWayEnum;
import com.nbhysj.coupon.common.Enum.TicketRefundSettingsEnum;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.ui.OrderSubmitActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/08/23.
 * description : 增加门票适配器
 */
public class IncreaseTicketAdapter extends RecyclerView.Adapter<IncreaseTicketAdapter.ViewHolder> {

    List<OrderSubmitInitResponse.GoodsPriceEntity> goodsPriceList;
    private Context mContext;
    private List<String> goodsPriceTagList = null;

    private IncreaseTicketListener increaseTicketListener;
    //购买数量
    private int mPurchaseNum = 0;
    //购买票价格
    private double mPurchasePrice = 0;

    public IncreaseTicketAdapter(Context mContext, IncreaseTicketListener increaseTicketListener) {

        this.mContext = mContext;
        this.increaseTicketListener = increaseTicketListener;

        if (goodsPriceTagList == null) {
            goodsPriceTagList = new ArrayList<>();
        } else {

            goodsPriceTagList.clear();
        }
    }

    public void setIncreaseTicketList(List<OrderSubmitInitResponse.GoodsPriceEntity> goodsPriceList) {

        this.goodsPriceList = goodsPriceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_increase_ticket_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            String refundSettingsValue = null;
            String ticketIntoTypeValue = null;
            OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(itemPosition);
            String photoUrl = goodsPriceEntity.getPhoto();
            double mDefaultPrice = goodsPriceEntity.getDefaultPrice();
            double mMarketPrice = goodsPriceEntity.getOtherMarketPrice();
            mPurchaseNum = goodsPriceEntity.getTicketPurchaseNum();

            String refundSettings = goodsPriceEntity.getRefundSettings();

            if (!TextUtils.isEmpty(refundSettings)) {
                refundSettingsValue = TicketRefundSettingsEnum.getEnumValueByKey(refundSettings);
            }

            //入园方式:无需换票，直接验证入园TICKET_CHANGE_NO换票入园TICKET_CHANGE
            String ticketIntoType = goodsPriceEntity.getTicketIntoType();
            if (!TextUtils.isEmpty(ticketIntoType)) {
                ticketIntoTypeValue = TicketEntranceWayEnum.getEnumValueByKey(ticketIntoType);
            }

            String title = goodsPriceEntity.getTitle();

            if (itemPosition == 1) {
                holder.mRlytIncreaseTicketItem.setBackgroundResource(R.drawable.bg_order_date_select_bottom_shape);

            } else {

                holder.mRlytIncreaseTicketItem.setBackgroundResource(R.color.white);
            }

            GlideUtil.loadImage(mContext, photoUrl,holder.mImgScenicSpots);
            holder.mTvTicketTitle.setText(title);

            holder.mTvDefaultPrice.setText(String.valueOf(mDefaultPrice));

            holder.mTvMarketPrice.setText("¥" + String.valueOf(mMarketPrice));
            holder.mTvMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            goodsPriceTagList.clear();
            goodsPriceTagList.add("官方");
            goodsPriceTagList.add(refundSettingsValue);
            goodsPriceTagList.add(ticketIntoTypeValue);

            if (goodsPriceTagList != null) {
                TagAdapter tagAdapter = new TagAdapter<String>(goodsPriceTagList) {
                    @Override
                    public View getView(FlowLayout parent, int position, String option) {
                        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_increase_ticket,
                                holder.mTagFlowLayout, false);
                        TextView mTvFlowlayout = view.findViewById(R.id.tv_flowlayout);
                        mTvFlowlayout.setText(option);
                        if (position == 0) {
                            view.setBackgroundResource(R.drawable.bg_stroke_radius_eight_light_orange_shape);

                            mTvFlowlayout.setTextColor(mContext.getResources().getColor(R.color.color_orange6));
                        } else {
                            view.setBackgroundResource(R.drawable.bg_stroke_radius_eight_light_gray_shape);
                            mTvFlowlayout.setTextColor(mContext.getResources().getColor(R.color.color_text_gray24));
                        }
                        view.getBackground().setAlpha(30);

                        return view;
                    }
                };

                holder.mTagFlowLayout.setAdapter(tagAdapter);
            }

            holder.mImgPurchaseNumReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mPurchaseNum > 0) {
                        mPurchaseNum = goodsPriceEntity.getTicketPurchaseNum();
                        mPurchaseNum--;
                        goodsPriceEntity.setTicketPurchaseNum(mPurchaseNum);
                        holder.mTvPurchaseNum.setText(String.valueOf(mPurchaseNum));
                        mPurchasePrice = mPurchaseNum * mDefaultPrice;
                        increaseTicketListener.setPurchaseNumListener(itemPosition,mPurchasePrice,mPurchaseNum);
                    }
                }
            });

            holder.mImgPurchaseNumAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPurchaseNum = goodsPriceEntity.getTicketPurchaseNum();
                    mPurchaseNum++;
                    goodsPriceEntity.setTicketPurchaseNum(mPurchaseNum);
                    holder.mTvPurchaseNum.setText(String.valueOf(mPurchaseNum));
                    mPurchasePrice = mPurchaseNum * mDefaultPrice;
                    increaseTicketListener.setPurchaseNumListener(itemPosition,mPurchasePrice,mPurchaseNum);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return goodsPriceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //景区门票现有价格
        @BindView(R.id.tv_default_price)
        TextView mTvDefaultPrice;
        //票名
        @BindView(R.id.tv_ticket_title)
        TextView mTvTicketTitle;
        //景区照片
        @BindView(R.id.image_scenic_spots)
        RoundedImageView mImgScenicSpots;
        //市场价格
        @BindView(R.id.tv_market_price)
        TextView mTvMarketPrice;
        //票标签
        @BindView(R.id.tag_flowlayout_ticket)
        TagFlowLayout mTagFlowLayout;
        //增加门票
        @BindView(R.id.rlyt_increase_ticket_item)
        RelativeLayout mRlytIncreaseTicketItem;
        //购买减少数量
        @BindView(R.id.img_purchase_num_reduce)
        ImageView mImgPurchaseNumReduce;
        //购买减少数量
        @BindView(R.id.img_purchase_num_add)
        ImageView mImgPurchaseNumAdd;

        //购买数量
        @BindView(R.id.tv_purchase_num)
        TextView mTvPurchaseNum;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface IncreaseTicketListener {

        void setPurchaseNumListener(int position,double price,int mPurchaseNum);

    }
}
