package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MoreQuestionListAdapter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/02
 * description：更多问题
 */
public class MoreQuestionsActivity extends BaseActivity {

    //很多问题
    @BindView(R.id.rv_more_question)
    RecyclerView mRvMoreQuestion;

    @BindView(R.id.tv_back)
    TextView mTvBack;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_more_questions;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(MoreQuestionsActivity.this);
        mRvMoreQuestion.setLayoutManager(layoutManager);
        MoreQuestionListAdapter moreQuestionListAdapter = new MoreQuestionListAdapter(MoreQuestionsActivity.this);
        mRvMoreQuestion.setAdapter(moreQuestionListAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

    }

    @OnClick({R.id.rlyt_my_ask_question,R.id.rlyt_my_answer,R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlyt_my_ask_question:

                toActivity(AskQuestionsActivity.class);

                break;
            case R.id.rlyt_my_answer:
                toActivity(AskQuestionsActivity.class);
                break;
            case R.id.tv_back:

                MoreQuestionsActivity.this.finish();

                break;
            default:
                break;
        }
    }
}
