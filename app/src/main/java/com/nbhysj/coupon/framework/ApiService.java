package com.nbhysj.coupon.framework;

import com.nbhysj.coupon.model.request.AddCountyRequest;
import com.nbhysj.coupon.model.request.AddMchRequest;
import com.nbhysj.coupon.model.request.AddRemarksRequest;
import com.nbhysj.coupon.model.request.AddTrafficRequest;
import com.nbhysj.coupon.model.request.CollectionBatchMchDeleteRequest;
import com.nbhysj.coupon.model.request.CollectionBatchPostsDeleteRequest;
import com.nbhysj.coupon.model.request.ContactsInfoRequest;
import com.nbhysj.coupon.model.request.CreateFavoritesRequest;
import com.nbhysj.coupon.model.request.CreateTripRequest;
import com.nbhysj.coupon.model.request.DeleteFavoritesRequest;
import com.nbhysj.coupon.model.request.DeleteTravellerInfoRequest;
import com.nbhysj.coupon.model.request.DeleteTripPlaceRequest;
import com.nbhysj.coupon.model.request.DeleteTripRequest;
import com.nbhysj.coupon.model.request.EditTripSubmitRequest;
import com.nbhysj.coupon.model.request.FavoritesBatchDeleteContentRequest;
import com.nbhysj.coupon.model.request.FavoritesBatchMoveContentRequest;
import com.nbhysj.coupon.model.request.FindPwdByEmailRequest;
import com.nbhysj.coupon.model.request.FindPwdByPhoneRequest;
import com.nbhysj.coupon.model.request.GroupMchOrderSubmitRequest;
import com.nbhysj.coupon.model.request.HotelHomestayOrderSubmitRequest;
import com.nbhysj.coupon.model.request.LoginRequest;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.request.MoveFavoritesContentRequest;
import com.nbhysj.coupon.model.request.OrderAllRefundRequest;
import com.nbhysj.coupon.model.request.OrderCancelRequest;
import com.nbhysj.coupon.model.request.OrderDeleteRequest;
import com.nbhysj.coupon.model.request.OrderGroupCommentRequest;
import com.nbhysj.coupon.model.request.OrderPartialCommentRequest;
import com.nbhysj.coupon.model.request.OrderPartialRefundRequest;
import com.nbhysj.coupon.model.request.PostOprateRequest;
import com.nbhysj.coupon.model.request.PostsCollectionRequest;
import com.nbhysj.coupon.model.request.PostsCommentRequest;
import com.nbhysj.coupon.model.request.PublishPostRequest;
import com.nbhysj.coupon.model.request.QueryByTopicRequest;
import com.nbhysj.coupon.model.request.QuestionAnsweringPublishRequest;
import com.nbhysj.coupon.model.request.RecipientsInfoRequest;
import com.nbhysj.coupon.model.request.RegisterUserRequest;
import com.nbhysj.coupon.model.request.ThirdPartyLoginCreateUserBind;
import com.nbhysj.coupon.model.request.ThirdPartyLoginRequest;
import com.nbhysj.coupon.model.request.TicketOrderSubmitRequest;
import com.nbhysj.coupon.model.request.TopicRequest;
import com.nbhysj.coupon.model.request.TravelAssistantAddOneDayRequest;
import com.nbhysj.coupon.model.request.TravellerInfoRequest;
import com.nbhysj.coupon.model.request.TripintelligentRequest;
import com.nbhysj.coupon.model.request.UpdateFavoritesRequest;
import com.nbhysj.coupon.model.request.UpdateUserInfoRequest;
import com.nbhysj.coupon.model.response.AlbumFavoritesDetail;
import com.nbhysj.coupon.model.response.ArticleWithCateResponse;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CarH5UrlResponse;
import com.nbhysj.coupon.model.response.ContactsInfoResponse;
import com.nbhysj.coupon.model.response.CountryBean;
import com.nbhysj.coupon.model.response.CreateFavoritesResponse;
import com.nbhysj.coupon.model.response.CreateTripResponse;
import com.nbhysj.coupon.model.response.DestinationResponse;
import com.nbhysj.coupon.model.response.EstimatedPriceResponse;
import com.nbhysj.coupon.model.response.FavoritesCollectionResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FavoritesResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.FoodRecommendListResponse;
import com.nbhysj.coupon.model.response.GroupMchDetailsResponse;
import com.nbhysj.coupon.model.response.GroupMchResponse;
import com.nbhysj.coupon.model.response.HomePageAllSearchResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageTypeSearchResponse;
import com.nbhysj.coupon.model.response.HotScenicSpotResponse;
import com.nbhysj.coupon.model.response.HotTagsTopicBean;
import com.nbhysj.coupon.model.response.HotelOrderInitResponse;
import com.nbhysj.coupon.model.response.LimitedTimeSalePageBean;
import com.nbhysj.coupon.model.response.LoginResponse;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCommentResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MchFoodDetailResponse;
import com.nbhysj.coupon.model.response.MchHomestayDetailsResponse;
import com.nbhysj.coupon.model.response.MerchantListResponse;
import com.nbhysj.coupon.model.response.MineCollectionAllResponse;
import com.nbhysj.coupon.model.response.MineCollectionDetailResponse;
import com.nbhysj.coupon.model.response.MinePostZanListResponse;
import com.nbhysj.coupon.model.response.MyCardResponse;
import com.nbhysj.coupon.model.response.MyPostShareResponse;
import com.nbhysj.coupon.model.response.NetFriendAlbumResponse;
import com.nbhysj.coupon.model.response.OrderAllRefundInitResponse;
import com.nbhysj.coupon.model.response.OrderDetailResponse;
import com.nbhysj.coupon.model.response.OrderGoodsInitResponse;
import com.nbhysj.coupon.model.response.OrderGroupGoodsInitResponse;
import com.nbhysj.coupon.model.response.OrderPaymentResponse;
import com.nbhysj.coupon.model.response.OrderRefundDetailResponse;
import com.nbhysj.coupon.model.response.OrderRefundInitResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.model.response.OrderSubmitResponse;
import com.nbhysj.coupon.model.response.PostCommentBean;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.model.response.PostsCommentResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.model.response.RecipientsInfoResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.ShopMallHomePageResponse;
import com.nbhysj.coupon.model.response.StrategyResponse;
import com.nbhysj.coupon.model.response.TagTopicSearchResponse;
import com.nbhysj.coupon.model.response.ThirdPartyLoginStatusResponse;
import com.nbhysj.coupon.model.response.TopicResponse;
import com.nbhysj.coupon.model.response.TourGuideBean;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.model.response.TravellerInfoResponse;
import com.nbhysj.coupon.model.response.TripDetailsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.model.response.TripRouteMapResponse;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.model.response.UserFansFollowResponse;
import com.nbhysj.coupon.model.response.UserInfoResponse;
import com.nbhysj.coupon.model.response.UserOrderListResponse;
import com.nbhysj.coupon.model.response.UserPersonalHomePageResponse;
import com.nbhysj.coupon.model.response.WaitMyAnswerResponse;
import com.nbhysj.coupon.model.response.WeatherResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * created by hysj at 2019/02/23.
 * description：接口访问url
 */
