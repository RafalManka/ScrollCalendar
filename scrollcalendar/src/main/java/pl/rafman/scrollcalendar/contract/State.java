package pl.rafman.scrollcalendar.contract;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import pl.rafman.scrollcalendar.data.CalendarDay;

/**
 * Created by rafal.manka on 10/09/2017
 */
@IntDef({
        CalendarDay.DEFAULT,
        CalendarDay.DISABLED,
        CalendarDay.TODAY,
        CalendarDay.UNAVAILABLE,
        CalendarDay.SELECTED,
        CalendarDay.FIRST_SELECTED,
        CalendarDay.LAST_SELECTED,
        CalendarDay.ONLY_SELECTED,
})
@Retention(value = RetentionPolicy.SOURCE)
public @interface State {
}
