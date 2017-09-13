package com.rafalmanka.scrollcalendar.contract;

import android.support.annotation.IntDef;

import com.rafalmanka.scrollcalendar.data.CalendarDay;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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
