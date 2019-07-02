package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/6/13
 * description：商家相册
 */
public class MchAlbumResponse {

    private List<CatePhotoNumsEntity> catePhotoNums;

    private List<CateWithPhotosEntity> cateWithPhotos;

    public List<CatePhotoNumsEntity> getCatePhotoNums() {
        return catePhotoNums;
    }

    public void setCatePhotoNums(List<CatePhotoNumsEntity> catePhotoNums) {
        this.catePhotoNums = catePhotoNums;
    }

    public List<CateWithPhotosEntity> getCateWithPhotos() {
        return cateWithPhotos;
    }

    public void setCateWithPhotos(List<CateWithPhotosEntity> cateWithPhotos) {
        this.cateWithPhotos = cateWithPhotos;
    }

    public class CatePhotoNumsEntity {

        private String title;

        private int num;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }


    public class CateWithPhotosEntity {

        private int id;

        private String title;

        private List<PhotosVOSEntity> photosVOS;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<PhotosVOSEntity> getPhotosVOS() {
            return photosVOS;
        }

        public void setPhotosVOS(List<PhotosVOSEntity> photosVOS) {
            this.photosVOS = photosVOS;
        }
    }


    public class PhotosVOSEntity {

        private String photo;

        private int mchId;

        private String smallPhoto;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getMchId() {
            return mchId;
        }

        public void setMchId(int mchId) {
            this.mchId = mchId;
        }

        public String getSmallPhoto() {
            return smallPhoto;
        }

        public void setSmallPhoto(String smallPhoto) {
            this.smallPhoto = smallPhoto;
        }
    }
}
