package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.AnswerBean;
import com.nbhysj.coupon.model.response.TouristBean;
import com.nbhysj.coupon.model.response.WaitForMeToAnswerBean;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/11/01.
 * description:待我回答适配器
 */
public class WaitForMeToAnswerAdapter extends RecyclerView.Adapter<WaitForMeToAnswerAdapter.ViewHolder> {

    List<WaitForMeToAnswerBean> waitForMeToAnswerList;
    private Context mContext;
    private WaitForMeToAnswerListener waitForMeToAnswerListener;

    public WaitForMeToAnswerAdapter(Context mContext, WaitForMeToAnswerListener waitForMeToAnswerListener) {

        this.mContext = mContext;
        this.waitForMeToAnswerListener = waitForMeToAnswerListener;
    }

    public void setWaitForMeToAnswerList(List<WaitForMeToAnswerBean> waitForMeToAnswerList) {

        this.waitForMeToAnswerList = waitForMeToAnswerList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wait_me_answer_the_question_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            WaitForMeToAnswerBean waitForMeToAnswerBean = waitForMeToAnswerList.get(itemPosition);
            String userName = waitForMeToAnswerBean.getUserName();
            String avatarUrl = waitForMeToAnswerBean.getAvater();
            String content = waitForMeToAnswerBean.getContent();
            long cTime = waitForMeToAnswerBean.getCtime();
            String questionDate = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat,cTime);
            GlideUtil.loadImage(mContext,avatarUrl,holder.mImgUserAvatarPhoto);
            if(!TextUtils.isEmpty(content)) {
                holder.mTvQuestionContent.setText(content);
            }

            if(!TextUtils.isEmpty(questionDate)) {
                holder.mTvQuestionDate.setText(questionDate);
            }
            if(!TextUtils.isEmpty(userName))
            {
                holder.mTvAnswererName.setText(userName);
            }

            holder.mTvQuestionIgnore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    waitForMeToAnswerListener.setQuestionIgnoreListener(itemPosition);
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
        return waitForMeToAnswerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //提问者头像
        CircleImageView mImgUserAvatarPhoto;
        //问题
        TextView mTvQuestionContent;
        //问题发布时间
        TextView mTvQuestionDate;
        //问题忽略
        TextView mTvQuestionIgnore;
        //问题回答
        TextView mTvQuestionAnswer;
        //回答者
        TextView mTvAnswererName;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgUserAvatarPhoto = itemView.findViewById(R.id.img_user_avatar_photo);
            mTvQuestionContent = itemView.findViewById(R.id.tv_question);
            mTvQuestionDate = itemView.findViewById(R.id.tv_question_date);
            mTvQuestionIgnore = itemView.findViewById(R.id.tv_question_ignore);
            mTvQuestionAnswer = itemView.findViewById(R.id.tv_question_answer);
            mTvAnswererName = itemView.findViewById(R.id.tv_answerer_name);

        }
    }

    public interface WaitForMeToAnswerListener {

        void setQuestionIgnoreListener(int position);

        void setQuestionAnswerListener(int position);
    }
}
