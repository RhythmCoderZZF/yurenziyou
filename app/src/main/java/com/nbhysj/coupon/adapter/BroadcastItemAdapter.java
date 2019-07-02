package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.ImageData;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.widget.glide.GlideApp;
import com.nbhysj.coupon.widget.glide.GlideRoundCornersTransUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/04/14.
 * description: 广播适配器
 */
public class BroadcastItemAdapter extends RecyclerView.Adapter<BroadcastItemAdapter.ViewHolder> {

    /**
     * 图片列表数据
     */
    private List<ImageData> imageDataList;

    private Context mContext;


    public BroadcastItemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setBroadcastList(List<ImageData> imageDataList) {

        this.imageDataList = imageDataList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = View.inflate(parent.getContext(), R.layout.list_survey_type_answer_item, null);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_broadcast_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {

            ImageData imageData = imageDataList.get(position);
            String imageUrl = imageData.getUrl();
            GlideApp.with(mContext)
                    .load(imageUrl)
                    .placeholder(R.mipmap.icon_placeholder_image)
                    .error(R.mipmap.icon_placeholder_image)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .override(Target.SIZE_ORIGINAL)
                    .transform(new GlideRoundCornersTransUtils(mContext, 20, GlideRoundCornersTransUtils.CornerType.TOP))
                    .into(holder.mImgBroadcast);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return imageDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //广播图片
        @BindView(R.id.image_broadcast)
        ImageView mImgBroadcast;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
