package com.nbhysj.coupon.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.GoodsTypeEnum;
import com.nbhysj.coupon.model.response.OrderDetailResponse;
import com.nbhysj.coupon.util.GlideUtil;

import java.io.Serializable;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/08
 * description：订单详情景区
 */
public class OrderDetailScenicSpotFragment extends BaseFragment {

    //商户名字
    @BindView(R.id.tv_mch_name)
    TextView mTvMchName;
    //景区票类型
    @BindView(R.id.tv_ticket_type)
    TextView mTvTicketType;
    //景区地址
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    //商户位置定位
    @BindView(R.id.img_mch_address)
    ImageView mImgMchAddress;
    //营业时间
    @BindView(R.id.tv_content)
    TextView mTvContent;

    public static OrderDetailScenicSpotFragment newInstance(OrderDetailResponse.OrderGoodsEntity orderGoodsEntity) {

        //            创建一个 bundle 传递 数据
        Bundle bundle = new Bundle();
        //使用bundle合适的put方法传递数据
        bundle.putSerializable("orderGoodsEntity", (Serializable) orderGoodsEntity);
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
        OrderDetailResponse.OrderGoodsEntity orderGoodsEntity = (OrderDetailResponse.OrderGoodsEntity) bundle.getSerializable("orderGoodsEntity");
        OrderDetailResponse.AddressEntity addressEntity = orderGoodsEntity.getAddress();
        String province = addressEntity.getProvince();
        String county = addressEntity.getCounty();
        String city = addressEntity.getCity();
        String address = addressEntity.getAddress();         //地址
        String goodsTitle = orderGoodsEntity.getGoodsTitle(); //商品标题
        String goodType = orderGoodsEntity.getGoodType();  //商品类型
        String goodNum = orderGoodsEntity.getGoodsNum();   //商品数量
        String note = orderGoodsEntity.getNote();

        if(goodType.equals(GoodsTypeEnum.getEnumByKey(0).getValue())) {
            mTvMchName.setText(orderGoodsEntity.getMchName());
           // mTvTicketType.setText(goodsTitle + goodNum + "张");
            mTvAddress.setText(province + city + county + address);
            mTvContent.setText("营业时间" + orderGoodsEntity.getOpenTime());

            GlideUtil.loadImage(getActivity(),note, mImgMchAddress);
        }
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
