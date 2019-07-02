package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.ShopMallHomePageResponse;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/7.
 * description : 房源适配器
 */
public class HouseResourcesAdapter extends RecyclerView.Adapter<HouseResourcesAdapter.ViewHolder> {

    List<ShopMallHomePageResponse.GuessEntity> guessYouLikeList;
    private Context mContext;

    public HouseResourcesAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setGuessYouLikeList(List<ShopMallHomePageResponse.GuessEntity> guessYouLikeList) {

        this.guessYouLikeList = guessYouLikeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_housing_resources_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            ShopMallHomePageResponse.GuessEntity guessEntity = guessYouLikeList.get(itemPosition);
            String photo = guessEntity.getPhoto();
            String mchName = guessEntity.getMchName();
            int mConsumePrice = guessEntity.getConsumePrice();
            GlideUtil.loadCornersTransformImage(mContext, photo, 5, holder.mImgShoppingMallGuessYouLike);
            holder.mTvMchName.setText(mchName);
            holder.mTvPerCapitaPrice.setText(String.valueOf(mConsumePrice));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return guessYouLikeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_guess_you_like)
        ImageView mImgShoppingMallGuessYouLike;
        //价格
        @BindView(R.id.tv_per_capita_price)
        TextView mTvPerCapitaPrice;
        //商户名
        @BindView(R.id.tv_merchant_name)
        TextView mTvMchName;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
