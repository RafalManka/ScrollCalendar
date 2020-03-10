package pl.rafman.scrollcalendar.adapter;

import android.graphics.Typeface;
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
import pl.rafman.scrollcalendar.contract.State;
import pl.rafman.scrollcalendar.data.CalendarDay;
import pl.rafman.scrollcalendar.data.CalendarMonth;
import pl.rafman.scrollcalendar.style.DayResProvider;
import pl.rafman.scrollcalendar.widgets.SquareTextView;

/**
 * Created by rafal.manka on 10/09/2017
 */
public class DayHolder implements View.OnClickListener {


    protected final DayResProvider resProvider;

    @NonNull
    private final ClickCallback calendarCallback;
    @Nullable
    protected SquareTextView textView;

    @Nullable
    private CalendarMonth calendarMonth;
    @Nullable
    private CalendarDay currentDay;


    public DayHolder(@NonNull ClickCallback calendarCallback, @NonNull DayResProvider resProvider) {
        this.calendarCallback = calendarCallback;
        this.resProvider = resProvider;
    }

    public View layout(LinearLayout parent) {
        if (textView == null) {
            textView = (SquareTextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.scrollcalendar_day, parent, false);
            textView.setOnClickListener(this);

        }
        return textView;
    }

    void display(@Nullable CalendarMonth calendarMonth, @Nullable CalendarDay currentDay, @Nullable CalendarDay previousDay, @Nullable CalendarDay nextDay) {
        this.calendarMonth = calendarMonth;
        this.currentDay = currentDay;

        setupVisibility(currentDay);
        setupStyles(currentDay, previousDay, nextDay);
    }


    private void setupVisibility(@Nullable CalendarDay calendarDay) {
        if (textView == null) {
            return;
        }

        if (calendarDay == null) {
            textView.setVisibility(View.INVISIBLE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(String.valueOf(calendarDay.getDay()));
        }
    }

    private void setupStyles(@Nullable CalendarDay currentDay, @Nullable CalendarDay previousDay, @Nullable CalendarDay nextDay) {
        if (currentDay != null) {
            setupStyleForDayState(currentDay.getState(), previousDay, nextDay);
        }
    }

    protected void setupStyleForDayState(@State int state, @Nullable CalendarDay previousDay, @Nullable CalendarDay nextDay) {
        switch (state) {
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

    private void setupForOnlySelectedDate() {
        if (textView == null) {
            return;
        }
        textView.setTextColor(resProvider.getSelectedDayTextColor());
        textView.setBackgroundResource(resProvider.getSelectedDayBackground());
    }

    private void setupForDefaultDate() {
        if (textView == null) {
            return;
        }

        textView.setTextColor(resProvider.getDayTextColor());
        textView.setBackgroundColor(resProvider.getDayBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForTodayDate() {
        if (textView == null) {
            return;
        }

        textView.setTextColor(resProvider.getCurrentDayTextColor());
        textView.setBackgroundResource(resProvider.getCurrentDayBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForDisabledDate() {
        if (textView == null) {
            return;
        }

        textView.setTextColor(resProvider.getDisabledTextColor());
        textView.setBackgroundColor(resProvider.getDisabledBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForUnavailableDate() {
        if (textView == null) {
            return;
        }

        textView.setTextColor(resProvider.getUnavailableTextColor());
        textView.setBackgroundResource(resProvider.getUnavailableBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForFirstSelectedDate() {
        if (textView == null) {
            return;
        }

        textView.setTextColor(resProvider.getSelectedBeginningDayTextColor());
        textView.setBackgroundResource(resProvider.getSelectedBeginningDayBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForLastSelectedDate() {
        if (textView == null) {
            return;
        }

        textView.setTextColor(resProvider.getSelectedEndDayTextColor());
        textView.setBackgroundResource(resProvider.getSelectedEndDayBackground());
        setFont(resProvider.getCustomFont());
    }

    private void setupForSelectedDate(@Nullable CalendarDay previousDay, @Nullable CalendarDay nextDay) {
        if (textView == null) {
            return;
        }
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

    protected void setFont(Typeface customFont) {
        if (textView == null) {
            return;
        }

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
