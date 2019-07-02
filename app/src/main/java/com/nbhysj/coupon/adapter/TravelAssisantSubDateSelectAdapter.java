package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.DateBean;
import com.nbhysj.coupon.model.response.TravelAssistantDateBean;
import com.nbhysj.coupon.model.response.TravellerBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther：hysj created at 2019/03/18
 * description：收件人地址适配器
 */
public class TravelAssisantSubDateSelectAdapter extends RecyclerView.Adapter<TravelAssisantSubDateSelectAdapter.ViewHolder> {

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    private Context mContext;
    private List<DateBean> dateBeanList;
    private TravellerInfoItemListener travellerInfoItemListener;
    private List<TravelAssistantDateBean> travelAssistantDateList;

    public TravelAssisantSubDateSelectAdapter(Context mContext, TravellerInfoItemListener travellerInfoItemListener) {
        this.mContext = mContext;
        this.travellerInfoItemListener = travellerInfoItemListener;
    }

    public void setTravelAssisantDateSelectList(List<DateBean> dateBeanList, List<TravelAssistantDateBean> travelAssistantDateList) {

        this.dateBeanList = dateBeanList;
        this.travelAssistantDateList = travelAssistantDateList;
    }

    @Override
    public TravelAssisantSubDateSelectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_calendar_sub_item, parent, false);
        return new TravelAssisantSubDateSelectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TravelAssisantSubDateSelectAdapter.ViewHolder holder, int position) {

        try {
            DateBean dateBean = dateBeanList.get(position);

            int size = travelAssistantDateList.size();

            int[] date = dateBean.getSolar();
            int day = date[2];
            String dayStr = String.valueOf(day);
            holder.mTvCalendarDay.setText(dayStr);

            holder.mTvCalendarDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < size; i++) {

                        List<DateBean> dateList = travelAssistantDateList.get(i).getDateList();

                        for (int j = 0; j < dateList.size(); j++) {

                            dateList.get(j).setSelect(false);

                        }
                    }
                    dateBean.setSelect(true);
                    if (dateBean.isStartSelect()) {

                        dateBean.setEndSelect(true);

                    } else {

                        dateBean.setStartSelect(true);
                    }

                    notifyDataSetChanged();
                }

            });


            /*if(dateBean.isStartSelect()){


            }*/


            if (dateBean.isSelect()) {
                holder.mTvCalendarDay.setBackgroundResource(R.drawable.shape_round_gradient);
                holder.mTvCalendarDay.setTextColor(mContext.getResources().getColor(R.color.white));
            } else {
                holder.mTvCalendarDay.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                holder.mTvCalendarDay.setTextColor(mContext.getResources().getColor(R.color.color_text_black2));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        //注意，空布局---> mEmptyType = 1 显示1个布局
        //            ---> mEmptyType = 0 所有布局都不显示的
        //   return dataList != null ? dataList.size() + mEmptyType : mEmptyType;
        return dateBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mEmptyType == EMPTY_VIEW) {
            //空布局的类型
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //日期
        @BindView(R.id.tv_calendar_day)
        TextView mTvCalendarDay;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface TravellerInfoItemListener {

        void setTravellerInfoItemListener();
    }
}