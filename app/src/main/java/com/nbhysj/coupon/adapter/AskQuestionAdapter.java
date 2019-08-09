package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.TouristBean;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/08/02.
 * description:提问适配器
 */
public class AskQuestionAdapter extends RecyclerView.Adapter<AskQuestionAdapter.ViewHolder> {


    List<TouristBean> touristInfoList;
    private Context mContext;
    private AskQuestionListener askQuestionListener;

    public AskQuestionAdapter(Context mContext, AskQuestionListener askQuestionListener) {

        this.mContext = mContext;
        this.askQuestionListener = askQuestionListener;
    }

    public void setTouristInfoList(List<TouristBean> touristInfoList) {

        this.touristInfoList = touristInfoList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ask_question_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            TouristBean touristBean = touristInfoList.get(itemPosition);

            GlideUtil.loadImage(mContext,"",holder.mImgAnswerUserAvatar);

            holder.mTvQuestion.setText("");

            holder.mTvQuestionPublishDate.setText("");

            holder.mLlytAskQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    askQuestionListener.setQesutionDetailListener(itemPosition);
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
        //商户名
        TextView mTvMchName;
        //问题
        TextView mTvQuestion;
        //问题发布时间
        TextView mTvQuestionPublishDate;
        //回答者名字
        TextView mTvAnswererName;
        //回答内容
        TextView mTvAnswerContent;
        LinearLayout mLlytAskQuestion;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgAnswerUserAvatar = itemView.findViewById(R.id.image_answer_user_avatar);
            mTvMchName = itemView.findViewById(R.id.tv_mch_name);
            mTvQuestion = itemView.findViewById(R.id.tv_question);
            mTvQuestionPublishDate = itemView.findViewById(R.id.tv_question_publish_date);
            mTvAnswererName = itemView.findViewById(R.id.tv_answerer_name);
            mTvAnswerContent = itemView.findViewById(R.id.tv_answer_content);
            mLlytAskQuestion = itemView.findViewById(R.id.llyt_ask_question_item);

        }
    }

    public interface AskQuestionListener {

        void setQesutionDetailListener(int position);
    }
}
