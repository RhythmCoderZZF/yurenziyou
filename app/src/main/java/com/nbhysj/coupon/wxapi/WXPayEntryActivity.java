package com.nbhysj.coupon.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.nbhysj.coupon.pay.wechat.PayConstants;
import com.nbhysj.coupon.ui.MyOrderActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, PayConstants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("", "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            // 根据返回码
            int code = resp.errCode;
            Intent intent = new Intent();
            switch (code) {
                case 0:
                    // 去本地确认支付结果
                   // EventBus.getDefault().post("0");
                    finish();

                    intent.setClass(WXPayEntryActivity.this, MyOrderActivity.class);
                    startActivity(intent);
                    break;
                case -1:
                    Toast.makeText(this, "支付异常", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case -2:
                    Toast.makeText(this, "支付已取消", Toast.LENGTH_SHORT).show();
                    intent.setClass(WXPayEntryActivity.this, MyOrderActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }
    }
}