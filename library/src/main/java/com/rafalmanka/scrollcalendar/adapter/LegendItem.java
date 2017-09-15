package com.rafalmanka.scrollcalendar.adapter;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rafalmanka.scrollcalendar.R;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafal.manka on 10/09/2017
 */
public class LegendItem {

    private static final char[] days;

    static {
        String[] original = new DateFormatSymbols().getWeekdays();
        List<Character> characters = new ArrayList<>();
        for (String s : original) {
            if (s != null && !s.isEmpty()) {
                characters.add(s.toUpperCase().charAt(0));
            }
        }
        days = new char[characters.size()];
        for (int i = 0; i < days.length; i++) {
            days[i] = characters.get(i);
        }
    }

    private final int dayOfWeek;

    @Nullable
    private TextView textView;

    public LegendItem(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }


    public View layout(LinearLayout parent, ResProvider resProvider) {
        if (textView == null) {
            textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.scrollcalendar_day, parent, false);
            if (textView != null) {
                Typeface typeface = resProvider.getCustomFont();
                if (typeface != null) {
                    textView.setTypeface(typeface);
                }
            }
        }
        return textView;
    }


    public void display() {
        if (textView != null) {
            textView.setText(getReadableSymbol());
        }
    }

    private String getReadableSymbol() {
        return String.valueOf(days[dayOfWeek - 1]);
    }
}
