package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.ImageData;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;


/**
 * @author hysj created on 2019/02/19.
 * description: 推荐好友图片子适配器
 */
public class FindFriendsPictureItemAdapter extends RecyclerView.Adapter<FindFriendsPictureItemAdapter.ViewHolder> {

    /**
     * 图片列表数据
     */
    private List<ImageData> imageDataList;

    private Context mContext;

    private ViewHolder.VRHouseOprateListener mVrHouseOprateListener;

    private boolean isHistoryDuediligence;

    public FindFriendsPictureItemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setRecommendFriendsPictureList(List<ImageData> imageDataList) {

        this.imageDataList = imageDataList;
    }

    /**
     * 监听
     *
     * @param vrHouseOprateListener
     */
    public void setRecommendFriendsPictureListener(ViewHolder.VRHouseOprateListener vrHouseOprateListener) {

        this.mVrHouseOprateListener = vrHouseOprateListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = View.inflate(parent.getContext(), R.layout.list_survey_type_answer_item, null);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recommend_friends_picture_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {

            ImageData imageData = imageDataList.get(position);
            String url = imageData.getUrl();
            holder.mImgRecommendFriends.load(url, R.mipmap.ic_launcher, 2);

          /*  if (fileBOList.size() == 11 && position == fileBOList.size()) {

                holder.mImgPhotopraph.setVisibility(View.GONE);
            } else {
                holder.mImgPhotopraph.setVisibility(View.VISIBLE);
            }*/
            // holder.mImgPhotopraph.setImageBitmap(bitmapList.get(position));

          /*  holder.mImgPhotopraph.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVrHouseOprateListener.setVRHouseOprateListener(position, false);
                }
            });
*/


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return imageDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public GlideImageView mImgRecommendFriends;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgRecommendFriends = (GlideImageView) itemView.findViewById(R.id.image_recommend_friends);
        }

        /**
         * 适配监听
         */
        public interface VRHouseOprateListener {

            void setVRHouseOprateListener(int position, boolean isSelectPictureDelete);

        }
    }
}
