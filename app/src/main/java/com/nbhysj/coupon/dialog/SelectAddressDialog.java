package com.nbhysj.coupon.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.AddressListAdapter;
import com.nbhysj.coupon.address.AddressCallBack;
import com.nbhysj.coupon.address.PagerSlidingTabStrip;
import com.nbhysj.coupon.address.TabOnClickListener;
import com.nbhysj.coupon.fragment.CityFragment;
import com.nbhysj.coupon.fragment.DistrictFragment;
import com.nbhysj.coupon.fragment.ProvinceFragment;
import com.nbhysj.coupon.model.response.DistrictBean;
import com.nbhysj.coupon.model.response.ProvinceBean;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.model.response.RecipientsCityBean;
import com.nbhysj.coupon.ui.RecipientInformationActivity;
import com.nbhysj.coupon.ui.RecipientListActivity;
import com.nbhysj.coupon.util.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther：hysj created at 2019/03/18
 * description：收件人地区选择弹框
 */
public class SelectAddressDialog extends DialogFragment implements AddressCallBack {
    private RecipientInformationActivity.SelectAddresFinish mSelectAddresFinish;
    private Context context;
    private View view;
    private ViewPager viewPager;
    private PagerSlidingTabStrip pagerTab;
    private FrameLayout popBg;

    private RecipientAddressResponse province;
    private RecipientsCityBean city;
    private DistrictBean district;
    private String defutText;

    private ProvinceFragment mProvinceFragment;
    private CityFragment mCityFragment;
    private DistrictFragment mDistrictFragment;
    // private TownFragment mTownFragment;
    //private AddressManager addressManager = AddressManager.newInstance();
    private List<RecipientAddressResponse> recipientAddressResponseList;
    private int mProvinceSelectPosition;
    private int mCitySelectPosition;

    public void setSelectAddresFinish(RecipientInformationActivity.SelectAddresFinish mSelectAddresFinish) {
        this.mSelectAddresFinish = mSelectAddresFinish;
    }

    public SelectAddressDialog() {

    }

    @SuppressLint("ValidFragment")
    public SelectAddressDialog(List<RecipientAddressResponse> recipientAddressResponseList) {

        this.recipientAddressResponseList = recipientAddressResponseList;
    }

   /* public static final SelectAddressDialog newInstance()
    {
        SelectAddressDialog fragment = new SelectAddressDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("recipientAddressList",(ArrayList)recipientAddressResponseList);
        fragment.setArguments(bundle);

        return fragment ;
    }*/
  /*  public SelectAddressDialog(List<RecipientAddressResponse> recipientAddressResponseList) {

      this.recipientAddressResponseList = recipientAddressResponseList;
    }*/

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

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    /* public void setAddress(String pCode, String cCode, String aCode){
         if (StringUtils.isNoEmpty(pCode) && StringUtils.isNoEmpty(cCode) && StringUtils.isNoEmpty(aCode)){
             province = addressManager.findProvinceByCode(pCode);
             city = province.findCityByCode(cCode);
             district = city.findDistrictByCode(aCode);
         }
     }
     public void setAddress(String pCode, String cCode, String aCode, String tCode){
         if (StringUtils.isNoEmpty(pCode) && StringUtils.isNoEmpty(cCode) && StringUtils.isNoEmpty(aCode)){
             province = addressManager.findProvinceByCode(pCode);
             city = province.findCityByCode(cCode);
             district = city.findDistrictByCode(aCode);
             if(StringUtils.isNoEmpty(tCode)){
                 town = district.findTownByCode(tCode);
             }
         }

     }*/
    private void initView() {
        /*Bundle bundle = getActivity().getIntent().getExtras();
        List<RecipientAddressResponse> recipientAddressResponseList = (List<RecipientAddressResponse>)bundle.getSerializable("recipientAddressList");
        System.out.print(recipientAddressResponseList);*/
        view = LayoutInflater.from(context).inflate(R.layout.layout_select_address_pop, null);
        ImageView ivClose = (ImageView) view.findViewById(R.id.ivClose);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        pagerTab = (PagerSlidingTabStrip) view.findViewById(R.id.pagerTab);
        popBg = (FrameLayout) view.findViewById(R.id.popBg);
        defutText = "请选择";
        pagerTab.setTextSize(DensityUtils.sp2px(context, 14));
        pagerTab.setSelectedColor(getResources().getColor(R.color.blue));
        pagerTab.setTextColor(getResources().getColor(R.color.regis_account_exist));

        List<View> lis = new ArrayList<View>();
        mProvinceFragment = new ProvinceFragment(context, this, recipientAddressResponseList);
        mCityFragment = new CityFragment(context, this, recipientAddressResponseList);
        mDistrictFragment = new DistrictFragment(context, this, recipientAddressResponseList);
        // mTownFragment = new TownFragment(context,this);
        lis.add(mProvinceFragment.getListview());
        lis.add(mCityFragment.getListview());
        lis.add(mDistrictFragment.getListview());
        //  lis.add(mTownFragment.getListview());
        viewPager.setAdapter(new AddressListAdapter(lis));

        String[] addres = null;
        if (province != null && city != null && district != null) {
            addres = new String[]{province.getName(), city.getName(), district.getName()};
           /* if(town != null){
                addres = new String[]{province.getName(),city.getName(),district.getName(),town.getName()};
                mProvinceFragment.setCode(province.getId());
                mCityFragment.setCode(province.getId(),city.getId());
                mDistrictFragment.setCode(province.getId(),city.getId(),district.getId());
               // mTownFragment.setCode(province.getCode(),city.getCode(),district.getCode(),town.getCode());
                viewPager.setCurrentItem(3);
                pagerTab.setTabsText(addres);
                pagerTab.setCurrentPosition(3);
            }else{*/
            mProvinceFragment.setCode(province.getId());
            mCityFragment.setCode(mProvinceSelectPosition, recipientAddressResponseList);
            mDistrictFragment.setCode(mProvinceSelectPosition, mCitySelectPosition, recipientAddressResponseList);
            viewPager.setCurrentItem(2);
            pagerTab.setTabsText(addres);
            pagerTab.setCurrentPosition(2);
            //  }
        } else {
            addres = new String[]{defutText};
            viewPager.setCurrentItem(0);
            pagerTab.setTabsText(addres);
            pagerTab.setCurrentPosition(0);
        }
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        popBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        pagerTab.setTabOnClickListener(new TabOnClickListener() {
            @Override
            public void onClick(View tab, int position) {
                if (defutText.equals(pagerTab.getTabs()[position])) {
                    return;
                }
                viewPager.setCurrentItem(position);
                String[] addres = null;
                switch (position) {
                    case 0:
                       /* if(town != null){
                            addres = new String[]{province.getName(), city.getName(),district.getName(),town.getName()};
                        }*/
                        if (district != null) {
                            addres = new String[]{province.getName(), city.getName(), district.getName(), defutText};
                        } else if (city != null) {
                            addres = new String[]{province.getName(), city.getName(), defutText};
                        } else {
                            addres = new String[]{province.getName(), defutText};
                        }
                        break;
                    case 1:
                        if (district != null) {
                            addres = new String[]{province.getName(), city.getName(), district.getName(), defutText};
                        } else {
                            addres = new String[]{province.getName(), city.getName(), defutText};
                        }
                        //mDistrictFragment.resetIndex();
                        break;
                    case 2:
                     /*   if(town != null){
                            addres = new String[]{province.getName(), city.getName(),district.getName(),town.getName()};
                        }else {
                            addres = new String[]{province.getName(), city.getName(), district.getName(), defutText};
                        }*/
                        break;
                }
                pagerTab.setTabsText(addres);
                pagerTab.setCurrentPosition(position);
            }
        });

    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //KeyBoardUtils.closeKeybord((Activity) context);
        super.show(manager, tag);
    }

