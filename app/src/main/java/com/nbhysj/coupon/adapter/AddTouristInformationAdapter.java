package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.TouristBean;

import java.util.List;

/**
 * @author hysj created at 2019/5/10.
 * description:新增游客信息适配器
 */
public class AddTouristInformationAdapter extends RecyclerView.Adapter<AddTouristInformationAdapter.ViewHolder> {


    List<TouristBean> touristInfoList;
    private Context mContext;
    private TouristInformationListener touristInformationListener;

    public AddTouristInformationAdapter(Context mContext, TouristInformationListener touristInformationListener) {

        this.mContext = mContext;
        this.touristInformationListener = touristInformationListener;
    }

    public void setTouristInfoList(List<TouristBean> touristInfoList) {

        this.touristInfoList = touristInfoList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_add_tourist_choice_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            TouristBean touristBean = touristInfoList.get(itemPosition);
            holder.mTvTouristName.setText(touristBean.getName());
            holder.mTvPhone.setText(touristBean.getIdNumber());
            holder.mTvEditVisitors.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    touristInformationListener.setEditTouristInfoListener(itemPosition);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return touristInfoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //游客名字
        TextView mTvTouristName;
        //游客电话
        TextView mTvPhone;
        //编辑游客
        TextView mTvEditVisitors;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvTouristName = itemView.findViewById(R.id.tv_tourist_name);
            mTvPhone = itemView.findViewById(R.id.tv_phone);
            mTvEditVisitors = itemView.findViewById(R.id.tv_edit_visitors);
        }
    }

    public interface TouristInformationListener {

        void setEditTouristInfoListener(int position);
    }
}
