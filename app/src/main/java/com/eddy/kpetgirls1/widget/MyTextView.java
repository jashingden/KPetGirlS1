package com.eddy.kpetgirls1.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by eddyteng on 2016/3/25.
 */
public class MyTextView extends TextView {

    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bmp = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
//        canvas.setDrawFilter(null);
        String text = this.getText().toString();
        Paint p = new Paint();
//        ColorMatrix cm = new ColorMatrix();
//        cm.setSaturation(0);
//        p.setColorFilter(new ColorMatrixColorFilter(cm));
        p.setTextSize(this.getTextSize());
        p.setTypeface(this.getTypeface());
        p.setColor(this.getCurrentTextColor());
        p.setTextAlign(Paint.Align.LEFT);
        Rect rect = new Rect();
        p.getTextBounds(text, 0, text.length(), rect);
        Canvas c = new Canvas(bmp);
//        Paint bp = new Paint();
//        bp.setColor(Color.YELLOW);
//        BitmapDrawable bd = new BitmapDrawable(bmp);
//        bd.setAlpha(0);
//        bd.draw(c);
//        new ColorDrawable(Color.TRANSPARENT).draw(c);
//        c.drawRect(0, 0, getWidth(), getHeight(), bp);
        c.drawText(text, 0, getHeight() / 2 + rect.height() / 2 - rect.bottom, p);
//        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(bmp, 0, 0, p);
    }
}
