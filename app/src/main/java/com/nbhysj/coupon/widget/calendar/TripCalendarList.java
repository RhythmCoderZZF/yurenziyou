package com.nbhysj.coupon.widget.calendar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.GoodsPriceDatesResponse;
import com.nbhysj.coupon.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.nbhysj.coupon.view.RoundedImageView.TAG;

public class TripCalendarList extends FrameLayout {
    RecyclerView mRvTicketDateSelect;
    CalendarAdapter adapter;
    DateBean startDate;//开始时间
    private DateBean endDate;//结束时间
    OnDateSelected onDateSelected;//选中监听
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    List<GoodsPriceDatesResponse> goodsPriceList;

    public TripCalendarList(Context context) {
        super(context);
        init(context);
    }

    public TripCalendarList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TripCalendarList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        addView(LayoutInflater.from(context).inflate(R.layout.item_calendar, this, false));

        mRvTicketDateSelect = findViewById(R.id.rv_ticket_date_select);
        adapter = new CalendarAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 7);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                if (DateBean.item_type_month == adapter.data.get(i).getItemType()) {
                    return 7;
                } else {
                    return 1;
                }
            }
        });
        mRvTicketDateSelect.setLayoutManager(gridLayoutManager);
        mRvTicketDateSelect.setAdapter(adapter);
        adapter.data.addAll(days("", ""));