public interface ApiService {

    //1.获取注册验证短信
    @GET("api/user/mobile/message")
    Observable<BackResult> getRegisterVerifyCode(@Query("mobile") String mobile);

    //2.用户注册
    @POST("api/user")
    Observable<BackResult> registerUser(@Body RegisterUserRequest registerUserRequest);

    //3.获取盐(登录除外)
    @GET("api/user/getSalt")
    Observable<BackResult<Object>> getSalt(@Query("mobile") String mobile);

    //4.获取盐(只有登录可用)
    @GET("api/user/getLoginSalt")
    Observable<BackResult> getLoginSalt(@Query("username") String username);

    //5.获取盐(通过邮箱修改密码)
    @GET("api/user/getSalt")
    Observable<BackResult<Object>> updatePwdByEmailGetSalt(@Query("email") String email);

    //6.获取登录验证短信
    @GET("api/user/login/message")
    Observable<BackResult> getLoginVerifyCode(@Query("mobile") String mobile);

    //7.登录
    @POST("api/user/login")
    Observable<BackResult<LoginResponse>> login(@Body LoginRequest loginRequest);

    //8.获取找回密码短信
    @GET("api/user/password/message")
    Observable<BackResult> getFindPwdVerifyCode(@Query("mobile") String mobile);

    //9.修改密码
    @POST("api/user/modifyPassword")
    Observable<BackResult> modifyPasswordByMobile(@Body FindPwdByPhoneRequest findPwdRequest);

    //10.发送邮箱
    @GET("api/user/sendEmail")
    Observable<BackResult> sendEmail(@Query("email") String email);

    //11.发邮箱修改密码
    @POST("api/user/email/modifyPassword")
    Observable<BackResult> modifyPasswordByEmail(@Query("email") FindPwdByEmailRequest findPwdByEmailRequest);

    //12.查询个人信息
    @GET("api/user")
    Observable<BackResult<UserInfoResponse>> getUserInfo(@Query("id") int id);

    //13.个人信息修改
    @PUT("api/user/updateInformation")
    Observable<BackResult> updateInformation(@Body UpdateUserInfoRequest updateUserInfoRequest);

    //14.登出
    @GET("api/user/cancel")
    Observable<BackResult> userLogout();

    //获取第三方登录状态
    @GET("api/user/accountManagement")
    Observable<BackResult<ThirdPartyLoginStatusResponse>> getThirdPartyLoginStatus(@Query("userId") int userId);

