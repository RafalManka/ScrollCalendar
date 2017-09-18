package pl.rafman.scrollcalendar.contract;

/**
 * Created by rafal.manka on 18/09/2017
 */

public interface MonthScrollListener {
    boolean shouldAddNextMonth(int lastDisplayedYear, int lastDisplayedMonth);
}
