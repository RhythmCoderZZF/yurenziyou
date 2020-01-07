package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.PositionDistanceBean;
import com.nbhysj.coupon.model.response.PositionDistanceSearchBean;

import java.util.List;

/**
 * @author hysj created at 2019/05/20.
 * description: (景点,美食)距离位置筛选列表适配器
 */
public class DropDownMenuDistanceClassifyAdapter extends RecyclerView.Adapter<DropDownMenuDistanceClassifyAdapter.ViewHolder> {

    List<PositionDistanceSearchBean> positionDistanceSearchList;
    private DropDownMenuDistanceClassifyListener dropDownMenuDistanceClassifyListener;
    private Context mContext;

    public DropDownMenuDistanceClassifyAdapter(Context mContext, DropDownMenuDistanceClassifyListener dropDownMenuDistanceClassifyListener) {

        this.mContext = mContext;
        this.dropDownMenuDistanceClassifyListener = dropDownMenuDistanceClassifyListener;
    }

    public void setPositionDistanceList(List<PositionDistanceSearchBean> positionDistanceSearchList) {

        this.positionDistanceSearchList = positionDistanceSearchList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_drop_down_menu_distance_classify_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            PositionDistanceSearchBean positionDistanceBean = positionDistanceSearchList.get(itemPosition);
            String positionDistanceType = positionDistanceBean.getTitle();
            holder.mTvPositionDistanceType.setText(positionDistanceType);
            holder.mLlytDistanceClassifyItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (int i = 0; i < positionDistanceSearchList.size(); i++) {
                        positionDistanceSearchList.get(i).setSelect(false);
                    }
                    positionDistanceBean.setSelect(true);

                    notifyDataSetChanged();

                    dropDownMenuDistanceClassifyListener.setDropDownMenuDistanceClassifyListener(itemPosition, positionDistanceBean);
                }
            });

            if (positionDistanceBean.isSelect()) {

                holder.mTvPositionDistanceType.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                holder.mViewDistanceClassify.setVisibility(View.VISIBLE);
                holder.mLlytDistanceClassifyItem.setBackgroundColor(mContext.getResources().getColor(R.color.color_text_gray16));
            } else {
                holder.mViewDistanceClassify.setVisibility(View.GONE);
                holder.mTvPositionDistanceType.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                holder.mLlytDistanceClassifyItem.setBackgroundColor(mContext.getResources().getColor(R.color.white));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return positionDistanceSearchList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvPositionDistanceType;
        View mViewDistanceClassify;
        LinearLayout mLlytDistanceClassifyItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvPositionDistanceType = itemView.findViewById(R.id.tv_position_distance_type);
            mViewDistanceClassify = itemView.findViewById(R.id.view_distance_classify);
            mLlytDistanceClassifyItem = itemView.findViewById(R.id.llyt_distance_classify_item);
        }
    }


    public interface DropDownMenuDistanceClassifyListener {

        void setDropDownMenuDistanceClassifyListener(int position, PositionDistanceSearchBean positionDistanceSearchBean);
    }


}
