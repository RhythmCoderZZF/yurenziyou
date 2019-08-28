package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amap.api.services.help.Tip;
import com.nbhysj.coupon.R;
import java.util.List;

/**
 * 输入提示adapter，展示item名称和地址
 */
public class InputTipsAdapter extends RecyclerView.Adapter<InputTipsAdapter.ViewHolder> {
    private Context mContext;
    private List<Tip> mListTips;
    private InputtipsListener inputtipsListener;

    public InputTipsAdapter(Context context, List<Tip> tipList,InputtipsListener inputtipsListener)
    {
        mContext = context;
        mListTips = tipList;
        this.inputtipsListener = inputtipsListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_inputtips, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {

            Tip tip = mListTips.get(position);
            String name = tip.getName();
            String address = tip.getAddress();
            holder.mTvAddressName.setText(name);

            if(!TextUtils.isEmpty(address)) {
                holder.mTvAddress.setVisibility(View.VISIBLE);
                holder.mTvAddress.setText(address);
            } else {
                holder.mTvAddress.setVisibility(View.GONE);
            }

            holder.mLlytVichicleUseAddressSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    inputtipsListener.setVichicleUseAddressSelect(tip);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (mListTips != null) {
            return mListTips.size();
        }
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //地点名称
        TextView mTvAddressName;
        //地点
        TextView mTvAddress;

        LinearLayout mLlytVichicleUseAddressSelect;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvAddressName = itemView.findViewById(R.id.tv_name);
            mTvAddress = itemView.findViewById(R.id.tv_adress);
            mLlytVichicleUseAddressSelect = itemView.findViewById(R.id.llyt_vichicle_use_address_select);
        }
    }

    public interface InputtipsListener{

        void setVichicleUseAddressSelect(Tip tip);
    }
}
