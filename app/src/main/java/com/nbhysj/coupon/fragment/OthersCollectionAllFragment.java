package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MineCollectionAllItemAdapter;
import com.nbhysj.coupon.adapter.OthersCollectionAllItemAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MineContract;
import com.nbhysj.coupon.contract.OthersHomePageContract;
import com.nbhysj.coupon.model.MineModel;
import com.nbhysj.coupon.model.OthersHomePageModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.MineCollectionAllResponse;
import com.nbhysj.coupon.model.response.MineCollectionDetailResponse;
import com.nbhysj.coupon.model.response.MinePostZanListResponse;
import com.nbhysj.coupon.model.response.UserPersonalHomePageResponse;
import com.nbhysj.coupon.model.response.ZanAndCollectionResponse;
import com.nbhysj.coupon.presenter.MinePresenter;
import com.nbhysj.coupon.presenter.OthersHomePagePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

import static com.alibaba.fastjson.util.IOUtils.UTF8;

public class OthersCollectionAllFragment extends BaseFragment<OthersHomePagePresenter, OthersHomePageModel> implements OthersHomePageContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.rv_mine_collection_all)
    RecyclerView mRvMineCollectionAll;

    private List<MineCollectionAllResponse> mineCollectionAllList;
    private OthersCollectionAllItemAdapter collectionAllItemAdapter;

    private boolean visibleToUser;

    private int userId;
    public OthersCollectionAllFragment() {
        // Required empty public constructor
    }

    public static OthersCollectionAllFragment newInstance(int userId) {
        OthersCollectionAllFragment fragment = new OthersCollectionAllFragment();
        Bundle args = new Bundle();
        args.putInt("userId", userId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        EventBus.getDefault().register(this);
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
        userId = getArguments().getInt("userId",0);

        if(mineCollectionAllList == null){

            mineCollectionAllList = new ArrayList<>();

        } else {

            mineCollectionAllList.clear();
        }

        //我的收藏全部
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvMineCollectionAll.setLayoutManager(layoutManager);
        collectionAllItemAdapter = new OthersCollectionAllItemAdapter(getActivity());
        collectionAllItemAdapter.setCollectionAllList(mineCollectionAllList);
        mRvMineCollectionAll.setAdapter(collectionAllItemAdapter);

    }

    @Override
    public void initData() {
        getOtherCollectionAllList();
    }

    @Override
    public void lazyInitView(View view) {

    }

    @Override
    public void deletePostResult(BackResult res) {

    }

    @Override
    public void getOthersHomePageInfoResult(BackResult<UserPersonalHomePageResponse> res) {

    }

    @Override
    public void userFollowResult(BackResult<FollowUserStatusResponse> res) {

    }

    @Override
    public void getOtherCollectionAllResult(ResponseBody response) {

        String json = getResponseBody(response);
        JSONObject jsonObject = JSONObject.parseObject(json);
        int code = jsonObject.getInteger("code");
        String msg = jsonObject.getString("msg");
        switch (code) {
            case Constants.SUCCESS_CODE:
                try {

                    JSONArray data = jsonObject.getJSONArray("data");
                    String JSONStr = JSON.toJSONString(data);
                    mineCollectionAllList = JSON.parseObject(JSONStr,new TypeReference<List<MineCollectionAllResponse>>(){});

                    if(mineCollectionAllList != null)
                    {
                        collectionAllItemAdapter.setCollectionAllList(mineCollectionAllList);
                        collectionAllItemAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 201:

                break;
            case Constants.USER_NOT_LOGIN_CODE:

                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(msg));
                break;
        }
    }

    @Override
    public void getOthersPostShareListResult(ResponseBody response) {

    }

    @Override
    public void getOtherBeforeZanResult(BackResult<MinePostZanListResponse> res) {

    }

    @Override
    public void getOtherFindFavoritesListResult(BackResult<FavoritesListResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    public void getOtherCollectionAllList(){

        if(validateInternet()){

            mPresenter.getOtherCollectionAllList(userId);
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
                getOtherCollectionAllList();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static String getResponseBody(ResponseBody responseBody) {

        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE); // Buffer the entire body.
        } catch (IOException e) {
            e.printStackTrace();
        }
        Buffer buffer = source.buffer();

        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                e.printStackTrace();
            }
        }
        return buffer.clone().readString(charset);
    }
}
