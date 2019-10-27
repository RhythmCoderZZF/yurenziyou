package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.HomeSearchMchTypeBean;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.FoodDetailActivity;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.view.StarBarView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/10/24.
 * description : 美食适配器
 */
public class HomePageSearchFoodAdapter extends RecyclerView.Adapter<HomePageSearchFoodAdapter.ViewHolder> {

    List<HomeSearchMchTypeBean> fineFoodList;
    private Context mContext;

    public HomePageSearchFoodAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setFineFoodList(List<HomeSearchMchTypeBean> fineFoodList) {

        this.fineFoodList = fineFoodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fine_food_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            HomeSearchMchTypeBean fineFoodBean = fineFoodList.get(itemPosition);
            String mchId = fineFoodBean.getId();
            String photoUrl = fineFoodBean.getPhoto();
            String mchName = fineFoodBean.getMchName();
            String dataName = fineFoodBean.getDataName();
            String mIntro = fineFoodBean.getIntro();
            String level = fineFoodBean.getLevel();
            float commentScore = fineFoodBean.getCommentScore();
            String mConsumePrice = String.valueOf(fineFoodBean.getConsumePrice());
            List<String> tagsEntityList = fineFoodBean.getTags();
            if (!TextUtils.isEmpty(mchName)) {

                holder.mTvMchName.setText(mchName);
            } else {
                holder.mTvMchName.setText(dataName);
            }

            if (mIntro != null) {

                holder.mTvFineFoodDes.setText(mIntro);
            } else {

                holder.mTvFineFoodDes.setText("");
            }
            holder.mTvFineFoodPrice.setText("¥ " + mConsumePrice + "/人");
            holder.mStarBarView.setStarMark(commentScore);
            holder.mStarBarView.setIntegerMark(true);

            GlideUtil.loadImage(mContext, photoUrl, holder.mImgFineFood);

            if (tagsEntityList != null) {
                if (tagsEntityList.size() > 0) {
                    holder.mTagFlowlayoutFineFood.setAdapter(new TagAdapter<String>(tagsEntityList) {

                        @Override
                        public View getView(FlowLayout parent, int position, String tags) {
                         LayoutInflater mInflater = LayoutInflater.from(mContext);
                            TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_orange_frame,
                                    holder.mTagFlowlayoutFineFood, false);
                            tagName.setText(tags);
                            return tagName;
                        }
                    });
                }
            }

            holder.mRlytFineFoodItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, FoodDetailActivity.class);
                    intent.putExtra("mchId",mchId);
                    mContext.startActivity(intent);

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return fineFoodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //商户名字
        TextView mTvMchName;
        //美食图片
        ImageView mImgFineFood;
        //美食标签
        TagFlowLayout mTagFlowlayoutFineFood;
        //美食序列号
        TextView mTvFineFoodSerialNum;
        //美食描述
        TextView mTvFineFoodDes;
        //美食价格
        TextView mTvFineFoodPrice;
        //评分星级
        StarBarView mStarBarView;

        RelativeLayout mRlytFineFoodItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvMchName = itemView.findViewById(R.id.tv_mch_name);
            mImgFineFood = itemView.findViewById(R.id.image_fine_food);
            mTagFlowlayoutFineFood = itemView.findViewById(R.id.flowlayout_fine_food_label);
            mTvFineFoodSerialNum = itemView.findViewById(R.id.tv_fine_food_serial_number);
            mTvFineFoodDes = itemView.findViewById(R.id.tv_fine_food_des);
            mTvFineFoodPrice = itemView.findViewById(R.id.tv_price);
            mStarBarView = itemView.findViewById(R.id.starbar_store_fine_food);
            mRlytFineFoodItem = itemView.findViewById(R.id.rlyt_fine_food_item);
        }
    }
}
