package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.TicketBookStatusEnum;
import com.nbhysj.coupon.model.response.GoodsPriceDatesResponse;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.StarBarView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.List;

/**
 * @author hysj created at 2019/4/27.
 * description:订单使用日期选择适配器
 */
public class OrderUseDateSelectAdapter extends RecyclerView.Adapter<OrderUseDateSelectAdapter.ViewHolder> {

    List<GoodsPriceDatesResponse> goodsPriceDatesList;
    private Context mContext;
    private int mSelectPosition = 1;
    private OrderUseDateSelectListener orderUseDateSelectListener;
    GoodsPriceDatesResponse goodsPriceDatesResponse;

    public OrderUseDateSelectAdapter(Context mContext, OrderUseDateSelectListener orderUseDateSelectListener) {

        this.mContext = mContext;
        this.orderUseDateSelectListener = orderUseDateSelectListener;
    }

    public void setOrderUseDateSelectList(List<GoodsPriceDatesResponse> goodsPriceDatesList) {

        this.goodsPriceDatesList = goodsPriceDatesList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_use_date_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            if (itemPosition == 3) {  //更多日期选择
                //取控件当前的布局参数
                GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.mRlytOrderUseDate.getLayoutParams();
                //设置宽度值
                params.width = DensityUtil.dip2px(55);
                //设置高度值
                params.height = DensityUtil.dip2px(57);
                //使设置好的布局参数应用到控件
                holder.mRlytOrderUseDate.setLayoutParams(params);

                holder.mTvOrderUseDate.setText(mContext.getResources().getString(R.string.str_more));
                holder.mTvPrice.setText(mContext.getResources().getString(R.string.str_calendar));

                holder.mTvOrderUseDate.setTextColor(mContext.getResources().getColor(R.color.color_text_black7));
                holder.mTvPrice.setTextColor(mContext.getResources().getColor(R.color.color_text_black7));

            } else {

                goodsPriceDatesResponse = goodsPriceDatesList.get(itemPosition);
                //日期
                String date = goodsPriceDatesResponse.getDate();
                //是否可以预订
                int isCanBooking = goodsPriceDatesResponse.getIsCanBooking();
                //价格
                double price = goodsPriceDatesResponse.getPrice();
                //判断是否是今天
                boolean isCurrentDate = DateUtil.isCurrentDate(date, DateUtil.sDateYMDFormat);
                String mmdd = DateUtil.toMMDD(date);
                if (itemPosition == 0) {

                    if (isCurrentDate)
                    {
                        holder.mTvOrderUseDate.setText("今天" + mmdd);

                    } else {

                        holder.mTvOrderUseDate.setText(date);
                    }

                } else if (itemPosition == 1) {
                    holder.mTvOrderUseDate.setText("明天" + mmdd);
                    holder.mTvPrice.setText("¥" + Tools.getTwoDecimalPoint(price));
                    holder.mTvPrice.setTextColor(mContext.getResources().getColor(R.color.color_orange3));
                    holder.mTvOrderUseDate.setTextColor(mContext.getResources().getColor(R.color.color_text_black7));
                    holder.mRlytOrderUseDate.setBackgroundResource(R.mipmap.icon_the_date_of_use_ticket_unselect);
                }
                if (itemPosition == 2) {

                    String whichDay = DateUtil.dateToWeek(date);

                    holder.mTvOrderUseDate.setText(whichDay + mmdd);
                    holder.mTvPrice.setText("¥" + Tools.getTwoDecimalPoint(price));
                    holder.mTvPrice.setTextColor(mContext.getResources().getColor(R.color.color_orange3));
                    holder.mTvOrderUseDate.setTextColor(mContext.getResources().getColor(R.color.color_text_black7));

                }

                if (isCanBooking == 1) {
                    holder.mTvPrice.setText("¥" + Tools.getTwoDecimalPoint(price));
                    holder.mTvPrice.setTextColor(mContext.getResources().getColor(R.color.color_orange3));
                    holder.mTvOrderUseDate.setTextColor(mContext.getResources().getColor(R.color.color_text_black7));
                    holder.mRlytOrderUseDate.setEnabled(true);
                } else {
                    holder.mTvPrice.setTextColor(mContext.getResources().getColor(R.color.color_text_gray35));
                    holder.mTvOrderUseDate.setTextColor(mContext.getResources().getColor(R.color.color_text_gray35));
                    holder.mRlytOrderUseDate.setEnabled(false);
                    holder.mTvPrice.setText(mContext.getResources().getString(R.string.str_un_bookable));
                }
            }

            holder.mRlytOrderUseDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (itemPosition == 3) {

                        orderUseDateSelectListener.moreDatesCallBack();
                    } else {

                        int isCanBooking = goodsPriceDatesList.get(itemPosition).getIsCanBooking();
                        if (isCanBooking == TicketBookStatusEnum.BOOKABLE.getKey())  //0:不可预订 1:可预订
                        {

                            mSelectPosition = itemPosition;
                         //   orderUseDateSelectListener.datePriceSelectCallBack(mSelectPosition);
                            for (int i = 0; i < goodsPriceDatesList.size(); i++) {
                                goodsPriceDatesList.get(i).setSelectDatePrice(false);
                            }
                            goodsPriceDatesList.get(itemPosition).setSelectDatePrice(true);
                            notifyDataSetChanged();

                        }
                    }
                }
            });
            boolean isSelectDatePrice = goodsPriceDatesResponse.isSelectDatePrice();

            if (isSelectDatePrice) {
                if (itemPosition != 3) {

                    holder.mRlytOrderUseDate.setBackgroundResource(R.mipmap.icon_the_date_of_use_ticket_select);

                    for (int i = 0; i < goodsPriceDatesList.size(); i++) {
                        goodsPriceDatesList.get(i).setSelectDatePrice(false);
                    }

                    goodsPriceDatesList.get(itemPosition).setSelectDatePrice(true);
                    orderUseDateSelectListener.datePriceSelectCallBack(itemPosition);
                }
                // notifyDataSetChanged();
            } else {

                holder.mRlytOrderUseDate.setBackgroundResource(R.mipmap.icon_the_date_of_use_ticket_unselect);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        int goodsPriceDatesSize = goodsPriceDatesList.size();
        if(goodsPriceDatesSize > 3)
        {
            return 4;
        } else {

            return goodsPriceDatesList.size() + 1;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout mRlytOrderUseDate;
        //订单使用日期
        TextView mTvOrderUseDate;

        //价格
        private TextView mTvPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            mRlytOrderUseDate = itemView.findViewById(R.id.rlyt_the_date_of_ticket_select_item);
            mTvOrderUseDate = itemView.findViewById(R.id.tv_order_use_date);
            mTvPrice = itemView.findViewById(R.id.tv_price);
        }
    }

    public interface OrderUseDateSelectListener {

        void moreDatesCallBack();

        void datePriceSelectCallBack(int position);
    }
}
