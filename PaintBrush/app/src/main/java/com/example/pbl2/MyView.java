package com.example.pbl2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

class MyPoint {
    float x, y;
    int color;
    String shape;
    MyPoint(float x, float y, int c, String s) {
        this.x = x; this.y = y; this.color = c; this.shape = s;
    }
}

public class MyView extends View {
    private static final int R =10;
    private ArrayList<MyPoint> mPoints = new ArrayList<>();
    private Paint mPaint = new Paint();
    private int mCurColor = Color.BLACK;
    private String mCurShape = "사각형";

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPaintColor(int color) {
        mCurColor = color;
    }
    public void setPaintShape(String shape) {
        mCurShape = shape;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i=0; i<mPoints.size(); i += 2) {
            MyPoint myPoint = mPoints.get(i);
            mPaint.setColor(myPoint.color);
            switch (myPoint.shape) {
                case "사각형":
                    canvas.drawRect(myPoint.x-R, myPoint.y-R, myPoint.x+R, myPoint.y+R, mPaint);
                    break;
                case "원":
                    canvas.drawCircle(myPoint.x, myPoint.y, R, mPaint);
                    break;
                case "세모":
                    Path path = new Path();
                    path.moveTo(myPoint.x, myPoint.y);
                    path.lineTo(myPoint.x-2*R, myPoint.y+3*R);
                    path.lineTo(myPoint.x+2*R, myPoint.y+3*R);
                    canvas.drawPath(path, mPaint);
                    break;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mPoints.add(new MyPoint(event.getX(), event.getY(), mCurColor, mCurShape));
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }
        return super.onTouchEvent(event);
    }
}
