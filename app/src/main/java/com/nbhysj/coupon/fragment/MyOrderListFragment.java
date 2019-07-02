package com.nbhysj.coupon.fragment;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MyOrderListAdapter;
import com.nbhysj.coupon.adapter.RecommendFriendsPictureAdapter;
import com.nbhysj.coupon.model.response.RecommendFriendsBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * created by hysj on 2018/03/29.
 * description: 我的订单
 */
public class MyOrderListFragment extends BaseFragment {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //我的订单列表
    @BindView(R.id.rv_my_order)
    RecyclerView mRvMyOrderList;
    private List<RecommendFriendsBean> recommendFriendsList;

    public static MyOrderListFragment newInstance(String content) {
        MyOrderListFragment fragment = new MyOrderListFragment();

        fragment.mContent = content;

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
    public int getLayoutId() {
        return R.layout.fragment_my_order_item;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(View v) {

        if (recommendFriendsList == null) {

            recommendFriendsList = new ArrayList<>();
        } else {

            recommendFriendsList.clear();
        }

        RecommendFriendsBean recommendFriendsBean = new RecommendFriendsBean();
        recommendFriendsBean.setImage("http://img3.redocn.com/20131012/Redocn_2013101208241467.jpg");
        recommendFriendsBean.setLove(false);
        recommendFriendsBean.setAvatar("http://qimg.hxnews.com/2019/0202/1549068681309.jpg");
        recommendFriendsBean.setName("小明");
        recommendFriendsBean.setDes("运动达人");

        RecommendFriendsBean recommendFriendsBean1 = new RecommendFriendsBean();
        recommendFriendsBean1.setImage("http://pic36.nipic.com/20131219/10512072_171745235190_2.jpg");
        recommendFriendsBean1.setLove(true);
        recommendFriendsBean1.setAvatar("http://pic32.nipic.com/20130816/2531170_195051406000_2.jpg");
        recommendFriendsBean1.setName("小张");
        recommendFriendsBean1.setDes("旅游达人");

        RecommendFriendsBean recommendFriendsBean2 = new RecommendFriendsBean();
        recommendFriendsBean2.setImage("http://pic36.nipic.com/20131219/10512072_171745235190_2.jpg");
        recommendFriendsBean2.setLove(false);
        recommendFriendsBean2.setAvatar("http://img.daimg.com/uploads/allimg/140428/3-14042Q43201.jpg");
        recommendFriendsBean2.setName("xx");
        recommendFriendsBean2.setDes("健康达人");

        RecommendFriendsBean recommendFriendsBean3 = new RecommendFriendsBean();
        recommendFriendsBean3.setImage("http://pic36.nipic.com/20131219/10512072_171745235190_2.jpg");
        recommendFriendsBean3.setLove(false);
        recommendFriendsBean3.setAvatar("http://pic32.nipic.com/20130816/2531170_195051406000_2.jpg");
        recommendFriendsBean3.setName("小陈");
        recommendFriendsBean3.setDes("购物达人");

        RecommendFriendsBean recommendFriendsBean4 = new RecommendFriendsBean();
        recommendFriendsBean4.setImage("http://pic36.nipic.com/20131219/10512072_171745235190_2.jpg");
        recommendFriendsBean4.setLove(true);
        recommendFriendsBean4.setAvatar("http://img.daimg.com/uploads/allimg/140428/3-14042Q43201.jpg");
        recommendFriendsBean4.setName("小白");
        recommendFriendsBean4.setDes("知识达人");

        recommendFriendsList.add(recommendFriendsBean);
        recommendFriendsList.add(recommendFriendsBean1);
        recommendFriendsList.add(recommendFriendsBean2);
        recommendFriendsList.add(recommendFriendsBean3);
        recommendFriendsList.add(recommendFriendsBean4);

        // 创建一个线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvMyOrderList.setLayoutManager(linearLayoutManager);

        MyOrderListAdapter myOrderListAdapter = new MyOrderListAdapter(getActivity());
        myOrderListAdapter.setMyOrderList(recommendFriendsList);
        mRvMyOrderList.setAdapter(myOrderListAdapter);
    }

    @Override
    public void initData() {
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
    public void lazyInitView(View view) {

    }
}
