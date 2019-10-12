package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.OrderCommentMchBean;
import com.nbhysj.coupon.model.response.OrderCommentMchTagBean;
import com.nbhysj.coupon.ui.OrderEvaluateActivity;
import com.nbhysj.coupon.util.RegularExpressionUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.OrderCommentStarBarView;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.view.StarBarView;
import com.nbhysj.coupon.widget.ToggleButton;
import com.zhihu.matisse.MimeType;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;


/**
 * created by hysj on 2019/10/07.
 * description: 订单评论图片适配器
 */
public class OrderEvaluateAdapter extends RecyclerView.Adapter<OrderEvaluateAdapter.ViewHolder> {

    private Context mContext;

    private OrderCommentListener orderCommentListener;

    private OrderCommentPictureAdapter orderCommentPictureAdapter;

    private List<OrderCommentMchBean> orderCommentMchList;

    private Set<Integer> selectPosSet;

    private List<Integer> selectTagIdList;

    private List<String> orderCommentPictureList;

    private TagAdapter tagAdapter;

    public OrderEvaluateAdapter(Context mContext, OrderCommentListener orderCommentListener) {

        this.mContext = mContext;
        this.orderCommentListener = orderCommentListener;

        if (selectTagIdList == null) {

            selectTagIdList = new ArrayList<>();
        } else {
            selectTagIdList.clear();
        }

    }

