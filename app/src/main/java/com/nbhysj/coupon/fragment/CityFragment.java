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
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.model.response.RecipientsCityBean;

import java.util.ArrayList;
import java.util.List;


public class CityFragment implements AdapterView.OnItemClickListener {
    private AddressCallBack callBack;
    private String code;
    private Context context;
    private AddressAdapter adapter;
    private ListView listview;
    private String provinceCode;
    private List<RecipientsCityBean> recipientsCityList = new ArrayList<RecipientsCityBean>();
    private List<RecipientAddressResponse> recipientAddressCityList;
    private int mPosition;

    public CityFragment(Context context, AddressCallBack callBack, List<RecipientAddressResponse> recipientAddressResponseList) {
        this.context = context;
        this.callBack = callBack;
        this.recipientAddressCityList = recipientAddressResponseList;
        initView();
    }

    public void setCode(int mPosition, List<RecipientAddressResponse> recipientAddressResponseList) {
        //recipientsCityList.clear();
        recipientsCityList = recipientAddressResponseList.get(mPosition).getCityVOs();
        adapter.setCityAddressList(recipientsCityList);
        adapter.notifyDataSetChanged();
    }

    public ListView getListview() {
        return listview;
    }

    public View initView() {
        listview = (ListView) LayoutInflater.from(context).inflate(R.layout.list_select_address_pop, null);
        //cityList = AddressManager.newInstance().findProvinceByCode(provinceCode).getAllCities();
        recipientsCityList = recipientAddressCityList.get(mPosition).getCityVOs();
        adapter = new AddressAdapter(recipientsCityList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
        return listview;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        code = String.valueOf(recipientsCityList.get(i).getId());
        if (callBack != null) {
            callBack.selectCity(i, recipientsCityList.get(i));
        }
        adapter.notifyDataSetChanged();
    }

    class AddressAdapter extends BaseAdapter {

        private List<RecipientsCityBean> list;

        public AddressAdapter(List<RecipientsCityBean> list) {
            this.list = list;
        }

        public void setCityAddressList(List<RecipientsCityBean> list) {
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
            if (String.valueOf(list.get(i).getId()).equals(code)) {
                text.setTextColor(context.getResources().getColor(R.color.color_text_blue2));
                ivSelect.setVisibility(View.VISIBLE);
            }
            return view;
        }
    }
}
