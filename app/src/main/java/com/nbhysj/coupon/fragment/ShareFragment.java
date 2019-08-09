package com.nbhysj.coupon.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FollowListAdapter;
import com.nbhysj.coupon.adapter.ShareAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.model.HomePageModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BannerUrlBO;
import com.nbhysj.coupon.model.response.FollowDetailBean;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.model.response.ShareResponse;
import com.nbhysj.coupon.presenter.HomePagePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/04/14
 * description：分享
 */
public class ShareFragment extends BaseFragment<HomePagePresenter, HomePageModel> implements HomePageContract.View {

    @BindView(R.id.rv_share)
    RecyclerView mRvShare;

    private List<ShareResponse> shareList;
    private ShareAdapter shareAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_share;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView(View v) {

        //getHomeAttention();
        if (shareList == null) {

            shareList = new ArrayList<>();
        } else {
            shareList.clear();
        }

        ShareResponse share = new ShareResponse();
        List<ShareResponse.ShareEntity> shareEntityList = new ArrayList<>();

        ShareResponse.ShareEntity shareResponse = new ShareResponse().new ShareEntity();
        ShareResponse.ShareEntity shareResponse1 = new ShareResponse().new ShareEntity();
        ShareResponse.ShareEntity shareResponse2 = new ShareResponse().new ShareEntity();
        List<String> image = new ArrayList<>();
        image.add("https://img5.duitang.com/uploads/item/201410/05/20141005190442_nuceP.thumb.700_0.jpeg");
        image.add("https://t1.hddhhn.com/uploads/tu/201611/228/st87.png");
        List<String> image1 = new ArrayList<>();
        image1.add("http://gss0.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/lvpics/w=1000/sign=a669f57d3a12b31bc76cc929b628377a/503d269759ee3d6d801feef140166d224f4ade2b.jpg");
        List<String> image2 = new ArrayList<>();
        image2.add("https://t1.hddhhn.com/uploads/tu/201611/228/st87.png");
        image2.add("https://t1.hddhhn.com/uploads/tu/201611/228/st87.png");
        image2.add("https://t1.hddhhn.com/uploads/tu/201611/228/st87.png");
        image2.add("https://t1.hddhhn.com/uploads/tu/201611/228/st87.png");
        image2.add("https://t1.hddhhn.com/uploads/tu/201611/228/st87.png");
        shareResponse.setTitle("四明湖红杉林");
        shareResponse.setContent("宁波沙发啥双方都委曲求全");
        shareResponse.setImageList(image);

        shareResponse1.setTitle("四明湖红杉林1");
        shareResponse1.setContent("宁波沙发啥双方都委曲求全1");
        shareResponse1.setImageList(image1);

        shareResponse2.setTitle("四明湖红杉林2");
        shareResponse2.setContent("宁波沙发啥双方都委曲求全2");
        shareResponse2.setImageList(image2);

        shareEntityList.add(shareResponse);
        shareEntityList.add(shareResponse1);
        shareEntityList.add(shareResponse2);

        share.setShareList(shareEntityList);
        share.setTime(121213131L);


        shareList.add(share);

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 设置布局管理器
        mRvShare.setLayoutManager(layoutManager);

        shareAdapter = new ShareAdapter(getActivity());
        shareAdapter.setShareList(shareList);
        mRvShare.setAdapter(shareAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void getHomePageIndexResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void queryByTopicResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void getPostInfoResult(BackResult<PostInfoDetailResponse> res) {

    }

    @Override
    public void postOprateResult(BackResult res) {

    }

    @Override
    public void getHomeAttentionResult(BackResult<HomePageResponse> res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    System.out.print(res);

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
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    public void getHomeAttention() {

        if (validateInternet()) {

            mPresenter.getHomeAttention(1, 10);
        }
    }

    @Override
    public void lazyInitView(View view) {

    }
}
