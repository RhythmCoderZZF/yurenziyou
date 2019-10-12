package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
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
import com.nbhysj.coupon.common.Enum.GoodsTypeEnum;
import com.nbhysj.coupon.common.Enum.OrderRefundStatusEnum;
import com.nbhysj.coupon.model.response.UserOrderListResponse;
import com.nbhysj.coupon.ui.AllRefundApplyActivity;
import com.nbhysj.coupon.ui.GroupMchDetailsActivity;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.ui.OrderDetailActivity;
import com.nbhysj.coupon.ui.OrderEvaluateActivity;
import com.nbhysj.coupon.ui.OrderPaymentActivity;
import com.nbhysj.coupon.ui.OrderSubmitActivity;
import com.nbhysj.coupon.ui.RefundDetailsActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.Tools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/03/29.
 * description: 订单适配器
 */
public class MyOrderListAdapter extends RecyclerView.Adapter<MyOrderListAdapter.ViewHolder> {

    /**
     * 图片列表数据
     */
    private List<UserOrderListResponse.OrderTypeEntity> userOrderList;

    private Context mContext;

    private MyOrderListener myOrderListener;

    public MyOrderListAdapter(Context mContext, MyOrderListener myOrderListener) {

        this.mContext = mContext;
        this.myOrderListener = myOrderListener;
    }

