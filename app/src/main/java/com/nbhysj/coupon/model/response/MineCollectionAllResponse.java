package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/20
 * description：我的收藏(全部列表)
 */
public class MineCollectionAllResponse{

        private String type;

        private int num;

        private List<String> photos;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<String> getPhotos() {
            return photos;
        }

        public void setPhotos(List<String> photos) {
            this.photos = photos;
        }
}
