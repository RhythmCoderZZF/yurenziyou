package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.InputTipsAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/08/26
 * description：用车地址选择
 */
public class VehicleAddressSelectionActivity extends BaseActivity implements TextWatcher, Inputtips.InputtipsListener, AdapterView.OnItemClickListener {

    //用车POI搜索地点
    @BindView(R.id.rv_vehicle_use_address)
    RecyclerView mRvVehicleUseAddress;

    //用车地址输入搜索
    @BindView(R.id.edt_vehicle_address)
    EditText mEdtVehicleAddress;

    private List<Tip> mCurrentTipList;

    private InputTipsAdapter mIntipAdapter;

    public static final int RESULT_CODE_INPUTTIPS = 101;
    public static final int RESULT_CODE_KEYWORDS = 102;

    @Override
    public int getLayoutId() {
        return R.layout.activity_vehicle_use_address_selection;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(VehicleAddressSelectionActivity.this, getResources().getString(R.string.str_address_select), R.mipmap.icon_left_arrow_black);
        initSearchView();
    }

    @Override
    public void initData() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VehicleAddressSelectionActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvVehicleUseAddress.setLayoutManager(linearLayoutManager);
    }

    private void initSearchView() {
        mEdtVehicleAddress.addTextChangedListener(this);
        //设置SearchView默认为展开显示
      /*  mSearchViewVehicleAddress.setIconified(false);
        mSearchViewVehicleAddress.onActionViewExpanded();
        mSearchViewVehicleAddress.setIconifiedByDefault(true);
        mSearchViewVehicleAddress.setSubmitButtonEnabled(false);*/
    }

    /**
     * 输入提示回调
     *
     * @param tipList
     * @param rCode
     */
    @Override
    public void onGetInputtips(List<Tip> tipList, int rCode) {

        if (rCode == 1000) {// 正确返回
            mCurrentTipList = tipList;
            List<String> listString = new ArrayList<String>();
            for (int i = 0; i < tipList.size(); i++) {
                listString.add(tipList.get(i).getName());
            }
            mIntipAdapter = new InputTipsAdapter(
                    getApplicationContext(),
                    mCurrentTipList, new InputTipsAdapter.InputtipsListener() {
                @Override
                public void setVichicleUseAddressSelect(Tip tip) {

                    Intent intent = new Intent();
                    intent.putExtra("tip",tip);
                    setResult(RESULT_OK,intent);
                    VehicleAddressSelectionActivity.this.finish();
                }
            });
            mRvVehicleUseAddress.setAdapter(mIntipAdapter);
            mIntipAdapter.notifyDataSetChanged();
        } else {
            // mRvVehicleUseAddress.showerror(this, rCode);
        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String newText = charSequence.toString().trim();
        if (!IsEmptyOrNullString(newText)) {
            InputtipsQuery inputquery = new InputtipsQuery(newText, Constants.DEFAULT_CITY);
            Inputtips inputTips = new Inputtips(VehicleAddressSelectionActivity.this.getApplicationContext(), inputquery);
            inputTips.setInputtipsListener(this);
            inputTips.requestInputtipsAsyn();
        } else {
            if (mIntipAdapter != null && mCurrentTipList != null) {
                mCurrentTipList.clear();
                mIntipAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

   /* @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_WORDS_NAME, query);
        setResult(RESULT_CODE_KEYWORDS, intent);
        this.finish();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

    }*/

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (mCurrentTipList != null) {
            Tip tip = (Tip) adapterView.getItemAtPosition(i);
            Intent intent = new Intent();
            intent.putExtra(Constants.EXTRA_TIP, tip);
            setResult(RESULT_CODE_INPUTTIPS, intent);
            this.finish();
        }
    }

    public static boolean IsEmptyOrNullString(String s) {
        return (s == null) || (s.trim().length() == 0);
    }

    @Override
    public void initPresenter() {

      //  mPresenter.setVM(this, mModel);
    }
}
