package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/3/25
 * description：常见问题分类与常见问题(文章分类与文章)
 */
public class ArticleWithCateResponse {

    //分类id
    private int cateId;

    //分类标题
    private String name;

    //此分类的所有文章
    private List<ArticlesEntity> articles;

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArticlesEntity> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesEntity> articles) {
        this.articles = articles;
    }

    public class ArticlesEntity {

        private int id;

        private int cateId;

        private int del;

        private int sort;

        private int status;

        private Long time;

        private String author;

        private String content;

        private String photo;

        private String seoDesc;

        private String seoKeywords;

        private String seoTitle;

        private String title;

        private Long utime;

        private Long ctime;

        private String tails;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCateId() {
            return cateId;
        }

        public void setCateId(int cateId) {
            this.cateId = cateId;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getSeoDesc() {
            return seoDesc;
        }

        public void setSeoDesc(String seoDesc) {
            this.seoDesc = seoDesc;
        }

        public String getSeoKeywords() {
            return seoKeywords;
        }

        public void setSeoKeywords(String seoKeywords) {
            this.seoKeywords = seoKeywords;
        }

        public String getSeoTitle() {
            return seoTitle;
        }

        public void setSeoTitle(String seoTitle) {
            this.seoTitle = seoTitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Long getUtime() {
            return utime;
        }

        public void setUtime(Long utime) {
            this.utime = utime;
        }

        public Long getCtime() {
            return ctime;
        }

        public void setCtime(Long ctime) {
            this.ctime = ctime;
        }

        public String getTails() {
            return tails;
        }

        public void setTails(String tails) {
            this.tails = tails;
        }
    }
}
