package com.nbhysj.coupon.framework;

import android.support.annotation.NonNull;

import com.nbhysj.coupon.util.LogUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @auther：hysj created at 2019/01/23
 * description：用RxJava实现的EventBus
 */
public class RxBus {
    private static RxBus INSTANCE;

    public static synchronized RxBus $() {
        if (null == INSTANCE) {
            INSTANCE = new RxBus();
        }
        return INSTANCE;
    }

    private RxBus() {
    }

    @SuppressWarnings("rawtypes")
    /** tag对应一系列的响应者 **/
    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<Object, List<Subject>>();


    /**
     * 默认使用事件的类名来作为tag，类似eventBus
     *
     * @param content
     */
    public void post(@NonNull Object content) {
        post(content.getClass().getName(), content);
    }

    /**
     * 触发事件
     *
     * @param content
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void post(@NonNull Object tag, @NonNull Object content) {
        LogUtil.i("RxBus post", "RxBus post & eventTag: " + tag);
        List<Subject> subjectList = subjectMapper.get(tag);
        if (!isEmpty(subjectList)) { // 该事件有响应者
            for (Subject subject : subjectList) {
                subject.onNext(content); // 手动调用订阅者的响应
                LogUtil.d("RxBus:", "RxBus onEvent & eventTag: " + tag);
            }
        }
    }

    /**
     * 注册事件源
     *
     * @param tag 事件标记
     * @return 被观察者
     */
    @SuppressWarnings({"rawtypes"})
    public <T> Observable<T> register(@NonNull Object tag) {
        List<Subject> subjectList = subjectMapper.get(tag);// 维持所有的响应者
        if (null == subjectList) {
            subjectList = new ArrayList<Subject>();
            subjectMapper.put(tag, subjectList);
        }
        Subject<T> subject;
        subjectList.add(subject = PublishSubject.create());
        LogUtil.i("RxBus:", tag + " register memebers size:" + subjectList.size());
        return subject;
    }

    /**
     * 取消监听
     *
     * @param tag
     * @param observable
     * @return
     */
    @SuppressWarnings("rawtypes")
    public RxBus unregister(@NonNull Object tag, @NonNull Observable<?> observable) {
        if (null == observable)
            return $();
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove((Subject<?>) observable);
            if (isEmpty(subjects)) { //所有被观察者都移除后，再移除事件
                subjectMapper.remove(tag);
                LogUtil.i("RxBus:", tag + ", now memebers size:" + subjects.size());
            }
        }
        return $();
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Collection<Subject> collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * 订阅事件源
     *
     * @param mObservable
     * @param mAction1
     * @returnx
     */
    public RxBus OnEvent(Observable<?> mObservable, Consumer<Object> mAction1) {
        mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(mAction1, (e) -> LogUtil.e("", e.getMessage()));
        return $();
    }

    @SuppressWarnings("rawtypes")
    public void unregister(@NonNull Object tag) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjectMapper.remove(tag);
        }
    }

}
