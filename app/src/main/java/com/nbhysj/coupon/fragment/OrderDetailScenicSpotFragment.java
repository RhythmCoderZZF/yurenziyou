package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.model.LatLng;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.GoodsTypeEnum;
import com.nbhysj.coupon.model.response.OrderDetailResponse;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/08
 * description：订单详情景区
 */
public class OrderDetailScenicSpotFragment extends BaseFragment {

    //商户名字
    @BindView(R.id.tv_mch_name)
    TextView mTvMchName;
    //景区票类型
    @BindView(R.id.tv_ticket_type)
    TextView mTvTicketType;
    //景区地址
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    //商户位置定位
    @BindView(R.id.img_mch_address)
    ImageView mImgMchAddress;
    //营业时间
    @BindView(R.id.tv_content)
    TextView mTvContent;
    private String mchName;
    public static OrderDetailScenicSpotFragment newInstance(OrderDetailResponse.OrderGoodsEntity orderGoodsEntity) {

        //            创建一个 bundle 传递 数据
        Bundle bundle = new Bundle();
        //使用bundle合适的put方法传递数据
        bundle.putSerializable("orderGoodsEntity", (Serializable) orderGoodsEntity);
//             新建一个 fragment
        OrderDetailScenicSpotFragment fragment = new OrderDetailScenicSpotFragment();

        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_detail_scenic_spot_item;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(View v) {
        Bundle bundle = getArguments();
        OrderDetailResponse.OrderGoodsEntity orderGoodsEntity = (OrderDetailResponse.OrderGoodsEntity) bundle.getSerializable("orderGoodsEntity");
        if(orderGoodsEntity != null) {
            OrderDetailResponse.AddressEntity addressEntity = orderGoodsEntity.getAddress();
            mchName = orderGoodsEntity.getMchName();
            String province = addressEntity.getProvince();
            String county = addressEntity.getCounty();
            String city = addressEntity.getCity();
            String address = addressEntity.getAddress();         //地址
            String goodsTitle = orderGoodsEntity.getGoodsTitle(); //商品标题
            String goodType = orderGoodsEntity.getGoodType();  //商品类型
            String goodNum = orderGoodsEntity.getGoodsNum();   //商品数量
            String note = orderGoodsEntity.getNote();

            //if(goodType.equals(GoodsTypeEnum.getEnumByKey(0).getValue())) {
            mTvMchName.setText(mchName);
            // mTvTicketType.setText(goodsTitle + goodNum + "张");
            mTvAddress.setText(province + city + county + address);
            mTvContent.setText("营业时间" + orderGoodsEntity.getOpenTime());

            GlideUtil.loadImage(getActivity(), note, mImgMchAddress);
        }
        mImgMchAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OrderDetailResponse.NoteParamEntity noteParamEntity = orderGoodsEntity.getNoteParam();
                if(noteParamEntity != null)
                {
                    String mLatitude = noteParamEntity.getLatitude();
                    String mLongitude = noteParamEntity.getLongitude();
                    if(!TextUtils.isEmpty(mLatitude) &&
                            !TextUtils.isEmpty(mLongitude) && !TextUtils.isEmpty(mchName))
                    {
                        if (isInstalled("com.autonavi.minimap"))
                        {
                            openGaodeMapToGuide(mLatitude, mLongitude);

                        } else if(isInstalled("com.baidu.BaiduMap"))
                        {
                            goToBaiduMap(mLatitude, mLongitude);

                        } else {
                            showToast(getActivity(), "请先安装高德地图客户端");
                        }

                    }
                }
            }
        });

        //  }
    }

    @Override
    public void initData() {
    }

    public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
        private int itemSpace;
        private int itemNum;

        /**
         * @param itemSpace item间隔
         * @param itemNum   每行item的个数
         */
        public RecyclerItemDecoration(int itemSpace, int itemNum) {
            this.itemSpace = itemSpace;
            this.itemNum = itemNum;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = itemSpace;
                /*if (parent.getChildLayoutPosition(view)%itemNum == 0){  //parent.getChildLayoutPosition(view) 获取view的下标
                    outRect.left = 0;
                } else {
                    outRect.left = itemSpace;
                }*/
            outRect.left = itemSpace;
            outRect.right = itemSpace;
            //outRect.right = itemSpace;
        }
    }


    @Override
    public void lazyInitView(View view) {

    }

    /**
     * 跳转百度地图
     */
    private void goToBaiduMap(String mLatitude,String mLongitude) {
       /* if (!isInstalled("com.baidu.BaiduMap")) {
            showToast(getActivity(), "请先安装百度地图客户端");
            return;
        }*/
        Intent intent = new Intent();
        intent.setData(Uri.parse("baidumap://map/direction?destination=latlng:"
                + mLatitude + ","
                + mLongitude + "|name:" + mchName + // 终点
                "&mode=driving" + // 导航路线方式
                "&src=" + getActivity().getPackageName()));
        startActivity(intent); // 启动调用
    }


    /**
     * 跳转高德地图
     */
    private void goToGaodeMap(String mLatitude,String mLongitude) {
      /*  if (!isInstalled("com.autonavi.minimap")) {
            //showToast(getActivity(), "请先安装高德地图客户端");
            return;
        }*/
        double latitude = Double.parseDouble(mLatitude);
        double longitude = Double.parseDouble(mLongitude);
        LatLng endPoint = BD2GCJ(new LatLng(latitude, longitude));//坐标转换
        StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=").append("amap");
        stringBuffer.append("&lat=").append(endPoint.latitude)
                .append("&lon=").append(endPoint.longitude).append("&keywords=" + mchName)
                .append("&dev=").append(0)
                .append("&style=").append(2);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
        intent.setPackage("com.autonavi.minimap");
        startActivity(intent);
    }
    /**
     * 打开高德地图
     * https://lbs.amap.com/api/amap-mobile/guide/android/route
     */
    private void openGaodeMapToGuide(String mLatitude,String mLongitude) {
        String url;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        String curLatitude = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.LATITUDE,"");
        String curLongitude = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.LONGITUDE,"");
        Log.i("高德定位：","经度："+curLatitude+" ,纬度："+curLongitude);
        if (Double.parseDouble(curLatitude) == 0.0 || Double.parseDouble(curLongitude) == 0.0){
            url = "androidamap://route?sourceApplication=amap&dlat="+mLatitude+"&dlon="+mLongitude+"&dname="+mchName+"&dev=0&t=1";
        }else {
            url = "androidamap://route?sourceApplication=amap&slat="+curLatitude+"&slon="+curLongitude
                    +"&dlat="+mLatitude+"&dlon="+mLongitude+"&dname="+mchName+"&dev=0&t=1";
        }


        Uri uri = Uri.parse(url);
        //将功能Scheme以URI的方式传入data
        intent.setData(uri);
        //启动该页面即可
        startActivity(intent);
    }


    /**
     * BD-09 坐标转换成 GCJ-02 坐标
     */
    public static LatLng BD2GCJ(LatLng bd) {
        double x = bd.longitude - 0.0065, y = bd.latitude - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * Math.PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * Math.PI);

        double lng = z * Math.cos(theta);//lng
        double lat = z * Math.sin(theta);//lat
        return new LatLng(lat, lng);
    }

    /**
     * GCJ-02 坐标转换成 BD-09 坐标
     */
    public static LatLng GCJ2BD(LatLng bd) {
        double x = bd.longitude, y = bd.latitude;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * Math.PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * Math.PI);
        double tempLon = z * Math.cos(theta) + 0.0065;
        double tempLat = z * Math.sin(theta) + 0.006;
        return new LatLng(tempLat, tempLon);
    }


    /**
     * 检测程序是否安装
     *
     * @param packageName
     * @return
     */
    private boolean isInstalled(String packageName) {
        PackageManager manager = mContext.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> installedPackages = manager.getInstalledPackages(0);
        if (installedPackages != null) {
            for (PackageInfo info : installedPackages) {
                if (info.packageName.equals(packageName))
                    return true;
            }
        }
        return false;
    }
}
