package com.nbhysj.coupon.adapter;

import android.content.Context;
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
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;

import java.net.URLDecoder;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/11/01.
 * description:我的问题适配器
 */
public class MyQuestionListAdapter extends RecyclerView.Adapter<MyQuestionListAdapter.ViewHolder> {


    List<MyQuestionAnsweringBean> myQuestionAnsweringList;
    private Context mContext;
    private WaitForMeToAnswerListener waitForMeToAnswerListener;

    public MyQuestionListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setMyQuestionList(List<MyQuestionAnsweringBean> myQuestionAnsweringList) {

        this.myQuestionAnsweringList = myQuestionAnsweringList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_question_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            MyQuestionAnsweringBean myQuestionAnsweringBean = myQuestionAnsweringList.get(itemPosition);
            String mchName = myQuestionAnsweringBean.getMchName();
            String questionContent = myQuestionAnsweringBean.getQuestionContent();
            String answerContent = myQuestionAnsweringBean.getAnswerContent();
            long answerCtime = myQuestionAnsweringBean.getAnswerCTime();
            String answerAvatar = myQuestionAnsweringBean.getAnswerAvater();
            String answerUserName = myQuestionAnsweringBean.getAnswerUserName();


            if(!TextUtils.isEmpty(mchName))
            {
                holder.mTvMchName.setText(mchName);
            }

            if(!TextUtils.isEmpty(questionContent))
            {
                holder.mTvQuestionContent.setText(questionContent);
            }

            String answerTime = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat,answerCtime);
            holder.mTvAnswerDate.setText(answerTime);

            GlideUtil.loadImage(mContext,answerAvatar,holder.mImgAnswererAvatar);

            if (!TextUtils.isEmpty(answerUserName)) {

                String answererName = URLDecoder.decode(answerUserName, "UTF-8");

                if(!TextUtils.isEmpty(answererName))
                {
                    holder.mTvAnswererName.setText(answererName);
                }

                if(!TextUtils.isEmpty(answerContent)) {
                    holder.mTvAnswererContent.setText(answerContent);
                }

                holder.mLlytAnswererInfo.setVisibility(View.VISIBLE);


                    holder.mTvAnswerNoData.setVisibility(View.GONE);
            } else {
                holder.mTvAnswerNoData.setVisibility(View.VISIBLE);
                holder.mLlytAnswererInfo.setVisibility(View.GONE);
            }

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

        TextView mTvAnswerNoData;
        public ViewHolder(View itemView) {
            super(itemView);

            mTvMchName = itemView.findViewById(R.id.tv_mch_name);
            mTvQuestionContent = itemView.findViewById(R.id.tv_question_content);
            mImgAnswererAvatar = itemView.findViewById(R.id.image_answerer_avatar);
            mTvAnswererName = itemView.findViewById(R.id.tv_answerer_name);
            mTvAnswererContent = itemView.findViewById(R.id.tv_answer_content);
            mTvAnswerDate = itemView.findViewById(R.id.tv_answer_date);
            mLlytAnswererInfo = itemView.findViewById(R.id.llyt_answerer_info);
            mTvAnswerNoData = itemView.findViewById(R.id.tv_answer_no_data);

        }
    }

    public interface WaitForMeToAnswerListener {

        void setQesutionIgnoreListener(int position);

        void setQuestionAnswerListener(int position);
    }
}
