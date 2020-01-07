package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.CollectionSelectAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.model.HomePageModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CollectionTagResponse;
import com.nbhysj.coupon.model.response.FavoritesCollectionResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.HomePageAllSearchResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageTypeSearchResponse;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.presenter.HomePagePresenter;
import com.nbhysj.coupon.widget.MyIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/11/26
 * description：他人首页 收藏
 */
public class OthersCollectionFragment extends BaseFragment<HomePagePresenter, HomePageModel> implements HomePageContract.View {

    //选择项
    @BindView(R.id.indicator_collection_select_option)
    MyIndicator mIndicatorCollectionSelectOption;

    //收藏
    @BindView(R.id.view_pager_collection)
    ViewPager mViewPagerCollection;

    //收藏
    @BindView(R.id.frame_collection)
    FrameLayout FrameLayoutCollection;

    private CollectionSelectAdapter collectionSelectAdapter;
    private ArrayList<Fragment> fragments;

    private int userId;

    public static OthersCollectionFragment newInstance(int userId) {
        OthersCollectionFragment fragment = new OthersCollectionFragment();
        Bundle args = new Bundle();
        args.putInt("userId", userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collection;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView(View v) {

        userId = getArguments().getInt("userId",0);

    }

    @Override
    public void initData() {
        fragments = new ArrayList<>();
        List<CollectionTagResponse> tagList = new ArrayList<>();
        CollectionTagResponse share = new CollectionTagResponse();
        share.setTagName("全部");

        CollectionTagResponse album = new CollectionTagResponse();
        album.setTagName("专辑");

        tagList.add(share);
        tagList.add(album);
        fragments.add(new OthersCollectionAllFragment().newInstance(userId));
        fragments.add(new OthersCollectionAlbumFragment().newInstance(userId));

        collectionSelectAdapter = new CollectionSelectAdapter(getChildFragmentManager(), fragments, tagList);
        mViewPagerCollection.setOffscreenPageLimit(0);
        mViewPagerCollection.setAdapter(collectionSelectAdapter);
        mViewPagerCollection.setCurrentItem(0, false);
        mIndicatorCollectionSelectOption.setViewPager(mViewPagerCollection);


        mIndicatorCollectionSelectOption.setOnClickListenerCallbackListener(new MyIndicator.OnClickListenerCallbackListener() {
            @Override
            public void setOnClickListenerCallback(int position) {

            }
        });

    }

    @Override
    public void getHomePageIndexResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void queryByTopicResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void getPostInfoResult(BackResult<PostInfoDetailResponse> res) {

    }

    @Override
    public void userFollowResult(BackResult<FollowUserStatusResponse> res) {

    }

    @Override
    public void postOprateResult(BackResult res) {

    }

    @Override
    public void postsCommentResult(BackResult res) {

    }

    @Override
    public void getHomePageSearchAllResult(BackResult<HomePageAllSearchResponse> res) {

    }

    @Override
    public void getHomePageSearchByTypeResult(BackResult<HomePageTypeSearchResponse> res) {

    }

    @Override
    public void getHomeAttentionResult(BackResult<HomePageResponse> res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    System.out.print(res);

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
    public void postCollectionResult(BackResult<FavoritesCollectionResponse> res) {

    }

    @Override
    public void getFavoritesListResult(BackResult<FavoritesListResponse> res) {

    }

    @Override
    public void getUnReadMessageListResult(BackResult<Integer> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    @Override
    public void lazyInitView(View view) {

    }
}
