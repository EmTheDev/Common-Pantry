package com.example.frankkoutoulas.splashscreen;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by miles on 12/3/15.
 */

/*
* Source code from: http://www.scriptscoop.net/t/bafde71eb45a/android-drawerlayout-must-be-measured-with-measurespec-exactly.html
*
* We added this custom UI element class to our project as a workaround to a bug that's been
* acknowledged but unfixed in the Android SDK for over two years.  Without this custom class,
* we were encountering a crash that was complaining that the DrawerLayout must be measured with
* MeasureSpec.EXACTLY
* */
public class CustomDrawerLayout extends DrawerLayout {

    public CustomDrawerLayout(Context context) {
        super(context);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     *
     * @param widthMeasureSpec set width
     * @param heightMeasureSpec set height
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.EXACTLY);
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                View.MeasureSpec.getSize(heightMeasureSpec), View.MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
