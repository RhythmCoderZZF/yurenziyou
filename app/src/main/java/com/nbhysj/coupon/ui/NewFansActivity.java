package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.NewFansListAdapter;
import com.nbhysj.coupon.model.response.NewFansBean;
import com.nbhysj.coupon.model.response.SupportedUserBean;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/02
 * description：新增粉丝
 */
public class NewFansActivity extends BaseActivity {
    @BindView(R.id.rv_new_fans)
    RecyclerView mRvNewFansList;
    //新增粉丝
    private List<NewFansBean> newFansList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_fans;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(NewFansActivity.this, getResources().getString(R.string.str_new_fans), R.mipmap.nav_ico_back_black);
        if (newFansList == null) {

            newFansList = new ArrayList<>();

        } else {
            newFansList.clear();
        }

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(NewFansActivity.this);
        // 设置布局管理器
        mRvNewFansList.setLayoutManager(layoutManager);

        NewFansBean newFansBean = new NewFansBean();
        newFansBean.setUrl("http://img.duoziwang.com/2016/12/17/16303361286.jpg");
        newFansBean.setTime("2019-03-21");
        newFansBean.setTitle("张星星");
        newFansBean.setReturnPowderStatus(false);

        NewFansBean newFansBean1 = new NewFansBean();
        newFansBean1.setUrl("http://img.duoziwang.com/2016/12/17/16303361286.jpg");
        newFansBean1.setTime("2019-03-21");
        newFansBean1.setTitle("王建立");
        newFansBean1.setReturnPowderStatus(false);

        NewFansBean newFansBean2 = new NewFansBean();
        newFansBean2.setUrl("http://img.duoziwang.com/2016/12/17/16303361286.jpg");
        newFansBean2.setTime("2019-03-10");
        newFansBean2.setTitle("孙夹克");
        newFansBean2.setReturnPowderStatus(true);

        newFansList.add(newFansBean);
        newFansList.add(newFansBean1);
        newFansList.add(newFansBean2);

        NewFansListAdapter newFansListAdapter = new NewFansListAdapter(NewFansActivity.this);
        newFansListAdapter.setNewFansList(newFansList);
        mRvNewFansList.setAdapter(newFansListAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
