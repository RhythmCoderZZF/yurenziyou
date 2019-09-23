package com.nbhysj.coupon.presenter;
import com.nbhysj.coupon.contract.StrategyContract;

/**
 * @auther：hysj created on 2019/09/21
 * description：攻略Presenter
 */
public class StrategyPresenter extends StrategyContract.Presenter {

    @Override
    public void findAllStrategy(int pageNo, int pageSize) {
        mRxManager.add(mModel.findAllStrategy(pageNo,pageSize).subscribe(res -> mView.findAllStrategyResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
