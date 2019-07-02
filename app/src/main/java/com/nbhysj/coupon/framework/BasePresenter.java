package com.nbhysj.coupon.framework;

import android.content.Context;

/**
 * created by hysj at 2019/01/23.
 * description : Presenter 基类
 */
public abstract class BasePresenter<E, T> {
    @Deprecated
    public Context mContext;
    /**
     * 模型，包含业务模型和数据模型
     **/
    public E mModel;

    /**
     * 视图
     **/
    public T mView;

    public RxManager mRxManager = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    // 数据加载初始化
    public abstract void onStart();

    public void onDestroy() {
        mRxManager.clear();
    }

}
