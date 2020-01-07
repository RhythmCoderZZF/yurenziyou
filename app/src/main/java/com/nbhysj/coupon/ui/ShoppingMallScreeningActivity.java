package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.DropDownMenuClassifyThreeLevelAdapter;
import com.nbhysj.coupon.adapter.DropDownMenuDistanceClassifyAdapter;
import com.nbhysj.coupon.adapter.DropDownMenuDistanceSubTwoLevelAdapter;
import com.nbhysj.coupon.adapter.DropDownMenuDistanceTwoLevelAdapter;
import com.nbhysj.coupon.adapter.DropDownMenuListAdapter;
import com.nbhysj.coupon.adapter.ShopMallScreenStarClassAdapter;
import com.nbhysj.coupon.adapter.SortDropDownMenuListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HotelAndHomestaySearchContract;
import com.nbhysj.coupon.model.HotelAndHomestaySearchModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.PositionDistanceBean;
import com.nbhysj.coupon.model.response.PositionDistanceSearchBean;
import com.nbhysj.coupon.model.response.SortTypeBean;
import com.nbhysj.coupon.model.response.TagBaseEntity;
import com.nbhysj.coupon.presenter.HotelAndHomestaySearchPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.view.DropDownMenu;
import com.nbhysj.coupon.widget.DoubleSlideSeekBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/08
 * description：酒店民宿推荐排序
 */
public class ShoppingMallScreeningActivity extends BaseActivity<HotelAndHomestaySearchPresenter, HotelAndHomestaySearchModel> implements HotelAndHomestaySearchContract.View {
    private String headers[] = {"推荐排序", "位置距离", "价格/星级", "名宿"};
    @BindView(R.id.drop_down_menu_screening)
    DropDownMenu mDropDownMenu;
    //星级
    RecyclerView mRvStarClass;
    DropDownMenuDistanceClassifyAdapter dropDownMenuDistanceTypeOneLevelAdapter;
    DropDownMenuDistanceTwoLevelAdapter dropDownMenuDistanceTypeTwoLevelAdapter;
    DropDownMenuDistanceSubTwoLevelAdapter dropDownMenuDistanceTypeSubTwoLevelAdapter;
    DropDownMenuClassifyThreeLevelAdapter dropDownMenuClassifyThreeLevelAdapter;
    List<PositionDistanceSearchBean> positionDistanceSearchOneLevelList;

    List<PositionDistanceSearchBean> positionDistanceSearchTwoLevelList;

    private List<PositionDistanceSearchBean> positionDistanceSearchThreeLevelList;

    //二级列表
    private RecyclerView mRvPositionDistanceClassifyTwoLevelLinkage;

    //二级列表(有三级列表时候显示)
    private RecyclerView mRvPositionDistanceClassifySubTwoLevelLinkage;

    //三级列表
    private RecyclerView mRvPositionDistanceClassifyThreeLevelLinkage;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_shopping_mall_screening;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        if (positionDistanceSearchOneLevelList == null) {

            positionDistanceSearchOneLevelList = new ArrayList<>();

        } else {
            positionDistanceSearchOneLevelList.clear();
        }

        if (positionDistanceSearchTwoLevelList == null) {
            positionDistanceSearchTwoLevelList = new ArrayList<>();

        } else {
            positionDistanceSearchTwoLevelList.clear();
        }

        if (positionDistanceSearchThreeLevelList == null) {
            positionDistanceSearchThreeLevelList = new ArrayList<>();

        } else {
            positionDistanceSearchThreeLevelList.clear();
        }
        getHomestayScreeningCondition();
        List<View> popupViews = new ArrayList<>();

