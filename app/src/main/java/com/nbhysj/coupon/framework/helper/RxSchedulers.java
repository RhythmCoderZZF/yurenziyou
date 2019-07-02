package com.nbhysj.coupon.framework.helper;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * created by hysj at 2019/01/23.
 * description : 包装网络请求的Observable链，
 * 可扩展情形：工作线程中处理数据，然后在主线程中处理结果
 */

public class RxSchedulers {
    //  Transformer实际上就是Func1<Observable , Observable>，一段数据流的处理
    public static <T> ObservableTransformer<T, T> io_main() {
        // 在Schedulers.io()线程中执行后发送至主线程AndroidSchedulers.mainThread(),attention：网络请求无法在主线程上执行,
        // 需要启动异步线程，例如：Schedulers.newThread()
        // subscribeOn()：事件产生的线程
        // observeOn()：事件消费的线程，AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
        // .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
        // .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程

        return tObservable -> tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
