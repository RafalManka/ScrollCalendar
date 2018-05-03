package pl.rafman.scrollcalendar.data;

import android.annotation.SuppressLint;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static junit.framework.Assert.assertEquals;

/**
 * Created by rafal.manka on 10/09/2017
 */
public class CalendarMonthTest {

    @Test
    public void shouldWorkOnLeapYear() throws Exception {
        CalendarDay[] days = CalendarMonth.makeDays(2000, 1);
        assertEquals(29, days.length);
        assertEquals(1, days[0].getDay());
        assertEquals(20, days[19].getDay());
        assertEquals(29, days[28].getDay());
    }

    @Test
    public void shouldWorkOnJanuary() throws Exception {
        CalendarDay[] days = CalendarMonth.makeDays(2000, 0);
        assertEquals(31, days.length);
    }

    @Test
    public void shouldWorkOnApril() throws Exception {
        CalendarDay[] days = CalendarMonth.makeDays(2000, 3);
        assertEquals(30, days.length);
    }

    @Test
    public void shouldWorkOnCalendarDecember() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);


        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format1.format(calendar.getTime());
        assertEquals("2017-12-01", formatted);
    }

    @Test
    public void shouldFormatDayAndMonth() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);


        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
        String formatted = format1.format(calendar.getTime());
        assertEquals("2017-12", formatted);
    }
}