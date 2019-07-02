package com.nbhysj.coupon.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.fragment.FriendsNetAlbumFragment;
import com.nbhysj.coupon.fragment.MerchantAlbumFragment;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * @author hysj created at 2019/4/19.
 * description : 景点相册(1.商家相册 2.网友相册)
 */
public class ScenicSpotsAlbumActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mImgBack;
    @BindView(R.id.rg_merchant_album)
    RadioGroup mRgMerchantAlbum;
    @BindView(R.id.rbtn_merchant_album)
    RadioButton mRbtnMerchantAlbum;
    @BindView(R.id.rbtn_friends_album)
    RadioButton mRbtnfriendsAlbum;
    @BindView(R.id.frame_album)
    FrameLayout mFrameLayoutAlbum;

    private FragmentTransaction ft;
    private Fragment CURRENT_FRAGMENT;
    private List<Fragment> fragments;
    private int mchId;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_scenic_spots_album;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mchId = getIntent().getIntExtra("mchId", 0);
        fragments = new ArrayList<>();
        fragments.add(new MerchantAlbumFragment().newInstance(mchId));
        fragments.add(new FriendsNetAlbumFragment().newInstance(mchId));
        goFragment(0);
    }

    @Override
    public void initData() {
        mRgMerchantAlbum.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (mRbtnMerchantAlbum.getId() == checkedId) {
                    mRbtnfriendsAlbum.setChecked(false);
                    goFragment(0);
                }

                if (mRbtnfriendsAlbum.getId() == checkedId) {
                    mRbtnMerchantAlbum.setChecked(false);
                    goFragment(1);
                }
            }
        });

        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ScenicSpotsAlbumActivity.this.finish();
            }
        });
    }

    @Override
    public void initPresenter() {

    }


    public void goFragment(int index) {

        ft = getSupportFragmentManager().beginTransaction();
        if (null != CURRENT_FRAGMENT) {
            ft.hide(CURRENT_FRAGMENT);
        }
        Fragment fragment = getSupportFragmentManager()
                .findFragmentByTag(fragments.get(index).getClass().getName());
        if (fragment == null) {
            fragment = fragments.get(index);
        }
        CURRENT_FRAGMENT = fragment;
        if (!fragment.isAdded()) {
            ft.add(R.id.frame_album, fragment, fragment.getClass().getName());
        } else {
            ft.show(fragment);
        }
        ft.commit();
    }
}
