package com.nbhysj.coupon.ui;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.OrderCommentPictureAdapter;
import com.nbhysj.coupon.adapter.OrderEvaluateAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.PaymentTypeEnum;
import com.nbhysj.coupon.contract.OrderCommentContract;
import com.nbhysj.coupon.model.CommentModel;
import com.nbhysj.coupon.model.OrderCommentModel;
import com.nbhysj.coupon.model.request.OrderPartialCommentRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.HotTagsTopicBean;
import com.nbhysj.coupon.model.response.MerchantListResponse;
import com.nbhysj.coupon.model.response.OrderCommentMchBean;
import com.nbhysj.coupon.model.response.OrderCommentMchTagBean;
import com.nbhysj.coupon.model.response.OrderGoodsInitResponse;
import com.nbhysj.coupon.model.response.OrderGroupGoodsInitResponse;
import com.nbhysj.coupon.model.response.OrderPaymentResponse;
import com.nbhysj.coupon.model.response.WaitMyAnswerResponse;
import com.nbhysj.coupon.presenter.CommentPresenter;
import com.nbhysj.coupon.presenter.OrderCommentPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.RegularExpressionUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.OrderCommentStarBarView;
import com.nbhysj.coupon.view.StarBarView;
import com.nbhysj.coupon.widget.ToggleButton;
import com.nbhysj.coupon.widget.glide.GifSizeFilter;
import com.nbhysj.coupon.widget.glide.Glide4Engine;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/24
 * description：点评
 */
public class OrderEvaluateActivity extends BaseActivity<OrderCommentPresenter, OrderCommentModel> implements OrderCommentContract.View {
    //照片选取返回code
    private int REQUEST_CODE_POST_PHOTO = 23;
    //订单评价列表
    @BindView(R.id.rv_order_evaluate_picture)
    RecyclerView mRvOrderEvaluatePictureSelect;
    //商户名
    @BindView(R.id.tv_mch_name)
    TextView mTvMchName;
    //订单评论标签
    @BindView(R.id.flowlayout_order_comment)
    TagFlowLayout mTagFlowLayoutOrderComment;
    //商户评分
    @BindView(R.id.starbar_mch_score)
    OrderCommentStarBarView mStarBarMchScore;
    //消费金额
    @BindView(R.id.edt_consume_price)
    EditText mEdtConsumePrice;
    //订单评论描述
    @BindView(R.id.edt_order_comment_des)
    EditText mEdtOrderCommentDes;
    //评分1
    @BindView(R.id.starbar_store_one)
    OrderCommentStarBarView mStarBarOne;
    //评分2
     @BindView(R.id.starbar_store_two)
     OrderCommentStarBarView mStarBarTwo;
    //评分3
    @BindView(R.id.starbar_store_three)
    OrderCommentStarBarView mStarBarThree;

    //匿名评价 0:不匿名  1:匿名
    @BindView(R.id.btn_toggle_anonymous_score)
    ToggleButton mBtnToggleAnonymousScore;
    //订单评论列表
    List<String> orderCommentPictureList;

    private List<Integer> selectTopicIdList;
    //订单商品id
    private int orderGoodsId;

    TagAdapter tagAdapter;

    private Set<Integer> selectPosSet;

    //商户id
    private int mchId;

    private float score;

    private float score1;

    private float score2;

    private float score3;

    //匿名评分
    private int anonymousScore = 1;

    //评论照片选择
    private ArrayList<String> selectedPhotosList;

    private OrderCommentPictureAdapter orderEvaluateAdapter;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_evaluate;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        orderGoodsId = getIntent().getIntExtra("orderGoodsId", 0);


        if (selectTopicIdList == null) {

            selectTopicIdList = new ArrayList<>();
        } else {
            selectTopicIdList.clear();
        }

        if (orderCommentPictureList == null) {

            orderCommentPictureList = new ArrayList<>();
        } else {
            orderCommentPictureList.clear();
        }

