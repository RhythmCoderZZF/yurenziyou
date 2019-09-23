package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.GroupMchDetailsResponse;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/09/23.
 * description: 套餐详情子适配器
 */
public class GroupMchPackageMealSubItemAdapter extends RecyclerView.Adapter<GroupMchPackageMealSubItemAdapter.ViewHolder> {

    /**
     * 组合套餐列表数据
     */
    private List<GroupMchDetailsResponse.SubContentEntity> groupMchSubContentList;

    private Context mContext;


    public GroupMchPackageMealSubItemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setGroupMchContentList(List<GroupMchDetailsResponse.SubContentEntity> groupMchSubContentList) {

        this.groupMchSubContentList = groupMchSubContentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_mch_package_meal_sub_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            GroupMchDetailsResponse.SubContentEntity subContentEntity = groupMchSubContentList.get(position);
            String content = subContentEntity.getContent();
            List<String> photoUrlList = subContentEntity.getPhotoJson();

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
            holder.mIGroupMchSubPhoto.setLayoutManager(linearLayoutManager);
            GroupMchPackageMealPhototemAdapter mchPackageMealPhototemAdapter = new GroupMchPackageMealPhototemAdapter(mContext);
            mchPackageMealPhototemAdapter.setGroupMchContentList(photoUrlList);
            holder.mIGroupMchSubPhoto.setAdapter(mchPackageMealPhototemAdapter);
            if(!TextUtils.isEmpty(content))
            {
                holder.mTvMchContentDes.setText(content);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return groupMchSubContentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //商户照片
        @BindView(R.id.rv_group_mch_sub_photo)
        RecyclerView mIGroupMchSubPhoto;
        //商户内容
        @BindView(R.id.tv_mch_content_des)
        TextView mTvMchContentDes;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
