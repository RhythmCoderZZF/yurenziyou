package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.model.response.PositionDistanceBean;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/20.
 * description: 筛选下拉列表适配器
 */
public class DropDownMenuListAdapter extends RecyclerView.Adapter<DropDownMenuListAdapter.ViewHolder> {

    List<PositionDistanceBean> dropDownMenuList;
    private Context mContext;
    private DropDownMenuListener dropDownMenuListener;

    public DropDownMenuListAdapter(Context mContext, DropDownMenuListener dropDownMenuListener) {

        this.mContext = mContext;
        this.dropDownMenuListener = dropDownMenuListener;
    }

    public void setDropDownMenuList(List<PositionDistanceBean> dropDownMenuList) {

        this.dropDownMenuList = dropDownMenuList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_drop_down_menu_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            PositionDistanceBean positionDistanceBean = dropDownMenuList.get(itemPosition);
            String sortCondition = positionDistanceBean.getPositionDistanceType();
            holder.mTvSortCondition.setText(sortCondition);
            holder.mRlytDropDownMenuItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (int i = 0; i < dropDownMenuList.size(); i++) {
                        dropDownMenuList.get(i).setSelect(false);
                    }
                    positionDistanceBean.setSelect(true);

                    notifyDataSetChanged();

                    dropDownMenuListener.setDropDownMenuListener(positionDistanceBean);
                }
            });

            if (positionDistanceBean.isSelect()) {

                //  holder.mImgItemSelect.setBackgroundResource(R.mipmap.icon_orange_tick);
                holder.mTvSortCondition.setTextColor(mContext.getResources().getColor(R.color.color_orange2));
                holder.mImgItemSelect.setVisibility(View.VISIBLE);
            } else {
                // holder.mImgItemSelect.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                holder.mTvSortCondition.setTextColor(mContext.getResources().getColor(R.color.color_text_black2));
                holder.mImgItemSelect.setVisibility(View.GONE);
            }

            if (itemPosition == dropDownMenuList.size() - 1) {
                holder.mRlytDropDownMenuItem.setBackgroundResource(R.drawable.bg_white_radius_five_bottom_shape);
            } else {
                holder.mRlytDropDownMenuItem.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dropDownMenuList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //排序条件
        TextView mTvSortCondition;

        ImageView mImgItemSelect;

        RelativeLayout mRlytDropDownMenuItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvSortCondition = itemView.findViewById(R.id.tv_sort_condition);
            mImgItemSelect = itemView.findViewById(R.id.image_item_select);
            mRlytDropDownMenuItem = itemView.findViewById(R.id.rlyt_drop_down_menu_item);
        }
    }

    public interface DropDownMenuListener {

        void setDropDownMenuListener(PositionDistanceBean positionDistanceBean);
    }

}
