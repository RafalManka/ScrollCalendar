package pl.rafman.scrollcalendar.adapter.example;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

import pl.rafman.scrollcalendar.CalendarProvider;
import pl.rafman.scrollcalendar.adapter.ScrollCalendarAdapter;
import pl.rafman.scrollcalendar.data.CalendarDay;
import pl.rafman.scrollcalendar.style.DayResProvider;
import pl.rafman.scrollcalendar.style.MonthResProvider;

public class DefaultDateScrollCalendarAdapter extends ScrollCalendarAdapter {


    @Nullable
    private Calendar selected;
    protected CalendarProvider calendarProvider;

    public DefaultDateScrollCalendarAdapter(@NonNull MonthResProvider monthResProvider, @NonNull DayResProvider dayResProvider, CalendarProvider calendarProvider) {
        super(monthResProvider, dayResProvider, calendarProvider);
        this.calendarProvider = calendarProvider;
    }

    public DefaultDateScrollCalendarAdapter(@NonNull MonthResProvider monthResProvider, @NonNull DayResProvider dayResProvider, CalendarProvider calendarProvider, boolean addCurrentMonth) {
        super(monthResProvider, dayResProvider, calendarProvider, addCurrentMonth);
        this.calendarProvider = calendarProvider;
    }

    @Override
    protected void onCalendarDayClicked(int year, int month, int day) {
        Calendar calendar = calendarProvider.getCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        if (isInThePast(year, month, day)) {
            return;
        }

        if (selected != null && selected.equals(calendar)) {
            selected = null;
        } else {
            selected = calendar;
        }
        super.onCalendarDayClicked(year, month, day);
    }

    @Override
    protected int getStateForDate(int year, int month, int day) {
        if (isSelected(selected, year, month, day)) {
            return CalendarDay.SELECTED;
        }
        if (isToday(year, month, day)) {
            return CalendarDay.TODAY;
        }
        if (isInThePast(year, month, day)) {
            return CalendarDay.DISABLED;
        }
        return CalendarDay.DEFAULT;
    }

    private boolean isInThePast(int year, int month, int day) {
        Calendar calendar = calendarProvider.getCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long now = calendar.getTimeInMillis();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        long then = calendar.getTimeInMillis();
        return now > then;
    }

    @Override
    protected boolean isAllowedToAddPreviousMonth() {
        return false;
    }

    @Override
    protected boolean isAllowedToAddNextMonth() {
        return true;
    }

    private boolean isSelected(Calendar selected, int year, int month, int day) {
        if (selected == null) {
            return false;
        }
        //noinspection UnnecessaryLocalVariable
        Calendar calendar = calendarProvider.getCalendar();
        calendar.set(Calendar.YEAR, selected.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, selected.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, selected.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long millis = calendar.getTimeInMillis();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long millis2 = calendar.getTimeInMillis();

        return millis == millis2;
    }

    private boolean isToday(int year, int month, int day) {
        //noinspection UnnecessaryLocalVariable
        Calendar calendar = calendarProvider.getCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // Today in milliseconds
        long today = calendar.getTime().getTime();

        // Given day in milliseconds
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        long calendarMillis = calendar.getTime().getTime();

        return today == calendarMillis;
    }

    @Nullable
    public Date getSelectedDate() {
        return selected != null ? selected.getTime() : null;
    }
}
