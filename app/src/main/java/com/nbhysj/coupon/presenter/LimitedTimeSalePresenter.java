package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.LimitedTimeSaleContract;
import com.nbhysj.coupon.contract.RecreationContract;

import java.util.HashMap;

/**
 * @auther：hysj created on 2019/09/29
 * description：限时特卖 Presenter
 */
public class LimitedTimeSalePresenter extends LimitedTimeSaleContract.Presenter {

    @Override
    public void getLimitedTimeSalePage() {
        mRxManager.add(mModel.getLimitedTimeSalePage().subscribe(res -> mView.getLimitedTimeSalePageResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
