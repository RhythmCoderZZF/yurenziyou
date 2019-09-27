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
import android.widget.TextView;
import android.widget.Toast;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.CommentReceivedItemAdapter;
import com.nbhysj.coupon.adapter.ExpressionAdapter;
import com.nbhysj.coupon.model.response.CommentReceiveResponse;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.SmileyParser;
import com.nbhysj.coupon.util.ToolbarHelper;

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
public class CommentsListActivity extends BaseActivity implements View.OnClickListener {

    //收到的评论
    @BindView(R.id.rv_comment_received)
    RecyclerView mRvCommentReceivedList;
    /* @BindView(R.id.toolbar_space)
     View mToolbarSpace;*/
    private List<CommentReceiveResponse> commentReceiveList;
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
    private int keyBoardState = 1;//0为键盘为弹出，1为弹出
    private InputMethodManager imm;
    private EditText mEdtComment;

    /*   @Override
       protected void onCreate(@Nullable Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView();
           initView();
           initData();
       }
   */
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_comments_list;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

       /* ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }*/
        ToolbarHelper.setBar(CommentsListActivity.this, getResources().getString(R.string.str_comments_list), R.mipmap.nav_ico_back_black, "");


        if (commentReceiveList == null) {
            commentReceiveList = new ArrayList<>();

        } else {
            commentReceiveList.clear();
        }


        List<CommentReceiveResponse.CommentEntity> commentSubList = new ArrayList<>();
        CommentReceiveResponse commentReceiveResponse = new CommentReceiveResponse();
        commentReceiveResponse.setAvatar("http://img3.imgtn.bdimg.com/it/u=2584422743,1020351689&fm=214&gp=0.jpg");
        commentReceiveResponse.setCommentOprate("开始关注你了");
        commentReceiveResponse.setCommentTime("2019-08-09");
        commentReceiveResponse.setCommentUser("陈开开");
        commentReceiveResponse.setCommentPictrue("https://img3.duitang.com/uploads/blog/201407/10/20140710152857_Puy5M.jpeg");

        CommentReceiveResponse.CommentEntity commentEntity = commentReceiveResponse.new CommentEntity();
        commentEntity.setComment("很好吃啊");
        commentEntity.setMyComment(false);

        CommentReceiveResponse.CommentEntity commentEntity1 = commentReceiveResponse.new CommentEntity();
        commentEntity1.setComment("我的评论：看着很好吃啊");
        commentEntity1.setMyComment(true);

        CommentReceiveResponse.CommentEntity commentEntity2 = commentReceiveResponse.new CommentEntity();
        commentEntity2.setComment("看着很好吃啊");
        commentEntity2.setMyComment(false);
        commentSubList.add(commentEntity);
        commentSubList.add(commentEntity1);
        commentSubList.add(commentEntity2);

        commentReceiveResponse.setCommentList(commentSubList);

        CommentReceiveResponse commentReceiveResponse1 = new CommentReceiveResponse();
        commentReceiveResponse1.setAvatar("http://img3.imgtn.bdimg.com/it/u=2584422743,1020351689&fm=214&gp=0.jpg");
        commentReceiveResponse1.setCommentOprate("开始关注你了");
        commentReceiveResponse1.setCommentTime("2019-08-09");
        commentReceiveResponse1.setCommentUser("陈开开");
        commentReceiveResponse1.setCommentPictrue("https://img3.duitang.com/uploads/blog/201407/10/20140710152857_Puy5M.jpeg");

        CommentReceiveResponse.CommentEntity commentEntity3 = commentReceiveResponse.new CommentEntity();
        commentEntity3.setComment("很好吃啊");
        commentEntity3.setMyComment(false);

        CommentReceiveResponse.CommentEntity commentEntity4 = commentReceiveResponse.new CommentEntity();
        commentEntity4.setComment("我的评论：看着很好吃啊");
        commentEntity4.setMyComment(true);

        CommentReceiveResponse.CommentEntity commentEntity5 = commentReceiveResponse.new CommentEntity();
        commentEntity5.setComment("看着很好吃啊");
        commentEntity5.setMyComment(false);
        commentSubList.add(commentEntity3);
        commentSubList.add(commentEntity4);
        commentSubList.add(commentEntity5);

        commentReceiveResponse1.setCommentList(commentSubList);

        commentReceiveList.add(commentReceiveResponse);
        commentReceiveList.add(commentReceiveResponse1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommentsListActivity.this);
        mRvCommentReceivedList.setLayoutManager(linearLayoutManager);
        CommentReceivedItemAdapter commentReceivedItemAdapter = new CommentReceivedItemAdapter(CommentsListActivity.this);
        commentReceivedItemAdapter.setCommentReceivedList(commentReceiveList);
        mRvCommentReceivedList.setAdapter(commentReceivedItemAdapter);


        activity = this;
        imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        SmileyParser.init(this);//初始化正则表达式工具类
        mParser = SmileyParser.getInstance();
        emojie_tv = (ImageView) findViewById(R.id.emojie_tv);
        emojie_tv.setOnClickListener(this);
        TextView mTvSure = findViewById(R.id.enSure);
        mTvSure.setOnClickListener(this);
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

   /* @Override
    public void initPresenter() {

    }*/

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enSure://确定
                // Toast.makeText(activity, mParser.addSmileySpans(ev1.getText()), Toast.LENGTH_SHORT).show();
                //Log.i("TAG", "onClick: 输入内容"+ev1.getText());
                break;
            case R.id.emojie_tv://表情

                if (keyBoardState == 0) {//弹出键盘
                    keyBoardState = 1;
                    main_emoji_layout.setVisibility(View.GONE);
                    emojie_tv.setSelected(false);
                    mEdtComment.setFocusable(true);
                    showSoftInputFromWindow(CommentsListActivity.this, mEdtComment);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imm.showSoftInput(mEdtComment, 0);
                        }
                    }, 50);

                } else {//关闭键盘
                    keyBoardState = 0;
                    imm.hideSoftInputFromWindow(mEdtComment.getWindowToken(), 0);
                    emojie_tv.setSelected(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            main_emoji_layout.setVisibility(View.VISIBLE);
                        }
                    }, 50);
                }
                break;
        }
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

    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }


}
