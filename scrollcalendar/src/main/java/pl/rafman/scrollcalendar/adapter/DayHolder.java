package pl.rafman.scrollcalendar.adapter;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import pl.rafman.scrollcalendar.R;
import pl.rafman.scrollcalendar.contract.ClickCallback;
import pl.rafman.scrollcalendar.data.CalendarDay;
import pl.rafman.scrollcalendar.data.CalendarMonth;
import pl.rafman.scrollcalendar.widgets.SquareTextView;

/**
 * Created by rafal.manka on 10/09/2017
 */
class DayHolder implements View.OnClickListener {

    @NonNull
    private final ClickCallback calendarCallback;
    @Nullable
    private SquareTextView textView;
    private final ResProvider resProvider;
    @Nullable
    private CalendarMonth calendarMonth;
    @Nullable
    private CalendarDay currentDay;

    DayHolder(@NonNull ClickCallback calendarCallback, @NonNull ResProvider resProvider) {
        this.calendarCallback = calendarCallback;
        this.resProvider = resProvider;
    }

    public View layout(LinearLayout parent) {
        if (textView == null) {
            textView = (SquareTextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.scrollcalendar_day, parent, false);
            textView.setOnClickListener(this);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, resProvider.fontSize());
        }
        return textView;
    }

    void display(@Nullable CalendarMonth calendarMonth, @Nullable CalendarDay currentDay, @Nullable CalendarDay previousDay, @Nullable CalendarDay nextDay) {
        this.calendarMonth = calendarMonth;
        this.currentDay = currentDay;

        refreshAppearance(currentDay);
        refreshStyle(currentDay, previousDay, nextDay);
    }


    private void refreshAppearance(@Nullable CalendarDay calendarDay) {
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

    private void refreshStyle(@Nullable CalendarDay currentDay, @Nullable CalendarDay previousDay, @Nullable CalendarDay nextDay) {
        if (textView == null) {
            return;
        }

        if (currentDay != null) {
            switch (currentDay.getState()) {
                case CalendarDay.SELECTED:
                    if (hasNoNeighbours(previousDay, nextDay)) {
                        textView.setTextColor(resProvider.selectedTextColor());
                        textView.setBackgroundResource(resProvider.selectedBackgroundColor());
                        setFont(resProvider.getCustomFont());
                    } else if (isBeginning(previousDay)) {
                        textView.setTextColor(resProvider.selectedTextColor());
                        textView.setBackgroundResource(resProvider.selectedBeginningBackgroundColor());
                        setFont(resProvider.getCustomFont());
                    } else if (isMiddle(previousDay, nextDay)) {
                        textView.setTextColor(resProvider.selectedTextColor());
                        textView.setBackgroundResource(resProvider.selectedMiddleBackgroundColor());
                        setFont(resProvider.getCustomFont());
                    } else if (isEnd(nextDay)) {
                        textView.setTextColor(resProvider.selectedTextColor());
                        textView.setBackgroundResource(resProvider.selectedEndBackgroundColor());
                        setFont(resProvider.getCustomFont());
                    }
                    break;
                case CalendarDay.UNAVAILABLE:
                    textView.setTextColor(resProvider.unavailableTextColor());
                    textView.setBackgroundResource(resProvider.unavailableBackgroundColor());
                    setFont(resProvider.getCustomFont());
                    break;
                case CalendarDay.DISABLED:
                    textView.setTextColor(resProvider.disabledTextColor());
                    textView.setBackgroundColor(resProvider.disabledBackgroundColor());
                    setFont(resProvider.getCustomFont());
                    break;
                case CalendarDay.TODAY:
                    textView.setTextColor(resProvider.todayTextColor());
                    textView.setBackgroundResource(resProvider.todayBackground());
                    setFont(resProvider.getCustomFont());
                    break;
                case CalendarDay.DEFAULT:
                default:
                    textView.setTextColor(resProvider.defaultFontColor());
                    textView.setBackgroundColor(resProvider.defaultBackgroundColor());
                    setFont(resProvider.getCustomFont());
                    break;
            }
        }
    }

    private boolean isEnd(CalendarDay nextDay) {
        return !isSelected(nextDay);
    }

    private boolean isMiddle(CalendarDay previousDay, CalendarDay nextDay) {
        return isSelected(previousDay) && isSelected(nextDay);
    }

    private boolean isBeginning(@Nullable CalendarDay previousDay) {
        return !isSelected(previousDay);
    }

    private boolean hasNoNeighbours(@Nullable CalendarDay previousDay, @Nullable CalendarDay nextDay) {
        return !isSelected(previousDay) && !isSelected(nextDay);
    }

    private boolean isSelected(@Nullable CalendarDay previousDay) {
        return previousDay != null && previousDay.getState() == CalendarDay.SELECTED;
    }

    private void setFont(Typeface customFont) {
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
