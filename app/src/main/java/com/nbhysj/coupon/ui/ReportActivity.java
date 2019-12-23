package com.nbhysj.coupon.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.ReportReasonSelectAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.ReportContract;
import com.nbhysj.coupon.model.ReportModel;
import com.nbhysj.coupon.model.request.PostReportRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.presenter.ReportPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/11/25
 * description：举报
 */
public class ReportActivity extends BaseActivity<ReportPresenter, ReportModel> implements ReportContract.View {

    @BindView(R.id.rv_report_reason_select)
    RecyclerView mRvReportReasonSelect;

    //举报原因选择
    private List<String> mReportReasonSelectList;

    private String reportReason;

    //举报标识
    private int reportFlag;

    //帖子id
    private int postId;

    //用户id
    private int userId;
    private ReportReasonSelectAdapter reportReasonSelectAdapter;

    //举报请求code
    private int REPORT_REQUEST_CODE = 0;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_report;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(ReportActivity.this,getResources().getString(R.string.str_report),R.mipmap.icon_left_arrow_black);

        reportFlag = getIntent().getIntExtra("reportFlag",0);
        postId = getIntent().getIntExtra("postsId",0);
        userId = getIntent().getIntExtra("userId",0);

        if (mReportReasonSelectList == null) {

            mReportReasonSelectList = new ArrayList<>();
        } else {

            mReportReasonSelectList.clear();
        }

        mReportReasonSelectList.add("色情低俗");
        mReportReasonSelectList.add("政治敏感");
        mReportReasonSelectList.add("违法犯罪");
        mReportReasonSelectList.add("造谣传谣");
        mReportReasonSelectList.add("冒充官方");
        mReportReasonSelectList.add("侵权盗用");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ReportActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvReportReasonSelect.setLayoutManager(linearLayoutManager);

        reportReasonSelectAdapter = new ReportReasonSelectAdapter(new ReportReasonSelectAdapter.ReportReasonSelectListener() {
            @Override
            public void setReportReasonSelectListener(String reason) {

                reportReason = reason;
            }
        });
        reportReasonSelectAdapter.setRefundReasonList(mReportReasonSelectList);
        reportReasonSelectAdapter.setOtherReasonSelect(false);
        mRvReportReasonSelect.setAdapter(reportReasonSelectAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void postReportResult(BackResult res) {
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    showToast(ReportActivity.this,"举报成功");
                    ReportActivity.this.finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                dismissProgressDialog();
                showToast(ReportActivity.this, Constants.getResultMsg(res.getMsg()));
        }
        }

    @Override
    public void userReportResult(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(ReportActivity.this, Constants.getResultMsg(msg));
    }

    @OnClick({R.id.tv_report_submit,R.id.rlyt_other})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_report_submit:

                postReport();

                break;
            case R.id.rlyt_other:


                Intent intent = new Intent();
                intent.setClass(ReportActivity.this,ReportOtherActivity.class);
                intent.putExtra("reportFlag",reportFlag);
                intent.putExtra("postId",postId);
                intent.putExtra("userId",userId);
                startActivityForResult(intent,REPORT_REQUEST_CODE);

                reportReason = null;
                reportReasonSelectAdapter.setRefundReasonList(mReportReasonSelectList);
                reportReasonSelectAdapter.setOtherReasonSelect(true);
                reportReasonSelectAdapter.notifyDataSetChanged();


                break;
                default:break;
        }
    }

    public void postReport(){

        if(validateInternet()){

            if(TextUtils.isEmpty(reportReason)){

                showToast(ReportActivity.this,"请选择举报理由");
                return;
            }
            PostReportRequest postReportRequest = new PostReportRequest();
            postReportRequest.setPostsId(postId);
            postReportRequest.setReason(reportReason);
            mPresenter.postReport(postReportRequest);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REPORT_REQUEST_CODE && resultCode == RESULT_OK){

            ReportActivity.this.finish();
        }
    }
}
