package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2020/1/1
 * description：推荐排序
 */
public class SortTypeBean {

    private boolean isSelect;

    private String sortValue;

    private String sortType;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getSortValue() {
        return sortValue;
    }

    public void setSortValue(String sortValue) {
        this.sortValue = sortValue;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
