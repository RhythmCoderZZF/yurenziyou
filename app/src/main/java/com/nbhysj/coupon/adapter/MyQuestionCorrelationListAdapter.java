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
import com.nbhysj.coupon.model.response.AnswerBean;
import com.nbhysj.coupon.model.response.MyQuestionAnsweringBean;
import com.nbhysj.coupon.model.response.QuestionDetailsBean;
import com.nbhysj.coupon.ui.MyAnswerDetailActivity;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;

import java.net.URLDecoder;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/11/01.
 * description:我的问题(问答详情 相关问题)适配器
 */
public class MyQuestionCorrelationListAdapter extends RecyclerView.Adapter<MyQuestionCorrelationListAdapter.ViewHolder> {

    List<AnswerBean> myQuestionCorrelationList;

    private Context mContext;
    private QuestionUsefulListener questionUsefulListener;

    public MyQuestionCorrelationListAdapter(Context mContext, QuestionUsefulListener questionUsefulListener) {

        this.mContext = mContext;
        this.questionUsefulListener = questionUsefulListener;
    }

    public void setMyQuestionList(List<AnswerBean> myQuestionCorrelationList) {

        this.myQuestionCorrelationList = myQuestionCorrelationList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_question_detail_correlation_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            AnswerBean myQuestionAnsweringBean = myQuestionCorrelationList.get(itemPosition);
            int questionId = myQuestionAnsweringBean.getQuestionId();
            String questionerName = myQuestionAnsweringBean.getUserName();
            String answerAvatar = myQuestionAnsweringBean.getAvater();
            long answerCTime = myQuestionAnsweringBean.getCtime();
            String questionContent = myQuestionAnsweringBean.getContent();
            String question = URLDecoder.decode(questionContent, "UTF-8");

            GlideUtil.loadImage(mContext,answerAvatar,holder.mImgQuestionerAvatar);

                String questioner = URLDecoder.decode(questionerName, "UTF-8");
                holder.mTvQuestionerName.setText(questioner);

                holder.mTvQuestionContent.setText(question);

            holder.mTvViewAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, MyAnswerDetailActivity.class);
                    intent.putExtra("questionId",questionId);
                    mContext.startActivity(intent);
                  //  questionUsefulListener.setQuestionUsefulListener(itemPosition);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return myQuestionCorrelationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //问题
        TextView mTvQuestionContent;
        //回答者头像
        CircleImageView mImgQuestionerAvatar;
        //提问者姓名
        TextView mTvQuestionerName;
        //回答内容
        TextView mTvAnswererContent;

        TextView mTvViewAnswer;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvQuestionContent = itemView.findViewById(R.id.tv_question_content);
            mImgQuestionerAvatar = itemView.findViewById(R.id.image_questioner_avatar);
            mTvQuestionerName = itemView.findViewById(R.id.tv_questioner_name);
            mTvAnswererContent = itemView.findViewById(R.id.tv_answer_content);
            mTvViewAnswer = itemView.findViewById(R.id.tv_view_answer);
        }
    }

    //问题监听
    public interface QuestionUsefulListener {

        void setQuestionUsefulListener(int position);
    }
}
