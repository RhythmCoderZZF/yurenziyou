package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/5/20
 * description：位置距离
 */
public class PositionDistanceResponse {

    private List<PositionDistanceBean> positionDistanceList;

    public List<PositionDistanceBean> getPositionDistanceList() {
        return positionDistanceList;
    }

    public void setPositionDistanceList(List<PositionDistanceBean> positionDistanceList) {
        this.positionDistanceList = positionDistanceList;
    }
}
