package com.nbhysj.coupon.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SharePreference的根据类，使用它可以更方便的数据进行简单存储
 * 这里只要知道基本调用方法就可以了
 * 1.通过构造方法来传入上下文和文件名
 * 2.通过putValue方法传入一个或多个自定义的ContentValue对象，进行数据存储
 * 3.通过get方法来获取数据
 */
public class SharedPreferencesUtils {
    //定义一个SharePreference对象
    private static SharedPreferencesUtils util;
    private static SharedPreferences sp;
    public static final String FILE_NAME = "murloc_data";
    public static final String TOKEN = "Authorization";      //TOKEN
    public static final String USERNAME = "username";        //用户名
    public static final String NICKNAME = "nickname";        //昵称
    public static final String USER_ID = "userId";           //用户id
    public static final String USER_MOBILE = "mobile";       //手机号
    public static final String USER_SEX = "sex";             //用户性别
    public static final String USER_AVATAR = "avatar";       //用户头像
    public static final String USER_PROFILE = "profile";     //用户简介
    public static final String USER_BIRTHDAY = "birthday";   //用户生日

    public static final String FANS_NUM = "fansNum";           //粉丝数
    public static final String FOLLOW_NUM = "followNum";       //关注数
    public static final String COLLECTION_NUM = "collectionNum";     //收藏数
    public static final String ZAN_NUM = "zanNum";            //获赞数

    public static final String LATITUDE = "latitude";        //纬度
    public static final String LONGITUDE = "longitude";      //经度

    public static final String SEARCH_KEYWORD = "searchKeyWord";      //搜索关键字

    public static final String VERSION_NAME = "version_name";  //版本名
    public static final String VERSION_CODE = "version_code";  //版本code
    public static final String IS_INIT_FIRST = "isInitFirst";  //是否是第一次初始化

    private SharedPreferencesUtils(Context context, String name) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    /**
     * 初始化SharedPreferencesUtil,只需要初始化一次，建议在Application中初始化
     *
     * @param context 上下文对象
     * @param name    SharedPreferences Name
     */
    public static void getInstance(Context context, String name) {
        if (util == null) {
            util = new SharedPreferencesUtils(context, name);
        }
    }

    /**
     * 保存登录数据
     *
     * @param userId
     * @param mobile
     * @param nickname
     * @param token
     * @return
     */
    public static boolean saveLoginData(int userId, String mobile, String nickname,String token) {

        boolean result;
        SharedPreferences.Editor editor = sp.edit();
        try {
            editor.putInt(USER_ID, userId);
            editor.putString(USER_MOBILE, mobile);
            editor.putString(NICKNAME, nickname);
            if(!TextUtils.isEmpty(mobile)) {
                editor.putString(USERNAME, mobile);
            }
            editor.putString(SharedPreferencesUtils.TOKEN, token);
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }
    /**
     * 得到当前忽略版本
     *
     * @param context
     * @return
     */
    public static int getSkippedVersionCode(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt("skipped_version_code", 0);
    }

    /**
     * 设置当前版本
     *
     * @param context
     * @param versionName
     */
    public static void setVersionName(Context context, String versionName) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(VERSION_NAME, versionName);
        editor.commit();
    }

