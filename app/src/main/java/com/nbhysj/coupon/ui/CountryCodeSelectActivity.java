package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.CountryCodeIndexListAdapter;
import com.nbhysj.coupon.model.response.CityBean;
import com.nbhysj.coupon.model.response.CountryCodeBean;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.indexablerv.IndexableLayout;

/**
 * @auther：hysj created on 2019/03/05
 * description：国家代号和区号选择
 */
public class CountryCodeSelectActivity extends BaseActivity {

    /*   @BindView(R.id.tb_toolbar)
       Toolbar mToolbar;
   */
    @BindView(R.id.layout_indexable)
    IndexableLayout mIndexableLayout;

    private CountryCodeIndexListAdapter mCountryCodeIndexListAdapter;

    private String countryCodeJosn;

    Gson gson = new Gson();

    List<CountryCodeBean> countryCodeList = new ArrayList<>();

    private List<CityBean> cityBeanList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_country_code_select;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
      /*  mToolbar.setNavigationIcon(R.mipmap.nav_ico_back_black);
        mToolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);*/

        ToolbarHelper.setBar(CountryCodeSelectActivity.this, "选择国家和地区", R.mipmap.icon_cancel);
        countryCodeList.clear();
        readTextFromSDcard();
    }

    @Override
    public void initData() {

        CityBean cityBean = new CityBean();
        cityBean.setName("北京");
        cityBean.setId(1);
        cityBean.setPingyin("B");
        cityBean.setOpen(0);

        CityBean cityBean1 = new CityBean();
        cityBean1.setName("上海");
        cityBean1.setId(2);
        cityBean1.setPingyin("S");
        cityBean1.setOpen(0);

        CityBean cityBean2 = new CityBean();
        cityBean2.setName("安庆");
        cityBean2.setId(3);
        cityBean2.setPingyin("A");
        cityBean2.setOpen(0);

        CityBean cityBean3 = new CityBean();
        cityBean3.setName("杭州");
        cityBean3.setId(4);
        cityBean3.setPingyin("H");
        cityBean3.setOpen(0);

        CityBean cityBean4 = new CityBean();
        cityBean4.setName("宁波");
        cityBean4.setId(5);
        cityBean4.setPingyin("N");
        cityBean4.setOpen(0);

        CityBean cityBean5 = new CityBean();
        cityBean5.setName("舟山");
        cityBean5.setId(6);
        cityBean5.setPingyin("Z");
        cityBean5.setOpen(0);

        CityBean cityBean6 = new CityBean();
        cityBean6.setName("苏州");
        cityBean6.setId(7);
        cityBean6.setPingyin("S");
        cityBean6.setOpen(0);

        CityBean cityBean7 = new CityBean();
        cityBean7.setName("无锡");
        cityBean7.setId(8);
        cityBean7.setPingyin("W");
        cityBean7.setOpen(0);

        CityBean cityBean8 = new CityBean();
        cityBean8.setName("广州");
        cityBean8.setId(9);
        cityBean8.setPingyin("G");
        cityBean8.setOpen(0);

        CityBean cityBean9 = new CityBean();
        cityBean9.setName("常州");
        cityBean9.setId(10);
        cityBean9.setPingyin("Z");
        cityBean9.setOpen(0);

        CityBean cityBean10 = new CityBean();
        cityBean10.setName("南宁");
        cityBean10.setId(11);
        cityBean10.setPingyin("N");
        cityBean10.setOpen(0);


        cityBeanList.add(cityBean);
        cityBeanList.add(cityBean1);
        cityBeanList.add(cityBean2);
        cityBeanList.add(cityBean3);
        cityBeanList.add(cityBean4);
        cityBeanList.add(cityBean5);
        cityBeanList.add(cityBean6);
        cityBeanList.add(cityBean7);
        cityBeanList.add(cityBean8);
        cityBeanList.add(cityBean9);
        cityBeanList.add(cityBean10);

        mIndexableLayout.setLayoutManager(new LinearLayoutManager(this));
        mCountryCodeIndexListAdapter = new CountryCodeIndexListAdapter(this);
        mIndexableLayout.setAdapter(mCountryCodeIndexListAdapter);
        mCountryCodeIndexListAdapter.setDatas(cityBeanList);
        mIndexableLayout.setOverlayStyle_MaterialDesign(getResources().getColor(R.color.color_green2));
        mIndexableLayout.setCompareMode(IndexableLayout.MODE_FAST);

        List<String> bannerList = new ArrayList<>();
        bannerList.add("");
        // mCityIndexableHeaderAdapter = new CityIndexableHeaderAdapter("热门", null, bannerList);
        // indexableLayout.addHeaderAdapter(mCityIndexableHeaderAdapter);
    }

    @Override
    public void initPresenter() {

    }


    //将传入的is一行一行解析读取出来出来
    private void readTextFromSDcard() {
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(getAssets().open("countryCode.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            inputStreamReader.close();
            bufferedReader.close();
            countryCodeJosn = stringBuilder.toString();

            HashMap<String, List<CountryCodeBean>> hashMap = JSON.parseObject(countryCodeJosn, HashMap.class);

            getListByMap(hashMap);
            Log.i("TAG", stringBuilder.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据map返回key和value的list
     *
     * @param map true为key,false为value
     * @return
     */
    public List<CountryCodeBean> getListByMap(HashMap<String, List<CountryCodeBean>> map) {

        List<CountryCodeBean> list = new ArrayList<CountryCodeBean>();

        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            List<CountryCodeBean> countryCodeBeanList = map.get(key);
            countryCodeList.addAll(countryCodeBeanList);
        }

        return list;
    }
}
