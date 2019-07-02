package com.nbhysj.coupon.fragment;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.address.AddressCallBack;
import com.nbhysj.coupon.model.response.DistrictBean;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;

import java.util.ArrayList;
import java.util.List;


public class DistrictFragment implements AdapterView.OnItemClickListener {
    private AddressCallBack callBack;
    private String code;
    private Context context;
    private AddressAdapter adapter;
    private ListView listview;
    private String provinceCode;
    private String cityCode;
    private List<DistrictBean> districtsList = new ArrayList<DistrictBean>();
    private List<RecipientAddressResponse> recipientAddressCityList;

    public DistrictFragment(Context context, AddressCallBack callBack, List<RecipientAddressResponse> recipientAddressResponseList) {
        this.context = context;
        this.callBack = callBack;
        this.recipientAddressCityList = recipientAddressResponseList;
        initView();
    }

    public void setCode(int mProvinceSelectPosition, int mCitySelectPosition, List<RecipientAddressResponse> recipientAddressResponseList) {
        //recipientsCityList.clear();
        districtsList = recipientAddressResponseList.get(mProvinceSelectPosition).getCityVOs().get(mCitySelectPosition).getCities();
        adapter.setRegionList(districtsList);
        adapter.notifyDataSetChanged();

    }

    public ListView getListview() {
        return listview;
    }

    public View initView() {
        listview = (ListView) LayoutInflater.from(context).inflate(R.layout.list_select_address_pop, null);
        districtsList = recipientAddressCityList.get(0).getCityVOs().get(0).getCities();
        adapter = new AddressAdapter(districtsList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
        return listview;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        code = districtsList.get(i).getId();
        if (callBack != null) {
            callBack.selectDistrict(districtsList.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    class AddressAdapter extends BaseAdapter {

        private List<DistrictBean> list;

        public AddressAdapter(List<DistrictBean> list) {
            this.list = list;
        }

        public void setRegionList(List<DistrictBean> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(context).inflate(R.layout.address_listiew_item_textview, null);
            TextView text = (TextView) view.findViewById(R.id.tvTextName);
            ImageView ivSelect = (ImageView) view.findViewById(R.id.ivSelect);
            text.setText(list.get(i).getName());
            if (list.get(i).getId().equals(code)) {
                text.setTextColor(context.getResources().getColor(R.color.color_text_blue2));
                ivSelect.setVisibility(View.VISIBLE);
            }
            return view;
        }
    }
}
