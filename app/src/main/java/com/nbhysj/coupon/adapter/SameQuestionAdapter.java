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
 * description:同问适配器
 */
public class SameQuestionAdapter extends RecyclerView.Adapter<SameQuestionAdapter.ViewHolder> {


    List<TouristBean> touristInfoList;
    private Context mContext;
    private WaitForMeToAnswerListener waitForMeToAnswerListener;

    public SameQuestionAdapter(Context mContext, WaitForMeToAnswerListener waitForMeToAnswerListener) {

        this.mContext = mContext;
        this.waitForMeToAnswerListener = waitForMeToAnswerListener;
    }

    public void setTouristInfoList(List<TouristBean> touristInfoList) {

        this.touristInfoList = touristInfoList;
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

            TouristBean touristBean = touristInfoList.get(itemPosition);

            GlideUtil.loadImage(mContext,"",holder.mImgUserAvatarPhoto);

            holder.mTvQuestion.setText("");

            holder.mTvAnswererName.setText("");


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

    public interface WaitForMeToAnswerListener {

        void setQesutionIgnoreListener(int position);

        void setQuestionAnswerListener(int position);
    }
}
