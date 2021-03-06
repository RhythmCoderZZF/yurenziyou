package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/06/7.
 * description : 互动页面栏目适配器
 */
public class InteractionSectionAdapter extends RecyclerView.Adapter<InteractionSectionAdapter.ViewHolder> {

    List<MchTypeBean> recreationList;
    private Context mContext;

    public InteractionSectionAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setInteractionSectionList(List<MchTypeBean> recreationList) {

        this.recreationList = recreationList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_interaction_section_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            MchTypeBean recreationEntity = recreationList.get(itemPosition);
            int mchId = recreationEntity.getId();
            String photo = recreationEntity.getPhoto();
            holder.mTvRecreationyName.setText(recreationEntity.getMchName());

            GlideUtil.loadImage(mContext, photo, holder.mImgRecreation);

            if (itemPosition == 0) {

                holder.mHeader.setVisibility(View.VISIBLE);
            } else {

                holder.mHeader.setVisibility(View.GONE);
            }

            if (itemPosition == recreationList.size() - 1) {

                holder.mFooter.setVisibility(View.VISIBLE);
            } else {
                holder.mFooter.setVisibility(View.GONE);
            }

            holder.mLlytRecreationItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, ScenicSpotDetailActivity.class);
                    String mchRecreationType = MchTypeEnum.MCH_RECREATION.getValue();
                    intent.putExtra("mchType", mchRecreationType);
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
        return recreationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //互动名字
        @BindView(R.id.tv_recreation_name)
        TextView mTvRecreationyName;
        //美食照片
        @BindView(R.id.image_recreation)
        RoundedImageView mImgRecreation;
        @BindView(R.id.view_header)
        View mHeader;
        @BindView(R.id.view_footer)
        View mFooter;
        @BindView(R.id.llyt_recreation_item)
        LinearLayout mLlytRecreationItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
