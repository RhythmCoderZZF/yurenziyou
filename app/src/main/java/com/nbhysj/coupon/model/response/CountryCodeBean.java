package com.nbhysj.coupon.model.response;

import java.io.Serializable;

import me.yokeyword.indexablerv.IndexableEntity;

/**
 * @auther：hysj created on 2019/3/6
 * description：国家代码区号
 */
public class CountryCodeBean implements IndexableEntity, Serializable {

    private String name;

    private String country_code;

    private String en_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    @Override
    public String getFieldIndexBy() {
        return name;
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        this.name = indexField;
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CountryCodeBean) {
            CountryCodeBean cityBean = (CountryCodeBean) obj;
            return this.name.equals(cityBean.getName());
        }
        return super.equals(obj);
    }

/*    public class CountryCodeEntity{



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public String getEn_name() {
            return en_name;
        }

        public void setEn_name(String en_name) {
            this.en_name = en_name;
        }
    }*/
}
