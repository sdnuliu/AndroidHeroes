package com.yishengxu.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Clock extends View {

    private int mHeight, mWidth;

    public Clock(Context context) {
        super(context);
        // 获取宽高参数
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    public Clock(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Clock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawCircle(canvas);
        drawDegree(canvas);
        drawPointer(canvas);
        drawTime(canvas);
    }

    /**
     * 画指针
     *
     * @param canvas 画布
     */
    private void drawTime(Canvas canvas) {
        // 画指针
        Paint paintHour = new Paint();
        paintHour.setStrokeWidth(20);
        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(0, 0, 100, 100, paintHour);
        canvas.drawLine(0, 0, 100, 200, paintMinute);
        canvas.restore();
    }

    /**
     * 画圆心
     *
     * @param canvas 画布
     */
    private void drawPointer(Canvas canvas) {
        // 画圆心
        Paint paintPointer = new Paint();
        paintPointer.setStrokeWidth(30);
        canvas.drawPoint(mWidth / 2, mHeight / 2, paintPointer);
    }

    /**
     * 画刻度
     *
     * @param canvas 画布
     */
    private void drawDegree(Canvas canvas) {
        // 画刻度
        Paint painDegree = new Paint();
        for (int i = 0; i < 24; i++) {
            // 区分整点与非整点
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                painDegree.setStrokeWidth(5);
                painDegree.setTextSize(30);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + 60,
                        painDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,
                        mWidth / 2 - painDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 90,
                        painDegree);
            } else {
                painDegree.setStrokeWidth(3);
                painDegree.setTextSize(15);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + 30,
                        painDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,
                        mWidth / 2 - painDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 60,
                        painDegree);
            }
            // 通过旋转画布简化坐标运算
            canvas.rotate(15, mWidth / 2, mHeight / 2);
        }
    }

    /**
     * 画外圆
     *
     * @param canvas 画布
     */
    private void drawCircle(Canvas canvas) {
        // 画外圆
        Paint paintCircle = new Paint();
        //仅描边
        paintCircle.setStyle(Paint.Style.STROKE);
        //抗锯齿
        paintCircle.setAntiAlias(true);
        //边的宽度
        paintCircle.setStrokeWidth(5);
        //坐标点mWidth/2,mHeight/2,半径mWidth/2
        canvas.drawCircle(mWidth / 2,
                mHeight / 2, mWidth / 2, paintCircle);
    }
}
