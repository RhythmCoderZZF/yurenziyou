package com.nbhysj.coupon.presenter;
import com.nbhysj.coupon.contract.UserChatContract;
import com.nbhysj.coupon.model.request.ChatMessageReplyRequest;

/**
 * @auther：hysj created on 2019/06/05
 * description：用户聊天Presenter
 */
public class UserChatPresenter extends UserChatContract.Presenter {

    @Override
    public void getUserChatList(int uid, int page, int pageSize) {
        mRxManager.add(mModel.getUserChatList(uid,page,pageSize).subscribe(res -> mView.getUserChatListResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void userReplyChat(ChatMessageReplyRequest chatMessageReplyRequest) {
        mRxManager.add(mModel.userReplyChat(chatMessageReplyRequest).subscribe(res -> mView.userReplyChatResult(res), e -> mView.showMsg(e.getMessage())));
    }

    @Override
    public void onStart() {

    }
}
