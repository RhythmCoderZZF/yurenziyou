package com.nbhysj.coupon.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.CouponListContract;
import com.nbhysj.coupon.dialog.CouponDescriptionDialog;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.fragment.CouponListFragment;
import com.nbhysj.coupon.fragment.MyOrderFragmentManager;
import com.nbhysj.coupon.model.CouponListModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CouponListBean;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.UseCouponTicketResponse;
import com.nbhysj.coupon.presenter.CouponListPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.umeng.socialize.UMShareAPI;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CouponListActivity extends BaseActivity<CouponListPresenter, CouponListModel> implements CouponListContract.View  {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    //返回
    @BindView(R.id.iv_back)
    ImageView mImgBack;

    @BindView(R.id.edt_enter_exchange_code)
    EditText mEdtEnterExchangeCode;

    private String[] mTitles = new String[]{"未使用", "已过期", "已使用"};
    HashMap<String, String> couponStatusMap;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_coupon_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        couponStatusMap = getCouponStatus();
        List<Fragment> fragmentList = getFragments();
        FragmentPagerAdapter adapter = new MyOrderFragmentManager(getSupportFragmentManager(), mTitles,fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public void initData() {

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CouponListActivity.this.finish();
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            String couponStatus = couponStatusMap.get(mTitles[i]);
            fragments.add(new CouponListFragment().newInstance(couponStatus));
        }
        return fragments;
    }

    private HashMap<String, String> getCouponStatus() {

        HashMap<String, String> map = new HashMap<>();
        map.put("未使用", "ALREADYRECEIVED");
        map.put("已过期", "EXPIRED");
        map.put("已使用", "USED");  //已付款
        return map;
    }

    @OnClick({R.id.tv_ticket_center,R.id.tv_exchange_code})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_ticket_center:

                toActivity(CouponCenterActivity.class);
                break;
            case R.id.tv_exchange_code:
                couponExchange();
                break;
                default:break;

        }

    }

    @Override
    public void getCouponListResult(BackResult<CouponListBean> res) {

    }

    @Override
    public void getCouponCenterResult(BackResult<List<CouponsBean>> res) {

    }

    @Override
    public void getCouponResult(BackResult<CouponsGetBean> res) {

    }

    @Override
    public void useCouponTicketResult(BackResult<UseCouponTicketResponse> res) {

    }

    @Override
    public void couponExchangeResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    EventBus.getDefault().post("couponOprate");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(CouponListActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(CouponListActivity.this, Constants.getResultMsg(msg));
    }

    //优惠券兑换
    private void couponExchange(){

        if(validateInternet())
        {
            String exchangeCode = mEdtEnterExchangeCode.getText().toString();

            if(TextUtils.isEmpty(exchangeCode))
            {
                showToast(CouponListActivity.this,"请输入券码");
                return;
            }
            mPresenter.couponExchange(exchangeCode);
        }
    }
}
