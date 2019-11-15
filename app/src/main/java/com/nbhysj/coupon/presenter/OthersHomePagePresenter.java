package com.nbhysj.coupon.presenter;
import com.nbhysj.coupon.contract.OthersHomePageContract;

/**
 * @auther：hysj created on 2019/10/19
 * description：他人首页信息Presenter
 */
public class OthersHomePagePresenter extends OthersHomePageContract.Presenter {

    @Override
    public void onStart() {

    }

    @Override
    public void getOthersHomePageInfo(int userId) {

        mRxManager.add(mModel.getOthersHomePageInfo(userId).subscribe(res -> mView.getOthersHomePageInfoResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void userFollow(int userId) {
        mRxManager.add(mModel.userFollow(userId).subscribe(res -> mView.userFollowResult(res), e -> mView.showMsg(e.getMessage())));
    }
}
