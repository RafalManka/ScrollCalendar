package pl.rafman.scrollcalendar.adapter;

import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import pl.rafman.scrollcalendar.R;

/**
 * Created by rafal.manka on 10/09/2017
 */
public class LegendItem {

    private static final char[] days;

    private static final int[] attrs = {
            android.R.attr.textColor,
            android.R.attr.textSize,
            android.R.attr.padding,
            android.R.attr.gravity
    };

    static {
        Arrays.sort(attrs);
        //
        String[] original = new DateFormatSymbols().getWeekdays();
        List<Character> characters = new ArrayList<>();
        for (String s : original) {
            if (s != null && !s.isEmpty()) {
                characters.add(s.toUpperCase(Locale.US).charAt(0));
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
            textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.scrollcalendar_day_legend, parent, false);

            TypedArray typedArray = textView.getContext().getTheme().obtainStyledAttributes(resProvider.getLegendItemStyle(), attrs);
            for (int i = 0; i < attrs.length; i++) {
                switch (attrs[i]) {
                    case android.R.attr.textColor:
                        textView.setTextColor(typedArray.getColor(i, ContextCompat.getColor(textView.getContext(), android.R.color.black)));
                        break;
                    case android.R.attr.textSize:
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, typedArray.getDimensionPixelSize(i, 16));
                        break;
                    case android.R.attr.gravity:
                        textView.setGravity(typedArray.getInt(i, Gravity.CENTER));
                        break;
                    case android.R.attr.padding:
                        int padding = typedArray.getDimensionPixelOffset(i, 0);
                        textView.setPadding(padding, padding, padding, padding);
                        break;
                    default:
                        break;
                }
            }

            typedArray.recycle();
            Typeface typeface = resProvider.getCustomFont();
            if (typeface != null) {
                textView.setTypeface(typeface);
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
