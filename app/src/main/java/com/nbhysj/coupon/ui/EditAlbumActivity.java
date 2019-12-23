package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.AlbumContract;
import com.nbhysj.coupon.model.AlbumModel;
import com.nbhysj.coupon.model.request.FavoritesDeleteRequest;
import com.nbhysj.coupon.model.request.UpdateFavoritesRequest;
import com.nbhysj.coupon.model.response.AlbumFavoritesDetail;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.CarH5UrlResponse;
import com.nbhysj.coupon.model.response.CreateFavoritesResponse;
import com.nbhysj.coupon.model.response.FavoritesBean;
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

    //专辑标题
    String mAlbumTitle,mAlbumIntro;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_edit_album;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeadBar(EditAlbumActivity.this, getResources().getString(R.string.str_edit_albums), R.mipmap.icon_left_arrow_black, getResources().getString(R.string.str_save));
        mTvSave.setTextColor(getResources().getColor(R.color.color_text_blue2));
        FavoritesBean favoritesBean = (FavoritesBean)getIntent().getSerializableExtra("favoritesBean");
        if(favoritesBean != null)
        {
            collectionId = favoritesBean.getId();
            String title = favoritesBean.getTitle();
            String intro = favoritesBean.getIntro();
            isVisibleStatus = favoritesBean.getVisibleStatus();
            if(!TextUtils.isEmpty(title))
            {
                mEdtAlbumTitle.setText(title);
            }

            if(!TextUtils.isEmpty(intro))
            {
                mEdtAlbumIntro.setText(intro);
            }

            if(isVisibleStatus == 1)
            {
                mToggleBtnSetUpAsSelf.setToggleOn();
            } else if(isVisibleStatus == 0)
            {
                mToggleBtnSetUpAsSelf.setToggleOff();
            }
        }
    }

    @Override
    public void initData()
    {

        mToggleBtnSetUpAsSelf.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean isOnToggleChanged) {

                if(isOnToggleChanged){

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
    public void createFavoritesResult(BackResult<CreateFavoritesResponse> res) {

    }

    @Override
    public void getFavoritesCollectionListResult(BackResult<FavoritesListResponse> res) {

    }

    @Override
    public void updateFavoritesResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    Intent intent = new Intent();
                    intent.putExtra("albumTitle",mAlbumTitle);
                    intent.putExtra("albumIntro",mAlbumIntro);
                    intent.putExtra("albumOprate",0);
                    setResult(RESULT_OK,intent);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(EditAlbumActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
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


            //专辑标题
            mAlbumTitle = mEdtAlbumTitle.getText().toString();

            //专辑简介
            mAlbumIntro = mEdtAlbumIntro.getText().toString();
            if (TextUtils.isEmpty(mAlbumTitle)) {
                showToast(EditAlbumActivity.this, "请填写专辑标题");
                return;
            }

            showProgressDialog(EditAlbumActivity.this);
            UpdateFavoritesRequest updateFavoritesRequest = new UpdateFavoritesRequest();
            updateFavoritesRequest.setId(collectionId);
            updateFavoritesRequest.setTitle(mAlbumTitle);
            if(!TextUtils.isEmpty(mAlbumIntro)) {
                updateFavoritesRequest.setIntro(mAlbumIntro);
            }
            updateFavoritesRequest.setVisibleStatus(isVisibleStatus);
            mPresenter.updateFavorites(updateFavoritesRequest);
        }
    }

    @Override
    public void delFavoritesRequest(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    Intent intent = new Intent();
                    intent.putExtra("albumOprate",1);
                    setResult(RESULT_OK,intent);
                    setResult(RESULT_OK);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(EditAlbumActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    //删除专辑
    public void deleteFavorites()
    {
        if(validateInternet())
        {
            FavoritesDeleteRequest favoritesDeleteRequest = new FavoritesDeleteRequest();
            favoritesDeleteRequest.setId(collectionId);
            mPresenter.delFavoritesRequest(favoritesDeleteRequest);
        }
    }

    @OnClick({R.id.tv_album_delete,R.id.tv_save})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_album_delete:

                deleteFavorites();

                break;
            case R.id.tv_save:

                editAlbum();

                break;
            default:
                break;
        }
    }
}