        List<SortTypeBean> sortTypeDropDownMenuList = new ArrayList<>();
        SortTypeBean recommendedSorting = new SortTypeBean();
        recommendedSorting.setSortType("推荐排序");
        recommendedSorting.setSelect(true);
        SortTypeBean lowPriority = new SortTypeBean();
        lowPriority.setSortType("低价优先");
        SortTypeBean highPricePriority = new SortTypeBean();
        highPricePriority.setSortType("高价优先");
        SortTypeBean praiseFirst = new SortTypeBean();
        praiseFirst.setSortType("好评优先");
        sortTypeDropDownMenuList.add(recommendedSorting);
        sortTypeDropDownMenuList.add(lowPriority);
        sortTypeDropDownMenuList.add(highPricePriority);
        sortTypeDropDownMenuList.add(praiseFirst);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        RecyclerView mRvdropDownMenuList = new RecyclerView(ShoppingMallScreeningActivity.this);
        mRvdropDownMenuList.setLayoutManager(linearLayoutManager);
        SortDropDownMenuListAdapter dropDownMenuListAdapter = new SortDropDownMenuListAdapter(ShoppingMallScreeningActivity.this, new SortDropDownMenuListAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(SortTypeBean sortTypeBean) {
                String positionDistanceType = sortTypeBean.getSortType();
                mDropDownMenu.setTabText(positionDistanceType);
                mDropDownMenu.closeMenu();
            }


        });
        dropDownMenuListAdapter.setDropDownMenuList(sortTypeDropDownMenuList);
        mRvdropDownMenuList.setAdapter(dropDownMenuListAdapter);

        List<PositionDistanceBean> dropDownMenuList3 = new ArrayList<>();
        PositionDistanceBean positionDistance10 = new PositionDistanceBean();
        positionDistance10.setPositionDistanceType("推荐排序");

        PositionDistanceBean positionDistance11 = new PositionDistanceBean();
        positionDistance11.setPositionDistanceType("低价优先");
        PositionDistanceBean positionDistance12 = new PositionDistanceBean();
        PositionDistanceBean positionDistance13 = new PositionDistanceBean();
        positionDistance13.setPositionDistanceType("高价优先");
        PositionDistanceBean positionDistance14 = new PositionDistanceBean();
        positionDistance14.setPositionDistanceType("好评优先");
        dropDownMenuList3.add(positionDistance10);
        dropDownMenuList3.add(positionDistance11);
        dropDownMenuList3.add(positionDistance12);
        dropDownMenuList3.add(positionDistance13);
        dropDownMenuList3.add(positionDistance14);

        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        linearLayoutManager3.setOrientation(linearLayoutManager3.VERTICAL);
        RecyclerView mRvdropDownMenuList3 = new RecyclerView(ShoppingMallScreeningActivity.this);
        mRvdropDownMenuList3.setLayoutManager(linearLayoutManager3);
        DropDownMenuListAdapter dropDownMenuListAdapter3 = new DropDownMenuListAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuListAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(int position,PositionDistanceBean positionDistanceBean) {


              //  dropDownMenuListAdapter3.notifyDataSetChanged();

            }
        });
        dropDownMenuListAdapter3.setDropDownMenuList(dropDownMenuList3);
        mRvdropDownMenuList3.setAdapter(dropDownMenuListAdapter3);


        View mViewDistanceCondition = getLayoutInflater().inflate(R.layout.layout_shop_mall_position_distance_condition, null);
        RecyclerView mRvPositionDistanceClassifyType = mViewDistanceCondition.findViewById(R.id.rv_position_distance_classify_type);
        //二级列表
        mRvPositionDistanceClassifyTwoLevelLinkage = mViewDistanceCondition.findViewById(R.id.rv_position_distance_classify_two_level_linkage);

        //三级列表时候（对应二级,三级列表）
        mRvPositionDistanceClassifySubTwoLevelLinkage = mViewDistanceCondition.findViewById(R.id.rv_position_distance_classify_sub_two_level_linkage);
        mRvPositionDistanceClassifyThreeLevelLinkage = mViewDistanceCondition.findViewById(R.id.rv_position_distance_classify_three_level_linkage);

        LinearLayoutManager distanceConditionLinearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        distanceConditionLinearLayoutManager.setOrientation(distanceConditionLinearLayoutManager.VERTICAL);
        mRvPositionDistanceClassifyType.setLayoutManager(distanceConditionLinearLayoutManager);

        dropDownMenuDistanceTypeOneLevelAdapter = new DropDownMenuDistanceClassifyAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuDistanceClassifyAdapter.DropDownMenuDistanceClassifyListener() {
            @Override
            public void setDropDownMenuDistanceClassifyListener(int position, PositionDistanceSearchBean positionDistanceBean) {


                    if(positionDistanceSearchOneLevelList != null && positionDistanceSearchOneLevelList.size() > 0) {
                        for (int j = 0; j < positionDistanceSearchOneLevelList.size(); j++) {
                            PositionDistanceSearchBean positionDistanceTwoLevel = positionDistanceSearchOneLevelList.get(j);
                            positionDistanceTwoLevel.setSelect(false);

                            positionDistanceSearchTwoLevelList = positionDistanceTwoLevel.getPosition();

                            if (positionDistanceSearchTwoLevelList != null && positionDistanceSearchTwoLevelList.size() > 0)
                            {
                                for (int k = 0; k < positionDistanceSearchTwoLevelList.size(); k++)
                                {
                                    PositionDistanceSearchBean positionDistanceThreeLevel = positionDistanceSearchTwoLevelList.get(k);
                                    positionDistanceThreeLevel.setSelect(false);
                                }

                            }
                    }
                }

                positionDistanceBean.setSelect(true);
                dropDownMenuDistanceTypeOneLevelAdapter.notifyDataSetChanged();

                if (positionDistanceSearchOneLevelList != null && positionDistanceSearchOneLevelList.size() > 0) {

                    positionDistanceSearchTwoLevelList = positionDistanceSearchOneLevelList.get(position).getPosition();
                     positionDistanceSearchThreeLevelList = positionDistanceSearchTwoLevelList.get(0).getPosition();
                    // List<PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel> positionDistanceTypeClassifyThreeLevelList = positionDistanceTypeClassify.get(0).getClassifyThreeLevelList();
                    if (positionDistanceSearchTwoLevelList != null && positionDistanceSearchTwoLevelList.size() > 0 && positionDistanceSearchThreeLevelList != null && positionDistanceSearchThreeLevelList.size() > 0) {
                        PositionDistanceSearchBean positionDistanceTwoBean = positionDistanceSearchTwoLevelList.get(0);
                        positionDistanceTwoBean.setSelect(true);
                        dropDownMenuDistanceTypeSubTwoLevelAdapter.setPositionDistanceList(positionDistanceSearchTwoLevelList);
                        dropDownMenuDistanceTypeSubTwoLevelAdapter.notifyDataSetChanged();

                        PositionDistanceSearchBean positionDistanceThreeBean = positionDistanceSearchThreeLevelList.get(0);
                        positionDistanceThreeBean.setSelect(true);
                        dropDownMenuClassifyThreeLevelAdapter.setPositionDistanceList(positionDistanceSearchThreeLevelList);
                        dropDownMenuClassifyThreeLevelAdapter.notifyDataSetChanged();
                        //  mRvPositionDistanceClassifyTwoLevelLinkage.setVisibility(View.GONE);
                        mRvPositionDistanceClassifyTwoLevelLinkage.setVisibility(View.GONE);
                        mRvPositionDistanceClassifySubTwoLevelLinkage.setVisibility(View.VISIBLE);
                        mRvPositionDistanceClassifyThreeLevelLinkage.setVisibility(View.VISIBLE);


                    }else if (positionDistanceSearchTwoLevelList != null && positionDistanceSearchTwoLevelList.size() > 0 && positionDistanceSearchThreeLevelList == null || positionDistanceSearchThreeLevelList.size() == 0) {
                        PositionDistanceSearchBean positionDistanceTwoBean = positionDistanceSearchTwoLevelList.get(0);
                        positionDistanceTwoBean.setSelect(true);
                        dropDownMenuDistanceTypeTwoLevelAdapter.setPositionDistanceList(positionDistanceSearchTwoLevelList);
                        dropDownMenuDistanceTypeTwoLevelAdapter.notifyDataSetChanged();
                        mRvPositionDistanceClassifyThreeLevelLinkage.setVisibility(View.GONE);
                        mRvPositionDistanceClassifySubTwoLevelLinkage.setVisibility(View.GONE);
                        mRvPositionDistanceClassifyTwoLevelLinkage.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        dropDownMenuDistanceTypeOneLevelAdapter.setPositionDistanceList(positionDistanceSearchOneLevelList);
        mRvPositionDistanceClassifyType.setAdapter(dropDownMenuDistanceTypeOneLevelAdapter);

        //位置距离二级列表
        LinearLayoutManager twoLevelLinearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        twoLevelLinearLayoutManager.setOrientation(twoLevelLinearLayoutManager.VERTICAL);
        mRvPositionDistanceClassifyTwoLevelLinkage.setLayoutManager(twoLevelLinearLayoutManager);
        dropDownMenuDistanceTypeTwoLevelAdapter = new DropDownMenuDistanceTwoLevelAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuDistanceTwoLevelAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(int position, PositionDistanceSearchBean positionDistanceBean) {


            }
        });

        dropDownMenuDistanceTypeTwoLevelAdapter.setPositionDistanceList(positionDistanceSearchTwoLevelList);
        mRvPositionDistanceClassifyTwoLevelLinkage.setAdapter(dropDownMenuDistanceTypeTwoLevelAdapter);


        //位置距离二级子列表
        LinearLayoutManager subTwoLevelLinearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        subTwoLevelLinearLayoutManager.setOrientation(subTwoLevelLinearLayoutManager.VERTICAL);
        mRvPositionDistanceClassifySubTwoLevelLinkage.setLayoutManager(subTwoLevelLinearLayoutManager);
        dropDownMenuDistanceTypeSubTwoLevelAdapter = new DropDownMenuDistanceSubTwoLevelAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuDistanceSubTwoLevelAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(int position, PositionDistanceSearchBean positionDistanceBean) {

                if(positionDistanceSearchTwoLevelList != null && positionDistanceSearchTwoLevelList.size() > 0)
                {
                    positionDistanceSearchThreeLevelList = positionDistanceSearchTwoLevelList.get(position).getPosition();
                    dropDownMenuClassifyThreeLevelAdapter.setPositionDistanceList(positionDistanceSearchThreeLevelList);
                    dropDownMenuClassifyThreeLevelAdapter.notifyDataSetChanged();
                }
            }
        });

        dropDownMenuDistanceTypeSubTwoLevelAdapter.setPositionDistanceList(positionDistanceSearchTwoLevelList);
        mRvPositionDistanceClassifySubTwoLevelLinkage.setAdapter(dropDownMenuDistanceTypeSubTwoLevelAdapter);


        //位置距离三级列表
        LinearLayoutManager threeLevelLinearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        threeLevelLinearLayoutManager.setOrientation(threeLevelLinearLayoutManager.VERTICAL);
        mRvPositionDistanceClassifyThreeLevelLinkage.setLayoutManager(threeLevelLinearLayoutManager);
        dropDownMenuClassifyThreeLevelAdapter = new DropDownMenuClassifyThreeLevelAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuClassifyThreeLevelAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(int position, PositionDistanceSearchBean positionDistanceBean) {


            }
        });

        dropDownMenuClassifyThreeLevelAdapter.setPositionDistanceList(positionDistanceSearchThreeLevelList);
        mRvPositionDistanceClassifyThreeLevelLinkage.setAdapter(dropDownMenuClassifyThreeLevelAdapter);


        View mViewStarPriceCondition = getLayoutInflater().inflate(R.layout.layout_shop_mall_star_price_condition, null);
        DoubleSlideSeekBar mDoubleslideWithrule = mViewStarPriceCondition.findViewById(R.id.double_slide_price);
        mRvStarClass = mViewStarPriceCondition.findViewById(R.id.rv_star_class);
        // 用法
        mDoubleslideWithrule.setOnRangeListener(new DoubleSlideSeekBar.onRangeListener() {
            @Override
            public void onRange(float low, float big) {
                String lowPrice = String.format("%.0f", low);
                String bigPrice = String.format("%.0f", big);
                String mBudegt = lowPrice + "," + bigPrice;
                showToast(ShoppingMallScreeningActivity.this, mBudegt);
            }
        });

        List<TagBaseEntity> tagBaseEntityList = new ArrayList<>();
        TagBaseEntity tagBaseEntity = new TagBaseEntity();
        tagBaseEntity.setTitle("不限");
        TagBaseEntity tagBaseEntity1 = new TagBaseEntity();
        tagBaseEntity1.setTitle("二星/经济");
        TagBaseEntity tagBaseEntity2 = new TagBaseEntity();
        tagBaseEntity2.setTitle("三星/舒适");
        TagBaseEntity tagBaseEntity3 = new TagBaseEntity();
        tagBaseEntity3.setTitle("四星/豪华");
        TagBaseEntity tagBaseEntity4 = new TagBaseEntity();
        tagBaseEntity4.setTitle("五星/豪华");
        tagBaseEntityList.add(tagBaseEntity);
        tagBaseEntityList.add(tagBaseEntity1);
        tagBaseEntityList.add(tagBaseEntity2);
        tagBaseEntityList.add(tagBaseEntity3);
        tagBaseEntityList.add(tagBaseEntity4);

        //星级筛选
        mRvStarClass.setLayoutManager(new GridLayoutManager(ShoppingMallScreeningActivity.this, 4));
        ShopMallScreenStarClassAdapter huXingAdapter = new ShopMallScreenStarClassAdapter(ShoppingMallScreeningActivity.this, new ShopMallScreenStarClassAdapter.ShopMallScreenStarClassListener() {
            @Override
            public void setShopMallScreenStarClassListener(TagBaseEntity tagBaseBean) {


            }
        });
        huXingAdapter.setTagList(tagBaseEntityList);
        mRvStarClass.setAdapter(huXingAdapter);

        popupViews.add(mRvdropDownMenuList);
        popupViews.add(mViewDistanceCondition);
        popupViews.add(mViewStarPriceCondition);
        popupViews.add(mRvdropDownMenuList3);

        final View contentView = getLayoutInflater().inflate(R.layout.layout_shop_mall_dropdownmenu_condition, null);

        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getHomestayScreeningConditionResult(BackResult<List<PositionDistanceSearchBean>> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    positionDistanceSearchOneLevelList = res.getData();
                    if (positionDistanceSearchOneLevelList != null && positionDistanceSearchOneLevelList.size() > 0) {
                        PositionDistanceSearchBean positionDistanceOneBean = positionDistanceSearchOneLevelList.get(0);
                        positionDistanceOneBean.setSelect(true);
                        dropDownMenuDistanceTypeOneLevelAdapter.setPositionDistanceList(positionDistanceSearchOneLevelList);
                        dropDownMenuDistanceTypeOneLevelAdapter.notifyDataSetChanged();
                        positionDistanceSearchTwoLevelList = positionDistanceOneBean.getPosition();


                       /*     PositionDistanceSearchBean positionDistanceTwoBean = positionDistanceSearchTwoLevelList.get(0);
                            positionDistanceTwoBean.setSelect(true);
                            dropDownMenuDistanceTypeSubTwoLevelAdapter.setPositionDistanceList(positionDistanceSearchTwoLevelList);
                            dropDownMenuDistanceTypeSubTwoLevelAdapter.notifyDataSetChanged();
                            List<PositionDistanceSearchBean> positionDistanceSearchThreeLevelList = positionDistanceTwoBean.getPosition();

                            if(positionDistanceSearchThreeLevelList != null && positionDistanceSearchThreeLevelList.size() > 0){

                                PositionDistanceSearchBean positionDistanceThreeBean = positionDistanceSearchThreeLevelList.get(0);
                                positionDistanceThreeBean.setSelect(true);
                            }*/

                        if (positionDistanceSearchTwoLevelList != null && positionDistanceSearchTwoLevelList.size() > 0 && positionDistanceSearchThreeLevelList != null && positionDistanceSearchThreeLevelList.size() > 0) {
                            PositionDistanceSearchBean positionDistanceTwoBean = positionDistanceSearchTwoLevelList.get(0);
                            positionDistanceTwoBean.setSelect(true);
                            dropDownMenuDistanceTypeSubTwoLevelAdapter.setPositionDistanceList(positionDistanceSearchTwoLevelList);
                            dropDownMenuDistanceTypeSubTwoLevelAdapter.notifyDataSetChanged();
                            dropDownMenuClassifyThreeLevelAdapter.setPositionDistanceList(positionDistanceSearchThreeLevelList);
                            dropDownMenuClassifyThreeLevelAdapter.notifyDataSetChanged();
                            //  mRvPositionDistanceClassifyTwoLevelLinkage.setVisibility(View.GONE);
                            mRvPositionDistanceClassifyTwoLevelLinkage.setVisibility(View.GONE);
                            mRvPositionDistanceClassifySubTwoLevelLinkage.setVisibility(View.VISIBLE);
                            mRvPositionDistanceClassifyThreeLevelLinkage.setVisibility(View.VISIBLE);

                        }
                        if (positionDistanceSearchTwoLevelList != null && positionDistanceSearchTwoLevelList.size() > 0 && positionDistanceSearchThreeLevelList == null || positionDistanceSearchThreeLevelList.size() == 0) {
                            PositionDistanceSearchBean positionDistanceTwoBean = positionDistanceSearchTwoLevelList.get(0);
                            positionDistanceTwoBean.setSelect(true);
                            dropDownMenuDistanceTypeTwoLevelAdapter.setPositionDistanceList(positionDistanceSearchTwoLevelList);
                            dropDownMenuDistanceTypeTwoLevelAdapter.notifyDataSetChanged();
                            mRvPositionDistanceClassifyThreeLevelLinkage.setVisibility(View.GONE);
                            mRvPositionDistanceClassifySubTwoLevelLinkage.setVisibility(View.GONE);
                            mRvPositionDistanceClassifyTwoLevelLinkage.setVisibility(View.VISIBLE);


                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ShoppingMallScreeningActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

    }

    @OnClick({R.id.rlyt_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlyt_back:

                ShoppingMallScreeningActivity.this.finish();

                break;

            default:
                break;
        }

    }

    public void getHomestayScreeningCondition() {

        if (validateInternet()) {
            mPresenter.getHomestayScreeningCondition();
        }
    }
}
