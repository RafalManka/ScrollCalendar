package com.rafalmanka.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rafalmanka.scrollcalendar.ScrollCalendar;
import com.rafalmanka.scrollcalendar.adapter.LegendItem;
import com.rafalmanka.scrollcalendar.contract.ScrollCalendarCallback;
import com.rafalmanka.scrollcalendar.data.CalendarDay;
import com.rafalmanka.scrollcalendar.data.CalendarMonth;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Nullable
    Calendar selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScrollCalendar scrollCalendar = (ScrollCalendar) findViewById(R.id.scrollCalendar);
        if (scrollCalendar == null) {
            return;
        }
        scrollCalendar.setCallback(new ScrollCalendarCallback() {
            @Override
            public void onBeforeMonthDisplayed(@NonNull CalendarMonth month) {
                super.onBeforeMonthDisplayed(month);
                doOnBeforeMonthDisplayed(month);
            }

            @Override
            public void onBeforeLegendDisplayed(@NonNull LegendItem[] legend) {
                super.onBeforeLegendDisplayed(legend);
            }

            @Override
            public void onCalendarDayClicked(@NonNull CalendarMonth calendarMonth, @NonNull CalendarDay calendarDay) {
                super.onCalendarDayClicked(calendarMonth, calendarDay);
                doOnCalendarDayClicked(calendarMonth, calendarDay);
            }

            @Override
            public boolean shouldAddNextMonth(@NonNull CalendarMonth lastMonth) {
                return doShouldAddNextMonth(lastMonth);
            }

        });
    }

    private void doOnCalendarDayClicked(@NonNull CalendarMonth month, @NonNull CalendarDay day) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, month.getYear());
        calendar.set(Calendar.MONTH, month.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, day.getDay());

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        if (isInThePast(month.getYear(), month.getMonth(), day.getDay())) {
            return;
        }
        if (isWeekend(calendar, month.getYear(), month.getMonth(), day.getDay())) {
            return;
        }
        if (isUnavailable(month.getYear(), month.getMonth(), day.getDay())) {
            return;
        }

        selected = calendar;
    }

    private boolean isInThePast(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
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

    private boolean doShouldAddNextMonth(@NonNull CalendarMonth lastMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 10);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long target = calendar.getTimeInMillis();

        calendar.set(Calendar.YEAR, lastMonth.getYear());
        calendar.set(Calendar.MONTH, lastMonth.getMonth());

        return calendar.getTimeInMillis() < target;
    }

    private void doOnBeforeMonthDisplayed(@NonNull CalendarMonth month) {
        for (CalendarDay day : month.getDays()) {
            day.setState(CalendarDay.DEFAULT);
            maybeSetupPast(month, day);
            maybeSetupToday(month, day);
            maybeSetupDisabled(month, day);
            maybeSetupUnavailable(month, day);
            maybeSetupSelected(month, day);
        }
    }

    private void maybeSetupPast(CalendarMonth month, CalendarDay day) {
        if (isInThePast(month.getYear(), month.getMonth(), day.getDay())) {
            day.setState(CalendarDay.DISABLED);
        }
    }

    private void maybeSetupSelected(CalendarMonth month, CalendarDay day) {
        if (isSelected(selected, month.getYear(), month.getMonth(), day.getDay())) {
            day.setState(CalendarDay.SELECTED);
        }
    }

    private boolean isSelected(Calendar selected, int year, int month, int day) {
        if (selected == null) {
            return false;
        }
        //noinspection UnnecessaryLocalVariable
        Calendar calendar = Calendar.getInstance();
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

    private void maybeSetupUnavailable(CalendarMonth month, CalendarDay day) {
        if (isUnavailable(month.getYear(), month.getMonth(), day.getDay())) {
            day.setState(CalendarDay.UNAVAILABLE);
        }
    }

    private boolean isUnavailable(int year, int month, int day) {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.YEAR, year);
        now.set(Calendar.MONTH, month);
        now.set(Calendar.DAY_OF_MONTH, day);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        long millis = now.getTimeInMillis();

        //noinspection UnnecessaryLocalVariable
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        long millis2 = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        long millis3 = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        long millis4 = calendar.getTimeInMillis();

        return millis == millis2 || millis == millis3 || millis == millis4;
    }

    private void maybeSetupDisabled(CalendarMonth month, CalendarDay day) {
        Calendar now = Calendar.getInstance();
        if (isWeekend(now, month.getYear(), month.getMonth(), day.getDay())) {
            day.setState(CalendarDay.DISABLED);
        }
    }

    private boolean isWeekend(@Nullable Calendar now, int year, int month, int day) {
        if (now == null) {
            return false;
        }
        //noinspection UnnecessaryLocalVariable
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, now.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return dayOfWeek == 1 || dayOfWeek == 7;
    }

    private void maybeSetupToday(@NonNull CalendarMonth month, @NonNull CalendarDay day) {
        Calendar now = Calendar.getInstance();
        if (isToday(now, month.getYear(), month.getMonth(), day.getDay())) {
            day.setState(CalendarDay.TODAY);
        }
    }

    private boolean isToday(Calendar now, int year, int month, int day) {
        //noinspection UnnecessaryLocalVariable
        Calendar calendar = now;

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // Today in milliseconds
        long today = calendar.getTime().getTime();

        // calendar day in milliseconds
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        long calendarMillis = calendar.getTime().getTime();

        return today == calendarMillis;
    }

}
