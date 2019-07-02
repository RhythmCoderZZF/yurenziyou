package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FmPagerAdapter;
import com.nbhysj.coupon.adapter.TravelAssistantDetailFmPagerAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.dialog.TravelAssistantAddDialog;
import com.nbhysj.coupon.fragment.TravelAssisantDetailFragment;
import com.nbhysj.coupon.framework.RxBus;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.request.CreateTripRequest;
import com.nbhysj.coupon.model.request.DeleteTripPlaceRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.widget.MyIndicator;
import com.nbhysj.coupon.widget.MyViewPager;
import com.nbhysj.coupon.widget.TripAssistantIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/08
 * description：行程详情
 */
public class TravelAssistantDetailsActivity extends BaseActivity<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View, TravelAssisantDetailFragment.DeleteTravelAssistantPlaceListener {
    @BindView(R.id.indicator)
    TripAssistantIndicator mIndicator;
    @BindView(R.id.pager)
    MyViewPager pager;
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.img_my_travel_detail_header)
    ImageView mImgTravelDetailHeader;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private TravelAssisantDetailFragment travelAssisantDetailFragment;
    List<HomePageResponse.SmallTagEntity> labelList3;
    TravelAssistantDetailFmPagerAdapter fmPagerAdapter;
    private boolean isTripInit = true;

    private String countyPhotoUrl;
    private int mTripId;
    private int mDayIndex;
    private int mCurrentItemPosition = 0;
    List<TripDetailsResponse.DetailsEntity> detailsList;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_itinerary_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTripId = getIntent().getIntExtra("tripId", 0);
        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }

        if (detailsList == null) {

            detailsList = new ArrayList<>();
        } else {

            detailsList.clear();
        }
        fmPagerAdapter = new TravelAssistantDetailFmPagerAdapter(getSupportFragmentManager());
        fmPagerAdapter.setTravelAssistantDetailList(fragments, detailsList);
        pager.setAdapter(fmPagerAdapter);
        mIndicator.setViewPager(pager, 0);
    }

    @Override
    public void initData() {
        getTravelAssistantDetails();
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getTravelAssistantHomePageResult(BackResult<TripHomePageResponse> res) {

    }

    @Override
    public void getCountyListResult(BackResult<List<CountryBean>> res) {

    }

    @Override
    public void getCountyWebListResult(BackResult<List<TravelAssistantDetailCountryBean>> res) {

    }

    @Override
    public void getCreateTripResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void getTravelAssistantMchListResult(BackResult<TripScenicSpotAddCountryBean> res) {

    }

    @Override
    public void delTripPlaceResult(BackResult res) {


    }

    @Override
    public void getTripDetailsResult(BackResult<TripDetailsResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                // try {

                fragments.clear();
                TripDetailsResponse tripDetailsResponse = res.getData();
                countyPhotoUrl = tripDetailsResponse.getCountyPhoto();

                GlideUtil.loadImage(mContext, countyPhotoUrl, mImgTravelDetailHeader);

                detailsList = tripDetailsResponse.getDetails();

                if (detailsList.size() > 0) {

                    for (int i = 0; i < detailsList.size(); i++) {
                        TripDetailsResponse.DetailsEntity tripDetailsEntity = detailsList.get(i);
                        travelAssisantDetailFragment = new TravelAssisantDetailFragment().newInstance(mTripId, mDayIndex);
                        travelAssisantDetailFragment.setTravelAssisantDetailList(tripDetailsEntity);
                        fragments.add(travelAssisantDetailFragment);
                    }

                }
                if (fmPagerAdapter != null) {
                    fmPagerAdapter.setTravelAssistantDetailList(fragments, detailsList);
                    fmPagerAdapter.notifyDataSetChanged();
                }

                if (pager != null) {
                    pager.getAdapter().notifyDataSetChanged();
                }

                mIndicator.notifyDataSetChanged();

                mDayIndex = detailsList.get(0).getDayIndex();
                mIndicator.setMyOnPageChangeListener(new TripAssistantIndicator.MyOnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                        mCurrentItemPosition = position;
                        TripDetailsResponse.DetailsEntity tripDetailsEntity = detailsList.get(position);
                        mDayIndex = tripDetailsEntity.getDayIndex();

                        travelAssisantDetailFragment.setTravelAssisantDetailList(tripDetailsEntity);
                        // showToast(TravelAssistantDetailsActivity.this,dayIndex+"");
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                //  isTripInit = false;
             /*         String mBroadcastAction = Constants.BROCAST_ACTION_TRIP_ASSISANT;
                Intent intent = new Intent();
                //指定发送广播的频道
                intent.setAction(mBroadcastAction);
                //发送广播的数据
                sendBroadcast(intent);*/
                // 发送带 tag 为 "my tag" 的 String 类型事件
                // RxBus.$().post("with tag", "my tag");

               /* } catch (Exception e) {
                    e.printStackTrace();
                }*/
                break;
            default:
                showToast(TravelAssistantDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void insertPlaceMchResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void insertNoteResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(TravelAssistantDetailsActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.img_add_my_travel_content, R.id.iv_back, R.id.llyt_travel_assistant_preview, R.id.img_map})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.img_add_my_travel_content:
                // labelList3.remove(0);
                //fragments.remove(0);
/*
                for (int i = 0;i < detailsList.size();i++) {

                    detailsList.get(0).setCircuit("1111");
                }

                fmPagerAdapter.notifyDataSetChanged();

                pager.getAdapter().notifyDataSetChanged();

                mIndicator.setViewPager(pager,0);*/

                TravelAssistantAddDialog travelAssistantAddDialog = new TravelAssistantAddDialog();
                travelAssistantAddDialog.setTravelAssistantAddListener(new TravelAssistantAddDialog.TravelAssistantAddListener() {
                    @Override
                    public void travelAssistantAddListener(int position) {
                        Intent intent = new Intent();
                        if (position == 0) {

                            intent.setClass(TravelAssistantDetailsActivity.this, TravelAssisantScenicSpotAddActivity.class);
                            intent.putExtra("tripId", mTripId);
                            intent.putExtra("dayIndex", mDayIndex);
                        } else if (position == 6) {

                            intent.setClass(TravelAssistantDetailsActivity.this, TravelAssistantRemarksActivity.class);
                            intent.putExtra("tripId", mTripId);
                            intent.putExtra("dayIndex", mDayIndex);
                            intent.putExtra("isAddRemarks", true); //编辑 false : 添加 true
                        }

                        startActivityForResult(intent, 0);
                    }
                });
                travelAssistantAddDialog.show(getFragmentManager(), "行程助手添加");
                break;
            case R.id.iv_back:

                TravelAssistantDetailsActivity.this.finish();

                break;
            case R.id.llyt_travel_assistant_preview:


                intent.setClass(TravelAssistantDetailsActivity.this, TripPreviewActivity.class);
                startActivity(intent);

                break;
            case R.id.img_map:

                intent.setClass(TravelAssistantDetailsActivity.this, TravelAssistantRoutePlanMapActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    //获取行程助手详情
    public void getTravelAssistantDetails() {

        if (validateInternet()) {

            mPresenter.getTripDetails(mTripId);

        }
    }

    //删除行程助手行程点
    public void delTripPlace(int tripPlaceId) {

        if (validateInternet()) {

            DeleteTripPlaceRequest deleteTripPlaceRequest = new DeleteTripPlaceRequest();
            deleteTripPlaceRequest.setId(tripPlaceId);
            mPresenter.delTripPlace(deleteTripPlaceRequest);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            getTravelAssistantDetails();
           /* if(fmPagerAdapter != null) {
                fmPagerAdapter.notifyDataSetChanged();
            }

            if(pager != null) {
                pager.getAdapter().notifyDataSetChanged();
            }

            mIndicator.setViewPager(pager,0);*/

            // showToast(TravelAssistantDetailsActivity.this,"2222");
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        for (int indext = 0; indext < fragmentManager.getFragments().size(); indext++) {
            if (indext == mCurrentItemPosition) {
                Fragment fragment = fragmentManager.getFragments().get(indext); //找到第一层Fragment
                if (fragment == null)
                    Log.w("", "Activity result no fragment exists for index: 0x"
                            + Integer.toHexString(requestCode));
                else
                    handleResult(fragment, requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void setDeleteTravelAssistantPlaceListener(int position, int tripPlaceId) {

    }

    /**
     * 递归调用，对所有的子Fragment生效
     *
     * @param fragment
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void handleResult(Fragment fragment, int requestCode, int resultCode, Intent data) {
        fragment.onActivityResult(requestCode, resultCode, data);//调用每个Fragment的onActivityResult
        Log.e("", "MyBaseFragmentActivity");
        List<Fragment> childFragment = fragment.getChildFragmentManager().getFragments(); //找到第二层Fragment
        if (childFragment != null)
            for (Fragment f : childFragment)
                if (f != null) {
                    handleResult(f, requestCode, resultCode, data);
                }
        if (childFragment == null)
            Log.e("", "MyBaseFragmentActivity1111");

    }
}
