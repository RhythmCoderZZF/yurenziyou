package com.nbhysj.coupon.widget.calendar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nbhysj.coupon.util.DateUtil;

public class MyItemD extends RecyclerView.ItemDecoration {
    Paint paint = new Paint();
    Paint colorPaint = new Paint();
    Paint linePaint = new Paint();

    public MyItemD() {
        paint.setColor(Color.parseColor("#FFF7F7F7"));
        paint.setStyle(Paint.Style.FILL);
        colorPaint.setColor(Color.parseColor("#FF323332"));
        colorPaint.setAntiAlias(true);
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.parseColor("#dddddd"));
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
      try {


        if (parent.getChildCount() <= 0) {
            return;
        }

        //头部的高度
        int height = 0;
        final float scale = parent.getContext().getResources().getDisplayMetrics().density;
        height = (int) (height * scale + 0.5f);

        //获取第一个可见的view，通过此view获取其对应的月份
        CalendarList.CalendarAdapter a = (CalendarList.CalendarAdapter) parent.getAdapter();
        View fistView = parent.getChildAt(0);
        String text = a.data.get(parent.getChildAdapterPosition(fistView)).getMonthStr();

        String fistMonthStr = "";
        int fistViewTop = 0;
        //查找当前可见的itemView中第一个月份类型的item
        for (int i = 0; i < parent.getChildCount(); i++) {
            View v = parent.getChildAt(i);
            if (2 == parent.getChildViewHolder(v).getItemViewType()) {
                fistMonthStr = a.data.get(parent.getChildAdapterPosition(v)).getMonthStr();
                fistViewTop = v.getTop();
                break;
            }
        }

        //计算偏移量
        int topOffset = 0;
        if (!fistMonthStr.equals(text) && fistViewTop < height) {
            //前提是第一个可见的月份item不是当前显示的月份和距离的顶部的距离小于头部的高度
            topOffset = height - fistViewTop;
        }
        int t = 0 - topOffset;

        //绘制头部区域
        c.drawRect(parent.getLeft(), t, parent.getRight(), t + height, paint);

        colorPaint.setTextAlign(Paint.Align.CENTER);
        colorPaint.setTextSize(15 * scale + 0.5f);
        //绘制头部月份文字

        String mmddStr = DateUtil.toMMDD(text);
        c.drawText(mmddStr, parent.getRight() / 2, (t + height) / 1.5f, colorPaint);
      }catch (Exception e){
          e.printStackTrace();
      }
        //绘制分割线
//        if(fistViewTop!=height) {
//            linePaint.setStrokeWidth(scale * 0.5f + 0.5f);
//            c.drawLine(parent.getLeft(), t + height, parent.getRight(), t + height, linePaint);
//        }

    }


}