    //第三方登陆
    @POST("api/thirdPartyLogin/login")
    Observable<BackResult<LoginResponse>> thirdPartyLogin(@Body ThirdPartyLoginRequest thirdPartyLoginRequest);

    //首次第三方登陆需注册
    @POST("api/thirdPartyLogin/createUser")
    Observable<BackResult<LoginResponse>> thirdPartyLoginCreateUser(@Body ThirdPartyLoginCreateUserBind thirdPartyLoginCreateUserBind);

     //名片
    @GET("api/index/myNameCard")
    Observable<BackResult<MyCardResponse>> getMyCard(@Query("userId") int userId);

    /****************       1.旅客信息        *************/
    //15.查询旅客信息列表
    @GET("api/userTraveller")
    Observable<BackResult<TravellerInfoResponse>> getUserTravellerList(@Query("userId") int userId, @Query("page") int page, @Query("pageSize") int pageSize);

    //16.添加旅客信息
    @POST("api/userTraveller/add")
    Observable<BackResult> addTravellerInfo(@Body TravellerInfoRequest addTravellerRequest);

    //17.修改旅客信息
    @POST("api/userTraveller/update")
    Observable<BackResult> updateTravellerInfo(@Body TravellerInfoRequest updateTravellerRequest);

    //18.删除旅客信息
    @POST("api/userTraveller/delete")
    Observable<BackResult> deleteTravellerInfo(@Body DeleteTravellerInfoRequest deleteTravellerInfoRequest);

    /****************      2. 联系人信息        *************/

    //19.查询联系人列表
    @GET("api/userContacts/findList")
    Observable<BackResult<ContactsInfoResponse>> getUserContactsList(@Query("userId") int userId);

    //20.添加联系人
    @POST("api/userContacts")
    Observable<BackResult> addUserContactsInfo(@Body ContactsInfoRequest addTravellerRequest);

    //21.修改联系人
    @PUT("api/userContacts")
    Observable<BackResult> updateContactsInfo(@Body ContactsInfoRequest updateTravellerRequest);

    //22.删除联系人
    @HTTP(method = "DELETE", path = "api/userContacts", hasBody = true)
    Observable<BackResult> deleteContactsInfo(@Body DeleteTravellerInfoRequest deleteTravellerInfoRequest);

    /****************      3. 联系人信息       *************/
    //查询收件人列表

    /**
     * @param id       用户id
     * @param mobile
     * @param page
     * @param pageSize
     * @return
     */
    @GET("api/userAddress")
    Observable<BackResult<RecipientsInfoResponse>> getRecipientsInfoList(@Query("id") int id, @Query("mobile") String mobile, @Query("page") int page, @Query("pageSize") int pageSize);

    // 添加收件人信息
    @POST("api/userAddress/add")
    Observable<BackResult<RecipientsInfoResponse>> addRecipientsInfo(@Body RecipientsInfoRequest recipientsInfoRequest);

    // 修改收件人信息
    @POST("api/userAddress/update")
    Observable<BackResult> updateRecipientsInfo(@Body RecipientsInfoRequest recipientsInfoRequest);

    // 删除收件人信息
    @POST("api/userAddress/delete")
    Observable<BackResult> deleteRecipientsInfo(@Body DeleteTravellerInfoRequest deleteTravellerInfoRequest);

    //获取收件人省市区
    @GET("api/city")
    Observable<BackResult<List<RecipientAddressResponse>>> getRecipientsAddress();

    //搜索商户(发布帖子)
    @GET("api/mch")
    Observable<BackResult<MerchantListResponse>> getMerchantList(@Query("cityId") String cityId, @Query("mchName") String mchName, @Query("page") int page, @Query("pageSize") int pageSize);

    //主题搜索(热门标签)
    @GET("api/topic/list")
    Observable<BackResult<List<HotTagsTopicBean>>> getHotTagsTopicList(@Query("type") String type);

    //主题搜索（标签查询）
    @GET("api/topic/search")
    Observable<BackResult<TagTopicSearchResponse>> topicSearch(@Query("type") String type, @Query("param") String param);

    //创建主题(新增标签)
    @POST("api/topic/create")
    Observable<BackResult<HotTagsTopicBean>> topicCreate(@Body TopicRequest topicRequest);

    //发布帖子
    @POST("api/posts/createPost")
    Observable<BackResult> createPost(@Body PublishPostRequest publishPostRequest);

    //常见问题分类与常见问题(文章分类与文章)
    @GET("api/articleWithCate")
    Observable<BackResult<ArticleWithCateResponse>> getArticleWithCate();

    //首页
    @GET("api/index")
    Observable<BackResult<HomePageResponse>> getHomePageIndex();

    //获取 附近&推荐
    @POST("api/index/queryByTopic")
    Observable<BackResult<HomePageResponse>> queryByTopic(@Body QueryByTopicRequest queryByTopicRequest);

