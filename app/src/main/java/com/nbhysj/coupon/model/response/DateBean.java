package com.nbhysj.coupon.model.response;

public class DateBean {
    private int[] solar;//阳历年、月、日
    private String[] lunar;//农历月、日
    private String solarHoliday;//阳历节假日
    private String lunarHoliday;//阳历节假日
    private int type;//0:上月，1:当月，2:下月
    private String term;//节气
    /**
     * 日期是否选中
     **/
    private boolean isSelect;

    /**
     * 日期是否选中
     **/
    private boolean isStartSelect;

    /**
     * 日期是否选中
     **/
    private boolean isEndSelect;

    public int[] getSolar() {
        return solar;
    }

    public void setSolar(int year, int month, int day) {
        this.solar = new int[]{year, month, day};
    }

    public String[] getLunar() {
        return lunar;
    }

    public void setLunar(String[] lunar) {
        this.lunar = lunar;
    }

    public String getSolarHoliday() {
        return solarHoliday;
    }

    public void setSolarHoliday(String solarHoliday) {
        this.solarHoliday = solarHoliday;
    }

    public String getLunarHoliday() {
        return lunarHoliday;
    }

    public void setLunarHoliday(String lunarHoliday) {
        this.lunarHoliday = lunarHoliday;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setSolar(int[] solar) {
        this.solar = solar;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isStartSelect() {
        return isStartSelect;
    }

    public void setStartSelect(boolean startSelect) {
        isStartSelect = startSelect;
    }

    public boolean isEndSelect() {
        return isEndSelect;
    }

    public void setEndSelect(boolean endSelect) {
        isEndSelect = endSelect;
    }
}
