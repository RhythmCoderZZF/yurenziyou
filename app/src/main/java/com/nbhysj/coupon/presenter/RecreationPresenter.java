package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.RecreationContract;
import com.nbhysj.coupon.contract.ScenicSpotContract;

import java.util.HashMap;

/**
 * @auther：hysj created on 2019/06/05
 * description：景点Presenter
 */
public class RecreationPresenter extends RecreationContract.Presenter {

    @Override
    public void getRecreationHomePage(String longitude, String latitude) {
        mRxManager.add(mModel.getRecreationHomePage(longitude, latitude).subscribe(res -> mView.getRecreationHomePageResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void findRecreationByCate(HashMap<String, String> map) {
        mRxManager.add(mModel.findRecreationByCate(map).subscribe(res -> mView.findRecreationByCateResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getRecreationBangDanRanking(int cityId) {
        mRxManager.add(mModel.getRecreationDanRanking(cityId).subscribe(res -> mView.getRecreationDanRankingResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getRecreationListByCateId(HashMap<String, String> map) {
        mRxManager.add(mModel.getRecreationListByCateId(map).subscribe(res -> mView.getRecreationListByCateIdResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
