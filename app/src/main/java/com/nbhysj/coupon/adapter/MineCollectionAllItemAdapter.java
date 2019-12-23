package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MineCollectionTypeEnum;
import com.nbhysj.coupon.model.response.CommentReceiveResponse;
import com.nbhysj.coupon.model.response.MineCollectionAllResponse;
import com.nbhysj.coupon.ui.CollectionDetailsActivity;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/05/25.
 * description:  我的页面收藏(全部)适配器
 */
public class MineCollectionAllItemAdapter extends RecyclerView.Adapter<MineCollectionAllItemAdapter.ViewHolder> {

    /**
     * 图片列表数据
     */
    private List<MineCollectionAllResponse> mineCollectionAllList;

    private Context mContext;



    public MineCollectionAllItemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setCollectionAllList(List<MineCollectionAllResponse> mineCollectionAllList) {

        this.mineCollectionAllList = mineCollectionAllList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mine_collection_all_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {

            MineCollectionAllResponse mineCollectionAllResponse = mineCollectionAllList.get(position);
            String type = mineCollectionAllResponse.getType();
            List<String> photosUrl = mineCollectionAllResponse.getPhotos();

            if(photosUrl != null) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
                holder.mRvMineCollectionAll.setLayoutManager(linearLayoutManager);
                MineCollectionAllSubItemAdapter mineCollectionAllSubItemAdapter = new MineCollectionAllSubItemAdapter(mContext, new MineCollectionAllSubItemAdapter.MineCollectionAllItemListener() {
                    @Override
                    public void setMineCollectionAllItemListener(int position) {


                        Intent intent = new Intent();
                        intent.putExtra("type",type);
                        intent.setClass(mContext, CollectionDetailsActivity.class);
                        mContext.startActivity(intent);
                    }
                });
                mineCollectionAllSubItemAdapter.setMineCollectionPhotoUrlList(photosUrl);
                holder.mRvMineCollectionAll.setAdapter(mineCollectionAllSubItemAdapter);
            }

            if(!TextUtils.isEmpty(type))
            {
              String collectionType = MineCollectionTypeEnum.getEnumByKey(type).getValue();
                holder.mTvCollectionTitle.setText(collectionType);
            }

            holder.mTvLookAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.putExtra("type",type);
                    intent.setClass(mContext, CollectionDetailsActivity.class);
                    mContext.startActivity(intent);

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mineCollectionAllList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //我的收藏全部列表
        @BindView(R.id.rv_mine_collection_all)
        RecyclerView mRvMineCollectionAll;
        //查看全部
        @BindView(R.id.tv_look_all)
        TextView mTvLookAll;
        //收藏标题
        @BindView(R.id.tv_collection_title)
        TextView mTvCollectionTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
