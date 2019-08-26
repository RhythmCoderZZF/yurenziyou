package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.BasicApplication;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.PublishLocationListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.PublishPostContract;
import com.nbhysj.coupon.greendao.DaoSession;
import com.nbhysj.coupon.model.PublishPostModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.HotTagsTopicBean;
import com.nbhysj.coupon.model.response.MerchantListResponse;
import com.nbhysj.coupon.model.response.SearchBean;
import com.nbhysj.coupon.model.response.TagTopicSearchResponse;
import com.nbhysj.coupon.presenter.PublishPostPresenter;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/10
 * description：商户位置(发布分享)
 */
public class PublishLocationSearchActivity extends BaseActivity<PublishPostPresenter, PublishPostModel> implements PublishPostContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    //推荐位置列表
    @BindView(R.id.rv_recommend_location)
    RecyclerView mRvRecommendLocation;
    //取消
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    //历史标签
    @BindView(R.id.flowlayout_historical_label)
    TagFlowLayout mTagHistoryLabel;
    //商户搜索
    @BindView(R.id.et_search)
    EditText mEdtMerchantSearch;
    //历史标签
    @BindView(R.id.tv_historical_label)
    TextView mTvHistoricalLabel;
    //为您推荐
    @BindView(R.id.tv_recommend_merchant)
    TextView mTvRecommendMerchant;
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    private boolean isOnLoadMore = false;
    List<MerchantListResponse.MerchantEntity> merchantEntityList;

    private String mchNameKey = "";

    private int mTotalCount;
    //当前页数
    private int mPage = 1;
    //每页条数
    private int mPageSize = 10;

    private List<SearchBean> merchantList;

    private List<SearchBean> merchantAllList;

    private PublishLocationListAdapter publishLocationListAdapter;

    private boolean isContainSameMerchant = false;

    @Override
    public int getLayoutId() {

        return R.layout.activity_publish_location_search;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        BlurBehind.getInstance()
                .withAlpha(100)
                .withFilterColor(Color.parseColor("#000000"))
                .setBackground(PublishLocationSearchActivity.this);
        //沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }

        if (merchantEntityList == null) {

            merchantEntityList = new ArrayList<>();
        } else {
            merchantEntityList.clear();
        }

        if (merchantList == null) {

            merchantList = new ArrayList<>();
        } else {
            merchantList.clear();
        }

        if (merchantAllList == null) {

            merchantAllList = new ArrayList<>();
        } else {
            merchantAllList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PublishLocationSearchActivity.this);
        mRvRecommendLocation.setLayoutManager(linearLayoutManager);
        publishLocationListAdapter = new PublishLocationListAdapter(PublishLocationSearchActivity.this, new PublishLocationListAdapter.MerchantSelectListener() {
            @Override
            public void setMerchantSelectListener(MerchantListResponse.MerchantEntity merchant) {

                addData(merchantList, merchant);

                Bundle bundle = new Bundle();
                Intent intent = new Intent();
                bundle.putSerializable("merchant", merchant);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
        publishLocationListAdapter.setPublishRecommendLocationList(merchantEntityList);
        mRvRecommendLocation.setAdapter(publishLocationListAdapter);

        // deleteAll();
    }

    @Override
    public void initData() {
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        merchantList = queryAll();

        if (merchantList.size() > 0) {
            mTvHistoricalLabel.setVisibility(View.VISIBLE);
        } else {
            mTvHistoricalLabel.setVisibility(View.GONE);
        }

        TagAdapter tagAdapter = new TagAdapter<SearchBean>(merchantList) {
            @Override
            public View getView(FlowLayout parent, int position, SearchBean searchBean) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                View view = (View) mInflater.inflate(R.layout.layout_flowlayout_tag_check_frame,
                        mTagHistoryLabel, false);
                TextView mTvTagHistoryLabel = view.findViewById(R.id.tv_flowlayout);
                String merchantName = searchBean.getSearch();
                mTvTagHistoryLabel.setText(merchantName);
                mTvTagHistoryLabel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast(PublishLocationSearchActivity.this, merchantName);
                        //  SearchBean merchantEntity = merchantList.get(position);

                  /*      merchant.setId(merchantEntity.getMerchantId());
                        merchant.setMchName(merchantEntity.getSearch());
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent();
                        bundle.putSerializable("merchant", merchant);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();*/
                    }
                });
                return mTvTagHistoryLabel;
            }
        };

        mTagHistoryLabel.setAdapter(tagAdapter);

        mTagHistoryLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                return false;
            }
        });

        getMerchantList();
        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPage = 1;
                        getMerchantList();

                        isOnLoadMore = false;
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
                            if (mTotalCount == merchantEntityList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                getMerchantList();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 200);
            }
        });

        mEdtMerchantSearch.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                //发现执行了两次因为onkey事件包含了down和up事件，所以只需要加入其中一个即可。

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(PublishLocationSearchActivity.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //进行搜索操作的方法，在该方法中可以加入mEditSearchUser的非空判断
                    mPage = 1;
                    String merchantSearch = mEdtMerchantSearch.getText().toString().trim();
                    mchNameKey = merchantSearch;
                    showProgressDialog(PublishLocationSearchActivity.this);
                    getMerchantList();
                }
                return false;
            }
        });
    }


    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void publishPostResult(BackResult res) {

    }

    @Override
    public void topicSearchResult(BackResult<TagTopicSearchResponse> res) {

    }

    @Override
    public void getMerchantListResult(BackResult<MerchantListResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                if (isOnLoadMore) {

                    mSmartRefreshLayout.finishLoadMore();
                } else {

                    merchantEntityList.clear();
                    publishLocationListAdapter.notifyDataSetChanged();
                    mSmartRefreshLayout.finishRefresh();
                    mSmartRefreshLayout.setNoMoreData(false);
                }
                MerchantListResponse merchantListResponse = res.getData();
                List<MerchantListResponse.MerchantEntity> merchantList = merchantListResponse.getResult();
                BasePaginationResult paginationResult = merchantListResponse.getPage();
                mTotalCount = paginationResult.getPageCount();
                if (mTotalCount == 0) {
                    mTvRecommendMerchant.setVisibility(View.GONE);
                    mRlytNoData.setVisibility(View.VISIBLE);
                } else {
                    mTvRecommendMerchant.setVisibility(View.VISIBLE);
                    mRlytNoData.setVisibility(View.GONE);
                }
                if (merchantList != null) {
                    merchantEntityList.addAll(merchantList);
                }
                publishLocationListAdapter.setPublishRecommendLocationList(merchantEntityList);
                publishLocationListAdapter.notifyDataSetChanged();
                break;
            default:
                mSmartRefreshLayout.finishRefresh();
                showToast(PublishLocationSearchActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getHotTagsTopicListResult(BackResult<List<HotTagsTopicBean>> res) {


    }

    @Override
    public void createTopicResult(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(PublishLocationSearchActivity.this, Constants.getResultMsg(msg));
    }

    public void getMerchantList() {

        if (validateInternet()) {

            showProgressDialog(PublishLocationSearchActivity.this);
            mPresenter.getMerchantList("330200", mchNameKey, mPage, mPageSize);
        }
    }

    private void addData(List<SearchBean> merchantList, MerchantListResponse.MerchantEntity merchant) {
        try {
            merchantAllList.clear();
            DaoSession daoSession = ((BasicApplication) getApplication()).getDaoSession();

            SearchBean searchBean = new SearchBean();
            searchBean.setSearch(merchant.getMchName());
            searchBean.setMerchantId(merchant.getMchId());
            if (merchantList.size() > 0) {
                for (int i = 0; i < merchantList.size(); i++) {
                    int id = merchantList.get(i).getMerchantId();
                    int merchantId = merchant.getMchId();
                    if (id == merchantId) {

                        isContainSameMerchant = true;
                    }
                }
                if (!isContainSameMerchant) {

                    if (merchantList.size() == 10) {

                        merchantList.remove(merchantList.get(merchantList.size() - 1));

                    }

                    merchantAllList.add(searchBean);
                    merchantAllList.addAll(merchantList);
                    deleteAll();
                    for (int i = 0; i < merchantAllList.size(); i++) {
                        SearchBean searchMerchant = merchantAllList.get(i);
                        searchMerchant.setId(null);
                        daoSession.insert(searchMerchant);
                    }
                }
            } else {

                daoSession.insert(searchBean);
            }

            isContainSameMerchant = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<SearchBean> queryAll() {
        List<SearchBean> searchs = ((BasicApplication) getApplication()).getDaoSession().loadAll(SearchBean.class);
        return searchs;
    }

    public void deleteAll() {
        DaoSession daoSession = ((BasicApplication) getApplication()).getDaoSession();
        daoSession.deleteAll(SearchBean.class);
    }
}
