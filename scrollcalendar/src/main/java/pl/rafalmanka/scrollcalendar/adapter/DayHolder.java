package pl.rafalmanka.scrollcalendar.adapter;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import pl.rafalmanka.scrollcalendar.R;
import pl.rafalmanka.scrollcalendar.SquareTextView;
import pl.rafalmanka.scrollcalendar.contract.ClickCallback;
import pl.rafalmanka.scrollcalendar.data.CalendarDay;
import pl.rafalmanka.scrollcalendar.data.CalendarMonth;

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
    private CalendarDay calendarDay;

    DayHolder(@NonNull ClickCallback calendarCallback, @NonNull ResProvider resProvider) {
        this.calendarCallback = calendarCallback;
        this.resProvider = resProvider;
    }

    public View layout(LinearLayout parent) {
        if (textView == null) {
            textView = (SquareTextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.scrollcalendar_day, parent, false);
            textView.setOnClickListener(this);
            textView.setTextSize(resProvider.fontSize());
        }
        return textView;
    }

    void display(@Nullable CalendarMonth calendarMonth, @Nullable CalendarDay calendarDay) {
        refreshValue(calendarMonth, calendarDay);
        refreshAppearance(calendarDay);
        refreshStyle(calendarDay);
    }

    private void refreshValue(@Nullable CalendarMonth calendarMonth, @Nullable CalendarDay calendarDay) {
        this.calendarMonth = calendarMonth;
        this.calendarDay = calendarDay;
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

    private void refreshStyle(@Nullable CalendarDay calendarDay) {
        if (textView == null) {
            return;
        }

        if (calendarDay != null) {
            switch (calendarDay.getState()) {
                case CalendarDay.SELECTED:
                    textView.setTextColor(resProvider.selectedTextColor());
                    textView.setBackgroundResource(resProvider.selectedBackgroundColor());
                    setFont(resProvider.getCustomFont());
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
        if (calendarMonth != null && calendarDay != null) {
            calendarCallback.onCalendarDayClicked(calendarMonth, calendarDay);
        }
    }

}
