package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CommentReceiveResponse;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/04/14.
 * description:  我的页面收藏(全部)图片配器
 */
public class MineCollectionAllSubItemAdapter extends RecyclerView.Adapter<MineCollectionAllSubItemAdapter.ViewHolder> {

    /**
     * 图片列表数据
     */
    private List<CommentReceiveResponse.CommentEntity> commentSubList;

    private Context mContext;


    public MineCollectionAllSubItemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setCommentReceivedSubItemList(List<CommentReceiveResponse.CommentEntity> commentSubList) {

        this.commentSubList = commentSubList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mine_collection_all_sub_picture_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {

            GlideUtil.loadCornersTransformImage(mContext, "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3093674761,1038215453&fm=26&gp=0.jpg", 5, holder.mImageCollectionAll);

            if (position == 5) {

                holder.mViewMineCollectionAllSubPictureFoot.setVisibility(View.VISIBLE);
            } else {
                holder.mViewMineCollectionAllSubPictureFoot.setVisibility(View.GONE);
            }

            if (position == 0) {
                holder.mViewMineCollectionAllSubPictureHeader.setVisibility(View.VISIBLE);
            } else {
                holder.mViewMineCollectionAllSubPictureHeader.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //收藏有图片
        @BindView(R.id.image_collection_all)
        ImageView mImageCollectionAll;
        @BindView(R.id.view_mine_collection_all_sub_picture_foot)
        View mViewMineCollectionAllSubPictureFoot;
        @BindView(R.id.view_mine_collection_all_sub_picture_header)
        View mViewMineCollectionAllSubPictureHeader;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
