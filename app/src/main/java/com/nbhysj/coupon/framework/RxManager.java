package com.nbhysj.coupon.framework;

import com.nbhysj.coupon.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * created by hysj at 2019/01/23.
 * description : Rx事件管理
 */

public class RxManager {

    public RxBus mRxBus = RxBus.$();
    private Map<String, Observable<?>> mObservables = new HashMap<>();// 管理被观察者，即事件源
    private CompositeDisposable mCompositeSubscription = new CompositeDisposable();// 管理订阅者

    /**
     * 发送bus事件
     *
     * @param tag     事件tag
     * @param content 事件内容
     */
    public void post(Object tag, Object content) {
        mRxBus.post(tag, content);
    }

    /**
     * 响应bus事件
     *
     * @param tag     事件tag
     * @param action1 事件内容
     */
    public void on(String tag, Consumer<Object> action1) {
        Observable<?> mObservable = mRxBus.register(tag);
        mObservables.put(tag, mObservable);
        mCompositeSubscription.add(mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(action1, e ->
                LogUtil.e("RxManage", e.getMessage(), e))); //在主线程观察并进行响应
    }

    /**
     * 添加订阅者
     *
     * @param m 订阅者
     */
    public void add(Disposable m) {
        mCompositeSubscription.add(m);
    }

    /**
     * RXjava取消注册，以避免内存泄露
     */
    public void clear() {
        if (mCompositeSubscription != null && mCompositeSubscription.isDisposed()) {
            mCompositeSubscription.dispose();// 取消订阅
        }
        for (Map.Entry<String, Observable<?>> entry : mObservables.entrySet()) {
            mRxBus.unregister(entry.getKey(), entry.getValue());// 移除所有观察
        }
    }
}
