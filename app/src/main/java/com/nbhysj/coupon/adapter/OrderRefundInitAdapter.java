package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.GoodsTypeEnum;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.OrderRefundInitResponse;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;


/**
 * @author hysj created at 2019/05/17.
 * description: 全部退款商品适配器
 */
public class OrderRefundInitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<OrderRefundInitResponse> orderRefundInitList;
    private Context mContext;

    public OrderRefundInitAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setOrderAllRefundList(List<OrderRefundInitResponse> orderRefundInitList) {

        this.orderRefundInitList = orderRefundInitList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            if (viewType == GoodsTypeEnum.GOODS_TICKET.getKey() || viewType == GoodsTypeEnum.GOODS_HOTEL_ROOM.getKey() || viewType == GoodsTypeEnum.GOODS_RECREATION.getKey() || viewType == GoodsTypeEnum.ITEM_FOOD.getKey()) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_all_goods_refund_item, parent, false);//解决宽度不能铺满
                GoodsViewHolder hold = new GoodsViewHolder(view);
                return hold;
            }

            if (viewType == GoodsTypeEnum.GOODS_CAR.getKey()) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_vehicle_use_refund_item, parent, false);//解决宽度不能铺满
                GoodsVehicleViewHolder hold = new GoodsVehicleViewHolder(view);
                return hold;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_all_goods_refund_item, parent, false);//解决宽度不能铺满
        GoodsViewHolder hold = new GoodsViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int itemPosition) {

        try {

            OrderRefundInitResponse orderRefundInitEntity = orderRefundInitList.get(itemPosition);
            String photoUrl = orderRefundInitEntity.getPhoto();
            String title = orderRefundInitEntity.getTitle();
            double deductPrice = orderRefundInitEntity.getDeductPrice();
            double price = orderRefundInitEntity.getPrice();
            String deductNote = orderRefundInitEntity.getDeductNote();
            double totalPrice = orderRefundInitEntity.getTotalPrice();
            int validNum = orderRefundInitEntity.getValidNum();  //可用数量
            int usedNum = orderRefundInitEntity.getUsedNum(); //已使用数量

            if (holder instanceof GoodsViewHolder) {

                GlideUtil.loadImage(mContext, photoUrl, ((GoodsViewHolder) holder).mImgGoods);

                if(!TextUtils.isEmpty(title))
                {
                    ((GoodsViewHolder) holder).mTvGoodDeductNote.setText(title);
                }

                if(!TextUtils.isEmpty(deductNote))
                {
                    ((GoodsViewHolder) holder).mTvGoodDeductNote.setText(deductNote);
                }

                ((GoodsViewHolder) holder).mTvHasBeenUsedNum.setText(String.valueOf(usedNum));
                ((GoodsViewHolder) holder).mTvQuantityOfRefundNum.setText(String.valueOf(validNum));
                ((GoodsViewHolder) holder).mTvGoodDeductPrice.setText(String.valueOf(deductPrice));
                ((GoodsViewHolder) holder).mTvTotalPrice.setText(String.valueOf(totalPrice));


            } else if (holder instanceof GoodsVehicleViewHolder) {
                String goodsTime = orderRefundInitEntity.getGoodsTime(); //用车时间
                String vehicleStartAddress = orderRefundInitEntity.getStartName();
                String vehicleEndAddress = orderRefundInitEntity.getEndName();

                ((GoodsVehicleViewHolder) holder).mTvVehicleExpensesPrice.setText(Tools.getTwoDecimalPoint(price));

                if(!TextUtils.isEmpty(deductNote))
                {
                    ((GoodsVehicleViewHolder) holder).mTvGoodDeductNote.setText(deductNote);
                }

                if(!TextUtils.isEmpty(goodsTime))
                {
                    ((GoodsVehicleViewHolder) holder).mTvVehicleUseTime.setText(goodsTime);
                }

                if(!TextUtils.isEmpty(vehicleStartAddress)){

                    ((GoodsVehicleViewHolder) holder).mTvVehicleStartPoint.setText(vehicleStartAddress);
                }

                if(!TextUtils.isEmpty(vehicleEndAddress)){

                    ((GoodsVehicleViewHolder) holder).mTvVehicleDestination.setText(vehicleEndAddress);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return orderRefundInitList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        OrderRefundInitResponse orderRefundInitResponse = getItem(position);
        String goodsType = orderRefundInitResponse.getGoodsType();
        if (goodsType.equals(GoodsTypeEnum.GOODS_TICKET.getValue())) {
            return GoodsTypeEnum.GOODS_TICKET.getKey();
        } else if (goodsType.equals(MchTypeEnum.CAR.getValue())) {
            return GoodsTypeEnum.GOODS_CAR.getKey();
        }

        return 0;
    }

    public OrderRefundInitResponse getItem(int position) {
        return position >= 0 && position < getItemCount() ? orderRefundInitList.get(position) : null;
    }


    //商品适配器
    public static class GoodsViewHolder extends RecyclerView.ViewHolder {

        //商品照片
        RoundedImageView mImgGoods;

        //已经被使用
        TextView mTvHasBeenUsedNum;

        //可使用数量
        TextView mTvQuantityOfRefundNum;

        //总金额
        TextView mTvTotalPrice;

        //商品数量
        TextView mTvGoodNum;

        //商品扣款理由
        TextView mTvGoodDeductNote;

        //商品扣款价格
        TextView mTvGoodDeductPrice;

        public GoodsViewHolder(View itemView) {
            super(itemView);

            mImgGoods = itemView.findViewById(R.id.image_goods);
            mTvHasBeenUsedNum = itemView.findViewById(R.id.tv_has_been_used_num);
            mTvQuantityOfRefundNum = itemView.findViewById(R.id.tv_quantity_of_refund_num);
            mTvTotalPrice = itemView.findViewById(R.id.tv_total_price);
            mTvGoodNum = itemView.findViewById(R.id.tv_good_num);
            mTvGoodDeductNote = itemView.findViewById(R.id.tv_good_deduct_note);
            mTvGoodDeductPrice = itemView.findViewById(R.id.tv_deduct_price);
        }
    }

    //用车退款适配器
    public static class GoodsVehicleViewHolder extends RecyclerView.ViewHolder {

        //用车使用时间
        TextView mTvVehicleUseTime;

        //用车花费金额
        TextView mTvVehicleExpensesPrice;

        //用车起始地点
        TextView mTvVehicleStartPoint;

        //用车结束地点
        TextView mTvVehicleDestination;

        //退款理由
        TextView mTvGoodDeductNote;

        public GoodsVehicleViewHolder(View itemView) {
            super(itemView);

            mTvVehicleUseTime = itemView.findViewById(R.id.tv_vehicle_use_time);
            mTvVehicleUseTime = itemView.findViewById(R.id.tv_deduction_information);
            mTvVehicleExpensesPrice = itemView.findViewById(R.id.tv_vehicle_expenses_price);
            mTvVehicleStartPoint = itemView.findViewById(R.id.tv_starting_point);
            mTvVehicleDestination = itemView.findViewById(R.id.tv_destination);
            mTvGoodDeductNote = itemView.findViewById(R.id.tv_deduction_information);

        }
    }
}
