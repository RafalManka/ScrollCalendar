package pl.rafman.scrollcalendar.contract;

import pl.rafman.scrollcalendar.widgets.SquareTextView;

/**
 * Created by rafal.manka on 18/09/2017
 */
public interface DateWatcher {
    @State
    int getStateForDate(int year, int month, int day);
    void onDateTextSet(SquareTextView tvDate, int year, int month, int day);
}
