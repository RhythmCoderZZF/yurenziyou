package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/09/23.
 * description: 套餐详情图片适配器
 */
public class GroupMchPackageMealPhototemAdapter extends RecyclerView.Adapter<GroupMchPackageMealPhototemAdapter.ViewHolder> {

    /**
     * 组合套餐图片列表数据
     */
    private List<String> groupMchPhotoUrlList;

    private Context mContext;


    public GroupMchPackageMealPhototemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setGroupMchContentList(List<String> groupMchPhotoUrlList) {

        this.groupMchPhotoUrlList = groupMchPhotoUrlList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_mch_package_meal_photo_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            String groupMchPhotoUrl = groupMchPhotoUrlList.get(position);
            if(!TextUtils.isEmpty(groupMchPhotoUrl))
            {
                GlideUtil.loadImage(mContext, groupMchPhotoUrl, holder.mImgGroupMchPhoto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return groupMchPhotoUrlList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //商户照片
        @BindView(R.id.img_group_mch_photo)
        ImageView mImgGroupMchPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
