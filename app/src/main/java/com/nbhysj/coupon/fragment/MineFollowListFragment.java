package com.nbhysj.coupon.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MineFollowFansHeaderListAdapter;
import com.nbhysj.coupon.adapter.MineFollowHeaderAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MessageContract;
import com.nbhysj.coupon.model.MessageModel;
import com.nbhysj.coupon.model.response.AttentionResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.BroadcastResponse;
import com.nbhysj.coupon.model.response.CommentAndAnswerResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.MessageResponse;
import com.nbhysj.coupon.model.response.StrategyBean;
import com.nbhysj.coupon.model.response.UserFansFollowBean;
import com.nbhysj.coupon.model.response.UserFansFollowResponse;
import com.nbhysj.coupon.model.response.UserFollowResponse;
import com.nbhysj.coupon.model.response.ZanAndCollectionResponse;
import com.nbhysj.coupon.presenter.MessagePresenter;
import com.nbhysj.coupon.ui.NewFansActivity;
import com.nbhysj.coupon.ui.StrategyActivity;
import com.nbhysj.coupon.ui.UserPersonalHomePageActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import butterknife.BindView;

/**
 * @auther：hysj created on 2019/06/18
 * description：关注列表
 */
public class MineFollowListFragment extends BaseFragment<MessagePresenter, MessageModel> implements MessageContract.View {
    private static final String ORDER_STATUS_CODE = "orderStatus";
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //我的粉丝和关注
    @BindView(R.id.rv_mine_fans_and_follow_list)
    RecyclerView mRvMineFansAndFollowList;

    MineFollowFansHeaderListAdapter mineFollowFooterAdapter;

    private MineFollowHeaderAdapter mineFollowFansHeaderListAdapter;

    private int mPageNo = 1;

    private int mPageSize = 10;

    int mTotalPageCount;

    private List<UserFansFollowBean> userFansFollowFooterList;

    private List<UserFansFollowBean> userFansFollowHeaderList;

    private View header;

    private int mHeaderPosition;

    private int mFooterPosition;

    //是否是头部用户关注
    private boolean isHeaderUserFollow;

    private boolean isOnLoadMore = false;
    public MineFollowListFragment() {

    }

    public static MineFollowListFragment newInstance(String orderStatus) {

        MineFollowListFragment fragment = new MineFollowListFragment();
        Bundle args = new Bundle();
        args.putString(ORDER_STATUS_CODE, orderStatus);
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            long time = new Date().getTime();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_follow_sub_list;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }


