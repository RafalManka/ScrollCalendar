package pl.rafman.scrollcalendar.style;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;

import java.util.Arrays;

import pl.rafman.scrollcalendar.adapter.ResProvider;

public class MonthResProviderImpl implements MonthResProvider {
    private static final int[] attrs = {
            android.R.attr.textColor,
            android.R.attr.textSize,
            android.R.attr.gravity,
            android.R.attr.textAllCaps,
    };

    static {
        Arrays.sort(attrs);
    }

    @ColorInt
    private int textColor;
    private int textSize;
    private int gravity;
    private boolean textAllCaps;
    //    private Typeface typeface;
    private boolean showYearAlways;

    public MonthResProviderImpl(Context context, ResProvider resProvider) {
        // Text appearance
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(resProvider.getMonthTitleStyle(), attrs);
        for (int i = 0; i < attrs.length; i++) {
            switch (attrs[i]) {
                case android.R.attr.textColor:
                    textColor = typedArray.getColor(i, ContextCompat.getColor(context, android.R.color.black));
                    break;
                case android.R.attr.textSize:
                    textSize = typedArray.getDimensionPixelSize(i, 12);
                    break;
                case android.R.attr.gravity:
                    gravity = typedArray.getInt(i, Gravity.START);
                    break;
                case android.R.attr.textAllCaps:
                    textAllCaps = typedArray.getBoolean(i, false);
                    break;
                default:
                    break;
            }
        }
        typedArray.recycle();
        // Typeface
//        typeface = resProvider.getCustomFont();
        showYearAlways = resProvider.showYearAlways();

    }

    @Override
    public int getTextColor() {
        return textColor;
    }

    @Override
    public float gatTextSize() {
        return textSize;
    }

    @Override
    public int getGravity() {
        return gravity;
    }

    @Override
    public boolean getTextAllCaps() {
        return textAllCaps;
    }

    @Override
    public boolean showYearAlways() {
        return showYearAlways;
    }
}