    //获取首页关注
    @GET("api/index/attention")
    Observable<BackResult<HomePageResponse>> getHomeAttention(@Query("page") int page, @Query("pageSize") int pageSize);

    //贴子详情
    @GET("api/index/postInfo")
    Observable<BackResult<PostInfoDetailResponse>> getPostInfo(@Query("id") int id, @Query("postKey") String postKey, @Query("longitude") String longitude, @Query("latitude") String latitude);

    //帖子点赞、收藏或者评论
    //帖子类型： 1点赞帖子 2点赞帖子的评论  postsType
    @PUT("api/PostsUserZanCollection/praiseOrCollect")
    Observable<BackResult<PraiseOrCollectResponse>> postOprate(@Body PostOprateRequest postOprateRequest);

    //帖子评论
    @POST("api/postsComment/addPostsComment")
    Observable<BackResult> postsCommentRequest(@Body PostsCommentRequest postsCommentRequest);

    //分页查看帖子所有评论
    @GET("api/postsComment/findAllByAuthorId")
    Observable<BackResult<PostsCommentResponse>> getAllPostsCommentListByArticleId(@Query("articleId") int articleId, @Query("page") int page, @Query("pageSize") int pageSize);

    //他人主页
    @GET("api/index/homePageInfo")
    Observable<BackResult<UserPersonalHomePageResponse>> getOthershomePageInfo(@Query("userId") int userId);

    /***********************************   商城   ****************************************/

    //获取商城首页
    @GET("api/store/index")
    Observable<BackResult<ShopMallHomePageResponse>> getShopMallHomePageData();

    //景点栏目首页
    @GET("api/store/scenic")
    Observable<BackResult<ScenicSpotHomePageResponse>> getScenicSpotHomePage(@Query("longitude") String longitude, @Query("latitude") String latitude);

    //景点栏目筛选
    @GET("api/store/findScenicByCate")
    Observable<BackResult<ScenicSpotResponse>> findScenicByCate(@QueryMap HashMap<String, String> map);
    //@Query("longitude") String longitude, @Query("latitude") String latitude, @Query("cateId") String cateId, @Query("sorting") String sorting, @Query("page") int page, @Query("pageSize") int pageSize

    //景点榜单
    @GET("api/store/scenicRanking")
    Observable<BackResult<MchBangDanRankingResponse>> getScenicBangDanRanking(@Query("cityId") int cityId);

    //美食栏目首页
    @GET("api/store/food")
    Observable<BackResult<ScenicSpotHomePageResponse>> getFineFoodHomePage(@Query("longitude") String longitude, @Query("latitude") String latitude);

    //美食栏目筛选
    @GET("api/store/findFoodByCate")
    Observable<BackResult<ScenicSpotResponse>> findFoodByCate(@QueryMap HashMap<String, String> map);
    //@Query("longitude") String longitude, @Query("latitude") String latitude, @Query("cateId") String cateId, @Query("sorting") String sorting, @Query("page") int page, @Query("pageSize") int pageSize

    //美食榜单
    @GET("api/store/foodRanking")
    Observable<BackResult<MchBangDanRankingResponse>> getFoodBangDanRank(@Query("cityId") int cityId);

    //酒店栏目首页
    @GET("api/store/hotel")
    Observable<BackResult<ScenicSpotHomePageResponse>> getHotelHomePage(@Query("longitude") String longitude, @Query("latitude") String latitude);

    //酒店栏目筛选
    @GET("api/store/findHotelByCate")
    Observable<BackResult<ScenicSpotResponse>> findHotelByCate(@QueryMap HashMap<String, String> map);

    //酒店榜单
    @GET("api/mchRank/wineshop")
    Observable<BackResult<MchBangDanRankingResponse>> getHotelBangDanRank(@Query("cityId") int cityId);

    //民宿栏目首页
    @GET("api/store/homeStay")
    Observable<BackResult<ScenicSpotHomePageResponse>> getHomestayHomePage(@Query("longitude") String longitude, @Query("latitude") String latitude);

    //民宿栏目筛选
    @GET("api/store/findHomeStayByCate")
    Observable<BackResult<ScenicSpotResponse>> findHomestayByCate(@QueryMap HashMap<String, String> map);

    //民宿榜单（仅民宿)
    @GET("api/mchRank/homestayRank")
    Observable<BackResult<MchBangDanRankingResponse>> getHomestayBangDanRank(@Query("cityId") int cityId);

    //互动栏目首页
    @GET("api/store/recreation")
    Observable<BackResult<ScenicSpotHomePageResponse>> getRecreationHomePage(@Query("longitude") String longitude, @Query("latitude") String latitude);

