package com.nbhysj.coupon.oss.audio;

import java.io.File;

public interface IConvertCallback {

    void onSuccess(File convertedFile);

    void onFailure(Exception error);

}