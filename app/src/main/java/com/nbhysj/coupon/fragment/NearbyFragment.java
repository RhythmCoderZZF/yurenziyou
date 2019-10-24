package com.nbhysj.coupon.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.lin.cardlib.CardLayoutManager;
import com.lin.cardlib.CardSetting;
import com.lin.cardlib.CardTouchHelperCallback;
import com.lin.cardlib.OnSwipeCardListener;
import com.lin.cardlib.utils.ReItemTouchHelper;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.CardAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.model.HomePageModel;
import com.nbhysj.coupon.model.request.QueryByTopicRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FavoritesCollectionResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.HomePageAllSearchResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.model.response.HomePageTypeSearchResponse;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.presenter.HomePagePresenter;
import com.nbhysj.coupon.widget.NearbyTabIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/02/20
 * description：附近
 */
public class NearbyFragment extends BaseFragment<HomePagePresenter, HomePageModel> implements HomePageContract.View {

    //指示器
    @BindView(R.id.indicator)
    NearbyTabIndicator mTabIndicator;

    //暂无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;

    private List<HomePageResponse.SmallTagEntity> tagList;
    RecyclerView mRecyclerView;
    ReItemTouchHelper mReItemTouchHelper;
    private CardAdapter nearbyCardAdapter;
    private int mTagId;
    private boolean isInitView = false;
    private boolean isVisible = false;
    private List<HomePageSubTopicTagBean> nearbyCardList;
    private int mPage = 1;
    private int mPageSize = 10;
    public int cardSwipedOutNum = 0;
    private int hasNext;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String mLatitude = "";
    private String mLongitude = "";
    private HomePageSubTopicTagBean mHomePageSubTopicTagBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_nearby;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    public void newInstance(HomePageResponse.ResultBean.PostsTagsBean postsTagsBean) {
        if (tagList == null) {

            tagList = new ArrayList<HomePageResponse.SmallTagEntity>();
        } else {
            tagList.clear();
        }
        tagList = postsTagsBean.getSmallTagList();
        mTabIndicator.initTab(tagList, 13);
        mTabIndicator.setmTabSelector(0);
        if (tagList.size() > 0) {
            mTagId = tagList.get(0).getId();

            isInitView = true;
            isCanLoadData();

            mTabIndicator.setMyOnPageChangeListener(new NearbyTabIndicator.MyOnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    // showToast(getActivity(),position+"");
                    nearbyCardList.clear();
                    showProgressDialog(getActivity());
                    HomePageResponse.SmallTagEntity smallTagEntity = tagList.get(position);
                    mTagId = smallTagEntity.getId();
                    mPage = 1;
                    queryByTopic();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    @Override
    public void postOprateResult(BackResult res) {

    }

    @Override
    public void initView(View v) {
        initLocation();
        if (nearbyCardList == null) {

            nearbyCardList = new ArrayList<>();
        } else {
            nearbyCardList.clear();
        }
        mRecyclerView = (RecyclerView) v.findViewById(R.id.list);
        CardSetting setting = new CardSetting();
        setting.setSwipeListener(new OnSwipeCardListener<HomePageSubTopicTagBean>() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float dx, float dy, int direction) {
                switch (direction) {
                    case ReItemTouchHelper.DOWN:
                        Log.e("aaa", "swiping direction=down");
                        break;
                    case ReItemTouchHelper.UP:
                        Log.e("aaa", "swiping direction=up");
                        break;
                    case ReItemTouchHelper.LEFT:
                        Log.e("aaa", "swiping direction=left");
                        break;
                    case ReItemTouchHelper.RIGHT:
                        Log.e("aaa", "swiping direction=right");
                        break;
                }
            }

            @Override
            public void onSwipedOut(RecyclerView.ViewHolder viewHolder, HomePageSubTopicTagBean o, int direction) {
                cardSwipedOutNum++;
                if (hasNext == 1) {
                    if (cardSwipedOutNum == 10) {
                        showProgressDialog(getActivity());
                        mPage++;
                        queryByTopic();
                    }
                } else {
                    if (cardSwipedOutNum == 10) {
                        showProgressDialog(getActivity());
                        mPage = 1;
                        nearbyCardList.clear();
                        queryByTopic();
                    }
                }
                //switch (direction) {
                //   cardSwipedOutNum++;

                  /*  case ReItemTouchHelper.DOWN:
                        //  Toast.makeText(getActivity(), "swipe down out", Toast.LENGTH_SHORT).show();
                        break;
                    case ReItemTouchHelper.UP:
                        //Toast.makeText(getActivity(), "swipe up out ", Toast.LENGTH_SHORT).show();
                        break;
                    case ReItemTouchHelper.LEFT:
                        //Toast.makeText(getActivity(), "swipe left out", Toast.LENGTH_SHORT).show();
                        break;
                    case ReItemTouchHelper.RIGHT:
                        //Toast.makeText(getActivity(), "swipe right out", Toast.LENGTH_SHORT).show();
                        break;*/


                // }
            }

            @Override
            public void onSwipedClear() {
                // Toast.makeText(getActivity(), "cards are consumed", Toast.LENGTH_SHORT).show();
            }

        });
        CardTouchHelperCallback helperCallback = new CardTouchHelperCallback(mRecyclerView, nearbyCardList, setting);
        mReItemTouchHelper = new ReItemTouchHelper(helperCallback);
        CardLayoutManager layoutManager = new CardLayoutManager(mReItemTouchHelper, setting);
        mRecyclerView.setLayoutManager(layoutManager);
        nearbyCardAdapter = new CardAdapter(getActivity(), new CardAdapter.PostFollowListener() {
            @Override
            public void setPostFollowListener(HomePageSubTopicTagBean homePageSubTopicTagBean,int userId) {
                mHomePageSubTopicTagBean = homePageSubTopicTagBean;
                userFollow(userId);
            }
        });
        nearbyCardAdapter.setNearbyCardList(nearbyCardList);
        mRecyclerView.setAdapter(nearbyCardAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void getHomePageIndexResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void postsCommentResult(BackResult res) {

    }

    @Override
    public void getHomePageSearchAllResult(BackResult<HomePageAllSearchResponse> res) {

    }

    @Override
    public void getHomePageSearchByType(BackResult<HomePageTypeSearchResponse> res) {

    }

    @Override
    public void queryByTopicResult(BackResult<HomePageResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    nearbyCardList.clear();
                    cardSwipedOutNum = 0;
                    HomePageResponse.PageBean pageBean = res.getData().getPage();
                    hasNext = pageBean.getHasNext();
                    HomePageResponse.ResultBean result = res.getData().getResult();
                    List<HomePageSubTopicTagBean> postsTagsBeanList = result.getList();
                    nearbyCardList.addAll(postsTagsBeanList);

                    if (nearbyCardList.size() == 0) {
                        mRlytNoData.setVisibility(View.VISIBLE);
                    } else {

                        mRlytNoData.setVisibility(View.GONE);
                    }
                    nearbyCardAdapter.setNearbyCardList(nearbyCardList);
                    nearbyCardAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getHomeAttentionResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void getPostInfoResult(BackResult<PostInfoDetailResponse> res) {

    }

    @Override
    public void postCollectionResult(BackResult<FavoritesCollectionResponse> res) {

    }

    @Override
    public void getFavoritesListResult(BackResult<FavoritesListResponse> res) {

    }

    @Override
    public void userFollowResult(BackResult<FollowUserStatusResponse> res) {
        dismissProgressDialog();

        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    FollowUserStatusResponse followStatusResponse = res.getData();
                    int attentionStatus = followStatusResponse.getFollowStatus();

                    if(attentionStatus == 0)
                    {
                        mHomePageSubTopicTagBean.setLove(false);

                    } else if(attentionStatus == 1)
                    {
                        mHomePageSubTopicTagBean.setLove(true);
                    }

                    nearbyCardAdapter.setNearbyCardList(nearbyCardList);
                    nearbyCardAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(),Constants.getResultMsg(msg));
    }

    public void queryByTopic() {

        if (validateInternet()) {

            QueryByTopicRequest queryByTopicRequest = new QueryByTopicRequest();
            queryByTopicRequest.setId(mTagId);
            queryByTopicRequest.setPage(mPage);
            queryByTopicRequest.setPageSize(mPageSize);
            queryByTopicRequest.setLatitude(mLatitude);
            queryByTopicRequest.setLongitude(mLongitude);
            mPresenter.queryByTopic(queryByTopicRequest);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见，获取该标志记录下来
       /* if(isRefrsh){
            queryByTopic();
        }*/
        if (isVisibleToUser) {
            isVisible = true;
            isCanLoadData();
        } else {
            isVisible = false;
        }
    }

    private void isCanLoadData() {
        //所以条件是view初始化完成并且对用户可见
        if (isInitView && isVisible) {
            queryByTopic();

            //防止重复加载数据
            isInitView = false;
            isVisible = false;
        }
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(getActivity());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);

        startLocation();
    }


    /**
     * 默认的定位参数
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    /**
     * 开始定位
     */
    private void startLocation() {
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }


    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                mLongitude = String.valueOf(location.getLongitude());
                mLatitude = String.valueOf(location.getLatitude());


             /*   StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if(location.getErrorCode() == 0){
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //定位完成的时间
                    sb.append("定位时间: " + MapUtils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }
                sb.append("***定位质量报告***").append("\n");
                sb.append("* WIFI开关：").append(location.getLocationQualityReport().isWifiAble() ? "开启":"关闭").append("\n");
                sb.append("* GPS状态：").append(getGPSStatusString(location.getLocationQualityReport().getGPSStatus())).append("\n");
                sb.append("* GPS星数：").append(location.getLocationQualityReport().getGPSSatellites()).append("\n");
                sb.append("* 网络类型：" + location.getLocationQualityReport().getNetworkType()).append("\n");
                sb.append("* 网络耗时：" + location.getLocationQualityReport().getNetUseTime()).append("\n");
                sb.append("****************").append("\n");
                //定位之后的回调时间
                sb.append("回调时间: " + MapUtils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");

                //解析定位结果，
                String result = sb.toString();*/
            } else {
            }
        }
    };

    /**
     * 获取GPS状态的字符串
     *
     * @param statusCode GPS状态码
     * @return
     */
    private String getGPSStatusString(int statusCode) {
        String str = "";
        switch (statusCode) {
            case AMapLocationQualityReport.GPS_STATUS_OK:
                str = "GPS状态正常";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPROVIDER:
                str = "手机中没有GPS Provider，无法进行GPS定位";
                break;
            case AMapLocationQualityReport.GPS_STATUS_OFF:
                str = "GPS关闭，建议开启GPS，提高定位质量";
                break;
            case AMapLocationQualityReport.GPS_STATUS_MODE_SAVING:
                str = "选择的定位模式中不包含GPS定位，建议选择包含GPS定位的模式，提高定位质量";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPERMISSION:
                str = "没有GPS定位权限，建议开启gps定位权限";
                break;
        }
        return str;
    }

    @Override
    public void lazyInitView(View view) {

    }


    public void userFollow(int userId){

        if(validateInternet()){

            showProgressDialog(getActivity());
            mPresenter.userFollow(userId);
        }
    }
}
