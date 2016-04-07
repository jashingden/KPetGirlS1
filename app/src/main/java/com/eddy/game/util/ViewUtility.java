package com.eddy.game.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by eddyteng on 2016/4/7.
 */
public class ViewUtility {

    public static int getDimensionPixelSize(Context context, int size)
    {
        return getDimensionPixelSize(context, TypedValue.COMPLEX_UNIT_DIP, size);
    }

    public static int getDimensionPixelSize(Context context, int unit, int size)
    {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return (int)TypedValue.applyDimension(unit, size, dm);
    }

}
