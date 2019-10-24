package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/21.
 * description : 目的地搜索目的地适配器
 */
public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {


    List<String> destinationList;
    private Context mContext;

    public DestinationAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setDestinationList(List<String> destinationList) {

        this.destinationList = destinationList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_destination_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            String destination = destinationList.get(itemPosition);
            holder.mTvFlowlayoutDestination.setText(destination);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_flowlayout)
        TextView mTvFlowlayoutDestination;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
