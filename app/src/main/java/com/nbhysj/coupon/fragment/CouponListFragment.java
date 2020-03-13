package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.CouponListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.CouponListContract;
import com.nbhysj.coupon.dialog.CouponDescriptionDialog;
import com.nbhysj.coupon.model.CouponListModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.CouponListBean;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.model.response.UseCouponTicketResponse;
import com.nbhysj.coupon.model.response.UserOrderListResponse;
import com.nbhysj.coupon.presenter.CouponListPresenter;
import com.nbhysj.coupon.ui.MainActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * created by hysj on 2018/03/29.
 * description: 我的订单
 */
public class CouponListFragment extends BaseFragment<CouponListPresenter, CouponListModel> implements CouponListContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //优惠券列表
    @BindView(R.id.rv_my_coupon)
    RecyclerView mRvMyCouponList;
    //暂无订单数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoOrderData;
    //优惠券数据
    private List<CouponsBean> couponAllList;
    private int mPage = 1;
    private int mPageSize = 10;
    int mTotalPageCount;
    private boolean isOnLoadMore = false;
    UserOrderListResponse.OrderTypeEntity mOrderTypeEntity;
    CouponListAdapter mCouponListAdapter;
    private String mCouponReceiveStatus;
    private CouponDescriptionDialog couponDescriptionDialog;
    private static final String COUPON_RECEIVE_STATUS_CODE = "couponReceiveStatus";
    private boolean visibleToUser;

    //优惠券id
    private int couponId;
    public static CouponListFragment newInstance(String couponStatus) {
        CouponListFragment fragment = new CouponListFragment();

        Bundle args = new Bundle();
        args.putString(COUPON_RECEIVE_STATUS_CODE, couponStatus);
        fragment.setArguments(args);
        return fragment;
    }

    private String mContent = "fragmentpage";

   /* @Override
    public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
                mContent = savedInstanceState.getString(KEY_CONTENT);
          }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
         super.onSaveInstanceState(outState);
         outState.putString(KEY_CONTENT, mContent);
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_coupon_list;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView(View v) {

        mCouponReceiveStatus = getArguments().getString(COUPON_RECEIVE_STATUS_CODE, "");

        if (couponAllList == null) {

            couponAllList = new ArrayList<>();
        } else {

            couponAllList.clear();
        }

        // 创建一个线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvMyCouponList.setLayoutManager(linearLayoutManager);

        mCouponListAdapter = new CouponListAdapter(getActivity(), new CouponListAdapter.CouponToUseListener() {

            @Override
            public void setCouponToUseCallback(int position, CouponsBean couponResponse) {

              //  couponId = couponResponse.getCouponId();
                //receiveCoupon();

            }

            @Override
            public void setAllCouponUseCallback() {


                getActivity().finish();

                Intent mIntent = new Intent(Constants.BROADCAST_ACTION_MAIN_BACK);
                mIntent.putExtra(Constants.BROADCAST_ACTION_ARG_OPRATE,Constants.BROADCAST_ACTION_BACK_SHOPPING_MALL);
                getActivity().sendBroadcast(mIntent);

            }

            @Override
            public void setCouponDesCallback(String intro) {
                if (!TextUtils.isEmpty(intro))
                {
                    if (couponDescriptionDialog == null) {
                        couponDescriptionDialog = new CouponDescriptionDialog(getActivity()).builder().setContent(intro);
                    }
                    couponDescriptionDialog.show();
                }
            }
        });
        mCouponListAdapter.setCouponList(couponAllList);
        mRvMyCouponList.setAdapter(mCouponListAdapter);
    }

    @Override
    public void initData() {

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPage = 1;
                        couponAllList.clear();
                        mCouponListAdapter.notifyDataSetChanged();
                        showProgressDialog(getActivity());
                        getCouponList();

                    }
                }, 100);
            }
        });

        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isOnLoadMore = true;
                        try {
                            if (mTotalPageCount == couponAllList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                getCouponList();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 200);
            }
        });
    }

    @Override
    public void useCouponTicketResult(BackResult<UseCouponTicketResponse> res) {

    }

    @Override
    public void getCouponResult(BackResult res) {
        dismissProgressDialog();
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {




                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
        private int itemSpace;
        private int itemNum;

        /**
         * @param itemSpace item间隔
         * @param itemNum   每行item的个数
         */
        public RecyclerItemDecoration(int itemSpace, int itemNum) {
            this.itemSpace = itemSpace;
            this.itemNum = itemNum;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = itemSpace;
                /*if (parent.getChildLayoutPosition(view)%itemNum == 0){  //parent.getChildLayoutPosition(view) 获取view的下标
                    outRect.left = 0;
                } else {
                    outRect.left = itemSpace;
                }*/
            outRect.left = itemSpace;
            outRect.right = itemSpace;
            //outRect.right = itemSpace;
        }
    }

    @Override
    public void getCouponCenterResult(BackResult<List<CouponsBean>> res) {

    }

    @Override
    public void lazyInitView(View view) {

        if (couponAllList != null)
        {
            couponAllList.clear();
        }

        if(mCouponListAdapter != null){

            mCouponListAdapter.notifyDataSetChanged();
        }

        showProgressDialog(getActivity());
        getCouponList();
    }

    @Override
    public void getCouponListResult(BackResult<CouponListBean> res) {

        dismissProgressDialog();
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    if (isOnLoadMore) {

                        mSmartRefreshLayout.finishLoadMore();
                    } else {

                        couponAllList.clear();
                        mCouponListAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }
                    CouponListBean couponListResponse = res.getData();
                    BasePaginationResult paginationResult = couponListResponse.getPage();
                    mTotalPageCount = paginationResult.getPageCount();

                    if (mTotalPageCount == 0)
                    {
                        mRlytNoOrderData.setVisibility(View.VISIBLE);
                    } else {
                        mRlytNoOrderData.setVisibility(View.GONE);
                    }

                    List<CouponsBean> couponList = couponListResponse.getResult();

                    if (couponList != null)
                    {
                        couponAllList.addAll(couponList);
                    }

                    mCouponListAdapter.setCouponList(couponAllList);
                    mCouponListAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void couponExchangeResult(BackResult res) {

    }

    /**
     * 获取优惠券列表
     */
    public void getCouponList() {

        if (validateInternet()) {

            mPresenter.getCouponList(mCouponReceiveStatus,mPage,mPageSize);
        }
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visibleToUser = isVisibleToUser;
    }

    @Subscribe
    public void onEvent(String couponOprate)
    {
        if(couponOprate.equals("couponOprate"))
        {
            getCouponList();
        }


    }

}
