package com.nbhysj.coupon.common.Enum;

/**
 * @auther：hysj created on 2019/10/12
 * description：帖子类型
 */
public enum PostsTypeEnum {

    //1帖子点赞，2帖子收藏，3.帖子评论 postsType
    POST_PRAISE(1, "POST_PRAISE"), POST_COLLECT(2, "POST_COLLECT"), POST_COMMENT(3, "POST_COMMENT");

    private final int key;
    private final String value;

    PostsTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    //根据key获取枚举
    public static PostsTypeEnum getEnumByKey(int key) {
        for (PostsTypeEnum temp : PostsTypeEnum.values()) {
            if (temp.getKey() == key) {
                return temp;
            }
        }
        return null;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
