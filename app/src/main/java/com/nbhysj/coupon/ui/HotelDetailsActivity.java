package com.nbhysj.coupon.ui;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.HotelDetailRoomAdapter;
import com.nbhysj.coupon.adapter.HotelDetailUserCommentAdapter;
import com.nbhysj.coupon.adapter.HotelPeripheryAdapter;
import com.nbhysj.coupon.adapter.NearbyHotSellHotelsAdapter;
import com.nbhysj.coupon.dialog.HotelDetailsSupplementDialog;
import com.nbhysj.coupon.model.response.BannerUrlBO;
import com.nbhysj.coupon.model.response.ScenicSpotsUserCommentResponse;
import com.nbhysj.coupon.view.HotelDetailBannerView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/09
 * description：酒店详情
 */
public class HotelDetailsActivity extends BaseActivity {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.banner_hotel_detail)
    HotelDetailBannerView mBannerViewHotelDetail;
    //酒店房间筛选
    @BindView(R.id.rv_hotel_room)
    RecyclerView mRvHotelRoom;
    @BindView(R.id.flowlayout_hotel_comment_label)
    TagFlowLayout mTagFlowHotelCommentLabel;
    @BindView(R.id.rv_user_comment)
    RecyclerView mRvUserComment;
    @BindView(R.id.rv_hotel_periphery_recommendation)
    RecyclerView mRvHotelPeriphery;
    /*  @BindView(R.id.tab_layout)
      MyTabLayout mMyTabLayout;*/
    @BindView(R.id.tv_delicious_food)
    TextView mTvDeliciousFood;
    @BindView(R.id.tv_entertainment)
    TextView mTvEntertainment;
    @BindView(R.id.tv_scenic_spot)
    TextView mTvScenicSpot;
    @BindView(R.id.view_delicious_food)
    View mViewDeliciousFood;
    @BindView(R.id.view_entertainment)
    View mViewEntertainment;
    @BindView(R.id.view_scenic_spot)
    View mViewScenicSpot;
    //附近热销酒店
    @BindView(R.id.rv_hot_selling_hotels_nearby)
    RecyclerView mRvHotSellingHotelsNearby;

    private List<ImageView> viewList;
    private List<BannerUrlBO> bannerList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_hotel_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }

        viewList = new ArrayList<ImageView>();
        bannerList = new ArrayList<>();

        BannerUrlBO bannerUrl = new BannerUrlBO();
        bannerUrl.setUrl("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1557554436&di=c2e7961ab98f67ec40d614855821b940&src=http://img.zx123.cn/Resources/zx123cn/uploadfile/2016/1017/2ce851d6e06c41834d1e5aa7de4f3150.jpg");

        BannerUrlBO bannerUrl1 = new BannerUrlBO();
        bannerUrl1.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1557554460&di=95c8dfec45441982a6628f5316b89f36&src=http://pic17.photophoto.cn/20101201/0040039386261589_b.jpg");

        BannerUrlBO bannerUrl2 = new BannerUrlBO();
        bannerUrl2.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1557554460&di=587cccfcf79487fa86575a004a4785fd&src=http://seopic.699pic.com/photo/50014/4961.jpg_wh1200.jpg");
        bannerList.add(bannerUrl);
        bannerList.add(bannerUrl1);
        bannerList.add(bannerUrl2);

        if (bannerList.size() > 0) {

            for (int i = 0; i < bannerList.size(); i++) {
                ImageView image = new ImageView(HotelDetailsActivity.this);
                image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                //设置显示格式
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                viewList.add(image);
            }
        }


        mBannerViewHotelDetail.startLoop(false);
        mBannerViewHotelDetail.setViewList(HotelDetailsActivity.this, viewList, bannerList);

        LinearLayoutManager linearLayout = new LinearLayoutManager(HotelDetailsActivity.this);
        linearLayout.setOrientation(linearLayout.VERTICAL);
        mRvHotelRoom.setLayoutManager(linearLayout);
        HotelDetailRoomAdapter setMealDetailAdapter = new HotelDetailRoomAdapter(HotelDetailsActivity.this, new HotelDetailRoomAdapter.HotelRoomItemListener() {
            @Override
            public void setHotelRoomItemListener(int position) {

                HotelDetailsSupplementDialog hotelDetailsSupplementDialog = new HotelDetailsSupplementDialog();
                hotelDetailsSupplementDialog.show(getFragmentManager(), "酒店详情补充");

            }
        });
        mRvHotelRoom.setAdapter(setMealDetailAdapter);


        /*mMyTabLayout.setViewPager(titles);
        mMyTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {


            }

            @Override
            public void onTabReselect(int position) {
                showToast(HotelDetailsActivity.this, titles[position]);
            }
        });*/


    }

    @Override
    public void initData() {

        List<String> fineFoodTagList = new ArrayList<>();
        fineFoodTagList.add("服务热情(500)");
        fineFoodTagList.add("实惠(20)");
        fineFoodTagList.add("干净卫生(200)");
        fineFoodTagList.add("服务热情(500)");
        fineFoodTagList.add("刷差评(200)");
        TagAdapter tagAdapter = new TagAdapter<String>(fineFoodTagList) {
            @Override
            public View getView(FlowLayout parent, int position, String option) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_gray_frame,
                        mTagFlowHotelCommentLabel, false);
                TextView tv = view.findViewById(R.id.tv_flowlayout);
                tv.setText(option);

                return view;
            }
        };
        mTagFlowHotelCommentLabel.setMaxSelectCount(1);
        mTagFlowHotelCommentLabel.setAdapter(tagAdapter);

        mTagFlowHotelCommentLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String content = "";
                Set<Integer> selectPosSet = mTagFlowHotelCommentLabel.getSelectedList();
                Iterator it = selectPosSet.iterator();
                while (it.hasNext()) {
                    int index = (int) it.next();
                    //content = options[index];
                    // MoveToPosition(layoutManager,index);


                }
                return true;
            }
        });
        tagAdapter.setSelectedList(0);

        List<ScenicSpotsUserCommentResponse> spotsUserCommentResponseList = new ArrayList<>();
        List<String> userCommentPhotoList = new ArrayList<>();
        userCommentPhotoList.add("http://img5.imgtn.bdimg.com/it/u=3300305952,1328708913&fm=26&gp=0.jpg");
        userCommentPhotoList.add("http://d.hiphotos.baidu.com/lvpics/w=1000/sign=e2347e78217f9e2f703519082f00eb24/730e0cf3d7ca7bcb49f90bb1b8096b63f724a8aa.jpg");
        userCommentPhotoList.add("http://i0.hexunimg.cn/2011-08-18/132587770.jpg");
        ScenicSpotsUserCommentResponse userCommentResponse = new ScenicSpotsUserCommentResponse();
        userCommentResponse.setId(1);
        userCommentResponse.setUsername("陈发发");
        userCommentResponse.setCommentPublishTime("2019-04-29");
        userCommentResponse.setContent("第一次去海洋馆真的超级幸运,先去看的是剧场表扬 互动环节的时候很幸运被抽中和白鲸接触来了一个深海之吻超级感动,后面又到水族参观,最喜欢的是哒哒哒哒哒哒多多");
        userCommentResponse.setStarLevel(3);
        userCommentResponse.setUserAvatarPhoto("http://pic9.nipic.com/20100901/4753218_163400058451_2.jpg");
        userCommentResponse.setUserCommentPhotoList(userCommentPhotoList);

        spotsUserCommentResponseList.add(userCommentResponse);
        LinearLayoutManager userCommentLayoutManager = new LinearLayoutManager(HotelDetailsActivity.this);
        userCommentLayoutManager.setOrientation(userCommentLayoutManager.VERTICAL);
        mRvUserComment.setLayoutManager(userCommentLayoutManager);
        HotelDetailUserCommentAdapter hotelDetailUserCommentAdapter = new HotelDetailUserCommentAdapter(HotelDetailsActivity.this);
        hotelDetailUserCommentAdapter.setScenicSpotsUserCommentList(spotsUserCommentResponseList);
        mRvUserComment.setAdapter(hotelDetailUserCommentAdapter);

        LinearLayoutManager deliciousFoodLinearLayout = new LinearLayoutManager(HotelDetailsActivity.this);
        deliciousFoodLinearLayout.setOrientation(deliciousFoodLinearLayout.HORIZONTAL);
        mRvHotelPeriphery.setLayoutManager(deliciousFoodLinearLayout);
        HotelPeripheryAdapter hotelPeripheryAdapter = new HotelPeripheryAdapter(HotelDetailsActivity.this);
        mRvHotelPeriphery.setAdapter(hotelPeripheryAdapter);

        //附近热销酒店
        LinearLayoutManager layoutManager = new LinearLayoutManager(HotelDetailsActivity.this);
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvHotSellingHotelsNearby.setLayoutManager(layoutManager);
        NearbyHotSellHotelsAdapter nearbyHotSellHotelsAdapter = new NearbyHotSellHotelsAdapter(HotelDetailsActivity.this);
        mRvHotSellingHotelsNearby.setAdapter(nearbyHotSellHotelsAdapter);
    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.llyt_delicious_food, R.id.llyt_entertainment, R.id.llyt_scenic_spot})
    public void onClick(View v) {
        Typeface normalFont = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);
        mTvEntertainment.setTypeface(normalFont);
        Typeface boldFont = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
        mTvEntertainment.setTypeface(boldFont);
        switch (v.getId()) {
            case R.id.llyt_delicious_food:
                mViewDeliciousFood.setVisibility(View.VISIBLE);
                mViewEntertainment.setVisibility(View.GONE);
                mViewScenicSpot.setVisibility(View.GONE);
                mTvDeliciousFood.setTypeface(boldFont);
                mTvEntertainment.setTypeface(normalFont);
                mTvScenicSpot.setTypeface(normalFont);
                break;
            case R.id.llyt_entertainment:
                mViewEntertainment.setVisibility(View.VISIBLE);
                mViewDeliciousFood.setVisibility(View.GONE);
                mViewScenicSpot.setVisibility(View.GONE);
                mTvEntertainment.setTypeface(boldFont);
                mTvDeliciousFood.setTypeface(normalFont);
                mTvScenicSpot.setTypeface(normalFont);
                break;
            case R.id.llyt_scenic_spot:
                mViewScenicSpot.setVisibility(View.VISIBLE);
                mViewDeliciousFood.setVisibility(View.GONE);
                mViewEntertainment.setVisibility(View.GONE);
                mTvScenicSpot.setTypeface(boldFont);
                mTvDeliciousFood.setTypeface(normalFont);
                mTvEntertainment.setTypeface(normalFont);
                break;
            default:
                break;

        }
    }
}
