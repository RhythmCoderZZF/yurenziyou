package com.nbhysj.coupon.model.response;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class HomeSearchComprehensiveBean {
    @Id(autoincrement = true)
    Long id;
    @Unique // 搜索记录(唯一)
    String search;//字段
    @Generated(hash = 1656305850)
    public HomeSearchComprehensiveBean(Long id, String search) {
        this.id = id;
        this.search = search;
    }
    @Generated(hash = 1253379798)
    public HomeSearchComprehensiveBean() {
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
