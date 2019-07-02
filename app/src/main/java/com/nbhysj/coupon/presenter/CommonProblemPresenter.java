package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.CommonProblemContract;
import com.nbhysj.coupon.contract.PublishPostContract;
import com.nbhysj.coupon.model.request.PublishPostRequest;

/**
 * @auther：hysj created on 2019/03/25
 * description：帖子发布Presenter
 */
public class CommonProblemPresenter extends CommonProblemContract.Presenter {

    @Override
    public void getArticleWithCate() {
        mRxManager.add(mModel.getArticleWithCate().subscribe(res -> mView.getArticleWithCateResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
