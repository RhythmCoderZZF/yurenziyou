package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/11/11
 * description：消息对象
 */
public class MessageResponse {

    private List<MessageEntity> result;

    private BasePaginationResult page;

    public List<MessageEntity> getResult() {
        return result;
    }

    public void setResult(List<MessageEntity> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }

    public class MessageEntity{

    private int id;

    private String nickname;

    private String avater;

    private String message;

    private int readStatus;

    private long ctime;

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }

        public int getReadStatus() {
            return readStatus;
        }
    }

}
