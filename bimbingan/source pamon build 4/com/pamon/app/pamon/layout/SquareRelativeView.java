package com.pamon.app.pamon.layout;

import android.content.Context;
import android.util.AttributeSet;

import android.widget.RelativeLayout;

/**
 * Created by Sofiyanudin on 06/11/2017.
 */

public class SquareRelativeView extends RelativeLayout {
    public SquareRelativeView(Context context) {
        super(context);
    }

    public SquareRelativeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareRelativeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //noinspection SuspiciousNameCombination
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
