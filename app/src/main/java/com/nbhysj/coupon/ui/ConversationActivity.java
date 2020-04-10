package com.nbhysj.coupon.ui;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.nbhysj.coupon.R;

import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;

public class ConversationActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.conversation;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        FragmentManager fragmentManage = getSupportFragmentManager();
        ConversationFragment fragement = (ConversationFragment) fragmentManage.findFragmentById(R.id.conversation);
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(Conversation.ConversationType.PRIVATE.getName().toLowerCase())
                .appendQueryParameter("targetId", "170").build();

        fragement.setUri(uri);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
