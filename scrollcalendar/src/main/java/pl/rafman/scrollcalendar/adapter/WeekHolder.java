package pl.rafman.scrollcalendar.adapter;

<<<<<<< HEAD
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
=======
>>>>>>> 19419b1b5f390dba9cd8ddedea1336892cc9c3c7
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pl.rafman.scrollcalendar.R;
import pl.rafman.scrollcalendar.contract.ClickCallback;
import pl.rafman.scrollcalendar.data.CalendarDay;
import pl.rafman.scrollcalendar.data.CalendarMonth;
import pl.rafman.scrollcalendar.style.DayResProvider;

/**
 * Created by rafal.manka on 11/09/2017
 */
public class WeekHolder {

    private final DayHolder[] days = new DayHolder[7];

    @Nullable
    private LinearLayout container;

    public WeekHolder(@NonNull ClickCallback calendarCallback, @NonNull DayResProvider resProvider) {
        for (int i = 0; i < days.length; i++) {
            days[i] = createDayHolder(calendarCallback, resProvider);
        }
    }

    protected DayHolder createDayHolder(@NonNull ClickCallback calendarCallback, @NonNull DayResProvider resProvider) {
        return new DayHolder(calendarCallback, resProvider);
    }

    @Nullable
    public View layout(LinearLayout parent) {
        if (container == null) {
            container = (LinearLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.scrollcalendar_week, parent, false);
            if (container != null) {
                for (DayHolder day : days) {
                    container.addView(day.layout(container));
                }
            }
        }
        return container;
    }

    void display(int week, CalendarMonth month, CalendarDay[] daysOfWeek) {
        if (container != null) {
            container.setVisibility(daysOfWeek.length == 0 ? View.GONE : View.VISIBLE);
        }
        for (int i = 0; i < days.length; i++) {
            days[i].display(
                    month,
                    dayOrNull(i, week, daysOfWeek),
                    dayOrNull(i - 1, week, daysOfWeek),
                    dayOrNull(i + 1, week, daysOfWeek)
            );
        }
    }

    private CalendarDay dayOrNull(int position, int week, CalendarDay[] calendarDays) {
        if (isRightAligned(week)) {
            return takeRightAligned(position, calendarDays);
        } else {
            return takeLeftAligned(position, calendarDays);
        }
    }

    @Nullable
    private CalendarDay takeLeftAligned(int position, CalendarDay[] calendarDays) {
        if (position >= 0 && position < calendarDays.length) { // 0 < 3 ; 1 < 3 ; 2 < 3
            return calendarDays[position];
        } else { // 3!<3 | 4!<3 | 5!<3 | 6!<3
            return null;
        }
    }

    @Nullable
    private CalendarDay takeRightAligned(int position, CalendarDay[] calendarDays) {
        int offset = days.length - calendarDays.length;
        if (position < offset) {
            return null;
        } else {
            int realPosition = position - offset;
            if (realPosition < 0 || realPosition >= calendarDays.length) {
                return null;
            }
            return calendarDays[realPosition];
        }
    }

    private boolean isRightAligned(int week) {
        return week <= 1;
    }

}
