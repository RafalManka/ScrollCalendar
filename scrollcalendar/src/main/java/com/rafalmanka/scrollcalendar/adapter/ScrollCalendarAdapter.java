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
    @Nullable
    private ScrollCalendarCallback calendarCallback;
    @Nullable
    private View recyclerView;
    @NonNull
    private final ResProvider resProvider;

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
        return calendarCallback != null && calendarCallback.shouldAddNextMonth(getLastItem());
    }

    private void prepare(CalendarMonth month) {
        if (calendarCallback != null) {
            calendarCallback.onBeforeMonthDisplayed(month);
        }
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

    public void setCalendarCallback(@Nullable ScrollCalendarCallback calendarCallback) {
        this.calendarCallback = calendarCallback;
    }

    @Override
    public void onCalendarDayClicked(@NonNull CalendarMonth calendarMonth, @NonNull CalendarDay calendarDay) {
        if (calendarCallback != null) {
            calendarCallback.onCalendarDayClicked(calendarMonth, calendarDay);
            notifyDataSetChanged();
        }
    }
}
