package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.AddTouristInformationAdapter;
import com.nbhysj.coupon.adapter.IncreaseTicketAdapter;
import com.nbhysj.coupon.adapter.OrderDetailTicketInfoAdapter;
import com.nbhysj.coupon.adapter.OrderUseDateSelectAdapter;
import com.nbhysj.coupon.adapter.TouristInformationAdapter;
import com.nbhysj.coupon.adapter.VehicleUseAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.OrderSubmitContract;
import com.nbhysj.coupon.dialog.OrderSubmitDatePickerDialog;
import com.nbhysj.coupon.dialog.VehicleSelectionModelAddDialog;
import com.nbhysj.coupon.dialog.VehicleUseAddDialog;
import com.nbhysj.coupon.dialog.VehicleUseTimeSelectDialog;
import com.nbhysj.coupon.model.OrderSubmitModel;
import com.nbhysj.coupon.model.request.CarsBean;
import com.nbhysj.coupon.model.request.GoodsBean;
import com.nbhysj.coupon.model.request.TicketOrderSubmitRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CarTypeBean;
import com.nbhysj.coupon.model.response.EstimatedPriceResponse;
import com.nbhysj.coupon.model.response.GoodsPriceDatesResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.model.response.TicketOrderSubmitResponse;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;
import com.nbhysj.coupon.presenter.OrderSubmitPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.DateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/08/20
 * description：订单提交
 */
public class OrderSubmitActivity extends BaseActivity<OrderSubmitPresenter, OrderSubmitModel> implements OrderSubmitContract.View, PoiSearch.OnPoiSearchListener {

    //订单使用日期选择
    @BindView(R.id.rv_use_ticket_date)
    RecyclerView mRvUseTicketDateSelect;
    //游客信息列表
    @BindView(R.id.rv_tourist_information)
    RecyclerView mRvTouristInformation;
    //游客名字
    @BindView(R.id.tv_tourist_name)
    TextView mTvTouristName;
    //游客电话
    @BindView(R.id.tv_tourist_mobile)
    TextView mTvTouristMobile;

