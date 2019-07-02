package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.Map;

/**
 * @auther：hysj created on 2019/3/23
 * description：
 */
public class SerializableThirdPartyMap implements Serializable {

    private Map<String, String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
