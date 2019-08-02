package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.TouristBean;
import com.nbhysj.coupon.util.GlideUtil;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/08/02.
 * description:回答适配器
 */

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    List<TouristBean> touristInfoList;
    private Context mContext;
    private WaitForMeToAnswerListener waitForMeToAnswerListener;

    public AnswerAdapter(Context mContext, WaitForMeToAnswerListener waitForMeToAnswerListener) {

        this.mContext = mContext;
        this.waitForMeToAnswerListener = waitForMeToAnswerListener;
    }

    public void setTouristInfoList(List<TouristBean> touristInfoList) {

        this.touristInfoList = touristInfoList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_answer_question_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            TouristBean touristBean = touristInfoList.get(itemPosition);

            GlideUtil.loadImage(mContext,"",holder.mImgUserAvatarPhoto);

            holder.mTvQuestion.setText("");

            holder.mTvQuestionPublishDate.setText("");

            holder.mTvQuestionIgnore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    waitForMeToAnswerListener.setQesutionIgnoreListener(itemPosition);
                }
            });

            holder.mTvQuestionAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    waitForMeToAnswerListener.setQuestionAnswerListener(itemPosition);
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

        //提问者头像
        CircleImageView mImgUserAvatarPhoto;
        //问题
        TextView mTvQuestion;
        //问题发布时间
        TextView mTvQuestionPublishDate;
        //问题忽略
        TextView mTvQuestionIgnore;
        //问题回答
        TextView mTvQuestionAnswer;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgUserAvatarPhoto = itemView.findViewById(R.id.img_user_avatar_photo);
            mTvQuestion = itemView.findViewById(R.id.tv_question);
            mTvQuestionPublishDate = itemView.findViewById(R.id.tv_question_publish_date);
            mTvQuestionIgnore = itemView.findViewById(R.id.tv_question_ignore);
            mTvQuestionAnswer = itemView.findViewById(R.id.tv_question_answer);

        }
    }

    public interface WaitForMeToAnswerListener {

        void setQesutionIgnoreListener(int position);

        void setQuestionAnswerListener(int position);
    }
}
