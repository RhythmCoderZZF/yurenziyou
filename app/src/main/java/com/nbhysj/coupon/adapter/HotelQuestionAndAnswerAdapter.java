package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.widget.glide.GlideApp;
import com.nbhysj.coupon.widget.glide.GlideRoundCornersTransUtils;

import java.util.List;

/**
 * @author hysj created at 2019/4/7.
 * description : 酒店问答适配器
 */
public class HotelQuestionAndAnswerAdapter extends RecyclerView.Adapter<HotelQuestionAndAnswerAdapter.ViewHolder> {

    /**
     * 酒店问答内容
     */
    List<String> mQuestionContentList;
    private Context mContext;

    public HotelQuestionAndAnswerAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setQuestionContentList(List<String> questionContentList) {

        this.mQuestionContentList = questionContentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel_questions_and_answers_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            String questionContent = mQuestionContentList.get(itemPosition);
            holder.mTvQuestionContent.setText(questionContent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mQuestionContentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //酒店问答内容
        public TextView mTvQuestionContent;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvQuestionContent = itemView.findViewById(R.id.tv_question_content);
        }
    }
}
