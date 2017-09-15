package pl.rafalmanka.scrollcalendar.contract;

/**
 * Created by rafal.manka on 10/09/2017
 */

public interface ScrollCalendarCallback {

    @State
    int getStateForDate(int year, int month, int day);

    boolean shouldAddNextMonth(int lastDisplayedYear, int lastDisplayedMonth);

    void onCalendarDayClicked(int year, int month, int day);
}
