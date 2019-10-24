package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FmPagerAdapter;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.widget.MyIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Function:
 */

public class RecommendFragment extends BaseFragment {

    @BindView(R.id.indicator)
    MyIndicator mIndicator;
    @BindView(R.id.pager)
    ViewPager pager;
    private FragmentPagerAdapter adapter;
    private List<HomePageResponse.SmallTagEntity> tagList;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    HomeRecommendFragment myRecommendFragment;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initPresenter() {

    }

    public void newInstance(HomePageResponse.ResultBean.PostsTagsBean postsTagsBean) {
        if (tagList == null) {

            tagList = new ArrayList<HomePageResponse.SmallTagEntity>();
        } else {
            tagList.clear();
        }

        tagList = postsTagsBean.getSmallTagList();

        for (int i = 0; i < tagList.size(); i++) {
            myRecommendFragment = new HomeRecommendFragment();
            fragments.add(myRecommendFragment);
        }
        pager.setAdapter(new FmPagerAdapter(fragments, tagList, getActivity().getSupportFragmentManager()));
        mIndicator.setViewPager(pager);
    }

    @Override
    public void initView(View v) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            HomePageResponse.ResultBean.PostsTagsBean postsTagsBean = (HomePageResponse.ResultBean.PostsTagsBean) bundle.getSerializable("postsTagsBean");
            System.out.print(postsTagsBean);
        }

        adapter = new RecommendFragmentManager(getChildFragmentManager());
       // mIndicator.onPageSelected(1);
        mIndicator.setMyOnPageChangeListener(new MyIndicator.MyOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                myRecommendFragment.newInstance(position, tagList.get(position).getId());
                // pager.setCurrentItem(position);
                //   MyRecommendFragment myRecommendFragment = new MyRecommendFragment();
                // myRecommendFragment.newInstance(position, tagList.get(position).getId());
                // ((MyFragmentManager) adapter).setData(smallTagList,recommendList);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mIndicator.setOnClickListenerCallbackListener(new MyIndicator.OnClickListenerCallbackListener() {
            @Override
            public void setOnClickListenerCallback(int position) {

                myRecommendFragment.newInstance(position, tagList.get(position).getId());
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void lazyInitView(View view) {

    }

}
