package com.android.launcher.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mr.kong on 2017/11/8.
 */

public class TestView extends View {

    public Paint paint;

    private float sx = -1, sy = -1;
    private float tx = -1, ty = 0;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        if (event.getActionMasked() != MotionEvent.ACTION_HOVER_MOVE)
            dumpEvent(event);
        if (event.getAction() == MotionEvent.ACTION_HOVER_EXIT) {
            sx = -1f;
        } else {
            sx = event.getX();
            sy = event.getY();
        }
        invalidate();
        return super.dispatchGenericMotionEvent(event);

    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        dumpEvent(event);
        return super.onGenericMotionEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            tx = -1f;
        } else {
            tx = event.getX();
            ty = event.getY();
        }
        invalidate();
        return super.onTouchEvent(event);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        if (sx == -1f) {
            if (tx > 0) {
                canvas.drawCircle(tx, ty, 15f, paint);
            }
            return;
        }
        canvas.drawPoint(sx, sy, paint);
    }

    private void dumpEvent(MotionEvent event) {
        String[] names = {"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "ACTION_HOVER_MOVE", "8?", "ACTION_HOVER_ENTER", "ACTION_HOVER_EXIT"};
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int i = action >> MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[i]);
        if (i == MotionEvent.ACTION_POINTER_DOWN || i == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(
                    action << MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }
        sb.append("[");
        for (int j = 0; j < event.getPointerCount(); j++) {
            sb.append("#").append(j);
            sb.append("(pid ").append(event.getPointerId(j));
            sb.append(")=").append(event.getX(j));
            sb.append(",").append(event.getY(j));
            if (j + 1 < event.getPointerCount())
                sb.append(";");
        }


        sb.append("]");
        Log.e("HoverTest", sb.toString());
    }
}
