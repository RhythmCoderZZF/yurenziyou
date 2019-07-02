package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
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
 * @auther：hysj created on 2019/03/15
 * description：常用信息模块(1.常用旅客信息 2.常用联系人信息 3.收件人信息)
 */
public interface FrequentlyUsedInfoContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {
        //1.
        //获取旅客列表
        Observable<BackResult<TravellerInfoResponse>> getUserTravellerList(int userId, int page, int pageSize);

        //添加旅客
        Observable<BackResult> addUserTraveller(TravellerInfoRequest addTravellerRequest);

        //修改旅客
        Observable<BackResult> updateUserTraveller(TravellerInfoRequest updateTravellerRequest);

        //删除旅客
        Observable<BackResult> deleteUserTraveller(DeleteTravellerInfoRequest deleteTravellerInfoRequest);

        //2.
        //获取联系人列表
        Observable<BackResult<ContactsInfoResponse>> getUserContactsList(int userId);

        //添加联系人
        Observable<BackResult> addUserContacts(ContactsInfoRequest contactsInfoRequest);

        //修改联系人
        Observable<BackResult> updateUserContacts(ContactsInfoRequest contactsInfoRequest);

        //删除联系人
        Observable<BackResult> deleteUserContacts(DeleteTravellerInfoRequest deleteTravellerInfoRequest);

        //3.
        //获取收件人列表
        Observable<BackResult<RecipientsInfoResponse>> getRecipientsInfoList(int userId, String mobile, int page, int pageSize);

        Observable<BackResult> addRecipientsInfo(RecipientsInfoRequest recipientsInfoRequest);

        Observable<BackResult> updateRecipientsInfo(RecipientsInfoRequest recipientsInfoRequest);

        Observable<BackResult> deleteRecipientsInfo(DeleteTravellerInfoRequest deleteTravellerInfoRequest);

        Observable<BackResult<List<RecipientAddressResponse>>> getRecipientsAddress();
    }

    interface View extends BaseView {
        //1.旅客
        void getUserTravellerListResult(BackResult<TravellerInfoResponse> res);

        void addUserTravellerResult(BackResult res);

        void updateUserTravellerResult(BackResult res);

        void deleteUserTravellerResult(BackResult res);

        //2.联系人
        void getUserContactsListResult(BackResult<ContactsInfoResponse> res);

        void addUserContactsResult(BackResult res);

        void updateUserContactsResult(BackResult res);

        void deleteUserContactsResult(BackResult res);

        //3.收件人
        void getRecipientsInfoListResult(BackResult<RecipientsInfoResponse> res);

    /*   void addUserContactsResult(BackResult res);

        void updateUserContactsResult(BackResult res);

        void deleteUserContactsResult(BackResult res);*/

        void getRecipientsAddressResult(BackResult<List<RecipientAddressResponse>> res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getUserTravellerList(int userId, int page, int pageSize);

        public abstract void addUserTraveller(TravellerInfoRequest addTravellerRequest);

        public abstract void updateUserTraveller(TravellerInfoRequest updateTravellerRequest);

        public abstract void deleteUserTraveller(DeleteTravellerInfoRequest deleteTravellerInfoRequest);

        public abstract void getUserContactsList(int userId);

        public abstract void addUserContacts(ContactsInfoRequest contactsInfoRequest);

        public abstract void updateUserContacts(ContactsInfoRequest contactsInfoRequest);

        //删除联系人
        public abstract void deleteUserContacts(DeleteTravellerInfoRequest deleteTravellerInfoRequest);

        //获取收件人列表
        public abstract void getRecipientsInfoList(int userId, String mobile, int page, int pageSize);

        //获取收件人地址列表
        public abstract void getRecipientsAddress();
    }
}