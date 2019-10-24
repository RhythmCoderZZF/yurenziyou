package com.nbhysj.coupon.framework;

import android.app.Activity;

import com.nbhysj.coupon.ui.BaseActivity;
import com.nbhysj.coupon.util.LogUtil;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * created by hysj at 2019/01/23.
 * description :  App类管理栈
 */
public class AppManager {

    private static Stack<Activity> activityStack;

    private static AppManager instance;

    public AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getInstance() {

        if (instance == null) {

            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {

        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishSingleActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishSingleActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {

        if (activity != null) {
            if (activityStack != null && activityStack.contains(activity)) {// 兼容未使用AppManager管理的实例
                activityStack.remove(activity);
            }
            activity.finish();
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }



    /**
     * 结束指定类名的Activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        List<Activity> removeList = new ArrayList<Activity>();
        if (activityStack != null) {
            for(int i= 0;i < activityStack.size();i++){
                //如果字符串包含“1”，则 remove
                Activity activity = activityStack.get(i);
                if(!activity.getClass().equals(cls)){
                    removeList.add(activity);
                        activity.finish();
                    activity = null;
                }

            }

            activityStack.removeAll(removeList);


        }
    }


    /**
     * 退出应用程序
     */
    public void exitApp() {
        try {
            finishAllActivity();
            // System.exit(0);
            //    android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            LogUtil.e("退出应用异常", e.getMessage());
        }
    }
}
