package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.OrderDetailGuessBean;
import com.nbhysj.coupon.model.response.ShopMallHomePageResponse;
import com.nbhysj.coupon.ui.FoodDetailActivity;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/10/03.
 * description : 订单详情猜你喜欢适配器
 */
public class OrderDetailGuessYouLikeAdapter extends RecyclerView.Adapter<OrderDetailGuessYouLikeAdapter.ViewHolder> {

    List<OrderDetailGuessBean> orderDetailGuessList;
    private Context mContext;

    public OrderDetailGuessYouLikeAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setGuessYouLikeList(List<OrderDetailGuessBean> orderDetailGuessList) {

        this.orderDetailGuessList = orderDetailGuessList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_shopping_mall_guess_you_like_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            OrderDetailGuessBean guessEntity = orderDetailGuessList.get(itemPosition);
            String photo = guessEntity.getPhoto();
            int mchId = guessEntity.getMchId();
            String mchName = guessEntity.getMchName();
            double mConsumePrice = guessEntity.getPrice();
            String mchType = guessEntity.getMchType();
            GlideUtil.loadImage(mContext, photo, holder.mImgShoppingMallGuessYouLike);
            holder.mTvMchName.setText(mchName);
            holder.mTvPerCapitaPrice.setText(Tools.getTwoDecimalPoint(mConsumePrice));

            holder.LlytGuessYouLikeItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    String mchFood = MchTypeEnum.MCH_FOOD.getValue();
                    String mchScenicSpot = MchTypeEnum.MCH_SCENIC.getValue();
                    String mchHotel = MchTypeEnum.MCH_HOTEL.getValue();
                    String mchRecreation = MchTypeEnum.MCH_RECREATION.getValue();
                    if (mchType.equals(mchFood)) {
                        intent.setClass(mContext, FoodDetailActivity.class);
                        intent.putExtra("mchId", mchId);
                        mContext.startActivity(intent);

                    } else if (mchType.equals(mchScenicSpot)) {
                        intent.setClass(mContext, ScenicSpotDetailActivity.class);
                        intent.putExtra("mchId", mchId);
                        intent.putExtra("mchType", mchType);
                        mContext.startActivity(intent);
                    } else if (mchType.equals(mchRecreation)) {
                        intent.setClass(mContext, ScenicSpotDetailActivity.class);
                        intent.putExtra("mchId", mchId);
                        intent.putExtra("mchType", mchType);
                        mContext.startActivity(intent);
                    }else if (mchType.equals(mchHotel)) {
                        String mchHotelType = MchTypeEnum.MCH_HOTEL1.getValue();
                        String mchHomestayType = MchTypeEnum.MCH_HOTEL1.getValue();
                        String type2 = guessEntity.getMchType2();
                        if (type2.equals(mchHotelType)) {

                            intent.setClass(mContext, HotelDetailsActivity.class);
                            intent.putExtra("mchId", mchId);
                            mContext.startActivity(intent);
                        } else if (type2.equals(mchHomestayType)) {

                            intent.setClass(mContext, HomestayDetailActivity.class);
                            intent.putExtra("mchId", mchId);
                            mContext.startActivity(intent);
                        }

                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return orderDetailGuessList.size();
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

        @BindView(R.id.llyt_guess_you_like_item)
        LinearLayout LlytGuessYouLikeItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
