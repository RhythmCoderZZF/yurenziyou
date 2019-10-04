package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.model.response.TouristBean;
import com.nbhysj.coupon.model.response.TravellerBean;

import java.util.List;

/**
 * @author hysj created at 2019/08/29.
 * description:游客信息适配器
 */
public class AddTouristInformationAdapter extends RecyclerView.Adapter<AddTouristInformationAdapter.ViewHolder> {


    List<TravellerBean> touristInfoList;
    private Context mContext;
    private TouristInformationListener touristInformationListener;

    public AddTouristInformationAdapter(Context mContext, TouristInformationListener touristInformationListener) {

        this.mContext = mContext;
        this.touristInformationListener = touristInformationListener;
    }

    public void setTouristInfoList(List<TravellerBean> touristInfoList) {

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

            TravellerBean touristBean = touristInfoList.get(itemPosition);
            holder.mTvTouristName.setText(touristBean.getRealname());
            holder.mTvPhone.setText(touristBean.getMobile());
            boolean isTravellerSelect = touristBean.isTravellerSelect();
            holder.mTvEditVisitors.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    touristInformationListener.setEditTouristInfoListener(itemPosition);
                    for (int i = 0;i < touristInfoList.size();i++)
                    {

                        touristInfoList.get(i).setTravellerSelect(false);
                    }
                    touristInfoList.get(itemPosition).setTravellerSelect(true);

                    notifyDataSetChanged();

                }
            });

            if(isTravellerSelect)
            {
              holder.mImgVisitorsCheck.setBackgroundResource(R.mipmap.icon_payment_method_check_true);
            } else {

                holder.mImgVisitorsCheck.setBackgroundResource(R.mipmap.icon_payment_method_check_false);
            }

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

        ImageView mImgVisitorsCheck;

        LinearLayout mLlytVisitorsItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvTouristName = itemView.findViewById(R.id.tv_tourist_name);
            mTvPhone = itemView.findViewById(R.id.tv_phone);
            mTvEditVisitors = itemView.findViewById(R.id.tv_edit_visitors);
            mImgVisitorsCheck = itemView.findViewById(R.id.img_visitors_check);
            mLlytVisitorsItem = itemView.findViewById(R.id.llyt_visitors_item);
        }
    }

    public interface TouristInformationListener {

        void setEditTouristInfoListener(int position);
    }
}
