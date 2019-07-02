package com.nbhysj.coupon.fragment;

import android.view.View;

public abstract class LazyFragment extends BaseFragment {

    public static final String TAG = LazyFragment.class.getSimpleName();

    private boolean hasLoaded = false;

    private boolean isCreated = false;

    private boolean isVisibleToUser = false;
    private View view;

    public LazyFragment() {
    }

    @Override
    public void initView(View view) {
        isCreated = true;
        this.view = view;
        lazyLoad(this.view);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisibleToUser = isVisibleToUser;//注：关键步骤
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad(view);
    }

    private void lazyLoad(View view) {
        if (!isVisibleToUser || hasLoaded || !isCreated) {
            return;
        }
        lazyInitView(view);
        hasLoaded = true;//注：关键步骤，确保数据只加载一次
    }

    public abstract void lazyInitView(View view);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isCreated = false;
        hasLoaded = false;
    }

}
