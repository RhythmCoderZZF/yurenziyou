package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MyPostShareBean;
import com.nbhysj.coupon.ui.PostRecommendDetailActivity;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.view.NineGridLayoutView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/10/16.
 * description : 分享适配器
 */
public class SubShareAdapter extends RecyclerView.Adapter<SubShareAdapter.ViewHolder> {


    List<MyPostShareBean> myPostShareList;
    private Context mContext;

    private SubShareListener subShareListener;
    public SubShareAdapter(Context mContext,SubShareListener subShareListener) {

        this.mContext = mContext;
        this.subShareListener = subShareListener;
    }

    public void setPostShareList(List<MyPostShareBean> myPostShareList) {

        this.myPostShareList = myPostShareList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_share_sub_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            MyPostShareBean myPostShareBean = myPostShareList.get(itemPosition);

            int postId = myPostShareBean.getId();
            String dateStr = myPostShareBean.getDayStr();
            String content = myPostShareBean.getContent();
            int zanCount = myPostShareBean.getZanCount();
            int commentCount = myPostShareBean.getCommentCount();
            int collectionCount = myPostShareBean.getCollectionCount();
            List<String> photoUrlList = myPostShareBean.getPhotos();
            int commentStatus = myPostShareBean.getCommentStatus();
            int collectionStatus = myPostShareBean.getCollectionStatus();
            int zanStatus = myPostShareBean.getZanStatus();
            holder.mGvSharePhoto.setUrlList(photoUrlList);
            holder.mGvSharePhoto.setNineGridLayoutView(new NineGridLayoutView.NineGridLayoutViewListener() {
                @Override
                public void setNineGridLayoutView() {

                    Intent intent = new Intent();
                    intent.putExtra("postId",postId);
                    intent.setClass(mContext, PostRecommendDetailActivity.class);
                    mContext.startActivity(intent);
                }
            });

            holder.mTvShareTitle.setText(content);

            String day = DateUtil.toDDStr(dateStr);
            String month = DateUtil.toMMStr(dateStr);

            holder.mTvDay.setText(day);
            holder.mTvMonth.setText(month + "月");

            holder.mTvPostZanNum.setText(String.valueOf(zanCount));
            if(zanStatus == 0) {

                holder.mImgPostZanNum.setImageResource(R.mipmap.icon_my_module_zan_gray);

            } else if(zanStatus == 1){

                holder.mImgPostZanNum.setImageResource(R.mipmap.icon_my_module_zan_red);
            }

            holder.mTvPostCollectionNum.setText(String.valueOf(collectionCount));
            if(collectionStatus == 0) {

                holder.mImgPostCollection.setImageResource(R.mipmap.icon_my_module_collection_gray);

            } else if(collectionStatus == 1){

                holder.mImgPostCollection.setImageResource(R.mipmap.icon_my_module_collection_red);
            }

            holder.mTvPostCollectionNum.setText(String.valueOf(commentCount));

            holder.mRlytMyShareItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.putExtra("postId",postId);
                    intent.setClass(mContext, PostRecommendDetailActivity.class);
                    mContext.startActivity(intent);
                }
            });

            holder.mImgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    subShareListener.setSubShareListener(postId,itemPosition);

                }
            });

            holder.mTvShareMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    subShareListener.setSubShareListener(postId,itemPosition);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return myPostShareList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.gv_share_photo)
        NineGridLayoutView mGvSharePhoto;

        @BindView(R.id.tv_share_title)
        TextView mTvShareTitle;

        //帖子点赞数量
        @BindView(R.id.tv_post_zan_num)
        TextView mTvPostZanNum;

        //点赞
        @BindView(R.id.img_my_module_zan)
        ImageView mImgPostZanNum;

        //帖子收藏数量
        @BindView(R.id.tv_post_collection_num)
        TextView mTvPostCollectionNum;

        //收藏
        @BindView(R.id.img_is_collection_posts)
        ImageView mImgPostCollection;

        //评论数
        @BindView(R.id.tv_post_comment_num)
        TextView mTvPostCommentNum;

        //帖子发布日期
        @BindView(R.id.tv_day)
        TextView mTvDay;

        //帖子发布月份
        @BindView(R.id.tv_month)
        TextView mTvMonth;

        //我的分享
        @BindView(R.id.rlyt_my_share_item)
        RelativeLayout mRlytMyShareItem;

        @BindView(R.id.img_share)
        ImageView mImgShare;

        @BindView(R.id.tv_share_menu)
        TextView mTvShareMenu;
        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface SubShareListener{

        void setSubShareListener(int mPostId,int position);
    }
}
