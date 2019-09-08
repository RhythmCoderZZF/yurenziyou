package com.nbhysj.coupon.util;

import android.text.TextUtils;
import android.util.Log;

import com.nbhysj.coupon.model.response.GoodsPriceDatesResponse;
import com.nbhysj.coupon.model.response.VehicleUseTimeResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @auther：hysj created on 2019/03/13
 * description：日期工具类
 */
public class DateUtil {
    private final static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
    public final static String sDateYMDFormat = "yyyy-MM-dd";

    public final static String sDateYMDHHMMSSFormat = "yyyy-MM-dd HH:mm:ss";
    public final static String sDateHHMMSSFormat = "HH:mm:ss";
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    private static StringBuffer stringBuffer = new StringBuffer();

    /**
     * 日期小于10的数字 添加首位添加0 保持两位数
     *
     * @param dateStr
     */
    public static String dateDeal(String dateStr) {
        String dateNewStr = "";
        if (!TextUtils.isEmpty(dateStr)) {
            if (dateStr.length() == 1) {
                dateNewStr = "0" + dateStr;
            } else {
                dateNewStr = dateStr;
            }
        }
        return dateNewStr;
    }


    public static String getTheCurrentDayOfMonth() {
        Date date = new Date();
        String dateStr = sFormat.format(date);
        return dateStr;
    }


    public static String getTheFirstDayOfMonth() {
        //获取当前月第一天：
        Calendar calstr = Calendar.getInstance();
        //本月
        calstr.add(Calendar.MONTH, 0);
        //设置为1号为本月第一天 
        calstr.set(Calendar.DAY_OF_MONTH, 1);
        String first = sFormat.format(calstr.getTime());
        System.out.println("当月第一天:" + first);
        return first;
    }

    public static String getTheLastDayOfMonth() {
        //获取当前月最后一天
        Calendar calast = Calendar.getInstance();
        //设置当月为最后一天
        calast.set(Calendar.DAY_OF_MONTH, calast.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = sFormat.format(calast.getTime());
        System.out.println("当月最后一天:" + last);
        return last;
    }


    //String 转 Date
    public static Date getDateStrToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    //String 转 long
    public static long getDateStrToLong(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        long time = date.getTime() / 1000;
        return time;
    }

    //Date转String
    public static String getTime(Date date, String dateFormat) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }

