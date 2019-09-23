package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.GroupMchResponse;
import com.nbhysj.coupon.ui.GroupMchDetailsActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hysj created at 2019/09/23.
 * description : 组合商品分类适配器
 */
public class GroupMchGoodsClassifyListAdapter extends RecyclerView.Adapter<GroupMchGoodsClassifyListAdapter.ViewHolder> {

    List<String> containCostsList;

    private Context mContext;
    LayoutInflater mInflater;

    public GroupMchGoodsClassifyListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setGroupMchGoodsList(List<String> containCostsList) {

        this.containCostsList = containCostsList;
    }

    @Override
    public GroupMchGoodsClassifyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_mch_good_classify_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupMchGoodsClassifyListAdapter.ViewHolder holder, int position) {

        try {

            String goodName = containCostsList.get(position);
            holder.mTvGroupMchGoodName.setText(goodName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return containCostsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //组合商品描述
        TextView mTvGroupMchGoodName;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvGroupMchGoodName = itemView.findViewById(R.id.tv_group_mch_good_name);
        }
    }
}
