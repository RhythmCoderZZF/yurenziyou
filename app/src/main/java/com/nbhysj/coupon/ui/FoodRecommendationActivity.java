package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.DeliciousFoodShopRecommendMoreAdapter;
import com.nbhysj.coupon.adapter.ScenicSpotDetailUserCommentAdapter;
import com.nbhysj.coupon.adapter.ShopRecommendDeliciousFoodAdapter;
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
 * @auther：hysj created on 2019/03/08
 * description：本店推荐  美食详情页
 */
public class FoodRecommendationActivity extends BaseActivity {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    //美食banner
    @BindView(R.id.banner_delicious_food)
    HotelDetailBannerView mBannerDeliciousFood;
    @BindView(R.id.rv_shop_recommend_delicious_food)
    RecyclerView mRvShopRecommendDeliciousFood;
    //美食用户评价标签
    @BindView(R.id.flowlayout_food_recommend_label)
    TagFlowLayout mTagFlowFoodRecommentLabel;
    @BindView(R.id.rv_user_comment)
    RecyclerView mRvUserComment;
    //更多推荐
    @BindView(R.id.rv_delicious_food_more_recommendation)
    RecyclerView mRvDeliciousFoodMoreRecommendation;
    //推荐菜查看更多
    @BindView(R.id.tv_recommend_delicious_food_look_more)
    TextView mTvRecommendDeliciousFoodLookMore;
    private List<ImageView> viewList;
    private List<BannerUrlBO> bannerList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_food_recommendation;
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
        bannerUrl.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557813437879&di=eb498d12d37c7c602689b1778f93b7d9&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01f98859cb1337a801218e18a09d25.jpg%401280w_1l_2o_100sh.jpg");

        BannerUrlBO bannerUrl1 = new BannerUrlBO();
        bannerUrl1.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1557803696&di=75dea3f801249c7db49b06f7cbf689d3&src=http://b-ssl.duitang.com/uploads/item/201507/29/20150729084905_mcCez.jpeg");

        BannerUrlBO bannerUrl2 = new BannerUrlBO();
        bannerUrl2.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1557554460&di=587cccfcf79487fa86575a004a4785fd&src=http://seopic.699pic.com/photo/50014/4961.jpg_wh1200.jpg");
        bannerList.add(bannerUrl);
        bannerList.add(bannerUrl1);
        bannerList.add(bannerUrl2);

        if (bannerList.size() > 0) {

            for (int i = 0; i < bannerList.size(); i++) {
                ImageView image = new ImageView(FoodRecommendationActivity.this);
                image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                //设置显示格式
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                viewList.add(image);
            }
        }


        mBannerDeliciousFood.startLoop(false);
        mBannerDeliciousFood.setViewList(FoodRecommendationActivity.this, viewList, bannerList);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(FoodRecommendationActivity.this,
                        3);
        mRvShopRecommendDeliciousFood.setLayoutManager(gridLayoutManager);
        // linearLayoutManager.setAutoMeasureEnabled(true);
        ShopRecommendDeliciousFoodAdapter mallGuessYouLikeAdapter = new ShopRecommendDeliciousFoodAdapter(FoodRecommendationActivity.this);
        mRvShopRecommendDeliciousFood.setAdapter(mallGuessYouLikeAdapter);
        mRvShopRecommendDeliciousFood.addItemDecoration(new RecyclerItemDecoration(11, 3));


        List<ScenicSpotsUserCommentResponse> spotsUserCommentResponseList = new ArrayList<>();
        List<String> userCommentPhotoList = new ArrayList<>();
        userCommentPhotoList.add("http://img5.imgtn.bdimg.com/it/u=3300305952,1328708913&fm=26&gp=0.jpg");
        userCommentPhotoList.add("http://d.hiphotos.baidu.com/lvpics/w=1000/sign=e2347e78217f9e2f703519082f00eb24/730e0cf3d7ca7bcb49f90bb1b8096b63f724a8aa.jpg");
        userCommentPhotoList.add("http://i0.hexunimg.cn/2011-08-18/132587770.jpg");
        ScenicSpotsUserCommentResponse userCommentResponse = new ScenicSpotsUserCommentResponse();
        userCommentResponse.setId(1);
        userCommentResponse.setUsername("飞飞飞");
        userCommentResponse.setCommentPublishTime("2019-04-29");
        userCommentResponse.setContent("第一次去海洋馆真的超级幸运,先去看的是剧场表扬 互动环节的时候很幸运被抽中和白鲸接触来了一个深海之吻超级感动,后面又到水族参观,最喜欢的是哒哒哒哒哒哒多多");
        userCommentResponse.setStarLevel(3);
        userCommentResponse.setUserAvatarPhoto("http://pic9.nipic.com/20100901/4753218_163400058451_2.jpg");
        userCommentResponse.setUserCommentPhotoList(userCommentPhotoList);

