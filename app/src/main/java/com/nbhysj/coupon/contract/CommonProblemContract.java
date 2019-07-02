package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.PublishPostRequest;
import com.nbhysj.coupon.model.response.ArticleWithCateResponse;
import com.nbhysj.coupon.model.response.BackResult;

import io.reactivex.Observable;

/**
 * @auther：hysj created on 2019/03/25
 * description：常见问题分类与常见问题模块
 */
public interface CommonProblemContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //获取常见问题分类与常见问题模块
        Observable<BackResult<ArticleWithCateResponse>> getArticleWithCate();
    }

    interface View extends BaseView {

        void getArticleWithCateResult(BackResult<ArticleWithCateResponse> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getArticleWithCate();

    }
}