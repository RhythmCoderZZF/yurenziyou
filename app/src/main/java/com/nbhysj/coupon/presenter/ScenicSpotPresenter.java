package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.ScenicSpotContract;

import java.util.HashMap;

/**
 * @auther：hysj created on 2019/06/05
 * description：景点Presenter
 */
public class ScenicSpotPresenter extends ScenicSpotContract.Presenter {

    @Override
    public void getScenicSpotHomePage(String longitude, String latitude) {
        mRxManager.add(mModel.getScenicSpotHomePage(longitude, latitude).subscribe(res -> mView.getScenicSpotHomePageResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void findScenicByCate(HashMap<String, String> map) {
        mRxManager.add(mModel.findScenicByCate(map).subscribe(res -> mView.findScenicByCateResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getScenicBangDanRanking(int cityId) {
        mRxManager.add(mModel.getScenicBangDanRanking(cityId).subscribe(res -> mView.getScenicBangDanRankingResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getMchDetails(int mchId) {
        mRxManager.add(mModel.getMchDetails(mchId).subscribe(res -> mView.getMchDetailsResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getTourGuideList(HashMap<String, String> map) {
        mRxManager.add(mModel.getTourGuideList(map).subscribe(res -> mView.getTourGuideListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getMchAlbumList(int mchId) {
        mRxManager.add(mModel.getMchAlbumList(mchId).subscribe(res -> mView.getMchAlbumListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getNetFriendAlbumList(int mchId, int page, int pageSize) {
        mRxManager.add(mModel.getNetFriendAlbumList(mchId, page, pageSize).subscribe(res -> mView.getNetFriendAlbumListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
