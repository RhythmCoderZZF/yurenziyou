package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.util.BitmapUtils;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * created by hysj on 2019/03/15.
 * description: 帖子图片适配器
 */
public class NotePictureItemAdapter extends RecyclerView.Adapter<NotePictureItemAdapter.ViewHolder> {

    /**
     * 图片列表数据
     */
    private Context mContext;

    private NotePictureListener notePictureListener;

    private boolean isPhotoSelect;

    //音频文件
    private String audioFileName;

    private int audioFileLength;

    private List<String> fileNotePictureList = new ArrayList();

    public NotePictureItemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setNotePictureList(List<String> fileBOList, boolean isPhotoSelect, String audioFileName, int audioFileLength) {

        this.isPhotoSelect = isPhotoSelect;
        this.audioFileName = audioFileName;
        this.audioFileLength = audioFileLength;

        fileNotePictureList.clear();
        if (isPhotoSelect) {
            fileNotePictureList.add("");
            fileNotePictureList.add("");
            fileNotePictureList.addAll(fileBOList);
        } else {
            if (fileBOList.size() == 0)
            {
                fileNotePictureList.add("");
            }
            fileNotePictureList.addAll(fileBOList);
        }
    }

    /**
     * 监听
     */
    public void setNotePictureListener(NotePictureListener notePictureListener) {

        this.notePictureListener = notePictureListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = View.inflate(parent.getContext(), R.layout.list_survey_type_answer_item, null);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_note_picture_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        try {
            if (isPhotoSelect) {
                holder.mRlytVoiceCapusule.getBackground().setAlpha(20);
                holder.mRlytInnerCircleVoiceCapusule.getBackground().setAlpha(30);
                holder.mRlytOuterCircleVoiceCapusule.getBackground().setAlpha(40);
                if (TextUtils.isEmpty(audioFileName)) {
                    holder.mImgVoiceCapsule.setVisibility(View.VISIBLE);
                    holder.mRlytOuterCircleVoiceCapusule.setVisibility(View.GONE);
                    holder.mTvVoiceCapsuleTip.setText("添加声音");
                } else {
                    holder.mImgVoiceCapsule.setVisibility(View.GONE);
                    holder.mRlytOuterCircleVoiceCapusule.setVisibility(View.VISIBLE);
                    holder.mTvVoiceCapsuleTip.setText("声音胶囊");
                }
                if (fileNotePictureList.size() == 2) {
                    if (position == 0) {
                        holder.mRlytVoiceCapusule.setVisibility(View.GONE);
                        holder.mImgPhotopraph.setVisibility(View.VISIBLE);
                        holder.mImgPhotopraph.setImageResource(R.mipmap.icon_default_photograph);

                    } else if (fileNotePictureList.size() > 2) {
                        holder.mRlytVoiceCapusule.setVisibility(View.GONE);

                        String imgUrl = fileNotePictureList.get(position);

                        holder.mImgPhotopraph.load(imgUrl, R.mipmap.icon_placeholder_image, 30);

                    }

                    holder.mImgPhotopraph.setOnClickListener(v -> {
                        notePictureListener.setNotePictureListener(position, false);
                    });

                } else {

                    if (position == 0) {

                        holder.mRlytVoiceCapusule.setVisibility(View.VISIBLE);
                        // holder.mImgPhotopraph.setImageResource(R.mipmap.icon_default_photograph);
                        holder.mImgPhotopraph.setVisibility(View.GONE);
                    } else if (position == 1) {
                        holder.mRlytVoiceCapusule.setVisibility(View.GONE);
                        holder.mImgPhotopraph.setVisibility(View.VISIBLE);
                        holder.mImgPhotopraph.setImageResource(R.mipmap.icon_default_photograph);

                    } else {
                        holder.mRlytVoiceCapusule.setVisibility(View.GONE);

                        String imgUrl = fileNotePictureList.get(position);

                        holder.mImgPhotopraph.load(imgUrl, R.mipmap.icon_placeholder_image, 30);

                    }

                    holder.mImgPhotopraph.setOnClickListener(v -> {
                        notePictureListener.setNotePictureListener(position, false);
                    });
                }

                holder.mRlytVoiceCapusule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        notePictureListener.setNotePictureListener(position, false);
                    }
                });

                holder.mTvVoiceCapsulelength.setText(String.valueOf(audioFileLength));
            } else {

                String localVideoPath = fileNotePictureList.get(position);

                if (!TextUtils.isEmpty(localVideoPath)) {
                    Bitmap bitmap = BitmapUtils.getVideoThumbnail(localVideoPath);
                    holder.mImgPhotopraph.setImageBitmap(bitmap);
                } else {
                    if (position == 0) {
                        holder.mRlytVoiceCapusule.setVisibility(View.GONE);
                        holder.mImgPhotopraph.setVisibility(View.VISIBLE);
                        holder.mImgPhotopraph.setImageResource(R.mipmap.icon_default_photograph);

                    }
                }

                holder.mImgPhotopraph.setOnClickListener(v -> {
                    notePictureListener.setNotePictureListener(position, false);
                });
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return fileNotePictureList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public GlideImageView mImgPhotopraph;

        public RelativeLayout mRlytVoiceCapusule, mRlytOuterCircleVoiceCapusule, mRlytInnerCircleVoiceCapusule;

        TextView mTvVoiceCapsuleTip;

        ImageView mImgVoiceCapsule;

        TextView mTvVoiceCapsulelength;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgPhotopraph = itemView.findViewById(R.id.img_photograph);
            mRlytVoiceCapusule = itemView.findViewById(R.id.rlyt_voice_capsule);
            mRlytOuterCircleVoiceCapusule = itemView.findViewById(R.id.rlyt_outer_circle_voice_capsule);
            mRlytInnerCircleVoiceCapusule = itemView.findViewById(R.id.rlyt_inner_circle_voice_capsule);
            mTvVoiceCapsuleTip = itemView.findViewById(R.id.tv_voice_capsule_tip);
            mImgVoiceCapsule = itemView.findViewById(R.id.image_voice_capsule);
            mTvVoiceCapsulelength = itemView.findViewById(R.id.tv_sound_recording_length);

        }
    }

    /**
     * 适配监听
     */
    public interface NotePictureListener {

        void setNotePictureListener(int position, boolean isSelectPictureDelete);

    }
}
