package com.rafalmanka.scrollcalendar.contract;

import android.support.annotation.NonNull;

import com.rafalmanka.scrollcalendar.adapter.LegendItem;
import com.rafalmanka.scrollcalendar.data.CalendarDay;
import com.rafalmanka.scrollcalendar.data.CalendarMonth;

/**
 * Created by rafal.manka on 10/09/2017
 */

public abstract class ScrollCalendarCallback {

    @State
    public int getStateForDate(int year, int month, int day) {
        return CalendarDay.DEFAULT;
    }

    public boolean shouldAddNextMonth(@NonNull CalendarMonth lastMonth) {
        return true;
    }

    public void onCalendarDayClicked(int year, int month, int day) {
        // Do nothing by default
    }
}
