package pl.rafman.scrollcalendar.style;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;

import java.util.Arrays;

import pl.rafman.scrollcalendar.adapter.ResProvider;

public class DayResProviderImpl implements DayResProvider {

    private static final int[] attrs = {
            android.R.attr.background,
            android.R.attr.textColor,
    };

    static {
        Arrays.sort(attrs);
    }

    @DrawableRes
    private int dayBackground;
    @DrawableRes
    private int unavailableBackground;
    @DrawableRes
    private int currentDayBackground;
    @DrawableRes
    private int selectedDayBackground;
    @DrawableRes
    private int selectedBeginningDayBackground;
    @DrawableRes
    private int selectedMiddleDayBackground;
    @DrawableRes
    private int selectedEndDayBackground;
    @DrawableRes
    private int disabledBackground;
    @ColorInt
    private int currentDayTextColor;
    @ColorInt
    private int disabledTextColor;
    @ColorInt
    private int selectedDayTextColor;
    @ColorInt
    private int selectedMiddleDayTextColor;
    @ColorInt
    private int selectedBeginningDayTextColor;
    @ColorInt
    private int selectedEndDayTextColor;
    @ColorInt
    private int unavailableTextColor;
    @ColorInt
    private int dayTextColor;

    private final Typeface customFont;
    private boolean softLineBreaks;

    @Override
    @DrawableRes
    public int getDayBackground() {
        return dayBackground;
    }

    @Override
    @DrawableRes
    public int getUnavailableBackground() {
        return unavailableBackground;
    }

    @Override
    @DrawableRes
    public int getCurrentDayBackground() {
        return currentDayBackground;
    }

    @Override
    @DrawableRes
    public int getSelectedDayBackground() {
        return selectedDayBackground;
    }

    @Override
    @DrawableRes
    public int getSelectedBeginningDayBackground() {
        return selectedBeginningDayBackground;
    }

    @Override
    @DrawableRes
    public int getSelectedMiddleDayBackground() {
        return selectedMiddleDayBackground;
    }

    @Override
    @DrawableRes
    public int getSelectedEndDayBackground() {
        return selectedEndDayBackground;
    }

    @Override
    @DrawableRes
    public int getDisabledBackground() {
        return disabledBackground;
    }

    @Override
    @ColorInt
    public int getCurrentDayTextColor() {
        return currentDayTextColor;
    }

    @Override
    @ColorInt
    public int getDisabledTextColor() {
        return disabledTextColor;
    }

    @Override
    @ColorInt
    public int getSelectedDayTextColor() {
        return selectedDayTextColor;
    }

    @Override
    @ColorInt
    public int getSelectedMiddleDayTextColor() {
        return selectedMiddleDayTextColor;
    }

    @Override
    @ColorInt
    public int getSelectedBeginningDayTextColor() {
        return selectedBeginningDayTextColor;
    }

    @Override
    @ColorInt
    public int getSelectedEndDayTextColor() {
        return selectedEndDayTextColor;
    }

    @Override
    @ColorInt
    public int getUnavailableTextColor() {
        return unavailableTextColor;
    }

    @ColorInt
    @Override
    public int getDayTextColor() {
        return dayTextColor;
    }

    @Override
    public Typeface getCustomFont() {
        return customFont;
    }

    @Override
    public boolean softLineBreaks() {
        return softLineBreaks;
    }

    public DayResProviderImpl(Context context, ResProvider resProvider) {
        customFont = resProvider.getCustomFont();
        softLineBreaks = resProvider.softLineBreaks();
        setupDayStyle(context, resProvider);
        setupCurrentDayStyle(context, resProvider);
        setupSelectedDayStyle(context, resProvider);
        setupSelectedBeginningDayStyle(context, resProvider);
        setupSelectedMiddleDateStyle(context, resProvider);
        setupSelectedEndDateStyle(context, resProvider);
        setupDisabledDayStyle(context, resProvider);
        setupUnavailableDayStyle(context, resProvider);
    }


