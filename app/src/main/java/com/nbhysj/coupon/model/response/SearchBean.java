package com.nbhysj.coupon.model.response;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

import java.util.Objects;

@Entity
public class SearchBean {
    @Id(autoincrement = true)
    Long id;
    @Property(nameInDb = "merchantId")
    private int merchantId;
    String search;//字段

    @Generated(hash = 1785472892)
    public SearchBean(Long id, int merchantId, String search) {
        this.id = id;
        this.merchantId = merchantId;
        this.search = search;
    }

    @Generated(hash = 562045751)
    public SearchBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearch() {
        return this.search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 重写equals()方法
     */
   /* @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(object == null)
            return false;
        if(this.getClass() != object.getClass())
            return false;
        final SearchBean student = (SearchBean) object;
        if(this.getMerchantId() != student.getMerchantId())
            return false;
        if(this.getSearch().equals(student.getSearch()))
            return false;
        return true;
    }*/
}
