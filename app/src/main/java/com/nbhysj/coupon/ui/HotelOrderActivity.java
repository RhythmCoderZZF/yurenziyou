package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HotelContract;
import com.nbhysj.coupon.dialog.CouponSelectDialog;
import com.nbhysj.coupon.dialog.RoomNumberSelectDialog;
import com.nbhysj.coupon.model.HotelModel;
import com.nbhysj.coupon.model.request.GoodsBeanRequest;
import com.nbhysj.coupon.model.request.HotelHomestayOrderSubmitRequest;
import com.nbhysj.coupon.model.request.QueryByTicketRequest;
import com.nbhysj.coupon.model.request.UseCouponTicketRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.HotelOrderInitResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.QueryByTicketResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.UseCouponTicketResponse;
import com.nbhysj.coupon.presenter.HotelPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.CalendarUtil;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.util.Tools;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/09
 * description：酒店下单(民宿共用)
 */
public class HotelOrderActivity extends BaseActivity<HotelPresenter, HotelModel> implements HotelContract.View {
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    //酒店商品类型
    @BindView(R.id.tv_hotel_goods_type)
    TextView mTvHotelGoodsType;
    //预定取消规则
    @BindView(R.id.tv_reservation_cancel_rule)
    TextView mTvReservationCancelRule;
    //入住时间
    @BindView(R.id.llyt_check_in_time)
    LinearLayout mLlytCheckInTime;
    //入住时间
    @BindView(R.id.tv_check_in_time)
    TextView mTvCheckInTime;
    //入住时间周几
    @BindView(R.id.tv_check_in_date_week)
    TextView mTvCheckInTimeDateWeek;
    @BindView(R.id.llyt_leave_time)
    LinearLayout mLlytLeaveTime;
    //离开时间
    @BindView(R.id.tv_leave_time)
    TextView mTvLeaveTime;
    //离店时间周几
    @BindView(R.id.tv_leave_time_date_week)
    TextView mTvLeaveTimeDateWeek;
    //到店时间
    @BindView(R.id.tv_arrival_hotel_time)
    TextView mTvArrivalHotelTime;
    //入住人姓名
    @BindView(R.id.edt_check_in_people_name)
    EditText mEdtCheckInPeopleName;
    //入住人手机号码
    @BindView(R.id.edt_check_in_people_phone)
    EditText mEdtCheckInPeoplePhone;
    //报销凭证
    @BindView(R.id.tv_reimbursement_certificate)
    TextView mTvReimbursementCertificate;
    //入离描述
    @BindView(R.id.tv_into_and_leave_desc)
    TextView mTvIntoAndLeaveDes;
    //酒店标签
    @BindView(R.id.tv_hotel_tag)
    TextView mTvHotelTag;
    //入住时间和离店时间相差天数
    @BindView(R.id.tv_differ_days_num)
    TextView mTvDifferDaysNum;
    //房间数量选择
    @BindView(R.id.tv_room_num)
    TextView mTvRoomNum;

    @BindView(R.id.tv_coupon)
    TextView mTvCoupon;

    //已减价格
    @BindView(R.id.tv_already_reduced_price)
    TextView mTvAlreadyReducedPrice;

    //默认价格
    @BindView(R.id.tv_default_ticket_price)
    TextView mTvDefaultTicketPrice;

    //市场票价
    @BindView(R.id.tv_market_ticket_price)
    TextView mTvMarketTicketPrice;

    @BindView(R.id.img_coupon)
    ImageView mImgCoupon;

    //房间数量
    private int roomNum = 1;
    //商品id
    private int goodsId;
    //商户名
    private String mchName;

    RoomNumberSelectDialog roomNumberSelectDialog;

    //入住时间
    private String checkInDateStr;

    //离店时间
    private String leaveDateStr;

    StringBuffer stringBuffer = new StringBuffer();

    //酒店或者民宿（单价）
    private double price;

    //酒店或者民宿（总价）
    private double totalPrice;

    //优惠券
    List<CouponsBean> couponList;

    //商品列表
    private List<GoodsBeanRequest> goodsList;

    private CouponSelectDialog couponSelectDialog;

    //选择的优惠券id
    private List<Integer> chooseIds;

    //新选择的优惠券id
    private int newUseId;

    private List<String> calendar;

    //优惠券标题
    private String mCouponTitle;
    @Override
    public int getLayoutId() {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_hotel_and_homestay_order;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        goodsId = getIntent().getIntExtra("goodsId",0);
        mchName = getIntent().getStringExtra("mchName");
        ToolbarHelper.setHeadBar(HotelOrderActivity.this, mchName, R.mipmap.icon_left_arrow_black, "");

        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }

