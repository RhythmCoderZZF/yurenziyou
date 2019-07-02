package com.nbhysj.coupon.model.response;

/**
 * created by hysj at 2019/05/21
 * description：model 标签基础类
 */
public class TagBaseEntity {

    private int id;

    private String title;

    private boolean isSelect;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
