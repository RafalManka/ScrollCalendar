package com.rafalmanka.scrollcalendar.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.rafalmanka.scrollcalendar.contract.ClickCallback;
import com.rafalmanka.scrollcalendar.contract.ScrollCalendarCallback;
import com.rafalmanka.scrollcalendar.data.CalendarDay;
import com.rafalmanka.scrollcalendar.data.CalendarMonth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafal.manka on 10/09/2017
 */
public class ScrollCalendarAdapter extends RecyclerView.Adapter<MonthViewHolder> implements ClickCallback {

    @NonNull
    private final List<CalendarMonth> months = new ArrayList<>();
    @NonNull
    private final ResProvider resProvider;

    @Nullable
    private ScrollCalendarCallback callback;
    @Nullable
    private View recyclerView;

    public ScrollCalendarAdapter(@NonNull ResProvider resProvider) {
        this.resProvider = resProvider;
        months.add(CalendarMonth.now());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public MonthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MonthViewHolder.create(parent, this, resProvider);
    }

    @Override
    public void onBindViewHolder(MonthViewHolder holder, int position) {
        CalendarMonth month = getItem(position);
        prepare(month);
        holder.bind(month);
        afterBindViewHolder(position);
    }

    private void afterBindViewHolder(int position) {
        if (recyclerView != null && shouldAddNextMonth(position)) {
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    appendCalendarMonth();
                }
            });
        }
    }

    private boolean shouldAddNextMonth(int position) {
        return isNearBottom(position) && isAllowedToAddNextMonth();
    }

    private boolean isAllowedToAddNextMonth() {
<<<<<<< Updated upstream:library/src/main/java/com/rafalmanka/scrollcalendar/adapter/ScrollCalendarAdapter.java
        return calendarCallback != null && calendarCallback.shouldAddNextMonth(getLastItem());
    }

    private void prepare(CalendarMonth month) {
        if (calendarCallback != null) {
            calendarCallback.onBeforeMonthDisplayed(month);
        }
=======
        if (callback == null) {
            return true;
        }
        CalendarMonth item = getLastItem();
        return callback.shouldAddNextMonth(item.getYear(), item.getMonth());
    }

    private void prepare(CalendarMonth month) {
        for (CalendarDay calendarDay : month.getDays()) {
            calendarDay.setState(makeState(month, calendarDay));
        }
    }

    @State
    private int makeState(CalendarMonth month, CalendarDay calendarDay) {
        if (callback == null) {
            return CalendarDay.DEFAULT;
        }
        int year = month.getYear();
        int monthInt = month.getMonth();
        int day = calendarDay.getDay();
        return callback.getStateForDate(year, monthInt, day);
>>>>>>> Stashed changes:scrollcalendar/src/main/java/pl/rafalmanka/scrollcalendar/adapter/ScrollCalendarAdapter.java
    }

    private void appendCalendarMonth() {
        months.add(getLastItem().next());
        notifyDataSetChanged();
    }

    private CalendarMonth getLastItem() {
        return months.get(months.size() - 1);
    }

    private CalendarMonth getItem(int position) {
        return months.get(position);
    }

    private boolean isNearBottom(int position) {
        return months.size() - 1 <= position;
    }

    @Override
    public int getItemCount() {
        return months.size();
    }

    public void setCallback(@Nullable ScrollCalendarCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onCalendarDayClicked(@NonNull CalendarMonth calendarMonth, @NonNull CalendarDay calendarDay) {
<<<<<<< Updated upstream:library/src/main/java/com/rafalmanka/scrollcalendar/adapter/ScrollCalendarAdapter.java
        if (calendarCallback != null) {
            calendarCallback.onCalendarDayClicked(calendarMonth, calendarDay);
=======
        if (callback != null) {
            int year = calendarMonth.getYear();
            int month = calendarMonth.getMonth();
            int day = calendarDay.getDay();
            callback.onCalendarDayClicked(year, month, day);
>>>>>>> Stashed changes:scrollcalendar/src/main/java/pl/rafalmanka/scrollcalendar/adapter/ScrollCalendarAdapter.java
            notifyDataSetChanged();
        }
    }
}
