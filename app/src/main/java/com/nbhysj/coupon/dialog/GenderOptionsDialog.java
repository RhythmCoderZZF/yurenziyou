package com.nbhysj.coupon.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * @auther：hysj created at 2019/03/09
 * description：性别选择对话框
 */
public class GenderOptionsDialog extends Dialog {


    private static int default_width = 300; //默认宽度

    private static int default_height = 300;//默认高度

    public GenderOptionsDialog(Context context, View layout, int style) {
        this(context, default_width, default_height, layout, style);
    }

    public GenderOptionsDialog(Context context, int width, int height, View layout, int style) {

        super(context, style);

        setContentView(layout);

     /*   Window window = getWindow();

        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);*/

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth() - 200; //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);


    }
}
