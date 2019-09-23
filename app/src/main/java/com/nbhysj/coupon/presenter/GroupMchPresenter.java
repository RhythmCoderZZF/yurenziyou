package com.nbhysj.coupon.presenter;
import com.nbhysj.coupon.contract.GroupMchContract;

/**
 * @auther：hysj created on 2019/09/21
 * description：组合Presenter
 */
public class GroupMchPresenter extends GroupMchContract.Presenter {

    @Override
    public void getGroupMchHomePage() {
        mRxManager.add(mModel.getGroupMchHomePage().subscribe(res -> mView.getGroupMchHomePageResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getGroupMchDetail(int packageId) {
        mRxManager.add(mModel.getGroupMchDetail(packageId).subscribe(res -> mView.getGroupMchDetailResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
