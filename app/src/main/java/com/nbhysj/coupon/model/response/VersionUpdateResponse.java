package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/11/11
 * description：版本更新
 */
public class VersionUpdateResponse {

    private int id;

    private String version;

    private String updateNote;

    private String onlineStatus;

    private String updateStatus;

    private long ctime;

    private long utime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUpdateNote() {
        return updateNote;
    }

    public void setUpdateNote(String updateNote) {
        this.updateNote = updateNote;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public long getUtime() {
        return utime;
    }

    public void setUtime(long utime) {
        this.utime = utime;
    }
}
