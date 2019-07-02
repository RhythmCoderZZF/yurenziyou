package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.NewFansBean;
import com.nbhysj.coupon.model.response.SupportedUserBean;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by hysj on 2019/03/21
 * description: 新增关注列表适配器
 */

public class NewFansListAdapter extends RecyclerView.Adapter<NewFansListAdapter.ViewHolder> {

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    private Context mContext;
    private List<NewFansBean> newFansList;
    private HouseDetailListener mListener;

    public NewFansListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setNewFansList(List<NewFansBean> newFansList) {

        this.newFansList = newFansList;
    }

    @Override
    public NewFansListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_new_fans_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewFansListAdapter.ViewHolder holder, int position) {

        try {
            NewFansBean newFansBean = newFansList.get(position);
            holder.mTvTitle.setText(newFansBean.getTitle());
            holder.mTvContent.setText(newFansBean.getTime());
            String url = newFansBean.getUrl();
            holder.mImgUserAvatar.loadCircle(url);
            if (newFansBean.isReturnPowderStatus() == false) {
                holder.mTvFollow.setBackgroundResource(R.drawable.bg_new_fans_stroke_radius_red_shape);
                holder.mTvFollow.setTextColor(mContext.getResources().getColor(R.color.btn_alert_press));

            } else if (newFansBean.isReturnPowderStatus() == true) {
                holder.mTvFollow.setBackgroundResource(R.drawable.bg_new_fans_stroke_radius_green_shape);
                holder.mTvFollow.setTextColor(mContext.getResources().getColor(R.color.color_green2));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        //注意，空布局---> mEmptyType = 1 显示1个布局
        //            ---> mEmptyType = 0 所有布局都不显示的
        //   return dataList != null ? dataList.size() + mEmptyType : mEmptyType;
        return newFansList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mEmptyType == EMPTY_VIEW) {
            //空布局的类型
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_user_avatar)
        GlideImageView mImgUserAvatar;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_content)
        TextView mTvContent;
        @BindView(R.id.tv_follow)
        TextView mTvFollow;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface HouseDetailListener {

        public void setcheckItemListener(int position);

        public void setDeleteItemListener(int position);
    }
}
