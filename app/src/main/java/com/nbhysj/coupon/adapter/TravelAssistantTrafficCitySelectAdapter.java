package com.nbhysj.coupon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CountryBean;

import java.util.List;

/**
 * @author hysj created at 2019/07/15.
 * description: 添加交通(城市选择)
 */
public class TravelAssistantTrafficCitySelectAdapter extends RecyclerView.Adapter<TravelAssistantTrafficCitySelectAdapter.TripLocationCityHolder> {

    private  List<CountryBean> countryList;

    private TravelAssistantLocationCityListener travelAssistantLocationCityListener;

    public TravelAssistantTrafficCitySelectAdapter(TravelAssistantLocationCityListener travelAssistantLocationCityListener) {

        this.travelAssistantLocationCityListener = travelAssistantLocationCityListener;
    }

    public void setTravelAssistantLocationCityList(List<CountryBean> countryList){

       this.countryList = countryList;
    }

    @Override
    public TripLocationCityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travel_assistant_traffic_city_select_item, parent, false);
        return new TripLocationCityHolder(view);
    }

    @Override
    public void onBindViewHolder(final TripLocationCityHolder holder, final int position) {
        CountryBean countryBean = countryList.get(position);

        String countyName = countryBean.getCounty();
        holder.mTvLocationCityName.setText(countyName);

        holder.mTvLocationCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                travelAssistantLocationCityListener.setTravelAssistantLocationCityListener(countryBean);

            }
        });

        holder.mTvLocationCityName.getBackground().setAlpha(50);

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    static class TripLocationCityHolder extends RecyclerView.ViewHolder {

        //城市名称
        TextView mTvLocationCityName;

        public TripLocationCityHolder(View itemView) {
            super(itemView);

            mTvLocationCityName = itemView.findViewById(R.id.tv_city_name);

        }
    }

    public interface TravelAssistantLocationCityListener {

        void setTravelAssistantLocationCityListener(CountryBean countryBean);
    }
}
