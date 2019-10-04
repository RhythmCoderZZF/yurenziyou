package com.nbhysj.coupon.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.GoodsMealDetailAdapter;
import com.nbhysj.coupon.adapter.GoodsMealPriceDetailAdapter;
import com.nbhysj.coupon.adapter.OrderDetailVehicleUseAdapter;
import com.nbhysj.coupon.adapter.OrderPriceDetailDialogVehicleUseAdapter;
import com.nbhysj.coupon.model.response.OrderDetailResponse;
import com.nbhysj.coupon.ui.OrderDetailActivity;

import java.util.List;

import butterknife.BindView;

/**
 * created by hysj on 2018/04/07.
 * description: 价格明细对话框
 */
public class OrderPriceDetailsDialog extends DialogFragment {
    /**
     * 上下文
     **/
    private Context context;

    /**
     * 提示提交弹框
     **/
    private FrameLayout mFrameOrderPriceDetail;

    private ImageView mImgCancelDialog;

    private View view;

    //订单详情票
    RecyclerView mRvOrderDetailTicket;

    //用车详情
    RecyclerView mRvVehicleUse;

    //订单详情
    OrderDetailResponse orderDetailResponse;

    //订单明细适配器
    private GoodsMealPriceDetailAdapter goodMealDetailAdapter;

    //用车
    private OrderPriceDetailDialogVehicleUseAdapter orderDetailVehicleUseAdapter;

    //商品列表
    private List<OrderDetailResponse.OrderGoodsEntity> orderGoodsList;

    //用车列表
    private List<OrderDetailResponse.OrderCarEntity> orderCarList;

    //订单总价
    private TextView mTvOrderTotalPrice;
    //订单折扣价格
    private TextView mTvOrderDiscountPrice;
    //实付金额
    private TextView mTvActualAmountPaid;
    public OrderPriceDetailsDialog(){

    }


    public void setOrderPriceDetailList(Context context,OrderDetailResponse orderDetailResponse) {

        this.context = context;
        this.orderDetailResponse = orderDetailResponse;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        context = getActivity();
        initView();
        Dialog dialog = new Dialog(context, R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        return dialog;
    }

    private void initView() {

        view = LayoutInflater.from(context).inflate(R.layout.layout_order_price_details_pop, null);
        mFrameOrderPriceDetail = (FrameLayout) view.findViewById(R.id.frame_order_price_details);
        mRvOrderDetailTicket = view.findViewById(R.id.rv_order_deatil_ticket); //订单详情
        mRvVehicleUse = view.findViewById(R.id.rv_vehicle_use); //订单详情
        mImgCancelDialog = (ImageView) view.findViewById(R.id.image_cancel_dialog);
        mTvOrderTotalPrice = view.findViewById(R.id.tv_order_total_price);
        mTvOrderDiscountPrice = view.findViewById(R.id.tv_order_discount_price);
        mTvActualAmountPaid = view.findViewById(R.id.tv_actual_amount_paid);

        orderGoodsList = orderDetailResponse.getOrderGoods();
        OrderDetailResponse.OrderEntity orderEntity = orderDetailResponse.getOrder();
        String totalFee = orderEntity.getTotalFee();  //总金额
        String discountFee = orderEntity.getDiscountFee(); //优惠金额
        String payFee = orderEntity.getPayFee();    //实付金额
        mTvOrderTotalPrice.setText(totalFee);
        mTvOrderDiscountPrice.setText(discountFee);
        mTvActualAmountPaid.setText(payFee);

        LinearLayoutManager linearLayout = new LinearLayoutManager(context);
        linearLayout.setOrientation(linearLayout.VERTICAL);
        mRvOrderDetailTicket.setLayoutManager(linearLayout);
        goodMealDetailAdapter = new GoodsMealPriceDetailAdapter(context);
        goodMealDetailAdapter.setOrderDetailDialogList(orderGoodsList);
        mRvOrderDetailTicket.setAdapter(goodMealDetailAdapter);

        //订单详情用车模块
        orderCarList = orderDetailResponse.getOrderCar();
        LinearLayoutManager orderDetailVehicleLinearLayout = new LinearLayoutManager(context);
        orderDetailVehicleLinearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        mRvVehicleUse.setLayoutManager(orderDetailVehicleLinearLayout);
        orderDetailVehicleUseAdapter = new OrderPriceDetailDialogVehicleUseAdapter();
        orderDetailVehicleUseAdapter.setVehicleUseList(orderCarList);
        mRvVehicleUse.setAdapter(orderDetailVehicleUseAdapter);
        mFrameOrderPriceDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mImgCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //KeyBoardUtils.closeKeybord((Activity) context);
        super.show(manager, tag);
        //订单详情商品模块

    }
}
