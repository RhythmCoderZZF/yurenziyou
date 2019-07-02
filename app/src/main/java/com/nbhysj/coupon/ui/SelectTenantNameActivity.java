package com.nbhysj.coupon.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
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
 * description：选择住客
 */
public class SelectTenantNameActivity extends BaseActivity {

    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.flowlayout_frequent_passengers_label)
    TagFlowLayout mTagFlowLayoutFrequentPassengersLabel;

    @Override
    public int getLayoutId() {

        return R.layout.activity_select_tenant_name;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeadBar(SelectTenantNameActivity.this, getResources().getString(R.string.str_tenant_name), R.mipmap.icon_left_arrow_black, "");
    }

    @Override
    public void initData() {
        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }

        List<String> fineFoodTagList = new ArrayList<>();
        fineFoodTagList.add("张三");
        fineFoodTagList.add("李四");
        fineFoodTagList.add("赵五六");
        fineFoodTagList.add("Monkey D. Luffy");
        fineFoodTagList.add("钱多多");
        TagAdapter tagAdapter = new TagAdapter<String>(fineFoodTagList) {
            @Override
            public View getView(FlowLayout parent, int position, String option) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.layout_white_flowlayout_tag_stroke_gray_frame,
                        mTagFlowLayoutFrequentPassengersLabel, false);
                TextView tv = view.findViewById(R.id.tv_flowlayout);
                tv.setText(option);

                return view;
            }
        };
        mTagFlowLayoutFrequentPassengersLabel.setMaxSelectCount(1);
        mTagFlowLayoutFrequentPassengersLabel.setAdapter(tagAdapter);

        mTagFlowLayoutFrequentPassengersLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String content = "";
                Set<Integer> selectPosSet = mTagFlowLayoutFrequentPassengersLabel.getSelectedList();
                Iterator it = selectPosSet.iterator();
                while (it.hasNext()) {
                    int index = (int) it.next();
                    //content = options[index];
                    // MoveToPosition(layoutManager,index);


                }
                return true;
            }
        });

    }

    @Override
    public void initPresenter() {

    }
}
