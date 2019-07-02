package com.nbhysj.coupon.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MessageListAdapter;
import com.nbhysj.coupon.model.response.MessageBean;
import com.nbhysj.coupon.ui.CommentsAndAnswersActivity;
import com.nbhysj.coupon.ui.NewFansActivity;
import com.nbhysj.coupon.ui.PraiseAndCollectionActivity;
import com.nbhysj.coupon.ui.StrokeDynamicsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageFragment extends BaseFragment {

    @BindView(R.id.rv_message)
    RecyclerView mRvMessageList;
    @BindView(R.id.tv_designate_me_tag)
    TextView mTvDesignateMeTag;
    private List<MessageBean> messageList;
    @BindView(R.id.llyt_comment_and_answer)
    LinearLayout mLlytCommentAndAnswer;

    public MessageFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(View v) {
        if (messageList == null) {

            messageList = new ArrayList<>();
        } else {

            messageList.clear();
        }

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置布局管理器
        mRvMessageList.setLayoutManager(layoutManager);

        MessageBean messageBean = new MessageBean();
        messageBean.setContent("消息123");
        messageBean.setRead(false);
        messageBean.setTime("2019-02-20");
        messageBean.setTitle("123");
        messageBean.setUrl("http://img.bimg.126.net/photo/ZZ5EGyuUCp9hBPk6_s4Ehg==/5727171351132208489.jpg");

        MessageBean messageBean1 = new MessageBean();
        messageBean1.setContent("消息456");
        messageBean1.setRead(false);
        messageBean1.setTime("2019-02-20");
        messageBean1.setTitle("456");
        messageBean1.setUrl("http://www.zt5.com/uploadfile/2019/0127/20190127010112529.jpg");

        messageList.add(messageBean);
        messageList.add(messageBean1);

        MessageListAdapter messageListAdapter = new MessageListAdapter(getActivity());
        messageListAdapter.setMessageList(messageList);
        mRvMessageList.setAdapter(messageListAdapter);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.llyt_comment_and_answer, R.id.rlyt_stroke_dynamics, R.id.rlyt_question_and_answer, R.id.llyt_new_fans, R.id.llyt_praise_and_collection})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llyt_comment_and_answer:
                toActivity(CommentsAndAnswersActivity.class);
                break;
            case R.id.rlyt_stroke_dynamics:
                toActivity(StrokeDynamicsActivity.class);
                break;
            case R.id.rlyt_question_and_answer:
                toActivity(CommentsAndAnswersActivity.class);
                break;
            case R.id.llyt_new_fans:   //新增粉丝
                toActivity(NewFansActivity.class);
                break;
            case R.id.llyt_praise_and_collection:   //赞和收藏
                toActivity(PraiseAndCollectionActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void lazyInitView(View view) {

    }
}
