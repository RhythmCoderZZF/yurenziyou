package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MineCollectionAllItemAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MineContract;
import com.nbhysj.coupon.model.MineModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MineCollectionAllResponse;
import com.nbhysj.coupon.model.response.MineCollectionDetailResponse;
import com.nbhysj.coupon.model.response.MinePostZanListResponse;
import com.nbhysj.coupon.model.response.MyPostShareResponse;
import com.nbhysj.coupon.model.response.UserPersonalHomePageResponse;
import com.nbhysj.coupon.model.response.ZanAndCollectionResponse;
import com.nbhysj.coupon.presenter.MinePresenter;
import com.nbhysj.coupon.ui.UserPersonalHomePageActivity;

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
    public void getMineCollectionAllListResult(ResponseBody response) {

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
    public void getMineCollectionDetailResult(BackResult<MineCollectionDetailResponse> res) {

    }

    @Override
    public void collectionPostsBatchDeleteResult(BackResult res) {

    }

    @Override
    public void getZanMsgListResult(BackResult<ZanAndCollectionResponse> res) {

    }

    @Override
    public void getCollectionMsgListResult(BackResult<ZanAndCollectionResponse> res) {

    }

    @Override
    public void deletePostResult(BackResult res) {

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
