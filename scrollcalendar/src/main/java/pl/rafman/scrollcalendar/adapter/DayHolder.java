package pl.rafman.scrollcalendar.adapter;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.rafman.scrollcalendar.contract.ClickCallback;
import pl.rafman.scrollcalendar.contract.DayViewFactory;
import pl.rafman.scrollcalendar.data.CalendarDay;
import pl.rafman.scrollcalendar.data.CalendarMonth;
import pl.rafman.scrollcalendar.style.DayResProvider;
import pl.rafman.scrollcalendar.widgets.SquareTextView;

/**
 * Created by rafal.manka on 10/09/2017
 */
class DayHolder implements View.OnClickListener {

    private final DayResProvider resProvider;

    @NonNull
    private final ClickCallback calendarCallback;
    @Nullable
    private View layoutView;

    @Nullable
    private CalendarMonth calendarMonth;
    @Nullable
    private CalendarDay currentDay;

    @NonNull
    private final DayViewFactory factory;

    DayHolder(@NonNull ClickCallback calendarCallback, @NonNull DayResProvider resProvider, DayViewFactory factory) {
        this.calendarCallback = calendarCallback;
        this.resProvider = resProvider;
        this.factory = factory != null ? factory : new DayViewFactory() {
        };
    }

    public View layout(LinearLayout parent) {
        if (layoutView == null) {
            layoutView = factory.createView(parent);
            layoutView.setOnClickListener(this);
        }
        return layoutView;
    }

    void display(@Nullable CalendarMonth calendarMonth, @Nullable CalendarDay currentDay, @Nullable CalendarDay previousDay, @Nullable CalendarDay nextDay) {
        this.calendarMonth = calendarMonth;
        this.currentDay = currentDay;
        setupVisibility(currentDay);
        setupStyles(currentDay, previousDay, nextDay);
    }

    private void setupVisibility(@Nullable CalendarDay calendarDay) {
        if (layoutView == null) {
            return;
        }
        if (calendarDay == null) {
            layoutView.setVisibility(View.INVISIBLE);
        } else {
            layoutView.setVisibility(View.VISIBLE);
            if (layoutView instanceof SquareTextView) {
                TextView textView = (TextView) layoutView;
                textView.setText(String.valueOf(calendarDay.getDay()));
            }
            if (calendarMonth != null) {
                factory.setupView(layoutView, calendarMonth.getYear(), calendarMonth.getMonth(), calendarDay.getDay());
            }
        }
    }

    private void setupStyles(@Nullable CalendarDay currentDay, @Nullable CalendarDay previousDay, @Nullable CalendarDay nextDay) {
        if (!(layoutView instanceof TextView)) {
            return;
        }
        if (currentDay != null) {
            switch (currentDay.getState()) {
                case CalendarDay.FIRST_SELECTED:
                    setupForFirstSelectedDate();
                    break;
                case CalendarDay.ONLY_SELECTED:
                    setupForOnlySelectedDate();
                    break;
                case CalendarDay.LAST_SELECTED:
                    setupForLastSelectedDate();
                    break;
                case CalendarDay.SELECTED:
                    setupForSelectedDate(previousDay, nextDay);
                    break;
                case CalendarDay.UNAVAILABLE:
                    setupForUnavailableDate();
                    break;
                case CalendarDay.DISABLED:
                    setupForDisabledDate();
                    break;
                case CalendarDay.TODAY:
                    setupForTodayDate();
                    break;
                case CalendarDay.DEFAULT:
                default:
                    setupForDefaultDate();
                    break;
            }
        }
    }

    private void setupForOnlySelectedDate() {
        if (!(layoutView instanceof TextView)) {
            return;
        }
        TextView textView = (TextView) layoutView;
        textView.setTextColor(resProvider.getSelectedDayTextColor());
        textView.setBackgroundResource(resProvider.getSelectedDayBackground());
    }

