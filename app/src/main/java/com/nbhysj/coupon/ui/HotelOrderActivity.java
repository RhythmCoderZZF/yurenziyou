package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HotelContract;
import com.nbhysj.coupon.dialog.RoomNumberSelectDialog;
import com.nbhysj.coupon.model.HotelModel;
import com.nbhysj.coupon.model.request.HotelHomestayOrderSubmitRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HotelOrderInitResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.presenter.HotelPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/09
 * description：酒店下单
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
    //总价
    @BindView(R.id.tv_totel_price)
    TextView mTvTotelPrice;
    //酒店标签
    @BindView(R.id.tv_hotel_tag)
    TextView mTvHotelTag;
    //入住时间和离店时间相差天数
    @BindView(R.id.tv_differ_days_num)
    TextView mTvDifferDaysNum;
    //房间数量选择
    @BindView(R.id.tv_room_num)
    TextView mTvRoomNum;
    //房间数量
    private int roomNum = 1;
    //商品id
    private int goodId;
    //商户名
    private String mchName;

    RoomNumberSelectDialog roomNumberSelectDialog;

    //入住时间
    private String checkInDateStr;

    //离店时间
    private String leaveDateStr;

    StringBuffer stringBuffer = new StringBuffer();
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

        goodId = getIntent().getIntExtra("goodsId",0);
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
                    double price = hotelOrderInitResponse.getPrice();
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
                    mTvTotelPrice.setText(String.valueOf(price));
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
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("orderSubmitResponse", orderSubmitResponse);
                    intent.putExtras(bundle);
                    intent.setClass(HotelOrderActivity.this, OrderPaymentActivity.class);
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
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(HotelOrderActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.tv_room_num,R.id.llyt_check_in_time,R.id.llyt_leave_time,R.id.tv_order_submit})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_room_num:
                if (roomNumberSelectDialog == null) {
                    roomNumberSelectDialog = new RoomNumberSelectDialog();
                    roomNumberSelectDialog.setVehicleUseSelectListener(new RoomNumberSelectDialog.VehicleUseSelectModelListener() {

                        @Override
                        public void setVehicleUseSelectModelCallBack(int mItemSelectRoomNum) {

                            roomNum = mItemSelectRoomNum;
                            mTvRoomNum.setText(String.valueOf(mItemSelectRoomNum) + "间");
                        }
                    });
                    roomNumberSelectDialog.show(getFragmentManager(), "");
                } else {

                    roomNumberSelectDialog.show(getFragmentManager(), "");
                }
                    break;
            case R.id.llyt_check_in_time:

                Intent intent = new Intent();
                intent.setClass(HotelOrderActivity.this,CalendarActivity.class);
                intent.putExtra("selectType",1);
                startActivityForResult(intent,0);
                break;
            case R.id.llyt_leave_time:

                toActivityForResult(CalendarActivity.class,0);

                break;
            case R.id.tv_order_submit:

                hotelHomestayOrderSubmit();

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
            mPresenter.getHotelHomestayOrderInit(goodId,checkInDateStr + "~" + leaveDateStr);
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

            homestayOrderSubmitRequest.setGoodsId(goodId); //商品id
            homestayOrderSubmitRequest.setCheckInAndOutDate(checkInDateStr + "~" + leaveDateStr);
            homestayOrderSubmitRequest.setName(checkInUsername);
            homestayOrderSubmitRequest.setMobile(checkInUserPhone);
            homestayOrderSubmitRequest.setNum(roomNum);
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
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
