package com.nbhysj.coupon.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ExpressionAdapter;
import com.nbhysj.coupon.adapter.PostCommentItemAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.PostsTypeEnum;
import com.nbhysj.coupon.contract.CommentContract;
import com.nbhysj.coupon.model.CommentModel;
import com.nbhysj.coupon.model.request.PostOprateRequest;
import com.nbhysj.coupon.model.request.PostsCommentRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.MchCommentResponse;
import com.nbhysj.coupon.model.response.PostsCommentResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.presenter.CommentPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.SmileyParser;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;

import static android.view.View.VISIBLE;

/**
 * @auther：hysj created on 2019/05/28
 * description：评论列表
 */
public class CommentsListActivity extends BaseActivity<CommentPresenter, CommentModel> implements CommentContract.View,View.OnClickListener {

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
    private List<PostsCommentResponse.PostsCommentEntity> postCommentList;
    private SmileyParser mParser;
    private Activity activity;
    private ImageView emojie_tv;
    List<List<Integer>> caicaiList = new ArrayList<>();//表情2的数据源
    List<List<String>> caicaiTxtList = new ArrayList<>();//表情2的符号数据源
    Map<Integer, List<List<Integer>>> expressionTypeList = new TreeMap<>();//表情总数据源,以图标为key
    private List<GridView> gridList = new ArrayList<>();
    private ExpressionAdapter mExpressionAdapter;
    private ViewPager vViewPager;
    private LinearLayout vLl_dots;
    private LinearLayout main_emoji_layout;
    private int keyBoardState = 0;//0为键盘收缩，1为弹出
    private InputMethodManager imm;
    private EditText mEdtComment;

    //帖子id
    private int mPostId;

    private int mPageNo = 1;
    private int mPageSize = 10;

    private PostCommentItemAdapter postCommentItemAdapter;

    private boolean isOnLoadMore = false;

    //总条数
    private int mTotalPageCount;

    private PostsCommentResponse.PostsCommentEntity mPostsCommentEntity;

    //(0:评论帖子 1:评论) 帖子的评论
    private int mPid = 0;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_comments_list;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(CommentsListActivity.this, getResources().getString(R.string.str_comments_list), R.mipmap.nav_ico_back_black, "");

        mPostId = getIntent().getIntExtra("mPostId",0);

        if (postCommentList == null)
        {
            postCommentList = new ArrayList<>();

        } else {
            postCommentList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommentsListActivity.this);
        mRvCommentList.setLayoutManager(linearLayoutManager);
        postCommentItemAdapter = new PostCommentItemAdapter(CommentsListActivity.this, new PostCommentItemAdapter.PostCommentPraiseListener() {
            @Override
            public void setPostCommentPraiseListener(int position, PostsCommentResponse.PostsCommentEntity postsCommentEntity) {
                mPostsCommentEntity =  postsCommentEntity;
                mPid = postsCommentEntity.getId();
                postOprate();
            }

            @Override
            public void setPostCommentListener(int position, PostsCommentResponse.PostsCommentEntity postsCommentEntity) {

                mPid = postsCommentEntity.getId();
                softInputFromWindowOprate();
            }
        });
        postCommentItemAdapter.setPostCommentList(postCommentList);
        mRvCommentList.setAdapter(postCommentItemAdapter);

        activity = this;
        imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        SmileyParser.init(this);//初始化正则表达式工具类
        mParser = SmileyParser.getInstance();
        emojie_tv = (ImageView) findViewById(R.id.emojie_tv);
        emojie_tv.setOnClickListener(this);
        TextView mTvSure = findViewById(R.id.tv_comment_sure_send);
        mTvSure.setOnClickListener(this);
        mLlytUserComment.setOnClickListener(this);
        vViewPager = (ViewPager) findViewById(R.id.viwepager_expression);//viewPager
        vLl_dots = (LinearLayout) findViewById(R.id.ll_dot_container);//圆点容器
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

