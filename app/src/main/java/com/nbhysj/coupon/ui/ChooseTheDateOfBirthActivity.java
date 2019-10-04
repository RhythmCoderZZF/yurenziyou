package com.nbhysj.coupon.ui;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.NumericWheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.UserInfoContract;
import com.nbhysj.coupon.model.UserInfoModel;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.presenter.UserInfoPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/08
 * description：选择出生日期
 */
public class ChooseTheDateOfBirthActivity extends BaseActivity<UserInfoPresenter, UserInfoModel> implements UserInfoContract.View {
    @BindView(R.id.rlyt_the_date_of_birth)
    RelativeLayout mRlytTheDateOfBirth;
    //生日
    @BindView(R.id.tv_the_date_of_birth)
    TextView mTvTheDateOfBirth;
    //星座
    @BindView(R.id.tv_constellation)
    TextView mTvConstellation;
    //出生日期
    @BindView(R.id.tv_toolbar_right)
    TextView mTvSaveBirthDay;

    WheelView wv_year, wv_month, wv_day;
    private static final int DEFAULT_START_YEAR = 1900;
    private static final int DEFAULT_END_YEAR = 2100;
    private static final int DEFAULT_START_MONTH = 1;
    private static final int DEFAULT_END_MONTH = 12;
    private static final int DEFAULT_START_DAY = 1;
    private static final int DEFAULT_END_DAY = 31;

    private int startYear = DEFAULT_START_YEAR;
    private int endYear = DEFAULT_END_YEAR;
    private int startMonth = DEFAULT_START_MONTH;
    private int endMonth = DEFAULT_END_MONTH;
    private int startDay = DEFAULT_START_DAY;
    private int endDay = DEFAULT_END_DAY; //表示31天的
    private int currentYear;

    private Calendar date;//当前选中时间
    private Calendar startDate;//开始时间
    private Calendar endDate;//终止时间
    int year, month, day, hours, minute, seconds;
    private String mStartTimeSelectYear, mStartTimeSelectMonth, mStartTimeSelectDay;
    private String birthday;
    String[] birthdayArray;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_choose_the_date_of_birth;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(ChooseTheDateOfBirthActivity.this, getResources().getString(R.string.str_choose_the_date_of_birth), R.mipmap.nav_ico_back_black, getResources().getString(R.string.str_save));

       /* theFirstDayOfMonth = getIntent().getStringExtra("theFirstDayOfMonth");
        theLastDayOfMonth = getIntent().getStringExtra("theLastDayOfMonth");*/
        userId = getSharedPreferencesUserId();
        birthday = getIntent().getStringExtra("birthday");

        if (!TextUtils.isEmpty(birthday)) {
            birthdayArray = birthday.split("-");
            mStartTimeSelectYear = birthdayArray[0];
            mStartTimeSelectMonth = birthdayArray[1];
            mStartTimeSelectDay = birthdayArray[2];
        } else {
            long birthdayTime = new Date().getTime();
            String time = DateUtil.transferLongToDateStr(DateUtil.sDateYMDFormat, birthdayTime);
            birthdayArray = time.split("-");
            mStartTimeSelectYear = birthdayArray[0];
            mStartTimeSelectMonth = birthdayArray[1];
            mStartTimeSelectDay = birthdayArray[2];

            birthday = mStartTimeSelectYear + "-" + mStartTimeSelectMonth + "-" + mStartTimeSelectDay;
        }
        mTvTheDateOfBirth.setText(birthday);
        int selectMonth = Integer.parseInt(mStartTimeSelectMonth);
        int selectDay = Integer.parseInt(mStartTimeSelectDay);
        getAstro(selectMonth, selectDay);

