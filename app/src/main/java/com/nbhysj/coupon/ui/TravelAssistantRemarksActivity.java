package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.request.AddRemarksRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/09
 * description：行程助手备注
 */
public class TravelAssistantRemarksActivity extends BaseActivity<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View {

    @BindView(R.id.edt_remarks)
    EditText mEdtRemarks;
    @BindView(R.id.tv_remarks_length)
    TextView mTvRemarksLength;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    //天数索引
    private int mDayIndex;
    //行程Id
    private int mTripId;

    //判断是添加备注||编辑备注
    private boolean isAddRemarks;

    //备注
    private String remarks;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_travel_assistant_remarks;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(TravelAssistantRemarksActivity.this, getResources().getString(R.string.str_remarks), R.mipmap.icon_left_arrow_black);
    }

    @Override
    public void initData() {

        String remarks = getIntent().getStringExtra("remarks");
        mTripId = getIntent().getIntExtra("tripId", 0);
        mDayIndex = getIntent().getIntExtra("dayIndex", 0);
        isAddRemarks = getIntent().getBooleanExtra("isAddRemarks", false); //判断是添加备注||编辑备注
        if (!TextUtils.isEmpty(remarks)) {
            mEdtRemarks.setText(remarks);
            mTvRemarksLength.setText(remarks.length() + "/50");
            checkIsInputFill();
        }
        mEdtRemarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int profileLength = charSequence.length();
                mTvRemarksLength.setText(profileLength + "/50");
                checkIsInputFill();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
    public void getTripDetailsResult(BackResult<TripDetailsResponse> res) {

    }

    @Override
    public void getTravelAssistantMchListResult(BackResult<TripScenicSpotAddCountryBean> res) {

    }

    @Override
    public void insertPlaceMchResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void delTripPlaceResult(BackResult res) {

    }

    @Override
    public void insertNoteResult(BackResult<CreateTripResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    if (isAddRemarks) {

                        showToast(TravelAssistantRemarksActivity.this, "备注添加成功");

                    } else {
                        showToast(TravelAssistantRemarksActivity.this, "备注编辑成功");
                    }

                    Intent intent = new Intent();
                    intent.putExtra("remarks", remarks);
                    setResult(RESULT_OK, intent);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(TravelAssistantRemarksActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void travelAssistantPlusADay(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(TravelAssistantRemarksActivity.this, Constants.getResultMsg(msg));
    }


    //校验输入信息是否填写完成
    public void checkIsInputFill() {

        String remarks = mEdtRemarks.getText().toString().trim();
        if (TextUtils.isEmpty(remarks)) {

            mTvSave.setBackgroundResource(R.drawable.bg_rect_gray_shape);
        } else {
            mTvSave.setBackgroundResource(R.drawable.btn_oprate_bg);
        }
    }


    @OnClick({R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                String remarks = mEdtRemarks.getText().toString().trim();
                if (!TextUtils.isEmpty(remarks)) {
                    addTravelAssistantRemarks();
                } else {

                    showToast(TravelAssistantRemarksActivity.this, "请填写备注");
                }
                break;

            default:
                break;

        }
    }

    public void addTravelAssistantRemarks() {

        if (validateInternet()) {
            remarks = mEdtRemarks.getText().toString().trim();
            AddRemarksRequest addRemarksRequest = new AddRemarksRequest();
            addRemarksRequest.setNote(remarks);
            addRemarksRequest.setDayIndex(mDayIndex);
            addRemarksRequest.setTripId(mTripId);
            mPresenter.insertRemarks(addRemarksRequest);
        }

    }
}
