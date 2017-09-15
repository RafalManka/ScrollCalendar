package pl.rafalmanka.scrollcalendar.contract;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import pl.rafalmanka.scrollcalendar.data.CalendarDay;

/**
 * Created by rafal.manka on 10/09/2017
 */
@IntDef({
        CalendarDay.DEFAULT,
        CalendarDay.DISABLED,
        CalendarDay.TODAY,
        CalendarDay.UNAVAILABLE,
        CalendarDay.SELECTED,
})
@Retention(value = RetentionPolicy.SOURCE)
public @interface State {
}
