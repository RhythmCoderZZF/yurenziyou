package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.StrokeDynamicsResponse;

import java.util.List;

import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/04/14.
 * description: 行程动态适配器
 */
public class StrokeDynamicsItemAdapter extends RecyclerView.Adapter<StrokeDynamicsItemAdapter.ViewHolder> {

    /**
     * 图片列表数据
     */
    private List<StrokeDynamicsResponse> strokeDynamicsList;

    private Context mContext;


    public StrokeDynamicsItemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setBroadcastList(List<StrokeDynamicsResponse> strokeDynamicsList) {

        this.strokeDynamicsList = strokeDynamicsList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = View.inflate(parent.getContext(), R.layout.list_survey_type_answer_item, null);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_stroke_dynamics_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
