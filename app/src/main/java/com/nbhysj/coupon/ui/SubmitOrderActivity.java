package com.nbhysj.coupon.ui;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.AddTouristInformationAdapter;
import com.nbhysj.coupon.adapter.IncreaseTicketAdapter;
import com.nbhysj.coupon.adapter.OrderDetailTicketInfoAdapter;
import com.nbhysj.coupon.adapter.OrderUseDateSelectAdapter;
import com.nbhysj.coupon.adapter.TouristInformationAdapter;
import com.nbhysj.coupon.adapter.TouristInformationEditAdapter;
import com.nbhysj.coupon.dialog.NewTouristsDialog;
import com.nbhysj.coupon.model.response.TouristBean;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/08
 * description：订单提交
 */
public class SubmitOrderActivity extends BaseActivity {

    //订单使用日期选择
    @BindView(R.id.rv_use_ticket_date)
    RecyclerView mRvUseTicketDateSelect;
    //游客信息列表
    @BindView(R.id.rv_tourist_information)
    RecyclerView mRvTouristInformation;
    //游客信息编辑
    @BindView(R.id.rv_tourist_information_edit)
    RecyclerView mRvTouristInformationEdit;
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

                    Drawable nav_up = getResources().getDrawable(R.mipmap.icon_order_detail_up_arrow);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    mTvOrderDetailLook.setCompoundDrawables(null, null, nav_up, null);
                } else {
                    mLlytOrderDetailItem.setVisibility(View.GONE);
                    mLlytOrderDetailItem.startAnimation(outAnimation);
                    mLlytShadowBg.setVisibility(View.GONE);
                    Drawable nav_up = getResources().getDrawable(R.mipmap.icon_order_detail_down_arrow);
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    mTvOrderDetailLook.setCompoundDrawables(null, null, nav_up, null);
                }
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(SubmitOrderActivity.this);
        layoutManager.setOrientation(layoutManager.HORIZONTAL);
        mRvUseTicketDateSelect.setLayoutManager(layoutManager);
        OrderUseDateSelectAdapter orderUseDateSelectAdapter = new OrderUseDateSelectAdapter(SubmitOrderActivity.this);
        mRvUseTicketDateSelect.setAdapter(orderUseDateSelectAdapter);

        List<String> touristInfoList = new ArrayList<>();
        touristInfoList.add("谁谁谁");
        touristInfoList.add("大大大");
        LinearLayoutManager touristInfoLayoutManager = new LinearLayoutManager(SubmitOrderActivity.this);
        touristInfoLayoutManager.setOrientation(touristInfoLayoutManager.HORIZONTAL);
        mRvTouristInformation.setLayoutManager(touristInfoLayoutManager);
        TouristInformationAdapter touristInformationAdapter = new TouristInformationAdapter(SubmitOrderActivity.this, new TouristInformationAdapter.TouristInformationListener() {
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
                } else {


                }
            }
        });
        touristInformationAdapter.setTouristInfoList(touristInfoList);
        mRvTouristInformation.setAdapter(touristInformationAdapter);

        List<String> touristInfoList1 = new ArrayList<>();
        touristInfoList1.add("谁谁谁");
        touristInfoList1.add("大大大");
        LinearLayoutManager touristInformationEditLayoutManager = new LinearLayoutManager(SubmitOrderActivity.this);
        touristInformationEditLayoutManager.setOrientation(touristInformationEditLayoutManager.VERTICAL);
        mRvTouristInformationEdit.setLayoutManager(touristInformationEditLayoutManager);
        TouristInformationEditAdapter touristInformationEditAdapter = new TouristInformationEditAdapter(SubmitOrderActivity.this);
        touristInformationEditAdapter.setTouristInfoList(touristInfoList1);
        mRvTouristInformationEdit.setAdapter(touristInformationEditAdapter);

        LinearLayoutManager increaseTicketLayoutManager = new LinearLayoutManager(SubmitOrderActivity.this);
        mRvIncreaseTicket.setLayoutManager(increaseTicketLayoutManager);
        increaseTicketLayoutManager.setOrientation(increaseTicketLayoutManager.VERTICAL);
        IncreaseTicketAdapter increaseTicketAdapter = new IncreaseTicketAdapter(SubmitOrderActivity.this);
        mRvIncreaseTicket.setAdapter(increaseTicketAdapter);

        LinearLayoutManager orderDeatilTicketInfoLayoutManager = new LinearLayoutManager(SubmitOrderActivity.this);
        mRvOrderDeatilTicketInfo.setLayoutManager(orderDeatilTicketInfoLayoutManager);
        orderDeatilTicketInfoLayoutManager.setOrientation(orderDeatilTicketInfoLayoutManager.VERTICAL);
        OrderDetailTicketInfoAdapter orderDetailTicketInfoAdapter = new OrderDetailTicketInfoAdapter(SubmitOrderActivity.this);
        mRvOrderDeatilTicketInfo.setAdapter(orderDetailTicketInfoAdapter);

        List<TouristBean> touristBeanList = new ArrayList<>();

        TouristBean touristBean = new TouristBean();
        touristBean.setName("唐哈哈");
        touristBean.setIdNumber("33062419930307827x");

        TouristBean touristBean1 = new TouristBean();
        touristBean1.setName("陈对对");
        touristBean1.setIdNumber("330624199303073547");
        touristBeanList.add(touristBean);
        touristBeanList.add(touristBean1);

        LinearLayoutManager newTouristsLayoutManager = new LinearLayoutManager(SubmitOrderActivity.this);
        mRvNewTourists.setLayoutManager(newTouristsLayoutManager);
        newTouristsLayoutManager.setOrientation(newTouristsLayoutManager.VERTICAL);
        AddTouristInformationAdapter addTouristInformationAdapter = new AddTouristInformationAdapter(SubmitOrderActivity.this, new AddTouristInformationAdapter.TouristInformationListener() {
            @Override
            public void setEditTouristInfoListener(int position) {


                mLlytEditTourists.setVisibility(View.VISIBLE);
                mLlytEditTourists.startAnimation(inAnimation);

            }
        });
        addTouristInformationAdapter.setTouristInfoList(touristBeanList);
        mRvNewTourists.setAdapter(addTouristInformationAdapter);

        mLlytAddAndUpdateTourists.setEnabled(false);
    }

    @Override
    public void initData() {
       /* OrderDetailBottomDialog bottomDialogFr = new OrderDetailBottomDialog();
        bottomDialogFr.show(getFragmentManager(), "DF");*/


    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.tv_order_submit, R.id.llyt_shadow_bg, R.id.rlyt_new_tourists_bg_half, R.id.tv_added_frequently_used_tourists,
            R.id.rlyt_ticket, R.id.tv_confirm, R.id.llyt_edit_tourists, R.id.ibtn_back, R.id.tv_purchase_notes})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_order_submit:

                showToast(SubmitOrderActivity.this, "订单提交");
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


                break;
            case R.id.tv_confirm:
                showToast(SubmitOrderActivity.this, mLlytAddAndUpdateTourists.getVisibility() + "");
                break;
            case R.id.ibtn_back:

                SubmitOrderActivity.this.finish();

                break;
            case R.id.tv_purchase_notes:
                showToast(SubmitOrderActivity.this, "购买须知");
                break;
            default:
                break;
        }
    }
}
