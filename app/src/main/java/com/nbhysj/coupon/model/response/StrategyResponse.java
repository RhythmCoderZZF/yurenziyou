package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/9/21
 * description：攻略
 */
public class StrategyResponse {

    //攻略列表
    private List<StrategyEntity> result;

    private BasePaginationResult page;

    public List<StrategyEntity> getResult() {
        return result;
    }

    public void setResult(List<StrategyEntity> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }

    public class StrategyEntity{
    //攻id
    private int id;

    //攻略图片
    private String photo;

    //收藏数量
    private int collectionNum;

    //点赞数量
    private int hits;

    //攻略标题
    private String title;

    //攻略简介
    private String intro;

    //攻略h5
    private String strategyH5Url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getStrategyH5Url() {
        return strategyH5Url;
    }

    public void setStrategyH5Url(String strategyH5Url) {
        this.strategyH5Url = strategyH5Url;
    }
    }
}
