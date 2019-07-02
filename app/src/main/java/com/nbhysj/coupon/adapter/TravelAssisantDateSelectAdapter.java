package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
public class TravelAssisantDateSelectAdapter extends RecyclerView.Adapter<TravelAssisantDateSelectAdapter.ViewHolder> {

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    private Context mContext;
    private List<TravelAssistantDateBean> travelAssistantDateList;
    private TravellerListAdapter.TravellerInfoItemListener travellerInfoItemListener;

    public TravelAssisantDateSelectAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setTravelAssisantDateSelectList(List<TravelAssistantDateBean> travelAssistantDateList) {

        this.travelAssistantDateList = travelAssistantDateList;
    }

    @Override
    public TravelAssisantDateSelectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_calendar_item, parent, false);
        return new TravelAssisantDateSelectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TravelAssisantDateSelectAdapter.ViewHolder holder, int position) {

        try {

            GridLayoutManager linearLayoutManager = new GridLayoutManager(mContext, 7);
            holder.mRvCalendarDate.setLayoutManager(linearLayoutManager);
            TravelAssistantDateBean travelAssistantDateBean = travelAssistantDateList.get(position);
            String date = travelAssistantDateBean.getDate();
            List<DateBean> dateBeanList = travelAssistantDateBean.getDateList();
            holder.mTvCalendarDate.setText(date);

            TravelAssisantSubDateSelectAdapter travelAssisantSubDateSelectAdapter = new TravelAssisantSubDateSelectAdapter(mContext, new TravelAssisantSubDateSelectAdapter.TravellerInfoItemListener() {
                @Override
                public void setTravellerInfoItemListener() {

                }
            });
            travelAssisantSubDateSelectAdapter.setTravelAssisantDateSelectList(dateBeanList, travelAssistantDateList);
            holder.mRvCalendarDate.setAdapter(travelAssisantSubDateSelectAdapter);
            holder.mRvCalendarDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    System.out.print(position + "");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        //注意，空布局---> mEmptyType = 1 显示1个布局
        //            ---> mEmptyType = 0 所有布局都不显示的
        //   return dataList != null ? dataList.size() + mEmptyType : mEmptyType;
        return travelAssistantDateList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mEmptyType == EMPTY_VIEW) {
            //空布局的类型
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);

    }

    @Override
    public long getItemId(int position) {

        return super.getItemId(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //日期
        @BindView(R.id.tv_calendar_date)
        TextView mTvCalendarDate;
        //日历
        @BindView(R.id.rv_calendar_date)
        RecyclerView mRvCalendarDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface TravellerInfoItemListener {

        void setTravellerInfoItemListener(TravellerBean travellerInfo);

        void setTravellerInfoDeleteListener(int travellerId);
    }

}