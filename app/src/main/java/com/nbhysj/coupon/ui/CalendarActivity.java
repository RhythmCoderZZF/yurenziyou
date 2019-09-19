package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.dialog.CollectEnterAlbumsDialog;
import com.nbhysj.coupon.dialog.TravelAssistantAddDialog;
import com.nbhysj.coupon.model.response.CollectionAlbumListResponse;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.nbhysj.coupon.util.blurbehind.OnBlurCompleteListener;
import com.nbhysj.coupon.widget.calendar.CalendarList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/24
 * description：日历
 */
public class CalendarActivity extends BaseActivity {

    //日历选择
    @BindView(R.id.tv_calendar_date_select)
    TextView mTvCalendarDateSelect;

    //开始时间
    private String mStartDate;

    //结束时间
    private String mEndDate;

    private int selectType;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_calendar;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(CalendarActivity.this, getResources().getString(R.string.str_calendar_select), R.mipmap.icon_left_arrow_black);
        selectType = getIntent().getIntExtra("selectType", 0);  //0:创建行程 || 1:编辑行程选择日期 || 酒店民宿下单日期选择
        CalendarList calendarList = findViewById(R.id.calendar_list);
        calendarList.setOnDateSelected(new CalendarList.OnDateSelected() {
            @Override
            public void selected(String startDate, String endDate) {
                try {

                    mStartDate = startDate;
                    mEndDate = endDate;
                    String[] startDateArray = startDate.split("-");
                    String[] endDateArray = endDate.split("-");

                    String startDateForYear = startDateArray[0];
                    String startDateForMonth = startDateArray[1];
                    String startDateForDay = startDateArray[2];

                    String endDateForYear = endDateArray[0];
                    String endDateForMonth = endDateArray[1];
                    String endDateForDay = endDateArray[2];

                    mTvCalendarDateSelect.setText(startDateForYear + "年" + startDateForMonth + "月" + startDateForDay + "日" + " - " + endDateForYear + "年" + endDateForMonth + "月" + endDateForDay + "日");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void singleDateSelected(String startDate) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.tv_next})
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_next:

                if (!TextUtils.isEmpty(mStartDate) && !TextUtils.isEmpty(mEndDate)) {
                    intent.putExtra("startDate", mStartDate);
                    intent.putExtra("endDate", mEndDate);
                    if (selectType == 0) {
                        intent.setClass(CalendarActivity.this, TravelAssistanSelectCountyActivity.class);
                        startActivity(intent);
                    } else if (selectType == 1) {
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                } else {

                    showToast(CalendarActivity.this, "请选择时间区间");
                }
               /* BlurBehind.getInstance().execute(CalendarActivity.this, new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {

                        Intent intent = new Intent(CalendarActivity.this, MyBusinessCardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                        startActivity(intent);
                    }
                });
*/
                //TravelAssistantAddDialog travelAssistantAddDialog = new TravelAssistantAddDialog();
                // travelAssistantAddDialog.show(getFragmentManager(), "行程助手添加");
                break;
            default:
                break;
        }
    }
}
