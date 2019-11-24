package com.nbhysj.coupon.model;


import com.nbhysj.coupon.contract.MineContract;
import com.nbhysj.coupon.framework.Api;
import com.nbhysj.coupon.framework.helper.RxSchedulers;
import com.nbhysj.coupon.model.request.CollectionBatchMchDeleteRequest;
import com.nbhysj.coupon.model.request.CollectionBatchPostsDeleteRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MineCollectionAllResponse;
import com.nbhysj.coupon.model.response.MineCollectionDetailResponse;
import com.nbhysj.coupon.model.response.MinePostZanListResponse;
import com.nbhysj.coupon.model.response.ZanAndCollectionResponse;

import java.util.List;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * created by hysj at 2019/10/16.
 * description :我的模块Model层
 */

public class MineModel implements MineContract.Model {

    @Override
    public Observable<ResponseBody> getMyPostShareList() {
        return Api.getInstance().apiService.getMyPostShareList().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MinePostZanListResponse>> getMinePostZanList(int pageNo,int pageSize) {
        return Api.getInstance().apiService.getMinePostZanList(pageNo,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<ResponseBody> getMineCollectionAllList() {
        return Api.getInstance().apiService.getMineCollectionAllList().compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<MineCollectionDetailResponse>> getMineCollectionDetail(String type,int pageNo,int pageSize) {
        return Api.getInstance().apiService.getMineCollectionDetail(type,pageNo,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> collectionBatchDeleteContentRequest(CollectionBatchMchDeleteRequest collectionBatchDeleteRequest) {
        return Api.getInstance().apiService.collectionMchBatchDeleteContentRequest(collectionBatchDeleteRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult> collectionPostsBatchDeleteRequest(CollectionBatchPostsDeleteRequest collectionBatchDeleteRequest) {
        return Api.getInstance().apiService.collectionPostsBatchDeleteRequest(collectionBatchDeleteRequest).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<ZanAndCollectionResponse>> getZanMsgList(int pageNo, int pageSize) {
        return Api.getInstance().apiService.getZanMsgList(pageNo,pageSize).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BackResult<ZanAndCollectionResponse>> getCollectionMsgList(int pageNo, int pageSize) {
        return Api.getInstance().apiService.getCollectionMsgList(pageNo,pageSize).compose(RxSchedulers.io_main());
    }
}