    private void setupDayStyle(Context context, ResProvider resProvider) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(resProvider.getDayStyle(), attrs);
        for (int i = 0; i < attrs.length; i++) {
            if (attrs[i] == android.R.attr.background) {
                dayBackground = typedArray.getResourceId(i, 0);
            } else if (attrs[i] == android.R.attr.textColor) {
                dayTextColor = typedArray.getColor(i, 0);
            }
        }
        typedArray.recycle();
    }

    private void setupUnavailableDayStyle(Context context, ResProvider resProvider) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(resProvider.getUnavailableItemStyle(), attrs);
        for (int i = 0; i < attrs.length; i++) {
            if (attrs[i] == android.R.attr.background) {
                unavailableBackground = typedArray.getResourceId(i, 0);
            } else if (attrs[i] == android.R.attr.textColor) {
                unavailableTextColor = typedArray.getColor(i, 0);
            }
        }
        typedArray.recycle();
    }

    private void setupDisabledDayStyle(Context context, ResProvider resProvider) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(resProvider.getDisabledItemStyle(), attrs);
        for (int i = 0; i < attrs.length; i++) {
            if (attrs[i] == android.R.attr.background) {
                disabledBackground = typedArray.getResourceId(i, 0);
            } else if (attrs[i] == android.R.attr.textColor) {
                disabledTextColor = typedArray.getColor(i, 0);
            }
        }
        typedArray.recycle();
    }

    private void setupSelectedEndDateStyle(Context context, ResProvider resProvider) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(resProvider.getSelectedEndDayStyle(), attrs);
        for (int i = 0; i < attrs.length; i++) {
            if (attrs[i] == android.R.attr.background) {
                selectedEndDayBackground = typedArray.getResourceId(i, 0);
            } else if (attrs[i] == android.R.attr.textColor) {
                selectedEndDayTextColor = typedArray.getColor(i, 0);
            }
        }
        typedArray.recycle();
    }

    private void setupSelectedMiddleDateStyle(Context context, ResProvider resProvider) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(resProvider.getSelectedMiddleDayStyle(), attrs);
        for (int i = 0; i < attrs.length; i++) {
            if (attrs[i] == android.R.attr.background) {
                selectedMiddleDayBackground = typedArray.getResourceId(i, 0);
            } else if (attrs[i] == android.R.attr.textColor) {
                selectedMiddleDayTextColor = typedArray.getColor(i, 0);
            }
        }
        typedArray.recycle();
    }

    private void setupSelectedBeginningDayStyle(Context context, ResProvider resProvider) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(resProvider.getSelectedBeginningDayStyle(), attrs);
        for (int i = 0; i < attrs.length; i++) {
            if (attrs[i] == android.R.attr.background) {
                selectedBeginningDayBackground = typedArray.getResourceId(i, 0);
            } else if (attrs[i] == android.R.attr.textColor) {
                selectedBeginningDayTextColor = typedArray.getColor(i, 0);
            }
        }
        typedArray.recycle();
    }

    private void setupSelectedDayStyle(Context context, ResProvider resProvider) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(resProvider.getSelectedDayStyle(), attrs);
        for (int i = 0; i < attrs.length; i++) {
            if (attrs[i] == android.R.attr.background) {
                selectedDayBackground = typedArray.getResourceId(i, 0);
            } else if (attrs[i] == android.R.attr.textColor) {
                selectedDayTextColor = typedArray.getColor(i, 0);
            }
        }
        typedArray.recycle();
    }

    private void setupCurrentDayStyle(Context context, ResProvider resProvider) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(resProvider.getCurrentDayStyle(), attrs);
        for (int i = 0; i < attrs.length; i++) {
            if (attrs[i] == android.R.attr.background) {
                currentDayBackground = typedArray.getResourceId(i, 0);
            } else if (attrs[i] == android.R.attr.textColor) {
                currentDayTextColor = typedArray.getColor(i, 0);
            }
        }
        typedArray.recycle();
    }

}
