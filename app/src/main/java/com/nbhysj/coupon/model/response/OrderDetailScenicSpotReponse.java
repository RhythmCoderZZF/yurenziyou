package com.nbhysj.coupon.model.response;

import java.io.Serializable;

/**
 * @auther：hysj created on 2019/4/2
 * description：订单详情景点切换
 */
public class OrderDetailScenicSpotReponse implements Serializable {

    private String scenicSpotName;

    private String ticketType;

    private String useTime;

    private String scenicSpotAddress;

    public String getScenicSpotName() {
        return scenicSpotName;
    }

    public void setScenicSpotName(String scenicSpotName) {
        this.scenicSpotName = scenicSpotName;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getScenicSpotAddress() {
        return scenicSpotAddress;
    }

    public void setScenicSpotAddress(String scenicSpotAddress) {
        this.scenicSpotAddress = scenicSpotAddress;
    }
}