    @BindView(R.id.rv_increase_ticket)
    RecyclerView mRvIncreaseTicket;
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.llyt_order_detail_item)
    LinearLayout mLlytOrderDetailItem;
    @BindView(R.id.rlyt_order_submit)
    RelativeLayout mRlytOrderSubmit;
    @BindView(R.id.rlyt_order_submit_header)
    RelativeLayout mRlytOrderSubmitHeader;
    //订单明细购票信息
    @BindView(R.id.rv_order_deatil_ticket_info)
    RecyclerView mRvOrderDeatilTicketInfo;
    //订单明细查看
    @BindView(R.id.ckb_order_detail_look)
    CheckBox mCkbOrderDetailLook;
    @BindView(R.id.tv_order_submit)
    TextView mTvOrderSubmit;
    //阴暗背景
    @BindView(R.id.llyt_shadow_bg)
    LinearLayout mLlytShadowBg;
    @BindView(R.id.tv_order_detail_look)
    TextView mTvOrderDetailLook;
    //退出动画
    TranslateAnimation outAnimation;
    //进入动画
    TranslateAnimation inAnimation;
    //新增游客
    @BindView(R.id.rv_new_tourists)
    RecyclerView mRvNewTourists;
    //新增游客阴暗背景
    @BindView(R.id.rlyt_new_tourists_bg_half)
    RelativeLayout mRlytNewTouristsBgHalf;
    @BindView(R.id.llyt_add_and_update_tourists)
    LinearLayout mLlytAddAndUpdateTourists;
    @BindView(R.id.tv_added_frequently_used_tourists)
    TextView mTvAddedFrequentlyUsedTourists;
    @BindView(R.id.rlyt_ticket)
    RelativeLayout mRlytTicket;
    @BindView(R.id.llyt_edit_tourists)
    LinearLayout mLlytEditTourists;
    @BindView(R.id.llyt_add_tourists)
    LinearLayout mLlytAddTourists;
    //购买数量
    @BindView(R.id.tv_purchase_num)
    TextView mTvPurchaseNum;
    //市场票价
    @BindView(R.id.tv_market_ticket_price)
    TextView mTvMarketTicketPrice;
    //票标题
    @BindView(R.id.tv_ticket_title)
    TextView mTvTicketTitle;

    @BindView(R.id.llyt_increase_ticket)
    LinearLayout mLlytIncreaseTicket;

    //添加更多用车
    @BindView(R.id.tv_add_vehicle_more)
    TextView mTvAddVehicleMore;

    //是否需要用车
    @BindView(R.id.ckb_is_need_use_car)
    CheckBox mCkbIsNeedUseCar;

    //用车列表
    @BindView(R.id.rv_travel_by_car)
    RecyclerView mRvTravelByCar;

    //车辆使用
    @BindView(R.id.llyt_vehicle_use)
    LinearLayout mLlytVehicleUse;

    //购买数量
    private int mPurchaseNum = 1;

    //使用日期价格
    private int datePrice;

    //增加门票价格
    private int increaseTicketPrice;

    //商品id
    private int goodsId;

    private List<OrderSubmitInitResponse.TravellersEntity> travellersList;

    //游客信息适配器
    private TouristInformationAdapter touristInformationAdapter;

    //更多日期选择弹框
    private OrderSubmitDatePickerDialog orderSubmitDatePickerDialog;

    //时间价格订单适配器
    private OrderUseDateSelectAdapter orderUseDateSelectAdapter;

    //增加门票适配器
    private IncreaseTicketAdapter increaseTicketAdapter;

    //价格明细
    OrderDetailTicketInfoAdapter orderDetailTicketInfoAdapter;

    //日历价格
    private List<GoodsPriceDatesResponse> orderSubmitDateList;

    private List<OrderSubmitInitResponse.GoodsPriceEntity> goodsPriceList;

    //增加门票
    private List<OrderSubmitInitResponse.GoodsPriceEntity> goodsPriceTicketAddList;

    //价格明细
    private List<OrderSubmitInitResponse.GoodsPriceEntity> goodsPriceDetailList;

    //我的位置
    private int REQUEST_CODE_MY_LOCATION_SELECT = 0;

    //目的地
    private int REQUEST_CODE_DESTINATION_SELECT = 1;

    //0:我的位置 1:您要去的地方(目的地) 2:用车时间 3:选择车辆型号
    private int mVehicleUseOprateFlag = 0;

    //车辆使用弹框
    private VehicleUseAddDialog vehicleUseAddDialog;

    //用车时间选择
    private VehicleUseTimeSelectDialog vehicleUseTimeSelectDialog;

    //选择车辆型号
    private VehicleSelectionModelAddDialog vehicleSelectionModelAddDialog;

    //增加游客信息
    private AddTouristInformationAdapter addTouristInformationAdapter;

    //用车适配器
    private VehicleUseAdapter vehicleUseAdapter;

    Map<String, Object> map = new HashMap<>();

    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索

    private PoiResult poiResult; // poi返回的结果

    //商品列表
    private List<GoodsBean> goodsList;

    private List<CarsBean> carsBeanList;

    //旅客id
    private int userTravelerId;

    //商品价格日历选择
    private String goodsPriceDateSelect;
    @Override
    public int getLayoutId() {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_submit_order;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        //沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }

        goodsId = getIntent().getIntExtra("goodsId", 0);

        if (travellersList == null) {

            travellersList = new ArrayList<>();
        } else {
            travellersList.clear();
        }

        //使用日期
        if (orderSubmitDateList == null) {

            orderSubmitDateList = new ArrayList<>();
        } else {

            orderSubmitDateList.clear();
        }

        if (goodsPriceList == null) {

            goodsPriceList = new ArrayList<>();
        } else {
            goodsPriceList.clear();
        }

        if (goodsPriceTicketAddList == null) {

            goodsPriceTicketAddList = new ArrayList<>();
        } else {
            goodsPriceTicketAddList.clear();
        }

        //价格明细
        if (goodsPriceDetailList == null) {

            goodsPriceDetailList = new ArrayList<>();
        } else {
            goodsPriceDetailList.clear();
        }

        //商品列表 组装订单提交使用
        if(goodsList != null){

            goodsList = new ArrayList<>();
        } else {
            goodsList.clear();
        }

        if (carsBeanList == null) {
            carsBeanList = new ArrayList<>();
        } else {
            carsBeanList.clear();
        }

        inAnimation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 1, TranslateAnimation.RELATIVE_TO_SELF, 0);
        inAnimation.setDuration(100l);     //设置动画的过渡时间

        outAnimation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 1);
        outAnimation.setDuration(100l);     //设置动画的过渡时间


        mCkbOrderDetailLook.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {

                if (isCheck) {

                    mLlytOrderDetailItem.setVisibility(View.VISIBLE);
                    mLlytOrderDetailItem.startAnimation(inAnimation);
                    mLlytShadowBg.setVisibility(View.VISIBLE);

                    Drawable nav_up = getResources().getDrawable(R.mipmap.icon_order_detail_down_arrow);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    mCkbOrderDetailLook.setCompoundDrawables(null, null, nav_up, null);

                    if (goodsPriceDetailList != null) {
                        goodsPriceDetailList.clear();
                    }

                    if (goodsPriceList != null) {

                        //对增加门票进行遍历

                        OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(0);
                        int ticketPurchaseNum = goodsPriceEntity.getTicketPurchaseNum();
                        if (ticketPurchaseNum > 0) {
                            goodsPriceDetailList.add(goodsPriceEntity);
                        }
                    }

                    if (goodsPriceTicketAddList != null) {

                        //对增加门票进行遍历
                        for (int i = 0; i < goodsPriceTicketAddList.size(); i++) {
                            OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceTicketAddList.get(i);
                            int ticketPurchaseNum = goodsPriceEntity.getTicketPurchaseNum();
                            if (ticketPurchaseNum > 0) {
                                goodsPriceDetailList.add(goodsPriceEntity);
                            }
                        }
                        orderDetailTicketInfoAdapter.setOrderDetailTicketList(goodsPriceDetailList);
                        orderDetailTicketInfoAdapter.notifyDataSetChanged();
                    }
                } else {
                    mLlytOrderDetailItem.setVisibility(View.GONE);
                    mLlytOrderDetailItem.startAnimation(outAnimation);
                    mLlytShadowBg.setVisibility(View.GONE);
                    Drawable nav_up = getResources().getDrawable(R.mipmap.icon_order_detail_up_arrow);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    mCkbOrderDetailLook.setCompoundDrawables(null, null, nav_up, null);
                }
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(OrderSubmitActivity.this, 4);
        mRvUseTicketDateSelect.setLayoutManager(layoutManager);
        orderUseDateSelectAdapter = new OrderUseDateSelectAdapter(OrderSubmitActivity.this, new OrderUseDateSelectAdapter.OrderUseDateSelectListener() {
            @Override
            public void moreDatesCallBack() {

                if (orderSubmitDatePickerDialog == null) {
                    orderSubmitDatePickerDialog = new OrderSubmitDatePickerDialog();
                    orderSubmitDatePickerDialog.setDataStatisticsDatePickerDialog(new OrderSubmitDatePickerDialog.OrderSubmitDatePickerListener() {
                        @Override
                        public void setDataStatisticsDatePickerListener(String startDate, String endDate) {

                        }

                        @Override
                        public void setNoDatePickerSelectListener() {

                        }
                    });
                    orderSubmitDatePickerDialog.show(getFragmentManager(), "数据日期选择");
                } else {
                    orderSubmitDatePickerDialog.show(getFragmentManager(), "数据日期选择");
                    orderSubmitDatePickerDialog.setDatePickerClear();
                }

            }

            @Override
            public void datePriceSelectCallBack(int position) {
                GoodsPriceDatesResponse goodsPriceDatesResponse = orderSubmitDateList.get(position);
                datePrice = goodsPriceDatesResponse.getPrice();
                goodsPriceDateSelect = goodsPriceDatesResponse.getDate();
                int totalPrice = increaseTicketPrice + datePrice;
                mTvMarketTicketPrice.setText(String.valueOf(totalPrice));

            }
        });
        //订单提交日期
        orderSubmitDateList = DateUtil.getOrderSubmitDate();
        orderUseDateSelectAdapter.setOrderUseDateSelectList(orderSubmitDateList);
        mRvUseTicketDateSelect.setAdapter(orderUseDateSelectAdapter);

        /**
         * 游客信息列表
         */
        LinearLayoutManager touristInfoLayoutManager = new LinearLayoutManager(OrderSubmitActivity.this);
        touristInfoLayoutManager.setOrientation(touristInfoLayoutManager.HORIZONTAL);
        mRvTouristInformation.setLayoutManager(touristInfoLayoutManager);
        touristInformationAdapter = new TouristInformationAdapter(OrderSubmitActivity.this, new TouristInformationAdapter.TouristInformationListener() {
            @Override
            public void setTouristInformationListener(int position, boolean isAddTourists) {

                if (isAddTourists) {

                    //   NewTouristsDialog newTouristsDialog = new NewTouristsDialog();
                    //newTouristsDialog.getDialog().setCancelable(true);
                    //newTouristsDialog.getDialog().setCanceledOnTouchOutside(true);
                    // newTouristsDialog.show(getFragmentManager(),"newTourist");

                    mLlytAddAndUpdateTourists.setVisibility(View.VISIBLE);
                    mLlytAddAndUpdateTourists.startAnimation(inAnimation);
                    mRlytNewTouristsBgHalf.setVisibility(View.VISIBLE);
                    if (addTouristInformationAdapter != null) {
                        addTouristInformationAdapter.notifyDataSetChanged();
                    }
                } else {
                    OrderSubmitInitResponse.TravellersEntity travellersEntity = travellersList.get(position);
                    String realname = travellersEntity.getRealname();
                    String mobile = travellersEntity.getMobile();
                    userTravelerId = travellersEntity.getUserId();
                    mTvTouristName.setText(realname);
                    mTvTouristMobile.setText(mobile);
                }
            }
        });
        touristInformationAdapter.setTouristInfoList(travellersList);
        mRvTouristInformation.setAdapter(touristInformationAdapter);

        //增加票价
        LinearLayoutManager increaseTicketLayoutManager = new LinearLayoutManager(OrderSubmitActivity.this);
        mRvIncreaseTicket.setLayoutManager(increaseTicketLayoutManager);
        increaseTicketLayoutManager.setOrientation(increaseTicketLayoutManager.VERTICAL);
        increaseTicketAdapter = new IncreaseTicketAdapter(OrderSubmitActivity.this, new IncreaseTicketAdapter.IncreaseTicketListener() {
            @Override
            public void setPurchaseNumListener(int position, int purchasePrice, int mAddTicketPurchaseNum) { //mAddTicketPurchaseNum 增加门票数量
                increaseTicketPrice = purchasePrice;
                int totalPrice = increaseTicketPrice + datePrice * mPurchaseNum;  //mPurchaseNum
                mTvMarketTicketPrice.setText(String.valueOf(totalPrice));
                if (goodsPriceTicketAddList.size() > 0) {
                    goodsPriceTicketAddList.get(position).setTicketPurchaseNum(mAddTicketPurchaseNum);
                }
            }
        });
        increaseTicketAdapter.setIncreaseTicketList(goodsPriceList);
        mRvIncreaseTicket.setAdapter(increaseTicketAdapter);

        LinearLayoutManager orderDeatilTicketInfoLayoutManager = new LinearLayoutManager(OrderSubmitActivity.this);
        mRvOrderDeatilTicketInfo.setLayoutManager(orderDeatilTicketInfoLayoutManager);
        orderDeatilTicketInfoLayoutManager.setOrientation(orderDeatilTicketInfoLayoutManager.VERTICAL);
        orderDetailTicketInfoAdapter = new OrderDetailTicketInfoAdapter(OrderSubmitActivity.this);
        orderDetailTicketInfoAdapter.setOrderDetailTicketList(goodsPriceDetailList);
        mRvOrderDeatilTicketInfo.setAdapter(orderDetailTicketInfoAdapter);

        //新增游客信息弹框
        LinearLayoutManager newTouristsLayoutManager = new LinearLayoutManager(OrderSubmitActivity.this);
        mRvNewTourists.setLayoutManager(newTouristsLayoutManager);
        newTouristsLayoutManager.setOrientation(newTouristsLayoutManager.VERTICAL);
        addTouristInformationAdapter = new AddTouristInformationAdapter(OrderSubmitActivity.this, new AddTouristInformationAdapter.TouristInformationListener() {
            @Override
            public void setEditTouristInfoListener(int position) {

                mLlytEditTourists.setVisibility(View.VISIBLE);
                mLlytEditTourists.startAnimation(inAnimation);

            }
        });
        addTouristInformationAdapter.setTouristInfoList(travellersList);
        mRvNewTourists.setAdapter(addTouristInformationAdapter);

        mLlytAddAndUpdateTourists.setEnabled(false);

        //新增游客信息弹框
        LinearLayoutManager travelByCarLayoutManager = new LinearLayoutManager(OrderSubmitActivity.this);
        travelByCarLayoutManager.setOrientation(travelByCarLayoutManager.VERTICAL);
        mRvTravelByCar.setLayoutManager(travelByCarLayoutManager);
        vehicleUseAdapter = new VehicleUseAdapter();
        vehicleUseAdapter.setVehicleUseList(carsBeanList);
        mRvTravelByCar.setAdapter(vehicleUseAdapter);

    }

    @Override
    public void initData() {

        getOrderSubmitInit();

        mCkbIsNeedUseCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {

                if (isCheck) {

                    showVehicleUseAddDialog();

                } else {
                    mLlytVehicleUse.setVisibility(View.GONE);
                    carsBeanList.clear();
                    vehicleUseAdapter.setVehicleUseList(carsBeanList);
                    vehicleUseAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.tv_order_submit, R.id.llyt_shadow_bg, R.id.rlyt_new_tourists_bg_half, R.id.tv_added_frequently_used_tourists,
            R.id.rlyt_ticket, R.id.tv_confirm, R.id.llyt_edit_tourists, R.id.ibtn_back, R.id.tv_purchase_notes, R.id.img_reduce_purchase_num,
            R.id.img_add_purchase_num, R.id.tv_add_vehicle_more})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_order_submit:

                // showToast(OrderSubmitActivity.this, "订单提交");
              //  toActivity(PaymentOrderActivity.class);
                ticketOrderSubmit();
                break;
            case R.id.llyt_shadow_bg:

              /*  mLlytOrderDetailItem.setVisibility(View.GONE);
                mLlytOrderDetailItem.startAnimation(ctrlOutAnimation);
                Drawable nav_up = getResources().getDrawable(R.mipmap.icon_order_detail_down_arrow);
                nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                mTvOrderDetailLook.setCompoundDrawables(null, null, nav_up, null);*/
                mCkbOrderDetailLook.setChecked(false);
                break;
            case R.id.rlyt_new_tourists_bg_half:

                int addTouristsVisibility = mLlytAddTourists.getVisibility();
                int editTouristsVisibility = mLlytEditTourists.getVisibility();
                if (addTouristsVisibility == 8 && editTouristsVisibility == 8) {
                    mRlytNewTouristsBgHalf.setVisibility(View.GONE);
                    mLlytAddAndUpdateTourists.setVisibility(View.GONE);
                    mLlytAddAndUpdateTourists.startAnimation(outAnimation);

                } else {

                    mLlytAddTourists.setVisibility(View.GONE);
                    mLlytAddTourists.startAnimation(outAnimation);

                }
                if (editTouristsVisibility == 8 && addTouristsVisibility == 8) {

                    mRlytNewTouristsBgHalf.setVisibility(View.GONE);
                    mLlytAddAndUpdateTourists.setVisibility(View.GONE);
                    mLlytAddAndUpdateTourists.startAnimation(outAnimation);

                } else {

                    mLlytEditTourists.setVisibility(View.GONE);
                    mLlytEditTourists.startAnimation(outAnimation);
                }

                //    mRlytNewTouristsBgHalf.setVisibility(View.GONE);
                //  mLlytNewTourists.setVisibility(View.GONE);
                //mLlytNewTourists.startAnimation(outAnimation);


                break;
            case R.id.tv_added_frequently_used_tourists:

                mLlytAddTourists.setVisibility(View.VISIBLE);
                mLlytAddTourists.startAnimation(inAnimation);
                int visibility = mLlytAddAndUpdateTourists.getVisibility();
                if (visibility == 8) {
                    mRlytNewTouristsBgHalf.setVisibility(View.VISIBLE);
                }

                //  mRlytNewTouristsBgHalf.setVisibility(View.GONE);
               /* NewTouristsDialog newTouristsDialog = new NewTouristsDialog();
                //newTouristsDialog.getDialog().setCancelable(true);
                //newTouristsDialog.getDialog().setCanceledOnTouchOutside(true);
                 newTouristsDialog.show(getFragmentManager(),"newTourist");*/


                break;
            case R.id.rlyt_ticket:

           //     ticketOrderSubmit();
                break;
            case R.id.tv_confirm:
                showToast(OrderSubmitActivity.this, mLlytAddAndUpdateTourists.getVisibility() + "");
                break;
            case R.id.ibtn_back:

                OrderSubmitActivity.this.finish();

                break;
            case R.id.tv_purchase_notes:
                showToast(OrderSubmitActivity.this, "购买须知");
                break;
            case R.id.img_reduce_purchase_num:

                if (mPurchaseNum > 1) {
                    mPurchaseNum--;
                    mTvPurchaseNum.setText(String.valueOf(mPurchaseNum));
                    if (goodsPriceList.size() > 0) {
                        OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(0);
                        goodsPriceEntity.setTicketPurchaseNum(mPurchaseNum);
                        int totalPrice = increaseTicketPrice + datePrice * mPurchaseNum;
                        mTvMarketTicketPrice.setText(String.valueOf(totalPrice));
                    }
                } else {

                    showToast(OrderSubmitActivity.this, getResources().getString(R.string.str_need_to_buy_at_least_one));
                    return;
                }

                break;
            case R.id.img_add_purchase_num:
                mPurchaseNum++;
                mTvPurchaseNum.setText(String.valueOf(mPurchaseNum));
                if (goodsPriceList.size() > 0) {
                    OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(0);
                    goodsPriceEntity.setTicketPurchaseNum(mPurchaseNum);
                }
                int totalPrice = increaseTicketPrice + datePrice * mPurchaseNum;
                mTvMarketTicketPrice.setText(String.valueOf(totalPrice));
                break;
            case R.id.tv_add_vehicle_more:

                showVehicleUseAddDialog();
                break;
            default:
                break;
        }
    }

    @Override
    public void getUserTravellerListResult(BackResult<TravellerInfoResponse> res) {

    }

    @Override
    public void addUserTravellerResult(BackResult res) {

    }

    @Override
    public void updateUserTravellerResult(BackResult res) {

    }

    @Override
    public void deleteUserTravellerResult(BackResult res) {

    }

    @Override
    public void ticketOrderSubmitResult(BackResult<TicketOrderSubmitResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    TicketOrderSubmitResponse ticketOrderSubmitResponse = res.getData();
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ticketOrderSubmit",ticketOrderSubmitResponse);
                    intent.putExtras(bundle);
                    intent.setClass(OrderSubmitActivity.this,PaymentOrderActivity.class);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(OrderSubmitActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getOrderSubmitInitResult(BackResult<OrderSubmitInitResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    OrderSubmitInitResponse orderSubmitInitResponse = res.getData();
                    travellersList = orderSubmitInitResponse.getTravellers();
                    userTravelerId = travellersList.get(0).getUserId();

                    goodsPriceList = orderSubmitInitResponse.getGoodsPrice();
                    OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(0);
                    goodsPriceEntity.setTicketPurchaseNum(1);
                    String title = goodsPriceEntity.getTitle();
                    //商品价格日期
                    List<GoodsPriceDatesResponse> goodsPriceDatesList = goodsPriceEntity.getGoodsPriceDates();

                    for (int i = 0; i < orderSubmitDateList.size(); i++) {
                        GoodsPriceDatesResponse orderSubmitDateResponse = orderSubmitDateList.get(i);
                        String orderSubmitDate = orderSubmitDateResponse.getDate();
                        for (int j = 0; j < goodsPriceDatesList.size(); j++) {
                            GoodsPriceDatesResponse goodsPriceDatesResponse = goodsPriceDatesList.get(j);
                            String goodsPriceDate = goodsPriceDatesResponse.getDate();
                            int id = goodsPriceDatesResponse.getId();
                            int price = goodsPriceDatesResponse.getPrice();

                            if (orderSubmitDate.equals(goodsPriceDate)) {
                                orderSubmitDateResponse.setIsCanBooking(1);
                                orderSubmitDateResponse.setId(id);
                                orderSubmitDateResponse.setPrice(price);
                                orderSubmitDateResponse.setSelectDatePrice(true);
                            }
                        }
                    }

                    //订单日期价格
                    orderUseDateSelectAdapter.setOrderUseDateSelectList(orderSubmitDateList);
                    orderUseDateSelectAdapter.notifyDataSetChanged();

                    //增加门票
                    if (goodsPriceList != null) {
                        if (goodsPriceList.size() > 0) {

                            for (int i = 0; i < goodsPriceList.size(); i++) {
                                if (i > 0) {
                                    OrderSubmitInitResponse.GoodsPriceEntity goodsPriceBean = goodsPriceList.get(i);
                                    goodsPriceTicketAddList.add(goodsPriceBean);
                                }
                            }
                        }
                    }

                    if (goodsPriceTicketAddList.size() == 0) {
                        mLlytIncreaseTicket.setVisibility(View.GONE);

                    } else {

                        mLlytIncreaseTicket.setVisibility(View.VISIBLE);
                        increaseTicketAdapter.setIncreaseTicketList(goodsPriceTicketAddList);
                        increaseTicketAdapter.notifyDataSetChanged();
                    }

                    if (travellersList != null) {
                        OrderSubmitInitResponse.TravellersEntity travellersEntity = travellersList.get(0);
                        if (travellersEntity != null) {
                            travellersEntity.setTravellerSelect(true);
                            String realname = travellersEntity.getRealname();
                            String mobile = travellersEntity.getMobile();
                            mTvTouristName.setText(realname);
                            mTvTouristMobile.setText(mobile);
                        }

                        touristInformationAdapter.setTouristInfoList(travellersList);
                        touristInformationAdapter.notifyDataSetChanged();

                        //新增游客编辑
                        addTouristInformationAdapter.setTouristInfoList(travellersList);
                        touristInformationAdapter.notifyDataSetChanged();
                    }
                    mTvTicketTitle.setText(title);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(OrderSubmitActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getEstimatedPriceResult(BackResult<EstimatedPriceResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    EstimatedPriceResponse estimatedPrice = res.getData();
                    vehicleUseAddDialog.setTotalVehicleExpenses(estimatedPrice);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(OrderSubmitActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(OrderSubmitActivity.this, Constants.getResultMsg(msg));
    }

    //获取订单初始化
    public void getOrderSubmitInit() {

        if (validateInternet()) {
            showProgressDialog(OrderSubmitActivity.this);
            mPresenter.getOrderSubmitInit(goodsId);
        }
    }

    //获取订单初始化
    public void getEvaluateVehicleUsageFee(String departureTime, String startLg, String startLt, String endLg, String endLt, int carType) {

        if (validateInternet()) {
            if (!TextUtils.isEmpty(departureTime)) {
                map.put("departureTime", departureTime);
            } else {
                showToast(OrderSubmitActivity.this, "请选择出行时间");
                return;
            }

            if (!TextUtils.isEmpty(startLg)) {
                map.put("startLg", startLg);
            } else {
                showToast(OrderSubmitActivity.this, "请选择所在位置");
                return;
            }

            if (!TextUtils.isEmpty(startLt)) {
                map.put("startLt", startLt);
            } else {
                showToast(OrderSubmitActivity.this, "请选择所在位置");
                return;
            }

            if (!TextUtils.isEmpty(endLg)) {
                map.put("endLg", endLg);
            } else {
                showToast(OrderSubmitActivity.this, "请选择目的地");
                return;
            }

            if (!TextUtils.isEmpty(endLt)) {
                map.put("endLt", endLt);
            } else {
                showToast(OrderSubmitActivity.this, "请选择目的地");
                return;
            }

            if (carType != 0) {

                map.put("carType", carType);
            } else {
                showToast(OrderSubmitActivity.this, "请选择车辆类型");
                return;
            }
            showProgressDialog(OrderSubmitActivity.this);
            mDialog.setTitle("正在计算,请稍等...");
            mPresenter.getEstimatedPrice(map);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MY_LOCATION_SELECT && resultCode == RESULT_OK) {  //车辆我的位置
            Tip tip = data.getParcelableExtra("tip");
            LatLonPoint latLonPoint = tip.getPoint();
            String addressName = tip.getName();
            vehicleUseAddDialog.setVehicleUseAddressData(latLonPoint, addressName, REQUEST_CODE_MY_LOCATION_SELECT);

        } else if (requestCode == REQUEST_CODE_DESTINATION_SELECT && resultCode == RESULT_OK) {  //车辆目的地
            Tip tip = data.getParcelableExtra("tip");
            LatLonPoint latLonPoint = tip.getPoint();
            String addressName = tip.getName();
            vehicleUseAddDialog.setVehicleUseAddressData(latLonPoint, addressName, REQUEST_CODE_DESTINATION_SELECT);
        }
    }

    /**
     * 订单提交
     */
    public void ticketOrderSubmit(){

        if(validateInternet()){

            goodsList.clear();
            TicketOrderSubmitRequest ticketOrderSubmitRequest = new TicketOrderSubmitRequest();
            ticketOrderSubmitRequest.setUserTravelerId(userTravelerId);

            OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(0);
            int goodsId = goodsPriceEntity.getGoodsId();
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setGoodsId(goodsId);
            goodsBean.setNum(mPurchaseNum);
            goodsBean.setPriceDate(goodsPriceDateSelect);
            goodsList.add(goodsBean);

            for(int i = 0;i < goodsPriceTicketAddList.size();i++)
            {

                OrderSubmitInitResponse.GoodsPriceEntity goodsPrice = goodsPriceTicketAddList.get(i);
                GoodsBean goodsAddTicket = new GoodsBean();
                goodsAddTicket.setGoodsId(goodsId);
                goodsAddTicket.setNum(goodsPrice.getTicketPurchaseNum());    //1.增加门票模块 票数字段 采用ticketPurchaseNum 2.外层价格日历选择 票数字段 采用mPurchaseNum 默认为1
                goodsAddTicket.setPriceDate(goodsPriceDateSelect);
                goodsList.add(goodsAddTicket);
            }

            ticketOrderSubmitRequest.setGoods(goodsList);
            ticketOrderSubmitRequest.setCars(carsBeanList);

            //是否用车 1:用 || 0:不用
            if(mCkbIsNeedUseCar.isChecked())
            {
                ticketOrderSubmitRequest.setCarStatus(1);
            } else {
                ticketOrderSubmitRequest.setCarStatus(0);
            }
            mPresenter.ticketOrderSubmit(ticketOrderSubmitRequest);
        }
    }

    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery(String keywords) {
        showProgressDialog(OrderSubmitActivity.this);
        // 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query = new PoiSearch.Query(keywords, "", Constants.DEFAULT_CITY);
        // 设置每页最多返回多少条poiitem
        query.setPageSize(1);
        // 设置查第一页
        query.setPageNum(1);
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        dismissProgressDialog();// 隐藏对话框
        if (rCode == 1000) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    PoiItem poiItem = poiItems.get(0);
                    LatLonPoint latLonPoint = poiItem.getLatLonPoint();
                    String addressName = poiItem.getCityName();

                    if (vehicleUseAddDialog != null) {

                        vehicleUseAddDialog.setVehicleUseAddressData(latLonPoint, addressName, mVehicleUseOprateFlag);
                    }

                }
            }
        }

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    public void showVehicleUseAddDialog() {
        if (vehicleUseAddDialog == null) {
            vehicleUseAddDialog = new VehicleUseAddDialog();
            vehicleUseAddDialog.setVehicleUseSelectListener(new VehicleUseAddDialog.VehicleUseSelectListener() {
                @Override
                public void setVehicleUseSelectFlagCallBack(int vehicleUseOprateFlag) {
                    mVehicleUseOprateFlag = vehicleUseOprateFlag;
                    if (vehicleUseOprateFlag == 0) {
                        Intent mIntent = new Intent();
                        mIntent.setClass(OrderSubmitActivity.this, VehicleAddressSelectionActivity.class);
                        mIntent.putExtra("vehicleUseOprateFlag", vehicleUseOprateFlag);
                        startActivityForResult(mIntent, REQUEST_CODE_MY_LOCATION_SELECT);
                    } else if (vehicleUseOprateFlag == 1) {

                        Intent mIntent = new Intent();
                        mIntent.setClass(OrderSubmitActivity.this, VehicleAddressSelectionActivity.class);
                        mIntent.putExtra("vehicleUseOprateFlag", vehicleUseOprateFlag);
                        startActivityForResult(mIntent, REQUEST_CODE_DESTINATION_SELECT);
                    } else if (vehicleUseOprateFlag == 2) {  //用车时间

                        if (vehicleUseTimeSelectDialog == null) {
                            vehicleUseTimeSelectDialog = new VehicleUseTimeSelectDialog();
                            vehicleUseTimeSelectDialog.setVehicleUseTimeSelectListener(new VehicleUseTimeSelectDialog.VehicleUseTimeSelectListener() {
                                @Override
                                public void setVehicleUseConfirmCallBack(String vehicleUseDateStr) {

                                    vehicleUseAddDialog.setVehicleUseTravelTime(vehicleUseDateStr);

                                }
                            });
                            vehicleUseTimeSelectDialog.show(getFragmentManager(), "");
                        } else {

                            vehicleUseTimeSelectDialog.show(getFragmentManager(), "");
                        }
                    } else if (vehicleUseOprateFlag == 3) {  //选择车辆类型

                        if (vehicleSelectionModelAddDialog == null) {
                            vehicleSelectionModelAddDialog = new VehicleSelectionModelAddDialog();
                            vehicleSelectionModelAddDialog.setVehicleUseSelectListener(new VehicleSelectionModelAddDialog.VehicleUseSelectModelListener() {
                                @Override
                                public void setVehicleUseSelectModelCallBack(CarTypeBean carType) {

                                    vehicleUseAddDialog.setVehicleModelSelect(carType);

                                }
                            });
                        }
                        vehicleSelectionModelAddDialog.show(getFragmentManager(), "");
                    }
                }

                @Override
                public void setEvaluateVehicleUsageFeeCallBack(String departureTime, String startLg, String startLt, String endLg, String endLt, int carType) {

                    getEvaluateVehicleUsageFee(departureTime, startLg, startLt, endLg, endLt, carType);

                }

                @Override
                public void setAddressPOISearchCallBack(String addressName, int vehicleUseOprateFlag) {
                    mVehicleUseOprateFlag = vehicleUseOprateFlag;
                    if (!TextUtils.isEmpty(addressName)) {
                        doSearchQuery(addressName);
                    }
                }

                @Override
                public void setVehicleUseConfirmCallBack(CarsBean carsBean) {
                    mLlytVehicleUse.setVisibility(View.VISIBLE);
                    carsBeanList.add(carsBean);
                    vehicleUseAdapter.setVehicleUseList(carsBeanList);
                    vehicleUseAdapter.notifyDataSetChanged();

                }

                @Override
                public void setVehicleUseParamEmptyCallBack(String message) {

                    showToast(OrderSubmitActivity.this, message);
                }

                @Override
                public void setVehicleUseCancelCallBack() {   //取消选择用车

                    mCkbIsNeedUseCar.setChecked(false);
                }
            });
        }
        vehicleUseAddDialog.show(getFragmentManager(), "");
    }
}
