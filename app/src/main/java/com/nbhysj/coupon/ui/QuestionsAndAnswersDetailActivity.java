package com.nbhysj.coupon.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ExpressionAdapter;
import com.nbhysj.coupon.util.SmileyParser;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.View.VISIBLE;
import static com.nbhysj.coupon.ui.CommentsListActivity.showSoftInputFromWindow;

/**
 * @auther：hysj created on 2019/08/02
 * description：问题详情
 */
public class QuestionsAndAnswersDetailActivity extends BaseActivity {

    @BindView(R.id.rv_questions_answers)
    RecyclerView mRvQuestionsAnswers;

    //评论输入框
    @BindView(R.id.edt_comment)
    EditText mEdtComment;

    //表情
    @BindView(R.id.img_emojie)
    ImageView mImgEmojie;

    @BindView(R.id.llyt_main_emoji)
    LinearLayout mLlytMainEmoji;

    @BindView(R.id.viwepager_expression)
    ViewPager vViewPager;
    
    @BindView(R.id.ll_dot_container)
    LinearLayout mLlytDotContainer;

    InputMethodManager mInputMethodManager;

    private SmileyParser mParser;

    private int mKeyBoardState = 1;//0为键盘为弹出，1为弹出
    private List<GridView> gridList = new ArrayList<>();
    List<List<Integer>> caicaiList = new ArrayList<>();//表情2的数据源
    List<List<String>> caicaiTxtList = new ArrayList<>();//表情2的符号数据源
    Map<Integer, List<List<Integer>>> expressionTypeList = new TreeMap<>();//表情总数据源,以图标为key
    private ExpressionAdapter mExpressionAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_questions_and_answers_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(QuestionsAndAnswersDetailActivity.this,getResources().getString(R.string.str_question_and_answer_detail),R.mipmap.icon_left_arrow_black);

        mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        mParser = SmileyParser.getInstance();
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

        mEdtComment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mKeyBoardState = 1;
                mLlytMainEmoji.setVisibility(View.GONE);
                mInputMethodManager.showSoftInput(mEdtComment, 0);
                mImgEmojie.setSelected(false);
                return false;
            }
        });
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
        vViewPager.setAdapter(new QuestionsAndAnswersDetailActivity.EmojiAdapter(gridList));
        gotoInitData(gridList);
    }

    @Override
    public void initPresenter() {

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
        mLlytDotContainer.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(QuestionsAndAnswersDetailActivity.this);
            if (i == 0) {
                imageView.setImageResource(R.drawable.shape_dot_select);

            } else {
                imageView.setImageResource(R.drawable.shape_dot_nomal);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(SmileyParser.dp2px(QuestionsAndAnswersDetailActivity.this, 8),
                    SmileyParser.dp2px(QuestionsAndAnswersDetailActivity.this, 8));
            layoutParams.setMargins(20, 0, 0, 0);
            mLlytDotContainer.addView(imageView, layoutParams);

        }
        if (mLlytDotContainer.getChildCount() <= 1) {
            mLlytDotContainer.setVisibility(View.GONE);
        } else {
            mLlytDotContainer.setVisibility(VISIBLE);
        }
        vViewPager.setOffscreenPageLimit(6);
        vViewPager.setCurrentItem(0);
        vViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mLlytDotContainer.getChildCount(); i++) {
                    if (i != position) {
                        ((ImageView) mLlytDotContainer.getChildAt(i)).setImageResource(R.drawable.shape_dot_nomal);
                    }
                }
                ((ImageView) mLlytDotContainer.getChildAt(position)).setImageResource(R.drawable.shape_dot_select);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @OnClick({R.id.img_emojie})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_publish://确定
                // Toast.makeText(activity, mParser.addSmileySpans(ev1.getText()), Toast.LENGTH_SHORT).show();
                //Log.i("TAG", "onClick: 输入内容"+ev1.getText());
                break;
            case R.id.img_emojie:
                if (mKeyBoardState == 0) {//弹出键盘
                    mKeyBoardState = 1;
                    mLlytMainEmoji.setVisibility(View.GONE);
                    mImgEmojie.setSelected(false);
                    mEdtComment.setFocusable(true);
                    showSoftInputFromWindow(QuestionsAndAnswersDetailActivity.this, mEdtComment);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mInputMethodManager.showSoftInput(mEdtComment, 0);
                        }
                    }, 50);

                } else {//关闭键盘
                    mKeyBoardState = 0;
                    mInputMethodManager.hideSoftInputFromWindow(mEdtComment.getWindowToken(), 0);
                    mImgEmojie.setSelected(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mLlytMainEmoji.setVisibility(View.VISIBLE);
                        }
                    }, 50);
                }
                break;
                default:break;

        }
    }


}
