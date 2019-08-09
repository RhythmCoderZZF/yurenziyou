package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.TouristBean;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/08/03.
 * description:问答详情(其他相关问题)适配器
 */
public class OtherRelatedIssuesAdapter extends RecyclerView.Adapter<OtherRelatedIssuesAdapter.ViewHolder> {

    List<TouristBean> touristInfoList;
    private Context mContext;
    private QuestionAndAnswerDetailsAnswerListener questionAndAnswerDetailsAnswerListener;

    public OtherRelatedIssuesAdapter(Context mContext, QuestionAndAnswerDetailsAnswerListener questionAndAnswerDetailsAnswerListener) {

        this.mContext = mContext;
        this.questionAndAnswerDetailsAnswerListener = questionAndAnswerDetailsAnswerListener;
    }

    public void setTouristInfoList(List<TouristBean> touristInfoList) {

        this.touristInfoList = touristInfoList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_question_and_answer_detail_answer_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            TouristBean touristBean = touristInfoList.get(itemPosition);

            GlideUtil.loadImage(mContext,"",holder.mImgAnswerUserAvatar);

            holder.mTvAnswerDate.setText("");

            holder.mTvQuestionAdopt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    questionAndAnswerDetailsAnswerListener.setQuestionAdoptListener(itemPosition);
                }
            });


            holder.mTvQuestionUseful.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    questionAndAnswerDetailsAnswerListener.setQuestionUsefulListener(itemPosition);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {

        return touristInfoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //回答问题者头像
        CircleImageView mImgAnswerUserAvatar;
        //已采纳标签
        ImageView mImgAdoptedLabel;
        //问题回答时间
        TextView mTvAnswerDate;
        //回答者名字
        TextView mTvAnswererName;
        //回答内容
        TextView mTvAnswerContent;
        //问题采纳
        TextView mTvQuestionAdopt;
        //问题有用
        TextView mTvQuestionUseful;
        LinearLayout mLlytAskQuestion;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgAnswerUserAvatar = itemView.findViewById(R.id.image_answer_user_avatar);
            mImgAdoptedLabel = itemView.findViewById(R.id.img_adopted_label);
            mTvAnswerDate = itemView.findViewById(R.id.tv_answer_date);
            mTvAnswererName = itemView.findViewById(R.id.tv_answerer_name);
            mTvAnswerContent = itemView.findViewById(R.id.tv_answer_content);
            mTvQuestionAdopt = itemView.findViewById(R.id.tv_question_adopt);
            mTvQuestionUseful = itemView.findViewById(R.id.tv_question_useful);
            mLlytAskQuestion = itemView.findViewById(R.id.llyt_ask_question_item);

        }
    }

    public interface QuestionAndAnswerDetailsAnswerListener {

        void setQuestionAdoptListener(int position);

        void setQuestionUsefulListener(int position);
    }
}