        //商品列表 组装订单提交使用
        if (goodsList == null) {

            goodsList = new ArrayList<>();
        } else {
            goodsList.clear();
        }

        if (chooseIds == null) {

            chooseIds = new ArrayList<>();
        } else {
            chooseIds.clear();
        }
    }

    @Override
    public void initData() {

        try {

            Date checkInDate = new Date();
            long time = checkInDate.getTime();
            checkInDateStr = DateUtil.transferLongToDateStr(DateUtil.sDateYMDFormat, time);

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 1);
            Date leaveDate = c.getTime();
            long postponeDay = leaveDate.getTime();
            leaveDateStr = DateUtil.transferLongToDateStr(DateUtil.sDateYMDFormat, postponeDay);

            hotelDateSelectDeal(checkInDateStr,leaveDateStr);

            getHotelHomestayOrderInit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void getHotelHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findHotelByCateResult(BackResult<ScenicSpotResponse> res) {

    }

    @Override
    public void getHotelBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

    }

    @Override
    public void getMchDetailsResult(BackResult<MchDetailsResponse> res) {

    }

    @Override
    public void getHotelHomestayOrderInitResult(BackResult<HotelOrderInitResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    stringBuffer.setLength(0);
                    HotelOrderInitResponse hotelOrderInitResponse = res.getData();
                    price = hotelOrderInitResponse.getPrice();
                    totalPrice = roomNum * price;
                    String intoTime = hotelOrderInitResponse.getIntoTime();
                    String leaveTime = hotelOrderInitResponse.getLeaveTime();
                    List<String> sign = hotelOrderInitResponse.getSign();
                    HotelOrderInitResponse.GoodsEntity goodsEntity = hotelOrderInitResponse.getGoods();
                    String title = goodsEntity.getTitle();
                    String intoAndLeaveDesc = hotelOrderInitResponse.getIntoAndLeaveDesc();
                    String invoice = hotelOrderInitResponse.getInvoice();
                    HotelOrderInitResponse.RefundRulesEntity refundRulesEntity = hotelOrderInitResponse.getRefundRules();
                    String refundTime = refundRulesEntity.getRefundTime();

                    //酒店标题
                    if(sign != null) {
                        if (sign.size() > 0)
                            for (int i = 0; i < sign.size(); i++) {
                                String hotelSignStr = sign.get(i);
                                stringBuffer.append(hotelSignStr + " ");
                                mTvHotelTag.setText(stringBuffer.toString());
                            }
                    }

                    mTvReservationCancelRule.setText(refundTime);
                    //报销凭证
                    mTvReimbursementCertificate.setText(invoice);
                    //总价
                    mTvMarketTicketPrice.setText(Tools.getTwoDecimalPoint(price));
                    mTvHotelGoodsType.setText(title);

                    mTvArrivalHotelTime.setText(intoTime + "点后办理入住");

                    if(!TextUtils.isEmpty(intoAndLeaveDesc))
                    {
                        mTvIntoAndLeaveDes.setText(Html.fromHtml(intoAndLeaveDesc.replace("</br>","<br>")));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(HotelOrderActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void hotelHomestayOrderSubmitResult(BackResult<OrderSubmitResponse> res) {

        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    OrderSubmitResponse orderSubmitResponse = res.getData();
                    Intent intent = new Intent();
                    double price = orderSubmitResponse.getPrice();
                    String title = orderSubmitResponse.getTitle();
                    long payExprireTime = orderSubmitResponse.getPayExprireTime();
                    String orderNo = orderSubmitResponse.getOrderNo();
                    intent.setClass(HotelOrderActivity.this, OrderPaymentActivity.class);
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
                showToast(HotelOrderActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void mchCollectionResult(BackResult<MchCollectionResponse> res) {

    }

    @Override
    public void queryMchCouponListResult(BackResult<List<MchCouponResponse>> res) {

    }

    @Override
    public void getCouponResult(BackResult<CouponsGetBean> res) {

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
                        mImgCoupon.setVisibility(View.VISIBLE);
                        CouponsBean couponsBean1 = new CouponsBean();
                        couponsBean1.setTitle("不使用优惠");
                        couponList.add(couponsBean1);
                    } else if (couponNum > 1) {
                        mTvCoupon.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                        mTvCoupon.setText(couponNum + "张可用");
                        mImgCoupon.setVisibility(View.VISIBLE);
                        CouponsBean couponsBean1 = new CouponsBean();
                        couponsBean1.setTitle("不使用优惠");
                        couponList.add(couponsBean1);
                    } else {
                        mTvCoupon.setText("无优惠券");
                        mTvCoupon.setTextColor(mContext.getResources().getColor(R.color.color_text_gray17));
                        mImgCoupon.setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(HotelOrderActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }

    }

    @Override
    public void useCouponTicketResult(BackResult<UseCouponTicketResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                        UseCouponTicketResponse useCouponTicketResponse = res.getData();

                        int disCount = useCouponTicketResponse.getDiscount();
                        chooseIds.clear();
                        chooseIds = useCouponTicketResponse.getChooseId();

                        if (couponSelectDialog != null)
                        {
                            couponSelectDialog.dismiss();
                        }

                        if(disCount > 0) {

                            mTvAlreadyReducedPrice.setVisibility(View.VISIBLE);
                            mTvAlreadyReducedPrice.setText("已减" + disCount + "元");
                            mTvDefaultTicketPrice.setVisibility(View.VISIBLE);
                            double price = totalPrice - disCount;
                            mTvMarketTicketPrice.setText(Tools.getTwoDecimalPoint(price));
                            mTvDefaultTicketPrice.setText("¥" + Tools.getTwoDecimalPoint(totalPrice));
                            mTvDefaultTicketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
                            mTvCoupon.setText(mCouponTitle);
                        } else {
                            mTvCoupon.setText("无优惠券");
                            mTvMarketTicketPrice.setText(Tools.getTwoDecimalPoint(totalPrice));
                            mTvAlreadyReducedPrice.setVisibility(View.GONE);
                            mTvDefaultTicketPrice.setVisibility(View.GONE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                break;
            default:
                showToast(HotelOrderActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(HotelOrderActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.tv_room_num,R.id.llyt_check_in_time,R.id.llyt_leave_time,R.id.tv_order_submit,R.id.rlyt_coupon})
    public void onClick(View v){
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.tv_room_num:
                if (roomNumberSelectDialog == null) {
                    roomNumberSelectDialog = new RoomNumberSelectDialog();
                    roomNumberSelectDialog.setVehicleUseSelectListener(new RoomNumberSelectDialog.VehicleUseSelectModelListener() {

                        @Override
                        public void setVehicleUseSelectModelCallBack(int mItemSelectRoomNum) {

                            roomNum = mItemSelectRoomNum;
                            mTvRoomNum.setText(String.valueOf(mItemSelectRoomNum) + "间");
                            double totalPrice = roomNum * price;

                            mTvMarketTicketPrice.setText(Tools.getTwoDecimalPoint(totalPrice));
                        }
                    });
                    roomNumberSelectDialog.show(getFragmentManager(), "");
                } else {

                    roomNumberSelectDialog.show(getFragmentManager(), "");
                }
                    break;
            case R.id.llyt_check_in_time:

                intent.setClass(HotelOrderActivity.this,CalendarActivity.class);
                intent.putExtra("selectType",1);
                startActivityForResult(intent,0);
                break;
            case R.id.llyt_leave_time:

                intent.setClass(HotelOrderActivity.this,CalendarActivity.class);
                intent.putExtra("selectType",1);
                startActivityForResult(intent,0);

                break;
            case R.id.tv_order_submit:

                hotelHomestayOrderSubmit();

                break;
            case R.id.rlyt_coupon:

                if(couponList.size() > 0) {

                    if (couponSelectDialog == null) {
                        couponSelectDialog = new CouponSelectDialog(couponList, new CouponSelectDialog.CouponSelectListener() {
                            @Override
                            public void setCouponSelectCallback(int couponId, boolean isCouponSelect, String couponTitle) {

                                mCouponTitle = couponTitle;
                                if(!mCouponTitle.equals("不使用优惠"))
                                {
                                    getAndUseCoupon(couponId, isCouponSelect);

                                } else {

                                    mTvCoupon.setText(mCouponTitle);
                                    mTvAlreadyReducedPrice.setVisibility(View.GONE);
                                    mTvDefaultTicketPrice.setVisibility(View.GONE);

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
                }
                break;
            default:break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 编辑行程日期选择保存
         */
        if(requestCode == 0 && resultCode == RESULT_OK)
        {
            String startDate = data.getStringExtra("startDate");
            String endDate = data.getStringExtra("endDate");

            hotelDateSelectDeal(startDate,endDate);
        }
    }

    //获取酒店初始化页面
    public void getHotelHomestayOrderInit(){

        if(validateInternet())
        {
            showProgressDialog(HotelOrderActivity.this);
            mPresenter.getHotelHomestayOrderInit(goodsId,checkInDateStr + "~" + leaveDateStr);
        }
    }

    //获取酒店订单提交
    public void hotelHomestayOrderSubmit(){

        if(validateInternet())
        {
            showProgressDialog(HotelOrderActivity.this);
            HotelHomestayOrderSubmitRequest homestayOrderSubmitRequest = new HotelHomestayOrderSubmitRequest();
            String checkInUsername = mEdtCheckInPeopleName.getText().toString();

            String checkInUserPhone = mEdtCheckInPeoplePhone.getText().toString();
            if(TextUtils.isEmpty(checkInUsername)) {
                dismissProgressDialog();
                showToast(HotelOrderActivity.this,getResources().getString(R.string.str_checkin_username));
                return;
            }

            if(TextUtils.isEmpty(checkInUserPhone)) {
                dismissProgressDialog();
                showToast(HotelOrderActivity.this,getResources().getString(R.string.str_checkin_user_phone));
                return;
            }

            homestayOrderSubmitRequest.setGoodsId(goodsId); //商品id
            homestayOrderSubmitRequest.setCheckInAndOutDate(checkInDateStr + "~" + leaveDateStr);
            homestayOrderSubmitRequest.setName(checkInUsername);
            homestayOrderSubmitRequest.setMobile(checkInUserPhone);
            homestayOrderSubmitRequest.setNum(roomNum);
            homestayOrderSubmitRequest.setCouponIds(chooseIds);
            mPresenter.hotelHomestayOrderSubmit(homestayOrderSubmitRequest);
        }
    }

    //酒店日期处理
    public void hotelDateSelectDeal(String checkInDateStr,String leaveDateStr){

        try {

            if(!TextUtils.isEmpty(checkInDateStr) && !TextUtils.isEmpty(leaveDateStr))
            {
                String currentDate = DateUtil.toMMDDStr(checkInDateStr);
                String checkInDay = DateUtil.dateToWeek(checkInDateStr);
                mTvCheckInTime.setText(currentDate);
                mTvCheckInTimeDateWeek.setText(checkInDay);

                String postponeDate = DateUtil.toMMDDStr(leaveDateStr);
                String leaveDay = DateUtil.dateToWeek(leaveDateStr);

                mTvLeaveTime.setText(postponeDate);
                mTvLeaveTimeDateWeek.setText(leaveDay);

                DateFormat df = new SimpleDateFormat(DateUtil.sDateYMDFormat);
                Date leaveDate = df.parse(leaveDateStr);
                Date checkInDate = df.parse(checkInDateStr);
                long differDays = DateUtil.differDays(leaveDate, checkInDate);
                mTvDifferDaysNum.setText("共" + String.valueOf(differDays) + "晚");

                calendar = CalendarUtil.getDays(checkInDateStr,leaveDateStr);
                queryByTicket(calendar);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getAndUseCoupon(int couponId, boolean isCouponSelect) {
        goodsList.clear();

        for (int i = 0; i < calendar.size();i++)
        {
            String calendarStr = calendar.get(i);
            GoodsBeanRequest goodsBean = new GoodsBeanRequest();
            goodsBean.setGoodsId(goodsId);
            goodsBean.setNum(roomNum);
            goodsBean.setGoodsType("GOODS_HOTEL_ROOM");
            goodsBean.setDate(calendarStr);
            goodsList.add(goodsBean);
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
            if (!isCountainsCouponId) {

                chooseIds.add(couponId);
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

    public void queryByTicket(List<String> calendar) {
        //showProgressDialog(OrderSubmitActivity.this);
        QueryByTicketRequest queryByTicketRequest = new QueryByTicketRequest();
        goodsList.clear();

        for (int i = 0; i < calendar.size();i++)
        {
            String calendarStr = calendar.get(i);
            GoodsBeanRequest goodsBean = new GoodsBeanRequest();
            goodsBean.setGoodsId(goodsId);
            goodsBean.setNum(roomNum);
            goodsBean.setGoodsType("GOODS_HOTEL_ROOM");
            goodsBean.setDate(calendarStr);
            goodsList.add(goodsBean);
        }

        queryByTicketRequest.setGoods(goodsList);
        mPresenter.queryByTicket(queryByTicketRequest);
    }

    //使用优惠券校验
    public void useCouponTicket(){

        if(validateInternet()){
            showProgressDialog(HotelOrderActivity.this);
            UseCouponTicketRequest useCouponTicketRequest = new UseCouponTicketRequest();
            useCouponTicketRequest.setGoods(goodsList);
            useCouponTicketRequest.setChooseIds(chooseIds);
            useCouponTicketRequest.setNewUseId(newUseId);
            mPresenter.useCouponTicketRequest(useCouponTicketRequest);
        }
    }
}
