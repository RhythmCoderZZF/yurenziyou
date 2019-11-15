package com.nbhysj.coupon.model.response;

/**
 * created by fqq on 2018/7/18.
 * description: 权限验证
 */

public class AuthorityVerificationBean {

    //权限图标
    private int image;

    //权限名
    private String authorityName;

    //权限描述
    private String authorityDescription;


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityDescription() {
        return authorityDescription;
    }

    public void setAuthorityDescription(String authorityDescription) {
        this.authorityDescription = authorityDescription;
    }
}
