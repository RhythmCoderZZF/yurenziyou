package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.TicketEntranceWayEnum;
import com.nbhysj.coupon.common.Enum.TicketRefundSettingsEnum;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.OrderSubmitActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther：hysj created on 2019/09/22
 * description：组合订票
 */
public class GroupMchTicketAdapter extends BaseExpandableListAdapter {

    Context context;
    List<MchGoodsBean> entranceTicketList;
    private List<String> goodsPriceTagList = null;
    private GroupMchTicketListener groupMchTicketListener;
    public GroupMchTicketAdapter(Context context, List<MchGoodsBean> entranceTicketList,GroupMchTicketListener groupMchTicketListener)
    {
        this.context = context;
        this.entranceTicketList = entranceTicketList;
        this.groupMchTicketListener = groupMchTicketListener;
        if (goodsPriceTagList == null) {

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
            view = LayoutInflater.from(context).inflate(R.layout.layout_group_mch_ticket_expandable_item, viewGroup, false);
            groupitem = new Groupitem();
            groupitem.mTvTicketTitle = view.findViewById(R.id.tv_ticket_title);
            groupitem.mImgTicketExpandable = view.findViewById(R.id.img_admission_ticket_status);
            groupitem.mTvDefaultPrice = view.findViewById(R.id.tv_per_capita_price); //价格
            groupitem.mTvMarketPrice = view.findViewById(R.id.tv_market_price); //默认价格
            groupitem.mTvBookInfo = view.findViewById(R.id.tv_good_booking_info); //预定信息

            view.setTag(groupitem);
        } else {
            groupitem = (Groupitem) view.getTag();
        }
        MchGoodsBean mchGoodsBean = entranceTicketList.get(groupPosition);
        String title = mchGoodsBean.getTitle();
        int defaultPrice = mchGoodsBean.getDefaultPrice();
        int marketPrice = mchGoodsBean.getMarketPrice();
        String bookingInfo = mchGoodsBean.getBookingInfo();
        groupitem.mTvTicketTitle.setText(title);
        groupitem.mTvDefaultPrice.setText(String.valueOf(defaultPrice));
        groupitem.mTvMarketPrice.setText("¥" + String.valueOf(marketPrice));
        groupitem.mTvMarketPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
        if(!TextUtils.isEmpty(bookingInfo))
        {
            groupitem.mTvBookInfo.setText(bookingInfo);
        }
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
            view = LayoutInflater.from(context).inflate(R.layout.layout_group_mch_ticket_expandable_sub_item, viewGroup, false);
            chilItem = new chilItem();
            chilItem.mTvTicketTitle = view.findViewById(R.id.tv_ticket_title);
            chilItem.mTvBookTicket = view.findViewById(R.id.tv_book_ticket);
            chilItem.mTvBookTicketInfo = view.findViewById(R.id.tv_book_info);
            chilItem.mTvSellNum = view.findViewById(R.id.tv_sell_num);
            chilItem.mTvDefaultPrice = view.findViewById(R.id.tv_default_price);
            chilItem.mTvMarketPrice = view.findViewById(R.id.tv_market_price); //价格
            chilItem.mTvAlreadyReduced = view.findViewById(R.id.tv_already_reduced); //已优惠
            chilItem.mRvGroupMchDetailsClassify = view.findViewById(R.id.rv_group_mch_good_classify);
            chilItem.mTagFlowLayoutTicketInfo = view.findViewById(R.id.flowlayout_hot_label);
            chilItem.mLlytGroupMchTicketItem = view.findViewById(R.id.llyt_group_mch_ticket_item);

            view.setTag(chilItem);
        } else {

            chilItem = (chilItem) view.getTag();
        }
        goodsPriceTagList.clear();
        MchGoodsBean mchGoodsBean = entranceTicketList.get(groupPosition);
        String bookingInfo = mchGoodsBean.getBookingInfo();
        int defaultPrice = mchGoodsBean.getDefaultPrice();
        int marketPrice = mchGoodsBean.getMarketPrice();
        int sellNum = mchGoodsBean.getSellNum();
        String refundSettingsValue = mchGoodsBean.getRefundSettings(); //退票信息
        String ticketIntoTypeValue = mchGoodsBean.getTicketIntoType(); //换票信息
        String goodsBuyNotes = mchGoodsBean.getGoodsBuyNotes();
        int goodsId = mchGoodsBean.getGoodsId();

        goodsPriceTagList.add(context.getResources().getString(R.string.str_official));
        if(!TextUtils.isEmpty(refundSettingsValue)) {
            goodsPriceTagList.add(TicketRefundSettingsEnum.getEnumValueByKey(refundSettingsValue));
        }
        if(!TextUtils.isEmpty(ticketIntoTypeValue))
        {
            goodsPriceTagList.add(TicketEntranceWayEnum.getEnumValueByKey(ticketIntoTypeValue));
        }
        if (goodsPriceTagList != null) {
            TagAdapter tagAdapter = new TagAdapter<String>(goodsPriceTagList) {
                @Override
                public View getView(FlowLayout parent, int position, String option) {
                    View view = LayoutInflater.from(context).inflate(R.layout.layout_flowlayout_tag_increase_ticket,
                            chilItem.mTagFlowLayoutTicketInfo, false);
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

            chilItem.mTagFlowLayoutTicketInfo.setAdapter(tagAdapter);
        }
        chilItem.mTvTicketTitle.setText(mchGoodsBean.getTitle());
        chilItem.mTvBookTicketInfo.setText(bookingInfo);
        chilItem.mTvSellNum.setText("已售"+ sellNum + " | 购买须知 >");
        chilItem.mTvMarketPrice.setText("¥" + String.valueOf(marketPrice));
        int discountAmount = marketPrice - defaultPrice;
        chilItem.mTvDefaultPrice.setText("¥" + String.valueOf(defaultPrice));
        chilItem.mTvAlreadyReduced.setText("已减" + String.valueOf(discountAmount) + "元");

        chilItem.mTvMarketPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线

        List<String> containCostsList = mchGoodsBean.getContainCosts();

        //商品分类(费用包含)
        if(containCostsList != null)
        {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
            chilItem.mRvGroupMchDetailsClassify.setLayoutManager(linearLayoutManager);
            GroupMchGoodsClassifyListAdapter goodsClassifyListAdapter = new GroupMchGoodsClassifyListAdapter(context);
            goodsClassifyListAdapter.setGroupMchGoodsList(containCostsList);
            chilItem.mRvGroupMchDetailsClassify.setAdapter(goodsClassifyListAdapter);
        }
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

        chilItem.mLlytGroupMchTicketItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                groupMchTicketListener.setGroupMchTicketCallback(groupPosition,childPosition,goodsBuyNotes);
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
        TextView mTvTicketTitle;
        ImageView mImgTicketExpandable;
        //默认价格
        TextView mTvDefaultPrice;
        //市场价格
        TextView mTvMarketPrice;
        //预定信息
        TextView mTvBookInfo;
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
        //组合商户详情
        RecyclerView mRvGroupMchDetailsClassify;

        //标签
        TagFlowLayout mTagFlowLayoutTicketInfo;

        LinearLayout mLlytGroupMchTicketItem;
    }
    public interface GroupMchTicketListener{

        void setGroupMchTicketCallback(int groupPosition,int childPosition,String goodsBuyNotes);
    }
}
