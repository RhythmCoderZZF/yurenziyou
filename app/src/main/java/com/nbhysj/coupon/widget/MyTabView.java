package com.nbhysj.coupon.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;

public class MyTabView extends AppCompatTextView {

    public int index;

    public MyTabView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MyTabView(Context context, int index) {
        super(context);
        this.index = index;
    }
}
