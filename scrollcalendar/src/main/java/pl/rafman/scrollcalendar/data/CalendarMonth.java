package pl.rafman.scrollcalendar.data;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.Calendar;

import pl.rafman.scrollcalendar.CalendarProvider;

/**
 * Created by rafal.manka on 10/09/2017
 */

public class CalendarMonth implements Serializable {

    private final String[] months = new DateFormatSymbols().getMonths();
    private final int year;
    private final int month;
    @NonNull
    private final CalendarDay[] days;
    private final CalendarProvider calendarProvider;

    public CalendarMonth(CalendarProvider calendarProvider, int year, int month) {
        this(calendarProvider, year, month, makeDays(calendarProvider, year, month));
    }

    private CalendarMonth(CalendarProvider calendarProvider, int year, int month, @NonNull CalendarDay[] days) {
        this.calendarProvider = calendarProvider;
        this.year = year;
        this.month = month;
        this.days = days;
    }

    @NonNull
    static CalendarDay[] makeDays(CalendarProvider calendarProvider, int year, int month) {
        Calendar calendar = calendarProvider.getCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        CalendarDay[] calendarDays = new CalendarDay[maxDays];

        for (int i = 1; i <= maxDays; i++) {
            calendarDays[i - 1] = new CalendarDay(i);
        }

        return calendarDays;
    }

    @NonNull
    public CalendarDay[] getDays() {
        return days;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }


    public CalendarMonth previous() {
        if (month == Calendar.JANUARY) {
            return new CalendarMonth(calendarProvider, year - 1, Calendar.DECEMBER);
        } else {
            return new CalendarMonth(calendarProvider, year, month - 1);
        }
    }

    public CalendarMonth next() {
        if (month == Calendar.DECEMBER) {
            return new CalendarMonth(calendarProvider, year + 1, Calendar.JANUARY);
        } else {
            return new CalendarMonth(calendarProvider, year, month + 1);
        }
    }

    public static CalendarMonth now(CalendarProvider calendarProvider) {
        Calendar calendar = calendarProvider.getCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        return new CalendarMonth(calendarProvider, year, month);
    }

    @NonNull
    public String getReadableMonthName() {
        return getMonthForInt(getMonth()) + maybeGetYear();
    }

    private String maybeGetYear() {
        if (isaCurrentYear()) {
            return "";
        } else {
            return " " + year;
        }
    }

    private boolean isaCurrentYear() {
        return year == calendarProvider.getCalendar().get(Calendar.YEAR);
    }

    private String getMonthForInt(int num) {
        if (num < 0) {
            return "";
        }
        if (num >= months.length) {
            return "";
        }
        return months[num];
    }

    @NonNull
    public String getMonthNameWithYear() {
        return getMonthForInt(getMonth()) + " " + year;
    }
}
