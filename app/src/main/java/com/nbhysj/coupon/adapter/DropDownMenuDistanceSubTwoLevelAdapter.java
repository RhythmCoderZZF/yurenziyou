package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.PositionDistanceSearchBean;

import java.util.List;

/**
 * @author hysj created at 2019/05/20.
 * description: 距离位置筛选二级列表适配器
 */
public class DropDownMenuDistanceSubTwoLevelAdapter extends RecyclerView.Adapter<DropDownMenuDistanceSubTwoLevelAdapter.ViewHolder> {
    private DropDownMenuListener dropDownMenuListener;
    List<PositionDistanceSearchBean> positionDistanceTypeClassifyList;
    private Context mContext;

    public DropDownMenuDistanceSubTwoLevelAdapter(Context mContext, DropDownMenuListener dropDownMenuListener) {

        this.mContext = mContext;
        this.dropDownMenuListener = dropDownMenuListener;
    }

    public void setPositionDistanceList(List<PositionDistanceSearchBean> positionDistanceTypeClassifyList) {

        this.positionDistanceTypeClassifyList = positionDistanceTypeClassifyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_drop_down_menu_sub_two_level_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            PositionDistanceSearchBean positionDistanceBean = positionDistanceTypeClassifyList.get(itemPosition);
            String sortCondition = positionDistanceBean.getTitle();
            holder.mTvSortCondition.setText(sortCondition);
            holder.mRlytDropDownMenuItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (int i = 0; i < positionDistanceTypeClassifyList.size(); i++) {
                        positionDistanceTypeClassifyList.get(i).setSelect(false);
                    }
                    positionDistanceBean.setSelect(true);

                    notifyDataSetChanged();

                    dropDownMenuListener.setDropDownMenuListener(itemPosition, positionDistanceBean);
                }
            });

            if (positionDistanceBean.isSelect()) {

                holder.mTvSortCondition.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                holder.mViewSubTwoLevelItem.setVisibility(View.VISIBLE);
                holder.mRlytDropDownMenuItem.setBackgroundColor(mContext.getResources().getColor(R.color.color_text_gray16));
            } else {
                holder.mViewSubTwoLevelItem.setVisibility(View.GONE);
                holder.mTvSortCondition.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                holder.mRlytDropDownMenuItem.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return positionDistanceTypeClassifyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvSortCondition;
        RelativeLayout mRlytDropDownMenuItem;
        View mViewSubTwoLevelItem;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvSortCondition = itemView.findViewById(R.id.tv_sort_condition);
            mRlytDropDownMenuItem = itemView.findViewById(R.id.rlyt_drop_down_menu_item);
            mViewSubTwoLevelItem = itemView.findViewById(R.id.view_sub_two_level_item);
        }
    }

    public interface DropDownMenuListener {

        void setDropDownMenuListener(int position, PositionDistanceSearchBean positionDistanceBean);
    }

}
