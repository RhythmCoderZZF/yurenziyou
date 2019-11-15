package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CardBean;
import com.nbhysj.coupon.model.response.HotTagsTopicBean;
import com.nbhysj.coupon.model.response.TagTopicSearchResponse;
import com.nbhysj.coupon.ui.NearbyCardDetailActivity;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.widget.glide.GlideApp;
import com.nbhysj.coupon.widget.glide.GlideRoundCornersTransUtils;

import java.util.List;

public class TagTopicSearchAdapter extends RecyclerView.Adapter<TagTopicSearchAdapter.CardHolder> {

    private List<HotTagsTopicBean> tagTopicSearchList;
    private Context mContent;
    private TagTopicListener tagTopicListener;

    public TagTopicSearchAdapter(Context mContent,TagTopicListener tagTopicListener) {
        this.mContent = mContent;
        this.tagTopicListener = tagTopicListener;
    }

    public void setTagTopicList(List<HotTagsTopicBean> tagTopicSearchList) {

        this.tagTopicSearchList = tagTopicSearchList;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tag_topic_item, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {

        HotTagsTopicBean topicSearchEntity = tagTopicSearchList.get(position);
        holder.mTvTagTopic.setText(topicSearchEntity.getTitle());

        holder.mLlytCreateTopicItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tagTopicListener.setTagTopicListener(topicSearchEntity);

            }
        });
    }

    @Override
    public int getItemCount() {
        return tagTopicSearchList.size();
    }

    static class CardHolder extends RecyclerView.ViewHolder {
        TextView mTvTagTopic;
        LinearLayout mLlytCreateTopicItem;

        public CardHolder(View itemView) {
            super(itemView);
            mTvTagTopic = itemView.findViewById(R.id.tv_topic);
            mLlytCreateTopicItem = itemView.findViewById(R.id.llyt_create_topic_item);
        }
    }

    public interface TagTopicListener{

        void setTagTopicListener(HotTagsTopicBean hotTagsTopicBean);

    }
}
