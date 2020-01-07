package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.PositionDistanceBean;
import com.nbhysj.coupon.model.response.SortTypeBean;

import java.util.List;

/**
 * @author hysj created at 2019/05/20.
 * description: 筛选下拉列表适配器
 */
public class SortDropDownMenuListAdapter extends RecyclerView.Adapter<SortDropDownMenuListAdapter.ViewHolder> {

    List<SortTypeBean> sortTypeList;
    private Context mContext;
    private DropDownMenuListener dropDownMenuListener;

    public SortDropDownMenuListAdapter(Context mContext, DropDownMenuListener dropDownMenuListener) {

        this.mContext = mContext;
        this.dropDownMenuListener = dropDownMenuListener;
    }

    public void setDropDownMenuList(List<SortTypeBean> sortTypeList) {

        this.sortTypeList = sortTypeList;
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

            SortTypeBean sortTypeBean = sortTypeList.get(itemPosition);
            String sortCondition = sortTypeBean.getSortType();
            holder.mTvSortCondition.setText(sortCondition);
            holder.mRlytDropDownMenuItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (int i = 0; i < sortTypeList.size(); i++) {
                        sortTypeList.get(i).setSelect(false);
                    }
                    sortTypeBean.setSelect(true);

                    notifyDataSetChanged();

                    dropDownMenuListener.setDropDownMenuListener(sortTypeBean);
                }
            });

            if (sortTypeBean.isSelect()) {

                //  holder.mImgItemSelect.setBackgroundResource(R.mipmap.icon_orange_tick);
                holder.mTvSortCondition.setTextColor(mContext.getResources().getColor(R.color.color_orange2));
                holder.mImgItemSelect.setVisibility(View.VISIBLE);
            } else {
                // holder.mImgItemSelect.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                holder.mTvSortCondition.setTextColor(mContext.getResources().getColor(R.color.color_text_black2));
                holder.mImgItemSelect.setVisibility(View.GONE);
            }

            if (itemPosition == sortTypeList.size() - 1) {
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
        return sortTypeList.size();
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

        void setDropDownMenuListener(SortTypeBean sortTypeBean);
    }

}