//        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
//        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.shape));
//        recyclerView.addItemDecoration(dividerItemDecoration);

        MyItemD myItemD = new MyItemD();
        mRvTicketDateSelect.addItemDecoration(myItemD);

        adapter.setOnRecyclerviewItemClick(new OnRecyclerviewItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                onClick(adapter.data.get(position));
            }
        });
    }

    private void onClick(DateBean dateBean) {

        if (dateBean.getItemType() == DateBean.item_type_month || TextUtils.isEmpty(dateBean.getDay())) {
            return;
        }

        //如果没有选中开始日期则此次操作选中开始日期
        if (startDate == null) {
            startDate = dateBean;
            dateBean.setItemState(DateBean.ITEM_STATE_BEGIN_DATE);
            if (onDateSelected != null) {
                onDateSelected.singleDateSelected(simpleDateFormat.format(startDate.getDate()));
            }
        } else if (endDate == null) {
            //如果选中了开始日期但没有选中结束日期，本次操作选中结束日期

            //如果当前点击的结束日期跟开始日期一致 则不做操作
            if (startDate == dateBean) {

            } else if (dateBean.getDate().getTime() < startDate.getDate().getTime()) {
                //当前点选的日期小于当前选中的开始日期 则本次操作重新选中开始日期
                startDate.setItemState(DateBean.ITEM_STATE_NORMAL);
                startDate = dateBean;
                startDate.setItemState(DateBean.ITEM_STATE_BEGIN_DATE);

            } else {
                //选中结束日期
                endDate = dateBean;
                endDate.setItemState(DateBean.ITEM_STATE_END_DATE);
                setState();

                if (onDateSelected != null) {
                    onDateSelected.selected(simpleDateFormat.format(startDate.getDate()), simpleDateFormat.format(endDate.getDate()));
                }
            }

        } else if (startDate != null && endDate != null) {
            //结束日期和开始日期都已选中
            clearState();

            //重新选择开始日期,结束日期清除
            startDate.setItemState(DateBean.ITEM_STATE_NORMAL);
            startDate = dateBean;
            startDate.setItemState(DateBean.ITEM_STATE_BEGIN_DATE);

            endDate.setItemState(DateBean.ITEM_STATE_NORMAL);
            endDate = null;
        }
        if (onDateSelected != null) {
            onDateSelected.singleDateSelected(simpleDateFormat.format(startDate.getDate()));
        }
        adapter.notifyDataSetChanged();
    }

    //选中中间的日期
    private void setState() {
        if (endDate != null && startDate != null) {
            int start = adapter.data.indexOf(startDate);
            DateBean startBean = adapter.data.get(start);
            startBean.setBeginTimeSelect(true);
            start += 1;
            int end = adapter.data.indexOf(endDate);
            DateBean endBean = adapter.data.get(end);
            endBean.setBeginTimeSelect(true);
            for (; start < end; start++) {

                DateBean dateBean = adapter.data.get(start);
                if (!TextUtils.isEmpty(dateBean.getDay())) {
                    dateBean.setItemState(DateBean.ITEM_STATE_SELECTED);
                }
            }
        }
    }

    //取消选中状态
    private void clearState() {
        if (endDate != null && startDate != null) {
            int start = adapter.data.indexOf(startDate);
            DateBean startBean = adapter.data.get(start);
            startBean.setBeginTimeSelect(false);
            start += 1;
            int end = adapter.data.indexOf(endDate);
            DateBean endBean = adapter.data.get(end);
            endBean.setBeginTimeSelect(false);
            for (; start < end; start++) {
                DateBean dateBean = adapter.data.get(start);
                dateBean.setItemState(DateBean.ITEM_STATE_NORMAL);
                dateBean.setBeginTimeSelect(false);
            }
        }
    }

    //日历adapter
    public class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        ArrayList<DateBean> data = new ArrayList<DateBean>();
        OnRecyclerviewItemClick onRecyclerviewItemClick;
        List<GoodsPriceDatesResponse> goodsPriceList;

      /*  public void setGoodPrice(List<GoodsPriceDatesResponse> goodsPriceList) {

            this.goodsPriceList = goodsPriceList;

            for (int i = 0; i < data.size(); i++) {
                DateBean dateBean = data.get(i);
                String dateStr = dateBean.getDateStr();
                for (int j = 0; j < goodsPriceList.size(); j++) {
                    String goodPriceDate = goodsPriceList.get(j).getDate();
                    int mTicketPrice = goodsPriceList.get(j).getPrice();
                    if (dateStr != null) {
                        if (dateStr.equals(goodPriceDate)) {
                            data.get(i).setSellStatus(1);
                            data.get(i).setTicketPrice(mTicketPrice);
                        }
                    }
                }
            }
        }*/

        public OnRecyclerviewItemClick getOnRecyclerviewItemClick() {
            return onRecyclerviewItemClick;
        }

        public void setOnRecyclerviewItemClick(OnRecyclerviewItemClick onRecyclerviewItemClick) {
            this.onRecyclerviewItemClick = onRecyclerviewItemClick;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            if (i == DateBean.item_type_day) {
                View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_day, viewGroup, false);

                final CalendarAdapter.DayViewHolder dayViewHolder = new CalendarAdapter.DayViewHolder(rootView);
                dayViewHolder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onRecyclerviewItemClick != null) {
                            onRecyclerviewItemClick.onItemClick(v, dayViewHolder.getLayoutPosition());
                        }
                    }
                });
                return dayViewHolder;
            } else if (i == DateBean.item_type_month) {
                View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_month, viewGroup, false);
                final CalendarAdapter.MonthViewHolder monthViewHolder = new CalendarAdapter.MonthViewHolder(rootView);
                monthViewHolder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onRecyclerviewItemClick != null) {
                            onRecyclerviewItemClick.onItemClick(v, monthViewHolder.getLayoutPosition());
                        }
                    }
                });
                return monthViewHolder;
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            if (viewHolder instanceof CalendarAdapter.MonthViewHolder) {
                try {
                    String month = data.get(i).getMonthStr();
                    String yymm = DateUtil.toYYMM(month);
                    ((CalendarAdapter.MonthViewHolder) viewHolder).tv_month.setText(yymm);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                CalendarAdapter.DayViewHolder vh = ((CalendarAdapter.DayViewHolder) viewHolder);
                vh.tv_day.setText(data.get(i).getDay());
                DateBean dateBean = data.get(i);

                //设置item状态
                if (dateBean.getItemState() == DateBean.ITEM_STATE_BEGIN_DATE || dateBean.getItemState() == DateBean.ITEM_STATE_END_DATE) {
                    //开始日期或结束日期
                    if (dateBean.isBeginTimeSelect == true && dateBean.getItemState() == DateBean.ITEM_STATE_BEGIN_DATE) {
                        //  vh.tv_check_in_check_out.setText("离店");
                        vh.mLlytDayItem.setBackgroundResource(R.drawable.bg_rectangle_left_blue_and_green_gradient);
                        vh.mLlytDayItem.getBackground().setAlpha(80);
                        vh.mRlytDayItem.setBackgroundResource(R.drawable.shape_round_gradient);
                        vh.tv_day.setTextColor(Color.WHITE);
                        //  vh.mRlytDayItem.getBackground().setAlpha(80);
                    } else if (dateBean.isBeginTimeSelect == true && dateBean.getItemState() == DateBean.ITEM_STATE_END_DATE) {
                        //vh.tv_check_in_check_out.setText("入住");
                        vh.mLlytDayItem.setBackgroundResource(R.drawable.bg_rectangle_right_blue_and_green_gradient);
                        // vh.itemViitemViewew.setBackgroundResource(R.drawable.bg_rectangle_right_blue_and_green_gradient);
                        vh.mLlytDayItem.getBackground().setAlpha(80);
                        vh.mRlytDayItem.setBackgroundResource(R.drawable.shape_round_gradient);
                        vh.tv_day.setTextColor(Color.WHITE);
                    } else {
                        //vh.mLlytDayItem.setBackgroundColor(Color.WHITE);
                        vh.tv_day.setTextColor(Color.WHITE);
                        vh.mRlytDayItem.setBackgroundResource(R.drawable.shape_round_gradient);
                    }

                } else if (dateBean.getItemState() == DateBean.ITEM_STATE_SELECTED) {
                    //选中状态
                    vh.tv_day.setTextColor(Color.parseColor("#FF302F2F"));
                    vh.mLlytDayItem.setBackgroundColor(Color.parseColor("#FF21BEAE"));
                    vh.mLlytDayItem.getBackground().setAlpha(80);
                    vh.mRlytDayItem.setBackgroundColor(Color.TRANSPARENT);
                    // vh.itemView.setBackgroundColor(Color.parseColor("#ffa500"));
                    // vh.tv_day.setTextColor(Color.WHITE);
                } else if (dateBean.getItemState() == DateBean.ITEM_STATE_NORMAL) {
                    //正常状态
                    vh.mLlytDayItem.setBackgroundColor(Color.TRANSPARENT);
                    vh.mRlytDayItem.setBackgroundColor(Color.TRANSPARENT);
                    vh.tv_day.setTextColor(Color.BLACK);
                }

                String tripSelectDateStr = data.get(i).getDateStr();
                try {
                    java.util.Date nowdate = new java.util.Date();

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(nowdate);
                    calendar.add(Calendar.DAY_OF_MONTH, 0);
                    Date yesterdayDate = calendar.getTime();//这是昨天

                    long nowDateLong = yesterdayDate.getTime();
                    String nowDateStr = DateUtil.transferLongToDateStr(DateUtil.sDateYMDFormat, nowDateLong);
                    Date nowDate = DateUtil.getDateStrToDate(nowDateStr, DateUtil.sDateYMDFormat);
                    if(!TextUtils.isEmpty(tripSelectDateStr))
                    {
                        Date tripDate = DateUtil.getDateStrToDate(tripSelectDateStr, DateUtil.sDateYMDFormat);
                        int flag = tripDate.compareTo(nowDate);
                        if (flag >= 0) {//当天及当天之后，<0就是在日期之前

                            vh.tv_day.setTextColor(Color.BLACK);
                            vh.itemView.setClickable(true);
                         //   vh.mRlytDayItem.setClickable(false);
                            //vh.mRlytDayItem.setClickable(false);
                          //  vh.tv_day.setClickable(false);

                        } else {
                            vh.itemView.setClickable(false);
                           // vh.mRlytDayItem.setClickable(true);
                          // vh.tv_day.setClickable(true);
                            vh.tv_day.setTextColor(getResources().getColor(R.color.gray));
                        }
                    }
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            return data.get(position).getItemType();
        }

        public class DayViewHolder extends RecyclerView.ViewHolder {
            TextView tv_day;
            TextView mTvTicketPrice;
            RelativeLayout mRlytDayItem;
            LinearLayout mLlytDayItem;
            //购票日期选择弹框取消
            ImageView mImgTicketDateSelectCancel;


            public DayViewHolder(@NonNull View itemView) {
                super(itemView);

                tv_day = itemView.findViewById(R.id.tv_day);
                mTvTicketPrice = itemView.findViewById(R.id.tv_ticket_price);
                mRlytDayItem = itemView.findViewById(R.id.rlyt_day_item);
                mLlytDayItem = itemView.findViewById(R.id.llyt_day_item);

            }
        }

        public class MonthViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_month;

            public MonthViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_month = itemView.findViewById(R.id.tv_month);
            }
        }

    }

    public interface OnRecyclerviewItemClick {
        void onItemClick(View v, int position);
    }

    /**
     * 生成日历数据
     */
    private List<DateBean> days(String sDate, String eDate) {
        List<DateBean> dateBeans = new ArrayList<>();
        try {
            Calendar calendar = Calendar.getInstance();
            //日期格式化
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatYYYYMM = new SimpleDateFormat("yyyy-MM");

            //起始日期
            Date startDate = new Date();
            calendar.setTime(startDate);

            //结束日期
            calendar.add(Calendar.MONTH, 3);
            Date endDate = new Date(calendar.getTimeInMillis());

            Log.d(TAG, "startDate:" + format.format(startDate) + "----------endDate:" + format.format(endDate));

            //格式化开始日期和结束日期为 yyyy-mm-dd格式
            String endDateStr = format.format(endDate);
            endDate = format.parse(endDateStr);

            String startDateStr = format.format(startDate);
            startDate = format.parse(startDateStr);

            calendar.setTime(startDate);

            Log.d(TAG, "startDateStr:" + startDateStr + "---------endDate:" + format.format(endDate));
            Log.d(TAG, "endDateStr:" + endDateStr + "---------endDate:" + format.format(endDate));

            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Calendar monthCalendar = Calendar.getInstance();


            //按月生成日历 每行7个 最多6行 42个
            //每一行有七个日期  日 一 二 三 四 五 六 的顺序
            for (; calendar.getTimeInMillis() <= endDate.getTime(); ) {

                //月份item
                DateBean monthDateBean = new DateBean();
                monthDateBean.setDate(calendar.getTime());
                monthDateBean.setMonthStr(formatYYYYMM.format(monthDateBean.getDate()));
                monthDateBean.setItemType(DateBean.item_type_month);
                dateBeans.add(monthDateBean);

                //获取一个月结束的日期和开始日期
                monthCalendar.setTime(calendar.getTime());
                monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
                Date startMonthDay = calendar.getTime();

                monthCalendar.add(Calendar.MONTH, 1);
                monthCalendar.add(Calendar.DAY_OF_MONTH, -1);
                Date endMonthDay = monthCalendar.getTime();

                //重置为本月开始
                monthCalendar.set(Calendar.DAY_OF_MONTH, 1);

                Log.d(TAG, "月份的开始日期:" + format.format(startMonthDay) + "---------结束日期:" + format.format(endMonthDay));
                for (; monthCalendar.getTimeInMillis() <= endMonthDay.getTime(); ) {
                    //生成单个月的日历

                    //处理一个月开始的第一天
                    if (monthCalendar.get(Calendar.DAY_OF_MONTH) == 1) {
                        //看某个月第一天是周几
                        int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
                        switch (weekDay) {
                            case 1:
                                //周日
                                break;
                            case 2:
                                //周一
                                addDatePlaceholder(dateBeans, 1, monthDateBean.getMonthStr());
                                break;
                            case 3:
                                //周二
                                addDatePlaceholder(dateBeans, 2, monthDateBean.getMonthStr());
                                break;
                            case 4:
                                //周三
                                addDatePlaceholder(dateBeans, 3, monthDateBean.getMonthStr());
                                break;
                            case 5:
                                //周四
                                addDatePlaceholder(dateBeans, 4, monthDateBean.getMonthStr());
                                break;
                            case 6:
                                addDatePlaceholder(dateBeans, 5, monthDateBean.getMonthStr());
                                //周五
                                break;
                            case 7:
                                addDatePlaceholder(dateBeans, 6, monthDateBean.getMonthStr());
                                //周六
                                break;
                        }
                    }

                    //生成某一天日期实体 日item
                    DateBean dateBean = new DateBean();
                    dateBean.setDate(monthCalendar.getTime());
                    dateBean.setDay(monthCalendar.get(Calendar.DAY_OF_MONTH) + "");
                    dateBean.setMonthStr(monthDateBean.getMonthStr());
                    String dateStr = DateUtil.getTime(monthCalendar.getTime(), DateUtil.sDateYMDFormat);
                    dateBean.setDateStr(dateStr);
                    dateBeans.add(dateBean);

                    //处理一个月的最后一天
                    if (monthCalendar.getTimeInMillis() == endMonthDay.getTime()) {
                        //看某个月第一天是周几
                        int weekDay = monthCalendar.get(Calendar.DAY_OF_WEEK);
                        switch (weekDay) {
                            case 1:
                                //周日
                                addDatePlaceholder(dateBeans, 6, monthDateBean.getMonthStr());
                                break;
                            case 2:
                                //周一
                                addDatePlaceholder(dateBeans, 5, monthDateBean.getMonthStr());
                                break;
                            case 3:
                                //周二
                                addDatePlaceholder(dateBeans, 4, monthDateBean.getMonthStr());
                                break;
                            case 4:
                                //周三
                                addDatePlaceholder(dateBeans, 3, monthDateBean.getMonthStr());
                                break;
                            case 5:
                                //周四
                                addDatePlaceholder(dateBeans, 2, monthDateBean.getMonthStr());
                                break;
                            case 6:
                                addDatePlaceholder(dateBeans, 1, monthDateBean.getMonthStr());
                                //周5
                                break;
                        }
                    }

                    //天数加1
                    monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
                }

                Log.d(TAG, "日期" + format.format(calendar.getTime()) + "----周几" + getWeekStr(calendar.get(Calendar.DAY_OF_WEEK) + ""));
                //月份加1
                calendar.add(Calendar.MONTH, 1);
            }

        } catch (Exception ex) {

        }

        return dateBeans;
    }

    //添加空的日期占位
    private void addDatePlaceholder(List<DateBean> dateBeans, int count, String monthStr) {
        for (int i = 0; i < count; i++) {
            DateBean dateBean = new DateBean();
            dateBean.setMonthStr(monthStr);
            dateBeans.add(dateBean);
        }
    }

    private String getWeekStr(String mWay) {
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return mWay;
    }

    public interface OnDateSelected {
        void selected(String startDate, String endDate);

        void singleDateSelected(String startDate);
    }

    public void setOnDateSelected(OnDateSelected onDateSelected) {
        this.onDateSelected = onDateSelected;
    }

   /* public void setGoodsPriceList(List<GoodsPriceDatesResponse> goodsPriceList) {

        this.goodsPriceList = goodsPriceList;
        adapter.setGoodPrice(goodsPriceList);
        adapter.notifyDataSetChanged();

    }*/
}
