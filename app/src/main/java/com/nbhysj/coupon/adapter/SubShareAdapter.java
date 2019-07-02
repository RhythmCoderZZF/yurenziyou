package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.ShareResponse;
import com.nbhysj.coupon.view.NineGridLayoutView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/14.
 * description : 分享适配器
 */
public class SubShareAdapter extends RecyclerView.Adapter<SubShareAdapter.ViewHolder> {


    List<ShareResponse.ShareEntity> shareList;
    private Context mContext;

    public SubShareAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setShareList(List<ShareResponse.ShareEntity> shareList) {

        this.shareList = shareList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_share_sub_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            ShareResponse.ShareEntity shareEntity = shareList.get(itemPosition);
            List<String> imageList = shareEntity.getImageList();
            holder.mGvSharePhoto.setUrlList(imageList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return shareList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.gv_share_photo)
        NineGridLayoutView mGvSharePhoto;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
