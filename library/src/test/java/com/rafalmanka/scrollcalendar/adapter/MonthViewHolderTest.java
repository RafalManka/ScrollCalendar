package pl.rafalmanka.scrollcalendar.adapter;

import android.annotation.SuppressLint;
import android.view.View;

import pl.rafalmanka.scrollcalendar.data.CalendarDay;
import pl.rafalmanka.scrollcalendar.data.CalendarMonth;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static junit.framework.Assert.assertEquals;

/**
 * Created by rafal.manka on 11/09/2017
 */
public class MonthViewHolderTest {


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void should1BeJanuary() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 26);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format1.format(calendar.getTime());
        assertEquals("2017-01-26", formatted);
    }

    @Test
    public void shouldNovemberWeeksHave4_7_4Days() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        MonthViewHolder cm = new MonthViewHolder(Mockito.mock(View.class));

        CalendarMonth calendarMonth = new CalendarMonth(2017, 10);
        CalendarDay[] w1 = cm.filterWeekDays(0, calendarMonth);
        assertEquals(0, w1.length);

        CalendarDay[] w2 = cm.filterWeekDays(1, calendarMonth);
        assertEquals(5, w2.length);

        CalendarDay[] w3 = cm.filterWeekDays(2, calendarMonth);
        assertEquals(7, w3.length);

        CalendarDay[] w4 = cm.filterWeekDays(3, calendarMonth);
        assertEquals(7, w4.length);

        CalendarDay[] w5 = cm.filterWeekDays(4, calendarMonth);
        assertEquals(7, w5.length);

        CalendarDay[] w6 = cm.filterWeekDays(5, calendarMonth);
        assertEquals(4, w6.length);

        CalendarDay[] w7 = cm.filterWeekDays(6, calendarMonth);
        assertEquals(0, w7.length);

    }


    @Test
    public void shouldHave31DaysDays() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        int daysOfMonth = calendar.getMaximum(Calendar.DAY_OF_MONTH);
        assertEquals(31, daysOfMonth);

    }
}