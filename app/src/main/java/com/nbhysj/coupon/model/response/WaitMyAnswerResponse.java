package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/4
 * description：商户界面商户问答列表
 */
public class WaitMyAnswerResponse {

    private List<WaitMyAnswerBean> result;

    private String mchName;

    private String recomment;

    private BasePaginationResult page;

    public List<WaitMyAnswerBean> getResult() {
        return result;
    }

    public void setResult(List<WaitMyAnswerBean> result) {
        this.result = result;
    }

    public String getMchName() {
        return mchName;
    }

    public void setMchName(String mchName) {
        this.mchName = mchName;
    }

    public String getRecomment() {
        return recomment;
    }

    public void setRecomment(String recomment) {
        this.recomment = recomment;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }

}
