package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.TagBaseEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by hysj at 2019/05/21.
 * description：商城星级筛选
 */
public class ShopMallScreenStarClassAdapter extends RecyclerView.Adapter<ShopMallScreenStarClassAdapter.ViewHolder> {
    private Context context;
    private List<TagBaseEntity> tagList;
    private ShopMallScreenStarClassListener shopMallScreenStarClassListener;


    public ShopMallScreenStarClassAdapter(Context context, ShopMallScreenStarClassListener shopMallScreenStarClassListener) {
        this.context = context;
        this.shopMallScreenStarClassListener = shopMallScreenStarClassListener;
    }

    public void setTagList(List<TagBaseEntity> tagList) {
        this.tagList = tagList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shop_mall_star_class_screen_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TagBaseEntity tagBaseBean = tagList.get(position);
        holder.mTvStarClassSelectItem.setText(tagBaseBean.getTitle());
        holder.mTvStarClassSelectItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (TagBaseEntity tag : tagList) {
                    tag.setSelect(false);
                }
                int tagId = tagBaseBean.getId();
                shopMallScreenStarClassListener.setShopMallScreenStarClassListener(tagBaseBean);
                tagBaseBean.setSelect(true);
                notifyDataSetChanged();
            }
        });

        if (tagBaseBean.isSelect()) {
            holder.mTvStarClassSelectItem.setTextColor(context.getResources().getColor(R.color.color_drak_green2));
            holder.mTvStarClassSelectItem.setBackgroundResource(R.mipmap.bg_star_class_select_item);
        } else {
            holder.mTvStarClassSelectItem.setTextColor(context.getResources().getColor(R.color.text_black));
            holder.mTvStarClassSelectItem.setBackgroundResource(R.drawable.bg_radius_five_gray_shape);
        }

    }

    @Override
    public int getItemCount() {
        return tagList.size();
    }

/*

    class ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.delete)
        ImageView delete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
*/


    public static class ViewHolder extends RecyclerView.ViewHolder {

        //星级选中
        @BindView(R.id.tv_star_class_select_item)
        TextView mTvStarClassSelectItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ShopMallScreenStarClassListener {

        void setShopMallScreenStarClassListener(TagBaseEntity tagBaseBean);

    }
}