    public void setOrderCommentMchList(List<OrderCommentMchBean> orderCommentMchList) {

        this.orderCommentMchList = orderCommentMchList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = View.inflate(parent.getContext(), R.layout.list_survey_type_answer_item, null);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_evaluate, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int orderGroupPosition) {

        try {

            OrderCommentMchBean orderCommentMchBean = orderCommentMchList.get(orderGroupPosition);
            int mchId = orderCommentMchBean.getMchId();
            List<Integer> tagJson = orderCommentMchBean.getTagJson();
            String mchName = orderCommentMchBean.getMchName();

            holder.mTvMchName.setText(mchName);

           float score = orderCommentMchBean.getScore();
           holder.mStarBarViewMchScore.setStarMark(score);

            float score1 = orderCommentMchBean.getScore1();
            holder.mStarBarViewScoreOne.setStarMark(score1);

            float score2 = orderCommentMchBean.getScore2();
            holder.mStarBarViewScoreTwo.setStarMark(score2);

            float score3 = orderCommentMchBean.getScore3();
            holder.mStarBarViewScoreThree.setStarMark(score3);

            //消费金额
            double consumePrice = orderCommentMchBean.getConsumePrice();

            if(consumePrice == 0)
            {
                holder.mEdtConsumePrice.setText("");

            } else {
                holder.mEdtConsumePrice.setText(String.valueOf(consumePrice));
            }

            //商户标签
            List<OrderCommentMchTagBean> commentMchTagsList = orderCommentMchBean.getMchTag();

            if (commentMchTagsList != null && commentMchTagsList.size() > 0) {

                tagAdapter = new TagAdapter<OrderCommentMchTagBean>(commentMchTagsList) {
                    @Override
                    public View getView(FlowLayout parent, int position, OrderCommentMchTagBean orderCommentMchTagBean) {
                        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_order_comment,
                                holder.mTagFlowLayoutOrderComment, false);

                        TextView tv = view.findViewById(R.id.tv_flowlayout);
                        String title = orderCommentMchTagBean.getTitle();
                        tv.setText(title);

                        return view;
                    }
                };
                holder.mTagFlowLayoutOrderComment.setAdapter(tagAdapter);

                holder.mTagFlowLayoutOrderComment.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        selectTagIdList.clear();
                        selectPosSet = holder.mTagFlowLayoutOrderComment.getSelectedList();
                        Iterator it = selectPosSet.iterator();
                        while (it.hasNext()) {
                            int index = (int) it.next();
                            int topicId = commentMchTagsList.get(index).getId();
                            selectTagIdList.add(topicId);
                            orderCommentMchBean.setTagJson(selectTagIdList);
                        }
                        return true;
                    }
                });
            }

            if(tagJson != null && tagJson.size() > 0) {
                if (commentMchTagsList != null && commentMchTagsList.size() > 0) {
                    for (int i = 0; i < commentMchTagsList.size(); i++) {
                        int id = commentMchTagsList.get(i).getId();
                        for (int j = 0; j < tagJson.size(); j++) {
                            int selectTopicId = tagJson.get(j);
                            if (id == selectTopicId) {
                                tagAdapter.setSelectedList(i);
                            }
                        }
                    }
                }
            }
            String content = orderCommentMchBean.getContent();
            if(!TextUtils.isEmpty(content)){
                holder.mEdtOrderCommentDes.setText(content);
            } else {
                holder.mEdtOrderCommentDes.setText("");
            }



            orderCommentPictureList = orderCommentMchBean.getPhoto();

            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
            gridLayoutManager.setOrientation(gridLayoutManager.VERTICAL);
            holder.mRvOrderEvaluatePicture.addItemDecoration(new RecyclerViewItemDecoration(Tools.dip2px(mContext, 10)));
            holder.mRvOrderEvaluatePicture.setLayoutManager(gridLayoutManager);

            orderCommentPictureAdapter = new OrderCommentPictureAdapter(mContext, new OrderCommentPictureAdapter.OrderCommentPictureListener() {
                @Override
                public void setPhotoSelectListener(int picturePosition) {

                    orderCommentListener.setPictureSelectListener(orderGroupPosition, picturePosition);
                }

                @Override
                public void setPhotoDeleteListener(int picturePosition) {

                    orderCommentListener.setPictureDeleteListener(orderGroupPosition, picturePosition);
                }
            });
            orderCommentPictureAdapter.setOrderCommentPictureList(orderCommentPictureList);
            holder.mRvOrderEvaluatePicture.setAdapter(orderCommentPictureAdapter);

            holder.mStarBarViewMchScore.setOnStarChangeListener(new OrderCommentStarBarView.OnStarChangeListener() {
                @Override
                public void onStarChange(float mark) {
                    orderCommentMchBean.setScore(mark);
                }
            });
            holder.mStarBarViewScoreOne.setOnStarChangeListener(new OrderCommentStarBarView.OnStarChangeListener() {
                @Override
                public void onStarChange(float mark) {
                    orderCommentMchBean.setScore1(mark);
                }
            });

            holder.mStarBarViewScoreTwo.setOnStarChangeListener(new OrderCommentStarBarView.OnStarChangeListener() {
                @Override
                public void onStarChange(float mark) {
                    orderCommentMchBean.setScore2(mark);
                }
            });

            holder.mStarBarViewScoreThree.setOnStarChangeListener(new OrderCommentStarBarView.OnStarChangeListener() {
                @Override
                public void onStarChange(float mark) {

                    orderCommentMchBean.setScore3(mark);
                }

            });

            holder.mToggleBtnAnonymousScore.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
                @Override
                public void onToggle(boolean isToggle) {

                    if (isToggle) {

                        orderCommentMchBean.setAnonymousStatus(1); //匿名

                    } else {

                        orderCommentMchBean.setAnonymousStatus(0); //不匿名

                    }
                }
            });

            holder.mEdtOrderCommentDes.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String orderEvaluateDes = charSequence.toString().trim();
                    orderCommentMchBean.setContent(orderEvaluateDes);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            holder.mEdtConsumePrice.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String mConsumePrice = charSequence.toString().trim();

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String consumePrice = editable.toString().trim();
                    if (!TextUtils.isEmpty(consumePrice)) {

                        boolean matcherDecimalNumberFlag = RegularExpressionUtil.isTwoDecimalRemain(consumePrice);
                        if(!matcherDecimalNumberFlag)
                        {
                            //showToast(OrderEvaluateActivity.this,"金额格式保留两位小数 例:0.00");
                            orderCommentListener.setConsumePriceEditTipListener();
                            return;
                        }
                        orderCommentMchBean.setConsumePrice(Double.parseDouble(consumePrice));
                    }


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return orderCommentMchList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //商户名称
        TextView mTvMchName;

        //星级评分
        OrderCommentStarBarView mStarBarViewMchScore;

        //星级评分1
        OrderCommentStarBarView mStarBarViewScoreOne;

        //星级评分2
        OrderCommentStarBarView mStarBarViewScoreTwo;

        //星级评分3
        OrderCommentStarBarView mStarBarViewScoreThree;

        //订单评论标签
        TagFlowLayout mTagFlowLayoutOrderComment;

        //消费金额
        EditText mEdtConsumePrice;

        //订单评价图文描述
        EditText mEdtOrderCommentDes;

        //匿名选择
        ToggleButton mBtnToggleAnonymous;

        RecyclerView mRvOrderEvaluatePicture;

        //匿名评分 0:匿名 1:不匿名
        ToggleButton mToggleBtnAnonymousScore;


        public ViewHolder(View itemView) {
            super(itemView);

            mTvMchName = itemView.findViewById(R.id.tv_mch_name);
            mStarBarViewMchScore = itemView.findViewById(R.id.starbar_mch_score);
            mStarBarViewScoreOne = itemView.findViewById(R.id.starbar_store_one);
            mStarBarViewScoreTwo = itemView.findViewById(R.id.starbar_store_two);
            mStarBarViewScoreThree = itemView.findViewById(R.id.starbar_store_three);
            mTagFlowLayoutOrderComment = itemView.findViewById(R.id.flowlayout_order_comment);
            mEdtConsumePrice = itemView.findViewById(R.id.edt_consume_price);
            mEdtOrderCommentDes = itemView.findViewById(R.id.edt_order_comment_des);
            mBtnToggleAnonymous = itemView.findViewById(R.id.btn_toggle_anonymous_score);
            mRvOrderEvaluatePicture = itemView.findViewById(R.id.rv_order_evaluate_picture);
            mToggleBtnAnonymousScore = itemView.findViewById(R.id.btn_toggle_anonymous_score);
        }
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

    /**
     * 适配监听
     */
    public interface OrderCommentListener {

        void setPictureSelectListener(int orderGroupPosition, int picturePosition);

        void setPictureDeleteListener(int orderGroupPosition, int picturePosition);

        void setConsumePriceEditTipListener();
    }
}
