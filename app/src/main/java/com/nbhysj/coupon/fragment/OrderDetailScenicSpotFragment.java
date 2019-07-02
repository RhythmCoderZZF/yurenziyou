package com.nbhysj.coupon.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.RecommendFriendsPictureAdapter;
import com.nbhysj.coupon.model.response.OrderDetailScenicSpotReponse;
import com.nbhysj.coupon.model.response.RecommendFriendsBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/08
 * description：订单详情景区
 */
public class OrderDetailScenicSpotFragment extends BaseFragment {

    //景区名字
    @BindView(R.id.tv_scenic_spot_name)
    TextView mTvScenicSpotName;
    //景区票类型
    @BindView(R.id.tv_ticket_type)
    TextView mTvTicketType;
    //景区票使用时间
    @BindView(R.id.tv_ticket_use_time)
    TextView mTvTicketUseTime;
    //景区地址
    @BindView(R.id.tv_scenic_spot_address)
    TextView mTvScenicSpotAddress;

    public static OrderDetailScenicSpotFragment newInstance(OrderDetailScenicSpotReponse orderDetailScenicSpotReponse) {

        //            创建一个 bundle 传递 数据
        Bundle bundle = new Bundle();
        //使用bundle合适的put方法传递数据
        bundle.putSerializable("orderDetailScenicSpot", (Serializable) orderDetailScenicSpotReponse);
//             新建一个 fragment
        OrderDetailScenicSpotFragment fragment = new OrderDetailScenicSpotFragment();

        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_detail_scenic_spot_item;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(View v) {
        Bundle bundle = getArguments();
        OrderDetailScenicSpotReponse detailScenicSpot = (OrderDetailScenicSpotReponse) bundle.getSerializable("orderDetailScenicSpot");
        mTvScenicSpotName.setText(detailScenicSpot.getScenicSpotName());
        mTvTicketType.setText(detailScenicSpot.getTicketType());
        mTvTicketUseTime.setText(detailScenicSpot.getUseTime());
        mTvScenicSpotAddress.setText(detailScenicSpot.getScenicSpotAddress());
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