    public void setMyOrderList(List<UserOrderListResponse.OrderTypeEntity> userOrderList) {

        this.userOrderList = userOrderList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = View.inflate(parent.getContext(), R.layout.list_survey_type_answer_item, null);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_order_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {

            UserOrderListResponse.OrderTypeEntity orderTypeEntity = userOrderList.get(position);
            String orderStatus = orderTypeEntity.getOrderStatus();
            String orderType = orderTypeEntity.getOrderType();
            int goodsNum = orderTypeEntity.getGoodsNum();
            double totalPrice = orderTypeEntity.getTotalPrice();
            long outTime = orderTypeEntity.getOutTime();
            String orderNo = orderTypeEntity.getOrderNo();      //订单号
            String orderRefundNo = orderTypeEntity.getOrderRefundNo();  //退款订单号
            String goodsName = orderTypeEntity.getGoodsName();  //商品名字
            UserOrderListResponse.StatusEntity statusEntity = orderTypeEntity.getStatusVO();
            int canBuyAgainStatus = statusEntity.getCanBuyAgainStatus();
            int canCancelStatus = statusEntity.getCanCancelStatus();
            int canDelStatus = statusEntity.getCanDelStatus();
            int canPayStatus = statusEntity.getCanPayStatus();
            int commentStatus = statusEntity.getCommentStatus();
            int useCarStatus = statusEntity.getUseCarStatus();
            int canRefundStatus = statusEntity.getCanRefundStatus();
            //再次购买
            UserOrderListResponse.BuyAginEntity buyAginEntity = orderTypeEntity.getBuyAginVO();
            int buyId = buyAginEntity.getBuyId();
            String buyType = buyAginEntity.getBuyType();

            List<UserOrderListResponse.MchsEntity> mchsEntityList = orderTypeEntity.getMchs();

            List<UserOrderListResponse.OrderListCarEntity> orderListCarList = orderTypeEntity.getOrderListCarVO();

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
            holder.mRvUserTicket.setLayoutManager(linearLayoutManager);
            MyOrderTicketListAdapter myOrderTicketListAdapter = new MyOrderTicketListAdapter(mContext, new MyOrderTicketListAdapter.OrderOnclickListener() {
                @Override
                public void setLookMyOrderDetailListener() {

                    Intent intent = new Intent();
                    intent.setClass(mContext, OrderDetailActivity.class);
                    intent.putExtra("orderNo", orderNo);
                    mContext.startActivity(intent);

                }
            });
            myOrderTicketListAdapter.setMchsEntityList(mchsEntityList);
            holder.mRvUserTicket.setAdapter(myOrderTicketListAdapter);

            //车辆 路径信息
            LinearLayoutManager vehicleUseLinearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(vehicleUseLinearLayoutManager.VERTICAL);
            holder.mRvVehicleUse.setLayoutManager(vehicleUseLinearLayoutManager);
            OrderVehicleUseAdapter orderVehicleUseAdapter = new OrderVehicleUseAdapter(new OrderVehicleUseAdapter.OrderOnclickListener() {
                @Override
                public void setLookMyOrderDetailListener() {

                    Intent intent = new Intent();
                    intent.setClass(mContext, OrderDetailActivity.class);
                    intent.putExtra("orderNo", orderNo);
                    mContext.startActivity(intent);

                }
            });
            orderVehicleUseAdapter.setVehicleUseList(orderListCarList);
            holder.mRvVehicleUse.setAdapter(orderVehicleUseAdapter);

            holder.mTvOrderStatus.setText(orderStatus);
            holder.mTvCommodityNum.setText("共" + goodsNum + "件商品");
            holder.mTvTotalPrice.setText(Tools.getTwoDecimalPoint(totalPrice));
            String goodsTypeTicket = GoodsTypeEnum.GOODS_TICKET.getValue();
            String goodsTypeHotelRoom = GoodsTypeEnum.GOODS_HOTEL_ROOM.getValue();
            String goodsTypeRecreation = GoodsTypeEnum.GOODS_RECREATION.getValue();
            String goodsTypeGroup = GoodsTypeEnum.GROUP_GOODS.getValue();

            String goodsTypeHotel = GoodsTypeEnum.MCH_HOTEL1.getValue();
            String goodsTypeHotelHomestay = GoodsTypeEnum.MCH_HOTEL2.getValue();

            if (orderType.equals(goodsTypeTicket)) {
                holder.mImgOrderTypeTag.setImageResource(R.mipmap.icon_order_scenic_spot_tag);
                holder.mTvOrderMchType.setText(mContext.getResources().getString(R.string.str_scenic_spot));
            } else if (orderType.equals(goodsTypeHotelRoom)) {

                holder.mImgOrderTypeTag.setImageResource(R.mipmap.icon_order_hotel_tag);
                holder.mTvOrderMchType.setText(mContext.getResources().getString(R.string.str_hotel));

            } else if (orderType.equals(goodsTypeRecreation)) {

                holder.mImgOrderTypeTag.setImageResource(R.mipmap.icon_order_recreation_tag);
                holder.mTvOrderMchType.setText(mContext.getResources().getString(R.string.str_recreation));

            } else if (orderType.equals(goodsTypeGroup)) {

                holder.mImgOrderTypeTag.setImageResource(R.mipmap.icon_order_group_mch_tag);
                holder.mTvOrderMchType.setText(mContext.getResources().getString(R.string.str_group_mch));
            }

            //可取消
            if (canCancelStatus == 0) {

                holder.mTvOrderCancel.setVisibility(View.GONE);

            } else if (canCancelStatus == 1) {

                holder.mTvOrderCancel.setVisibility(View.VISIBLE);
            }

            //待支付
            if (canPayStatus == 0) {
                holder.mLlytOrderNotPendingPayment.setVisibility(View.VISIBLE);
                holder.mRlytPendingOrder.setVisibility(View.GONE);

            } else if (canPayStatus == 1) {
                holder.mLlytOrderNotPendingPayment.setVisibility(View.GONE);
                holder.mRlytPendingOrder.setVisibility(View.VISIBLE);
                long payExprireTime = orderTypeEntity.getPayExprireTime();
                String payExprireTimeStr = DateUtil.millisToStringShort(payExprireTime);
                holder.mTvPayExprireTime.setText(payExprireTimeStr + "后取消订单");
            }

            //再次购买
            if (canBuyAgainStatus == 0) {

                holder.mTvOrderRefundBuyAgain.setVisibility(View.GONE);

            } else if (canBuyAgainStatus == 1) {

                holder.mTvOrderRefundBuyAgain.setVisibility(View.VISIBLE);
            }

            //可退款
            if (canRefundStatus == 0)
            {
                holder.mTvOrderRefund.setVisibility(View.GONE);

            } else if(canRefundStatus == 1){

                holder.mTvOrderRefund.setVisibility(View.VISIBLE);
            }

            //可删除订单
            if (canDelStatus == 0) {
                holder.mTvOrderDelete.setVisibility(View.GONE);

            } else if (canDelStatus == 1) {

                holder.mTvOrderDelete.setVisibility(View.VISIBLE);
            }

            //可评论
            if (commentStatus == 0) {
                holder.mTvOrderComment.setVisibility(View.GONE);

            } else if (commentStatus == 1) {

                holder.mTvOrderComment.setVisibility(View.VISIBLE);
            }

            /**************************    退款    *************************/
            if (!TextUtils.isEmpty(orderStatus)) {

                if (orderStatus.equals(OrderRefundStatusEnum.ORDER_REFUNDING.getValue())) {

                    holder.mTvViewingRefundProgress.setVisibility(View.VISIBLE);
                    holder.mTvViewingRefundProgress.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent();
                            intent.putExtra("orderRefundNo", orderRefundNo);
                            intent.setClass(mContext, RefundDetailsActivity.class);
                            mContext.startActivity(intent);
                        }
                    });

                } else if (orderStatus.equals(OrderRefundStatusEnum.ORDER_REFUND_SUCCESSFUL.getValue())) {

                    holder.mTvOrderRefundBuyAgain.setVisibility(View.VISIBLE);
                    holder.mTvOrderDelete.setVisibility(View.VISIBLE);

                } else if (orderStatus.equals(OrderRefundStatusEnum.ORDER_REFUND_CLOSED.getValue())) {

                    holder.mTvViewingRefundDetail.setVisibility(View.VISIBLE);
                }
            }

            holder.mTvOrderPendingPayment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, OrderPaymentActivity.class);
                    intent.putExtra("price", totalPrice);
                    intent.putExtra("title", goodsName);
                    intent.putExtra("payExprireTime", outTime);
                    intent.putExtra("orderNo", orderNo);
                    mContext.startActivity(intent);
                }
            });

            holder.mTvOrderRefundBuyAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent mIntent = new Intent();

                    if (orderType.equals(goodsTypeTicket)) {  //景点

                        mIntent.setClass(mContext, ScenicSpotDetailActivity.class);
                        mIntent.putExtra("mchId", buyId);
                        mContext.startActivity(mIntent);

                    } else if (orderType.equals(goodsTypeRecreation)) {  //互动

                        mIntent.setClass(mContext, ScenicSpotDetailActivity.class);
                        mIntent.putExtra("mchId", buyId);
                        mContext.startActivity(mIntent);

                    } else if (orderType.equals(goodsTypeGroup)) { //组合

                        mIntent.setClass(mContext, GroupMchDetailsActivity.class);
                        mIntent.putExtra("packageId", buyId);
                        mContext.startActivity(mIntent);
                    } else if (orderType.equals(goodsTypeHotel)) {  //酒店

                        mIntent.setClass(mContext, HotelDetailsActivity.class);
                        mIntent.putExtra("mchId", buyId);
                        mContext.startActivity(mIntent);

                    }else if (orderType.equals(goodsTypeHotelHomestay)) {  //民宿

                        mIntent.setClass(mContext, HomestayDetailActivity.class);
                        mIntent.putExtra("mchId", buyId);
                        mContext.startActivity(mIntent);

                    }
                }
            });

            holder.mTvOrderRefund.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myOrderListener.setOrderAllRefundListener(orderNo);
                }
            });

            holder.mLlytMyOrderItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, OrderDetailActivity.class);
                    intent.putExtra("orderNo", orderNo);
                    mContext.startActivity(intent);
                }
            });

            holder.mTvOrderDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myOrderListener.setOrderDeleteListener(position, orderTypeEntity);
                }
            });

            holder.mTvOrderCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myOrderListener.setOrderCancelListener(orderTypeEntity);
                }
            });

            holder.mTvOrderComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    if(!orderType.equals(goodsTypeGroup))
                    {
                        if(mchsEntityList != null) {
                            UserOrderListResponse.MchsEntity mchsEntity = mchsEntityList.get(0);
                            UserOrderListResponse.GoodsEntity goodsEntity = mchsEntity.getGoodsVOList().get(0);
                            if(goodsEntity != null) {

                                int orderGoodsId = goodsEntity.getOrderGoodsId();
                                myOrderListener.setPartialOrderCommentListener(orderGoodsId);
                            }
                        }


                    } else {

                        myOrderListener.setGroupOrderCommentListener(orderNo);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return userOrderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llyt_my_order_item)
        LinearLayout mLlytMyOrderItem;
        //订单类型图片
        @BindView(R.id.img_order_type_tag)
        ImageView mImgOrderTypeTag;
        //订单商户类型
        @BindView(R.id.tv_order_mch_type)
        TextView mTvOrderMchType;
        //订单状态
        @BindView(R.id.tv_order_status)
        TextView mTvOrderStatus;
        //票详情
        @BindView(R.id.rv_user_ticket)
        RecyclerView mRvUserTicket;
        //用车列表
        @BindView(R.id.rv_vehicle_use)
        RecyclerView mRvVehicleUse;
        //商品数量
        @BindView(R.id.tv_commodity_num)
        TextView mTvCommodityNum;

        //待评价
        @BindView(R.id.tv_order_comment)
        TextView mTvOrderComment;

        //订单取消
        @BindView(R.id.tv_order_cancel)
        TextView mTvOrderCancel;

        //订单删除
        @BindView(R.id.tv_order_delete)
        TextView mTvOrderDelete;

        //订单待支付
        @BindView(R.id.tv_order_pending_payment)
        TextView mTvOrderPendingPayment;

        //待支付时间  订单将取消
        @BindView(R.id.tv_pending_payment_time)
        TextView mTvPayExprireTime;

        //待支付订单
        @BindView(R.id.rlyt_pending_order)
        RelativeLayout mRlytPendingOrder;

        //订单去退款
        @BindView(R.id.tv_order_refund)
        TextView mTvOrderRefund;

        //再次预定
        @BindView(R.id.tv_order_buy_again)
        TextView mTvOrderRefundBuyAgain;

        //查看退款进度
        @BindView(R.id.tv_viewing_refund_progress)
        TextView mTvViewingRefundProgress;
        //查看退款详情
        @BindView(R.id.tv_viewing_refund_detail)
        TextView mTvViewingRefundDetail;
        //订单非待支付
        @BindView(R.id.llyt_order_not_pending_payment)
        LinearLayout mLlytOrderNotPendingPayment;
      /*  //交易成功
        @BindView(R.id.rlyt_successful_trade)
        RelativeLayout mRlytSuccessfulTrade;

        //交易关闭
        @BindView(R.id.rlyt_transaction_closed)
        RelativeLayout mRlytTransactionClosed;*/

        //总价格
        @BindView(R.id.tv_total_price)
        TextView mTvTotalPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface MyOrderListener {

        void setOrderDeleteListener(int position, UserOrderListResponse.OrderTypeEntity orderTypeEntity);

        void setOrderCancelListener(UserOrderListResponse.OrderTypeEntity orderTypeEntity);

        void setOrderAllRefundListener(String orderNo);

        void setGroupOrderCommentListener(String orderNo);

        void setPartialOrderCommentListener(int orderGoodsId);
    }
}
