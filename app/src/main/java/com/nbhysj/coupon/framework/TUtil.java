package com.nbhysj.coupon.framework;

import com.nbhysj.coupon.util.LogUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @auther：hysj created at 2019/01/23
 * description：泛型类型自动解析工具类
 */
public class TUtil {

    public static <T> T getT(Object o, int i) {
        try {
            Class<T> clazz;
            // getClass().getGenericSuperclass()表示此Class所表示的实体(类、接口、基本类型或void)的直接超类的Type然后将其转换ParameterizedType
            // getActualTypeArguments()返回表示此类型实际类型参数的 Type 对象的数组。
            // 获得超类的泛型参数的实际类型的实例
            //当前对象的直接超类的 Type
            Type genericSuperclass = o.getClass().getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                //参数化类型
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                //返回表示此类型实际类型参数的 Type 对象的数组
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                clazz = (Class<T>) actualTypeArguments[i];
            } else {
                clazz = (Class<T>) genericSuperclass;
            }
            return clazz.newInstance();
        } catch (InstantiationException e) {
            LogUtil.e("实例化异常", e.getMessage());
        } catch (IllegalAccessException e) {
            LogUtil.e("非法访问异常", e.getMessage());
        } catch (ClassCastException e) {
            LogUtil.e("类型转换异常", e.getMessage());
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            LogUtil.e("类无法加载异常", e.getMessage());
        }
        return null;
    }
}
