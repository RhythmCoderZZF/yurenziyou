package com.nbhysj.coupon.ui;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.nbhysj.coupon.R;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

public class ConversationListActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.conversationlist;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        FragmentManager fragmentManage = getSupportFragmentManager();
        ConversationListFragment fragement = (ConversationListFragment) fragmentManage.findFragmentById(R.id.conversationlist);
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")
                .build();
        fragement.setUri(uri);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }
}
