package pl.rafman.scrollcalendar.adapter;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import pl.rafman.scrollcalendar.R;
import pl.rafman.scrollcalendar.contract.ClickCallback;
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
    private View dayView;
    @Nullable
    private SquareTextView tvDate;
    @Nullable
    private SquareTextView tvSubTitle;

    @Nullable
    private CalendarMonth calendarMonth;
    @Nullable
    private CalendarDay currentDay;


    DayHolder(@NonNull ClickCallback calendarCallback, @NonNull DayResProvider resProvider) {
        this.calendarCallback = calendarCallback;
        this.resProvider = resProvider;
    }

    public View layout(LinearLayout parent) {
        if (dayView == null) {
            dayView = LayoutInflater.from(parent.getContext()).inflate(R.layout.scrollcalendar_day, parent, false);
            dayView.setOnClickListener(this);
            tvDate = dayView.findViewById(R.id.date);
            tvSubTitle = dayView.findViewById(R.id.subtitle);
        }
        return dayView;
    }

    void display(@Nullable CalendarMonth calendarMonth, @Nullable CalendarDay currentDay, @Nullable CalendarDay previousDay, @Nullable CalendarDay nextDay) {
        this.calendarMonth = calendarMonth;
        this.currentDay = currentDay;

        setupVisibility(currentDay);
        setupStyles(currentDay, previousDay, nextDay);
    }


    private void setupVisibility(@Nullable CalendarDay calendarDay) {
        if (dayView == null || tvDate == null || tvSubTitle == null) {
            return;
        }

        if (calendarDay == null) {
            dayView.setVisibility(View.INVISIBLE);
        } else {
            dayView.setVisibility(View.VISIBLE);
            tvDate.setText(String.valueOf(calendarDay.getDay()));
            tvSubTitle.setText(calendarDay.getSubTitle());
            tvSubTitle.setVisibility(calendarDay.getSubTitle().trim().length() == 0 ?
                    View.GONE : View.VISIBLE);
        }
    }

    private void setupStyles(@Nullable CalendarDay currentDay, @Nullable CalendarDay previousDay, @Nullable CalendarDay nextDay) {
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
        if (dayView == null || tvDate == null || tvSubTitle == null) {
            return;
        }
        tvDate.setTextColor(resProvider.getSelectedDayTextColor());
        tvSubTitle.setTextColor(resProvider.getSelectedDayTextColor());
        dayView.setBackgroundResource(resProvider.getSelectedDayBackground());
    }

    private void setupForDefaultDate() {
        if (dayView == null || tvDate == null || tvSubTitle == null) {
            return;
        }

        tvDate.setTextColor(resProvider.getDayTextColor());
        tvSubTitle.setTextColor(resProvider.getDayTextColor());
        dayView.setBackgroundColor(resProvider.getDayBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForTodayDate() {
        if (dayView == null || tvDate == null || tvSubTitle == null) {
            return;
        }

        tvDate.setTextColor(resProvider.getCurrentDayTextColor());
        tvSubTitle.setTextColor(resProvider.getCurrentDayTextColor());
        dayView.setBackgroundResource(resProvider.getCurrentDayBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForDisabledDate() {
        if (dayView == null || tvDate == null || tvSubTitle == null) {
            return;
        }

        tvDate.setTextColor(resProvider.getDisabledTextColor());
        tvSubTitle.setTextColor(resProvider.getDisabledTextColor());
        dayView.setBackgroundColor(resProvider.getDisabledBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForUnavailableDate() {
        if (dayView == null || tvDate == null || tvSubTitle == null) {
            return;
        }

        tvDate.setTextColor(resProvider.getUnavailableTextColor());
        tvSubTitle.setTextColor(resProvider.getUnavailableTextColor());
        dayView.setBackgroundResource(resProvider.getUnavailableBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForFirstSelectedDate() {
        if (dayView == null || tvDate == null || tvSubTitle == null) {
            return;
        }

        tvDate.setTextColor(resProvider.getSelectedBeginningDayTextColor());
        tvSubTitle.setTextColor(resProvider.getSelectedBeginningDayTextColor());
        dayView.setBackgroundResource(resProvider.getSelectedBeginningDayBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForLastSelectedDate() {
        if (dayView == null || tvDate == null || tvSubTitle == null) {
            return;
        }

        tvDate.setTextColor(resProvider.getSelectedEndDayTextColor());
        tvSubTitle.setTextColor(resProvider.getSelectedEndDayTextColor());
        dayView.setBackgroundResource(resProvider.getSelectedEndDayBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForSelectedDate(@Nullable CalendarDay previousDay, @Nullable CalendarDay nextDay) {
        if (dayView == null || tvDate == null || tvSubTitle == null) {
            return;
        }
        if (!resProvider.softLineBreaks()) {
            tvDate.setTextColor(resProvider.getSelectedMiddleDayTextColor());
            tvSubTitle.setTextColor(resProvider.getSelectedMiddleDayTextColor());
            dayView.setBackgroundResource(resProvider.getSelectedMiddleDayBackground());
            return;
        }

        if (hasNoNeighbours(previousDay, nextDay)) {
            tvDate.setTextColor(resProvider.getSelectedDayTextColor());
            tvSubTitle.setTextColor(resProvider.getSelectedDayTextColor());
            dayView.setBackgroundResource(resProvider.getSelectedDayBackground());
        } else if (isBeginning(previousDay)) {
            if (resProvider.softLineBreaks()) {
                tvDate.setTextColor(resProvider.getSelectedBeginningDayTextColor());
                tvSubTitle.setTextColor(resProvider.getSelectedBeginningDayTextColor());
                dayView.setBackgroundResource(resProvider.getSelectedBeginningDayBackground());
            } else {
                tvDate.setTextColor(resProvider.getSelectedMiddleDayTextColor());
                tvSubTitle.setTextColor(resProvider.getSelectedMiddleDayTextColor());
                dayView.setBackgroundResource(resProvider.getSelectedMiddleDayBackground());
            }
        } else if (isMiddle(previousDay, nextDay)) {
            tvDate.setTextColor(resProvider.getSelectedMiddleDayTextColor());
            tvSubTitle.setTextColor(resProvider.getSelectedMiddleDayTextColor());
            dayView.setBackgroundResource(resProvider.getSelectedMiddleDayBackground());
        } else if (isEnd(nextDay)) {
            if (resProvider.softLineBreaks()) {
                tvDate.setTextColor(resProvider.getSelectedEndDayTextColor());
                tvSubTitle.setTextColor(resProvider.getSelectedEndDayTextColor());
                dayView.setBackgroundResource(resProvider.getSelectedEndDayBackground());
            } else {
                tvDate.setTextColor(resProvider.getSelectedMiddleDayTextColor());
                tvSubTitle.setTextColor(resProvider.getSelectedMiddleDayTextColor());
                dayView.setBackgroundResource(resProvider.getSelectedMiddleDayBackground());
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
        return previousDay != null && equalsAny(previousDay.getState(), CalendarDay.SELECTED_STATES);
    }

    private boolean equalsAny(int state, int[] ints) {
        for (int anInt : ints) {
            if (anInt == state) {
                return true;
            }
        }
        return false;
    }

    private void setFont(Typeface customFont) {
        if (tvDate == null || tvSubTitle == null) {
            return;
        }

        if (customFont != null) {
            tvDate.setTypeface(customFont);
            tvSubTitle.setTypeface(customFont);
        }
    }

    @Override
    public void onClick(View view) {
        if (calendarMonth != null && currentDay != null) {
            calendarCallback.onCalendarDayClicked(calendarMonth, currentDay);
        }
    }

}
