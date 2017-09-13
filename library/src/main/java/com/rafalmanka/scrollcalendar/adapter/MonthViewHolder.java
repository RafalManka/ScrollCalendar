package com.rafalmanka.scrollcalendar.adapter;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rafalmanka.scrollcalendar.R;
import com.rafalmanka.scrollcalendar.contract.ClickCallback;
import com.rafalmanka.scrollcalendar.data.CalendarDay;
import com.rafalmanka.scrollcalendar.data.CalendarMonth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by rafal.manka on 10/09/2017
 */
class MonthViewHolder extends RecyclerView.ViewHolder {

    @Nullable
    private final TextView title;

    private final WeekHolder[] weeks = new WeekHolder[6];

    private MonthViewHolder(@NonNull View rootView, @NonNull ClickCallback calendarCallback, @NonNull ResProvider resProvider) {
        super(rootView);
        LinearLayout monthContainer = rootView.findViewById(R.id.monthContainer);
        title = rootView.findViewById(R.id.title);
        Typeface typeface = resProvider.getCustomFont();
        if (typeface != null) {
            title.setTypeface(typeface);
        }
        for (int i = 0; i < weeks.length; i++) {
            weeks[i] = new WeekHolder(calendarCallback, resProvider);
        }
        for (WeekHolder week : weeks) {
            monthContainer.addView(week.layout(monthContainer));
        }
    }

    MonthViewHolder(View rootView) {
        super(rootView);
        title = null;
    }

    static MonthViewHolder create(@NonNull ViewGroup parent, @NonNull ClickCallback calendarCallback, @NonNull ResProvider resProvider) {
        return new MonthViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.month, parent, false),
                calendarCallback, resProvider);
    }

    void bind(CalendarMonth month) {
        if (title != null) {
            title.setText(month.getReadableMonthName());
        }
        for (int i = 1; i <= weeks.length; i++) {
            weeks[i - 1].display(i, month, filterWeekDays(i, month));
        }
    }

    CalendarDay[] filterWeekDays(int weekOfMonth, CalendarMonth calendarMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendarMonth.getYear());
        calendar.set(Calendar.MONTH, calendarMonth.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        List<CalendarDay> days = new ArrayList<>();
        for (CalendarDay calendarDay : calendarMonth.getDays()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendarDay.getDay());
            if (calendar.get(Calendar.WEEK_OF_MONTH) == weekOfMonth) {
                days.add(calendarDay);
            }
        }
        return days.toArray(new CalendarDay[days.size()]);
    }

}