    @Override
    protected void initView(View v) {
        getAttentionInit();
        if (userFansFollowFooterList == null) {

            userFansFollowFooterList = new ArrayList<>();
        } else {
            userFansFollowFooterList.clear();
        }

        if (userFansFollowHeaderList == null) {

            userFansFollowHeaderList = new ArrayList<>();
        } else {
            userFansFollowHeaderList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvMineFansAndFollowList.setLayoutManager(linearLayoutManager);
        mineFollowFooterAdapter = new MineFollowFansHeaderListAdapter(getActivity(), new MineFollowFansHeaderListAdapter.FollowListener() {
            @Override
            public void setFollowListener(int userId, int position) {
                isHeaderUserFollow = false;
                mFooterPosition = position;
                userFollow(userId);
            }
        });
        mineFollowFooterAdapter.setMineFollowFansHeaderList(userFansFollowFooterList);
        mRvMineFansAndFollowList.setAdapter(mineFollowFooterAdapter);
        setHeader(mRvMineFansAndFollowList);
    }

    private void setHeader(RecyclerView view)
    {
        header = LayoutInflater.from(getActivity()).inflate(R.layout.layout_mine_follow_header_item, view, false);
        RecyclerView mRvMineFollow = header.findViewById(R.id.rv_mine_fans_and_follow_header_item);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvMineFollow.setLayoutManager(linearLayoutManager);
        mineFollowFansHeaderListAdapter = new MineFollowHeaderAdapter(getActivity(), new MineFollowHeaderAdapter.FollowListener() {
            @Override
            public void setFollowListener(int userId,int position) {
                isHeaderUserFollow = true;
                mHeaderPosition = position;
                userFollow(userId);
            }
        });
        mineFollowFansHeaderListAdapter.setUserFansFollowList(userFansFollowHeaderList);
        mRvMineFollow.setAdapter(mineFollowFansHeaderListAdapter);
    }

    @Override
    public void initData() {
        // getOrderList();
        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        userFansFollowHeaderList.clear();
                        mineFollowFansHeaderListAdapter.notifyDataSetChanged();

                        userFansFollowHeaderList.clear();
                        mineFollowFooterAdapter.notifyDataSetChanged();

                        getAttentionInit();

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
                            mSmartRefreshLayout.finishLoadMore();
                            if (mTotalPageCount == userFansFollowFooterList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getUserFollow();
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
    public void getUserFansListResult(BackResult<UserFansFollowResponse> res) {

    }

    @Override
    public void userFollowResult(BackResult<FollowUserStatusResponse> res) {
        dismissProgressDialog();

        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    FollowUserStatusResponse followUserStatusResponse = res.getData();
                    int followStatus = followUserStatusResponse.getFollowStatus();
                    userFansFollowHeaderList.clear();
                    mineFollowFansHeaderListAdapter.notifyDataSetChanged();

                    userFansFollowFooterList.clear();
                    mineFollowFooterAdapter.notifyDataSetChanged();
                    mPageNo = 1;
                    getAttentionInit();
                   /* if(isHeaderUserFollow) {
                        userFansFollowHeaderList.clear();
                        mineFollowFansHeaderListAdapter.notifyDataSetChanged();

                        userFansFollowFooterList.clear();
                        mineFollowFooterAdapter.notifyDataSetChanged();
                        mPageNo = 1;

                        getAttentionInit();
                    } else {

                        userFansFollowHeaderList.clear();
                        mineFollowFansHeaderListAdapter.notifyDataSetChanged();

                        userFansFollowFooterList.clear();
                        mineFollowFooterAdapter.notifyDataSetChanged();
                        mPageNo = 1;

                        getAttentionInit();
                    }*/
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
    public void getAttentionInitResult(BackResult<AttentionResponse> res) {
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.setNoMoreData(false);
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    AttentionResponse attentionResponse = res.getData();
                    userFansFollowHeaderList = attentionResponse.getRecommend();

                    mineFollowFansHeaderListAdapter.setUserFansFollowList(userFansFollowHeaderList);
                    mineFollowFansHeaderListAdapter.notifyDataSetChanged();

                    userFansFollowFooterList = attentionResponse.getList().getResult();
                    mineFollowFooterAdapter.setMineFollowFansHeaderList(userFansFollowFooterList);
                    mineFollowFooterAdapter.notifyDataSetChanged();

                    mineFollowFooterAdapter.setHeaderView(header);
                   // scenicSpotsListAdapter.setHeaderView(header);
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
    public void getUserFollowResult(BackResult<UserFollowResponse> res) {
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

                        userFansFollowFooterList.clear();
                        mineFollowFooterAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<UserFansFollowBean> userFansFollowList = res.getData().getResult();

                    if (userFansFollowList != null)
                    {
                        userFansFollowFooterList.addAll(userFansFollowList);
                    }

                    mineFollowFooterAdapter.setMineFollowFansHeaderList(userFansFollowFooterList);
                    mineFollowFooterAdapter.notifyDataSetChanged();

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
    public void getMessageListResult(BackResult<MessageResponse> res) {

    }

    @Override
    public void getZanAndCollectionMsgResult(BackResult<ZanAndCollectionResponse> res) {

    }

    @Override
    public void getPostsCommentAndAnswerResult(BackResult<CommentAndAnswerResponse> res) {

    }

    @Override
    public void getBroadcatMessageListResult(BackResult<BroadcastResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        Constants.getResultMsg(Constants.getResultMsg(msg));
    }

    @Override
    public void lazyInitView(View view) {

        userFansFollowHeaderList.clear();
        mineFollowFansHeaderListAdapter.notifyDataSetChanged();

        userFansFollowFooterList.clear();
        mineFollowFooterAdapter.notifyDataSetChanged();
        mPageNo = 1;

        getAttentionInit();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        //this.isVisibleToUser = isVisibleToUser;//注：关键步骤
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
        }
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //用户关注初始化接口
    public void getAttentionInit()
    {
        if(validateInternet()){

            showProgressDialog(getActivity());
            mPresenter.getAttentionInit();
        }
    }

    //用户关注
      public void userFollow(int userId)
    {
        showProgressDialog(getActivity());
        mPresenter.userFollow(userId);
    }

    public void getUserFollow(){

        mPresenter.getUserFollow(mPageNo,mPageSize);
    }

}
