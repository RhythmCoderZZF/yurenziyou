package com.nbhysj.coupon.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.SharePlatformEnum;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * @auther：hysj created at 2019/11/25
 * description：帖子操作弹框
 */
public class MinePostOprateDialog {
    private Context context;
    private Dialog dialog;
    private Display display;
    private OnSharePlatformItemClickListener onSharePlatformItemClickListener;

    public MinePostOprateDialog(Context context, OnSharePlatformItemClickListener onSharePlatformItemClickListener) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        this.onSharePlatformItemClickListener = onSharePlatformItemClickListener;
    }

    public MinePostOprateDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_mine_post_oprate_dialog, null);

        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(display.getWidth());

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        // 设置宽度为屏宽、靠近屏幕底部
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams wlp = dialogWindow.getAttributes();
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(wlp);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        RelativeLayout mRlytCancel = (RelativeLayout) view.findViewById(R.id.rlyt_cancel);
        RelativeLayout mRlytShareDialog = (RelativeLayout) view.findViewById(R.id.rlyt_share_dialog);
        LinearLayout mLlytWechatFriendsShare = view.findViewById(R.id.llyt_wechat_friends_share);
        LinearLayout mLlytWechatFriendsCircleShare = view.findViewById(R.id.llyt_wechat_friends_circle_share);
        LinearLayout mLlytQQShare = view.findViewById(R.id.llyt_qq_share);
        LinearLayout mLlytQQZoneShare = view.findViewById(R.id.llyt_qq_zone_share);
        LinearLayout mLlytSinaWeiboShare = view.findViewById(R.id.llyt_sina_weibo_share);
        LinearLayout mLlytPostDelete = view.findViewById(R.id.llyt_post_delete);
        LinearLayout mLlytGeneratePicture = view.findViewById(R.id.llyt_generate_picture);

        mRlytCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });
        mRlytShareDialog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        mLlytWechatFriendsShare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                onSharePlatformItemClickListener.onSharePlatformItemClick(SHARE_MEDIA.WEIXIN);

            }
        });

        mLlytWechatFriendsCircleShare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                onSharePlatformItemClickListener.onSharePlatformItemClick(SHARE_MEDIA.WEIXIN_CIRCLE);

            }
        });

        mLlytQQShare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                onSharePlatformItemClickListener.onSharePlatformItemClick(SHARE_MEDIA.QQ);

            }
        });

        mLlytQQZoneShare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                onSharePlatformItemClickListener.onSharePlatformItemClick(SHARE_MEDIA.QZONE);

            }
        });

        mLlytSinaWeiboShare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                onSharePlatformItemClickListener.onSharePlatformItemClick(SHARE_MEDIA.SINA);

            }
        });

        mLlytPostDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                onSharePlatformItemClickListener.onPostDeleteItemClick();
            }
        });

        mLlytGeneratePicture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                onSharePlatformItemClickListener.onGeneratePictureItemClick();
            }
        });

      /*  mLlytCopyLink.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                onSharePlatformItemClickListener.onSharePlatformItemClick("复制链接");

            }
        });*/

        return this;
    }

    public MinePostOprateDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public MinePostOprateDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void show() {
        dialog.show();
    }

    public interface OnSharePlatformItemClickListener {

        void onSharePlatformItemClick(SHARE_MEDIA sharePlatform);

        void onPostDeleteItemClick();

        void onGeneratePictureItemClick();
    }
}