    /**
     * 设置当前版本
     *
     * @param context
     * @param isInitFirst
     */
    public static void setInitFirst(Context context, boolean isInitFirst) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(IS_INIT_FIRST, isInitFirst);
        editor.commit();
    }

    /**
     * 设置当前版本
     *
     * @param context
     */
    public static Boolean getInitFirst(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        boolean isInitFirst = sp.getBoolean(IS_INIT_FIRST, true);
         return isInitFirst ;
    }


    /**
     * 保存登录数据
     *
     * @param latitude
     * @param longitude
     * @return
     */
    public static boolean saveLongitudeAndLatitudeData(String latitude, String longitude) {

        boolean result;
        SharedPreferences.Editor editor = sp.edit();
        try {
            editor.putString(LATITUDE, latitude);
            editor.putString(LONGITUDE, longitude);
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }

    /**
     * 保存用户信息数据
     *
     * @param sex      性别
     * @param birthday 生日
     * @param profile  简介
     * @return
     */
    public static boolean saveUserInfoData(String avatar, int sex, String birthday, String profile, String fansNum, String followNum, String collectionNum, String zanNum) {

        boolean result;
        SharedPreferences.Editor editor = sp.edit();
        try {
            editor.putString(USER_AVATAR, avatar);
            editor.putInt(USER_SEX, sex);
            editor.putString(USER_BIRTHDAY, birthday);
            editor.putString(USER_PROFILE, profile);
            editor.putString(FANS_NUM, fansNum);
            editor.putString(FOLLOW_NUM, followNum);
            editor.putString(COLLECTION_NUM, collectionNum);
            editor.putString(ZAN_NUM, zanNum);
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }

    /**
     * 保存数据到SharedPreferences
     *
     * @param key   键
     * @param value 需要保存的数据
     * @return 保存结果
     */
    public static boolean putData(String key, Object value) {
        boolean result;
        SharedPreferences.Editor editor = sp.edit();
        String type = value.getClass().getSimpleName();
        try {
            switch (type) {
                case "Boolean":
                    editor.putBoolean(key, (Boolean) value);
                    break;
                case "Long":
                    editor.putLong(key, (Long) value);
                    break;
                case "Float":
                    editor.putFloat(key, (Float) value);
                    break;
                case "String":
                    editor.putString(key, (String) value);
                    break;
                case "Integer":
                    editor.putInt(key, (Integer) value);
                    break;
                default:
                    Gson gson = new Gson();
                    String json = gson.toJson(value);
                    editor.putString(key, json);
                    break;
            }
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }

    /**
     * 获取SharedPreferences中保存的数据
     *
     * @param key          键
     * @param defaultValue 获取失败默认值
     * @return 从SharedPreferences读取的数据
     */
    public static Object getData(String key, Object defaultValue) {
        Object result;
        String type = defaultValue.getClass().getSimpleName();
        try {
            switch (type) {
                case "Boolean":
                    result = sp.getBoolean(key, (Boolean) defaultValue);
                    break;
                case "Long":
                    result = sp.getLong(key, (Long) defaultValue);
                    break;
                case "Float":
                    result = sp.getFloat(key, (Float) defaultValue);
                    break;
                case "String":
                    result = sp.getString(key, (String) defaultValue);
                    break;
                case "Integer":
                    result = sp.getInt(key, (Integer) defaultValue);
                    break;
                default:
                    Gson gson = new Gson();
                    String json = sp.getString(key, "");
                    if (!json.equals("") && json.length() > 0) {
                        result = gson.fromJson(json, defaultValue.getClass());
                    } else {
                        result = defaultValue;
                    }
                    break;
            }
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 用于保存集合
     *
     * @param key  key
     * @param list 集合数据
     * @return 保存结果
     */
    public static <T> boolean putListData(String key, List<T> list) {
        boolean result;
        String type = list.get(0).getClass().getSimpleName();
        SharedPreferences.Editor editor = sp.edit();
        JsonArray array = new JsonArray();
        try {
            switch (type) {
                case "Boolean":
                    for (int i = 0; i < list.size(); i++) {
                        array.add((Boolean) list.get(i));
                    }
                    break;
                case "Long":
                    for (int i = 0; i < list.size(); i++) {
                        array.add((Long) list.get(i));
                    }
                    break;
                case "Float":
                    for (int i = 0; i < list.size(); i++) {
                        array.add((Float) list.get(i));
                    }
                    break;
                case "String":
                    for (int i = 0; i < list.size(); i++) {
                        array.add((String) list.get(i));
                    }
                    break;
                case "Integer":
                    for (int i = 0; i < list.size(); i++) {
                        array.add((Integer) list.get(i));
                    }
                    break;
                default:
                    Gson gson = new Gson();
                    for (int i = 0; i < list.size(); i++) {
                        JsonElement obj = gson.toJsonTree(list.get(i));
                        array.add(obj);
                    }
                    break;
            }
            editor.putString(key, array.toString());
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }

    /**
     * 获取保存的List
     *
     * @param key key
     * @return 对应的Lis集合
     */
    public static <T> List<T> getListData(String key, Class<T> cls) {
        List<T> list = new ArrayList<>();
        String json = sp.getString(key, "");
        if (!json.equals("") && json.length() > 0) {
            Gson gson = new Gson();
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement elem : array) {
                list.add(gson.fromJson(elem, cls));
            }
        }
        return list;
    }

    /**
     * 用于保存集合
     *
     * @param key key
     * @param map map数据
     * @return 保存结果
     */
    public static <K, V> boolean putHashMapData(String key, Map<K, V> map) {
        boolean result;
        SharedPreferences.Editor editor = sp.edit();
        try {
            Gson gson = new Gson();
            String json = gson.toJson(map);
            editor.putString(key, json);
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }

    /**
     * 用于保存集合
     *
     * @param key key
     * @return HashMap
     */
    public static <V> HashMap<String, V> getHashMapData(String key, Class<V> clsV) {
        String json = sp.getString(key, "");
        HashMap<String, V> map = new HashMap<>();
        Gson gson = new Gson();
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entrySet = obj.entrySet();
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            String entryKey = entry.getKey();
            JsonObject value = (JsonObject) entry.getValue();
            map.put(entryKey, gson.fromJson(value, clsV));
        }
        Log.e("SharedPreferencesUtil", obj.toString());
        return map;
    }

    public static void clearSharePreference() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}