    /**
     * 把long 转换成 日期 再转换成String类型
     */
    public static String transferLongToDateStr(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec * 1000);
        return sdf.format(date);
    }

    public static boolean timeMoreThan1day(long startDate, long endDate) {
        try {

            //判断是否大于1天
            if (((endDate - startDate) / (24 * 60 * 60 * 1000)) >= 1) {
                return true;
            } else {

                return false;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static boolean timeMoreThan1day(String startDate, String endDate) {
        //格式化时间
        SimpleDateFormat CurrentTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {

            Date beginTime = CurrentTime.parse(startDate);
            Date endTime = CurrentTime.parse(endDate);
            //判断是否大于两天
            if (((endTime.getTime() - beginTime.getTime()) / (24 * 60 * 60 * 1000)) >= 1) {
                return true;
            } else {
                Log.v("hi", "小于两天");
                return false;
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static String formatDateToYMD(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        String formatStr = "";
        try {
            formatStr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr;
    }

    public static String formatDateToMD(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat("MM-dd");
        String formatStr = "";
        try {
            formatStr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr;
    }


    public static String dateFormat(Date date) {
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

    /**
     * 年月日
     *
     * @param dateStr
     * @return
     * @throws java.text.ParseException
     */
    public static String toYYMMDD(String dateStr) throws java.text.ParseException {
        try {
            String date;
            if (!TextUtils.isEmpty(dateStr)) {
                String[] dateArray = dateStr.split("-");
                String year = dateArray[0];
                String month = dateArray[1];
                String day = dateArray[2];
                date = year + "年" + month + "月" + day + "日";
                return date;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 月日
     *
     * @param dateStr
     * @return
     * @throws java.text.ParseException
     */
    public static String toMMDD(String dateStr) throws java.text.ParseException {
        try {
            String date;
            if (!TextUtils.isEmpty(dateStr)) {
                String[] dateArray = dateStr.split("-");
                String month = dateArray[1];
                String day = dateArray[2];
                date = month + "-" + day;
                return date;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 月日
     * @param dateStr
     * @return
     * @throws java.text.ParseException
     */
    public static String toMMDDStr(String dateStr) throws java.text.ParseException {
        try {
            String date;
            if (!TextUtils.isEmpty(dateStr)) {
                String[] dateArray = dateStr.split("-");
                String month = dateArray[1];
                String day = dateArray[2];
                date = month + "月" + day + "日";
                return date;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String dateToWeek(String datetime) throws java.text.ParseException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        datet = (Date) f.parse(datetime);
        cal.setTime(datet);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        System.out.println(weekDays[w]);//星期二
        return weekDays[w];
    }

    public static String dateToWeek2(String datetime) throws java.text.ParseException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        datet = (Date) f.parse(datetime);
        cal.setTime(datet);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        System.out.println(weekDays[w]);//星期二
        return weekDays[w];
    }

    //获取星期几
    public static String getWeek() {
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    /**
     * 获取提交时间
     *
     * @return
     */
    public static List<GoodsPriceDatesResponse> getOrderSubmitDate() {
        List<GoodsPriceDatesResponse> dateList = null;
        try {
            Date date = new Date();//取时间
            String currentDate = getTime(date, sDateYMDFormat);
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(date); //需要将date数据转移到Calender对象中操作
            calendar.add(calendar.DATE, 2);//把日期往后增加n天.正数往后推,负数往前移动
            date = calendar.getTime();   //这个时间就是日期往后推一天的结果
            String twoDaysLaterDate = getTime(date, sDateYMDFormat);

            dateList = findDates(currentDate, twoDaysLaterDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateList;
    }

    /**
     * 根据起始时间和结束时间 计算时间段
     *
     * @param dBegin
     * @param dEnd
     * @return
     * @throws ParseException
     */
    public static List<GoodsPriceDatesResponse> findDates(String dBegin, String dEnd) throws ParseException {
        List<GoodsPriceDatesResponse> datelist = null;

        try {
            //日期工具类准备
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            //设置开始时间
            Calendar calBegin = Calendar.getInstance();
            calBegin.setTime(format.parse(dBegin));

            //设置结束时间
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(format.parse(dEnd));

            //装返回的日期集合容器
            datelist = new ArrayList<GoodsPriceDatesResponse>();
            GoodsPriceDatesResponse goodsPriceDatesResponse = new GoodsPriceDatesResponse();
            goodsPriceDatesResponse.setDate(dBegin);
            datelist.add(goodsPriceDatesResponse);
            // 每次循环给calBegin日期加一天，直到calBegin.getTime()时间等于dEnd
            while (format.parse(dEnd).after(calBegin.getTime())) {
                // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                GoodsPriceDatesResponse aftergoodsPriceDates = new GoodsPriceDatesResponse();
                calBegin.add(Calendar.DAY_OF_MONTH, 1);

                String time = format.format(calBegin.getTime());
                aftergoodsPriceDates.setDate(time);
                datelist.add(aftergoodsPriceDates);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datelist;
    }

    /**
     * 获取用车时间
     * @return
     */
    public static List<VehicleUseTimeResponse> getVehicleUseTime(){
        List<VehicleUseTimeResponse> dateList = null;
        try {
            Date date = new Date();//取时间
            String currentDate = getTime(date, sDateYMDFormat);
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(date); //需要将date数据转移到Calender对象中操作
            calendar.add(calendar.DATE, 4);//把日期往后增加n天.正数往后推,负数往前移动
            date = calendar.getTime();   //这个时间就是日期往后推一天的结果
            String twoDaysLaterDate = getTime(date, sDateYMDFormat);

            dateList = findVehicleUseDates(currentDate, twoDaysLaterDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateList;
    }

    /**
     * 根据起始时间和结束时间 计算时间段
     *
     * @param dBegin
     * @param dEnd
     * @return
     * @throws ParseException
     */
    public static List<VehicleUseTimeResponse> findVehicleUseDates(String dBegin, String dEnd) throws ParseException {
        List<VehicleUseTimeResponse> datelist = null;

        try {
            //日期工具类准备
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            //设置开始时间
            Calendar calBegin = Calendar.getInstance();
            calBegin.setTime(format.parse(dBegin));

            //设置结束时间
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(format.parse(dEnd));

            //装返回的日期集合容器
            datelist = new ArrayList<VehicleUseTimeResponse>();
            VehicleUseTimeResponse vehicleUseTimeResponse = new VehicleUseTimeResponse();
            vehicleUseTimeResponse.setDate(dBegin);
            String vehicleUseTime = getVehicleUseDate(dBegin);
            vehicleUseTimeResponse.setVehicleUseTime(vehicleUseTime);

            datelist.add(vehicleUseTimeResponse);
            // 每次循环给calBegin日期加一天，直到calBegin.getTime()时间等于dEnd
            while (format.parse(dEnd).after(calBegin.getTime())) {
                // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                VehicleUseTimeResponse vehicleUseDate = new VehicleUseTimeResponse();
                calBegin.add(Calendar.DAY_OF_MONTH, 1);
                String time = format.format(calBegin.getTime());
                String date = getVehicleUseDate(time);
                vehicleUseDate.setDate(time);
                vehicleUseDate.setVehicleUseTime(date);
                datelist.add(vehicleUseDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datelist;
    }


    //判断选择的日期是否是今天
    public static boolean isToday(long time) {
        return isThisTime(time, sDateYMDFormat);
    }

    //判断选择的日期是否是本周
    public static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if (paramWeek == currentWeek) {
            return true;
        }
        return false;
    }


    //判断选择的日期是否是本月
    public static boolean isThisMonth(long time) {
        return isThisTime(time, "yyyy-MM");
    }

    public static boolean isThisTime(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }
        return false;
    }

    public static boolean isCurrentDate(String time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String now = sdf.format(new Date());//当前时间
        if (time.equals(now)) {
            return true;
        }
        return false;
    }

    public static String getVehicleUseDate(String vehicleUseTime){

        try {

            stringBuffer.setLength(0);
            String date = toMMDDStr(vehicleUseTime);
            String dateToWeek = dateToWeek2(vehicleUseTime);
            stringBuffer.append(date);
            stringBuffer.append(" ");
            stringBuffer.append(dateToWeek);

        }catch (Exception e){
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public static String getTheRemainingTime(long time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String remainingTime = sdf.format(time);//当前时间
        return remainingTime;
    }


    /**
     * 把毫秒数转换成时分秒
     * @param millis
     * @return
     */
    public static String millisToStringShort(long millis) {
        StringBuffer strBuilder = new StringBuffer();
        long temp = millis;
        long hper = 60 * 60 * 1000;
        long mper = 60 * 1000;
        long sper = 1000;
      /*  if (temp / hper > 0) {
            strBuilder.append(temp / hper).append("小时");
        }*/
        temp = temp % hper;

        if (temp / mper > 0) {
            strBuilder.append(temp / mper).append("分");
        }
        strBuilder.append(":");
        temp = temp % mper;
        if (temp / sper > 0) {
            strBuilder.append(temp / sper).append("秒");
        }
        return strBuilder.toString();
    }
}
