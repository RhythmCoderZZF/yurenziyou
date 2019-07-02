package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.ImageBean;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @auther：hysj created at 2019/01/24
 */
public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {
    private Context context;
    private List<ImageBean> imageBeans;
    private OnClickListener onClickListener;
    String[] letterArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public ImageListAdapter(Context context) {
        this.context = context;
    }

    public void setImageList(List<ImageBean> imageBeans) {
        this.imageBeans = imageBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_image_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);

        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageBean imageBean = imageBeans.get(position);
        int siteEntranceName = imageBean.getImage();

    }

    @Override
    public int getItemCount() {
        return imageBeans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickListener {
        void setListener(int siteEntranceId);
    }
}
