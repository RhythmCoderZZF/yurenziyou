package com.nbhysj.coupon.model.response;

import java.util.List;

/**
 * @auther：hysj created on 2019/10/13
 * description：帖子评论
 */
public class PostsCommentResponse {

    private List<PostsCommentEntity> result;

    private BasePaginationResult page;

    public List<PostsCommentEntity> getResult() {
        return result;
    }

    public void setResult(List<PostsCommentEntity> result) {
        this.result = result;
    }

    public BasePaginationResult getPage() {
        return page;
    }

    public void setPage(BasePaginationResult page) {
        this.page = page;
    }

    public class PostsCommentEntity {

        private int id;

        private CommentUserEntity user;

        //评论内容
        private String content;

        //点赞数
        private int zanCount;

        //点赞状态
        private int zanStatus;

        private List<CommentSubBean> comment;

        private long ctime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public CommentUserEntity getUser() {
            return user;
        }

        public void setUser(CommentUserEntity user) {
            this.user = user;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getZanCount() {
            return zanCount;
        }

        public void setZanCount(int zanCount) {
            this.zanCount = zanCount;
        }

        public int getZanStatus() {
            return zanStatus;
        }

        public void setZanStatus(int zanStatus) {
            this.zanStatus = zanStatus;
        }

        public List<CommentSubBean> getComment() {
            return comment;
        }

        public void setComment(List<CommentSubBean> comment) {
            this.comment = comment;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }
    }

}
