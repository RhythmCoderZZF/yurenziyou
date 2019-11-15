package com.nbhysj.coupon.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.nbhysj.coupon.R;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.Hashtable;

import static android.graphics.Color.BLACK;

/**
 * created by hysj on 2018/04/07.
 * description: 操作对话框
 */
public class OrderVerificationCodeDialog {
    /**
     * 上下文
     **/
    private Context context;

    /**
     * 提示提交弹框
     **/
    private Dialog dialog;

    /**
     * 内容
     */
    private TextView mTvQrOrderNo;


    /**
     * 弹框占屏幕大小尺寸
     **/
    private Display display;

    /**
     * 确定按钮
     **/
    private boolean showPosBtn = false;

    /**
     * 取消按钮
     **/
    private boolean showNegBtn = false;

    private ImageView mImgQRCodeOrder;

    /**
     * 构造器
     *
     * @param context
     */
    public OrderVerificationCodeDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    /**
     * 弹框创建
     *
     * @return
     */
    public OrderVerificationCodeDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_purchase_success, null);

        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);
        //订单二维码展示
        mImgQRCodeOrder = (ImageView) view.findViewById(R.id.img_qr_order);
        mTvQrOrderNo = view.findViewById(R.id.tv_qr_order_no);

        return this;
    }

    /**
     * 设置标题
     */
    public OrderVerificationCodeDialog setContent(String orderNo) {
        if(!TextUtils.isEmpty(orderNo))
        {
            mTvQrOrderNo.setText(orderNo);
        }
        showQRCode(orderNo);
        return this;
    }


    /**
     * 设置布局
     *
     * @param view
     * @return
     */
    public OrderVerificationCodeDialog setView(View view) {

        dialog.setContentView(view);

        return this;
    }

    /**
     * 设置是否可以点击消失
     *
     * @param cancel
     * @return
     */
    public OrderVerificationCodeDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 弹框显示
     */
    public void show() {
        // setLayout();
        dialog.show();
    }

    //show我的二维码
    public void showQRCode(String url) {
        int qrWidth = (int) (DensityUtil.getDensity(context) * 240);
        Bitmap qrCode = null;
        try {
            qrCode = createQRCode(url, qrWidth);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        mImgQRCodeOrder.setImageBitmap(qrCode);
    }

    public static Bitmap createQRCode(String url, int widthAndHeight)
            throws WriterException {
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix matrix = new MultiFormatWriter().encode(url,
                BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);

        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];
        //画黑点
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = BLACK; //0xff000000
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