    @Override
    public void selectProvince(int position, RecipientAddressResponse province) {
        mProvinceSelectPosition = position;
        String[] addres = new String[]{province.getName(), defutText};
        pagerTab.setTabsText(addres);
        pagerTab.setCurrentPosition(1);
        viewPager.setCurrentItem(1);
        if (province != this.province) {
            city = null;
            district = null;
        }
        this.province = province;

        if (province.getCityVOs().isEmpty()) {

            mSelectAddresFinish.finish(province.getName(), null, null);
            dismiss();
        } else {
            mCityFragment.setCode(position, recipientAddressResponseList);
        }
    }

    @Override
    public void selectCity(int position, RecipientsCityBean city) {
        String[] addres = new String[]{province.getName(), city.getName(), defutText};
        pagerTab.setTabsText(addres);
        pagerTab.setCurrentPosition(2);
        viewPager.setCurrentItem(2);
        if (city != this.city) {
            district = null;
        }
        this.city = city;
        mDistrictFragment.setCode(mProvinceSelectPosition, mCitySelectPosition, recipientAddressResponseList);
    }

    @Override
    public void selectDistrict(DistrictBean district) {
        String[] addres = new String[]{province.getName(), city.getName(), district.getName()};
        pagerTab.setTabsText(addres);
        //   Toast.makeText(context,province.getName()+ city.getName()+ district.getName(), Toast.LENGTH_SHORT).show();
        mSelectAddresFinish.finish(province.getName(), city.getName(), district.getName());
        dismiss();
        //判断是否有下级
     /*   List<AddressManager.Town> townList = AddressManager.newInstance().findProvinceByCode(province.getCode())
                .findCityByCode(city.getCode()).findDistrictByCode(district.getCode()).getAllTowns();*/
      /*  if(townList.size()<1){

            this.district = district;
            String[] addres = new String[]{province.getName(),city.getName(),district.getName()};
            pagerTab.setTabsText(addres);
            //mSelectAddresFinish.finish(province.getCode(),city.getCode(),district.getCode(),null);
            dismiss();
        }else{
            dismiss();
            Toast.makeText(context,province.getName()+ city.getName()+ district.getName(), Toast.LENGTH_SHORT).show();
          *//*  String[] addres = new String[]{province.getName(),city.getName(),district.getName(),defutText};
            pagerTab.setTabsText(addres);
            pagerTab.setCurrentPosition(3);
            viewPager.setCurrentItem(3);
            if(district != this.district){
                town = null;
            }
            this.district = district;*//*
         //   mTownFragment.setCode(province.getCode(),city.getCode(),district.getCode(),null);
        }*/


    }
}
