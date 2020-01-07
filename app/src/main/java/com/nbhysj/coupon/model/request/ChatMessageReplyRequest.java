package com.nbhysj.coupon.model.request;

/**
 * @auther：hysj created on 2020/1/4
 * description：用户聊天消息
 */
public class ChatMessageReplyRequest {

    //接收者
    private int toUid;

    //类型0文字 1图片
    private int type;

    //消息
    private String message;

    public int getToUid() {
        return toUid;
    }

    public void setToUid(int toUid) {
        this.toUid = toUid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
