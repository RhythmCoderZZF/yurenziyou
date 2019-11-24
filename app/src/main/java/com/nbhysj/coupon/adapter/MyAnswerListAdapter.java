package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MyQuestionAnsweringBean;
import com.nbhysj.coupon.model.response.OtherAnswerBean;
import com.nbhysj.coupon.ui.AskAndAnswerDetailActivity;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/11/01.
 * description:我的问题适配器
 */
public class MyAnswerListAdapter extends RecyclerView.Adapter<MyAnswerListAdapter.ViewHolder> {

    List<MyQuestionAnsweringBean> myQuestionAnsweringList;

    private Context mContext;
    private QuestionUsefulListener questionUsefulListener;

    public MyAnswerListAdapter(Context mContext,QuestionUsefulListener questionUsefulListener) {

        this.mContext = mContext;
        this.questionUsefulListener = questionUsefulListener;
    }

    public void setMyQuestionList(List<MyQuestionAnsweringBean> myQuestionAnsweringList) {

        this.myQuestionAnsweringList = myQuestionAnsweringList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_answer_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            MyQuestionAnsweringBean myQuestionAnsweringBean = myQuestionAnsweringList.get(itemPosition);
            int questionId = myQuestionAnsweringBean.getQuestionId();
            String answerNickName = myQuestionAnsweringBean.getAnswerUserName();
            String answerAvatar = myQuestionAnsweringBean.getAnswerAvater();
            long answerCTime = myQuestionAnsweringBean.getAnswerCTime();
            String questionContent = myQuestionAnsweringBean.getQuestionContent();
            String answerContent = myQuestionAnsweringBean.getAnswerContent();
            String mchName = myQuestionAnsweringBean.getMchName();
            String question = URLDecoder.decode(questionContent, "UTF-8");
            holder.mTvQuestionContent.setText(question);
            String answerTime = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat,answerCTime);
            holder.mTvAnswerDate.setText(answerTime);

            GlideUtil.loadImage(mContext,answerAvatar,holder.mImgAnswererAvatar);

            if (!TextUtils.isEmpty(answerNickName)) {

                String answererName = URLDecoder.decode(answerNickName, "UTF-8");
                holder.mTvAnswererName.setText(answererName);

                holder.mTvAnswererContent.setText(answerContent);
            }

            holder.mTvMchName.setText(mchName);

            holder.mTvQuestionUseful.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                  /*  Intent intent = new Intent();
                    intent.setClass(mContext, AskAndAnswerDetailActivity.class);
                    intent.putExtra("questionId",questionId);
                    mContext.startActivity(intent);*/
                    questionUsefulListener.setQuestionUsefulListener(itemPosition);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return myQuestionAnsweringList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //商户名
        TextView mTvMchName;
        //问题
        TextView mTvQuestionContent;
        //回答者头像
        CircleImageView mImgAnswererAvatar;
        //回答者姓名
        TextView mTvAnswererName;
        //回答内容
        TextView mTvAnswererContent;
        //回答时间
        TextView mTvAnswerDate;

        LinearLayout mLlytAnswererInfo;

        //问题有用
        TextView mTvQuestionUseful;
        public ViewHolder(View itemView) {
            super(itemView);

            mTvMchName = itemView.findViewById(R.id.tv_mch_name);
            mTvQuestionContent = itemView.findViewById(R.id.tv_question_content);
            mImgAnswererAvatar = itemView.findViewById(R.id.image_answerer_avatar);
            mTvAnswererName = itemView.findViewById(R.id.tv_answerer_name);
            mTvAnswererContent = itemView.findViewById(R.id.tv_answer_content);
            mTvAnswerDate = itemView.findViewById(R.id.tv_answer_date);
            mLlytAnswererInfo = itemView.findViewById(R.id.llyt_answerer_info);
            mTvQuestionUseful = itemView.findViewById(R.id.tv_question_useful);
        }
    }

    //问题监听
    public interface QuestionUsefulListener {

        void setQuestionUsefulListener(int position);
    }
}
