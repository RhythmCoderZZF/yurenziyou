package com.nbhysj.coupon.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.DropDownMenuClassifyThreeLevelAdapter;
import com.nbhysj.coupon.adapter.DropDownMenuDistanceClassifyAdapter;
import com.nbhysj.coupon.adapter.DropDownMenuListAdapter;
import com.nbhysj.coupon.adapter.DropDownMenuSubDistanceClassifyAdapter;
import com.nbhysj.coupon.adapter.ShopMallScreenStarClassAdapter;
import com.nbhysj.coupon.model.response.PositionDistanceBean;
import com.nbhysj.coupon.model.response.PositionDistanceResponse;
import com.nbhysj.coupon.model.response.TagBaseEntity;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.widget.DoubleSlideSeekBar;
import com.nbhysj.coupon.widget.dropdownmenu.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/08
 * description：推荐排序
 */
public class ShoppingMallScreeningActivity extends BaseActivity {
    private String headers[] = {"推荐排序", "位置距离", "价格/星级", "名宿"};
    @BindView(R.id.drop_down_menu_screening)
    DropDownMenu mDropDownMenu;
    //星级
    RecyclerView mRvStarClass;
    private DropDownMenuSubDistanceClassifyAdapter typeClassifyTwoLevelAdapter;
    DropDownMenuSubDistanceClassifyAdapter dropDownMenuDistanceTypeSubTwoLevelAdapter;
    DropDownMenuClassifyThreeLevelAdapter dropDownMenuClassifyThreeLevelAdapter;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_shopping_mall_screening;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        List<View> popupViews = new ArrayList<>();

        List<PositionDistanceBean> dropDownMenuList = new ArrayList<>();
        PositionDistanceBean positionDistance = new PositionDistanceBean();
        positionDistance.setPositionDistanceType("推荐排序");
        positionDistance.setSelect(true);
        PositionDistanceBean positionDistance1 = new PositionDistanceBean();
        positionDistance1.setPositionDistanceType("低价优先");
        PositionDistanceBean positionDistance2 = new PositionDistanceBean();
        positionDistance2.setPositionDistanceType("低价优先");
        PositionDistanceBean positionDistance3 = new PositionDistanceBean();
        positionDistance3.setPositionDistanceType("高价优先");
        PositionDistanceBean positionDistance4 = new PositionDistanceBean();
        positionDistance4.setPositionDistanceType("好评优先");
        dropDownMenuList.add(positionDistance);
        dropDownMenuList.add(positionDistance1);
        dropDownMenuList.add(positionDistance2);
        dropDownMenuList.add(positionDistance3);
        dropDownMenuList.add(positionDistance4);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        RecyclerView mRvdropDownMenuList = new RecyclerView(ShoppingMallScreeningActivity.this);
        mRvdropDownMenuList.setLayoutManager(linearLayoutManager);
        DropDownMenuListAdapter dropDownMenuListAdapter = new DropDownMenuListAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuListAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(PositionDistanceBean positionDistanceBean) {
                String positionDistanceType = positionDistanceBean.getPositionDistanceType();
                mDropDownMenu.setTabText(positionDistanceType);
                mDropDownMenu.closeMenu();
            }
        });
        dropDownMenuListAdapter.setDropDownMenuList(dropDownMenuList);
        mRvdropDownMenuList.setAdapter(dropDownMenuListAdapter);

