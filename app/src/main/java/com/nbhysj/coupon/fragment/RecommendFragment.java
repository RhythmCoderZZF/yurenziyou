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
    /*  @Override
      public void onActivityCreated(@Nullable Bundle savedInstanceState) {
          super.onActivityCreated(savedInstanceState);
          recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
          recycler.setAdapter(new RecyclerAdapter());
      }*/


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
     /*   HomePageResponse.SmallTagEntity smallTag = new HomePageResponse().new SmallTagEntity();
        tagList.add(smallTag);
        tagList.addAll(smallTagList);*/
        // ((RecommendFragmentManager) adapter).setData(tagList, recommendList);
        for (int i = 0; i < tagList.size(); i++) {
            myRecommendFragment = new HomeRecommendFragment();
            myRecommendFragment.newInstance(getActivity());
            fragments.add(myRecommendFragment);
        }
        pager.setAdapter(new FmPagerAdapter(fragments, tagList, getActivity().getSupportFragmentManager()));
        //  pager.setAdapter(adapter);
        mIndicator.setViewPager(pager);
        // pager.setOffscreenPageLimit(0);
   /*     //            创建一个 bundle 传递 数据
        Bundle bundle = new Bundle();
        //使用bundle合适的put方法传递数据
        bundle.putSerializable("postsTagsBean", (Serializable) postsTagsBean);
//             新建一个 fragment
        RecommendFragment fragment = new RecommendFragment();

        fragment.setArguments(bundle);

        return fragment;*/
    }

    @Override
    public void initView(View v) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            HomePageResponse.ResultBean.PostsTagsBean postsTagsBean = (HomePageResponse.ResultBean.PostsTagsBean) bundle.getSerializable("postsTagsBean");
            System.out.print(postsTagsBean);
        }

        adapter = new RecommendFragmentManager(getChildFragmentManager());

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
    }

    @Override
    public void initData() {

    }

    @Override
    public void lazyInitView(View view) {

    }

}
