package com.nbhysj.coupon.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ChatMessageListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.UserChatContract;
import com.nbhysj.coupon.model.UserChatModel;
import com.nbhysj.coupon.model.request.ChatMessageReplyRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.UserChatBean;
import com.nbhysj.coupon.model.response.UserChatResponse;
import com.nbhysj.coupon.presenter.UserChatPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.SmileyParser;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;


/**
 * @auther：hysj created on 2019/05/28
 * description：评论列表
 */
public class UserChatListActivity extends BaseActivity<UserChatPresenter, UserChatModel> implements UserChatContract.View,View.OnClickListener {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    //评论列表
    @BindView(R.id.rv_comment)
    RecyclerView mRvCommentList;
    //用户评论
    @BindView(R.id.llyt_user_comment)
    LinearLayout mLlytUserComment;
    private List<UserChatBean> userChatList;
    private SmileyParser mParser;
    private Activity activity;
    private ImageView emojie_tv;
    private LinearLayout main_emoji_layout;
    private int keyBoardState = 0;//0为键盘收缩，1为弹出
    private InputMethodManager imm;
    private EditText mEdtComment;

    //其他用户id
    private int uid;

    private int mPageNo = 1;
    private int mPageSize = 10;

    private ChatMessageListAdapter chatMessageListAdapter;

    private boolean isOnLoadMore = false;

    //总条数
    private int mTotalPageCount;

    TextView mTvSure;

    //用户名
    private String username;

    private String chatMessage;

    //自己的头像
    private String avatarUrl;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_user_chat_list;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        uid = getIntent().getIntExtra("uid",0);
        username = getIntent().getStringExtra("username");

        avatarUrl = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.USER_AVATAR,"");
        if (!TextUtils.isEmpty(username))
        {
            ToolbarHelper.setBar(UserChatListActivity.this, username, R.mipmap.icon_left_arrow_black, "");
        }

        if (userChatList == null)
        {
            userChatList = new ArrayList<>();

        } else {
            userChatList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UserChatListActivity.this);
        mRvCommentList.setLayoutManager(linearLayoutManager);
        chatMessageListAdapter = new ChatMessageListAdapter(UserChatListActivity.this);
        chatMessageListAdapter.setChatMessageList(userChatList);
        mRvCommentList.setAdapter(chatMessageListAdapter);

        activity = this;
        imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        SmileyParser.init(this);//初始化正则表达式工具类
        mParser = SmileyParser.getInstance();
        emojie_tv = (ImageView) findViewById(R.id.emojie_tv);
        emojie_tv.setOnClickListener(this);
        mTvSure = findViewById(R.id.tv_comment_sure_send);
        mTvSure.setOnClickListener(this);
        mLlytUserComment.setOnClickListener(this);
        main_emoji_layout = (LinearLayout) findViewById(R.id.main_emoji_layout);

        mEdtComment = (EditText) findViewById(R.id.edt_comment);
        mEdtComment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                keyBoardState = 1;
                main_emoji_layout.setVisibility(View.GONE);
                imm.showSoftInput(mEdtComment, 0);
                emojie_tv.setSelected(false);
                return false;
            }
        });
    }

    @Override
    public void initData() {

        showProgressDialog(UserChatListActivity.this);
        getUserChatMessageList();

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPageNo = 1;
                        userChatList.clear();
                        chatMessageListAdapter.notifyDataSetChanged();
                        showProgressDialog(UserChatListActivity.this);
                        getUserChatMessageList();

                    }
                }, 100);
            }
        });

        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isOnLoadMore = true;
                        try {
                            if (mTotalPageCount == userChatList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getUserChatMessageList();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 200);
            }
        });
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_comment_sure_send://确定
                //两秒连续点击，只取第一次的点击有效
                RxView.clicks(mTvSure).throttleFirst(2, TimeUnit.SECONDS).subscribe(new rx.Observer<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Void aVoid) {
                      //  Log.e(TAG, "onNext: 点击事件" );
                        chatMessageReply();
                    }
                });

                break;
            case R.id.emojie_tv://表情

                softInputFromWindowOprate();

            case R.id.llyt_user_comment:

                softInputFromWindowOprate();

                break;
                default:break;
        }
    }

    public void softInputFromWindowOprate(){

        if (keyBoardState == 0) {//弹出键盘
            showSoftInputFromWindow();

        } else {//关闭键盘
            hideSoftInputFromWindow();
        }
    }

    public void hideSoftInputFromWindow(){

        keyBoardState = 0;
        imm.hideSoftInputFromWindow(mEdtComment.getWindowToken(), 0);
        emojie_tv.setSelected(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                main_emoji_layout.setVisibility(View.GONE);
            }
        }, 50);

       // mPid = 0;
    }


    public void showSoftInputFromWindow(){

        keyBoardState = 1;
        main_emoji_layout.setVisibility(View.GONE);
        mEdtComment.setFocusable(true);
        mEdtComment.setFocusableInTouchMode(true);
        mEdtComment.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
      //  showSoftInputFromWindow(CommentsListActivity.this, mEdtComment);
    /*    new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imm.showSoftInput(mEdtComment, 0);
            }
        }, 50);
*/

        imm.showSoftInput(mEdtComment, 0);
        emojie_tv.setSelected(false);
    }

    @Override
    public void userReplyChatResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    UserChatBean userChatBean = new UserChatBean();
                    userChatBean.setOwnStatus(1);
                    userChatBean.setAvater(avatarUrl);
                    userChatBean.setMessage(chatMessage);

                    userChatList.add(userChatBean);
                    chatMessageListAdapter.setChatMessageList(userChatList);
                    chatMessageListAdapter.notifyDataSetChanged();
                    hideSoftInputFromWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(UserChatListActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(UserChatListActivity.this, Constants.getResultMsg(msg));
    }

    @Override
    public void getUserChatListResult(BackResult<UserChatResponse> res) {
        dismissProgressDialog();
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    if (isOnLoadMore) {

                        mSmartRefreshLayout.finishLoadMore();

                    } else {

                        userChatList.clear();
                        chatMessageListAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<UserChatBean> userChatBeanList = res.getData().getResult();

                    if (mTotalPageCount == 0)
                    {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if (userChatBeanList != null)
                    {
                        userChatList.addAll(userChatBeanList);
                    }

                    Collections.reverse(userChatList);
                    chatMessageListAdapter.setChatMessageList(userChatList);
                    chatMessageListAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(UserChatListActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    //获取帖子评论列表
    public void getUserChatMessageList(){

        if(validateInternet()){

            showProgressDialog(UserChatListActivity.this);
            mPresenter.getUserChatList(uid,mPageNo,mPageSize);
        }
    }

    //消息发送
     public void chatMessageReply() {

        if (validateInternet()) {

            chatMessage = mEdtComment.getText().toString().trim();

            if (TextUtils.isEmpty(chatMessage))
            {
              //  dismissProgressDialog();
                showToast(UserChatListActivity.this, "请填写需要发送的消息");
                return;
            }
            showProgressDialog(UserChatListActivity.this);
            ChatMessageReplyRequest chatMessageReplyRequest = new ChatMessageReplyRequest();
            chatMessageReplyRequest.setToUid(uid);
            chatMessageReplyRequest.setType(0);
            chatMessageReplyRequest.setMessage(chatMessage);
            mPresenter.userReplyChat(chatMessageReplyRequest);

        }
    }
}
