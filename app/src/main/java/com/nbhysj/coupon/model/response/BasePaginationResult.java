package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/3/15
 * description：分页基类
 */
public class BasePaginationResult {

    //页码
    private int page;
    //页数
    private int pageSize;

    private int begin;

    private int pageCount;

    private int totalPage;

    private int hasNext;

    private int hasPrevious;

    private String keyWords;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getHasNext() {
        return hasNext;
    }

    public void setHasNext(int hasNext) {
        this.hasNext = hasNext;
    }

    public int getHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(int hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
}
