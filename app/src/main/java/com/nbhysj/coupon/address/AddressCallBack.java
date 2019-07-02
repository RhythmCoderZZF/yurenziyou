package com.nbhysj.coupon.address;

import com.nbhysj.coupon.model.response.DistrictBean;
import com.nbhysj.coupon.model.response.ProvinceBean;
import com.nbhysj.coupon.model.response.RecipientAddressResponse;
import com.nbhysj.coupon.model.response.RecipientsCityBean;

/**
 * @auther：hysj created at 2019/03/20
 * description：地址选择回调
 */
public interface AddressCallBack {
    public void selectProvince(int position, RecipientAddressResponse recipientAddressResponse);

    public void selectCity(int position, RecipientsCityBean city);

    public void selectDistrict(DistrictBean district);
}
