package com.nbhysj.coupon.adapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CountryBean;
import java.util.List;

/**
 * @author hysj created at 2019/07/15.
 * description: 添加交通(定位城市)
 */
public class TravelAssistantLocationCityAdapter extends RecyclerView.Adapter<TravelAssistantLocationCityAdapter.TripLocationCityHolder> {

    private  List<CountryBean> countryList;

    private TravelAssistantLocationCityListener travelAssistantLocationCityListener;

    public TravelAssistantLocationCityAdapter(TravelAssistantLocationCityListener travelAssistantLocationCityListener) {

        this.travelAssistantLocationCityListener = travelAssistantLocationCityListener;
    }

    public void setTravelAssistantLocationCityList(List<CountryBean> countryList){

       this.countryList = countryList;
    }

    @Override
    public TripLocationCityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travel_assistant_traffic_add_location_city_item, parent, false);
        return new TripLocationCityHolder(view);
    }

    @Override
    public void onBindViewHolder(final TripLocationCityHolder holder, final int position) {
        CountryBean countryBean = countryList.get(position);

        String countyName = countryBean.getCounty();
        boolean isLocationCity = countryBean.getIsLocationCity();
        holder.mTvLocationCityName.setText(countyName);
        holder.mRlytTravelAssistantAddLocationCityItem.getBackground().setAlpha(50);
        holder.mRlytTravelAssistantAddLocationCityItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                travelAssistantLocationCityListener.setTravelAssistantLocationCityListener(countryBean);

            }
        });

        if(isLocationCity){

            holder.mImgTravelAssistantLocationCity.setVisibility(View.VISIBLE);
        } else {

            holder.mImgTravelAssistantLocationCity.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    static class TripLocationCityHolder extends RecyclerView.ViewHolder {

        //定位城市标识
        ImageView mImgTravelAssistantLocationCity;
        //定位城市名称
        TextView mTvLocationCityName;
        RelativeLayout mRlytTravelAssistantAddLocationCityItem;

        public TripLocationCityHolder(View itemView) {
            super(itemView);

            mImgTravelAssistantLocationCity = itemView.findViewById(R.id.img_travel_assistant_location_city);
            mTvLocationCityName = itemView.findViewById(R.id.tv_city_name);
            mRlytTravelAssistantAddLocationCityItem = itemView.findViewById(R.id.rlyt_travel_assistant_add_location_city_item);

        }
    }

    public interface TravelAssistantLocationCityListener {

        void setTravelAssistantLocationCityListener(CountryBean countryBean);
    }
}
