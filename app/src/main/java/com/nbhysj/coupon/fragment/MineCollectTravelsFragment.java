package com.nbhysj.coupon.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MineCollectionAlbumAdapter;
import com.nbhysj.coupon.adapter.MineCollectionTravelsAdapter;
import com.nbhysj.coupon.model.response.MineCollectionAlbumResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/05/25
 * description：我的收藏游记
 */
public class MineCollectTravelsFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //我的收藏游记
    @BindView(R.id.rv_mine_collection_travels)
    RecyclerView mRvMineCollectionTravels;
    private List<MineCollectionAlbumResponse> mineCollectionAlbumList;

    public MineCollectTravelsFragment() {
        // Required empty public constructor
    }

    public static MineCollectTravelsFragment newInstance(String param1, String param2) {
        MineCollectTravelsFragment fragment = new MineCollectTravelsFragment();
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
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_collection_travels;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(View v) {

        if (mineCollectionAlbumList == null) {

            mineCollectionAlbumList = new ArrayList<>();
        } else {
            mineCollectionAlbumList.clear();
        }

        MineCollectionAlbumResponse mineCollectionAlbumResponse = new MineCollectionAlbumResponse();
        mineCollectionAlbumResponse.setAlbumName("吃吃吃");
        mineCollectionAlbumResponse.setAlbumDes("20");
        mineCollectionAlbumResponse.setAlbumImage("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1557554460&di=587cccfcf79487fa86575a004a4785fd&src=http://seopic.699pic.com/photo/50014/4961.jpg_wh1200.jpg");

        MineCollectionAlbumResponse mineCollectionAlbumResponse1 = new MineCollectionAlbumResponse();
        mineCollectionAlbumResponse1.setAlbumName("吃吃吃1");
        mineCollectionAlbumResponse1.setAlbumDes("20");
        mineCollectionAlbumResponse1.setAlbumImage("http://pic34.nipic.com/20131102/11840161_135433676377_2.jpg");

        MineCollectionAlbumResponse mineCollectionAlbumResponse2 = new MineCollectionAlbumResponse();
        mineCollectionAlbumResponse2.setAlbumName("吃吃吃2");
        mineCollectionAlbumResponse2.setAlbumDes("20");
        mineCollectionAlbumResponse2.setAlbumImage("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1557554460&di=587cccfcf79487fa86575a004a4785fd&src=http://seopic.699pic.com/photo/50014/4961.jpg_wh1200.jpg");

        mineCollectionAlbumList.add(mineCollectionAlbumResponse);
        mineCollectionAlbumList.add(mineCollectionAlbumResponse1);
        mineCollectionAlbumList.add(mineCollectionAlbumResponse2);

        //我的收藏专辑
        LinearLayoutManager travelListAdapterLayoutManager = new LinearLayoutManager(getActivity());
        travelListAdapterLayoutManager.setOrientation(travelListAdapterLayoutManager.VERTICAL);
        mRvMineCollectionTravels.setLayoutManager(travelListAdapterLayoutManager);
        MineCollectionTravelsAdapter mineCollectionAlbumAdapter = new MineCollectionTravelsAdapter(getActivity());
        mRvMineCollectionTravels.setAdapter(mineCollectionAlbumAdapter);

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
