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
import com.nbhysj.coupon.model.response.AnswerBean;
import com.nbhysj.coupon.model.response.UserFansFollowBean;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * @author hysj created on 2019/11/14.
 * description: 问提详情回答适配器
 */
public class AnswerDetailSubAdapter extends RecyclerView.Adapter<AnswerDetailSubAdapter.ViewHolder> {

    private List<AnswerBean> answerList;
    private Context mContext;
    private AnswerUsefulListener answerUsefulListener;

    public AnswerDetailSubAdapter(Context mContext, AnswerUsefulListener answerUsefulListener) {

        this.mContext = mContext;
        this.answerUsefulListener = answerUsefulListener;
    }

    public void setAnswerList(List<AnswerBean> answerList) {

        this.answerList = answerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_answer_detail_sub_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            AnswerBean answerBean = answerList.get(position);
            String avatarUrl = answerBean.getAvater();
            String userName = answerBean.getUserName();
            int questionId = answerBean.getQuestionId();
            int zanStatus = answerBean.getZanStatus();
            String content = answerBean.getContent();
            int zanNum = answerBean.getZanNum();
            long ctime = answerBean.getCtime();
            int buyStatus = answerBean.getBuy();
            int adoptStatus = answerBean.getAdoptStatus();

            GlideUtil.loadImage(mContext, avatarUrl, holder.mImgUserAvatar);

            holder.mTvAnswerContent.setText(content);

            holder.mTvUserName.setText(userName);
            String answerTime = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat,ctime);
            holder.mTvAnswerTime.setText(answerTime);

            if (zanStatus == 0) {

                holder.mTvAnswerUseful.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.icon_answer_useful_praise), null, null, null);
                holder.mTvAnswerUseful.setBackgroundResource(R.drawable.bg_stroke_light_blue_radius_thirteen_shape);
                holder.mTvAnswerUseful.setTextColor(mContext.getResources().getColor(R.color.color_text_blue10));
                if(zanNum > 0){

                    holder.mTvAnswerUseful.setText(String.valueOf(zanNum));
                } else {
                    holder.mTvAnswerUseful.setText(mContext.getResources().getString(R.string.str_useful));
                }
            } else if (zanStatus == 1) {

                holder.mTvAnswerUseful.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.mipmap.icon_answer_useful_already_praised), null, null, null);
                holder.mTvAnswerUseful.setBackgroundResource(R.drawable.bg_stroke_radius_ten_gray_shape);
                holder.mTvAnswerUseful.setTextColor(mContext.getResources().getColor(R.color.color_text_gray27));
                holder.mTvAnswerUseful.setText(String.valueOf(zanNum));
            }

            if(buyStatus == 0)
            {
                holder.mTvBuyStatus.setVisibility(View.GONE);
            } else if(buyStatus == 1)
            {
                holder.mTvBuyStatus.setVisibility(View.VISIBLE);
            }

            if(adoptStatus == 0)
            {
                holder.mImgAnswerAdopted.setVisibility(View.GONE);
            } else if(adoptStatus == 1)
            {
                holder.mImgAnswerAdopted.setVisibility(View.VISIBLE);
            }

            holder.mTvAnswerUseful.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    answerUsefulListener.setAnswerUsefulListener(position,answerBean);
                }
            });

            holder.mTvAnswerAdopt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    answerUsefulListener.setAnswerAdoptListener(position,answerBean);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //用户头像
        @BindView(R.id.image_user_avatar)
        CircleImageView mImgUserAvatar;

        //用户名
        @BindView(R.id.tv_answerer)
        TextView mTvUserName;

        //回答时间
        @BindView(R.id.tv_answer_time)
        TextView mTvAnswerTime;

        //问答有用
        @BindView(R.id.tv_answer_useful)
        TextView mTvAnswerUseful;

        //问题内容
        @BindView(R.id.tv_answer_content)
        TextView mTvAnswerContent;

        //购买状态
        @BindView(R.id.tv_buy_status)
        TextView mTvBuyStatus;

        //回答采纳
        @BindView(R.id.img_answer_adopted)
        ImageView mImgAnswerAdopted;

        //采纳问题
        @BindView(R.id.tv_answer_adopt)
        TextView mTvAnswerAdopt;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface AnswerUsefulListener {

        void setAnswerAdoptListener(int position,AnswerBean answerBean);

        void setAnswerUsefulListener(int position,AnswerBean answerBean);

    }
}