        List<String> userCommentPhotoList1 = new ArrayList<>();
        userCommentPhotoList1.add("http://pic34.nipic.com/20131102/11840161_135433676377_2.jpg");
        userCommentPhotoList1.add("http://i2.hexunimg.cn/2015-11-16/180589438.jpg");
        userCommentPhotoList1.add("http://photocdn.sohu.com/20120216/Img334903378.jpg");
        ScenicSpotsUserCommentResponse userCommentResponse1 = new ScenicSpotsUserCommentResponse();
        userCommentResponse1.setId(2);
        userCommentResponse1.setUsername("哒哒滴");
        userCommentResponse1.setCommentPublishTime("2019-04-23");
        userCommentResponse1.setContent("第一次去海洋馆真的超级幸运,先去看的是剧场表扬 互动环节的时候很幸运被抽中和白鲸接触来了一个深海之吻超级感动,后面又到水族参观,最喜欢的是哒哒哒哒哒哒多多");
        userCommentResponse1.setStarLevel(4);
        userCommentResponse1.setUserAvatarPhoto("https://b-ssl.duitang.com/uploads/item/201512/08/20151208130909_SiFP5.jpeg");
        userCommentResponse1.setUserCommentPhotoList(userCommentPhotoList1);
        spotsUserCommentResponseList.add(userCommentResponse);
        spotsUserCommentResponseList.add(userCommentResponse1);

        LinearLayoutManager userCommentLayoutManager = new LinearLayoutManager(FoodRecommendationActivity.this);
        userCommentLayoutManager.setOrientation(userCommentLayoutManager.VERTICAL);
        mRvUserComment.setLayoutManager(userCommentLayoutManager);
        ScenicSpotDetailUserCommentAdapter scenicSpotDetailUserCommentAdapter = new ScenicSpotDetailUserCommentAdapter(FoodRecommendationActivity.this);
        // scenicSpotDetailUserCommentAdapter.setScenicSpotsUserCommentList(spotsUserCommentResponseList);
        //mRvUserComment.setAdapter(scenicSpotDetailUserCommentAdapter);

        GridLayoutManager moreRecommendationGridLayoutManager =
                new GridLayoutManager(FoodRecommendationActivity.this,
                        2);
        mRvDeliciousFoodMoreRecommendation.setLayoutManager(moreRecommendationGridLayoutManager);
        // linearLayoutManager.setAutoMeasureEnabled(true);
        DeliciousFoodShopRecommendMoreAdapter deliciousFoodShopRecommendMoreAdapter = new DeliciousFoodShopRecommendMoreAdapter(FoodRecommendationActivity.this);
        mRvDeliciousFoodMoreRecommendation.setAdapter(deliciousFoodShopRecommendMoreAdapter);
        mRvDeliciousFoodMoreRecommendation.addItemDecoration(new RecyclerItemDecoration(11, 2));
    }

    @Override
    public void initData() {
        List<String> fineFoodTagList = new ArrayList<>();
        fineFoodTagList.add("晒图评价 66");
        fineFoodTagList.add("低分评价 20");
        fineFoodTagList.add("最新评价");
        fineFoodTagList.add("味道赞 74");
        fineFoodTagList.add("性价比高 35");
        fineFoodTagList.add("点心好 35");
        TagAdapter tagAdapter = new TagAdapter<String>(fineFoodTagList) {
            @Override
            public View getView(FlowLayout parent, int position, String option) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_gray_frame,
                        mTagFlowFoodRecommentLabel, false);
                TextView tv = view.findViewById(R.id.tv_flowlayout);
                tv.setText(option);

                return view;
            }
        };
        mTagFlowFoodRecommentLabel.setMaxSelectCount(1);
        mTagFlowFoodRecommentLabel.setAdapter(tagAdapter);

        mTagFlowFoodRecommentLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String content = "";
                Set<Integer> selectPosSet = mTagFlowFoodRecommentLabel.getSelectedList();
                Iterator it = selectPosSet.iterator();
                while (it.hasNext()) {
                    int index = (int) it.next();
                    //content = options[index];
                    // MoveToPosition(layoutManager,index);


                }
                return true;
            }
        });
    }

    @Override
    public void initPresenter() {

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

    @OnClick({R.id.tv_recommend_delicious_food_look_more})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_recommend_delicious_food_look_more:
                Intent intent = new Intent();
                intent.setClass(FoodRecommendationActivity.this, RecommendFoodLookMoreActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
