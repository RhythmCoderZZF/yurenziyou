package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.FrequentlyUsedInfoContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.ContactsInfoRequest;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.request.RecipientsInfoRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.ContactsInfoResponse;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.model.response.RecipientsInfoResponse;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;

/**
 * created by hysj at 2019/03/15.
 * description :常用信息模块(1.常用旅客信息 2.常用联系人信息 3.收件人信息)Model层
 */

public class FrequentlyUsedInfoModel implements FrequentlyUsedInfoContract.Model {

    @Override
    public Observable<BackResult<TravellerInfoResponse>> getUserTravellerList(int userId, int page, int pageSize) {
        return Api.getInstance().apiService.getUserTravellerList(userId, page, pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> addUserTraveller(TravellerInfoRequest addTravellerInfoRequest) {
        return Api.getInstance().apiService.addTravellerInfo(addTravellerInfoRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> updateUserTraveller(TravellerInfoRequest updateTravellerRequest) {
        return Api.getInstance().apiService.updateTravellerInfo(updateTravellerRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> deleteUserTraveller(DeleteTravellerInfoRequest deleteTravellerInfoRequest) {
        return Api.getInstance().apiService.deleteTravellerInfo(deleteTravellerInfoRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<ContactsInfoResponse>> getUserContactsList(int userId) {
        return Api.getInstance().apiService.getUserContactsList(userId).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> addUserContacts(ContactsInfoRequest contactsInfoRequest) {
        return Api.getInstance().apiService.addUserContactsInfo(contactsInfoRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> updateUserContacts(ContactsInfoRequest contactsInfoRequest) {
        return Api.getInstance().apiService.updateContactsInfo(contactsInfoRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> deleteUserContacts(DeleteTravellerInfoRequest deleteTravellerInfoRequest) {
        return Api.getInstance().apiService.deleteContactsInfo(deleteTravellerInfoRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> addRecipientsInfo(RecipientsInfoRequest recipientsInfoRequest) {
        return null;
    }

    @Override
    public Observable<BackResult> updateRecipientsInfo(RecipientsInfoRequest recipientsInfoRequest) {
        return null;
    }

    @Override
    public Observable<BackResult> deleteRecipientsInfo(DeleteTravellerInfoRequest deleteTravellerInfoRequest) {
        return null;
    }

    @Override
    public Observable<BackResult<List<RecipientAddressResponse>>> getRecipientsAddress() {
        return Api.getInstance().apiService.getRecipientsAddress().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<RecipientsInfoResponse>> getRecipientsInfoList(int userId, String mobile, int page, int pageSize) {
        return Api.getInstance().apiService.getRecipientsInfoList(userId, mobile, page, pageSize).compose(RxSchedulers.io_main());
    }
}
