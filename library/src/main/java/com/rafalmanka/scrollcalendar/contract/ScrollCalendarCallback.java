package com.rafalmanka.scrollcalendar.contract;

import android.support.annotation.NonNull;

import com.rafalmanka.scrollcalendar.adapter.LegendItem;
import com.rafalmanka.scrollcalendar.data.CalendarDay;
import com.rafalmanka.scrollcalendar.data.CalendarMonth;

/**
 * Created by rafal.manka on 10/09/2017
 */

public abstract class ScrollCalendarCallback {

    public void onBeforeMonthDisplayed(@NonNull CalendarMonth month) {
    }

    public void onBeforeLegendDisplayed(@NonNull LegendItem[] legend) {
    }

    public void onCalendarDayClicked(@NonNull CalendarMonth calendarMonth, @NonNull CalendarDay calendarDay) {
    }

    public boolean shouldAddNextMonth(@NonNull CalendarMonth lastMonth) {
        return true;
    }
}
