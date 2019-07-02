package com.nbhysj.coupon.framework;

/**
 * created by hysj at 2019/01/23.
 * descrpition:网络请求json解析
 */

import com.google.gson.TypeAdapter;
import com.nbhysj.coupon.util.LogUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String s = value.string() + "";
            LogUtil.e("GsonResponseBodyConverter", "json : " + s);
            return adapter.fromJson(s);
        } finally {
            value.close();
        }
    }
}

