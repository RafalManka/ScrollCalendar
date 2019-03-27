package pl.rafman.scrollcalendar.contract;

import android.support.annotation.NonNull;

import pl.rafman.scrollcalendar.data.CalendarDay;
import pl.rafman.scrollcalendar.data.CalendarMonth;
import pl.rafman.scrollcalendar.widgets.SquareTextView;

/**
 * Created by rafal.manka on 11/09/2017
 */
public interface CalendarDayCallback {
    void onCalendarDayClicked(@NonNull CalendarMonth calendarMonth, @NonNull CalendarDay calendarDay);
}
