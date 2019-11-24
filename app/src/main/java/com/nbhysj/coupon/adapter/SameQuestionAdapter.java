package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.AskTogetherBean;
import com.nbhysj.coupon.model.response.AskTogetherResponse;
import com.nbhysj.coupon.model.response.TouristBean;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/08/02.
 * description:同问适配器
 */
public class SameQuestionAdapter extends RecyclerView.Adapter<SameQuestionAdapter.ViewHolder> {

    List<AskTogetherBean> askTogetherList;
    private Context mContext;

    public SameQuestionAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setAskTogetherList(List<AskTogetherBean> askTogetherList) {

        this.askTogetherList = askTogetherList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_the_same_question_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            AskTogetherBean askTogetherBean = askTogetherList.get(itemPosition);
            int followCount = askTogetherBean.getFollowCount();
            int answerCount = askTogetherBean.getAnswerCount();
            String nickName = askTogetherBean.getNickname();
            String questionContent = askTogetherBean.getQuestionContent();
            String avatar = askTogetherBean.getAvater();

            if(!TextUtils.isEmpty(questionContent))
            {
                holder.mTvQuestion.setText(questionContent);
            }

            if(!TextUtils.isEmpty(nickName))
            {
                holder.mTvAnswererName.setText(nickName);
            }

            holder.mTvFollowAndAnswerNum.setText(followCount + "人关注," + answerCount + "个回答");

            GlideUtil.loadImage(mContext,avatar,holder.mImgUserAvatarPhoto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return askTogetherList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //提问者头像
        CircleImageView mImgUserAvatarPhoto;
        //问题
        TextView mTvQuestion;
        //关注数+回答数
        TextView mTvFollowAndAnswerNum;
        TextView mTvAnswererName;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgUserAvatarPhoto = itemView.findViewById(R.id.img_user_avatar_photo);
            mTvQuestion = itemView.findViewById(R.id.tv_question);
            mTvFollowAndAnswerNum = itemView.findViewById(R.id.tv_follow_and_answer_num);
            mTvAnswererName = itemView.findViewById(R.id.tv_answerer_name);
        }
    }
}
