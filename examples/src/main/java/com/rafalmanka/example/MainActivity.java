package com.rafalmanka.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

import pl.rafalmanka.scrollcalendar.ScrollCalendar;
import pl.rafalmanka.scrollcalendar.contract.ScrollCalendarCallback;
import pl.rafalmanka.scrollcalendar.contract.State;
import pl.rafalmanka.scrollcalendar.data.CalendarDay;

public class MainActivity extends AppCompatActivity {

    @Nullable
    private Calendar selected;

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
            public int getStateForDate(int year, int month, int day) {
                return doGetStateForDate(year, month, day);
            }

            @Override
            public void onCalendarDayClicked(int year, int month, int day) {
                doOnCalendarDayClicked(year, month, day);
            }

            @Override
            public boolean shouldAddNextMonth(int lastDisplayedYear, int lastDisplayedMonth) {
                return doShouldAddNextMonth(lastDisplayedYear, lastDisplayedMonth);
            }

        });
    }

    @State
    private int doGetStateForDate(int year, int month, int day) {

        if (isSelected(selected, year, month, day)) {
            return CalendarDay.SELECTED;
        }
        if (isUnavailable(year, month, day)) {
            return CalendarDay.UNAVAILABLE;
        }
        if (isInThePast(year, month, day)) {
            return CalendarDay.DISABLED;
        }

        Calendar now = Calendar.getInstance();
        if (isWeekend(now, year, month, day)) {
            return CalendarDay.DISABLED;
        }
        if (isToday(now, year, month, day)) {
            return CalendarDay.TODAY;
        }
        return CalendarDay.DEFAULT;
    }

    private void doOnCalendarDayClicked(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();

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
        if (isWeekend(calendar, year, month, day)) {
            return;
        }
        if (isUnavailable(year, month, day)) {
            return;
        }

        if (selected != null && selected.equals(calendar)) {
            selected = null;
        } else {
            selected = calendar;
        }
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

    private boolean doShouldAddNextMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 10);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long target = calendar.getTimeInMillis();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);

        return calendar.getTimeInMillis() < target;
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

    private boolean isToday(@Nullable Calendar now, int year, int month, int day) {
        if (now == null) {
            return false;
        }
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