        if(selectedPhotosList == null){

            selectedPhotosList = new ArrayList<>();
        } else {
            selectedPhotosList.clear();
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(OrderEvaluateActivity.this, 3);
        gridLayoutManager.setOrientation(gridLayoutManager.VERTICAL);
        mRvOrderEvaluatePictureSelect.addItemDecoration(new RecyclerViewItemDecoration(Tools.dip2px(OrderEvaluateActivity.this, 10)));
        mRvOrderEvaluatePictureSelect.setLayoutManager(gridLayoutManager);

        orderEvaluateAdapter = new OrderCommentPictureAdapter(OrderEvaluateActivity.this, new OrderCommentPictureAdapter.OrderCommentPictureListener() {
            @Override
            public void setPhotoSelectListener(int position) {

                getNotePhotoPicker(MimeType.ofImage(),true);
            }

            @Override
            public void setPhotoDeleteListener(int position) {
                String photo = selectedPhotosList.get(position);
                selectedPhotosList.remove(photo);
                if(selectedPhotosList != null)
                {
                    orderEvaluateAdapter.setOrderCommentPictureList(selectedPhotosList);
                    orderEvaluateAdapter.notifyDataSetChanged();
                }
            }
        });
        orderEvaluateAdapter.setOrderCommentPictureList(orderCommentPictureList);
        mRvOrderEvaluatePictureSelect.setAdapter(orderEvaluateAdapter);
    }
    public void getNotePhotoPicker(Set<MimeType> mimeTypes,boolean capture) {
        AndPermission.with(OrderEvaluateActivity.this)
                .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

                        Matisse.from(OrderEvaluateActivity.this)
                                .choose(mimeTypes, true)
                                .countable(true)
                                .capture(capture)
                                .captureStrategy(
                                        new CaptureStrategy(true, "com.nbhysj.coupon.fileprovider"))
                                .maxSelectable(9)
                                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .gridExpectedSize(
                                        getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                // for glide-V3
//                                            .imageEngine(new GlideEngine())
                                // for glide-V4
                                .imageEngine(new Glide4Engine())
                                .setOnSelectedListener(new OnSelectedListener() {
                                    @Override
                                    public void onSelected(
                                            @NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                                        // DO SOMETHING IMMEDIATELY HERE
                                        Log.e("onSelected", "onSelected: pathList=" + pathList);

                                    }
                                })
                                .originalEnable(true)
                                .maxOriginalSize(10)
                                .setOnCheckedListener(new OnCheckedListener() {
                                    @Override
                                    public void onCheck(boolean isChecked) {
                                        // DO SOMETHING IMMEDIATELY HERE
                                        Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                                    }
                                })
                                .forResult(REQUEST_CODE_POST_PHOTO);
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        Uri packageURI = Uri.parse("package:" + getPackageName());
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        // Toast.makeText(MainActivity.this, "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                    }
                }).start();
    }
    @Override
    public void initData() {

        getOrderGoodsCommentInit();
        mStarBarMchScore.setOnStarChangeListener(new OrderCommentStarBarView.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {
                score = mark;
                showToast(OrderEvaluateActivity.this, mark + "分");
            }
        });
        mStarBarOne.setOnStarChangeListener(new OrderCommentStarBarView.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {
                score1 = mark;
                showToast(OrderEvaluateActivity.this, mark + "分");
            }
        });

        mStarBarTwo.setOnStarChangeListener(new OrderCommentStarBarView.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {
                score2 = mark;
                showToast(OrderEvaluateActivity.this, mark + "分");
            }
        });

        mStarBarThree.setOnStarChangeListener(new OrderCommentStarBarView.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {

                score3 = mark;
                showToast(OrderEvaluateActivity.this, mark + "分");
            }

        });

        mBtnToggleAnonymousScore.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean isToggle) {

                if(isToggle){

                    anonymousScore = 0;

                } else {

                    anonymousScore = 1;

                }
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void orderGoodsCommentResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    showToast(OrderEvaluateActivity.this,"评论成功");
                    EventBus.getDefault().post("commentOprate");
                    OrderEvaluateActivity.this.finish();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                dismissProgressDialog();
                showToast(OrderEvaluateActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void orderGroupGoodsCommentResult(BackResult res) {

    }

    @Override
    public void getOrderGoodsCommentInitResult(BackResult<OrderGoodsInitResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    OrderGoodsInitResponse orderGoodsInitResponse = res.getData();
                    String orderNo = orderGoodsInitResponse.getOrderNo();
                    String goodsType = orderGoodsInitResponse.getGoodsType();
                    OrderCommentMchBean orderCommentMchBean = orderGoodsInitResponse.getMch();
                    mchId = orderCommentMchBean.getMchId();
                    String mchName = orderCommentMchBean.getMchName();

                    //商户名称
                    if (!TextUtils.isEmpty(mchName)) {
                        mTvMchName.setText(mchName);
                    }
                    List<OrderCommentMchTagBean> commentMchTagList = orderCommentMchBean.getMchTag();

                    if (commentMchTagList != null && commentMchTagList.size() > 0) {

                        tagAdapter = new TagAdapter<OrderCommentMchTagBean>(commentMchTagList) {
                            @Override
                            public View getView(FlowLayout parent, int position, OrderCommentMchTagBean orderCommentMchTagBean) {
                                View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_order_comment,
                                        mTagFlowLayoutOrderComment, false);

                                TextView tv = view.findViewById(R.id.tv_flowlayout);
                                String title = orderCommentMchTagBean.getTitle();
                                tv.setText(title);

                                return view;
                            }
                        };
                        mTagFlowLayoutOrderComment.setAdapter(tagAdapter);

                        mTagFlowLayoutOrderComment.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                            @Override
                            public boolean onTagClick(View view, int position, FlowLayout parent) {
                                selectTopicIdList.clear();

                                //view.setVisibility(View.GONE);
                                selectPosSet = mTagFlowLayoutOrderComment.getSelectedList();
                                Iterator it = selectPosSet.iterator();
                                while (it.hasNext()) {
                                    int index = (int) it.next();
                                    int topicId = commentMchTagList.get(index).getId();
                                    selectTopicIdList.add(topicId);

                                }
                                //  Toast.makeText(PublishNoteActivity.this, selectTopicIdList.toString(), Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                dismissProgressDialog();
                showToast(OrderEvaluateActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getOrderGroupGoodsCommentInitResult(BackResult<OrderGroupGoodsInitResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(OrderEvaluateActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.tv_order_comment_publish,R.id.iv_back,R.id.rlyt_order_comment_publish})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_order_comment_publish:


                orderEvaluate();
                break;
            case R.id.iv_back:

                OrderEvaluateActivity.this.finish();

                break;
            case R.id.rlyt_order_comment_publish:
                orderEvaluate();
                break;
            default:
                break;

        }
    }

    //订单评价
    public void orderEvaluate() {

        if (validateInternet()) {
            String consumePrice = mEdtConsumePrice.getText().toString();
            String orderCommentDes = mEdtOrderCommentDes.getText().toString();

            if (TextUtils.isEmpty(orderCommentDes)) {

                showToast(OrderEvaluateActivity.this, "请输入评价描述");
                return;
            }

        /*    if (TextUtils.isEmpty(consumePrice)) {

                showToast(OrderEvaluateActivity.this, "请输入消费金额");
                return;
            }*/

         /*   boolean matcherDecimalNumberFlag = RegularExpressionUtil.isTwoDecimalRemain(consumePrice);
            if(!matcherDecimalNumberFlag)
            {
                showToast(OrderEvaluateActivity.this,"金额格式保留两位小数 例:0.00");
                return;
            }
*/
            showProgressDialog(OrderEvaluateActivity.this);

            OrderPartialCommentRequest orderPartialCommentRequest = new OrderPartialCommentRequest();
            orderPartialCommentRequest.setMchId(mchId);
            if (!TextUtils.isEmpty(consumePrice))
            {
                orderPartialCommentRequest.setConsumePrice(Double.parseDouble(consumePrice));
            }
            orderPartialCommentRequest.setContent(orderCommentDes);
            orderPartialCommentRequest.setOrderGoodsId(orderGoodsId);
            orderPartialCommentRequest.setScore(score);
            orderPartialCommentRequest.setScore1(score1);
            orderPartialCommentRequest.setScore2(score2);
            orderPartialCommentRequest.setScore3(score3);
            orderPartialCommentRequest.setPhoto(selectedPhotosList);
            orderPartialCommentRequest.setTagJson(selectTopicIdList);
            orderPartialCommentRequest.setAnonymousStatus(anonymousScore);

           // System.out.print(orderPartialCommentRequest);
           mPresenter.orderPartialGoodsComment(orderPartialCommentRequest);
        }

    }

    //单个订单评价初始化页面
    public void getOrderGoodsCommentInit() {
        if (validateInternet()) {

            showProgressDialog(OrderEvaluateActivity.this);
            mPresenter.getOrderGoodsCommentInit(orderGoodsId);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (resultCode == RESULT_OK &&
                    requestCode == REQUEST_CODE_POST_PHOTO) {   //从相册中选取图片
                List<String> filePathList = null;
                if (data != null) {

                    filePathList = Matisse.obtainPathResult(data);
                    selectedPhotosList.addAll(filePathList);
                    orderEvaluateAdapter.setOrderCommentPictureList(selectedPhotosList);
                    orderEvaluateAdapter.notifyDataSetChanged();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 为RecyclerView增加间距
     * 预设2列，如果是3列，则左右值不同
     */
    public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
        private int space = 0;
        private int pos;

        public RecyclerViewItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            outRect.top = space;

            //该View在整个RecyclerView中位置。
            pos = parent.getChildAdapterPosition(view);

            //取模

            //两列的左边一列
            if (pos % 2 == 0) {
                outRect.left = space;
                outRect.right = space / 2;
            }

            //两列的右边一列
            if (pos % 2 == 1) {
                outRect.left = space / 2;
                outRect.right = space;
            }
        }
    }
}
