package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.SupportedUsersListAdapter;
import com.nbhysj.coupon.model.response.SupportedUserBean;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/02
 * description：点赞过的用户
 */
public class SupportedUsersActivity extends BaseActivity {

    //已点赞用户列表
    @BindView(R.id.rv_supported_users)
    RecyclerView mRvSpportedUserList;
    //已点赞用户
    private List<SupportedUserBean> supportedUserList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_supported_users;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ToolbarHelper.setBar(SupportedUsersActivity.this, getResources().getString(R.string.str_supported_users), R.mipmap.nav_ico_back_black);
        if (supportedUserList == null) {

            supportedUserList = new ArrayList<>();

        } else {
            supportedUserList.clear();
        }

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(SupportedUsersActivity.this);
        // 设置布局管理器
        mRvSpportedUserList.setLayoutManager(layoutManager);

        SupportedUsersListAdapter supportedUsersListAdapter = new SupportedUsersListAdapter(SupportedUsersActivity.this);

        SupportedUserBean supportedUserBean = new SupportedUserBean();
        supportedUserBean.setUrl("http://img.duoziwang.com/2016/12/17/16303361286.jpg");
        supportedUserBean.setContent("鱼香肉丝了解一下吗");
        supportedUserBean.setTitle("张星星");
        supportedUserBean.setFollowStatus(false);

        SupportedUserBean supportedUserBean1 = new SupportedUserBean();
        supportedUserBean1.setUrl("https://img5.duitang.com/uploads/item/201409/20/20140920163237_myPVw.thumb.700_0.png");
        supportedUserBean1.setContent("鱼很好吃");
        supportedUserBean1.setTitle("王建立");
        supportedUserBean.setFollowStatus(false);

        SupportedUserBean supportedUserBean2 = new SupportedUserBean();
        supportedUserBean2.setUrl("http://photo.16pic.com/00/61/47/16pic_6147665_b.jpg");
        supportedUserBean2.setContent("环境很好");
        supportedUserBean2.setTitle("孙夹克");
        supportedUserBean2.setFollowStatus(true);


        SupportedUserBean supportedUserBean3 = new SupportedUserBean();
        supportedUserBean3.setUrl("https://img5.duitang.com/uploads/item/201409/20/20140920163237_myPVw.thumb.700_0.png");
        supportedUserBean3.setContent("吃吃吃吃吃");
        supportedUserBean3.setTitle("浩浩");
        supportedUserBean3.setFollowStatus(false);

        SupportedUserBean supportedUserBean4 = new SupportedUserBean();
        supportedUserBean4.setUrl("http://img.zcool.cn/community/01ee17559a29e832f87598b51ff597.jpg@2o.jpg");
        supportedUserBean4.setContent("鱼很好吃");
        supportedUserBean4.setTitle("王建立");
        supportedUserBean4.setFollowStatus(false);

        SupportedUserBean supportedUserBean5 = new SupportedUserBean();
        supportedUserBean5.setUrl("https://img4.duitang.com/uploads/item/201410/02/20141002005620_Qxrcx.thumb.700_0.jpeg");
        supportedUserBean5.setContent("呃呃呃呃吗");
        supportedUserBean5.setTitle("鱼代驾");
        supportedUserBean5.setFollowStatus(true);

        supportedUserList.add(supportedUserBean);
        supportedUserList.add(supportedUserBean1);
        supportedUserList.add(supportedUserBean2);
        supportedUserList.add(supportedUserBean3);
        supportedUserList.add(supportedUserBean4);
        supportedUserList.add(supportedUserBean5);

        supportedUsersListAdapter.setSupportedUsersList(supportedUserList);
        mRvSpportedUserList.setAdapter(supportedUsersListAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
