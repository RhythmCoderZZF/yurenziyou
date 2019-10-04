package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.NearbyTypeResponse;
import com.nbhysj.coupon.model.response.UserOrderListResponse;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

/**
 * @author hysj created at 2019/05/09.
 * description:景点附近酒店适配器
 */
public class MyOrderTicketListAdapter extends RecyclerView.Adapter<MyOrderTicketListAdapter.ViewHolder> {

    List<UserOrderListResponse.MchsEntity> mchsEntityList;
    private Context mContext;

    public MyOrderTicketListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setMchsEntityList(List<UserOrderListResponse.MchsEntity> mchsEntityList) {

        this.mchsEntityList = mchsEntityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_order_ticket_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            UserOrderListResponse.MchsEntity mchsEntity = mchsEntityList.get(itemPosition);
            String address = mchsEntity.getAddress();
            int commentStatus = mchsEntity.getCommentStatus();
            int mchId = mchsEntity.getMchId();
            String mchName = mchsEntity.getMchName();
            String photoUrl = mchsEntity.getPhoto();
            List<UserOrderListResponse.GoodsEntity> goodsEntityList = mchsEntity.getGoodsVOList();

            GlideUtil.loadImage(mContext, photoUrl,  holder.mImgTicketPhotograph);

            holder.mTvAddress.setText(address);
            holder.mTvMchName.setText(mchName);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
            holder.mRvUserTicketDetail.setLayoutManager(linearLayoutManager);
            MyOrderTicketSubListAdapter myOrderTicketListAdapter = new MyOrderTicketSubListAdapter(mContext);
            myOrderTicketListAdapter.setUserOrderList(goodsEntityList);
            holder.mRvUserTicketDetail.setAdapter(myOrderTicketListAdapter);

            holder.mTvOrderToComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mchsEntityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //票标题
        TextView mTvTicketTitle;
        //票使用时间
        TextView mTvTicketTime;
        //票价
        TextView mTvTicketPrice;
        //购买数量
        TextView mTvTicketPurchaseNum;

        RecyclerView mRvUserTicketDetail;

        ImageView mImgTicketPhotograph;

        //商户名
        TextView mTvMchName;

        //地址
        TextView mTvAddress;

        //订单评价
        TextView mTvOrderToComment;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvTicketTitle = itemView.findViewById(R.id.tv_ticket_title);
            mTvTicketTime = itemView.findViewById(R.id.tv_ticket_time);
            mTvTicketPrice = itemView.findViewById(R.id.tv_ticket_price);
            mTvTicketPurchaseNum = itemView.findViewById(R.id.tv_ticket_num);
            mRvUserTicketDetail = itemView.findViewById(R.id.rv_user_ticket_detail);
            mImgTicketPhotograph = itemView.findViewById(R.id.img_ticket_photograph);
            mTvMchName = itemView.findViewById(R.id.tv_mch_name);
            mTvAddress = itemView.findViewById(R.id.tv_address);
            mTvOrderToComment = itemView.findViewById(R.id.tv_order_to_comment);
        }
    }

    public interface OrderOnclickListener{

        void setOrderOnclickListener();
    }
}
