package pl.rafman.scrollcalendar.adapter;

import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import pl.rafman.scrollcalendar.R;
import pl.rafman.scrollcalendar.contract.ClickCallback;
import pl.rafman.scrollcalendar.data.CalendarDay;
import pl.rafman.scrollcalendar.data.CalendarMonth;

/**
 * Created by rafal.manka on 10/09/2017
 */
class MonthViewHolder extends RecyclerView.ViewHolder {

    private static final int[] attrs = {
            android.R.attr.textColor,
            android.R.attr.textSize,
            android.R.attr.gravity,
            android.R.attr.textAllCaps,
    };

    static {
        Arrays.sort(attrs);
    }

    @Nullable
    private final TextView title;

    private final WeekHolder[] weeks = new WeekHolder[7];
    private boolean textAllCaps;


    private MonthViewHolder(@NonNull View rootView, @NonNull ClickCallback calendarCallback, @NonNull ResProvider resProvider) {
        super(rootView);
        LinearLayout monthContainer = rootView.findViewById(R.id.monthContainer);
        title = rootView.findViewById(R.id.title);
        setupTitleAppearance(resProvider);

        for (int i = 0; i < weeks.length; i++) {
            WeekHolder holder = new WeekHolder(calendarCallback, resProvider);
            weeks[i] = holder;
            monthContainer.addView(holder.layout(monthContainer));
        }
    }

    private void setupTitleAppearance(@NonNull ResProvider resProvider) {
        if (title == null) {
            return;
        }
        // Text appearance
        TypedArray typedArray = itemView.getContext().getTheme().obtainStyledAttributes(resProvider.getMonthTitleStyle(), attrs);
        for (int i = 0; i < attrs.length; i++) {
            switch (attrs[i]) {
                case android.R.attr.textColor:
                    title.setTextColor(typedArray.getColor(i, ContextCompat.getColor(itemView.getContext(), android.R.color.black)));
                    break;
                case android.R.attr.textSize:
                    title.setTextSize(TypedValue.COMPLEX_UNIT_PX, typedArray.getDimensionPixelSize(i, 12));
                    break;
                case android.R.attr.gravity:
                    title.setGravity(typedArray.getInt(i, Gravity.START));
                    break;
                case android.R.attr.textAllCaps:
                    textAllCaps = typedArray.getBoolean(i, false);
                    break;
                default:
                    break;
            }
        }
        typedArray.recycle();
        // Typeface
        Typeface typeface = resProvider.getCustomFont();
        if (typeface != null) {
            title.setTypeface(typeface);
        }
    }

    MonthViewHolder(View rootView) {
        super(rootView);
        title = null;
    }

    static MonthViewHolder create(@NonNull ViewGroup parent, @NonNull ClickCallback calendarCallback, @NonNull ResProvider resProvider) {
        return new MonthViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.scrollcalendar_month, parent, false),
                calendarCallback, resProvider);
    }

    void bind(CalendarMonth month, boolean showYearAlways) {
        if (title != null) {
            String txt = showYearAlways ? month.getMonthNameWithYear() : month.getReadableMonthName();
            title.setText(applyCase(txt));
        }
        for (int i = 0; i <= weeks.length - 1; i++) {
            weeks[i].display(i, month, filterWeekDays(i, month));
        }
    }

    private String applyCase(@NonNull String string) {
        return textAllCaps ? string.toUpperCase() : string;
    }

    CalendarDay[] filterWeekDays(int weekOfMonth, CalendarMonth calendarMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.set(Calendar.YEAR, calendarMonth.getYear());
        calendar.set(Calendar.MONTH, calendarMonth.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        List<CalendarDay> days = new ArrayList<>();
        for (CalendarDay calendarDay : calendarMonth.getDays()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendarDay.getDay());
            if (calendar.get(Calendar.WEEK_OF_MONTH) == weekOfMonth) {
                days.add(calendarDay);
            }
        }
        return days.toArray(new CalendarDay[days.size()]);
    }

}