        showProgressDialog(CommentsListActivity.this);
        getAllPostsCommentListByArticleId();

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPageNo = 1;
                        postCommentList.clear();
                        postCommentItemAdapter.notifyDataSetChanged();
                        showProgressDialog(CommentsListActivity.this);
                        getAllPostsCommentListByArticleId();

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
                            if (mTotalPageCount == postCommentList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getAllPostsCommentListByArticleId();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 200);
            }
        });

        List<Integer> data2 = new ArrayList<>();
        for (int i = 0; i < mParser.CAICAI_SMILEY_RES_IDS.length; i++) {
            data2.add(mParser.CAICAI_SMILEY_RES_IDS[i]);
        }
        //把图片数据分页，每页20个表情一个删除键，三行
        caicaiList = splitList(data2, 20);
        expressionTypeList.put(R.mipmap.aw, caicaiList);
        //把符号数据分页，每页最多20个表情，加上一个删除键
        // emojiTxtList = splitStringList(Arrays.asList(mParser.arrTextEMoji), 20);
        caicaiTxtList = splitStringList(Arrays.asList(mParser.arrTextCaiCai), 20);
        initEmojiAdapter(caicaiList.size());
    }

    @Override
    public void postOprateResult(BackResult<PraiseOrCollectResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    PraiseOrCollectResponse praiseOrCollectResponse = res.getData();
                    if(praiseOrCollectResponse != null)
                    {
                        int zanStatus = praiseOrCollectResponse.getZanStatus();
                        int zanCount = praiseOrCollectResponse.getZanNum();
                        mPostsCommentEntity.setZanStatus(zanStatus);
                        mPostsCommentEntity.setZanCount(zanCount);
                        postCommentItemAdapter.setPostCommentList(postCommentList);
                        postCommentItemAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(CommentsListActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_comment_sure_send://确定
                postCommentRequest();
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

        mPid = 0;
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

    /**
     * 把lista按固定长度分割成若干list
     *
     * @param dataList
     * @param length   每个集合长度
     * @return
     */
    public static List<List<Integer>> splitList(List<Integer> dataList, int length) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i = i + length) {
            int j = i + length;
            if (j > dataList.size()) {
                j = dataList.size();
            }

            List<Integer> insertList = dataList.subList(i, j);
            if (insertList.size() == 0) {
                break;
            }
            lists.add(insertList);
        }
        return lists;
    }

    /**
     * 把lista按固定长度分割成若干list
     *
     * @param dataList
     * @param length   每个集合长度
     * @return
     */
    public static List<List<String>> splitStringList(List<String> dataList, int length) {
        List<List<String>> lists = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i = i + length) {
            int j = i + length;
            if (j > dataList.size()) {
                j = dataList.size();
            }
            List<String> insertList = dataList.subList(i, j);
            if (insertList.size() == 0) {
                break;
            }
            lists.add(insertList);
        }
        return lists;
    }

    /**
     * 初始化表情资源
     */
    private void initEmojiAdapter(int emojiPage) {
        gridList.clear();
        for (int i = 0; i < emojiPage; i++) {
            GridView gridView = (GridView) getLayoutInflater().inflate(R.layout.gridview_emoji, null);
            final List<Integer> emojiResource = caicaiList.get(i);
            final List<String> emojiResourceName = caicaiTxtList.get(i);
            mExpressionAdapter = new ExpressionAdapter(getLayoutInflater(), emojiResource);
            gridView.setAdapter(mExpressionAdapter);
            //点击表情，将表情添加到输入框中。
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    if (position != emojiResource.size()) {
                        mEdtComment.getText().insert(mEdtComment.getSelectionStart(),
                                mParser.addSmileySpansReSize((emojiResourceName.get(position)), 20, 20));
                    } else {
                      /*  int keyCode = KeyEvent.KEYCODE_DEL;
                        KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
                        ev1.dispatchKeyEvent(keyEvent);*/
                    }
                }
            });
            gridList.add(gridView);
        }
        vViewPager.setAdapter(new EmojiAdapter(gridList));
        gotoInitData(gridList);
    }

    /**
     * 表情适配器
     */
    public class EmojiAdapter extends PagerAdapter {
        private List<GridView> list;

        public EmojiAdapter(List<GridView> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list != null && list.size() > 0) {
                return list.size();
            } else {
                return 0;
            }
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((GridView) object);
        }

        @Override
        public GridView instantiateItem(ViewGroup container, int position) {
            GridView GridView = list.get(position);
            container.addView(GridView);
            return GridView;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    /**
     * 初始表情布局下底部圆点
     *
     * @param list
     */
    private void gotoInitData(List<GridView> list) {
        vLl_dots.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(activity);
            if (i == 0) {
                imageView.setImageResource(R.drawable.shape_dot_select);

            } else {
                imageView.setImageResource(R.drawable.shape_dot_nomal);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(SmileyParser.dp2px(activity, 8),
                    SmileyParser.dp2px(activity, 8));
            layoutParams.setMargins(20, 0, 0, 0);
            vLl_dots.addView(imageView, layoutParams);

        }
        if (vLl_dots.getChildCount() <= 1) {
            vLl_dots.setVisibility(View.GONE);
        } else {
            vLl_dots.setVisibility(VISIBLE);
        }
        vViewPager.setOffscreenPageLimit(6);
        vViewPager.setCurrentItem(0);
        vViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < vLl_dots.getChildCount(); i++) {
                    if (i != position) {
                        ((ImageView) vLl_dots.getChildAt(i)).setImageResource(R.drawable.shape_dot_nomal);
                    }
                }
                ((ImageView) vLl_dots.getChildAt(position)).setImageResource(R.drawable.shape_dot_select);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    @Override
    public void postsCommentResult(BackResult res) {

        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    hideSoftInputFromWindow();
                    mEdtComment.setText("");
                    mPageNo = 1;
                    postCommentList.clear();
                    getAllPostsCommentListByArticleId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(CommentsListActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }

    }

    @Override
    public void getMchCommentListResult(BackResult<MchCommentResponse> res) {

    }

    @Override
    public void getAllPostsCommentListByArticleIdResult(BackResult<PostsCommentResponse> res) {
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

                        postCommentList.clear();
                        postCommentItemAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<PostsCommentResponse.PostsCommentEntity> postsCommentEntityList = res.getData().getResult();

                    if (mTotalPageCount == 0)
                    {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if (postsCommentEntityList != null)
                    {
                        postCommentList.addAll(postsCommentEntityList);
                    }

                    postCommentItemAdapter.setPostCommentList(postCommentList);
                    postCommentItemAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                toActivity(PhoneQuickLoginActivity.class);
                break;
            default:
                showToast(CommentsListActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(CommentsListActivity.this, Constants.getResultMsg(msg));
    }

    //获取帖子评论列表
    public void getAllPostsCommentListByArticleId(){

        if(validateInternet()){

            showProgressDialog(CommentsListActivity.this);
            mPresenter.getAllPostsCommentListByArticleId(mPostId,mPageNo,mPageSize);
        }

    }

    //点赞帖子评论操作
    public void postOprate(){

        if(validateInternet()){

            showProgressDialog(CommentsListActivity.this);
            PostOprateRequest postOprateRequest = new PostOprateRequest();
            int commentId = mPostsCommentEntity.getId();
            postOprateRequest.setPostsId(commentId);
            int postsType = PostsTypeEnum.POST_COMMENT.getKey();
            postOprateRequest.setPostsType(postsType);
            mPresenter.postOprate(postOprateRequest);
        }
    }

    //帖子评论
    public void postCommentRequest() {

        if (validateInternet()) {

            String postCommentContent = mEdtComment.getText().toString().trim();

            if (TextUtils.isEmpty(postCommentContent)) {
                dismissProgressDialog();
                showToast(CommentsListActivity.this, "请填写评论");
                return;
            }
            PostsCommentRequest postsCommentRequest = new PostsCommentRequest();
            postsCommentRequest.setArticleId(mPostId);
            postsCommentRequest.setContent(postCommentContent);
            postsCommentRequest.setPid(mPid);
            mPresenter.postsCommentRequest(postsCommentRequest);

        }
    }
}
