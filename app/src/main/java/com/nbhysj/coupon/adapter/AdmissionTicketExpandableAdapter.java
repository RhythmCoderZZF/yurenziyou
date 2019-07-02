package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.EntranceTicketResponse;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.ui.SubmitOrderActivity;

import java.util.List;

/**
 * @auther：hysj created on 2019/4/28
 * description：
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
            groupitem.textView = (TextView) view.findViewById(R.id.group_item);
            groupitem.mImgTicketExpandable = view.findViewById(R.id.img_admission_ticket_status);
            view.setTag(groupitem);
        } else {
            groupitem = (Groupitem) view.getTag();
        }
        groupitem.textView.setText(entranceTicketList.get(groupPosition).getTitle());

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
            chilItem.textView = (TextView) view.findViewById(R.id.item);
            chilItem.mTvBookTicket = view.findViewById(R.id.tv_book_ticket);
            view.setTag(chilItem);
        } else {
            chilItem = (chilItem) view.getTag();
        }
        MchGoodsBean ticketEntity = entranceTicketList.get(groupPosition);
        chilItem.textView.setText(ticketEntity.getTitle());
        chilItem.mTvBookTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mIntent = new Intent();
                mIntent.setClass(context, SubmitOrderActivity.class);
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
        TextView textView;
        ImageView mImgTicketExpandable;
    }

    class chilItem {
        TextView textView;
        TextView mTvBookTicket;
    }
}
