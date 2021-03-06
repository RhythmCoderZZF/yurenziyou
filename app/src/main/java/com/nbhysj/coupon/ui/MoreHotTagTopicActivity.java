package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.TagTopicSearchAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.HotTagsTypeEnum;
import com.nbhysj.coupon.contract.PublishPostContract;
import com.nbhysj.coupon.model.PublishPostModel;
import com.nbhysj.coupon.model.request.TopicRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HotTagsTopicBean;
import com.nbhysj.coupon.model.response.MerchantListResponse;
import com.nbhysj.coupon.model.response.TagTopicSearchResponse;
import com.nbhysj.coupon.model.response.TopicResponse;
import com.nbhysj.coupon.presenter.PublishPostPresenter;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.blurbehind.BlurBehind;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * @auther：hysj created on 2019/03/02
 * description：分享发布(更多热门标签)
 */
public class MoreHotTagTopicActivity extends BaseActivity<PublishPostPresenter, PublishPostModel> implements PublishPostContract.View {

    //推荐位置列表
    @BindView(R.id.rv_recommend_topic)
    RecyclerView mRvRecommendTopic;
    //创建自定义主题
    @BindView(R.id.llyt_create_topic)
    LinearLayout mLlytCreateTopic;
    //搜索更多话题
    @BindView(R.id.et_search_topic)
    EditText mEdtSearchTopic;
    //自定义主题
    @BindView(R.id.tv_topic)
    TextView mTvTopicCreate;
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    //取消
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    //为您推荐标签
    @BindView(R.id.tv_recommend_topic_tag)
    TextView mTvRecommendTopicTag;
    private String mTopicParam;
    private List<HotTagsTopicBean> tagTopicSearchList;
    private TagTopicSearchAdapter tagTopicSearchAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_more_hot_tag_topic;
    }

    @Override
    public void initView(Bundle savedInstanceState) {


        BlurBehind.getInstance()
                .withAlpha(100)
                .withFilterColor(Color.parseColor("#000000"))
                .setBackground(MoreHotTagTopicActivity.this);
        //沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }


        if (tagTopicSearchList == null) {

            tagTopicSearchList = new ArrayList<>();

        } else {

            tagTopicSearchList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MoreHotTagTopicActivity.this);
        mRvRecommendTopic.setLayoutManager(linearLayoutManager);

        tagTopicSearchAdapter = new TagTopicSearchAdapter(MoreHotTagTopicActivity.this, new TagTopicSearchAdapter.TagTopicListener() {
            @Override
            public void setTagTopicListener(HotTagsTopicBean hotTagsTopicBean) {

                Bundle bundle = new Bundle();
                Intent intent = new Intent();
                bundle.putSerializable("topicResponse",hotTagsTopicBean);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();

            }
        });
        tagTopicSearchAdapter.setTagTopicList(tagTopicSearchList);
        mRvRecommendTopic.setAdapter(tagTopicSearchAdapter);

    }

    @Override
    public void initData() {
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchTopic();
        mEdtSearchTopic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTopicParam = charSequence.toString().trim();
                searchTopic();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void publishPostResult(BackResult res) {

    }

    @Override
    public void getMerchantListResult(BackResult<MerchantListResponse> res) {

    }

    @Override
    public void getHotTagsTopicListResult(ResponseBody res) {

    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(MoreHotTagTopicActivity.this, Constants.getResultMsg(msg));
    }

    @Override
    public void topicSearchResult(BackResult<TagTopicSearchResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    mLlytCreateTopic.setVisibility(View.VISIBLE);
                    mTvRecommendTopicTag.setVisibility(View.GONE);
                    TagTopicSearchResponse tagTopicSearch = res.getData();
                    List<HotTagsTopicBean> tagTopicSearchList = tagTopicSearch.getResult();
                    String searchTopic = mEdtSearchTopic.getText().toString().trim();
                    if(tagTopicSearchList != null && tagTopicSearchList.size() > 0)
                    {
                        mLlytCreateTopic.setVisibility(View.GONE);
                        mRvRecommendTopic.setVisibility(View.VISIBLE);
                        mTvRecommendTopicTag.setVisibility(View.VISIBLE);


                        if (tagTopicSearchList.size() > 0) {
                            for (HotTagsTopicBean tagTopicSearchEntity : tagTopicSearchList) {
                                String title = tagTopicSearchEntity.getTitle();
                                if (title.equals(searchTopic)) {

                                    mLlytCreateTopic.setVisibility(View.VISIBLE);
                                    mRvRecommendTopic.setVisibility(View.GONE);
                                    mTvRecommendTopicTag.setVisibility(View.GONE);

                                }
                            }
                        }
                        tagTopicSearchAdapter.setTagTopicList(tagTopicSearchList);
                        tagTopicSearchAdapter.notifyDataSetChanged();
                    } else {
                        mTvTopicCreate.setText(searchTopic);
                        mLlytCreateTopic.setVisibility(View.VISIBLE);
                        mRvRecommendTopic.setVisibility(View.GONE);
                        mTvRecommendTopicTag.setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(MoreHotTagTopicActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void createTopicResult(BackResult<HotTagsTopicBean> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    HotTagsTopicBean topicResponse = res.getData();
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent();
                    bundle.putSerializable("topicResponse",topicResponse);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK,intent);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:
                onReLogin("");
                break;
            default:
                showToast(MoreHotTagTopicActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    //主题搜索
    public void searchTopic() {

        if (validateInternet()) {

            mPresenter.topicSearch(HotTagsTypeEnum.TOPIC.getValue(), mTopicParam);
        }
    }

    //创建主题
    public void createTopic() {

        if (validateInternet()) {

            if(TextUtils.isEmpty(mTopicParam)){

                showToast(MoreHotTagTopicActivity.this,"输入需要创建的话题");
                return;
            }

            showProgressDialog(MoreHotTagTopicActivity.this);
            mDialog.setTitle("正在创建主题...");
            TopicRequest topicRequest = new TopicRequest();
            topicRequest.setTitle(mTopicParam);
            topicRequest.setIntro(mTopicParam);
            mPresenter.createTopic(topicRequest);
        }
    }

    @OnClick({R.id.llyt_create_topic})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.llyt_create_topic:
                String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
                if (!TextUtils.isEmpty(token)) {

                    createTopic();

                } else {
                    onReLogin("");
                }
                break;
            default:break;
        }
    }
}
