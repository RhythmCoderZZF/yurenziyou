package com.nbhysj.coupon.widget.shadowlib;

class Check {
    static void ifNull(Object o) {
        if (o == null) {
            throw new RuntimeException("can not be null !");
        }
    }
}
