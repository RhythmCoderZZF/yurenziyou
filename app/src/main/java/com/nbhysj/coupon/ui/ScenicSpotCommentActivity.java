package com.nbhysj.coupon.ui;


import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ScenicSpotDetailUserCommentAdapter;
import com.nbhysj.coupon.model.response.ScenicSpotsUserCommentResponse;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/05/09
 * description：景区评论
 */
public class ScenicSpotCommentActivity extends BaseActivity {

    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.flowlayout_comment)
    TagFlowLayout mTagFlowComment;
    @BindView(R.id.rv_user_comment)
    RecyclerView mRvUserComment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scenic_spot_comment;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }
        ToolbarHelper.setHeadBar(ScenicSpotCommentActivity.this, "评论", R.mipmap.icon_left_arrow_black, "");
    }

    @Override
    public void initData() {
        List<String> fineFoodTagList = new ArrayList<>();
        fineFoodTagList.add("全部");
        fineFoodTagList.add("家庭亲子77");
        fineFoodTagList.add("价格高19");
        fineFoodTagList.add("人气旺19");
        fineFoodTagList.add("性价比低77");
        fineFoodTagList.add("地方赞19");
        fineFoodTagList.add("设施完善19");
        TagAdapter tagAdapter = new TagAdapter<String>(fineFoodTagList) {
            @Override
            public View getView(FlowLayout parent, int position, String option) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_comment,
                        mTagFlowComment, false);
                TextView tv = view.findViewById(R.id.tv_flowlayout);
                tv.setText(option);

                return view;
            }
        };
        mTagFlowComment.setMaxSelectCount(1);
        mTagFlowComment.setAdapter(tagAdapter);


        mTagFlowComment.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String content = "";
                Set<Integer> selectPosSet = mTagFlowComment.getSelectedList();
                Iterator it = selectPosSet.iterator();
                while (it.hasNext()) {
                    int index = (int) it.next();
                    //content = options[index];
                    // MoveToPosition(layoutManager,index);


                }
                return true;
            }
        });
        tagAdapter.setSelectedList(0);


        List<ScenicSpotsUserCommentResponse> spotsUserCommentResponseList = new ArrayList<>();
        List<String> userCommentPhotoList = new ArrayList<>();
        userCommentPhotoList.add("http://img5.imgtn.bdimg.com/it/u=3300305952,1328708913&fm=26&gp=0.jpg");
        userCommentPhotoList.add("http://d.hiphotos.baidu.com/lvpics/w=1000/sign=e2347e78217f9e2f703519082f00eb24/730e0cf3d7ca7bcb49f90bb1b8096b63f724a8aa.jpg");
        userCommentPhotoList.add("http://i0.hexunimg.cn/2011-08-18/132587770.jpg");
        userCommentPhotoList.add("http://pic34.nipic.com/20131102/11840161_135433676377_2.jpg");
        userCommentPhotoList.add("http://i2.hexunimg.cn/2015-11-16/180589438.jpg");
        userCommentPhotoList.add("http://photocdn.sohu.com/20120216/Img334903378.jpg");
        ScenicSpotsUserCommentResponse userCommentResponse = new ScenicSpotsUserCommentResponse();
        userCommentResponse.setId(1);
        userCommentResponse.setUsername("飞飞飞");
        userCommentResponse.setCommentPublishTime("2019-04-29");
        userCommentResponse.setContent("第一次去海洋馆真的超级幸运,先去看的是剧场表扬 互动环节的时候很幸运被抽中和白鲸接触来了一个深海之吻超级感动,后面又到水族参观,最喜欢的是哒哒哒哒哒哒多多");
        userCommentResponse.setStarLevel(3);
        userCommentResponse.setUserAvatarPhoto("http://pic9.nipic.com/20100901/4753218_163400058451_2.jpg");
        userCommentResponse.setUserCommentPhotoList(userCommentPhotoList);

        List<String> userCommentPhotoList1 = new ArrayList<>();
        userCommentPhotoList1.add("http://pic34.nipic.com/20131102/11840161_135433676377_2.jpg");
        userCommentPhotoList1.add("http://i2.hexunimg.cn/2015-11-16/180589438.jpg");
        userCommentPhotoList1.add("http://photocdn.sohu.com/20120216/Img334903378.jpg");
        userCommentPhotoList1.add("http://img5.imgtn.bdimg.com/it/u=3300305952,1328708913&fm=26&gp=0.jpg");
        userCommentPhotoList1.add("http://d.hiphotos.baidu.com/lvpics/w=1000/sign=e2347e78217f9e2f703519082f00eb24/730e0cf3d7ca7bcb49f90bb1b8096b63f724a8aa.jpg");
        userCommentPhotoList1.add("http://i0.hexunimg.cn/2011-08-18/132587770.jpg");
        ScenicSpotsUserCommentResponse userCommentResponse1 = new ScenicSpotsUserCommentResponse();
        userCommentResponse1.setId(2);
        userCommentResponse1.setUsername("哒哒滴");
        userCommentResponse1.setCommentPublishTime("2019-04-23");
        userCommentResponse1.setContent("第一次去海洋馆真的超级幸运,先去看的是剧场表扬 互动环节的时候很幸运被抽中和白鲸接触来了一个深海之吻超级感动,后面又到水族参观,最喜欢的是哒哒哒哒哒哒多多");
        userCommentResponse1.setStarLevel(4);
        userCommentResponse1.setUserAvatarPhoto("https://b-ssl.duitang.com/uploads/item/201512/08/20151208130909_SiFP5.jpeg");
        userCommentResponse1.setUserCommentPhotoList(userCommentPhotoList1);
        spotsUserCommentResponseList.add(userCommentResponse);
        spotsUserCommentResponseList.add(userCommentResponse1);

        List<String> userCommentPhotoList2 = new ArrayList<>();
        userCommentPhotoList2.add("http://pic34.nipic.com/20131102/11840161_135433676377_2.jpg");
        userCommentPhotoList2.add("http://i2.hexunimg.cn/2015-11-16/180589438.jpg");
        userCommentPhotoList2.add("http://photocdn.sohu.com/20120216/Img334903378.jpg");
        userCommentPhotoList2.add("http://img5.imgtn.bdimg.com/it/u=3300305952,1328708913&fm=26&gp=0.jpg");
        userCommentPhotoList2.add("http://d.hiphotos.baidu.com/lvpics/w=1000/sign=e2347e78217f9e2f703519082f00eb24/730e0cf3d7ca7bcb49f90bb1b8096b63f724a8aa.jpg");
        userCommentPhotoList2.add("http://i0.hexunimg.cn/2011-08-18/132587770.jpg");
        ScenicSpotsUserCommentResponse userCommentResponse2 = new ScenicSpotsUserCommentResponse();
        userCommentResponse2.setId(3);
        userCommentResponse2.setUsername("哒哒滴");
        userCommentResponse2.setCommentPublishTime("2019-04-23");
        userCommentResponse2.setContent("第一次去海洋馆真的超级幸运,先去看的是剧场表扬 互动环节的时候很幸运被抽中和白鲸接触来了一个深海之吻超级感动,后面又到水族参观,最喜欢的是哒哒哒哒哒哒多多");
        userCommentResponse2.setStarLevel(4);
        userCommentResponse2.setUserAvatarPhoto("https://b-ssl.duitang.com/uploads/item/201512/08/20151208130909_SiFP5.jpeg");
        userCommentResponse2.setUserCommentPhotoList(userCommentPhotoList1);
        spotsUserCommentResponseList.add(userCommentResponse);
        spotsUserCommentResponseList.add(userCommentResponse1);
        spotsUserCommentResponseList.add(userCommentResponse2);

        LinearLayoutManager userCommentLayoutManager = new LinearLayoutManager(ScenicSpotCommentActivity.this);
        userCommentLayoutManager.setOrientation(userCommentLayoutManager.VERTICAL);
        mRvUserComment.setLayoutManager(userCommentLayoutManager);
        ScenicSpotDetailUserCommentAdapter scenicSpotDetailUserCommentAdapter = new ScenicSpotDetailUserCommentAdapter(ScenicSpotCommentActivity.this);
        // scenicSpotDetailUserCommentAdapter.setScenicSpotsUserCommentList(spotsUserCommentResponseList);
        //mRvUserComment.setAdapter(scenicSpotDetailUserCommentAdapter);
    }

    @Override
    public void initPresenter() {

    }
}
