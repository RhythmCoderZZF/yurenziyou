package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.contract.OrderSubmitContract;
import com.nbhysj.coupon.dialog.CouponDescriptionDialog;
import com.nbhysj.coupon.dialog.CouponSelectDialog;
import com.nbhysj.coupon.dialog.MchCouponReceiveDialog;
import com.nbhysj.coupon.dialog.OrderSubmitDatePickerDialog;
import com.nbhysj.coupon.dialog.PurchaseInstructionsBrowseDialog;
import com.nbhysj.coupon.dialog.PurchaseInstructionsDialog;
import com.nbhysj.coupon.dialog.VehicleSelectionModelAddDialog;
import com.nbhysj.coupon.dialog.VehicleServiceAgreementDialog;
import com.nbhysj.coupon.dialog.VehicleServiceAgreementTipsDialog;
import com.nbhysj.coupon.dialog.VehicleUseAddDialog;
import com.nbhysj.coupon.dialog.VehicleUseTimeSelectDialog;
import com.nbhysj.coupon.model.OrderSubmitModel;
import com.nbhysj.coupon.model.request.CarEstimatePriceBean;
import com.nbhysj.coupon.model.request.CarsBean;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.request.EstimatedPriceRequest;
import com.nbhysj.coupon.model.request.GoodsBeanRequest;
import com.nbhysj.coupon.model.request.QueryByTicketRequest;
import com.nbhysj.coupon.model.request.TicketOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;
import com.nbhysj.coupon.model.request.UseCouponTicketRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CarTypeBean;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.model.response.EstimatedPriceResponse;
import com.nbhysj.coupon.model.response.GoodsPriceDatesResponse;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.QueryByTicketResponse;
import com.nbhysj.coupon.model.response.TravellerBean;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;
import com.nbhysj.coupon.model.response.UseCouponTicketResponse;
import com.nbhysj.coupon.presenter.OrderSubmitPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.Tools;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

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
    //增加游客
    @BindView(R.id.llyt_add_tourists)
    LinearLayout mLlytAddTourists;
    //增加游客取消
    @BindView(R.id.tv_add_tourists_cancel)
    TextView mTvAddTouristsCancel;
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

    //旅客编辑取消
    @BindView(R.id.tv_tourist_edit_cancel)
    TextView getmTvTouristEditCancel;

    //旅客名字编辑
    @BindView(R.id.edt_tourist_username)
    EditText mEdtTouristName;

    //旅客手机号编辑
    @BindView(R.id.edt_tourist_mobile)
    EditText mEdtTouristMobile;

    //旅客增加(名字填写)
    @BindView(R.id.edt_tourist_add_username)
    EditText mEdtTouristAddName;

    //旅客增加(手机号填写)
    @BindView(R.id.edt_tourist_add_mobile)
    EditText mEdtTouristAddMobile;

    //游客信息
    @BindView(R.id.rlyt_tourist_info_item)
    RelativeLayout mRlytTouristInfoItem;

    //游客删除
    @BindView(R.id.tv_delete_frequently_used_tourists)
    TextView mTvTouristDelete;
    //开启用车
    @BindView(R.id.llyt_open_car_status)
    LinearLayout mLlytOpenCarStatus;

    //优惠券
    @BindView(R.id.tv_coupon)
    TextView mTvCoupon;

    //默认价格
    @BindView(R.id.tv_default_ticket_price)
    TextView mTvDefaultTicketPrice;

    //已减价格
    @BindView(R.id.tv_already_reduced_price)
    TextView mTvAlreadyReducedPrice;

    @BindView(R.id.rlyt_discount)
    RelativeLayout mRlytDiscount;

    //立减
    @BindView(R.id.tv_order_discount_price)
    TextView mTvOrderDiscountPrice;

    @BindView(R.id.img_coupon_right_arrow)
    ImageView mImgCouponRightArrow;

    //用车协议
    @BindView(R.id.tv_vehicle_service_agreement)
    TextView mTvVehicleSericeAgreement;

    //购买数量
    private int mPurchaseNum = 1;

    //使用日期价格
    private double datePrice;

    //增加门票价格
    private double increaseTicketPrice;

    //优惠价格
    private double discountPrice;

    //商品id
    private int goodsId;

    //商户类型
    private String mchType;

    private List<TravellerBean> travellersList;

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

    //日历价格
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
    private List<GoodsBeanRequest> goodsList;

    private List<CarsBean> carsBeanList;

    private List<CarEstimatePriceBean> carEstimatePriceList;

    //旅客id
    private int userTravelerId;

    //商品价格日历选择
    private String goodsPriceDateSelect;

    private int mPosition;

    //游客名字
    private String realname;

    //游客号码
    private String mobile;

    //页数
    private int mPage = 1;

    //每页条数
    private int mPageSize = 10000;

    List<GoodsPriceDatesResponse> goodsPriceDatesList;

    //购买须知h5
    private String mchByNotesH5Url;

    //总价格
    private double mTotalPrice;

    private PurchaseInstructionsBrowseDialog mPurchaseInstructionsDialog;

    //用车服务协议弹框
    private VehicleServiceAgreementTipsDialog vehicleServiceAgreementTipsDialog;

    private CouponSelectDialog couponSelectDialog;

    //优惠券
    List<CouponsBean> couponList;

    //选择的优惠券id
    private List<Integer> chooseIds;

    //新选择的优惠券id
    private int newUseId;

    //优惠券标题
    private String mCouponTitle;

    //0:不用车 1:用车
    private int useCarStatus = 0;

    //用车服务协议
    private String vehicle;
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
        mchType = getIntent().getStringExtra("mchType");

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
        if (goodsList == null) {

            goodsList = new ArrayList<>();
        } else {
            goodsList.clear();
        }

        if (carsBeanList == null) {
            carsBeanList = new ArrayList<>();
        } else {
            carsBeanList.clear();
        }

        if (goodsPriceDatesList == null) {

            goodsPriceDatesList = new ArrayList<>();
        } else {
            goodsPriceDatesList.clear();
        }

        if (carEstimatePriceList == null) {

            carEstimatePriceList = new ArrayList<>();
        } else {
            carEstimatePriceList.clear();
        }

        if (couponList == null) {

            couponList = new ArrayList<>();
        } else {
            couponList.clear();
        }

        if (chooseIds == null) {

            chooseIds = new ArrayList<>();
        } else {
            chooseIds.clear();
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
                        public void setTickeDatePickerSelectListener(GoodsPriceDatesResponse goodsPriceDatesResponse) {

                            for (int i = 0; i < orderSubmitDateList.size(); i++) {
                                orderSubmitDateList.get(i).setSelectDatePrice(false);
                                String date = orderSubmitDateList.get(i).getDate();
                                String goodsPriceDatesSelect = goodsPriceDatesResponse.getDate();
                                if (date.equals(goodsPriceDatesSelect)) {
                                    orderSubmitDateList.get(i).setSelectDatePrice(true);
                                } else {

                                    orderSubmitDateList.remove(2);
                                    goodsPriceDatesResponse.setIsCanBooking(1);
                                    orderSubmitDateList.add(goodsPriceDatesResponse);
                                }
                            }

                            orderUseDateSelectAdapter.setOrderUseDateSelectList(orderSubmitDateList);
                            orderUseDateSelectAdapter.notifyDataSetChanged();
                            //   showToast(OrderSubmitActivity.this,ticketDateSelect);
                        }
                    }, goodsPriceDatesList);
                    orderSubmitDatePickerDialog.show(getFragmentManager(), "数据日期选择");
                } else {
                    orderSubmitDatePickerDialog.show(getFragmentManager(), "数据日期选择");
                    orderSubmitDatePickerDialog.setDatePickerClear();
                }

            }

            @Override
            public void datePriceSelectCallBack(int position) {

                GoodsPriceDatesResponse goodsPriceDatesResponse = goodsPriceDatesList.get(position);
                datePrice = goodsPriceDatesResponse.getPrice();
                goodsPriceDateSelect = goodsPriceDatesResponse.getDate();
                discountPrice = 0;
                getPriceSettlement();
                queryByTicket();
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

                    mLlytAddAndUpdateTourists.setVisibility(View.VISIBLE);
                    mLlytAddAndUpdateTourists.startAnimation(inAnimation);
                    mRlytNewTouristsBgHalf.setVisibility(View.VISIBLE);
                    if (addTouristInformationAdapter != null) {
                        addTouristInformationAdapter.notifyDataSetChanged();
                    }
                } else {

                    TravellerBean travellersEntity = travellersList.get(position);
                    realname = travellersEntity.getRealname();
                    mobile = travellersEntity.getMobile();

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
            public void setPurchaseNumListener(int position, double purchasePrice, int mAddTicketPurchaseNum) { //mAddTicketPurchaseNum 增加门票数量
                increaseTicketPrice = purchasePrice;
                //
                //  mTvMarketTicketPrice.setText(Tools.getTwoDecimalPoint(totalPrice));
               /* if (goodsPriceTicketAddList.size() > 0) {
                    goodsPriceTicketAddList.get(position).setTicketPurchaseNum(mAddTicketPurchaseNum);
                }*/
                double mIncreaseTicketTotalPrice = 0;
                for (int i = 0; i < goodsPriceList.size(); i++) {

                    OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(i);
                    int mTicketPurchaseNum = goodsPriceEntity.getTicketPurchaseNum();
                    double mDefaultPrice = goodsPriceEntity.getDefaultPrice();

                    double totalPrice = mTicketPurchaseNum * mDefaultPrice;

                    mIncreaseTicketTotalPrice = mIncreaseTicketTotalPrice + totalPrice;
                }

                getPriceSettlement();
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

                mPosition = position;
                TravellerBean travellersEntity = travellersList.get(position);
                String touristRealname = travellersEntity.getRealname();
                String touristMobile = travellersEntity.getMobile();
                userTravelerId = travellersEntity.getId();
                mEdtTouristName.setText(touristRealname);
                mEdtTouristMobile.setText(touristMobile);
                mLlytEditTourists.setVisibility(View.VISIBLE);
                mLlytEditTourists.startAnimation(inAnimation);

            }
        });
        addTouristInformationAdapter.setTouristInfoList(travellersList);
        mRvNewTourists.setAdapter(addTouristInformationAdapter);

        mLlytAddAndUpdateTourists.setEnabled(false);

        //车辆 路径信息
     /*   LinearLayoutManager travelByCarLayoutManager = new LinearLayoutManager(OrderSubmitActivity.this);
        travelByCarLayoutManager.setOrientation(travelByCarLayoutManager.VERTICAL);
        mRvTravelByCar.setLayoutManager(travelByCarLayoutManager);
        vehicleUseAdapter = new VehicleUseAdapter();
        vehicleUseAdapter.setVehicleUseList(carsBeanList);
        mRvTravelByCar.setAdapter(vehicleUseAdapter);*/


     //   mTvVehicleSericeAgreement.setText(Html.fromHtml("勾选即表示您同意" + "<u><font color='#4895F2'>" + "用车服务协议" + "</font></u>"));

    }

    @Override
    public void initData() {

        getOrderSubmitInit();

        mCkbIsNeedUseCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {

                if (isCheck) {

                   // useCarStatus = 1;
                    showVehicleUseAddDialog();
                } else {
                    mCkbIsNeedUseCar.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.icon_payment_method_check_false), null, null, null);
                   // useCarStatus = 0;
                    //*mLlytVehicleUse.setVisibility(View.GONE);
                 /*   carsBeanList.clear();
                    carEstimatePriceList.clear();
                    vehicleUseAdapter.setVehicleUseList(carsBeanList);
                    vehicleUseAdapter.notifyDataSetChanged();*/
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
            R.id.img_add_purchase_num, R.id.tv_add_vehicle_more, R.id.tv_tourist_edit_cancel, R.id.img_tourist_username_input_cancel,
            R.id.img_tourist_mobile_input_cancel,
            R.id.tv_tourist_edit_confirm, R.id.tv_tourists_info_edit, R.id.tv_delete_frequently_used_tourists, R.id.img_tourist_add_username_input_cancel, R.id.img_tourist_add_mobile_input_cancel, R.id.tv_add_tourists_confirm, R.id.tv_add_tourists_cancel, R.id.rlyt_coupon,R.id.tv_vehicle_service_agreement})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_order_submit:

                ticketOrderSubmit();
                break;
            case R.id.llyt_shadow_bg:

                mCkbOrderDetailLook.setChecked(false);
                break;
            case R.id.rlyt_new_tourists_bg_half:

                setTouristInfoDialog();

                break;
            case R.id.tv_added_frequently_used_tourists:
                mLlytAddTourists.setVisibility(View.VISIBLE);
                mLlytAddTourists.startAnimation(inAnimation);
                int visibility = mLlytAddTourists.getVisibility();
                if (visibility == 8) {
                    mRlytNewTouristsBgHalf.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.tv_add_tourists_cancel:

                setTouristInfoDialog();

                break;
            case R.id.rlyt_ticket:

                break;
            case R.id.tv_confirm:
                setTouristInfoDialog();
                break;
            case R.id.ibtn_back:

                OrderSubmitActivity.this.finish();

                break;
            case R.id.tv_purchase_notes:

                if (mPurchaseInstructionsDialog == null) {
                    mPurchaseInstructionsDialog = new PurchaseInstructionsBrowseDialog(getResources().getString(R.string.str_purchase_instructions), mchByNotesH5Url);
                }
                mPurchaseInstructionsDialog.show(getFragmentManager(), "组合商品购票须知");
                break;
            case R.id.img_reduce_purchase_num:

                if (mPurchaseNum > 1) {
                    mPurchaseNum--;
                    mTvPurchaseNum.setText(String.valueOf(mPurchaseNum));
                    if (goodsPriceList.size() > 0) {
                        OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(0);
                        goodsPriceEntity.setTicketPurchaseNum(mPurchaseNum);
                        getPriceSettlement();
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
                    getPriceSettlement();
                }

                break;
          /*  case R.id.tv_add_vehicle_more:

                showVehicleUseAddDialog();
                break;*/
            case R.id.tv_tourist_edit_cancel:

                mLlytEditTourists.setVisibility(View.GONE);

                break;
            case R.id.img_tourist_username_input_cancel: //旅客用户名输入取消

                mEdtTouristName.setText("");

                break;
            case R.id.img_tourist_mobile_input_cancel:   //旅客手机号输入取消

                mEdtTouristMobile.setText("");
                break;
            case R.id.tv_tourist_edit_confirm:

                updateTravellerInfo();

                break;
            case R.id.tv_tourists_info_edit:

                mLlytAddAndUpdateTourists.setVisibility(View.VISIBLE);
                mLlytAddAndUpdateTourists.startAnimation(inAnimation);
                mRlytNewTouristsBgHalf.setVisibility(View.VISIBLE);

                addTouristInformationAdapter.setTouristInfoList(travellersList);
                addTouristInformationAdapter.notifyDataSetChanged();

                break;
            case R.id.tv_delete_frequently_used_tourists:

                deleteTravellerInfo();

                break;
            case R.id.img_tourist_add_username_input_cancel:

                mEdtTouristAddName.setText("");

                break;
            case R.id.img_tourist_add_mobile_input_cancel:

                mEdtTouristAddMobile.setText("");

                break;
            case R.id.tv_add_tourists_confirm:

                addTraveller();

                break;
            case R.id.rlyt_coupon:

                if (couponSelectDialog == null) {
                    couponSelectDialog = new CouponSelectDialog(couponList, new CouponSelectDialog.CouponSelectListener() {
                        @Override
                        public void setCouponSelectCallback(int couponId, boolean isCouponSelect,String couponTitle) {

                            mCouponTitle = couponTitle;
                            if(!mCouponTitle.equals("不使用优惠"))
                            {
                                getAndUseCoupon(couponId, isCouponSelect);

                            } else {

                                mTvCoupon.setText(mCouponTitle);
                                if (couponSelectDialog != null)
                                {
                                    couponSelectDialog.dismiss();
                                }
                            }
                        }

                        @Override
                        public void setCouponListRefreshListener(RefreshLayout refreshLayout) {

                        }
                    });

                    couponSelectDialog.show(getFragmentManager(), "优惠券领取");

                } else {
                    couponSelectDialog.setCouponSelectList(couponList);
                    couponSelectDialog.show();
                }
                break;
         /*   case R.id.tv_vehicle_service_agreement:
                showVehicleUseAddDialog();
                break;*/
            default:
                break;
        }
    }

    @Override
    public void getUserTravellerListResult(BackResult<TravellerInfoResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    // mPosition++;
                    TravellerInfoResponse travellerInfoResponse = res.getData();
                    travellersList = travellerInfoResponse.getResult();
                    if (travellersList.size() > 0) {
                        TravellerBean travellerBean = travellersList.get(0);
                        userTravelerId = travellerBean.getId();
                        travellerBean.setTravellerSelect(true);
                        realname = travellerBean.getRealname();
                        mobile = travellerBean.getMobile();
                        mTvTouristName.setText(realname);
                        mTvTouristMobile.setText(mobile);

                        mRlytTouristInfoItem.setVisibility(View.VISIBLE);
                    }

                    addTouristInformationAdapter.setTouristInfoList(travellersList);
                    addTouristInformationAdapter.notifyDataSetChanged();

                    touristInformationAdapter.setTouristInfoList(travellersList);
                    touristInformationAdapter.notifyDataSetChanged();

                    mLlytAddTourists.setVisibility(View.GONE);

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
    public void getGroupMchOrderSubmitInitResult(BackResult<OrderSubmitInitResponse> res) {

    }

    @Override
    public void addUserTravellerResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    mEdtTouristAddName.setText("");
                    mEdtTouristAddMobile.setText("");
                    setTouristInfoDialog();
                    getTravellerList();

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
    public void updateUserTravellerResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    realname = mEdtTouristName.getText().toString();
                    mobile = mEdtTouristMobile.getText().toString();
                    TravellerBean travellersEntity = travellersList.get(mPosition);
                    userTravelerId = travellersEntity.getId();
                    travellersEntity.setRealname(mEdtTouristName.getText().toString());
                    travellersEntity.setMobile(mEdtTouristMobile.getText().toString());

                    addTouristInformationAdapter.setTouristInfoList(travellersList);
                    addTouristInformationAdapter.notifyDataSetChanged();

                    touristInformationAdapter.setTouristInfoList(travellersList);
                    touristInformationAdapter.notifyDataSetChanged();

                    mLlytEditTourists.setVisibility(View.GONE);
                    mTvTouristName.setText(realname);
                    mTvTouristMobile.setText(mobile);
                    mEdtTouristName.setText("");
                    mEdtTouristMobile.setText("");
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
    public void getRecreationDatePriceInitResult(BackResult<OrderSubmitInitResponse> res) {

    }

    @Override
    public void recreationOrderSubmitResult(BackResult<OrderSubmitResponse> res) {

    }

    @Override
    public void deleteUserTravellerResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                   // mLlytEditTourists.setVisibility(View.GONE);
                    if (travellersList != null) {
                        if (travellersList.size() > 0) {
                            TravellerBean travellersEntity = travellersList.get(mPosition);
                            travellersList.remove(travellersEntity);
                            if (travellersList != null && travellersList.size() > 0) {
                                TravellerBean travellers = travellersList.get(0);
                                userTravelerId = travellersEntity.getId();
                                travellers.setTravellerSelect(true);
                                addTouristInformationAdapter.setTouristInfoList(travellersList);
                                addTouristInformationAdapter.notifyDataSetChanged();

                                touristInformationAdapter.setTouristInfoList(travellersList);
                                touristInformationAdapter.notifyDataSetChanged();
                                realname = travellers.getRealname();
                                mobile = travellers.getMobile();

                                mLlytEditTourists.setVisibility(View.GONE);
                                mTvTouristName.setText(realname);
                                mTvTouristMobile.setText(mobile);
                                mEdtTouristName.setText("");
                                mEdtTouristMobile.setText("");

                                mTvTouristName.setText(realname);
                                mTvTouristMobile.setText(mobile);
                                mRlytTouristInfoItem.setVisibility(View.VISIBLE);
                            }else {
                                userTravelerId = 0;
                                mRlytTouristInfoItem.setVisibility(View.GONE);
                                mLlytEditTourists.setVisibility(View.GONE);
                                mLlytAddAndUpdateTourists.setVisibility(View.GONE);
                                mRlytNewTouristsBgHalf.setVisibility(View.GONE);
                                touristInformationAdapter.setTouristInfoList(travellersList);
                                touristInformationAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void useCouponTicketResult(BackResult<UseCouponTicketResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    UseCouponTicketResponse useCouponTicketResponse = res.getData();

                    discountPrice = useCouponTicketResponse.getDiscount();
                    chooseIds.clear();
                    chooseIds = useCouponTicketResponse.getChooseId();

                    if (couponSelectDialog != null)
                    {
                        couponSelectDialog.dismiss();
                    }

                    if(discountPrice > 0) {
                        getPriceSettlement();
                        mRlytDiscount.setVisibility(View.VISIBLE);
                        mTvOrderDiscountPrice.setText("¥" + Tools.getTwoDecimalPoint(discountPrice));
                        mTvCoupon.setText("-¥" + discountPrice);

                    } else {

                        getPriceSettlement();
                        mRlytDiscount.setVisibility(View.GONE);
                        mTvCoupon.setText("不使用优惠券");
                    }
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
    public void ticketOrderSubmitResult(BackResult<OrderSubmitResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    OrderSubmitResponse ticketOrderSubmitResponse = res.getData();
                    Intent intent = new Intent();
                    double price = ticketOrderSubmitResponse.getPrice();
                    String title = ticketOrderSubmitResponse.getTitle();
                    long payExprireTime = ticketOrderSubmitResponse.getPayExprireTime();
                    String orderNo = ticketOrderSubmitResponse.getOrderNo();
                    intent.setClass(OrderSubmitActivity.this, OrderPaymentActivity.class);
                    intent.putExtra("price", price);
                    intent.putExtra("title", title);
                    intent.putExtra("payExprireTime", payExprireTime);
                    intent.putExtra("orderNo", orderNo);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
                case Constants.USER_NOT_LOGIN_CODE:
                    onReLogin("");
                    break;
            default:
                showToast(OrderSubmitActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getOrderSubmitInitResult(BackResult<OrderSubmitInitResponse> res) {

        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    OrderSubmitInitResponse orderSubmitInitResponse = res.getData();
                    travellersList = orderSubmitInitResponse.getTravellers();
                    vehicle = orderSubmitInitResponse.getVehicle();
                    goodsPriceList = orderSubmitInitResponse.getGoodsPrice();

                    OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(0);
                    goodsPriceEntity.setTicketPurchaseNum(1);
                    String title = goodsPriceEntity.getTitle();
                    //商品价格日期
                    goodsPriceDatesList = goodsPriceEntity.getGoodsPriceDates();

                    if (goodsPriceDatesList != null && goodsPriceDatesList.size() > 0) {
                        goodsPriceDateSelect = goodsPriceDatesList.get(0).getDate();
                    }
                    for (int i = 0; i < orderSubmitDateList.size(); i++) {
                        GoodsPriceDatesResponse orderSubmitDateResponse = orderSubmitDateList.get(i);
                        String orderSubmitDate = orderSubmitDateResponse.getDate();
                        for (int j = 0; j < goodsPriceDatesList.size(); j++) {
                            GoodsPriceDatesResponse goodsPriceDatesResponse = goodsPriceDatesList.get(j);
                            String goodsPriceDate = goodsPriceDatesResponse.getDate();
                            int id = goodsPriceDatesResponse.getId();
                            double price = goodsPriceDatesResponse.getPrice();

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
                    if (goodsPriceList != null && goodsPriceList.size() > 0) {
                        for (int i = 0; i < goodsPriceList.size(); i++) {
                            if (i > 0) {
                                OrderSubmitInitResponse.GoodsPriceEntity goodsPriceBean = goodsPriceList.get(i);
                                goodsPriceTicketAddList.add(goodsPriceBean);
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
                        if (travellersList.size() > 0) {
                            TravellerBean travellersEntity = travellersList.get(0);
                            userTravelerId = travellersEntity.getId();
                            mRlytTouristInfoItem.setVisibility(View.VISIBLE);

                            travellersEntity.setTravellerSelect(true);
                            realname = travellersEntity.getRealname();
                            mobile = travellersEntity.getMobile();
                            mTvTouristName.setText(realname);
                            mTvTouristMobile.setText(mobile);
                        } else {
                            mRlytTouristInfoItem.setVisibility(View.GONE);
                        }

                        touristInformationAdapter.setTouristInfoList(travellersList);
                        touristInformationAdapter.notifyDataSetChanged();

                        //新增游客编辑
                        addTouristInformationAdapter.setTouristInfoList(travellersList);
                        touristInformationAdapter.notifyDataSetChanged();
                    } else {

                        mRlytTouristInfoItem.setVisibility(View.GONE);
                    }
                    mTvTicketTitle.setText(title);

                    mchByNotesH5Url = orderSubmitInitResponse.getGoodsBuyNotes();

                    //用车是否开启状态
                    int openCarStatus = orderSubmitInitResponse.getOpenCarStatus();

                    if (openCarStatus == 0) {
                        mLlytOpenCarStatus.setVisibility(View.GONE);
                    } else if (openCarStatus == 1) {
                        mLlytOpenCarStatus.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                    dismissProgressDialog();
                    e.printStackTrace();
                }
                break;
            default:
                dismissProgressDialog();
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
                  //  cityCode = estimatedPrice.getCityCode();
                    //lineType = estimatedPrice.getLineType();
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
    public void queryByTicketResult(BackResult<QueryByTicketResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    QueryByTicketResponse queryByTicketResponse = res.getData();
                    couponList = queryByTicketResponse.getCoupons();
                    int couponNum = couponList.size();
                    if (couponList != null && couponNum == 1) {
                        CouponsBean couponsBean = couponList.get(0);
                        String title = couponsBean.getTitle();
                        mTvCoupon.setText(title);
                        mTvCoupon.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                        mImgCouponRightArrow.setVisibility(View.VISIBLE);
                    } else if (couponNum > 1) {
                        mTvCoupon.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                        mTvCoupon.setText(couponNum + "张可用");
                        mImgCouponRightArrow.setVisibility(View.VISIBLE);
                    } else {

                        mImgCouponRightArrow.setVisibility(View.GONE);
                        mTvCoupon.setText("无优惠券");
                        mTvCoupon.setTextColor(mContext.getResources().getColor(R.color.color_text_gray17));
                    }

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
    public void onPointerCaptureChanged(boolean hasCapture) {

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
            String mchScenic = MchTypeEnum.MCH_SCENIC.getValue();
            String mchRecreation = MchTypeEnum.MCH_RECREATION.getValue();

            if(!TextUtils.isEmpty(mchType)) {
                if (mchType.equals(mchScenic)) {
                    mPresenter.getOrderSubmitInit(goodsId);
                } else if (mchType.equals(mchRecreation)) {
                    mPresenter.getRecreationDatePriceInit(goodsId);
                }
            }
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
    public void groupMchOrderSubmitResult(BackResult<OrderSubmitResponse> res) {

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
    public void ticketOrderSubmit() {

        if (validateInternet()) {

            goodsList.clear();
            TicketOrderSubmitRequest ticketOrderSubmitRequest = new TicketOrderSubmitRequest();

            if (userTravelerId == 0) {

                showToast(OrderSubmitActivity.this, "请填写顾客信息");
                return;
            }

            ticketOrderSubmitRequest.setUserTravelerId(userTravelerId);
            ticketOrderSubmitRequest.setCouponIds(chooseIds);

            OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(0);
            int goodsId = goodsPriceEntity.getGoodsId();
            GoodsBeanRequest goodsBean = new GoodsBeanRequest();
            goodsBean.setGoodsId(goodsId);
            goodsBean.setNum(mPurchaseNum);
            goodsBean.setPriceDate(goodsPriceDateSelect);
            goodsList.add(goodsBean);

            for (int i = 0; i < goodsPriceTicketAddList.size(); i++) {

                OrderSubmitInitResponse.GoodsPriceEntity goodsPrice = goodsPriceTicketAddList.get(i);
                int tcketPurchaseNum = goodsPrice.getTicketPurchaseNum();
                int goodId = goodsPrice.getGoodsId();
                if (tcketPurchaseNum > 0) {
                    GoodsBeanRequest goodsAddTicket = new GoodsBeanRequest();
                    goodsAddTicket.setGoodsId(goodId);
                    goodsAddTicket.setNum(tcketPurchaseNum);    //1.增加门票模块 票数字段 采用ticketPurchaseNum 2.外层价格日历选择 票数字段 采用mPurchaseNum 默认为1
                    goodsAddTicket.setPriceDate(goodsPriceDateSelect);
                    goodsList.add(goodsAddTicket);
                }
            }

            ticketOrderSubmitRequest.setGoods(goodsList);
            ticketOrderSubmitRequest.setCars(carsBeanList);
            //是否用车 1:用 || 0:不用
           /* if (mCkbIsNeedUseCar.isChecked()) {
                ticketOrderSubmitRequest.setCarStatus(1);
            } else {
                ticketOrderSubmitRequest.setCarStatus(0);
            }*/
            ticketOrderSubmitRequest.setUseCarStatus(useCarStatus);
            showProgressDialog(OrderSubmitActivity.this);
            mDialog.setTitle("正在提交订单...");
            String mchScenic = MchTypeEnum.MCH_SCENIC.getValue();
            String mchRecreation = MchTypeEnum.MCH_RECREATION.getValue();
            if (mchType.equals(mchScenic)) {
                mPresenter.ticketOrderSubmit(ticketOrderSubmitRequest);
            } else if (mchType.equals(mchRecreation)) {
                mPresenter.recreationOrderSubmit(ticketOrderSubmitRequest);
            }
        }
    }


    //修改游客信息
    public void updateTravellerInfo() {

        if (validateInternet()) {

            String touristName = mEdtTouristName.getText().toString().trim();
            String touristMobile = mEdtTouristMobile.getText().toString().trim();

            //请填写中文名
            if (TextUtils.isEmpty(touristName)) {

                showToast(OrderSubmitActivity.this, getResources().getString(R.string.str_input_chinese_name));
                return;
            }
            //请填写手机号
            if (TextUtils.isEmpty(touristMobile)) {

                showToast(OrderSubmitActivity.this, getResources().getString(R.string.str_input_phone));
                return;
            }

            TravellerInfoRequest updateTravellerRequest = new TravellerInfoRequest();
            updateTravellerRequest.setUserId(getSharedPreferencesUserId());
            updateTravellerRequest.setRealname(touristName);
            updateTravellerRequest.setMobile(touristMobile);
            updateTravellerRequest.setId(userTravelerId);
            showProgressDialog(OrderSubmitActivity.this);
            mDialog.setTitle(getResources().getString(R.string.str_saving));

            mPresenter.updateUserTraveller(updateTravellerRequest);
        }
    }

    //增加游客
    public void addTraveller() {

        if (validateInternet()) {

            String touristName = mEdtTouristAddName.getText().toString().trim();
            String touristMobile = mEdtTouristAddMobile.getText().toString().trim();

            //请填写中文名
            if (TextUtils.isEmpty(touristName)) {

                showToast(OrderSubmitActivity.this, getResources().getString(R.string.str_input_chinese_name));
                return;
            }
            //请填写手机号
            if (TextUtils.isEmpty(touristMobile)) {

                showToast(OrderSubmitActivity.this, getResources().getString(R.string.str_input_phone));
                return;
            }

            TravellerInfoRequest addTravellerRequest = new TravellerInfoRequest();
            addTravellerRequest.setUserId(getSharedPreferencesUserId());
            addTravellerRequest.setRealname(touristName);
            addTravellerRequest.setMobile(touristMobile);
            addTravellerRequest.setId(userTravelerId);
            showProgressDialog(OrderSubmitActivity.this);
            mDialog.setTitle(getResources().getString(R.string.str_saving));

            mPresenter.addUserTraveller(addTravellerRequest);
        }
    }

    //删除游客信息
    public void deleteTravellerInfo() {

        if (validateInternet()) {
            showProgressDialog(OrderSubmitActivity.this);
            mDialog.setTitle("正在删除...");
            DeleteTravellerInfoRequest deleteTravellerInfoRequest = new DeleteTravellerInfoRequest();
            deleteTravellerInfoRequest.setId(userTravelerId);
            mPresenter.deleteUserTraveller(deleteTravellerInfoRequest);
        }
    }

    public void getTravellerList() {

        showProgressDialog(OrderSubmitActivity.this);
        mPresenter.getUserTravellerList(getSharedPreferencesUserId(), mPage, mPageSize);
    }

    public void queryByTicket() {
        //showProgressDialog(OrderSubmitActivity.this);
        QueryByTicketRequest queryByTicketRequest = new QueryByTicketRequest();
        getGoodsQueryByTicket();
        queryByTicketRequest.setGoods(goodsList);
        queryByTicketRequest.setCars(carEstimatePriceList);
        mPresenter.queryByTicket(queryByTicketRequest);
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
        /*if (mVehicleServiceAgreementDialog == null)
        {
            mVehicleServiceAgreementDialog = new VehicleServiceAgreementDialog(new VehicleServiceAgreementDialog.PurchaseInstructionsListener() {
                @Override
                public void setPurchaseInstructionsCallback(MchGoodsBean mchGoodsBean) {
                    String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
                    if (!TextUtils.isEmpty(token)) {



                    } else {

                        onReLogin("");
                    }
                }
            },"http://wwww.baidu.com");
        }
        mVehicleServiceAgreementDialog.show(getFragmentManager(), "用车服务协议");*/

        if (vehicleServiceAgreementTipsDialog == null)
        {
            vehicleServiceAgreementTipsDialog = new VehicleServiceAgreementTipsDialog(OrderSubmitActivity.this, new VehicleServiceAgreementTipsDialog.VehicleServiceAgreementListener() {
                @Override
                public void setVehicleServiceAgreementCallback() {
                    useCarStatus = 1;
                    vehicleServiceAgreementTipsDialog.dialogDismiss();

                    mCkbIsNeedUseCar.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.icon_payment_method_check_true), null, null, null);
                   // mCkbIsNeedUseCar.setChecked(true);
                    vehicleServiceAgreementTipsDialog.setContent(vehicle);
                }

                @Override
                public void setVehicleAgreementUnreadCompleteCallback() {

                    showToast(OrderSubmitActivity.this,"请浏览完用车服务协议,再确认已阅读");
                }

                @Override
                public void setDialogDismissCallback() {
                    useCarStatus = 0;
                    mCkbIsNeedUseCar.setChecked(false);

                }
            }).builder().setContent(vehicle);
        }
        vehicleServiceAgreementTipsDialog.show();
    }
   /* public void showVehicleUseAddDialog() {
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
                    carsBean.setCityCode(cityCode);
                    carsBean.setLineType(lineType);
                    carsBeanList.add(carsBean);
                    double price = carsBean.getPrice();
                    CarEstimatePriceBean carEstimatePriceBean = new CarEstimatePriceBean();
                    carEstimatePriceBean.setEstimatePrice(price);
                    carEstimatePriceList.add(carEstimatePriceBean);
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
    }*/

    public void setTouristInfoDialog() {
        int addTouristsVisibility1 = mLlytAddTourists.getVisibility();
        int editTouristsVisibility1 = mLlytEditTourists.getVisibility();
        if (addTouristsVisibility1 == 8 && editTouristsVisibility1 == 8) {
            mRlytNewTouristsBgHalf.setVisibility(View.GONE);
            mLlytAddAndUpdateTourists.setVisibility(View.GONE);
            mLlytAddAndUpdateTourists.startAnimation(outAnimation);

        } else {

            mLlytAddTourists.setVisibility(View.GONE);
            mLlytAddTourists.startAnimation(outAnimation);

        }
        if (editTouristsVisibility1 == 8 && addTouristsVisibility1 == 8) {

            mRlytNewTouristsBgHalf.setVisibility(View.GONE);
            mLlytAddAndUpdateTourists.setVisibility(View.GONE);
            mLlytAddAndUpdateTourists.startAnimation(outAnimation);

        } else {

            mLlytEditTourists.setVisibility(View.GONE);
            mLlytEditTourists.startAnimation(outAnimation);

        }
    }

    public void getGoodsQueryByTicket() {
        goodsList.clear();
        OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(0);
        int goodsId = goodsPriceEntity.getGoodsId();
        String goodsType = goodsPriceEntity.getGoodsType();
        GoodsBeanRequest goodsBean = new GoodsBeanRequest();
        goodsBean.setGoodsId(goodsId);
        goodsBean.setNum(mPurchaseNum);
        goodsBean.setGoodsType(goodsType);
        goodsBean.setDate(goodsPriceDateSelect);
        goodsList.add(goodsBean);

        for (int i = 0; i < goodsPriceTicketAddList.size(); i++) {

            OrderSubmitInitResponse.GoodsPriceEntity goodsPrice = goodsPriceTicketAddList.get(i);
            int tcketPurchaseNum = goodsPrice.getTicketPurchaseNum();
            int goodId = goodsPrice.getGoodsId();
            if (tcketPurchaseNum > 0) {
                GoodsBeanRequest goodsAddTicket = new GoodsBeanRequest();
                goodsAddTicket.setGoodsId(goodId);
                goodsAddTicket.setNum(tcketPurchaseNum);    //1.增加门票模块 票数字段 采用ticketPurchaseNum 2.外层价格日历选择 票数字段 采用mPurchaseNum 默认为1
                goodsAddTicket.setDate(goodsPriceDateSelect);
                goodsList.add(goodsAddTicket);
            }
        }
    }

    public void getAndUseCoupon(int couponId, boolean isCouponSelect) {
        goodsList.clear();
        OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(0);
        int goodsId = goodsPriceEntity.getGoodsId();
        String goodsType = goodsPriceEntity.getGoodsType();
        GoodsBeanRequest goodsBean = new GoodsBeanRequest();
        goodsBean.setGoodsId(goodsId);
        goodsBean.setNum(mPurchaseNum);
        goodsBean.setGoodsType(goodsType);
        goodsBean.setDate(goodsPriceDateSelect);
        goodsList.add(goodsBean);

        for (int i = 0; i < goodsPriceTicketAddList.size(); i++) {

            OrderSubmitInitResponse.GoodsPriceEntity goodsPrice = goodsPriceTicketAddList.get(i);
            int tcketPurchaseNum = goodsPrice.getTicketPurchaseNum();
            int goodId = goodsPrice.getGoodsId();
            if (tcketPurchaseNum > 0) {
                GoodsBeanRequest goodsAddTicket = new GoodsBeanRequest();
                goodsAddTicket.setGoodsId(goodId);
                goodsAddTicket.setNum(tcketPurchaseNum);    //1.增加门票模块 票数字段 采用ticketPurchaseNum 2.外层价格日历选择 票数字段 采用mPurchaseNum 默认为1
                goodsAddTicket.setDate(goodsPriceDateSelect);
                goodsList.add(goodsAddTicket);
            }
        }

       if (isCouponSelect) {

            /*for (int i = 0;i < chooseIds.size();i++){
                int selectCouponId = chooseIds.get(i);
                if(selectCouponId == couponId)
                {
                    chooseIds.add(couponId);
                    newUseId = couponId;
                }
            }*/
            boolean isCountainsCouponId = chooseIds.contains(couponId);
            if (!isCountainsCouponId)
            {
               // chooseIds.add(couponId);
                newUseId = couponId;
            }
        } else {
            boolean isCountainsCouponId = chooseIds.contains(couponId);
            if (isCountainsCouponId) {

                for (int i = 0;i < chooseIds.size();i++){
                    int selectCouponId = chooseIds.get(i);
                    if(selectCouponId == couponId)
                    {
                        chooseIds.remove(i);
                        newUseId = 0;
                    }
                }
            }
        }
        useCouponTicket();
    }

    //使用优惠券校验
    public void useCouponTicket(){

        if(validateInternet()){
            showProgressDialog(OrderSubmitActivity.this);
            UseCouponTicketRequest useCouponTicketRequest = new UseCouponTicketRequest();
            useCouponTicketRequest.setCars(carEstimatePriceList);
            useCouponTicketRequest.setGoods(goodsList);
            useCouponTicketRequest.setChooseIds(chooseIds);
            useCouponTicketRequest.setNewUseId(newUseId);
            mPresenter.useCouponTicketRequest(useCouponTicketRequest);
        }
    }

    //价格结算
    public void getPriceSettlement()
    {
        mTotalPrice = increaseTicketPrice + datePrice * mPurchaseNum - discountPrice;
        if(mTotalPrice < 0)
        {
            mTotalPrice = 0.00;
        }
        mTvMarketTicketPrice.setText(Tools.getTwoDecimalPoint(mTotalPrice));
    }
}
