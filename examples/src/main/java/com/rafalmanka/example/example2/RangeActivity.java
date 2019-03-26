package com.rafalmanka.example.example2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import com.rafalmanka.example.R;

import java.util.Calendar;

import pl.rafman.scrollcalendar.ScrollCalendar;
import pl.rafman.scrollcalendar.contract.DateWatcher;
import pl.rafman.scrollcalendar.contract.OnDateClickListener;
import pl.rafman.scrollcalendar.contract.State;
import pl.rafman.scrollcalendar.data.CalendarDay;


public class RangeActivity extends AppCompatActivity {

    @Nullable
    private Calendar from;
    @Nullable
    private Calendar until;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range);
        TextView title = findViewById(R.id.title);
        title.setText(R.string.title_select_range);
        //
        ScrollCalendar scrollCalendar = findViewById(R.id.scrollCalendar);
        if (scrollCalendar == null) {
            return;
        }
        scrollCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onCalendarDayClicked(int year, int month, int day) {
                doOnCalendarDayClicked(year, month, day);
            }
        });
        scrollCalendar.setDateWatcher(new DateWatcher() {
            @State
            @Override
            public int getStateForDate(int year, int month, int day) {
                return doGetStateForDate(year, month, day);
            }

            @Override
            public void onTextViewTextSet(@NonNull TextView textView, int year, int month, int day) {
                String topText = textView.getText().toString();
                String allText = topText + "\n100$";
                SpannableString spannable = new SpannableString(allText);
                spannable.setSpan(
                        new RelativeSizeSpan(0.7f),
                        topText.length(),
                        allText.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
                textView.setText(spannable);
            }
        });
    }

    @State
    private int doGetStateForDate(int year, int month, int day) {
        if (isSelected(from, year, month, day)) {
            return CalendarDay.SELECTED;
        }
        if (isInRange(from, until, year, month, day)) {
            return CalendarDay.SELECTED;
        }
        return CalendarDay.DEFAULT;
    }

    private boolean isInRange(Calendar from, Calendar until, int year, int month, int day) {
        if (from == null || until == null) {
            return false;
        }
        //noinspection UnnecessaryLocalVariable
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, from.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, from.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, from.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long millis1 = calendar.getTimeInMillis();

        calendar.set(Calendar.YEAR, until.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, until.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, until.get(Calendar.DAY_OF_MONTH));
        long millis2 = calendar.getTimeInMillis();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long millis3 = calendar.getTimeInMillis();
        return millis1 <= millis3 && millis2 >= millis3;
    }

    private void doOnCalendarDayClicked(int year, int month, int day) {
        Calendar clickedOn = Calendar.getInstance();
        clickedOn.set(Calendar.YEAR, year);
        clickedOn.set(Calendar.MONTH, month);
        clickedOn.set(Calendar.DAY_OF_MONTH, day);
        clickedOn.set(Calendar.HOUR_OF_DAY, 0);
        clickedOn.set(Calendar.MINUTE, 0);
        clickedOn.set(Calendar.SECOND, 0);
        clickedOn.set(Calendar.MILLISECOND, 0);

        if (shouldClearAllSelected(clickedOn)) {
            from = null;
            until = null;
        } else if (shouldSetFrom(clickedOn)) {
            from = clickedOn;
            until = null;
        } else if (shouldSetUntil()) {
            until = clickedOn;
        }
    }

    private boolean shouldSetUntil() {
        return until == null;
    }

    private boolean shouldClearAllSelected(Calendar calendar) {
        return from != null && from.equals(calendar);
    }

    private boolean shouldSetFrom(Calendar calendar) {
        return from == null || until != null || isBefore(from, calendar);
    }

    private boolean isBefore(Calendar c1, Calendar c2) {
        if (c1 == null || c2 == null) {
            return false;
        }
        //noinspection UnnecessaryLocalVariable
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, c2.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, c2.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, c2.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long millis = calendar.getTimeInMillis();

        calendar.set(Calendar.YEAR, c1.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, c1.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, c1.get(Calendar.DAY_OF_MONTH));
        long millis2 = calendar.getTimeInMillis();

        return millis < millis2;
    }

    private boolean isSelected(Calendar selected, int year, int month, int day) {
        if (selected == null) {
            return false;
        }
        //noinspection UnnecessaryLocalVariable
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, selected.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, selected.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, selected.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long millis = calendar.getTimeInMillis();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long millis2 = calendar.getTimeInMillis();

        return millis == millis2;
    }

}
