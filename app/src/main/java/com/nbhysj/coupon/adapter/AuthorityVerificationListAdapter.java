package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.AuthorityVerificationBean;

import java.util.List;

/**
 * created by hysj on 2019/11/09.
 * description: 权限验证
 */

public class AuthorityVerificationListAdapter extends RecyclerView.Adapter<AuthorityVerificationListAdapter.ViewHolder> {

    private Context mContext;
    private List<AuthorityVerificationBean> authorityVerificationList;

    public AuthorityVerificationListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setAuthorityVerificationList(List<AuthorityVerificationBean> authorityVerificationList) {

        this.authorityVerificationList = authorityVerificationList;
    }

    @Override
    public AuthorityVerificationListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_authority_verification_item, parent, false);

        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(AuthorityVerificationListAdapter.ViewHolder holder, int position) {

        try {
            AuthorityVerificationBean authorityVerification = authorityVerificationList.get(position);
            int authorityImage = authorityVerification.getImage();
            String authorityName = authorityVerification.getAuthorityName();
            String authorityDescription = authorityVerification.getAuthorityDescription();
            holder.mTvAuthorityName.setText(authorityName);
            holder.mTvAuthorityDescription.setText(authorityDescription);
            holder.mTvAuthorityLogo.setImageResource(authorityImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return authorityVerificationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvAuthorityName;
        TextView mTvAuthorityDescription;
        ImageView mTvAuthorityLogo;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvAuthorityName = (TextView) itemView.findViewById(R.id.tv_authority_name);
            mTvAuthorityDescription = (TextView) itemView.findViewById(R.id.tv_authority_description);
            mTvAuthorityLogo = (ImageView) itemView.findViewById(R.id.img_authority_logo);

        }
    }
}
