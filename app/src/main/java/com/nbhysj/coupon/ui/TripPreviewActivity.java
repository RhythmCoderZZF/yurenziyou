package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.TravelPreviewAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.fragment.TravelAssisantDetailFragment;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TravelPreviewBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripRouteMapResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.model.response.WeatherResponse;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.widget.TripAssistantIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/09
 * description：行程预览
 */
public class TripPreviewActivity extends BaseActivity<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View {

    @BindView(R.id.rv_trip_preview)
    RecyclerView mRvTripPreview;

    //行程预览关闭
    @BindView(R.id.img_trip_preview_close)
    ImageView mImgTripPreviewClose;

    private List<TripDetailsResponse.DetailsEntity> travelPreviewList;
    private List<TravelPreviewBean.TravelPreviewEntity> travelPreviewEntityList;

    //行程点id
    private int mTripId;

    private TravelPreviewAdapter travelPreviewAdapter;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_trip_preview;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        mTripId = getIntent().getIntExtra("tripId", 0);

        if (travelPreviewList == null) {

            travelPreviewList = new ArrayList<>();
        } else {
            travelPreviewList.clear();
        }

        if (travelPreviewEntityList == null) {

            travelPreviewEntityList = new ArrayList<>();
        } else {
            travelPreviewEntityList.clear();
        }

     /*   TravelPreviewBean.TravelPreviewEntity travelPreviewEntity = new TravelPreviewBean().new TravelPreviewEntity();
        travelPreviewEntity.setDestination("四明湖红杉林");
        travelPreviewEntity.setTime("入住3天");
        TravelPreviewBean.TravelPreviewEntity travelPreviewEntity1 = new TravelPreviewBean().new TravelPreviewEntity();
        travelPreviewEntity1.setDestination("四明湖红杉林1");
        travelPreviewEntity1.setTime("2小时");
        TravelPreviewBean.TravelPreviewEntity travelPreviewEntity7 = new TravelPreviewBean().new TravelPreviewEntity();
        travelPreviewEntity7.setDestination("四明湖红杉林7");
        travelPreviewEntity7.setTime("入住3天");
        TravelPreviewBean.TravelPreviewEntity travelPreviewEntity8 = new TravelPreviewBean().new TravelPreviewEntity();
        travelPreviewEntity8.setDestination("四明湖红杉林8");
        travelPreviewEntity8.setTime("2小时");
        travelPreviewEntityList.add(travelPreviewEntity);
        travelPreviewEntityList.add(travelPreviewEntity1);
        travelPreviewEntityList.add(travelPreviewEntity7);
        travelPreviewEntityList.add(travelPreviewEntity8);


        List<TravelPreviewBean.TravelPreviewEntity> travelPreviewEntityList1 = new ArrayList<>();

        TravelPreviewBean.TravelPreviewEntity travelPreviewEntity2 = new TravelPreviewBean().new TravelPreviewEntity();
        travelPreviewEntity2.setDestination("四明湖红杉林");
        travelPreviewEntity2.setTime("入住3天");
        TravelPreviewBean.TravelPreviewEntity travelPreviewEntity3 = new TravelPreviewBean().new TravelPreviewEntity();
        travelPreviewEntity3.setDestination("四明湖红杉林1");
        travelPreviewEntity3.setTime("2小时");
        travelPreviewEntityList1.add(travelPreviewEntity2);
        travelPreviewEntityList1.add(travelPreviewEntity3);

        List<TravelPreviewBean.TravelPreviewEntity> travelPreviewEntityList2 = new ArrayList<>();

        TravelPreviewBean.TravelPreviewEntity travelPreviewEntity4 = new TravelPreviewBean().new TravelPreviewEntity();
        travelPreviewEntity4.setDestination("四明湖红杉林");
        travelPreviewEntity4.setTime("入住3天");
        TravelPreviewBean.TravelPreviewEntity travelPreviewEntity5 = new TravelPreviewBean().new TravelPreviewEntity();
        travelPreviewEntity5.setDestination("四明湖红杉林5");
        travelPreviewEntity5.setTime("2小时");
        travelPreviewEntityList2.add(travelPreviewEntity4);
        travelPreviewEntityList2.add(travelPreviewEntity5);

        TravelPreviewBean travelPreviewBean = new TravelPreviewBean();
        travelPreviewBean.setDate("2019.3.19 星期二");
        travelPreviewBean.setTravelPreviewEntityList(travelPreviewEntityList);


        TravelPreviewBean travelPreviewBean1 = new TravelPreviewBean();
        travelPreviewBean1.setDate("2019.3.20 星期三");
        travelPreviewBean1.setTravelPreviewEntityList(travelPreviewEntityList1);


        TravelPreviewBean travelPreviewBean2 = new TravelPreviewBean();
        travelPreviewBean2.setDate("2019.3.21 星期四");
        travelPreviewBean2.setTravelPreviewEntityList(travelPreviewEntityList2);

        travelPreviewList.add(travelPreviewBean);
        travelPreviewList.add(travelPreviewBean1);
        travelPreviewList.add(travelPreviewBean2);*/

        LinearLayoutManager userCommentLayoutManager = new LinearLayoutManager(TripPreviewActivity.this);
        userCommentLayoutManager.setOrientation(userCommentLayoutManager.VERTICAL);
        mRvTripPreview.setLayoutManager(userCommentLayoutManager);
        travelPreviewAdapter = new TravelPreviewAdapter(TripPreviewActivity.this);
        travelPreviewAdapter.setTravelPreviewList(travelPreviewList);
        mRvTripPreview.setAdapter(travelPreviewAdapter);
    }

    @Override
    public void initData() {

        getTripPreviewList();
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
    public void getWeatherResult(BackResult<WeatherResponse> res) {

    }

    @Override
    public void insertTrafficResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void getTripDetailsResult(BackResult<TripDetailsResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:

                TripDetailsResponse tripDetailsResponse = res.getData();

                List<TripDetailsResponse.DetailsEntity> detailsList = tripDetailsResponse.getDetails();

                if (detailsList.size() > 0) {

                    //  detailsList
                    travelPreviewAdapter.setTravelPreviewList(detailsList);
                    travelPreviewAdapter.notifyDataSetChanged();
                }

                break;
            default:
                showToast(TripPreviewActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getTravelAssistantMchListResult(BackResult<TripScenicSpotAddCountryBean> res) {

    }

    @Override
    public void insertPlaceMchResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void travelAssistantPlusADay(BackResult res) {

    }

    @Override
    public void insertNoteResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void delTripPlaceResult(BackResult res) {

    }

    @Override
    public void delTripResult(BackResult res) {

    }

    @Override
    public void updateTripInformationResult(BackResult res) {

    }

    @Override
    public void getTripRouteMapResult(BackResult<TripRouteMapResponse> res) {

    }

    @Override
    public void intelligentProjectResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(TripPreviewActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.img_trip_preview_close})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_trip_preview_close:

                TripPreviewActivity.this.finish();

                break;
            default:
                break;
        }

    }

    public void getTripPreviewList() {

        if (validateInternet()) {
            showProgressDialog(TripPreviewActivity.this);
            mPresenter.getTripDetails(mTripId);
        }
    }
}
