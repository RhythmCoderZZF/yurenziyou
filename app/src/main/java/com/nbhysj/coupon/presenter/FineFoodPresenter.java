package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.FineFoodContract;
import com.nbhysj.coupon.contract.ScenicSpotContract;

import java.util.HashMap;

/**
 * @auther：hysj created on 2019/06/05
 * description：美食Presenter
 */
public class FineFoodPresenter extends FineFoodContract.Presenter {

    @Override
    public void getFineFoodHomePage(String longitude, String latitude) {
        mRxManager.add(mModel.getFineFoodHomePage(longitude, latitude).subscribe(res -> mView.getFineFoodHomePageResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void findFoodByCate(HashMap<String, String> map) {
        mRxManager.add(mModel.findFoodByCate(map).subscribe(res -> mView.findFoodByCateResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getFoodBangDanRanking(int cityId) {
        mRxManager.add(mModel.getFoodBangDanRanking(cityId).subscribe(res -> mView.getFoodBangDanRankingResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
