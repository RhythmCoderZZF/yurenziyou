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
import android.widget.EditText;
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
import com.nbhysj.coupon.adapter.GroupMchOrderUseDateSelectAdapter;
import com.nbhysj.coupon.adapter.IncreaseTicketAdapter;
import com.nbhysj.coupon.adapter.OrderDetailTicketInfoAdapter;
import com.nbhysj.coupon.adapter.OrderUseDateSelectAdapter;
import com.nbhysj.coupon.adapter.TouristInformationAdapter;
import com.nbhysj.coupon.adapter.VehicleUseAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.GoodsTypeEnum;
import com.nbhysj.coupon.contract.OrderSubmitContract;
import com.nbhysj.coupon.dialog.OrderSubmitDatePickerDialog;
import com.nbhysj.coupon.dialog.PurchaseInstructionsBrowseDialog;
import com.nbhysj.coupon.dialog.PurchaseInstructionsDialog;
import com.nbhysj.coupon.dialog.VehicleSelectionModelAddDialog;
import com.nbhysj.coupon.dialog.VehicleUseAddDialog;
import com.nbhysj.coupon.dialog.VehicleUseTimeSelectDialog;
import com.nbhysj.coupon.model.OrderSubmitModel;
import com.nbhysj.coupon.model.request.CarsBean;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.request.GoodsBean;
import com.nbhysj.coupon.model.request.GroupMchOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TicketOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CarTypeBean;
import com.nbhysj.coupon.model.response.EstimatedPriceResponse;
import com.nbhysj.coupon.model.response.GoodsPriceDatesResponse;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.TravellerBean;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;
import com.nbhysj.coupon.presenter.OrderSubmitPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.MyRecycleView;

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
public class GroupMchOrderSubmitActivity extends BaseActivity<OrderSubmitPresenter, OrderSubmitModel> implements OrderSubmitContract.View, PoiSearch.OnPoiSearchListener {

    //订单使用日期选择
    @BindView(R.id.rv_use_ticket_date)
    MyRecycleView mRvUseTicketDateSelect;
    //游客信息列表
    @BindView(R.id.rv_tourist_information)
    RecyclerView mRvTouristInformation;
    //游客名字
    @BindView(R.id.tv_tourist_name)
    TextView mTvTouristName;
    //游客电话
    @BindView(R.id.tv_tourist_mobile)
    TextView mTvTouristMobile;
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
    //购买数量
    @BindView(R.id.tv_purchase_num)
    TextView mTvPurchaseNum;
    //票标题
    @BindView(R.id.tv_ticket_title)
    TextView mTvTicketTitle;

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

    //默认价格
    @BindView(R.id.tv_default_price)
    TextView mTvDefaultPrice;
    //用车状态
    @BindView(R.id.rlyt_open_car_status)
    RelativeLayout mRlytOpenCarStatus;

    //总金额
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;

    //购买数量
    private int mPurchaseNum = 1;

    //使用日期价格
    private double datePrice;

    //组合id
    private int groupId;

    private List<TravellerBean> travellersList;

    //游客信息适配器
    private TouristInformationAdapter touristInformationAdapter;

    //更多日期选择弹框
    private OrderSubmitDatePickerDialog orderSubmitDatePickerDialog;

    //时间价格订单适配器
    private GroupMchOrderUseDateSelectAdapter orderUseDateSelectAdapter;

    //价格明细
    OrderDetailTicketInfoAdapter orderDetailTicketInfoAdapter;

    //日历价格
    private List<GoodsPriceDatesResponse> orderSubmitDateList;

    //日历价格
    private List<OrderSubmitInitResponse.GoodsPriceEntity> goodsPriceList;

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

    private PurchaseInstructionsBrowseDialog mPurchaseInstructionsDialog;

    Map<String, Object> map = new HashMap<>();

    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索

    private PoiResult poiResult; // poi返回的结果

    //商品列表
    private List<GoodsBean> goodsList;

    private List<CarsBean> carsBeanList;

    //旅客id
    private int userTravelerId;

    private int mPosition;

    //游客名字
    private String realname;

    //游客号码
    private String mobile;

    //页数
    private int mPage = 1;

    //每页条数
    private int mPageSize = 10000;

    //是否开启用车模块
    private int openCarStatus;

