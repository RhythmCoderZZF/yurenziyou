package com.nbhysj.coupon.oss.audio;

public interface ILoadCallback {

    void onSuccess();

    void onFailure(Exception error);

}