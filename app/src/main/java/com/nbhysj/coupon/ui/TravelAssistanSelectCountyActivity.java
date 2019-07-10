package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.SelectedDestinationTagAdapter;
import com.nbhysj.coupon.adapter.TravelAssistantDestionationsListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.TravelAssistantContract;
import com.nbhysj.coupon.model.TravelAssistantModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.presenter.TravelAssistantPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/09
 * description：行程助手选择区县
 */
public class TravelAssistanSelectCountyActivity extends BaseActivity<TravelAssistantPresenter, TravelAssistantModel> implements TravelAssistantContract.View {
    //已选目的地标签
    @BindView(R.id.rv_selected_destionation)
    RecyclerView mRvSelectedDestionation;
    //已选目的地标签
    @BindView(R.id.rv_select_interest_destionation)
    RecyclerView mRvSelectInterestDestionation;
    //已选目的地
    @BindView(R.id.tv_selected_destionation)
    TextView mTvSelectedDestionation;
    @BindView(R.id.edt_search_destination)
    EditText mEdtSearchDestination;
    List<TravelAssistantDetailCountryBean> selectedDestinationTag;
    List<TravelAssistantDetailCountryBean> destionationsList;
    //已选目的地标签列表
    private SelectedDestinationTagAdapter selectedDestinationTagAdapter;
    //兴趣目的地列表
    private TravelAssistantDestionationsListAdapter selectDestionationsListAdapter;
    List<TravelAssistantDetailCountryBean> searchDestionationsList;
    private String startDate, endDate;


    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_select_destination;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");

        if (selectedDestinationTag == null) {

            selectedDestinationTag = new ArrayList<TravelAssistantDetailCountryBean>();
        } else {
            selectedDestinationTag.clear();
        }

        if (destionationsList == null) {

            destionationsList = new ArrayList<TravelAssistantDetailCountryBean>();
        } else {
            destionationsList.clear();
        }

        if (searchDestionationsList == null) {

            searchDestionationsList = new ArrayList<TravelAssistantDetailCountryBean>();
        } else {
            searchDestionationsList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TravelAssistanSelectCountyActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvSelectedDestionation.setLayoutManager(linearLayoutManager);
        selectedDestinationTagAdapter = new SelectedDestinationTagAdapter(TravelAssistanSelectCountyActivity.this, new SelectedDestinationTagAdapter.SelectedDestionationListener() {
            @Override
            public void setSelectedDestionationListener(TravelAssistantDetailCountryBean selectDestionation) {

                for (int i = 0; i < destionationsList.size(); i++) {

                    TravelAssistantDetailCountryBean selectDestionationsBean = destionationsList.get(i);
                    String destionation = selectDestionationsBean.getCountyName();
                    String selectDestionationName = selectDestionation.getCountyName();
                    if (destionation.equals(selectDestionationName)) {

                        selectDestionationsBean.setSelect(false);
                    }
                }

                selectDestionationsListAdapter.setSelectDestionationList(destionationsList);
                selectDestionationsListAdapter.notifyDataSetChanged();

                selectedDestinationTag.remove(selectDestionation);
                selectedDestinationTagAdapter.setSelectedDestinationTagList(selectedDestinationTag);
                selectedDestinationTagAdapter.notifyDataSetChanged();

                if (selectedDestinationTag.size() > 0) {

                    mTvSelectedDestionation.setVisibility(View.VISIBLE);
                } else {
                    mTvSelectedDestionation.setVisibility(View.GONE);
                }
            }
        });
        selectedDestinationTagAdapter.setSelectedDestinationTagList(selectedDestinationTag);
        mRvSelectedDestionation.setAdapter(selectedDestinationTagAdapter);

        LinearLayoutManager selectDestionatLinearLayoutManager = new LinearLayoutManager(TravelAssistanSelectCountyActivity.this);
        selectDestionatLinearLayoutManager.setOrientation(selectDestionatLinearLayoutManager.VERTICAL);
        mRvSelectInterestDestionation.setLayoutManager(selectDestionatLinearLayoutManager);
        selectDestionationsListAdapter = new TravelAssistantDestionationsListAdapter(TravelAssistanSelectCountyActivity.this, new TravelAssistantDestionationsListAdapter.DestionationSelectListener() {
            @Override
            public void setDestionationSelectListener(TravelAssistantDetailCountryBean selectDestionation) {

                selectedDestinationTag.add(selectDestionation);
                if (selectedDestinationTag.size() > 0) {

                    mTvSelectedDestionation.setVisibility(View.VISIBLE);
                } else {
                    mTvSelectedDestionation.setVisibility(View.GONE);
                }
                selectedDestinationTagAdapter.setSelectedDestinationTagList(selectedDestinationTag);
                selectedDestinationTagAdapter.notifyDataSetChanged();

            }
        });
        selectDestionationsListAdapter.setSelectDestionationList(destionationsList);
        mRvSelectInterestDestionation.setAdapter(selectDestionationsListAdapter);
    }

