package com.nbhysj.coupon.framework;

import android.os.Bundle;

/**
 * created by fqq at 2018/10/09.
 * description : BaseActivity 基类
 */

public interface IBaseActivity {

    /**
     * 初始化保存的参数（onCreate方法中调用）
     *
     * @param savedInstanceState
     */
    void initBundleParams(Bundle savedInstanceState);
}
