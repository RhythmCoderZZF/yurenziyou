package com.nbhysj.coupon.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.BannerUrlBO;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.view.HotelDetailBannerView;
import com.nbhysj.coupon.view.HotelDetailSupplementBannerView;
import com.nbhysj.coupon.view.ScenicSpotDetailBannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/5/13
 * description：酒店详情补充
 */
public class HotelDetailsSupplementDialog extends DialogFragment {
    private Context context;
    private View view;
    private HotelDetailSupplementBannerView bannerView;
    private TextView mTvBannerNum;
    private List<ImageView> viewList;
    private List<BannerUrlBO> bannerList;

    public HotelDetailsSupplementDialog() {

    }

    @SuppressLint("ValidFragment")
    public HotelDetailsSupplementDialog(List<RecipientAddressResponse> recipientAddressResponseList) {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        context = getActivity();
        initView();
        Dialog dialog = new Dialog(context, R.style.CustomDatePickerDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // must be called before set content
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        // 设置宽度为屏宽、靠近屏幕底部
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
      /*  window.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,

                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);*/
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    private void initView() {

        view = LayoutInflater.from(context).inflate(R.layout.layout_hotel_details_supplement_dialog, null);
        RelativeLayout mRlytNewTourists = view.findViewById(R.id.rlyt_new_tourists);
        bannerView = view.findViewById(R.id.banner);
        mTvBannerNum = view.findViewById(R.id.tv_banner_num);
        mRlytNewTourists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        viewList = new ArrayList<ImageView>();
        bannerList = new ArrayList<>();

        BannerUrlBO bannerUrl = new BannerUrlBO();
        bannerUrl.setUrl("http://pic32.nipic.com/20130823/13339320_183302468194_2.jpg");

        BannerUrlBO bannerUrl1 = new BannerUrlBO();
        bannerUrl1.setUrl("https://img5.duitang.com/uploads/item/201409/20/20140920163237_myPVw.thumb.700_0.png");

        BannerUrlBO bannerUrl2 = new BannerUrlBO();
        bannerUrl2.setUrl("https://t1.hddhhn.com/uploads/tu/201611/228/st87.png");
        bannerList.add(bannerUrl);
        bannerList.add(bannerUrl1);
        bannerList.add(bannerUrl2);

        if (bannerList.size() > 0) {

            for (int i = 0; i < bannerList.size(); i++) {
                ImageView image = new ImageView(context);
                image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                //设置显示格式
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                viewList.add(image);
            }
        }


        mTvBannerNum.setText("1/" + bannerList.size());
        //bannerView.startLoop(true);
        bannerView.setViewList(context, viewList, bannerList, new HotelDetailSupplementBannerView.ScenicSpotDetailBannerViewListener() {
            @Override
            public void setScenicSpotDetailBannerViewListener(int curPos) {

                mTvBannerNum.setText(curPos + "/" + bannerList.size());
            }
        });
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //KeyBoardUtils.closeKeybord((Activity) context);
        super.show(manager, tag);
    }
}
