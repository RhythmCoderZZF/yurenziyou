package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.response.AttentionResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CommentAndAnswerResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.MessageResponse;
import com.nbhysj.coupon.model.response.UserFansFollowResponse;
import com.nbhysj.coupon.model.response.UserFollowResponse;
import com.nbhysj.coupon.model.response.ZanAndCollectionResponse;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/10/16
 * description：我的模块
 */
public interface MessageContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //获取粉丝列表
        Observable<BackResult<UserFansFollowResponse>> getUserFansList();

        //关注
        Observable<BackResult<FollowUserStatusResponse>> userFollow(int userId);

        //获取关注页面初始化接口
        Observable<BackResult<AttentionResponse>> getAttentionInit();

        //获取关注页面初始化接口
        Observable<BackResult<UserFollowResponse>> getUserFollow(int pageNo, int pageSize);

        //获取消息列表
        Observable<BackResult<MessageResponse>> getMessageList();

        //获取赞与收藏
        Observable<BackResult<ZanAndCollectionResponse>> getZanAndCollectionMsg(int pageNo, int pageSize);

        //获取评论和回答
        Observable<BackResult<CommentAndAnswerResponse>> getPostsCommentAndAnswer(int pageNo, int pageSize);
    }

    interface View extends BaseView {

        void getUserFansListResult(BackResult<UserFansFollowResponse> res);

        void userFollowResult(BackResult<FollowUserStatusResponse> res);

        void getAttentionInitResult(BackResult<AttentionResponse> res);

        void getUserFollowResult(BackResult<UserFollowResponse> res);

        void getMessageListResult(BackResult<MessageResponse> res);

        void getZanAndCollectionMsgResult(BackResult<ZanAndCollectionResponse> res);

        void getPostsCommentAndAnswerResult(BackResult<CommentAndAnswerResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getUserFansList();

        public abstract void userFollow(int userId);

        public abstract void getAttentionInit();

        public abstract void getUserFollow(int pageNo,int pageSize);

        public abstract void getMessageList();

        public abstract void getZanAndCollectionMsg(int pageNo, int pageSize);

        public abstract void getPostsCommentAndAnswer(int pageNo, int pageSize);
    }
}