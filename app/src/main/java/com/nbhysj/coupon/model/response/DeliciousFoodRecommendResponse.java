package com.nbhysj.coupon.model.response;

/**
 * @auther：hysj created on 2019/4/19
 * description：美食推荐
 */
public class DeliciousFoodRecommendResponse {

    //美食菜系
    private String foodCuisine;

    //美食人均价格
    private String perCapitaPrice;

    //美食位置
    private String deliciousFoodStore;

    //美食照片
    private String deliciousFoodPhoto;

    public String getFoodCuisine() {
        return foodCuisine;
    }

    public void setFoodCuisine(String foodCuisine) {
        this.foodCuisine = foodCuisine;
    }

    public String getPerCapitaPrice() {
        return perCapitaPrice;
    }

    public void setPerCapitaPrice(String perCapitaPrice) {
        this.perCapitaPrice = perCapitaPrice;
    }

    public String getDeliciousFoodStore() {
        return deliciousFoodStore;
    }

    public void setDeliciousFoodStore(String deliciousFoodStore) {
        this.deliciousFoodStore = deliciousFoodStore;
    }

    public String getDeliciousFoodPhoto() {
        return deliciousFoodPhoto;
    }

    public void setDeliciousFoodPhoto(String deliciousFoodPhoto) {
        this.deliciousFoodPhoto = deliciousFoodPhoto;
    }
}