    //互动栏目筛选
    @GET("api/store/findRecreationByCate")
    Observable<BackResult<ScenicSpotResponse>> findRecreationByCate(@QueryMap HashMap<String, String> map);

    //互动榜单
    @GET("api/store/recreationRanking")
    Observable<BackResult<MchBangDanRankingResponse>> getRecreationRanking(@Query("cityId") int cityId);

    //商户类型收藏
    @POST("api/mch/mchCollection")
    Observable<BackResult<MchCollectionResponse>> mchCollection(@Body MchCollectionRequest mchCollectionRequest);

    //目的地首页
    @GET("api/destination/findByCityName")
    Observable<BackResult<DestinationResponse>> getDestinationHomePage(@Query("cityId") int cityId);

    //目的地搜索
    @GET("api/destination/findMchBycityName")
    Observable<BackResult<HotScenicSpotResponse>> findMchBycityName(@Query("cityId") int cityId, @Query("mchType") int mchType, @Query("page") int page, @Query("pageSize") int pageSize);

    //商户详情
    @GET("api/mchDetails")
    Observable<BackResult<MchDetailsResponse>> getMchDetails(@Query("mchId") int mchId);

    //民宿详情
    @GET("api/mchDetails")
    Observable<BackResult<MchHomestayDetailsResponse>> getMchHomestayDetail(@Query("mchId") int mchId);

    //美食详情
    @GET("api/mchDetails")
    Observable<BackResult<MchFoodDetailResponse>> getMchFoodDetail(@Query("mchId") int mchId);

    //美食推荐列表
    @GET("api/goodsFood/menu")
    Observable<BackResult<FoodRecommendListResponse>> goodsFoodRecommendList(@Query("mchId") int mchId);

    //商家相册
    @GET("api/mchPhoto/findByMchId")
    Observable<BackResult<MchAlbumResponse>> getMchAlbumList(@Query("mchId") int mchId);

    //网友相册
    @GET("api/mchComment/albumListOfNetizens")
    Observable<BackResult<NetFriendAlbumResponse>> getNetFriendsAlbumList(@Query("mchId") int mchId, @Query("page") int page, @Query("pageSize") int pageSize);

    //游玩指南列表
    @GET("api/playItem/findList")
    Observable<BackResult<List<TourGuideBean>>> getTourGuideList(@QueryMap HashMap<String, String> map);

    //门票订单提交界面(景区日历价格)
    @GET("api/goods/scenic")
    Observable<BackResult<OrderSubmitInitResponse>> getOrderSubmitInit(@Query("goodsId") int goodsId);

    //门票订单生成接口(门票下单接口)
    @POST("api/ticketOrder")
    Observable<BackResult<OrderSubmitResponse>> ticketOrderSubmit(@Body TicketOrderSubmitRequest ticketOrderSubmitRequest);

    //景区互动价格日历
    @GET("api/recreation/goods")
    Observable<BackResult<OrderSubmitInitResponse>> getRecreationDatePriceInit(@Query("goodsId") int goodsId);

    //互动下单接口
    @POST("api/goods/recreation")
    Observable<BackResult<OrderSubmitResponse>> recreationOrderSubmit(@Body TicketOrderSubmitRequest ticketOrderSubmitRequest);

    //首页限时特卖列表
    @GET("api/mch/limitedTimeSalePage")
    Observable<BackResult<LimitedTimeSalePageBean>> getLimitedTimeSalePage();

    //首页限时特卖详情
    @GET("api/mch/rushPurchaseInfo")
    Observable<BackResult<OrderSubmitInitResponse>> getRushPurchaseInfo();

    //首页搜索
    @GET("api/search")
    Observable<BackResult<HomePageTypeSearchResponse>> getHomePageSearchByType(@Query("queryType") String queryType, @Query("keyword") String keyword,@Query("page") int page, @Query("pageSize") int pageSize);

    //首页搜索全部
    @GET("api/search")
    Observable<BackResult<HomePageAllSearchResponse>> getHomePageSearchALL(@Query("queryType") String queryType, @Query("keyword") String keyword);

    //获取商户评论列表
    @GET("api/mchComment")
    Observable<BackResult<MchCommentResponse>> getMchCommentList(@Query("mchId") int mchId);

    //帖子收藏
    @POST("api/posts/postsCollection")
    Observable<BackResult<FavoritesCollectionResponse>> postsCollection(@Body PostsCollectionRequest postsCollectionRequest);

    //获取粉丝列表
    @GET("api/userFans/findList")
    Observable<BackResult<UserFansFollowResponse>> getUserFansList();

    //关注
    @GET("api/userFans/create")
    Observable<BackResult<FollowUserStatusResponse>> userFollow(@Query("userId") int userId);

    /*************     行程助手      ***************/
    //行程助手列表
    @GET("api/trip")
    Observable<BackResult<TripHomePageResponse>> getTripHomePage();

