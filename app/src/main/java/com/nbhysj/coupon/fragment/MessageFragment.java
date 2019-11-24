package com.nbhysj.coupon.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MessageListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MessageContract;
import com.nbhysj.coupon.model.MessageModel;
import com.nbhysj.coupon.model.response.AttentionResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BroadcastResponse;
import com.nbhysj.coupon.model.response.CommentAndAnswerResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.MessageResponse;
import com.nbhysj.coupon.model.response.UserFansFollowResponse;
import com.nbhysj.coupon.model.response.UserFollowResponse;
import com.nbhysj.coupon.model.response.ZanAndCollectionResponse;
import com.nbhysj.coupon.presenter.MessagePresenter;
import com.nbhysj.coupon.ui.CommentsAndAnswersActivity;
import com.nbhysj.coupon.ui.NewFansActivity;
import com.nbhysj.coupon.ui.PhoneQuickLoginActivity;
import com.nbhysj.coupon.ui.StrokeDynamicsActivity;
import com.nbhysj.coupon.ui.ZanAndCollectionActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageFragment extends BaseFragment<MessagePresenter, MessageModel> implements MessageContract.View {

    @BindView(R.id.rv_message)
    RecyclerView mRvMessageList;
    @BindView(R.id.tv_designate_me_tag)
    TextView mTvDesignateMeTag;
    private List<MessageResponse.MessageEntity> messageList;
    @BindView(R.id.llyt_comment_and_answer)
    LinearLayout mLlytCommentAndAnswer;

    MessageListAdapter messageListAdapter;

    public MessageFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
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

        messageListAdapter = new MessageListAdapter(getActivity());
        messageListAdapter.setMessageList(messageList);
        mRvMessageList.setAdapter(messageListAdapter);
    }

    @Override
    public void initData() {

        getMessageList();
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
                toActivity(ZanAndCollectionActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void lazyInitView(View view) {

    }

    @Override
    public void getUserFansListResult(BackResult<UserFansFollowResponse> res) {

    }

    @Override
    public void userFollowResult(BackResult<FollowUserStatusResponse> res) {

    }

    @Override
    public void getAttentionInitResult(BackResult<AttentionResponse> res) {

    }

    @Override
    public void getUserFollowResult(BackResult<UserFollowResponse> res) {

    }

    @Override
    public void getZanAndCollectionMsgResult(BackResult<ZanAndCollectionResponse> res) {

    }

    @Override
    public void getPostsCommentAndAnswerResult(BackResult<CommentAndAnswerResponse> res) {

    }

    @Override
    public void getMessageListResult(BackResult<MessageResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    // mPosition++;
                    MessageResponse messageResponse = res.getData();
                    List<MessageResponse.MessageEntity> messageList = messageResponse.getResult();
                    messageListAdapter.setMessageList(messageList);
                    messageListAdapter.notifyDataSetChanged();
                   /* if (userFollowResponseList.size() > 0) {
                        TravellerBean travellerBean = travellersList.get(0);
                        userTravelerId = travellerBean.getUserId();
                        travellerBean.setTravellerSelect(true);
                        realname = travellerBean.getRealname();
                        mobile = travellerBean.getMobile();
                        mTvTouristName.setText(realname);
                        mTvTouristMobile.setText(mobile);
                    }

                    addTouristInformationAdapter.setTouristInfoList(travellersList);
                    addTouristInformationAdapter.notifyDataSetChanged();

                    touristInformationAdapter.setTouristInfoList(travellersList);
                    touristInformationAdapter.notifyDataSetChanged();

                    mLlytAddTourists.setVisibility(View.GONE);*/

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getBroadcatMessageListResult(BackResult<BroadcastResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    public void getMessageList() {

        if (validateInternet()) {

            showProgressDialog(getActivity());
            mPresenter.getMessageList();
        }
    }
}
