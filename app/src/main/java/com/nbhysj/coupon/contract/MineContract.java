package com.nbhysj.coupon.contract;

import com.nbhysj.coupon.framework.BaseModel;
import com.nbhysj.coupon.framework.BasePresenter;
import com.nbhysj.coupon.framework.BaseView;
import com.nbhysj.coupon.model.request.CollectionBatchMchDeleteRequest;
import com.nbhysj.coupon.model.request.CollectionBatchPostsDeleteRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MineCollectionAllResponse;
import com.nbhysj.coupon.model.response.MineCollectionDetailResponse;
import com.nbhysj.coupon.model.response.MinePostZanListResponse;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @auther：hysj created on 2019/10/16
 * description：我的模块
 */
public interface MineContract {

    /**
     * 数据和业务模型
     **/
    interface Model extends BaseModel {

        //获取分享列表
        Observable<ResponseBody> getMyPostShareList();

        //获取赞列表
        Observable<BackResult<MinePostZanListResponse>> getMinePostZanList(int pageNo,int pageSize);

        //获取赞列表
        Observable<ResponseBody> getMineCollectionAllList();

        //获取收藏详情
        Observable<BackResult<MineCollectionDetailResponse>> getMineCollectionDetail(String type,int pageNo,int pageSize);

        //收藏批量删除商户
        Observable<BackResult> collectionBatchDeleteContentRequest(CollectionBatchMchDeleteRequest collectionBatchDeleteRequest);

        //收藏批量删除帖子
        Observable<BackResult> collectionPostsBatchDeleteRequest(CollectionBatchPostsDeleteRequest collectionBatchDeleteRequest);

    }

    interface View extends BaseView {

        void getMyPostShareListResult(ResponseBody res);

        void getMinePostZanListResult(BackResult<MinePostZanListResponse> res);

        void getMineCollectionAllListResult(ResponseBody res);

        void getMineCollectionDetailResult(BackResult<MineCollectionDetailResponse> res);

        void collectionMchBatchDeleteContentResult(BackResult res);

        void collectionPostsBatchDeleteResult(BackResult res);

        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getMyPostShareList();

        public abstract void getMinePostZanList(int pageNo,int pageSize);

        public abstract void getMineCollectionAllList();

        public abstract void getMineCollectionDetail(String type,int pageNo,int pageSize);

        public abstract void collectionMchBatchDeleteContentRequest(CollectionBatchMchDeleteRequest collectionBatchDeleteRequest);

        public abstract void collectionPostsBatchDeleteRequest(CollectionBatchPostsDeleteRequest  collectionBatchDeleteRequest);

    }
}