       /* if (!TextUtils.isEmpty(theLastDayOfMonth)) {
            //mCkbEndTime.setText(theLastDayOfMonth);
            String[] lastDayOfMonth = theLastDayOfMonth.split("-");
            mEndTimeSelectYear = lastDayOfMonth[0];
            mEndTimeSelectMonth = lastDayOfMonth[1];
            mEndTimeSelectDay = lastDayOfMonth[2];
        }*/
        if (startDate != null && endDate != null) {
            if (startDate.getTimeInMillis() <= endDate.getTimeInMillis()) {
                setRangDate();
            }
        } else if (startDate != null && endDate == null) {
            setRangDate();
        } else if (startDate == null && endDate != null) {
            setRangDate();
        }
        setTime();
        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
        String[] months_little = {"4", "6", "9", "11"};
        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);
        if (TextUtils.isEmpty(birthday)) {
            currentYear = year;
        } else {
            currentYear = Integer.parseInt(mStartTimeSelectYear);
        }
        // 年
        wv_year = (WheelView) findViewById(com.bigkoo.pickerview.R.id.year);
        wv_year.setAdapter(new NumericWheelAdapter(startYear, endYear));// 设置"年"的显示数据
        wv_year.setLabel(getResources().getString(com.bigkoo.pickerview.R.string.pickerview_year));
        wv_year.isCenterLabel(false);
        wv_year.setTextSize(18);//滚轮文字大小
        wv_year.setCurrentItem(currentYear - startYear);// 初始化时显示的数据
        wv_year.setGravity(Gravity.CENTER);


        // 月
        wv_month = (WheelView) findViewById(com.bigkoo.pickerview.R.id.month);
        if (startYear == endYear) {//开始年等于终止年
            wv_month.setAdapter(new NumericWheelAdapter(startMonth, endMonth));
            wv_month.setCurrentItem(month + 1 - startMonth);
        } else if (year == startYear) {
            //起始日期的月份控制
            wv_month.setAdapter(new NumericWheelAdapter(startMonth, 12));
            wv_month.setCurrentItem(month + 1 - startMonth);
        } else if (year == endYear) {
            //终止日期的月份控制
            wv_month.setAdapter(new NumericWheelAdapter(1, endMonth));
            wv_month.setCurrentItem(month);
        } else {
            wv_month.setAdapter(new NumericWheelAdapter(1, 12));
            if (TextUtils.isEmpty(birthday)) {
                wv_month.setCurrentItem(month);
            } else {
                wv_month.setCurrentItem(selectMonth - 1);
            }
        }
        wv_month.setLabel(getResources().getString(com.bigkoo.pickerview.R.string.pickerview_month));
        wv_month.isCenterLabel(false);
        wv_month.setTextSize(18);//滚轮文字大小
        wv_month.setGravity(Gravity.CENTER);
        // 日
        wv_day = (WheelView) findViewById(com.bigkoo.pickerview.R.id.day);

        if (startYear == endYear && startMonth == endMonth) {
            if (list_big.contains(String.valueOf(month + 1))) {
                if (endDay > 31) {
                    endDay = 31;
                }
                wv_day.setAdapter(new NumericWheelAdapter(startDay, endDay));
            } else if (list_little.contains(String.valueOf(month + 1))) {
                if (endDay > 30) {
                    endDay = 30;
                }
                wv_day.setAdapter(new NumericWheelAdapter(startDay, endDay));
            } else {
                // 闰年
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    if (endDay > 29) {
                        endDay = 29;
                    }
                    wv_day.setAdapter(new NumericWheelAdapter(startDay, endDay));
                } else {
                    if (endDay > 28) {
                        endDay = 28;
                    }
                    wv_day.setAdapter(new NumericWheelAdapter(startDay, endDay));
                }
            }
            wv_day.setCurrentItem(day - startDay);
        } else if (year == startYear && month + 1 == startMonth) {
            // 起始日期的天数控制
            if (list_big.contains(String.valueOf(month + 1))) {

                wv_day.setAdapter(new NumericWheelAdapter(startDay, 31));
            } else if (list_little.contains(String.valueOf(month + 1))) {

                wv_day.setAdapter(new NumericWheelAdapter(startDay, 30));
            } else {
                // 闰年
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {

                    wv_day.setAdapter(new NumericWheelAdapter(startDay, 29));
                } else {

                    wv_day.setAdapter(new NumericWheelAdapter(startDay, 28));
                }
            }
            wv_day.setCurrentItem(day - startDay);
        } else if (year == endYear && month + 1 == endMonth) {
            // 终止日期的天数控制
            if (list_big.contains(String.valueOf(month + 1))) {
                if (endDay > 31) {
                    endDay = 31;
                }
                wv_day.setAdapter(new NumericWheelAdapter(1, endDay));
            } else if (list_little.contains(String.valueOf(month + 1))) {
                if (endDay > 30) {
                    endDay = 30;
                }
                wv_day.setAdapter(new NumericWheelAdapter(1, endDay));
            } else {
                // 闰年
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    if (endDay > 29) {
                        endDay = 29;
                    }
                    wv_day.setAdapter(new NumericWheelAdapter(1, endDay));
                } else {
                    if (endDay > 28) {
                        endDay = 28;
                    }
                    wv_day.setAdapter(new NumericWheelAdapter(1, endDay));
                }
            }
            wv_day.setCurrentItem(day - 1);
        } else {
            // 判断大小月及是否闰年,用来确定"日"的数据
            if (list_big.contains(String.valueOf(month + 1))) {

                wv_day.setAdapter(new NumericWheelAdapter(1, 31));
            } else if (list_little.contains(String.valueOf(month + 1))) {

                wv_day.setAdapter(new NumericWheelAdapter(1, 30));
            } else {
                // 闰年
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {

                    wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                } else {

                    wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                }
            }
            wv_day.setCurrentItem(day - 1);
        }
        wv_day.setLabel(getResources().getString(com.bigkoo.pickerview.R.string.pickerview_day));
        wv_day.isCenterLabel(false);
        wv_day.setTextSize(18);//滚轮文字大小
        wv_day.setGravity(Gravity.CENTER);

        //时间天选择
        wv_day.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                mStartTimeSelectDay = DateUtil.dateDeal(String.valueOf(index + 1));
              /*  if (mCkbStartTime.isChecked()) {
                    mStartTimeSelectDay = DateUtil.dateDeal(String.valueOf(index + 1));
                } else if (mCkbEndTime.isChecked()) {
                    mEndTimeSelectDay = DateUtil.dateDeal(String.valueOf(index + 1));
                }*/
                getSelectTime();
            }
        });


        // 添加"年"监听
        OnItemSelectedListener wheelListener_year = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                int year_num = index + startYear;
                currentYear = year_num;
                mStartTimeSelectYear = String.valueOf(currentYear);
                //Toast.makeText(TimeSelectActivity.this,currentYear+"",Toast.LENGTH_SHORT).show();
                /*if (mCkbStartTime.isChecked()) {
                    mStartTimeSelectYear = String.valueOf(currentYear);
                } else if (mCkbEndTime.isChecked()) {
                    mEndTimeSelectYear = String.valueOf(currentYear);
                }*/

                getSelectTime();
                int currentMonthItem = wv_month.getCurrentItem();//记录上一次的item位置
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (startYear == endYear) {
                    //重新设置月份
                    wv_month.setAdapter(new NumericWheelAdapter(startMonth, endMonth));

                    if (currentMonthItem > wv_month.getAdapter().getItemsCount() - 1) {
                        currentMonthItem = wv_month.getAdapter().getItemsCount() - 1;
                        wv_month.setCurrentItem(currentMonthItem);
                    }

                    int monthNum = currentMonthItem + startMonth;

                    if (startMonth == endMonth) {
                        //重新设置日
                        setReDay(year_num, monthNum, startDay, endDay, list_big, list_little);
                    } else if (monthNum == startMonth) {
                        //重新设置日
                        setReDay(year_num, monthNum, startDay, 31, list_big, list_little);
                    } else {
                        //重新设置日
                        setReDay(year_num, monthNum, 1, 31, list_big, list_little);
                    }
                } else if (year_num == startYear) {//等于开始的年
                    //重新设置月份
                    wv_month.setAdapter(new NumericWheelAdapter(startMonth, 12));

                    if (currentMonthItem > wv_month.getAdapter().getItemsCount() - 1) {
                        currentMonthItem = wv_month.getAdapter().getItemsCount() - 1;
                        wv_month.setCurrentItem(currentMonthItem);
                    }

                    int month = currentMonthItem + startMonth;
                    if (month == startMonth) {

                        //重新设置日
                        setReDay(year_num, month, startDay, 31, list_big, list_little);
                    } else {
                        //重新设置日

                        setReDay(year_num, month, 1, 31, list_big, list_little);
                    }

                } else if (year_num == endYear) {
                    //重新设置月份
                    wv_month.setAdapter(new NumericWheelAdapter(1, endMonth));
                    if (currentMonthItem > wv_month.getAdapter().getItemsCount() - 1) {
                        currentMonthItem = wv_month.getAdapter().getItemsCount() - 1;
                        wv_month.setCurrentItem(currentMonthItem);
                    }
                    int monthNum = currentMonthItem + 1;

                    if (monthNum == endMonth) {
                        //重新设置日
                        setReDay(year_num, monthNum, 1, endDay, list_big, list_little);
                    } else {
                        //重新设置日
                        setReDay(year_num, monthNum, 1, 31, list_big, list_little);
                    }

                } else {
                    //重新设置月份
                    wv_month.setAdapter(new NumericWheelAdapter(1, 12));
                    //重新设置日
                    setReDay(year_num, wv_month.getCurrentItem() + 1, 1, 31, list_big, list_little);

                }

            }
        };
        // 添加"月"监听
        OnItemSelectedListener wheelListener_month = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                int month_num = index + 1;

                if (startYear == endYear) {
                    month_num = month_num + startMonth - 1;

                    if (startMonth == endMonth) {
                        //重新设置日
                        setReDay(currentYear, month_num, startDay, endDay, list_big, list_little);
                    } else if (startMonth == month_num) {

                        //重新设置日
                        setReDay(currentYear, month_num, startDay, 31, list_big, list_little);
                    } else if (endMonth == month_num) {
                        setReDay(currentYear, month_num, 1, endDay, list_big, list_little);
                    } else {
                        setReDay(currentYear, month_num, 1, 31, list_big, list_little);
                    }
                } else if (currentYear == startYear) {
                    month_num = month_num + startMonth - 1;
                    if (month_num == startMonth) {
                        //重新设置日
                        setReDay(currentYear, month_num, startDay, 31, list_big, list_little);
                    } else {
                        //重新设置日
                        setReDay(currentYear, month_num, 1, 31, list_big, list_little);
                    }

                } else if (currentYear == endYear) {
                    if (month_num == endMonth) {
                        //重新设置日
                        setReDay(currentYear, wv_month.getCurrentItem() + 1, 1, endDay, list_big, list_little);
                    } else {
                        setReDay(currentYear, wv_month.getCurrentItem() + 1, 1, 31, list_big, list_little);
                    }

                } else {
                    //重新设置日
                    setReDay(currentYear, month_num, 1, 31, list_big, list_little);

                }
                mStartTimeSelectMonth = DateUtil.dateDeal(String.valueOf(month_num));
              /*  if (mCkbStartTime.isChecked()) {
                    mStartTimeSelectMonth = DateUtil.dateDeal(String.valueOf(month_num));
                } else if (mCkbEndTime.isChecked()) {
                    mEndTimeSelectMonth = DateUtil.dateDeal(String.valueOf(month_num));
                }*/
                getSelectTime();
                //  Toast.makeText(TimeSelectActivity.this,month_num+"",Toast.LENGTH_SHORT).show();

            }
        };
        wv_year.setOnItemSelectedListener(wheelListener_year);
        wv_month.setOnItemSelectedListener(wheelListener_month);
    }

    public void getSelectTime() {
        // showToast(ChooseTheDateOfBirthActivity.this,mStartTimeSelectYear + "-" +mStartTimeSelectMonth + "-" + mStartTimeSelectDay);
        birthday = mStartTimeSelectYear + "-" + mStartTimeSelectMonth + "-" + mStartTimeSelectDay;
        mTvTheDateOfBirth.setText(birthday);
        int month = Integer.parseInt(mStartTimeSelectMonth);
        int day = Integer.parseInt(mStartTimeSelectDay);
        getAstro(month, day);
        /*if (mCkbStartTime.isChecked()) {

            mCkbStartTime.setText(mStartTimeSelectYear + "-" + mStartTimeSelectMonth + "-" + mStartTimeSelectDay);

        } else if (mCkbEndTime.isChecked())
        {
            mCkbEndTime.setText(mEndTimeSelectYear + "-" + mEndTimeSelectMonth + "-" + mEndTimeSelectDay);
        }*/
    }

    private String getAstro(int month, int day) {
        String[] astro = new String[]{"摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座",
                "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};
        int[] arr = new int[]{20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};// 两个星座分割日
        int index = month;
        // 所查询日期在分割日之前，索引-1，否则不变
        if (day < arr[month - 1]) {
            index = index - 1;
        }
        mTvConstellation.setText(astro[index]);
        // 返回索引指向的星座string
        return astro[index];
    }

    @Override
    public void initData() {

        mTvSaveBirthDay.setOnClickListener(v -> {

            String birthDay = mTvTheDateOfBirth.getText().toString().trim();
            updateBirthDay(birthDay);

        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    /**
     * 设置可以选择的时间范围, 要在setTime之前调用才有效果
     */
    private void setRangDate() {
        setRangDate(startDate, endDate);
        //如果设置了时间范围
        if (startDate != null && endDate != null) {
            //判断一下默认时间是否设置了，或者是否在起始终止时间范围内
            if (date == null || date.getTimeInMillis() < startDate.getTimeInMillis()
                    || date.getTimeInMillis() > endDate.getTimeInMillis()) {
                date = startDate;
            }
        } else if (startDate != null) {
            //没有设置默认选中时间,那就拿开始时间当默认时间
            date = startDate;
        } else if (endDate != null) {
            date = endDate;
        }
    }

    /**
     * 设置选中时间,默认选中当前时间
     */
    private void setTime() {


        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            calendar.setTimeInMillis(System.currentTimeMillis());
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            hours = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
            seconds = calendar.get(Calendar.SECOND);
        } else {
            year = date.get(Calendar.YEAR);
            month = date.get(Calendar.MONTH);
            day = date.get(Calendar.DAY_OF_MONTH);
            hours = date.get(Calendar.HOUR_OF_DAY);
            minute = date.get(Calendar.MINUTE);
            seconds = date.get(Calendar.SECOND);
        }


        //  setPicker(year, month, day, hours, minute, seconds);
    }

    public void setRangDate(Calendar startDate, Calendar endDate) {

        if (startDate == null && endDate != null) {
            int year = endDate.get(Calendar.YEAR);
            int month = endDate.get(Calendar.MONTH) + 1;
            int day = endDate.get(Calendar.DAY_OF_MONTH);
            if (year > startYear) {
                this.endYear = year;
                this.endMonth = month;
                this.endDay = day;
            } else if (year == startYear) {
                if (month > startMonth) {
                    this.endYear = year;
                    this.endMonth = month;
                    this.endDay = day;
                } else if (month == startMonth) {
                    if (month > startDay) {
                        this.endYear = year;
                        this.endMonth = month;
                        this.endDay = day;
                    }
                }
            }

        } else if (startDate != null && endDate == null) {
            int year = startDate.get(Calendar.YEAR);
            int month = startDate.get(Calendar.MONTH) + 1;
            int day = startDate.get(Calendar.DAY_OF_MONTH);
            if (year < endYear) {
                this.startMonth = month;
                this.startDay = day;
                this.startYear = year;
            } else if (year == endYear) {
                if (month < endMonth) {
                    this.startMonth = month;
                    this.startDay = day;
                    this.startYear = year;
                } else if (month == endMonth) {
                    if (day < endDay) {
                        this.startMonth = month;
                        this.startDay = day;
                        this.startYear = year;
                    }
                }
            }

        } else if (startDate != null && endDate != null) {
            this.startYear = startDate.get(Calendar.YEAR);
            this.endYear = endDate.get(Calendar.YEAR);
            this.startMonth = startDate.get(Calendar.MONTH) + 1;
            this.endMonth = endDate.get(Calendar.MONTH) + 1;
            this.startDay = startDate.get(Calendar.DAY_OF_MONTH);
            this.endDay = endDate.get(Calendar.DAY_OF_MONTH);
        }
    }

    private void setReDay(int year_num, int monthNum, int startD, int endD, List<String> list_big, List<String> list_little) {
        int currentItem = wv_day.getCurrentItem();

        int maxItem;
        if (list_big
                .contains(String.valueOf(monthNum))) {
            if (endD > 31) {
                endD = 31;
            }
            wv_day.setAdapter(new NumericWheelAdapter(startD, endD));
            maxItem = endD;
        } else if (list_little.contains(String.valueOf(monthNum))) {
            if (endD > 30) {
                endD = 30;
            }
            wv_day.setAdapter(new NumericWheelAdapter(startD, endD));
            maxItem = endD;
        } else {
            if ((year_num % 4 == 0 && year_num % 100 != 0)
                    || year_num % 400 == 0) {
                if (endD > 29) {
                    endD = 29;
                }
                wv_day.setAdapter(new NumericWheelAdapter(startD, endD));
                maxItem = endD;
            } else {
                if (endD > 28) {
                    endD = 28;
                }
                wv_day.setAdapter(new NumericWheelAdapter(startD, endD));
                maxItem = endD;
            }
        }
        //Toast.makeText(MainActivity.this,currentItem+"",Toast.LENGTH_SHORT).show();
        if (currentItem > wv_day.getAdapter().getItemsCount() - 1) {
            currentItem = wv_day.getAdapter().getItemsCount() - 1;
            wv_day.setCurrentItem(currentItem);
        }
    }

    @Override
    public void getUserInfoResult(BackResult<UserInfoResponse> res) {

    }

    @Override
    public void getThirdPartyLoginStatusResult(BackResult<ThirdPartyLoginStatusResponse> res) {

    }

    @Override
    public void updateInformationResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    Intent intent = new Intent();
                    intent.putExtra("birthday", birthday);
                    setResult(RESULT_OK, intent);
                    SharedPreferencesUtils.putData(SharedPreferencesUtils.USER_BIRTHDAY, birthday);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ChooseTheDateOfBirthActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void userLogoutResult(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(ChooseTheDateOfBirthActivity.this, Constants.getResultMsg(msg));
    }

    //修改生日
    public void updateBirthDay(String birthday) {

        if (validateInternet()) {

            UpdateUserInfoRequest updateUserInfoRequest = new UpdateUserInfoRequest();
            updateUserInfoRequest.setBirthday(birthday);
            updateUserInfoRequest.setId(userId);
            showProgressDialog(ChooseTheDateOfBirthActivity.this);
            mDialog.setTitle(getResources().getString(R.string.str_saving));
            mPresenter.updateInformation(updateUserInfoRequest);
        }
    }
}
