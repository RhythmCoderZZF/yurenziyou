package com.nbhysj.coupon.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.GoodsPriceDatesResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.widget.calendar.CalendarList;
import com.nbhysj.coupon.widget.calendar.ticketcalendar.TicketSelectCalendarList;
import com.nbhysj.coupon.widget.calendar.ticketcalendar.TicketSelectDateBean;

import java.util.List;

/**
 * @auther：hysj created on 2019/08/21
 * description：订单日历日期选择
 */
public class OrderSubmitDatePickerDialog extends DialogFragment {
    private Context context;
    private View view;
    OrderSubmitDatePickerListener orderSubmitDatePickerListener;
    //开始时间
    private String mStartDate;
    //结束时间
    private String mEndDate;

    private List<GoodsPriceDatesResponse> goodsPriceList;

    public OrderSubmitDatePickerDialog() {

    }

    @SuppressLint("ValidFragment")
    public void setDataStatisticsDatePickerDialog(OrderSubmitDatePickerListener orderSubmitDatePickerListener, List<GoodsPriceDatesResponse> goodsPriceList) {

        this.orderSubmitDatePickerListener = orderSubmitDatePickerListener;
        this.goodsPriceList = goodsPriceList;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        context = getActivity();
        initView();
        Dialog dialog = new Dialog(context, R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        // 设置宽度为屏宽、靠近屏幕底部
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    private void initView() {

        view = LayoutInflater.from(context).inflate(R.layout.layout_data_statistics_date_picker_dialog, null);
        RelativeLayout mRlytCalendarSelect = view.findViewById(R.id.rlyt_calendar_select);

        ImageView mImgTicketDateSelectCancel = view.findViewById(R.id.img_ticket_date_select_cancel);

        mRlytCalendarSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        mImgTicketDateSelectCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });
        TicketSelectCalendarList calendarList = view.findViewById(R.id.calendar_list);
        calendarList.setGoodsPriceList(goodsPriceList);
        calendarList.setOnDateSelected(new TicketSelectCalendarList.OnDateSelected() {
            @Override
            public void selected(String startDate, String endDate) {
                try {

               //     mTvCalendarDateSelect.setText(startDateForYear + "年" + startDateForMonth + "月" + startDateForDay + "日" + " - " + endDateForYear + "年" + endDateForMonth + "月" + endDateForDay + "日");
                    mStartDate = startDate;
                    mEndDate = endDate;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void singleDateSelected(String ticketSelectDate) {
                mStartDate = ticketSelectDate;
                mEndDate = "";
                    for (int i = 0; i < goodsPriceList.size();i++){
                        String goodPriceDate = goodsPriceList.get(i).getDate();
                        if(goodPriceDate.equals(ticketSelectDate))
                        {
                           GoodsPriceDatesResponse goodsPriceDatesResponse = goodsPriceList.get(i);
                            orderSubmitDatePickerListener.setTickeDatePickerSelectListener(goodsPriceDatesResponse);
                        }
                }

                dismiss();
            }
        });



    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //KeyBoardUtils.closeKeybord((Activity) context);
        super.show(manager, tag);
    }

    public interface OrderSubmitDatePickerListener {

        void setDataStatisticsDatePickerListener(String startDate, String endDate);

        void setTickeDatePickerSelectListener(GoodsPriceDatesResponse goodsPriceDatesResponse);
    }

    public void setDatePickerClear() {

        this.mStartDate = "";
        this.mEndDate = "";
    }
}