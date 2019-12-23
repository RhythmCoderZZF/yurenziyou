package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.GeneratePictureMenuBean;
import com.nbhysj.coupon.model.response.MchCitiesBean;
import com.nbhysj.coupon.ui.ScenicSpotDestinationActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/19.
 * description : 景点类型分类适配器
 */
public class GeneratePictureOprateAdapter extends RecyclerView.Adapter<GeneratePictureOprateAdapter.ViewHolder> {

    List<GeneratePictureMenuBean> generatePictureMenuList;
    private Context mContext;
    private GeneratePictureOprateListener generatePictureOprateListener;

    public GeneratePictureOprateAdapter(Context mContext,GeneratePictureOprateListener generatePictureOprateListener) {

        this.mContext = mContext;
        this.generatePictureOprateListener = generatePictureOprateListener;
    }

    public void setGeneratePictureOprateList(List<GeneratePictureMenuBean> generatePictureMenuList) {

        this.generatePictureMenuList = generatePictureMenuList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_generate_picture_oprate_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            GeneratePictureMenuBean generatePictureMenuBean = generatePictureMenuList.get(itemPosition);
            int photo = generatePictureMenuBean.getIcon();
            String title = generatePictureMenuBean.getTitle();
            holder.mTvGeneratePictureOprate.setText(title);
            holder.mImgGeneratePicturePhoto.setImageResource(photo);

            if (itemPosition == 0) {

                holder.mFooterLine.setVisibility(View.VISIBLE);
            } else {
                holder.mFooterLine.setVisibility(View.GONE);
            }

            holder.mLlytGeneratePictureItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemPosition == 0)
                    {
                        generatePictureOprateListener.setGeneratePictureCallback(itemPosition,null);
                    } else if(itemPosition == 1){
                        generatePictureOprateListener.setGeneratePictureCallback(itemPosition,SHARE_MEDIA.WEIXIN);
                    } else if(itemPosition == 2){
                        generatePictureOprateListener.setGeneratePictureCallback(itemPosition,SHARE_MEDIA.WEIXIN_CIRCLE);
                    } else if(itemPosition == 3){
                        generatePictureOprateListener.setGeneratePictureCallback(itemPosition,SHARE_MEDIA.QQ);
                    } else if(itemPosition == 4){
                        generatePictureOprateListener.setGeneratePictureCallback(itemPosition,SHARE_MEDIA.QZONE);
                    } else if(itemPosition == 5){
                        generatePictureOprateListener.setGeneratePictureCallback(itemPosition,SHARE_MEDIA.SINA);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return generatePictureMenuList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_generate_picture_oprate)
        TextView mTvGeneratePictureOprate;

        //生成图片操作
        @BindView(R.id.img_generate_picture_item)
        ImageView mImgGeneratePicturePhoto;

        @BindView(R.id.view_line)
        View mFooterLine;

        @BindView(R.id.llyt_generate_picture_item)
        LinearLayout mLlytGeneratePictureItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface GeneratePictureOprateListener{

        void setGeneratePictureCallback(int position, SHARE_MEDIA sharePlatform);
    }
}