    //区县选择列表接口
    @GET("api/trip/insertCountyWeb")
    Observable<BackResult<List<TravelAssistantDetailCountryBean>>> getCountyWebList(@QueryMap HashMap<String, String> map);

    //行程助手详情区县列表接口
    @GET("api/trip/countyList")
    Observable<BackResult<List<CountryBean>>> getCountyList(@Query("tripId") int tripId);

    //创建行程
    @POST("api/trip/createTrip")
    Observable<BackResult<CreateTripResponse>> createTrip(@Body CreateTripRequest createTripRequest);

    //行程助手详情
    @GET("api/trip/tripDetails")
    Observable<BackResult<TripDetailsResponse>> getTravelAssistantDetail(@Query("tripId") int tripId);

    //行程助手商户列表
    @GET("api/trip/mchList")
    Observable<BackResult<TripScenicSpotAddCountryBean>> getTravelAssistantMchList(@Query("tripId") int tripId, @Query("countyId") String countyId, @Query("mchType") String mchType, @Query("page") int page, @Query("pageSize") int pageSize);

    //添加区县
    @POST("api/trip/insertCounty")
    Observable<BackResult<CreateTripResponse>> insertCounty(@Body AddCountyRequest addCountyRequest);

    //添加一天
    @POST("api/trip/plusADay")
    Observable<BackResult> travelAssistantPlusADay(@Body TravelAssistantAddOneDayRequest addOneDayRequest);

    //添加商户
    @POST("api/trip/insertPlaceMch")
    Observable<BackResult<CreateTripResponse>> insertPlaceMch(@Body AddMchRequest addMchRequest);

    //添加交通
    @POST("api/trip/insertTraffic")
    Observable<BackResult<CreateTripResponse>> insertTraffic(@Body AddTrafficRequest addTrafficRequest);

    //添加备注
    @POST("api/trip/insertNote")
    Observable<BackResult<CreateTripResponse>> insertNote(@Body AddRemarksRequest addRemarksRequest);

    //编辑行程提交
    @PUT("api/trip/createTrip")
    Observable<BackResult> updateTripInformation(@Body EditTripSubmitRequest editTripSubmitRequest);

    //行程删除接口
    @HTTP(method = "DELETE", path = "api/trip/delTrip", hasBody = true)
    Observable<BackResult> delTrip(@Body DeleteTripRequest DeleteTripRequest);

    //行程点删除接口
    @HTTP(method = "DELETE", path = "api/trip/delTripPlace", hasBody = true)
    Observable<BackResult> delTripPlace(@Body DeleteTripPlaceRequest deleteTripPlaceRequest);

    //行程地图预览接口
    @GET("api/trip/tripMap")
    Observable<BackResult<TripRouteMapResponse>> getTripMap(@Query("tripId") int tripId);

    //智能规划接口
    @GET("api/trip/intelligentProject")
    Observable<BackResult<TripDetailsResponse>> intelligentProject(@Body TripintelligentRequest tripintelligentRequest);

    //获取天气接口
    @GET("api/weather")
    Observable<BackResult<WeatherResponse>> getWeather(@Query("cityCode") int cityCode);

    /**********************************        用车          ****************************************/

    //预估价格
    @GET("car/prePrice")
    Observable<BackResult<EstimatedPriceResponse>> getEstimatedPrice(@QueryMap Map<String, Object> map);

    //曹操叫车H5
    @GET("car/carH5Url")
    Observable<BackResult<CarH5UrlResponse>> getCarH5Url(@Query("startLg") String longitude, @Query("startLt") String latitude);

    //获取订单列表
    @GET("api/order/userOrderList")
    Observable<BackResult<UserOrderListResponse>> getUserOrderList(@Query("page") int page, @Query("pageSize") int pageSize);

    //用户待付款订单
    @GET("api/order/queryCreateOrder")
    Observable<BackResult<UserOrderListResponse>> getPendingOrdersList(@Query("page") int page, @Query("pageSize") int pageSize);

    //用户待出行订单
    @GET("api/order/queryAwaitGoing")
    Observable<BackResult<UserOrderListResponse>> getAwaitGoingList(@Query("page") int page, @Query("pageSize") int pageSize);

    //用户待评论订单
    @GET("api/order/queryAwaitComment")
    Observable<BackResult<UserOrderListResponse>> getAwaitCommentList(@Query("page") int page, @Query("pageSize") int pageSize);

    //退换/售后订单列表
    @GET("api/order/queryAwaitRefund")
    Observable<BackResult<UserOrderListResponse>> getAwaitRefundList(@Query("page") int page, @Query("pageSize") int pageSize);

    //删除订单
    @HTTP(method = "DELETE", path = "api/order/deleteOrder", hasBody = true)
    Observable<BackResult> deleteOrder(@Body OrderDeleteRequest deleteOrderRequest);

