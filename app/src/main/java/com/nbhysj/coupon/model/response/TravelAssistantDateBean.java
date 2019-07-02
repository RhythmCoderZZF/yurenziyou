package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/3/28
 * description：
 */
public class TravelAssistantDateBean {

    private String date;

    private List<DateBean> dateList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DateBean> getDateList() {
        return dateList;
    }

    public void setDateList(List<DateBean> dateList) {
        this.dateList = dateList;
    }
}
