package com.nbhysj.coupon.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.JsonArray;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ShareAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MineContract;
import com.nbhysj.coupon.model.MineModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.MineCollectionAllResponse;
import com.nbhysj.coupon.model.response.MineCollectionDetailResponse;
import com.nbhysj.coupon.model.response.MinePostZanListResponse;
import com.nbhysj.coupon.model.response.MyPostShareBean;
import com.nbhysj.coupon.model.response.MyPostShareResponse;
import com.nbhysj.coupon.presenter.MinePresenter;
import com.nbhysj.coupon.view.MyRecycleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

import static com.alibaba.fastjson.util.IOUtils.UTF8;

/**
 * @auther：hysj created on 2019/04/14
 * description：分享
 */
public class ShareFragment extends BaseFragment<MinePresenter, MineModel> implements MineContract.View {

    @BindView(R.id.rv_share)
    RecyclerView mRvShare;

    //暂无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    private List<MyPostShareResponse> myPostShareList;
    private ShareAdapter shareAdapter;

    private boolean visibleToUser;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_share;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView(View v) {
        EventBus.getDefault().register(this);
        //getHomeAttention();
        if (myPostShareList == null) {

            myPostShareList = new ArrayList<>();
        } else {
            myPostShareList.clear();
        }

        getMyPostShareList();
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置布局管理器
        mRvShare.setLayoutManager(layoutManager);

        shareAdapter = new ShareAdapter(getActivity());
        shareAdapter.setShareList(myPostShareList);
        mRvShare.setAdapter(shareAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void getMinePostZanListResult(BackResult<MinePostZanListResponse> res) {

    }

    @Override
    public void getMyPostShareListResult(ResponseBody response) {
        String json = getResponseBody(response);
        JSONObject jsonObject = JSONObject.parseObject(json);
        int code = jsonObject.getInteger("code");
        String msg = jsonObject.getString("msg");
        switch (code) {
            case Constants.SUCCESS_CODE:
                try {

                    JSONArray data = jsonObject.getJSONArray("data");
                    String JSONStr = JSON.toJSONString(data);
                    myPostShareList = JSON.parseObject(JSONStr,new TypeReference<List<MyPostShareResponse>>(){});

                    if(myPostShareList != null)
                    {
                        mRlytNoData.setVisibility(View.GONE);
                        shareAdapter.setShareList(myPostShareList);
                        shareAdapter.notifyDataSetChanged();
                    } else {

                        mRlytNoData.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(msg));
                break;
        }
    }

    @Override
    public void getMineCollectionAllListResult(BackResult<List<MineCollectionAllResponse>> res) {

    }

    @Override
    public void getMineCollectionDetailResult(BackResult<MineCollectionDetailResponse> res) {

    }

    @Override
    public void collectionMchBatchDeleteContentResult(BackResult res) {

    }

    @Override
    public void collectionPostsBatchDeleteResult(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    public void getMyPostShareList() {

        if (validateInternet()) {

            mPresenter.getMyPostShareList();
        }
    }

    @Override
    public void lazyInitView(View view) {

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
                myPostShareList.clear();
                shareAdapter.notifyDataSetChanged();
                getMyPostShareList();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
