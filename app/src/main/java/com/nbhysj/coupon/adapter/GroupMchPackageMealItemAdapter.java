package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.GroupMchDetailsResponse;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.view.MyRecycleView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/09/23.
 * description: 套餐详情适配器
 */
public class GroupMchPackageMealItemAdapter extends RecyclerView.Adapter<GroupMchPackageMealItemAdapter.ViewHolder> {

    private List<GroupMchDetailsResponse.ContentEntity> groupMchPackageMealList;

    private Context mContext;


    public GroupMchPackageMealItemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setGroupMchPackageMealList(List<GroupMchDetailsResponse.ContentEntity> groupMchPackageMealList) {

        this.groupMchPackageMealList = groupMchPackageMealList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_mch_package_meal_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            GroupMchDetailsResponse.ContentEntity contentEntity = groupMchPackageMealList.get(position);
            String mchName = contentEntity.getMchName();
            int mchLevel = contentEntity.getScenicLevel();
            float mchScore = contentEntity.getScore();
            if(!TextUtils.isEmpty(mchName))
            {
                holder.mTvMchName.setText(mchName);
            }

            holder.mTvMchScore.setText(mchScore+ "分");

                holder.mTvMchLevel.setText("国家" + mchLevel + "A级旅游景区");

            List<GroupMchDetailsResponse.SubContentEntity> subContentEntityList = contentEntity.getContent();
            if(subContentEntityList != null)
            {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                holder.mRvGroupMchPhoto.setLayoutManager(linearLayoutManager);
                // linearLayoutManager.setAutoMeasureEnabled(true);
                GroupMchPackageMealSubItemAdapter mchPackageMealSubItemAdapter = new GroupMchPackageMealSubItemAdapter(mContext);
                mchPackageMealSubItemAdapter.setGroupMchContentList(subContentEntityList);
                holder.mRvGroupMchPhoto.setAdapter(mchPackageMealSubItemAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return groupMchPackageMealList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //套餐图片列表
        @BindView(R.id.rv_group_mch_photo)
        MyRecycleView mRvGroupMchPhoto;
        //商户分数
        @BindView(R.id.tv_mch_score)
        TextView mTvMchScore;
        //商户星级
        @BindView(R.id.tv_mch_level)
        TextView mTvMchLevel;
        //商户名
        @BindView(R.id.tv_mch_name)
        TextView mTvMchName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
