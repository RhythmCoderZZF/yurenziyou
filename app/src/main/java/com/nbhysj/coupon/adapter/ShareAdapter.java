package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MyPostShareBean;
import com.nbhysj.coupon.model.response.MyPostShareResponse;
import com.nbhysj.coupon.view.MyRecycleView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/14.
 * description : 分享适配器
 */
public class ShareAdapter extends RecyclerView.Adapter<ShareAdapter.ViewHolder> {


    List<MyPostShareResponse> myPostShareList;
    private Context mContext;

    private ShareListener shareListener;

    public ShareAdapter(Context mContext,ShareListener shareListener) {

        this.mContext = mContext;
        this.shareListener = shareListener;
    }

    public void setShareList(List<MyPostShareResponse> myPostShareList) {

        this.myPostShareList = myPostShareList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_share_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            MyPostShareResponse myPostShareBean = myPostShareList.get(itemPosition);
            List<MyPostShareBean> myPostShareList = myPostShareBean.getMyPosts();

            String year = myPostShareBean.getYear();
            if(!TextUtils.isEmpty(year))
            {
                holder.mTvShareYear.setText(year + "年");
            }

            if(myPostShareList != null)
            {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                holder.mRvPostShare.setLayoutManager(linearLayoutManager);
                SubShareAdapter subShareAdapter = new SubShareAdapter(mContext, new SubShareAdapter.SubShareListener() {
                    @Override
                    public void setSubShareListener(int mPostId,int position) {

                        shareListener.setShareListener(mPostId,itemPosition,position);

                    }
                });
                subShareAdapter.setPostShareList(myPostShareList);
                holder.mRvPostShare.setAdapter(subShareAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return myPostShareList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //分享的年份
        @BindView(R.id.tv_share_year)
        TextView mTvShareYear;

        @BindView(R.id.rv_share)
        MyRecycleView mRvPostShare;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface ShareListener{

        void setShareListener(int mPostId,int groupPosition,int childPosition);
    }
}
