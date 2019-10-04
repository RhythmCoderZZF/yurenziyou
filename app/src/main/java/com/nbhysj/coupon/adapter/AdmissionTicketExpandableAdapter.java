package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.TicketEntranceWayEnum;
import com.nbhysj.coupon.common.Enum.TicketRefundSettingsEnum;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.ui.OrderSubmitActivity;
import com.nbhysj.coupon.util.Tools;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther：hysj created on 2019/8/18
 * description：门票订票
 */
public class AdmissionTicketExpandableAdapter extends BaseExpandableListAdapter {

    Context context;
    List<MchGoodsBean> entranceTicketList;
    private String mchType;
    private List<String> goodsPriceTagList;
    public AdmissionTicketExpandableAdapter(Context context, String mchType,List<MchGoodsBean> entranceTicketList) {
        this.context = context;
        this.entranceTicketList = entranceTicketList;
        this.mchType = mchType;
        if(goodsPriceTagList == null)
        {
            goodsPriceTagList = new ArrayList<>();
        } else {
            goodsPriceTagList.clear();
        }
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
            groupitem.mTvDefaultPrice = view.findViewById(R.id.tv_per_capita_price); //价格
            groupitem.mTvMarketPrice = view.findViewById(R.id.tv_market_price); //默认价格

            view.setTag(groupitem);
        } else {
            groupitem = (Groupitem) view.getTag();
        }
        MchGoodsBean mchGoodsBean = entranceTicketList.get(groupPosition);
        String title = mchGoodsBean.getTitle();
        double defaultPrice = mchGoodsBean.getDefaultPrice();
        double marketPrice = mchGoodsBean.getMarketPrice();
        groupitem.mTvTicketType.setText(title);
        groupitem.mTvDefaultPrice.setText(Tools.getTwoDecimalPoint(defaultPrice));
        groupitem.mTvMarketPrice.setText("¥" + Tools.getTwoDecimalPoint(marketPrice));
        groupitem.mTvMarketPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
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
        String refundSettingsValue = null;
        String ticketIntoTypeValue = null;
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
            chilItem.mTagFlowLayout = view.findViewById(R.id.flowlayout_label);

            view.setTag(chilItem);
        } else {

            chilItem = (chilItem) view.getTag();
        }

        MchGoodsBean ticketEntity = entranceTicketList.get(groupPosition);
        String bookingInfo = ticketEntity.getBookingInfo();
        double defaultPrice = ticketEntity.getDefaultPrice();
        double marketPrice = ticketEntity.getMarketPrice();
        int sellNum = ticketEntity.getSellNum();
        String refundSettings = ticketEntity.getRefundSettings();

        if (!TextUtils.isEmpty(refundSettings))
        {
            refundSettingsValue = TicketRefundSettingsEnum.getEnumValueByKey(refundSettings);
        }

        //入园方式:无需换票，直接验证入园TICKET_CHANGE_NO换票入园TICKET_CHANGE
        String ticketIntoType = ticketEntity.getTicketIntoType();
        if (!TextUtils.isEmpty(ticketIntoType)) {
            ticketIntoTypeValue = TicketEntranceWayEnum.getEnumValueByKey(ticketIntoType);
        }

        int goodsId = ticketEntity.getGoodsId();
        goodsPriceTagList.clear();
        goodsPriceTagList.add("官方");
        goodsPriceTagList.add(refundSettingsValue);
        goodsPriceTagList.add(ticketIntoTypeValue);

        if (goodsPriceTagList != null) {
            TagAdapter tagAdapter = new TagAdapter<String>(goodsPriceTagList) {
                @Override
                public View getView(FlowLayout parent, int position, String option) {
                    View view = LayoutInflater.from(context).inflate(R.layout.layout_flowlayout_tag_increase_ticket,
                            chilItem.mTagFlowLayout, false);
                    TextView mTvFlowlayout = view.findViewById(R.id.tv_flowlayout);
                    mTvFlowlayout.setText(option);
                    if (position == 0) {
                        view.setBackgroundResource(R.drawable.bg_stroke_radius_eight_light_orange_shape);

                        mTvFlowlayout.setTextColor(context.getResources().getColor(R.color.color_orange6));
                    } else {
                        view.setBackgroundResource(R.drawable.bg_stroke_radius_eight_light_gray_shape);
                        mTvFlowlayout.setTextColor(context.getResources().getColor(R.color.color_text_gray24));
                    }
                    view.getBackground().setAlpha(30);

                    return view;
                }
            };

            chilItem.mTagFlowLayout.setAdapter(tagAdapter);
        }

        chilItem.mTvTicketTitle.setText(ticketEntity.getTitle());
        chilItem.mTvBookTicketInfo.setText(bookingInfo);
        chilItem.mTvSellNum.setText("已售"+ sellNum + " | 购买须知 >");
        chilItem.mTvMarketPrice.setText("¥" + Tools.getTwoDecimalPoint(marketPrice));
        double discountAmount = marketPrice - defaultPrice;
        chilItem.mTvDefaultPrice.setText("¥" + Tools.getTwoDecimalPoint(defaultPrice));
        chilItem.mTvAlreadyReduced.setText("已减" + Tools.getTwoDecimalPoint(discountAmount) + "元");

        chilItem.mTvMarketPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
        chilItem.mTvBookTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent mIntent = new Intent();
                mIntent.setClass(context, OrderSubmitActivity.class);
                mIntent.putExtra("goodsId",goodsId);
                mIntent.putExtra("mchType",mchType);
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
        //默认价格
        TextView mTvDefaultPrice;
        //市场价格
        TextView mTvMarketPrice;
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
        //标签
        TagFlowLayout mTagFlowLayout;
    }
}
