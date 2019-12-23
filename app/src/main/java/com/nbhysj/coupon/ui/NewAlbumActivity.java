package com.nbhysj.coupon.ui;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.AlbumContract;
import com.nbhysj.coupon.model.AlbumModel;
import com.nbhysj.coupon.model.request.CreateFavoritesRequest;
import com.nbhysj.coupon.model.response.AlbumFavoritesDetail;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CreateFavoritesResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.presenter.AlbumPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.widget.ToggleButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/24
 * description：新建专辑
 */
public class NewAlbumActivity extends BaseActivity<AlbumPresenter, AlbumModel> implements AlbumContract.View {

    //保存
    @BindView(R.id.tv_save)
    TextView mTvSave;

    //专辑标题
    @BindView(R.id.edt_album_title)
    EditText mEdtAlbumTitle;
    //专辑简介
    @BindView(R.id.edt_album_intro)
    EditText mEdtAlbumIntro;
    //是否设置为自己可见
    @BindView(R.id.btn_toggle_set_up_as_self)
    ToggleButton mToggleBtnSetUpAsSelf;
    //是否可见
    private int isVisibleStatus;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_new_album;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeadBar(NewAlbumActivity.this, getResources().getString(R.string.str_new_albums), R.mipmap.icon_left_arrow_black, getResources().getString(R.string.str_save));
    }

    @Override
    public void initData() {

        mTvSave.setTextColor(getResources().getColor(R.color.color_text_blue2));

        mToggleBtnSetUpAsSelf.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean isOnToggle) {

                if (isOnToggle) {

                    isVisibleStatus = 1;

                } else {

                    isVisibleStatus = 0;
                }
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getFavoritesCollectionListResult(BackResult<FavoritesListResponse> res) {

    }

    @Override
    public void queryUserFavoritesResult(BackResult res) {

    }

    @Override
    public void createFavoritesResult(BackResult<CreateFavoritesResponse> res) {

        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    CreateFavoritesResponse createFavoritesResponse = res.getData();
                    if (createFavoritesResponse != null) {
                        int albumId = createFavoritesResponse.getAlbumID();
                        setResult(RESULT_OK);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(NewAlbumActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void albumFavoritesbatchMoveContentResult(BackResult res) {

    }

    @Override
    public void albumFavoritesbatchDeleteContentResult(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(NewAlbumActivity.this, Constants.getResultMsg(msg));
    }

    @Override
    public void updateFavoritesResult(BackResult res) {

    }

    @Override
    public void getAlbumFavoritesDetailResult(BackResult<AlbumFavoritesDetail> res) {

    }

    @Override
    public void getFavoritesListResult(BackResult<FavoritesListResponse> res) {

    }

    @Override
    public void delFavoritesRequest(BackResult res) {

    }

    @OnClick({R.id.tv_save})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save:

                createFavorites();

                break;
            default:
                break;
        }
    }

    public void createFavorites() {

        if (validateInternet()) {

            String albumTitle = mEdtAlbumTitle.getText().toString().trim();
            String albumIntro = mEdtAlbumIntro.getText().toString().trim();
            if (TextUtils.isEmpty(albumTitle)) {
                showToast(NewAlbumActivity.this, "请填写专辑标题");
                return;
            }

            showProgressDialog(NewAlbumActivity.this);
            CreateFavoritesRequest createFavoritesRequest = new CreateFavoritesRequest();
            createFavoritesRequest.setTitle(albumTitle);
            if (!TextUtils.isEmpty(albumIntro))
            {
                createFavoritesRequest.setIntro(albumIntro);
            }
            createFavoritesRequest.setVisibleStatus(isVisibleStatus);
            mPresenter.createFavorites(createFavoritesRequest);
        }
    }
}
