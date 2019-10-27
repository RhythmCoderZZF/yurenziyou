package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.nbhysj.coupon.BasicApplication;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.HomeSearchMchTypeEnum;
import com.nbhysj.coupon.fragment.HomeSearchComprehensiveFragment;
import com.nbhysj.coupon.fragment.HomeSearchFineFoodFragment;
import com.nbhysj.coupon.fragment.HomeSearchHotelFragment;
import com.nbhysj.coupon.fragment.HomeSearchPostsFragment;
import com.nbhysj.coupon.fragment.HomeSearchRecreationFragment;
import com.nbhysj.coupon.fragment.HomeSearchScenicSpotsFragment;
import com.nbhysj.coupon.fragment.HomeSearchStrategyFragment;
import com.nbhysj.coupon.fragment.MyAllOrderListFragment;
import com.nbhysj.coupon.fragment.MyOrderFragmentManager;
import com.nbhysj.coupon.fragment.PendingCommentOrderListFragment;
import com.nbhysj.coupon.fragment.PendingPaymentListFragment;
import com.nbhysj.coupon.fragment.PendingTravelListFragment;
import com.nbhysj.coupon.fragment.RefundOrderListFragment;
import com.nbhysj.coupon.greendao.DaoSession;
import com.nbhysj.coupon.model.response.HomeSearchComprehensiveBean;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.widget.HomePageSearchIndicator;

import net.lucode.hackware.magicindicator.MagicIndicator;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author hysj created at 2019/07/31.
 * description : 首页搜索
 */
public class HomePageSearchActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.edt_home_page_search)
    EditText mEdtHomePageSearch;

    private String[] titles = new String[]{"综合", "XIU", "住宿", "景点", "美食", "互动", "攻略"};
    private List<Fragment> fragmentList;
    private boolean isExistSearchContent = false;
    @Override
    public int getLayoutId()
    {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_home_page_search;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        if(fragmentList == null){

            fragmentList = new ArrayList<>();
        } else {

            fragmentList.clear();
        }

        fragmentList.add(new HomeSearchComprehensiveFragment());
        fragmentList.add(new HomeSearchPostsFragment());
        fragmentList.add(new HomeSearchHotelFragment());
        fragmentList.add(new HomeSearchScenicSpotsFragment());
        fragmentList.add(new HomeSearchFineFoodFragment());
        fragmentList.add(new HomeSearchRecreationFragment());
        fragmentList.add(new HomeSearchStrategyFragment());

        FragmentPagerAdapter adapter = new MyOrderFragmentManager(getSupportFragmentManager(), titles,fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager, titles);
    }

    @Override
    public void initData() {

        mEdtHomePageSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 按下完成按钮，这里和上面imeOptions对应
                    String searchKeyWordStr = textView.getText().toString().trim();

                        addSearchRecordData(searchKeyWordStr);
                        EventBus.getDefault().post(searchKeyWordStr);
                        SharedPreferencesUtils.putData(SharedPreferencesUtils.SEARCH_KEYWORD,searchKeyWordStr);
                    return false;   //返回true，保留软键盘。false，隐藏软键盘
                }

                return false;   //返回true，保留软键盘。false，隐藏软键盘
            }
        });

        mEdtHomePageSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchKeyWordStr = charSequence.toString().trim();
                if (TextUtils.isEmpty(searchKeyWordStr))
                {
                    SharedPreferencesUtils.putData(SharedPreferencesUtils.SEARCH_KEYWORD,searchKeyWordStr);
                    EventBus.getDefault().post(searchKeyWordStr);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void initPresenter() {

    }

    private void addSearchRecordData(String keyword)
    {
        List<HomeSearchComprehensiveBean> homeSearchComprehensiveList = queryAll();
        for (HomeSearchComprehensiveBean homeSearchComprehensiveBean:homeSearchComprehensiveList){

           String searchRecord = homeSearchComprehensiveBean.getSearch();
           if(!searchRecord.equals(keyword))
           {
               isExistSearchContent = true;
           }
        }

        //是否存在搜索内容 不存在的情况
        if(!isExistSearchContent)
        {
            if(homeSearchComprehensiveList != null && homeSearchComprehensiveList.size() > 4)
            {
                HomeSearchComprehensiveBean homeSearchComprehensiveBean = homeSearchComprehensiveList.get(homeSearchComprehensiveList.size() - 1);
                homeSearchComprehensiveList.remove(homeSearchComprehensiveBean);
            }
            DaoSession daoSession = ((BasicApplication) getApplication()).getDaoSession();
            HomeSearchComprehensiveBean searchBean = new HomeSearchComprehensiveBean();
            searchBean.setSearch(keyword);
            daoSession.insert(searchBean);
        }
    }

    //查询全部搜索记录
    public List<HomeSearchComprehensiveBean> queryAll() {
        List<HomeSearchComprehensiveBean> searchs = ((BasicApplication)getApplication()).getDaoSession().loadAll(HomeSearchComprehensiveBean.class);
        return searchs;
    }

    @OnClick({R.id.tv_cancel_search})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_cancel_search:
                HomePageSearchActivity.this.finish();
                break;
                default:break;
        }
    }
}
