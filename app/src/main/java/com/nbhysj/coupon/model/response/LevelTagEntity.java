package com.nbhysj.coupon.model.response;

/**
 * created by hysj at 2019/05/21
 * description：model 星级标签基础类
 */
public class LevelTagEntity {

    private int id;

    private String title;

    //等级
    private int level;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
