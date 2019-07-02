package com.nbhysj.coupon.fragment;

import android.content.Context;
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
import com.nbhysj.coupon.model.response.ProvinceBean;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzq on 16/9/26.
 */
public class ProvinceFragment implements AdapterView.OnItemClickListener {
    private AddressCallBack callBack;
    private String code;
    private Context context;
    private AddressAdapter adapter;
    private ListView listview;
    private List<ProvinceBean> provinceList;
    private List<RecipientAddressResponse> recipientAddressResponseList;

    public ProvinceFragment(Context context, AddressCallBack callBack, List<RecipientAddressResponse> recipientAddressResponseList) {
        this.context = context;
        this.callBack = callBack;
        this.recipientAddressResponseList = recipientAddressResponseList;
        initView();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ListView getListview() {
        return listview;
    }

    public View initView() {
        if (provinceList == null) {

            provinceList = new ArrayList<>();
        } else {
            provinceList.clear();
        }
        listview = (ListView) LayoutInflater.from(context).inflate(R.layout.list_select_address_pop, null);
        adapter = new AddressAdapter(recipientAddressResponseList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
        return listview;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        RecipientAddressResponse recipientAddressResponse = recipientAddressResponseList.get(i);
        code = recipientAddressResponse.getId();
        if (callBack != null) {
            callBack.selectProvince(i, recipientAddressResponse);
        }
        adapter.notifyDataSetChanged();
    }

    class AddressAdapter extends BaseAdapter {

        private List<RecipientAddressResponse> list;

        public AddressAdapter(List<RecipientAddressResponse> list) {
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
            TextView mTvAlphabet = (TextView) view.findViewById(R.id.tv_alphabet);
            text.setText(list.get(i).getName());
            String letter = list.get(i).getLetter();
            mTvAlphabet.setText(letter);
            if (list.get(i).getId().equals(code)) {
                text.setTextColor(context.getResources().getColor(R.color.color_text_blue2));
                //ivSelect.setVisibility(View.VISIBLE);
            }

            return view;
        }
    }
}
