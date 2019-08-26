package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.ui.OrderSubmitActivity;

import java.util.List;

/**
 * @auther：hysj created on 2019/8/18
 * description：门票订票
 */
public class AdmissionTicketExpandableAdapter extends BaseExpandableListAdapter {

    Context context;
    List<MchGoodsBean> entranceTicketList;

    public AdmissionTicketExpandableAdapter(Context context, List<MchGoodsBean> entranceTicketList) {
        this.context = context;
        this.entranceTicketList = entranceTicketList;
    }

    //        获取分组的个数
    @Override
    public int getGroupCount() {
        return entranceTicketList.size();
    }

    //        获取指定分组中的子选项的个数
    @Override
    public int getChildrenCount(int childPosition) {
        return 1;
    }

    //        获取指定的分组数据
    @Override
    public Object getGroup(int groupPosition) {
        return entranceTicketList.get(groupPosition);
    }

    //        获取指定分组中的指定子选项数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return entranceTicketList.get(childPosition);
    }

    //        获取指定分组的ID, 这个ID必须是唯一的
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //        获取子选项的ID, 这个ID必须是唯一的
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //        分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们。
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //        获取显示指定分组的视图
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
        Groupitem groupitem;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_ticket_expandable_item, viewGroup, false);
            groupitem = new Groupitem();
            groupitem.mTvTicketType = view.findViewById(R.id.tv_ticket_type);
            groupitem.mImgTicketExpandable = view.findViewById(R.id.img_admission_ticket_status);
            groupitem.mTvPerCapitaPrice = view.findViewById(R.id.tv_per_capita_price); //价格
            groupitem.mTvDefaultPrice = view.findViewById(R.id.tv_default_price); //默认价格

            view.setTag(groupitem);
        } else {
            groupitem = (Groupitem) view.getTag();
        }
        MchGoodsBean mchGoodsBean = entranceTicketList.get(groupPosition);
        String title = mchGoodsBean.getTitle();
        int defaultPrice = mchGoodsBean.getDefaultPrice();
        int marketPrice = mchGoodsBean.getMarketPrice();
        groupitem.mTvTicketType.setText(title);
        groupitem.mTvPerCapitaPrice.setText(String.valueOf(marketPrice));
        groupitem.mTvDefaultPrice.setText("¥" + String.valueOf(defaultPrice));
        if (isExpanded) {
            groupitem.mImgTicketExpandable.setImageResource(R.mipmap.icon_admission_ticket_expand);
        } else {
            groupitem.mImgTicketExpandable.setImageResource(R.mipmap.icon_admission_ticket_close);
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        chilItem chilItem;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_ticket_expandable_sub_item, viewGroup, false);
            chilItem = new chilItem();
            chilItem.mTvTicketTitle = view.findViewById(R.id.tv_ticket_title);
            chilItem.mTvBookTicket = view.findViewById(R.id.tv_book_ticket);
            chilItem.mTvBookTicketInfo = view.findViewById(R.id.tv_book_info);
            chilItem.mTvSellNum = view.findViewById(R.id.tv_sell_num);
            chilItem.mTvDefaultPrice = view.findViewById(R.id.tv_default_price);
            chilItem.mTvMarketPrice = view.findViewById(R.id.tv_market_price); //价格
            chilItem.mTvAlreadyReduced = view.findViewById(R.id.tv_already_reduced); //已优惠

            view.setTag(chilItem);
        } else {

            chilItem = (chilItem) view.getTag();
        }

        MchGoodsBean ticketEntity = entranceTicketList.get(groupPosition);
        String bookingInfo = ticketEntity.getBookingInfo();
        int defaultPrice = ticketEntity.getDefaultPrice();
        int marketPrice = ticketEntity.getMarketPrice();
        int sellNum = ticketEntity.getSellNum();
        String ticketIntoType = ticketEntity.getTicketIntoType();
        String refundSettings = ticketEntity.getRefundSettings();
        int goodsId = ticketEntity.getGoodsId();

        chilItem.mTvTicketTitle.setText(ticketEntity.getTitle());
        chilItem.mTvBookTicketInfo.setText(bookingInfo);
        chilItem.mTvSellNum.setText("已售"+ sellNum + " | 购买须知 >");
        chilItem.mTvMarketPrice.setText("¥" + String.valueOf(marketPrice));
        int discountAmount = marketPrice - defaultPrice;
        chilItem.mTvDefaultPrice.setText("¥" + String.valueOf(defaultPrice));
        chilItem.mTvAlreadyReduced.setText("已减" + String.valueOf(discountAmount) + "元");

        chilItem.mTvMarketPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
        chilItem.mTvBookTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent mIntent = new Intent();
                mIntent.setClass(context, OrderSubmitActivity.class);
                mIntent.putExtra("goodsId",goodsId);
                context.startActivity(mIntent);
            }
        });
        return view;
    }

    //        指定位置上的子元素是否可选中
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class Groupitem {
        //票类型
        TextView mTvTicketType;
        ImageView mImgTicketExpandable;
        //价格
        TextView mTvPerCapitaPrice;
        //默认价格
        TextView mTvDefaultPrice;
    }

    class chilItem {

        //票标题
        TextView mTvTicketTitle;
        //预定票
        TextView mTvBookTicket;
        //购票预定信息
        TextView mTvBookTicketInfo;
        //已售数量
        TextView mTvSellNum;
        //默认价格
        TextView mTvDefaultPrice;
        //市场价格
        TextView mTvMarketPrice;
        //已优惠
        TextView mTvAlreadyReduced;
    }
}
