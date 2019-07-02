package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.DestinationContract;

/**
 * @auther：hysj created on 2019/06/05
 * description：目的地Presenter
 */
public class DestinationPresenter extends DestinationContract.Presenter {

    @Override
    public void findMchBycityName(int cityId, int mchType, int page, int pageSize) {
        mRxManager.add(mModel.findMchBycityName(cityId, mchType, page, pageSize).subscribe(res -> mView.findMchBycityNameResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getDestinationHomePage(int cityId) {
        mRxManager.add(mModel.getDestinationHomePage(cityId).subscribe(res -> mView.getDestinationHomePageResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
