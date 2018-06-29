package pl.rafman.scrollcalendar.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

/**
 * Created by rafal.manka on 11/09/2017
 */
public interface ResProvider {

    // Other
    @Nullable
    Typeface getCustomFont();

    @StyleRes
    int getMonthTitleStyle();

    @StyleRes
    int getLegendItemStyle();

    @StyleRes
    int getCurrentDayStyle();

    @StyleRes
    int getSelectedDayStyle();

    @StyleRes
    int getSelectedBeginningDayStyle();

    @StyleRes
    int getSelectedMiddleDayStyle();

    @StyleRes
    int getSelectedEndDayStyle();

    @StyleRes
    int getUnavailableItemStyle();

    @StyleRes
    int getDayStyle();

    @StyleRes
    int getDisabledItemStyle();

    boolean showYearAlways();

    boolean softLineBreaks();
}