    private void setupForDefaultDate() {
        if (!(layoutView instanceof TextView)) {
            return;
        }
        TextView textView = (TextView) layoutView;

        textView.setTextColor(resProvider.getDayTextColor());
        textView.setBackgroundColor(resProvider.getDayBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForTodayDate() {

        if (!(layoutView instanceof TextView)) {
            return;
        }
        TextView textView = (TextView) layoutView;

        textView.setTextColor(resProvider.getCurrentDayTextColor());
        textView.setBackgroundResource(resProvider.getCurrentDayBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForDisabledDate() {

        if (!(layoutView instanceof TextView)) {
            return;
        }
        TextView textView = (TextView) layoutView;

        textView.setTextColor(resProvider.getDisabledTextColor());
        textView.setBackgroundColor(resProvider.getDisabledBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForUnavailableDate() {

        if (!(layoutView instanceof TextView)) {
            return;
        }
        TextView textView = (TextView) layoutView;

        textView.setTextColor(resProvider.getUnavailableTextColor());
        textView.setBackgroundResource(resProvider.getUnavailableBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForFirstSelectedDate() {
        if (!(layoutView instanceof TextView)) {
            return;
        }
        TextView textView = (TextView) layoutView;
        textView.setTextColor(resProvider.getSelectedBeginningDayTextColor());
        textView.setBackgroundResource(resProvider.getSelectedBeginningDayBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForLastSelectedDate() {
        if (!(layoutView instanceof TextView)) {
            return;
        }
        TextView textView = (TextView) layoutView;
        textView.setTextColor(resProvider.getSelectedEndDayTextColor());
        textView.setBackgroundResource(resProvider.getSelectedEndDayBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForSelectedDate(@Nullable CalendarDay previousDay, @Nullable CalendarDay nextDay) {
        if (!(layoutView instanceof TextView)) {
            return;
        }
        TextView textView = (TextView) layoutView;
        if (!resProvider.softLineBreaks()) {
            textView.setTextColor(resProvider.getSelectedMiddleDayTextColor());
            textView.setBackgroundResource(resProvider.getSelectedMiddleDayBackground());
            return;
        }

        if (hasNoNeighbours(previousDay, nextDay)) {
            textView.setTextColor(resProvider.getSelectedDayTextColor());
            textView.setBackgroundResource(resProvider.getSelectedDayBackground());
        } else if (isBeginning(previousDay)) {
            if (resProvider.softLineBreaks()) {
                textView.setTextColor(resProvider.getSelectedBeginningDayTextColor());
                textView.setBackgroundResource(resProvider.getSelectedBeginningDayBackground());
            } else {
                textView.setTextColor(resProvider.getSelectedMiddleDayTextColor());
                textView.setBackgroundResource(resProvider.getSelectedMiddleDayBackground());
            }
        } else if (isMiddle(previousDay, nextDay)) {
            textView.setTextColor(resProvider.getSelectedMiddleDayTextColor());
            textView.setBackgroundResource(resProvider.getSelectedMiddleDayBackground());
        } else if (isEnd(nextDay)) {
            if (resProvider.softLineBreaks()) {
                textView.setTextColor(resProvider.getSelectedEndDayTextColor());
                textView.setBackgroundResource(resProvider.getSelectedEndDayBackground());
            } else {
                textView.setTextColor(resProvider.getSelectedMiddleDayTextColor());
                textView.setBackgroundResource(resProvider.getSelectedMiddleDayBackground());
            }
        }
        setFont(resProvider.getCustomFont());
    }

    private boolean isBeginning(@Nullable CalendarDay previousDay) {
        return !isSelected(previousDay);
    }

    private boolean isEnd(CalendarDay nextDay) {
        return !isSelected(nextDay);
    }

    private boolean isMiddle(CalendarDay previousDay, CalendarDay nextDay) {
        return isSelected(previousDay) && isSelected(nextDay);
    }

    private boolean hasNoNeighbours(@Nullable CalendarDay previousDay, @Nullable CalendarDay nextDay) {
        return !isSelected(previousDay) && !isSelected(nextDay);
    }

    private boolean isSelected(@Nullable CalendarDay previousDay) {
        return previousDay != null && equalsAny(previousDay.getState());
    }

    private boolean equalsAny(int state) {
        for (int anInt : CalendarDay.SELECTED_STATES) {
            if (anInt == state) {
                return true;
            }
        }
        return false;
    }

    private void setFont(Typeface customFont) {
        if (!(layoutView instanceof TextView)) {
            return;
        }
        TextView textView = (TextView) layoutView;

        if (customFont != null) {
            textView.setTypeface(customFont);
        }
    }

    @Override
    public void onClick(View view) {
        if (calendarMonth != null && currentDay != null) {
            calendarCallback.onCalendarDayClicked(calendarMonth, currentDay);
        }
    }

}
