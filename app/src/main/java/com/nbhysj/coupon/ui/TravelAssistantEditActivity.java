package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MyBusinessCardOprateAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.request.DeleteTripPlaceRequest;
import com.nbhysj.coupon.model.request.DeleteTripRequest;
import com.nbhysj.coupon.model.request.EditTripSubmitRequest;
import com.nbhysj.coupon.model.response.AddCountyResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.model.response.WeatherResponse;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.nbhysj.coupon.fragment.TravelAssistantFragment.TRIP_EDIT_RESULT_CODE;

/**
 * @auther：hysj created on 2019/06/17
 * description：行程助手编辑
 */
public class TravelAssistantEditActivity extends BaseActivity<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View {

    //行程助手标题
    @BindView(R.id.edt_travel_assistant_title)
    EditText mEdtTravelAssistantTitle;

    //出游时间
    @BindView(R.id.tv_travel_start_date)
    TextView mTvTravelStartDate;

    //结束时间
    @BindView(R.id.tv_travel_end_date)
    TextView mTvTravelEndDate;

    //景点照片
    @BindView(R.id.image_scenic_spots)
    RoundedImageView mRoundImageScenicSpots;

    private OprateDialog oprateDialog;

    private TripHomePageResponse.TripEntity tripEntity;

    //行程助手开始时间
    private String mStartDate;

    //行程助手结束时间
    private String mEndDate;

    //行程助手标题
    private String travelAssistantTitle;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_travel_assisant_edit;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        //沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        BlurBehind.getInstance()
                .withAlpha(90)
                .withFilterColor(Color.parseColor("#000000"))
                .setBackground(TravelAssistantEditActivity.this);

      /*  ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }*/



    }

    @Override
    public void initData() {
       // TripHomePageResponse.TripEntity
        tripEntity = ( TripHomePageResponse.TripEntity)getIntent().getSerializableExtra("tripEntity");
        String photoUrl = tripEntity.getPhoto();
        String startPlace = tripEntity.getStartPlace();
        String endPlace = tripEntity.getEndPlace();
        mStartDate = tripEntity.getStartDate();
        mEndDate = tripEntity.getEndDate();
        String title = tripEntity.getTitle();
        GlideUtil.loadImage(TravelAssistantEditActivity.this,photoUrl,mRoundImageScenicSpots);

        mTvTravelStartDate.setText(mStartDate);
        mTvTravelEndDate.setText(mEndDate);

        mEdtTravelAssistantTitle.setText(title);
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
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
    public void getTripDetailsResult(BackResult<TripDetailsResponse> res) {

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
    public void getTripRouteMapResult(BackResult res) {

    }

    @Override
    public void getWeatherResult(BackResult<WeatherResponse> res) {

    }

    @Override
    public void intelligentProjectResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void insertTrafficResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void insertCountyResult(BackResult<AddCountyResponse> res) {

    }

    @Override
    public void delTripResult(BackResult res) {

        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    setResult(RESULT_OK);
                    TravelAssistantEditActivity.this.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
                default:
                    break;
        }

    }
    @Override
    public void updateTripInformationResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    tripEntity.setStartDate(mStartDate);
                    tripEntity.setEndDate(mEndDate);
                    tripEntity.setTitle(travelAssistantTitle);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tripEntity",tripEntity);
                    intent.putExtras(bundle);
                    setResult(TRIP_EDIT_RESULT_CODE,intent);
                    TravelAssistantEditActivity.this.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(TravelAssistantEditActivity.this,Constants.getResultMsg(msg));
    }

    @OnClick({R.id.tv_travel_start_date,R.id.tv_travel_end_date,R.id.llyt_my_travel_detail_item,R.id.img_travel_assistant_edit_close,R.id.tv_delete_trip,R.id.tv_save_travel_assistant})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.tv_travel_start_date:
                intent.putExtra("selectType",1);
                intent.setClass(TravelAssistantEditActivity.this,TripCalendarActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.tv_travel_end_date:
                intent.putExtra("selectType",1);
                intent.setClass(TravelAssistantEditActivity.this,TripCalendarActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.llyt_my_travel_detail_item:

                TravelAssistantEditActivity.this.finish();

                break;
            case R.id.img_travel_assistant_edit_close:

                TravelAssistantEditActivity.this.finish();

                break;
            case R.id.tv_delete_trip:

                if(oprateDialog == null){
                    oprateDialog = new OprateDialog(TravelAssistantEditActivity.this).builder().setTitle("确认要删除该行程吗");
                    oprateDialog.setNegativeButton(getResources().getString(R.string.str_cancel), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });

                    oprateDialog.setPositiveButton("确认", getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int tripPlaceId = tripEntity.getId();
                            showProgressDialog(TravelAssistantEditActivity.this);
                            mDialog.setTitle("正在删除...");
                            delTripPlace(tripPlaceId);
                        }
                    });
                }

                oprateDialog.show();
                break;
            case R.id.tv_save_travel_assistant:
                int tripPlaceId = tripEntity.getId();
                saveTrip(tripPlaceId);
                break;
                default:break;
        }
    }

    //保存行程
    public void saveTrip(int tripPlaceId) {

        if (validateInternet()) {

            showProgressDialog(TravelAssistantEditActivity.this);
            mDialog.setTitle("正在保存...");
            travelAssistantTitle = mEdtTravelAssistantTitle.getText().toString().trim();
            if(!TextUtils.isEmpty(travelAssistantTitle)) {
                EditTripSubmitRequest editTripSubmitRequest = new EditTripSubmitRequest();
                editTripSubmitRequest.setTripId(tripPlaceId);
                editTripSubmitRequest.setTitle(travelAssistantTitle);
                editTripSubmitRequest.setStartDate(mStartDate);
                editTripSubmitRequest.setEndDate(mEndDate);
                mPresenter.updateTripInformation(editTripSubmitRequest);
            } else {

                showToast(TravelAssistantEditActivity.this,"请输入行程标题");
            }
        }
    }

    //删除行程
    public void delTripPlace(int tripPlaceId) {

        if (validateInternet()) {
            showProgressDialog(TravelAssistantEditActivity.this);
            DeleteTripRequest deleteTripRequest = new DeleteTripRequest();
            deleteTripRequest.setId(tripPlaceId);
            mPresenter.delTrip(deleteTripRequest);

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
            mStartDate = startDate;
            mEndDate = endDate;
            mTvTravelStartDate.setText(mStartDate);
            mTvTravelEndDate.setText(mEndDate);
        }
    }
}
