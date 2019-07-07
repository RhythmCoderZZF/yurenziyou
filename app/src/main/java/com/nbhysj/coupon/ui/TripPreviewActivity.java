package com.nbhysj.coupon.ui;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.TravelPreviewAdapter;
import com.nbhysj.coupon.model.response.TravelPreviewBean;
import com.nbhysj.coupon.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/05/09
 * description：行程预览
 */
public class TripPreviewActivity extends BaseActivity {

    @BindView(R.id.rv_trip_preview)
    RecyclerView mRvTripPreview;
    private List<TravelPreviewBean> travelPreviewList;
    private List<TravelPreviewBean.TravelPreviewEntity> travelPreviewEntityList;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_trip_preview;
    }

    @Override
    public void initView(Bundle savedInstanceState) {


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

        TravelPreviewBean.TravelPreviewEntity travelPreviewEntity = new TravelPreviewBean().new TravelPreviewEntity();
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
        travelPreviewList.add(travelPreviewBean2);

        LinearLayoutManager userCommentLayoutManager = new LinearLayoutManager(TripPreviewActivity.this);
        userCommentLayoutManager.setOrientation(userCommentLayoutManager.VERTICAL);
        mRvTripPreview.setLayoutManager(userCommentLayoutManager);
        TravelPreviewAdapter travelPreviewAdapter = new TravelPreviewAdapter(TripPreviewActivity.this);
        travelPreviewAdapter.setTravelPreviewList(travelPreviewList);
        mRvTripPreview.setAdapter(travelPreviewAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
