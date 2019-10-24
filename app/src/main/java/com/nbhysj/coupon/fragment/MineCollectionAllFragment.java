package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MineCollectionAllItemAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MineContract;
import com.nbhysj.coupon.model.MineModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MineCollectionAllResponse;
import com.nbhysj.coupon.model.response.MineCollectionDetailResponse;
import com.nbhysj.coupon.model.response.MinePostZanListResponse;
import com.nbhysj.coupon.model.response.UserPersonalHomePageResponse;
import com.nbhysj.coupon.presenter.MinePresenter;
import com.nbhysj.coupon.ui.UserPersonalHomePageActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.ResponseBody;

public class MineCollectionAllFragment extends BaseFragment<MinePresenter, MineModel> implements MineContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.rv_mine_collection_all)
    RecyclerView mRvMineCollectionAll;

    private List<MineCollectionAllResponse> mineCollectionAllList;
    private MineCollectionAllItemAdapter collectionAllItemAdapter;

    private boolean visibleToUser;
    public MineCollectionAllFragment() {
        // Required empty public constructor
    }

    public static MineCollectionAllFragment newInstance(String param1, String param2) {
        MineCollectionAllFragment fragment = new MineCollectionAllFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collection_all;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView(View v) {

        if(mineCollectionAllList == null){

            mineCollectionAllList = new ArrayList<>();

        } else {

            mineCollectionAllList.clear();
        }

        //我的收藏全部
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvMineCollectionAll.setLayoutManager(layoutManager);
        collectionAllItemAdapter = new MineCollectionAllItemAdapter(getActivity());
        collectionAllItemAdapter.setCollectionAllList(mineCollectionAllList);
        mRvMineCollectionAll.setAdapter(collectionAllItemAdapter);

    }

    @Override
    public void initData() {
        getMineCollectionAllList();
    }

    @Override
    public void lazyInitView(View view) {

    }

    @Override
    public void getMyPostShareListResult(ResponseBody res) {

    }

    @Override
    public void getMinePostZanListResult(BackResult<MinePostZanListResponse> res) {

    }

    @Override
    public void collectionMchBatchDeleteContentResult(BackResult res) {

    }

    @Override
    public void getMineCollectionAllListResult(BackResult<List<MineCollectionAllResponse>> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    mineCollectionAllList = res.getData();

                    if(mineCollectionAllList != null)
                    {
                        collectionAllItemAdapter.setCollectionAllList(mineCollectionAllList);
                        collectionAllItemAdapter.notifyDataSetChanged();
                    }

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
    public void getMineCollectionDetailResult(BackResult<MineCollectionDetailResponse> res) {

    }

    @Override
    public void collectionPostsBatchDeleteResult(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    public void getMineCollectionAllList(){

        if(validateInternet()){

            mPresenter.getMineCollectionAllList();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visibleToUser = isVisibleToUser;
    }

    @Subscribe
    public void onEvent(String mineFragmentRefresh) {

        if(visibleToUser)
        {
            if(mineFragmentRefresh.equals("mineFragmentRefresh"))
            {
                getMineCollectionAllList();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