    //组合商品购买须知
    private String goodsBuyNotesH5Url;

    //城市ID
    private int cityCode;

    private int lineType;

    //组合单价
    private double payFee;

    @Override
    public int getLayoutId() {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_group_mch_submit_order;
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

        groupId = getIntent().getIntExtra("groupId", 0);

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

                    if (goodsPriceDetailList != null)
                    {
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(GroupMchOrderSubmitActivity.this);
        layoutManager.setOrientation(layoutManager.VERTICAL);
        mRvUseTicketDateSelect.setLayoutManager(layoutManager);
        orderUseDateSelectAdapter = new GroupMchOrderUseDateSelectAdapter(GroupMchOrderSubmitActivity.this, new GroupMchOrderUseDateSelectAdapter.GroupMchOrderUseDateSelectListener() {
            @Override
            public void setMoreDatesCallBack(int position) {
                OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(position);
                orderSubmitDateList = goodsPriceEntity.getGoodsPriceDates();
                if (orderSubmitDatePickerDialog == null) {
                    orderSubmitDatePickerDialog = new OrderSubmitDatePickerDialog();

                }
                orderSubmitDatePickerDialog.setDataStatisticsDatePickerDialog(new OrderSubmitDatePickerDialog.OrderSubmitDatePickerListener() {
                    @Override
                    public void setDataStatisticsDatePickerListener(String startDate, String endDate) {

                    }

                    @Override
                    public void setTickeDatePickerSelectListener(GoodsPriceDatesResponse goodsPriceDatesResponse) {
                        OrderSubmitInitResponse.GoodsPriceEntity goodsPrice = goodsPriceList.get(position);
                        List<GoodsPriceDatesResponse> goodsPriceDatesList = goodsPrice.getGoodsPriceDates();
                        for (int i = 0; i < goodsPriceDatesList.size(); i++) {
                            goodsPriceDatesList.get(i).setSelectDatePrice(false);
                            String date = goodsPriceDatesList.get(i).getDate();
                            String ticketDateSelect = goodsPriceDatesResponse.getDate();
                            if (date.equals(ticketDateSelect)) {
                                goodsPriceDatesList.get(i).setSelectDatePrice(true);
                            }
                        }

                        orderUseDateSelectAdapter.setOrderUseDateSelectList(goodsPriceList);
                        orderUseDateSelectAdapter.notifyDataSetChanged();
                    }

                }, orderSubmitDateList);
                orderSubmitDatePickerDialog.show(getFragmentManager(), "数据日期选择");
                orderSubmitDatePickerDialog.setDatePickerClear();
            }

            @Override
            public void setDatePriceSelectCallBack(int groupItemPosition, int childItemPosition) {
                OrderSubmitInitResponse.GoodsPriceEntity goodsPriceDatesResponse = goodsPriceList.get(groupItemPosition);
                orderSubmitDateList = goodsPriceDatesResponse.getGoodsPriceDates();
                GoodsPriceDatesResponse goodsPriceDateEntity = orderSubmitDateList.get(childItemPosition);
                //datePrice = goodsPriceDateEntity.getPrice();
              //  goodsPriceDateSelect = goodsPriceDateEntity.getDate();

                //  mTvMarketTicketPrice.setText(String.valueOf(totalPrice));
            }
        });

        orderUseDateSelectAdapter.setOrderUseDateSelectList(goodsPriceList);
        mRvUseTicketDateSelect.setAdapter(orderUseDateSelectAdapter);

        /**
         * 游客信息列表
         */
        LinearLayoutManager touristInfoLayoutManager = new LinearLayoutManager(GroupMchOrderSubmitActivity.this);
        touristInfoLayoutManager.setOrientation(touristInfoLayoutManager.HORIZONTAL);
        mRvTouristInformation.setLayoutManager(touristInfoLayoutManager);
        touristInformationAdapter = new TouristInformationAdapter(GroupMchOrderSubmitActivity.this, new TouristInformationAdapter.TouristInformationListener() {
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

        LinearLayoutManager orderDeatilTicketInfoLayoutManager = new LinearLayoutManager(GroupMchOrderSubmitActivity.this);
        mRvOrderDeatilTicketInfo.setLayoutManager(orderDeatilTicketInfoLayoutManager);
        orderDeatilTicketInfoLayoutManager.setOrientation(orderDeatilTicketInfoLayoutManager.VERTICAL);
        orderDetailTicketInfoAdapter = new OrderDetailTicketInfoAdapter(GroupMchOrderSubmitActivity.this);
        orderDetailTicketInfoAdapter.setOrderDetailTicketList(goodsPriceDetailList);
        mRvOrderDeatilTicketInfo.setAdapter(orderDetailTicketInfoAdapter);

        //新增游客信息弹框
        LinearLayoutManager newTouristsLayoutManager = new LinearLayoutManager(GroupMchOrderSubmitActivity.this);
        mRvNewTourists.setLayoutManager(newTouristsLayoutManager);
        newTouristsLayoutManager.setOrientation(newTouristsLayoutManager.VERTICAL);
        addTouristInformationAdapter = new AddTouristInformationAdapter(GroupMchOrderSubmitActivity.this, new AddTouristInformationAdapter.TouristInformationListener() {
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
        LinearLayoutManager travelByCarLayoutManager = new LinearLayoutManager(GroupMchOrderSubmitActivity.this);
        travelByCarLayoutManager.setOrientation(travelByCarLayoutManager.VERTICAL);
        mRvTravelByCar.setLayoutManager(travelByCarLayoutManager);
        vehicleUseAdapter = new VehicleUseAdapter();
        vehicleUseAdapter.setVehicleUseList(carsBeanList);
        mRvTravelByCar.setAdapter(vehicleUseAdapter);

    }

    @Override
    public void initData() {

        showProgressDialog(GroupMchOrderSubmitActivity.this);
        getGroupMchOrderSubmitInit();

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
            R.id.img_add_purchase_num, R.id.tv_add_vehicle_more, R.id.tv_tourist_edit_cancel, R.id.img_tourist_username_input_cancel,
            R.id.img_tourist_mobile_input_cancel,
            R.id.tv_tourist_edit_confirm, R.id.tv_tourists_info_edit, R.id.tv_delete_frequently_used_tourists, R.id.img_tourist_add_username_input_cancel, R.id.img_tourist_add_mobile_input_cancel, R.id.tv_add_tourists_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_order_submit:

                ticketOrderSubmit();
                break;
            case R.id.llyt_shadow_bg:

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

                break;
            case R.id.tv_added_frequently_used_tourists:
                mLlytAddTourists.setVisibility(View.VISIBLE);
                mLlytAddTourists.startAnimation(inAnimation);
                int visibility = mLlytAddTourists.getVisibility();
                if (visibility == 8) {
                    mRlytNewTouristsBgHalf.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.rlyt_ticket:

                break;
            case R.id.tv_confirm:
                showToast(GroupMchOrderSubmitActivity.this, mLlytAddAndUpdateTourists.getVisibility() + "");
                break;
            case R.id.ibtn_back:

                GroupMchOrderSubmitActivity.this.finish();

                break;
            case R.id.tv_purchase_notes:

                if (mPurchaseInstructionsDialog == null) {
                    mPurchaseInstructionsDialog = new PurchaseInstructionsBrowseDialog(getResources().getString(R.string.str_purchase_instructions), goodsBuyNotesH5Url);
                }
                mPurchaseInstructionsDialog.show(getFragmentManager(), "单商品购票须知");
                break;
            case R.id.img_reduce_purchase_num:

                if (mPurchaseNum > 1) {
                    mPurchaseNum--;
                    mTvPurchaseNum.setText(String.valueOf(mPurchaseNum));
                    if (goodsPriceList.size() > 0) {
                        OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(0);
                        goodsPriceEntity.setTicketPurchaseNum(mPurchaseNum);
                        double totalPrice = payFee * mPurchaseNum;
                        mTvTotalPrice.setText(Tools.getTwoDecimalPoint(totalPrice));
                    }
                } else {

                    showToast(GroupMchOrderSubmitActivity.this, getResources().getString(R.string.str_need_to_buy_at_least_one));
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
                double totalPrice = payFee * mPurchaseNum;
                mTvTotalPrice.setText(Tools.getTwoDecimalPoint(totalPrice));
                break;
            case R.id.tv_add_vehicle_more:

                showVehicleUseAddDialog();
                break;
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
                mEdtTouristName.setText(realname);
                mEdtTouristMobile.setText(mobile);

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
                    mPosition++;
                    TravellerInfoResponse travellerInfoResponse = res.getData();
                    travellersList = travellerInfoResponse.getResult();
                    travellersList.get(mPosition).setTravellerSelect(true);

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
                showToast(GroupMchOrderSubmitActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void addUserTravellerResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    getTravellerList();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(GroupMchOrderSubmitActivity.this, Constants.getResultMsg(res.getMsg()));
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
                showToast(GroupMchOrderSubmitActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void deleteUserTravellerResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    TravellerBean travellersEntity = travellersList.get(mPosition);

                    travellersList.remove(travellersEntity);
                    if (travellersList != null) {
                        if (travellersList.size() > 0) {
                            TravellerBean travellers = travellersList.get(0);
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
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void ticketOrderSubmitResult(BackResult<OrderSubmitResponse> res) {

    }

    @Override
    public void getOrderSubmitInitResult(BackResult<OrderSubmitInitResponse> res) {

    }

    @Override
    public void getEstimatedPriceResult(BackResult<EstimatedPriceResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    EstimatedPriceResponse estimatedPrice = res.getData();
                    cityCode = estimatedPrice.getCityCode();
                    lineType = estimatedPrice.getLineType();
                    vehicleUseAddDialog.setTotalVehicleExpenses(estimatedPrice);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(GroupMchOrderSubmitActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getGroupMchOrderSubmitInitResult(BackResult<OrderSubmitInitResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    OrderSubmitInitResponse orderSubmitInitResponse = res.getData();
                    openCarStatus = orderSubmitInitResponse.getOpenCarStatus();
                    goodsBuyNotesH5Url = orderSubmitInitResponse.getGoodsBuyNotes();
                    travellersList = orderSubmitInitResponse.getTravellers();
                    String title = orderSubmitInitResponse.getTitle();
                    userTravelerId = travellersList.get(0).getId();
                    payFee = orderSubmitInitResponse.getPayFee();

                    //开启用车状态
                    if(openCarStatus == 0){

                        mRlytOpenCarStatus.setVisibility(View.GONE);    //用车隐藏

                    } else if(openCarStatus == 1)
                    {
                        mRlytOpenCarStatus.setVisibility(View.VISIBLE); //用车显示
                    }

                    goodsPriceList = orderSubmitInitResponse.getGoodsPrice();
                    OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(0);
                    goodsPriceEntity.setTicketPurchaseNum(1);

                    //订单日期价格
                    orderUseDateSelectAdapter.setOrderUseDateSelectList(goodsPriceList);
                    orderUseDateSelectAdapter.notifyDataSetChanged();

                    if (travellersList != null) {
                        mRlytTouristInfoItem.setVisibility(View.VISIBLE);
                        TravellerBean travellersEntity = travellersList.get(0);
                        if (travellersEntity != null) {
                            travellersEntity.setTravellerSelect(true);
                            realname = travellersEntity.getRealname();
                            mobile = travellersEntity.getMobile();
                            mTvTouristName.setText(realname);
                            mTvTouristMobile.setText(mobile);
                        }

                        touristInformationAdapter.setTouristInfoList(travellersList);
                        touristInformationAdapter.notifyDataSetChanged();

                        //新增游客编辑
                        addTouristInformationAdapter.setTouristInfoList(travellersList);
                        touristInformationAdapter.notifyDataSetChanged();
                    } else {

                        mRlytTouristInfoItem.setVisibility(View.GONE);
                    }
                    if (!TextUtils.isEmpty(title)) {
                        mTvTicketTitle.setText(title);
                    }

                    mTvTotalPrice.setText(Tools.getTwoDecimalPoint(payFee));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(GroupMchOrderSubmitActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void groupMchOrderSubmitResult(BackResult<OrderSubmitResponse> res) {
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
                    intent.setClass(GroupMchOrderSubmitActivity.this, OrderPaymentActivity.class);
                    intent.putExtra("price",price);
                    intent.putExtra("title",title);
                    intent.putExtra("payExprireTime",payExprireTime);
                    intent.putExtra("orderNo",orderNo);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(GroupMchOrderSubmitActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void getRecreationDatePriceInitResult(BackResult<OrderSubmitInitResponse> res) {

    }

    @Override
    public void recreationOrderSubmitResult(BackResult<OrderSubmitResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(GroupMchOrderSubmitActivity.this, Constants.getResultMsg(msg));
    }

    //获取订单初始化
    public void getEvaluateVehicleUsageFee(String departureTime, String startLg, String startLt, String endLg, String endLt, int carType) {

        if (validateInternet()) {
            if (!TextUtils.isEmpty(departureTime)) {
                map.put("departureTime", departureTime);
            } else {
                showToast(GroupMchOrderSubmitActivity.this, "请选择出行时间");
                return;
            }

            if (!TextUtils.isEmpty(startLg)) {
                map.put("startLg", startLg);
            } else {
                showToast(GroupMchOrderSubmitActivity.this, "请选择所在位置");
                return;
            }

            if (!TextUtils.isEmpty(startLt)) {
                map.put("startLt", startLt);
            } else {
                showToast(GroupMchOrderSubmitActivity.this, "请选择所在位置");
                return;
            }

            if (!TextUtils.isEmpty(endLg)) {
                map.put("endLg", endLg);
            } else {
                showToast(GroupMchOrderSubmitActivity.this, "请选择目的地");
                return;
            }

            if (!TextUtils.isEmpty(endLt)) {
                map.put("endLt", endLt);
            } else {
                showToast(GroupMchOrderSubmitActivity.this, "请选择目的地");
                return;
            }

            if (carType != 0) {

                map.put("carType", carType);
            } else {
                showToast(GroupMchOrderSubmitActivity.this, "请选择车辆类型");
                return;
            }
            showProgressDialog(GroupMchOrderSubmitActivity.this);
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
    public void ticketOrderSubmit() {

        if (validateInternet()) {

            goodsList.clear();
            GroupMchOrderSubmitRequest groupMchOrderSubmitRequest = new GroupMchOrderSubmitRequest();
            groupMchOrderSubmitRequest.setUserTravelerId(userTravelerId);
            groupMchOrderSubmitRequest.setGroupId(groupId);
            groupMchOrderSubmitRequest.setNum(mPurchaseNum);

            for (int i = 0; i < goodsPriceList.size(); i++)
            {
                OrderSubmitInitResponse.GoodsPriceEntity goodsPrice = goodsPriceList.get(i);
                String goodsType = goodsPrice.getGoodsType();
                List<GoodsPriceDatesResponse> goodsPriceEntityList = goodsPrice.getGoodsPriceSelectList();
                GoodsBean goodsTicket = null;
                for (int j = 0; j < goodsPriceEntityList.size(); j++) {

                    GoodsPriceDatesResponse goodsPriceDatesResponse = goodsPriceEntityList.get(j);
                    boolean isSelectDatePrice = goodsPriceDatesResponse.isSelectDatePrice();
                   // int tcketPurchaseNum = goodsPrice.getTicketPurchaseNum();
                    String date = goodsPriceDatesResponse.getDate();
                    int goodId = goodsPrice.getGoodsId();
                    if (isSelectDatePrice)
                    {
                        goodsTicket = new GoodsBean();
                        goodsTicket.setGoodsId(goodId);
                       // goodsAddTicket.setNum(mPurchaseNum);    //1.增加门票模块 票数字段 采用ticketPurchaseNum 2.外层价格日历选择 票数字段 采用mPurchaseNum 默认为1
                        goodsTicket.setDate(date);
                        goodsTicket.setGoodsType(goodsType);
                        goodsList.add(goodsTicket);
                    }
                }
            }

            groupMchOrderSubmitRequest.setGoods(goodsList);
            groupMchOrderSubmitRequest.setCars(carsBeanList);

            //是否用车 1:用 || 0:不用
            if (mCkbIsNeedUseCar.isChecked())
            {
                groupMchOrderSubmitRequest.setCarStatus(1);
            } else {
                groupMchOrderSubmitRequest.setCarStatus(0);
            }
            System.out.print(groupMchOrderSubmitRequest);
            showProgressDialog(GroupMchOrderSubmitActivity.this);
            mDialog.setTitle("正在提交订单...");
            mPresenter.groupMchOrderSubmit(groupMchOrderSubmitRequest);
        }
    }


    //修改游客信息
    public void updateTravellerInfo() {

        if (validateInternet()) {

            String touristName = mEdtTouristName.getText().toString().trim();
            String touristMobile = mEdtTouristMobile.getText().toString().trim();

            //请填写中文名
            if (TextUtils.isEmpty(touristName)) {

                showToast(GroupMchOrderSubmitActivity.this, getResources().getString(R.string.str_input_chinese_name));
                return;
            }
            //请填写手机号
            if (TextUtils.isEmpty(touristMobile)) {

                showToast(GroupMchOrderSubmitActivity.this, getResources().getString(R.string.str_input_phone));
                return;
            }

            //请填写证件号码
         /*   if (TextUtils.isEmpty(identityCardNumber)) {

                showToast(OrderSubmitActivity.this, getResources().getString(R.string.str_input_identification_number));
                return;
            }*/

            TravellerInfoRequest updateTravellerRequest = new TravellerInfoRequest();
            updateTravellerRequest.setUserId(getSharedPreferencesUserId());
            updateTravellerRequest.setRealname(touristName);
            updateTravellerRequest.setMobile(touristMobile);
            updateTravellerRequest.setId(userTravelerId);
            showProgressDialog(GroupMchOrderSubmitActivity.this);
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

                showToast(GroupMchOrderSubmitActivity.this, getResources().getString(R.string.str_input_chinese_name));
                return;
            }
            //请填写手机号
            if (TextUtils.isEmpty(touristMobile)) {

                showToast(GroupMchOrderSubmitActivity.this, getResources().getString(R.string.str_input_phone));
                return;
            }

            //请填写证件号码
         /*   if (TextUtils.isEmpty(identityCardNumber)) {

                showToast(OrderSubmitActivity.this, getResources().getString(R.string.str_input_identification_number));
                return;
            }*/

            TravellerInfoRequest addTravellerRequest = new TravellerInfoRequest();
            addTravellerRequest.setUserId(getSharedPreferencesUserId());
            addTravellerRequest.setRealname(touristName);
            addTravellerRequest.setMobile(touristMobile);
            addTravellerRequest.setId(userTravelerId);
            showProgressDialog(GroupMchOrderSubmitActivity.this);
            mDialog.setTitle(getResources().getString(R.string.str_saving));

            mPresenter.addUserTraveller(addTravellerRequest);
        }
    }

    //删除游客信息
    public void deleteTravellerInfo() {

        if (validateInternet()) {
            showProgressDialog(GroupMchOrderSubmitActivity.this);
            mDialog.setTitle("正在删除...");
            DeleteTravellerInfoRequest deleteTravellerInfoRequest = new DeleteTravellerInfoRequest();
            deleteTravellerInfoRequest.setId(userTravelerId);
            mPresenter.deleteUserTraveller(deleteTravellerInfoRequest);
        }
    }

    public void getTravellerList() {

        showProgressDialog(GroupMchOrderSubmitActivity.this);
        mPresenter.getUserTravellerList(getSharedPreferencesUserId(), mPage, mPageSize);
    }

    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery(String keywords) {
        showProgressDialog(GroupMchOrderSubmitActivity.this);
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
                        mIntent.setClass(GroupMchOrderSubmitActivity.this, VehicleAddressSelectionActivity.class);
                        mIntent.putExtra("vehicleUseOprateFlag", vehicleUseOprateFlag);
                        startActivityForResult(mIntent, REQUEST_CODE_MY_LOCATION_SELECT);
                    } else if (vehicleUseOprateFlag == 1) {

                        Intent mIntent = new Intent();
                        mIntent.setClass(GroupMchOrderSubmitActivity.this, VehicleAddressSelectionActivity.class);
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
                    vehicleUseAdapter.setVehicleUseList(carsBeanList);
                    vehicleUseAdapter.notifyDataSetChanged();

                }

                @Override
                public void setVehicleUseParamEmptyCallBack(String message) {

                    showToast(GroupMchOrderSubmitActivity.this, message);
                }

                @Override
                public void setVehicleUseCancelCallBack() {   //取消选择用车

                    mCkbIsNeedUseCar.setChecked(false);
                }
            });
        }
        vehicleUseAddDialog.show(getFragmentManager(), "");
    }

    //组合商品下单初始化
    public void getGroupMchOrderSubmitInit() {

        if (validateInternet()) {

            mPresenter.getGroupMchOrderSubmitInit(groupId);
        }
    }
}
