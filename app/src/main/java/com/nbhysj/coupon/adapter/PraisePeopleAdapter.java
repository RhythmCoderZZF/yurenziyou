package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.library.banner.BannerLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.model.response.ZanAvatersBean;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.BannerSlideShowView;
import com.nbhysj.coupon.view.GlideImageView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by hysj on 2019/04/15.
 * description: 点赞用户头像适配器
 */

public class PraisePeopleAdapter extends RecyclerView.Adapter<PraisePeopleAdapter.ViewHolder> {

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    private Context mContext;
    private List<ZanAvatersBean> zanAvatersList;

    public PraisePeopleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setPraisePeopleList(List<ZanAvatersBean> zanAvatersList) {

        this.zanAvatersList = zanAvatersList;
    }

    @Override
    public PraisePeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_praise_people_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PraisePeopleAdapter.ViewHolder holder, int position) {

        try {
            String avatar = zanAvatersList.get(position).getAvater();
            GlideUtil.loadImage(mContext, avatar, holder.mImgRecommendUserAvatar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        //注意，空布局---> mEmptyType = 1 显示1个布局
        //            ---> mEmptyType = 0 所有布局都不显示的
        //   return dataList != null ? dataList.size() + mEmptyType : mEmptyType;
        return zanAvatersList.size();
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
        //点赞用户
        @BindView(R.id.image_praise_people_avatar)
        CircleImageView mImgRecommendUserAvatar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
