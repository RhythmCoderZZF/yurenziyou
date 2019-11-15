package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.AuthorityVerificationBean;

import java.util.List;

/**
 * created by hysj on 2019/11/09.
 * description:
 */

public class AuthorityProhibitListAdapter extends RecyclerView.Adapter<AuthorityProhibitListAdapter.ViewHolder> {

    private Context mContext;
    private List<AuthorityVerificationBean> authorityVerificationList;

    public AuthorityProhibitListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setAuthorityVerificationList(List<AuthorityVerificationBean> authorityVerificationList) {

        this.authorityVerificationList = authorityVerificationList;
    }

    @Override
    public AuthorityProhibitListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_authority_prohibit_item, parent, false);

        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(AuthorityProhibitListAdapter.ViewHolder holder, int position) {

        try {
            AuthorityVerificationBean authorityVerification = authorityVerificationList.get(position);
            String authorityName = authorityVerification.getAuthorityName();
            holder.mTvAuthorityName.setText(authorityName);
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

        public ViewHolder(View itemView) {
            super(itemView);

            mTvAuthorityName = (TextView) itemView.findViewById(R.id.tv_authority_name);

        }
    }
}
