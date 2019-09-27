package com.nbhysj.coupon.widget.calendar.ticketcalendar;

import java.util.Date;

public class TicketSelectDateBean {
    //item类型
    public static int item_type_day = 1;//日期item
    public static int item_type_month = 2;//月份item
    int itemType = 1;//默认是日期item

    //item状态
    public static int ITEM_STATE_BEGIN_DATE = 1;//开始日期
    public static int ITEM_STATE_END_DATE = 2;//结束日期
    public static int ITEM_STATE_SELECTED = 3;//选中状态
    public static int ITEM_STATE_NORMAL = 4;//正常状态
    public int itemState = ITEM_STATE_NORMAL;

    Date date;//具体日期
    String day;//一个月的某天
    String monthStr;//月份
    private String dateStr; //年月日

    public boolean isBeginTimeSelect = false;

    //是否可订票
    private int sellStatus = 0;

    private double ticketPrice = 0;

    public int getItemState() {
        return itemState;
    }

    public void setItemState(int itemState) {
        this.itemState = itemState;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getMonthStr() {
        return monthStr;
    }

    public void setMonthStr(String monthStr) {
        this.monthStr = monthStr;
    }

    public static int getItem_type_month() {
        return item_type_month;
    }

    public static void setItem_type_month(int item_type_month) {
        TicketSelectDateBean.item_type_month = item_type_month;
    }

    public static int getItem_type_day() {
        return item_type_day;
    }

    public static void setItem_type_day(int item_type_day) {
        TicketSelectDateBean.item_type_day = item_type_day;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isBeginTimeSelect() {
        return isBeginTimeSelect;
    }

    public void setBeginTimeSelect(boolean beginTimeSelect) {
        isBeginTimeSelect = beginTimeSelect;
    }

    public static int getItemStateBeginDate() {
        return ITEM_STATE_BEGIN_DATE;
    }

    public static void setItemStateBeginDate(int itemStateBeginDate) {
        ITEM_STATE_BEGIN_DATE = itemStateBeginDate;
    }

    public static int getItemStateEndDate() {
        return ITEM_STATE_END_DATE;
    }

    public static void setItemStateEndDate(int itemStateEndDate) {
        ITEM_STATE_END_DATE = itemStateEndDate;
    }

    public static int getItemStateSelected() {
        return ITEM_STATE_SELECTED;
    }

    public static void setItemStateSelected(int itemStateSelected) {
        ITEM_STATE_SELECTED = itemStateSelected;
    }

    public static int getItemStateNormal() {
        return ITEM_STATE_NORMAL;
    }

    public static void setItemStateNormal(int itemStateNormal) {
        ITEM_STATE_NORMAL = itemStateNormal;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public int getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(int sellStatus) {
        this.sellStatus = sellStatus;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