    //订单详情
    @GET("api/order/detail")
    Observable<BackResult<OrderDetailResponse>> getOrderDetail(@Query("orderNo") String orderNo);

    //订单取消
    @GET("api/order/pay/cancelOrder")
    Observable<BackResult<OrderDetailResponse>> cancelOrder(@Body OrderCancelRequest cancelOrderRequest);

    //推荐酒店评分
    @GET("api/order/score")
    Observable<BackResult> willingToRecommendScore(@Query("orderNo") String orderNo, @Query("score") int score);

    //酒店+民宿下单初始化页面
    @GET("api/goods/hotel/orderSubmission")
    Observable<BackResult<HotelOrderInitResponse>> getHotelHomestayOrderInit(@Query("goodId") int goodsId, @Query("checkInAndOutTime") String checkInAndOutTime);

    //酒店+民宿下单接口
    @POST("api/goods/hotel")
    Observable<BackResult<OrderSubmitResponse>> hotelHomestayOrderSubmit(@Body HotelHomestayOrderSubmitRequest hotelHomestayOrderSubmitRequest);

    /****************  专辑   *****************/

    //专辑详情
    @POST("api/userFavorites/info")
    Observable<BackResult<AlbumFavoritesDetail>> userFavoritesInfo(@Query("favoritesId") int favoritesId);

    //新增专辑
    @POST("api/userFavorites/createFavorites")
    Observable<BackResult<CreateFavoritesResponse>> createFavorites(@Body CreateFavoritesRequest createFavoritesRequest);

    //修改专辑
    @PUT("api/userFavorites/updateFavorites")
    Observable<BackResult> updateFavorites(@Body UpdateFavoritesRequest updateFavoritesRequest);

    //删除专辑
    @HTTP(method = "DELETE", path = "api/userContacts", hasBody = true)
    Observable<BackResult> deleteContactsInfo(@Body DeleteFavoritesRequest deleteFavoritesRequest);

    //查询专辑内容
    @GET("api/userFavorites/query") //（这个接口暂无用到）
    Observable<BackResult<FavoritesResponse>> queryUserFavorites(@Query("id") int id, @Query("page") int page, @Query("pageSize") int pageSize);

    //查询专辑详情
    @GET("api/userFavorites/info")
    Observable<BackResult<AlbumFavoritesDetail>> getAlbumFavoritesDetail(@Query("favoritesId") int favoritesId);

    //移动专辑内容
    @PUT("api/userFavorites/moveContent")
    Observable<BackResult> moveFavorites(@Body MoveFavoritesContentRequest moveFavoritesContentRequest);

    //我的专辑列表(弹框)
    @GET("api/userCollectionShow/userFindFavorites")
    Observable<BackResult<FavoritesListResponse>> getUserFindFavoritesList(@Query("page") int page, @Query("pageSize") int pageSize);

    //我的模块专辑列表
    @GET("api/userFavorites")
    Observable<BackResult<FavoritesListResponse>> getFavoritesCollectionList(@Query("page") int page, @Query("pageSize") int pageSize);

    //(我的)分享列表
    @GET("api/posts/myPost")
    Observable<ResponseBody> getMyPostShareList();

    //(我的)赞过列表
    @GET("api/userCollectionShow/beforeZen")
    Observable<BackResult<MinePostZanListResponse>> getMinePostZanList(@Query("page") int page, @Query("pageSize") int pageSize);

    //我的收藏全部列表
    @GET("api/userCollectionShow")
    Observable<BackResult<List<MineCollectionAllResponse>>> getMineCollectionAllList();

    //我的收藏全部列表
    @GET("api/userCollectionShow")
    Observable<BackResult<MineCollectionDetailResponse>> getMineCollectionDetail(@Query("type") String type,@Query("page") int page, @Query("pageSize") int pageSize);

    //批量移动专辑内容到另一个专辑
    @POST("api/userFavorites/batchMoveContent")
    Observable<BackResult> albumFavoritesbatchMoveContent(@Body FavoritesBatchMoveContentRequest favoritesBatchMoveContentRequest);

    //批量移动专辑内容到另一个专辑
    @POST("api/userFavorites/deleteContent")
    Observable<BackResult> albumFavoritesDeleteContent(@Body FavoritesBatchDeleteContentRequest favoritesBatchDeleteContentRequest);

    //批量删除收藏商户内容
    @POST("api/userCollectionShow/mchCollection")
    Observable<BackResult> collectionMchBatchDeleteContentRequest(@Body CollectionBatchMchDeleteRequest collectionBatchMchDeleteRequest);

    //批量删除收藏帖子内容
    @POST("api/userCollection/batchDelete")
    Observable<BackResult> collectionPostsBatchDeleteRequest(@Body CollectionBatchPostsDeleteRequest collectionBatchPostsDeleteRequest);

    /***************   组合模块    ***************/

