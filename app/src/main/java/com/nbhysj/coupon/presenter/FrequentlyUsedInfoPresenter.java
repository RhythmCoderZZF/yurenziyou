package com.nbhysj.coupon.presenter;

import com.nbhysj.coupon.contract.FrequentlyUsedInfoContract;
import com.nbhysj.coupon.model.request.ContactsInfoRequest;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;

/**
 * @auther：hysj created on 2019/03/05
 * description：常用信息模块(1.常用旅客信息 2.常用联系人信息 3.收件人信息)Presenter
 */
public class FrequentlyUsedInfoPresenter extends FrequentlyUsedInfoContract.Presenter {

    @Override
    public void getUserTravellerList(int userId, int page, int pageSize) {
        mRxManager.add(mModel.getUserTravellerList(userId, page, pageSize).subscribe(res -> mView.getUserTravellerListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void addUserTraveller(TravellerInfoRequest addTravellerRequest) {
        mRxManager.add(mModel.addUserTraveller(addTravellerRequest).subscribe(res -> mView.addUserTravellerResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void updateUserTraveller(TravellerInfoRequest updateTravellerRequest) {
        mRxManager.add(mModel.updateUserTraveller(updateTravellerRequest).subscribe(res -> mView.updateUserTravellerResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void deleteUserTraveller(DeleteTravellerInfoRequest deleteTravellerInfoRequest) {
        mRxManager.add(mModel.deleteUserTraveller(deleteTravellerInfoRequest).subscribe(res -> mView.deleteUserTravellerResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getUserContactsList(int userId) {
        mRxManager.add(mModel.getUserContactsList(userId).subscribe(res -> mView.getUserContactsListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void addUserContacts(ContactsInfoRequest contactsInfoRequest) {
        mRxManager.add(mModel.addUserContacts(contactsInfoRequest).subscribe(res -> mView.addUserContactsResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void updateUserContacts(ContactsInfoRequest contactsInfoRequest) {
        mRxManager.add(mModel.updateUserContacts(contactsInfoRequest).subscribe(res -> mView.updateUserContactsResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void deleteUserContacts(DeleteTravellerInfoRequest deleteTravellerInfoRequest) {
        mRxManager.add(mModel.deleteUserContacts(deleteTravellerInfoRequest).subscribe(res -> mView.deleteUserContactsResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getRecipientsAddress() {
        mRxManager.add(mModel.getRecipientsAddress().subscribe(res -> mView.getRecipientsAddressResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void getRecipientsInfoList(int userId, String mobile, int page, int pageSize) {
        mRxManager.add(mModel.getRecipientsInfoList(userId, mobile, page, pageSize).subscribe(res -> mView.getRecipientsInfoListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