/*
        List<PositionDistanceBean> dropDownMenuList2 = new ArrayList<>();
        PositionDistanceBean positionDistance5 = new PositionDistanceBean();
        positionDistance5.setPositionDistanceType("推荐排序");
        PositionDistanceBean positionDistance6 = new PositionDistanceBean();
        positionDistance6.setPositionDistanceType("低价优先");
        PositionDistanceBean positionDistance7 = new PositionDistanceBean();
        positionDistance7.setPositionDistanceType("低价优先");
        PositionDistanceBean positionDistance8 = new PositionDistanceBean();
        positionDistance8.setPositionDistanceType("高价优先");
        PositionDistanceBean positionDistance9 = new PositionDistanceBean();
        positionDistance9.setPositionDistanceType("好评优先");
        dropDownMenuList2.add(positionDistance5);
        dropDownMenuList2.add(positionDistance6);
        dropDownMenuList2.add(positionDistance7);
        dropDownMenuList2.add(positionDistance8);
        dropDownMenuList2.add(positionDistance9);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        linearLayoutManager2.setOrientation(linearLayoutManager2.VERTICAL);
        RecyclerView mRvdropDownMenuList2 = new RecyclerView(ShoppingMallScreeningActivity.this);
        mRvdropDownMenuList2.setLayoutManager(linearLayoutManager2);
        DropDownMenuListAdapter dropDownMenuListAdapter2 = new DropDownMenuListAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuListAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(PositionDistanceBean positionDistanceBean) {

            }
        });
        dropDownMenuListAdapter2.setDropDownMenuList(dropDownMenuList2);
        mRvdropDownMenuList2.setAdapter(dropDownMenuListAdapter2);*/

        List<PositionDistanceBean> dropDownMenuList3 = new ArrayList<>();
        PositionDistanceBean positionDistance10 = new PositionDistanceBean();
        positionDistance10.setPositionDistanceType("推荐排序");

        PositionDistanceBean positionDistance11 = new PositionDistanceBean();
        positionDistance11.setPositionDistanceType("低价优先");
        PositionDistanceBean positionDistance12 = new PositionDistanceBean();
        positionDistance12.setPositionDistanceType("低价优先");
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
            public void setDropDownMenuListener(PositionDistanceBean positionDistanceBean) {


            }
        });
        dropDownMenuListAdapter3.setDropDownMenuList(dropDownMenuList3);
        mRvdropDownMenuList3.setAdapter(dropDownMenuListAdapter3);


        View mViewDistanceCondition = getLayoutInflater().inflate(R.layout.layout_shop_mall_position_distance_condition, null);
        RecyclerView mRvPositionDistanceClassifyType = mViewDistanceCondition.findViewById(R.id.rv_position_distance_classify_type);
        //二级列表
        RecyclerView mRvPositionDistanceClassifyTwoLevelLinkage = mViewDistanceCondition.findViewById(R.id.rv_position_distance_classify_two_level_linkage);

        //三级列表时候（对应二级,三级列表）
        RecyclerView mRvPositionDistanceClassifySubTwoLevelLinkage = mViewDistanceCondition.findViewById(R.id.rv_position_distance_classify_sub_two_level_linkage);
        RecyclerView mRvPositionDistanceClassifyThreeLevelLinkage = mViewDistanceCondition.findViewById(R.id.rv_position_distance_classify_three_level_linkage);

        LinearLayoutManager distanceConditionLinearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        distanceConditionLinearLayoutManager.setOrientation(distanceConditionLinearLayoutManager.VERTICAL);
        mRvPositionDistanceClassifyType.setLayoutManager(distanceConditionLinearLayoutManager);


        List<PositionDistanceBean> positionDistanceList = new ArrayList<>();


        PositionDistanceBean positionDistanceBean = new PositionDistanceBean();
        positionDistanceBean.setPositionDistanceType("距离");
        List<PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel> positionDistanceTypeClassifyList = new ArrayList<>();
        PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel positionDistanceTypeClassify = new PositionDistanceBean().new PositionDistanceTypeClassifyTwoLevel();
        positionDistanceTypeClassify.setTwoLevelName("不限");
        positionDistanceTypeClassify.setSelect(true);
        PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel positionDistanceTypeClassify1 = new PositionDistanceBean().new PositionDistanceTypeClassifyTwoLevel();
        positionDistanceTypeClassify1.setTwoLevelName("≤500米");
        PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel positionDistanceTypeClassify2 = new PositionDistanceBean().new PositionDistanceTypeClassifyTwoLevel();
        positionDistanceTypeClassify2.setTwoLevelName("≤1公里");
        PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel positionDistanceTypeClassify3 = new PositionDistanceBean().new PositionDistanceTypeClassifyTwoLevel();
        positionDistanceTypeClassify3.setTwoLevelName("≤2公里");
        PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel positionDistanceTypeClassify4 = new PositionDistanceBean().new PositionDistanceTypeClassifyTwoLevel();
        positionDistanceTypeClassify4.setTwoLevelName("≤4公里");
        PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel positionDistanceTypeClassify5 = new PositionDistanceBean().new PositionDistanceTypeClassifyTwoLevel();
        positionDistanceTypeClassify5.setTwoLevelName("≤8公里");
        PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel positionDistanceTypeClassify6 = new PositionDistanceBean().new PositionDistanceTypeClassifyTwoLevel();
        positionDistanceTypeClassify6.setTwoLevelName("≤10公里");

        positionDistanceTypeClassifyList.add(positionDistanceTypeClassify);
        positionDistanceTypeClassifyList.add(positionDistanceTypeClassify1);
        positionDistanceTypeClassifyList.add(positionDistanceTypeClassify2);
        positionDistanceTypeClassifyList.add(positionDistanceTypeClassify3);
        positionDistanceTypeClassifyList.add(positionDistanceTypeClassify4);
        positionDistanceTypeClassifyList.add(positionDistanceTypeClassify5);
        positionDistanceTypeClassifyList.add(positionDistanceTypeClassify6);
        positionDistanceBean.setSelect(true);
        positionDistanceBean.setClassifyTwoLevelList(positionDistanceTypeClassifyList);


        PositionDistanceBean positionDistanceBean1 = new PositionDistanceBean();
        positionDistanceBean1.setPositionDistanceType("机场/车站");
        List<PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel> positionDistanceTypeClassifyTwoLevelList = new ArrayList<>();
        List<PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel> positionDistanceTypeClassifyThreeLevelList = new ArrayList<>();
        PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel positionDistanceTypeClassifyTwoLevel = new PositionDistanceBean().new PositionDistanceTypeClassifyTwoLevel();
        positionDistanceTypeClassifyTwoLevel.setTwoLevelName("机场");

        PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel positionDistanceTypeClassifyThreeLevel = new PositionDistanceBean().new PositionDistanceTypeClassifyThreeLevel();
        positionDistanceTypeClassifyThreeLevel.setThreeLevelName("不限");
        PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel positionDistanceTypeClassifyThreeLevel1 = new PositionDistanceBean().new PositionDistanceTypeClassifyThreeLevel();
        positionDistanceTypeClassifyThreeLevel1.setThreeLevelName("宁波栎社国际机场");
        positionDistanceTypeClassifyThreeLevelList.add(positionDistanceTypeClassifyThreeLevel);
        positionDistanceTypeClassifyThreeLevelList.add(positionDistanceTypeClassifyThreeLevel1);
        positionDistanceTypeClassifyTwoLevel.setClassifyThreeLevelList(positionDistanceTypeClassifyThreeLevelList);

        List<PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel> positionDistanceTypeClassifyThreeLevelList2 = new ArrayList<>();
        PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel positionDistanceTypeClassifyTwoLevel1 = new PositionDistanceBean().new PositionDistanceTypeClassifyTwoLevel();
        positionDistanceTypeClassifyTwoLevel1.setTwoLevelName("火车站");
        PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel positionDistanceTypeClassifyThreeLevel2 = new PositionDistanceBean().new PositionDistanceTypeClassifyThreeLevel();
        positionDistanceTypeClassifyThreeLevel2.setThreeLevelName("不限");
        PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel positionDistanceTypeClassifyThreeLevel3 = new PositionDistanceBean().new PositionDistanceTypeClassifyThreeLevel();
        positionDistanceTypeClassifyThreeLevel3.setThreeLevelName("宁波站");
        PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel positionDistanceTypeClassifyThreeLevel4 = new PositionDistanceBean().new PositionDistanceTypeClassifyThreeLevel();
        positionDistanceTypeClassifyThreeLevel4.setThreeLevelName("宁波东站");
        PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel positionDistanceTypeClassifyThreeLevel5 = new PositionDistanceBean().new PositionDistanceTypeClassifyThreeLevel();
        positionDistanceTypeClassifyThreeLevel5.setThreeLevelName("宁波火车南站");
        PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel positionDistanceTypeClassifyThreeLevel6 = new PositionDistanceBean().new PositionDistanceTypeClassifyThreeLevel();
        positionDistanceTypeClassifyThreeLevel6.setThreeLevelName("宁波站停车场");
        positionDistanceTypeClassifyThreeLevelList2.add(positionDistanceTypeClassifyThreeLevel2);
        positionDistanceTypeClassifyThreeLevelList2.add(positionDistanceTypeClassifyThreeLevel3);
        positionDistanceTypeClassifyThreeLevelList2.add(positionDistanceTypeClassifyThreeLevel4);
        positionDistanceTypeClassifyThreeLevelList2.add(positionDistanceTypeClassifyThreeLevel5);
        positionDistanceTypeClassifyThreeLevelList2.add(positionDistanceTypeClassifyThreeLevel6);
        positionDistanceTypeClassifyTwoLevel1.setClassifyThreeLevelList(positionDistanceTypeClassifyThreeLevelList2);

        List<PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel> positionDistanceTypeClassifyThreeLevelList3 = new ArrayList<>();
        PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel positionDistanceTypeClassifyTwoLevel2 = new PositionDistanceBean().new PositionDistanceTypeClassifyTwoLevel();
        positionDistanceTypeClassifyTwoLevel2.setTwoLevelName("长途汽车站");
        PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel positionDistanceTypeClassifyThreeLevel7 = new PositionDistanceBean().new PositionDistanceTypeClassifyThreeLevel();
        positionDistanceTypeClassifyThreeLevel7.setThreeLevelName("不限");
        PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel positionDistanceTypeClassifyThreeLevel8 = new PositionDistanceBean().new PositionDistanceTypeClassifyThreeLevel();
        positionDistanceTypeClassifyThreeLevel8.setThreeLevelName("宁波汽车南站");
        PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel positionDistanceTypeClassifyThreeLevel9 = new PositionDistanceBean().new PositionDistanceTypeClassifyThreeLevel();
        positionDistanceTypeClassifyThreeLevel9.setThreeLevelName("宁波客运中心");
        PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel positionDistanceTypeClassifyThreeLevel10 = new PositionDistanceBean().new PositionDistanceTypeClassifyThreeLevel();
        positionDistanceTypeClassifyThreeLevel10.setThreeLevelName("宁波汽车中心站");
        PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel positionDistanceTypeClassifyThreeLevel11 = new PositionDistanceBean().new PositionDistanceTypeClassifyThreeLevel();
        positionDistanceTypeClassifyThreeLevel11.setThreeLevelName("宁波汽车北站");
        PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel positionDistanceTypeClassifyThreeLevel12 = new PositionDistanceBean().new PositionDistanceTypeClassifyThreeLevel();
        positionDistanceTypeClassifyThreeLevel12.setThreeLevelName("宁波汽车东站");

        positionDistanceTypeClassifyThreeLevelList3.add(positionDistanceTypeClassifyThreeLevel7);
        positionDistanceTypeClassifyThreeLevelList3.add(positionDistanceTypeClassifyThreeLevel8);
        positionDistanceTypeClassifyThreeLevelList3.add(positionDistanceTypeClassifyThreeLevel9);
        positionDistanceTypeClassifyThreeLevelList3.add(positionDistanceTypeClassifyThreeLevel10);
        positionDistanceTypeClassifyThreeLevelList3.add(positionDistanceTypeClassifyThreeLevel11);
        positionDistanceTypeClassifyThreeLevelList3.add(positionDistanceTypeClassifyThreeLevel12);

        positionDistanceTypeClassifyTwoLevel2.setClassifyThreeLevelList(positionDistanceTypeClassifyThreeLevelList3);

        positionDistanceTypeClassifyTwoLevelList.add(positionDistanceTypeClassifyTwoLevel);
        positionDistanceTypeClassifyTwoLevelList.add(positionDistanceTypeClassifyTwoLevel1);
        positionDistanceTypeClassifyTwoLevelList.add(positionDistanceTypeClassifyTwoLevel2);
        positionDistanceBean1.setClassifyTwoLevelList(positionDistanceTypeClassifyTwoLevelList);
       /* PositionDistanceBean positionDistanceBean1 = new PositionDistanceBean();
        positionDistanceBean1.setPositionDistanceType("商圈");
        List<PositionDistanceBean.PositionDistanceTypeClassify> positionDistanceTypeClassifyList1 = new ArrayList<>();
        PositionDistanceBean.PositionDistanceTypeClassify positionDistanceTypeClassify6 = new PositionDistanceBean().new PositionDistanceTypeClassify();
        positionDistanceTypeClassify6.setPositionDistanceTypeClassify("不限");
        PositionDistanceBean.PositionDistanceTypeClassify positionDistanceTypeClassify7 = new PositionDistanceBean().new PositionDistanceTypeClassify();
        positionDistanceTypeClassify7.setPositionDistanceTypeClassify("天一广场/城隍庙/鼓楼");
        PositionDistanceBean.PositionDistanceTypeClassify positionDistanceTypeClassify8 = new PositionDistanceBean().new PositionDistanceTypeClassify();
        positionDistanceTypeClassify8.setPositionDistanceTypeClassify("鄞州万达/南部商务区");
        PositionDistanceBean.PositionDistanceTypeClassify positionDistanceTypeClassify9 = new PositionDistanceBean().new PositionDistanceTypeClassify();
        positionDistanceTypeClassify9.setPositionDistanceTypeClassify("海曙商业区");
        PositionDistanceBean.PositionDistanceTypeClassify positionDistanceTypeClassify10 = new PositionDistanceBean().new PositionDistanceTypeClassify();
        positionDistanceTypeClassify10.setPositionDistanceTypeClassify("老外滩/来福士广场");
        PositionDistanceBean.PositionDistanceTypeClassify positionDistanceTypeClassify11 = new PositionDistanceBean().new PositionDistanceTypeClassify();
        positionDistanceTypeClassify11.setPositionDistanceTypeClassify("高新区");
        positionDistanceTypeClassifyList1.add(positionDistanceTypeClassify6);
        positionDistanceTypeClassifyList1.add(positionDistanceTypeClassify7);
        positionDistanceTypeClassifyList1.add(positionDistanceTypeClassify8);
        positionDistanceTypeClassifyList1.add(positionDistanceTypeClassify9);
        positionDistanceTypeClassifyList1.add(positionDistanceTypeClassify10);
        positionDistanceTypeClassifyList1.add(positionDistanceTypeClassify11);
        positionDistanceBean1.setPositionDistanceTypeClassifiesList(positionDistanceTypeClassifyList1);

        PositionDistanceBean positionDistanceBean2 = new PositionDistanceBean();
        positionDistanceBean2.setPositionDistanceType("景点");
        List<PositionDistanceBean.PositionDistanceTypeClassify> positionDistanceTypeClassifyList2 = new ArrayList<>();
        PositionDistanceBean.PositionDistanceTypeClassify positionDistanceTypeClassify12 = new PositionDistanceBean().new PositionDistanceTypeClassify();
        positionDistanceTypeClassify12.setPositionDistanceTypeClassify("不限");
        PositionDistanceBean.PositionDistanceTypeClassify positionDistanceTypeClassify13 = new PositionDistanceBean().new PositionDistanceTypeClassify();
        positionDistanceTypeClassify13.setPositionDistanceTypeClassify("宁波海洋世界");
        PositionDistanceBean.PositionDistanceTypeClassify positionDistanceTypeClassify14 = new PositionDistanceBean().new PositionDistanceTypeClassify();
        positionDistanceTypeClassify14.setPositionDistanceTypeClassify("宁波雅戈尔动物园");
        PositionDistanceBean.PositionDistanceTypeClassify positionDistanceTypeClassify15 = new PositionDistanceBean().new PositionDistanceTypeClassify();
        positionDistanceTypeClassify15.setPositionDistanceTypeClassify("宁波植物园");
        PositionDistanceBean.PositionDistanceTypeClassify positionDistanceTypeClassify16 = new PositionDistanceBean().new PositionDistanceTypeClassify();
        positionDistanceTypeClassify16.setPositionDistanceTypeClassify("溪口雪窦寺");
        PositionDistanceBean.PositionDistanceTypeClassify positionDistanceTypeClassify17 = new PositionDistanceBean().new PositionDistanceTypeClassify();
        positionDistanceTypeClassify17.setPositionDistanceTypeClassify("宁波凤凰山");
        positionDistanceTypeClassifyList2.add(positionDistanceTypeClassify12);
        positionDistanceTypeClassifyList2.add(positionDistanceTypeClassify13);
        positionDistanceTypeClassifyList2.add(positionDistanceTypeClassify14);
        positionDistanceTypeClassifyList2.add(positionDistanceTypeClassify15);
        positionDistanceTypeClassifyList2.add(positionDistanceTypeClassify16);
        positionDistanceTypeClassifyList2.add(positionDistanceTypeClassify17);
        positionDistanceBean2.setPositionDistanceTypeClassifiesList(positionDistanceTypeClassifyList2);
*/
        positionDistanceList.add(positionDistanceBean);
        positionDistanceList.add(positionDistanceBean1);
        // positionDistanceList.add(positionDistanceBean2)

        DropDownMenuDistanceClassifyAdapter dropDownMenuDistanceTypeAdapter = new DropDownMenuDistanceClassifyAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuDistanceClassifyAdapter.DropDownMenuDistanceClassifyListener() {
            @Override
            public void setDropDownMenuDistanceClassifyListener(int position, PositionDistanceBean positionDistanceBean) {

                List<PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel> positionDistanceTypeClassify = positionDistanceList.get(position).getClassifyTwoLevelList();
                List<PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel> positionDistanceTypeClassifyThreeLevelList = positionDistanceTypeClassify.get(0).getClassifyThreeLevelList();
                if (positionDistanceTypeClassifyThreeLevelList != null) {

                    dropDownMenuDistanceTypeSubTwoLevelAdapter.setPositionDistanceList(positionDistanceTypeClassify);
                    dropDownMenuDistanceTypeSubTwoLevelAdapter.notifyDataSetChanged();
                    mRvPositionDistanceClassifyTwoLevelLinkage.setVisibility(View.GONE);
                    mRvPositionDistanceClassifySubTwoLevelLinkage.setVisibility(View.VISIBLE);
                    mRvPositionDistanceClassifyThreeLevelLinkage.setVisibility(View.VISIBLE);

                } else {
                    mRvPositionDistanceClassifyTwoLevelLinkage.setVisibility(View.VISIBLE);

                    mRvPositionDistanceClassifySubTwoLevelLinkage.setVisibility(View.GONE);
                    mRvPositionDistanceClassifyThreeLevelLinkage.setVisibility(View.GONE);
                }


            }
        });
        dropDownMenuDistanceTypeAdapter.setPositionDistanceList(positionDistanceList);
        mRvPositionDistanceClassifyType.setAdapter(dropDownMenuDistanceTypeAdapter);


        //位置距离二级列表
        LinearLayoutManager distanceConditionTypeClassifyLinearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        distanceConditionTypeClassifyLinearLayoutManager.setOrientation(distanceConditionTypeClassifyLinearLayoutManager.VERTICAL);
        mRvPositionDistanceClassifyTwoLevelLinkage.setLayoutManager(distanceConditionTypeClassifyLinearLayoutManager);
        typeClassifyTwoLevelAdapter = new DropDownMenuSubDistanceClassifyAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuSubDistanceClassifyAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(int position, PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel positionDistanceBean) {

                try {

                    for (int i = 0; i < positionDistanceList.size(); i++) {
                        PositionDistanceBean positionDistance = positionDistanceList.get(i);
                        List<PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel> positionDistanceTypeSubList = positionDistance.getClassifyTwoLevelList();

                        for (int j = 0; j < positionDistanceTypeSubList.size(); j++) {
                            if (j == position && positionDistance.isSelect()) {
                                positionDistanceTypeSubList.get(j).setSelect(true);

                            } else {
                                positionDistanceTypeSubList.get(j).setSelect(false);
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                String positionDistanceType = positionDistanceBean.getTwoLevelName();
                mDropDownMenu.setTabText(positionDistanceType);
                // mDropDownMenu.closeMenu();

            }
        });

        typeClassifyTwoLevelAdapter.setPositionDistanceList(positionDistanceTypeClassifyList);
        mRvPositionDistanceClassifyTwoLevelLinkage.setAdapter(typeClassifyTwoLevelAdapter);

        //位置距离二级列表
        LinearLayoutManager subTwoLevelLinearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        subTwoLevelLinearLayoutManager.setOrientation(subTwoLevelLinearLayoutManager.VERTICAL);
        mRvPositionDistanceClassifySubTwoLevelLinkage.setLayoutManager(subTwoLevelLinearLayoutManager);
        dropDownMenuDistanceTypeSubTwoLevelAdapter = new DropDownMenuSubDistanceClassifyAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuSubDistanceClassifyAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(int position, PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel positionDistanceBean) {

            }
        });
        List<PositionDistanceBean.PositionDistanceTypeClassifyTwoLevel> positionDistanceTypeClassifyTwoLevels = new ArrayList<>();

        dropDownMenuDistanceTypeSubTwoLevelAdapter.setPositionDistanceList(positionDistanceTypeClassifyTwoLevels);
        mRvPositionDistanceClassifySubTwoLevelLinkage.setAdapter(dropDownMenuDistanceTypeSubTwoLevelAdapter);


        //位置距离三级列表
        LinearLayoutManager threeLevelLinearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        threeLevelLinearLayoutManager.setOrientation(threeLevelLinearLayoutManager.VERTICAL);
        mRvPositionDistanceClassifyThreeLevelLinkage.setLayoutManager(threeLevelLinearLayoutManager);
        dropDownMenuClassifyThreeLevelAdapter = new DropDownMenuClassifyThreeLevelAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuClassifyThreeLevelAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(int position, PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel positionDistanceBean) {


            }
        });
        List<PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel> positionDistanceTypeClassifyThreeLevels = new ArrayList<>();
        dropDownMenuClassifyThreeLevelAdapter.setPositionDistanceList(positionDistanceTypeClassifyThreeLevels);
        mRvPositionDistanceClassifyThreeLevelLinkage.setAdapter(dropDownMenuDistanceTypeSubTwoLevelAdapter);


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


}
