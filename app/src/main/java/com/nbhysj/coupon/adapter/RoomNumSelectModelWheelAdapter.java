package com.nbhysj.coupon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.WheelAdapter;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CarTypeBean;

import java.util.List;

/**
 * @author hysj created at 2019/09/18.
 * description : 房间数量选择型号适配器
 */
public class RoomNumSelectModelWheelAdapter extends RecyclerView.Adapter<RoomNumSelectModelWheelAdapter.ViewHolder> implements WheelAdapter {

    List<CarTypeBean> carTypeModeList;

    public RoomNumSelectModelWheelAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_room_num_select_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemsCount() {
        return 20;
    }

    @Override
    public Object getItem(int index) {
        int mPosition = index + 1;
        return mPosition + "间";
    }

    @Override
    public int indexOf(Object o) {
        try {
            String roomNum = o.toString();
            String roomNumIndex = roomNum.replace("间", "");
            return Integer.parseInt(roomNumIndex);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return carTypeModeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //房间选择
        public TextView mTvRoomNumSelectItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvRoomNumSelectItem = itemView.findViewById(R.id.tv_room_num_select_item);
        }
    }
}
