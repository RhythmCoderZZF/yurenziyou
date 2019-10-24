package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.AlbumContract;
import com.nbhysj.coupon.model.AlbumModel;
import com.nbhysj.coupon.model.request.UpdateFavoritesRequest;
import com.nbhysj.coupon.model.response.AlbumFavoritesDetail;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CreateFavoritesResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FavoritesResponse;
import com.nbhysj.coupon.presenter.AlbumPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.widget.ToggleButton;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/05/24
 * description：编辑专辑
 */
public class EditAlbumActivity extends BaseActivity<AlbumPresenter, AlbumModel> implements AlbumContract.View {
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

    //专辑id
    private int collectionId;


    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_edit_album;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeadBar(EditAlbumActivity.this, getResources().getString(R.string.str_edit_albums), R.mipmap.icon_left_arrow_black, getResources().getString(R.string.str_save));
        mTvSave.setTextColor(getResources().getColor(R.color.color_text_blue2));
        collectionId = getIntent().getIntExtra("collectionId", 0);
    }

    @Override
    public void initData()
    {

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void createFavoritesResult(BackResult<CreateFavoritesResponse> res) {

    }

    @Override
    public void getFavoritesCollectionListResult(BackResult<FavoritesListResponse> res) {

    }

    @Override
    public void updateFavoritesResult(BackResult res) {

    }

    @Override
    public void queryUserFavoritesResult(BackResult<FavoritesResponse> res) {

    }

    @Override
    public void getAlbumFavoritesDetailResult(BackResult<AlbumFavoritesDetail> res) {

    }

    @Override
    public void albumFavoritesbatchMoveContentResult(BackResult res) {

    }

    @Override
    public void albumFavoritesbatchDeleteContentResult(BackResult res) {

    }

    @Override
    public void getFavoritesListResult(BackResult<FavoritesListResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(EditAlbumActivity.this, Constants.getResultMsg(msg));
    }

    public void editAlbum() {

        if (validateInternet()) {
            showProgressDialog(EditAlbumActivity.this);

            String mAlbumTitle = mEdtAlbumTitle.getText().toString();
            String mAlbumIntro = mEdtAlbumIntro.getText().toString();
            if (TextUtils.isEmpty(mAlbumTitle)) {
                showToast(EditAlbumActivity.this, "请填写专辑标题");
                return;
            }

            if (TextUtils.isEmpty(mAlbumIntro)) {
                showToast(EditAlbumActivity.this, "请填写专辑简介");
                return;
            }

            UpdateFavoritesRequest updateFavoritesRequest = new UpdateFavoritesRequest();
            updateFavoritesRequest.setId(collectionId);
            updateFavoritesRequest.setTitle(mAlbumTitle);
            updateFavoritesRequest.setIntro(mAlbumIntro);
            updateFavoritesRequest.setVisibleStatus(isVisibleStatus);
            mPresenter.updateFavorites(updateFavoritesRequest);
        }
    }

    @OnClick({R.id.tv_album_delete,R.id.tv_save})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_album_delete:


                break;
            case R.id.tv_save:

                editAlbum();

                break;
            default:
                break;
        }
    }
}
