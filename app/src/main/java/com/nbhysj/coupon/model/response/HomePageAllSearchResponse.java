package com.nbhysj.coupon.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * @auther：hysj created on 2019/4/3
 * description：首页类型搜索返回
 */
public class HomePageAllSearchResponse implements Serializable {

    //美食
    private List<HomeSearchMchTypeBean> foods;

    //攻略
    private List<HomeSearchMchTypeBean> strategys;

    //酒店
    private List<HomeSearchMchTypeBean> hotels;

    //组合
    private List<HomeSearchMchTypeBean> groups;

    //城市对象
    private List<CityEntity> citys;

    //互动
    private List<HomeSearchMchTypeBean> recreations;

    //帖子
    private List<HomePageResponse>  posts;

    //景点
    private List<HomeSearchMchTypeBean> scenics;

    public List<HomeSearchMchTypeBean> getFoods() {
        return foods;
    }

    public void setFoods(List<HomeSearchMchTypeBean> foods) {
        this.foods = foods;
    }

    public List<HomeSearchMchTypeBean> getStrategys() {
        return strategys;
    }

    public void setStrategys(List<HomeSearchMchTypeBean> strategys) {
        this.strategys = strategys;
    }

    public List<HomeSearchMchTypeBean> getHotels() {
        return hotels;
    }

    public void setHotels(List<HomeSearchMchTypeBean> hotels) {
        this.hotels = hotels;
    }

    public List<HomeSearchMchTypeBean> getGroups() {
        return groups;
    }

    public void setGroups(List<HomeSearchMchTypeBean> groups) {
        this.groups = groups;
    }

    public List<CityEntity> getCitys() {
        return citys;
    }

    public void setCitys(List<CityEntity> citys) {
        this.citys = citys;
    }


    public List<HomePageResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<HomePageResponse> posts) {
        this.posts = posts;
    }

    public List<HomeSearchMchTypeBean> getRecreations() {
        return recreations;
    }

    public void setRecreations(List<HomeSearchMchTypeBean> recreations) {
        this.recreations = recreations;
    }

    public List<HomeSearchMchTypeBean> getScenics() {
        return scenics;
    }

    public void setScenics(List<HomeSearchMchTypeBean> scenics) {
        this.scenics = scenics;
    }

    public class CityEntity{

        private String searchType;

        private String cityCode;

        private String letter;

        private String name;

        private String banner;

        private int pid;

        private String id;

        public String getSearchType() {
            return searchType;
        }

        public void setSearchType(String searchType) {
            this.searchType = searchType;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public String getLetter() {
            return letter;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
