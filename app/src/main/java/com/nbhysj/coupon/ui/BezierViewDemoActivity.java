package com.nbhysj.coupon.ui;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;
import com.nbhysj.coupon.util.blurbehind.OnBlurCompleteListener;

public class BezierViewDemoActivity extends BaseActivity {

    ImageView mImgAccount;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bezier_view_demo;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mImgAccount = findViewById(R.id.img_account);
        mImgAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BlurBehind.getInstance().execute(BezierViewDemoActivity.this, new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {

                        Intent intent = new Intent(BezierViewDemoActivity.this, MyBusinessCardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