    @Override
    public void initData() {

        getTravelAssistanSelectCounty(0);
        mEdtSearchDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                try {

                    searchDestionationsList.clear();
                    String searchDestination = charSequence.toString().trim();
                    if (TextUtils.isEmpty(searchDestination)) {

                        searchDestionationsList.addAll(destionationsList);
                    } else {
                        for (int j = 0; j < destionationsList.size(); j++) {
                            TravelAssistantDetailCountryBean destionationsBean = destionationsList.get(j);
                            String destinationName = destionationsBean.getCountyName();
                            if (destinationName.contains(searchDestination)) {

                                searchDestionationsList.add(destionationsBean);
                            }
                        }

                    }

                    if (searchDestionationsList.size() == 0) {

                        showToast(TravelAssistanSelectCountyActivity.this, "无搜索数据");
                    }

                    selectDestionationsListAdapter.setSelectDestionationList(searchDestionationsList);
                    selectDestionationsListAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
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
    public void getCreateTripResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void getTripDetailsResult(BackResult<TripDetailsResponse> res) {

    }

    @Override
    public void insertPlaceMchResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void delTripPlaceResult(BackResult res) {

    }

    @Override
    public void insertNoteResult(BackResult<CreateTripResponse> res) {

    }

    @Override
    public void getCountyWebListResult(BackResult<List<TravelAssistantDetailCountryBean>> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    List<TravelAssistantDetailCountryBean> countrySelectList = res.getData();
                    destionationsList.addAll(countrySelectList);
                    if (destionationsList != null) {
                        selectDestionationsListAdapter.setSelectDestionationList(destionationsList);
                        selectDestionationsListAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(TravelAssistanSelectCountyActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getTravelAssistantMchListResult(BackResult<TripScenicSpotAddCountryBean> res) {

    }
    @Override
    public void travelAssistantPlusADay(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(TravelAssistanSelectCountyActivity.this, Constants.getResultMsg(msg));
    }

    public void getTravelAssistanSelectCounty(int tripId) {
        if (validateInternet()) {

            HashMap<String, String> map = new HashMap<>();
            if (tripId != 0) {
                map.put("tripId", String.valueOf(tripId));
            }
            mPresenter.getCountyWebList(map);
        }
    }


    @OnClick({R.id.rlyt_back, R.id.tv_country_select})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlyt_back:

                TravelAssistanSelectCountyActivity.this.finish();

                break;

            case R.id.tv_country_select:


                ArrayList<Integer> selectedCountryTagList = selectedDestinationTagAdapter.getSelectedCountryTagList();

                if (selectedCountryTagList.size() > 0) {
                    Intent intent = new Intent();
                    intent.putExtra("startDate", startDate);
                    intent.putExtra("endDate", endDate);
                    intent.putIntegerArrayListExtra("countyIdList", selectedCountryTagList);
                    intent.setClass(TravelAssistanSelectCountyActivity.this, TravelPlanningActivity.class);
                    startActivity(intent);
                } else {

                    showToast(TravelAssistanSelectCountyActivity.this, "请选择城市");
                }
                break;
            default:
                break;
        }
    }

}
