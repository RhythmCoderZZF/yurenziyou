package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.model.response.SelectDestionationsBean;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hysj created at 2019/05/14.
 * description:已选目的标签适配器
 */
public class SelectedDestinationTagAdapter extends RecyclerView.Adapter<SelectedDestinationTagAdapter.ViewHolder> {

    List<TravelAssistantDetailCountryBean> selectedDestinationList;
    private Context mContext;
    private SelectedDestionationListener selectedDestionationListener;
    private ArrayList<Integer> selectedCountryTagList;

    public SelectedDestinationTagAdapter(Context mContext, SelectedDestionationListener selectedDestionationListener) {

        this.mContext = mContext;
        this.selectedDestionationListener = selectedDestionationListener;
        if (selectedCountryTagList == null) {

            selectedCountryTagList = new ArrayList<>();
        } else {

            selectedCountryTagList.clear();
        }
    }

    public void setSelectedDestinationTagList(List<TravelAssistantDetailCountryBean> selectedDestinationList) {

        this.selectedDestinationList = selectedDestinationList;
        selectedCountryTagList.clear();
        if (selectedDestinationList.size() > 0) {

            for (int i = 0; i < selectedDestinationList.size(); i++) {

                int countyId = selectedDestinationList.get(i).getCountyId();
                selectedCountryTagList.add(countyId);
            }

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travel_assistant_select_destination_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            TravelAssistantDetailCountryBean selectedDestination = selectedDestinationList.get(itemPosition);
            holder.mTvSelectedDestination.setText(selectedDestination.getCountyName());
            holder.mTvSelectedDestination.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    selectedDestionationListener.setSelectedDestionationListener(selectedDestination);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return selectedDestinationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //已选目的地
        TextView mTvSelectedDestination;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvSelectedDestination = itemView.findViewById(R.id.tv_selected_destination);
        }
    }

    public ArrayList<Integer> getSelectedCountryTagList() {

        return selectedCountryTagList;

    }

    public interface SelectedDestionationListener {

        void setSelectedDestionationListener(TravelAssistantDetailCountryBean selectDestionation);
    }

}
