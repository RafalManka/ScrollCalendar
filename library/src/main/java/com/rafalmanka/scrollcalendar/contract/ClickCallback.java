package com.rafalmanka.scrollcalendar.contract;

import android.support.annotation.NonNull;

import com.rafalmanka.scrollcalendar.data.CalendarDay;
import com.rafalmanka.scrollcalendar.data.CalendarMonth;

/**
 * Created by rafal.manka on 11/09/2017
 */
public interface ClickCallback {
    void onCalendarDayClicked(@NonNull CalendarMonth calendarMonth, @NonNull CalendarDay calendarDay);
}
