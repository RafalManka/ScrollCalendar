package pl.rafman.scrollcalendar.adapter;

import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;

/**
 * Created by rafal.manka on 11/09/2017
 */
public interface ResProvider {

    // ColorRes

    @ColorInt
    int defaultFontColor();

    @ColorInt
    int defaultBackgroundColor();

    @ColorInt
    int disabledTextColor();

    @ColorInt
    int disabledBackgroundColor();

    @ColorInt
    int todayTextColor();

    @ColorInt
    int unavailableTextColor();

    @ColorInt
    int selectedTextColor();

    // Drawables

    @DrawableRes
    int todayBackground();

    @DrawableRes
    int unavailableBackgroundColor();

    @DrawableRes
    int selectedBackgroundColor();

    @Dimension
    float fontSize();

    // Other

    @Nullable
    Typeface getCustomFont();

}
