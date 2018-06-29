package pl.rafman.scrollcalendar.style;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

import java.util.Arrays;

import pl.rafman.scrollcalendar.adapter.ResProvider;

public interface DayResProvider {
    @DrawableRes
    int getDayBackground();

    @DrawableRes
    int getUnavailableBackground();

    @DrawableRes
    int getCurrentDayBackground();

    @DrawableRes
    int getSelectedDayBackground();

    @DrawableRes
    int getSelectedBeginningDayBackground();

    @DrawableRes
    int getSelectedMiddleDayBackground();

    @DrawableRes
    int getSelectedEndDayBackground();

    @DrawableRes
    int getDisabledBackground();

    @ColorInt
    int getCurrentDayTextColor();

    @ColorInt
    int getDisabledTextColor();

    @ColorInt
    int getSelectedDayTextColor();

    @ColorInt
    int getSelectedMiddleDayTextColor();

    @ColorInt
    int getSelectedBeginningDayTextColor();

    @ColorInt
    int getSelectedEndDayTextColor();

    @ColorInt
    int getUnavailableTextColor();

    @ColorInt
    int getDayTextColor();

    Typeface getCustomFont();

    boolean softLineBreaks();
}