    //攻略列表
    @GET("api/strategyArticle/findAllStrategy")
    Observable<BackResult<StrategyResponse>> findAllStrategy(@Query("page") int page, @Query("pageSize") int pageSize);

    //组合首页
    @GET("api/group")
    Observable<BackResult<GroupMchResponse>> getGroupHomePage();

    //组合列表
    @GET("api/group/homeList")
    Observable<BackResult<StrategyResponse>> getGroupHomeList(@Query("tagsId") int tagsId, @Query("page") int page, @Query("pageSize") int pageSize);

    //组合详情
    @GET("api/mchDetails/groupDetails")
    Observable<BackResult<GroupMchDetailsResponse>> getGroupMchDetail(@Query("packageId") int packageId);

    //组合下单初始化
    @GET("api/groupOrder/priceDate")
    Observable<BackResult<OrderSubmitInitResponse>> getGroupMchOrderSubmitInit(@Query("groupId") int groupId);

    //组合下单
    @POST("api/groupOrder")
    Observable<BackResult<OrderSubmitResponse>> groupMchOrderSubmit(@Body GroupMchOrderSubmitRequest groupMchOrderSubmitRequest);

    /***************   退款    ******************/
    //部分退款页面初始化
    @GET("api/orderRefund/refundOneIndex")
    Observable<BackResult<OrderRefundInitResponse>> getOrderRefundPartialInit(@Query("orderGoodsId") int orderGoodsId, @Query("goodsType") String goodsType);

    //部分退款提交
    @POST("api/orderRefund/one")
    Observable<BackResult> orderPartialRefundSubmit(@Body OrderPartialRefundRequest orderPartialRefundRequest);

    //全部退款页面初始化
    @GET("api/orderRefund/refundAllIndex")
    Observable<BackResult<OrderAllRefundInitResponse>> getOrderRefundAllInit(@Query("orderNo") String orderNo);

    //全部退款提交
    @POST("api/orderRefund/all")
    Observable<BackResult> orderAllRefundSubmit(@Body OrderAllRefundRequest orderAllRefundRequest);

    //获取退款详情
    @GET("api/order/query/refund")
    Observable<BackResult<OrderRefundDetailResponse>> getOrderRefundDetail(@Query("orderRefundNo") String orderRefundNo);


    /***************    支付   *********************/
    //支付
    @GET("api/order/pay/pay")
    Observable<BackResult<OrderPaymentResponse>> orderPayment(@Query("orderNo") String orderNo, @Query("paymentFlag") String paymentFlag);

    /***************   订单评价  ********************/

    //可以评论的订单商品（单个）
    @GET("api/mchComment/mayCommentOrderGoods")
    Observable<BackResult<OrderGoodsInitResponse>> getOrderGoodsCommentInit(@Query("orderGoodsId") int orderGoodsId);

    //可以评论的订单商品（组合）
    @GET("api/mchComment/mayCommentOrder")
    Observable<BackResult<OrderGroupGoodsInitResponse>> getOrderGroupGoodsCommentInit(@Query("orderNo") String orderNo);

    //评论订单商品（单个）
    @POST("api/mchComment")
    Observable<BackResult> orderGoodsComment(@Body OrderPartialCommentRequest orderPartialCommentRequest);

    //评论订单商品（组合）
    @POST("api/mchComment")
    Observable<BackResult> orderGroupGoodsComment(@Body OrderGroupCommentRequest orderGroupCommentRequest);

    /***************   商户问题  ********************/

    //我要提问-发布问题
    @POST("api/questionAnswering")
    Observable<BackResult> questionAnswering(@Body QuestionAnsweringPublishRequest questionAnsweringPublishRequest);

    //商户界面商户问答列表
    @GET("api/mchQuestionAndAnswer")
    Observable<BackResult<WaitMyAnswerResponse>> getMchQuestionAndAnswerList(@Query("mchId") int mchId,@Query("page") int page, @Query("pageSize") int pageSize);

    //待我来答列表
    @GET("api/questionAnswering/waitMyAnswer")
    Observable<BackResult<WaitMyAnswerResponse>> getWaitMyAnswerList(@Query("page") int page, @Query("pageSize") int pageSize);

    //我的回答列表
    @GET("api/questionAnswering/myAnswer")
    Observable<BackResult<WaitMyAnswerResponse>> getMyAnswerList(@Query("page") int page, @Query("pageSize") int pageSize);

    //我的同问列表
    @GET("api/questionAnswering/userAskTogether")
    Observable<BackResult<WaitMyAnswerResponse>> getUserAskTogetherList(@Query("page") int page, @Query("pageSize") int pageSize);

    //我的提问列表
    @GET("api/questionAnswering/userQuestion")
    Observable<BackResult<WaitMyAnswerResponse>> getUserQuestionList(@Query("page") int page, @Query("pageSize") int pageSize);

}

