package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.OtherAnswerBean;
import com.nbhysj.coupon.model.response.WaitMyAnswerBean;
import com.nbhysj.coupon.model.response.WaitMyAnswerResponse;
import com.nbhysj.coupon.ui.AskAndAnswerDetailActivity;
import com.nbhysj.coupon.ui.LetMeAnswerActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.GlideImageView;

import java.net.URLDecoder;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/4/10
 * description : 更多问题适配器
 */
public class MoreQuestionListAdapter extends RecyclerView.Adapter<MoreQuestionListAdapter.ViewHolder> {

    /**
     * 商户问答  更多问题
     */
    List<WaitMyAnswerBean> mQuestionContentList;
    private Context mContext;

    public MoreQuestionListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setMoreQuestionList(List<WaitMyAnswerBean> waitMyAnswerList) {

        this.mQuestionContentList = waitMyAnswerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_more_questions_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            WaitMyAnswerBean waitMyAnswerEntity = mQuestionContentList.get(itemPosition);

            int questionId = waitMyAnswerEntity.getQuestionId();
            String answerNickName = waitMyAnswerEntity.getAnswerNickName();
            String answerAvatar = waitMyAnswerEntity.getAnswerAvater();
            String questionDate = waitMyAnswerEntity.getQuestionDate();
            int answerStatus = waitMyAnswerEntity.getHaveAnswerStatus();
            String questionContent = waitMyAnswerEntity.getQuestionContent();
            String answerContent = waitMyAnswerEntity.getAnswerContent();
            List<OtherAnswerBean> otherAnswerBeanList = waitMyAnswerEntity.getOtherAnswer();
            String question = URLDecoder.decode(questionContent, "UTF-8");
            holder.mTvQuestionContent.setText(question);
            if (!TextUtils.isEmpty(answerNickName)) {

                String answererName = URLDecoder.decode(answerNickName, "UTF-8");
             //   String username = new String(answererName.getBytes(),"GBK");

                holder.mTvAnswerer.setText(answererName);

                holder.mTvAnswerContent.setText(answerContent);
                holder.mLlytAnswerInfo.setVisibility(View.VISIBLE);
                if (otherAnswerBeanList != null && otherAnswerBeanList.size() > 0) {
                    int otherAnawerNum = otherAnswerBeanList.size();

                    if (otherAnawerNum > 0)
                    {
                        holder.mTvOtherAnswerNum.setText("查看其它" + otherAnswerBeanList.size() + "个回答");
                    }
                } else {

                    holder.mTvOtherAnswerNum.setText("暂时还没有人回答过该问题");
                }
            } else {
                holder.mTvOtherAnswerNum.setText("暂时还没有人回答过该问题");
                holder.mLlytAnswerInfo.setVisibility(View.GONE);
            }

            holder.mQuestionTime.setText(questionDate + "提问");

            if (!TextUtils.isEmpty(answerAvatar)) {

                GlideUtil.loadImage(mContext, answerAvatar, holder.mImgUserAvatar);
            }

            holder.mLlytMoreQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                  /*  Intent intent = new Intent();
                    intent.setClass(mContext, AskAndAnswerDetailActivity.class);
                    intent.putExtra("questionId",questionId);
                    mContext.startActivity(intent);*/

                }
            });

            holder.mTvLetMeAsk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, AskAndAnswerDetailActivity.class);
                    intent.putExtra("questionId", questionId);
                    mContext.startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mQuestionContentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //问题内容
        @BindView(R.id.tv_question_content)
        TextView mTvQuestionContent;

        //回答者
        @BindView(R.id.tv_answerer)
        TextView mTvAnswerer;

        //回答内容
        @BindView(R.id.tv_answer_content)
        TextView mTvAnswerContent;

        @BindView(R.id.tv_other_answer_num)
        TextView mTvOtherAnswerNum;

        //提问时间
        @BindView(R.id.tv_question_time)
        TextView mQuestionTime;

        //用户头像
        @BindView(R.id.img_user_avatar)
        CircleImageView mImgUserAvatar;

        //查看其他回答数量
        @BindView(R.id.llyt_more_question)
        LinearLayout mLlytMoreQuestion;

        //回答
        @BindView(R.id.tv_let_me_ask)
        TextView mTvLetMeAsk;

        //回答者信息
        @BindView(R.id.llyt_answer_info)
        LinearLayout mLlytAnswerInfo;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
