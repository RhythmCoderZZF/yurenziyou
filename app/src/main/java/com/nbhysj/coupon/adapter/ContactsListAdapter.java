package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.ContactsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by hysj on 2019/03/15.
 * description: 联系人列表适配器
 */

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ViewHolder> {

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    private Context mContext;
    private List<ContactsBean> contactsList;
    private ContactsInfoItemListener contactsInfoItemListener;

    public ContactsListAdapter(Context mContext, ContactsInfoItemListener contactsInfoItemListener) {
        this.mContext = mContext;
        this.contactsInfoItemListener = contactsInfoItemListener;
    }

    public void setContactsInfoList(List<ContactsBean> contactsList) {

        this.contactsList = contactsList;
    }

    @Override
    public ContactsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_contacts_info_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsListAdapter.ViewHolder holder, int position) {

        try {

            ContactsBean contactsBean = contactsList.get(position);
            holder.mTvTravellerName.setText(contactsBean.getRealname());
            holder.mTvMobile.setText(contactsBean.getMobile());
            holder.mLlytContactsInfoItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    contactsInfoItemListener.setContactsInfoItemListener(contactsBean);
                }
            });

            holder.mLlytContactsInfoItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    contactsInfoItemListener.setContactsInfoDeleteListener(contactsBean.getId());
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        //注意，空布局---> mEmptyType = 1 显示1个布局
        //            ---> mEmptyType = 0 所有布局都不显示的
        //   return dataList != null ? dataList.size() + mEmptyType : mEmptyType;
        return contactsList.size();
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
        //旅客名
        @BindView(R.id.tv_traveller_name)
        TextView mTvTravellerName;
        //手机号
        @BindView(R.id.tv_mobile)
        TextView mTvMobile;
        TextView mTvIdentificationCard;
        @BindView(R.id.llyt_contacts_info_item)
        LinearLayout mLlytContactsInfoItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface ContactsInfoItemListener {

        void setContactsInfoItemListener(ContactsBean contactsBean);

        void setContactsInfoDeleteListener(int contactsId);
    }
}
