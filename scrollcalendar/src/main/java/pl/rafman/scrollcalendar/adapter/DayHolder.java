package pl.rafman.scrollcalendar.adapter;

import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Arrays;

import pl.rafman.scrollcalendar.R;
import pl.rafman.scrollcalendar.contract.ClickCallback;
import pl.rafman.scrollcalendar.data.CalendarDay;
import pl.rafman.scrollcalendar.data.CalendarMonth;
import pl.rafman.scrollcalendar.widgets.SquareTextView;

/**
 * Created by rafal.manka on 10/09/2017
 */
class DayHolder implements View.OnClickListener {

    private static final int[] attrs = {
            android.R.attr.background,
            android.R.attr.textColor,
    };

    static {
        Arrays.sort(attrs);
    }

    @NonNull
    private final ClickCallback calendarCallback;
    @Nullable
    private SquareTextView textView;
    private final ResProvider resProvider;
    @Nullable
    private CalendarMonth calendarMonth;
    @Nullable
    private CalendarDay currentDay;

    @DrawableRes
    private int currentDayBackground;
    @DrawableRes
    private int selectedDayBackground;
    @DrawableRes
    private int selectedBeginningDayBackground;
    @DrawableRes
    private int selectedMiddleDayBackground;
    @DrawableRes
    private int selectedEndDayBackground;

    @ColorInt
    private int currentDayTextColor;
    @ColorInt
    private int selectedDayTextColor;
    @ColorInt
    private int selectedMiddleDayTextColor;
    @ColorInt
    private int selectedBeginningDayTextColor;
    @ColorInt
    private int selectedEndDayTextColor;

    DayHolder(@NonNull ClickCallback calendarCallback, @NonNull ResProvider resProvider) {
        this.calendarCallback = calendarCallback;
        this.resProvider = resProvider;
    }

    public View layout(LinearLayout parent) {
        if (textView == null) {
            textView = (SquareTextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.scrollcalendar_day, parent, false);
            textView.setOnClickListener(this);

            TypedArray typedArray = textView.getContext().getTheme().obtainStyledAttributes(resProvider.getCurrentDayStyle(), attrs);
            for (int i = 0; i < attrs.length; i++) {
                if (attrs[i] == android.R.attr.background) {
                    currentDayBackground = typedArray.getResourceId(i, 0);
                } else if (attrs[i] == android.R.attr.textColor) {
                    currentDayTextColor = typedArray.getColor(i, 0);
                }
            }
            typedArray.recycle();

            typedArray = textView.getContext().getTheme().obtainStyledAttributes(resProvider.getSelectedDayStyle(), attrs);
            for (int i = 0; i < attrs.length; i++) {
                if (attrs[i] == android.R.attr.background) {
                    selectedDayBackground = typedArray.getResourceId(i, 0);
                } else if (attrs[i] == android.R.attr.textColor) {
                    selectedDayTextColor = typedArray.getColor(i, 0);
                }
            }
            typedArray.recycle();


            typedArray = textView.getContext().getTheme().obtainStyledAttributes(resProvider.getSelectedBeginningDayStyle(), attrs);
            for (int i = 0; i < attrs.length; i++) {
                if (attrs[i] == android.R.attr.background) {
                    selectedBeginningDayBackground = typedArray.getResourceId(i, 0);
                } else if (attrs[i] == android.R.attr.textColor) {
                    selectedBeginningDayTextColor = typedArray.getColor(i, 0);
                }
            }
            typedArray.recycle();

            typedArray = textView.getContext().getTheme().obtainStyledAttributes(resProvider.getSelectedMiddleDayStyle(), attrs);
            for (int i = 0; i < attrs.length; i++) {
                if (attrs[i] == android.R.attr.background) {
                    selectedMiddleDayBackground = typedArray.getResourceId(i, 0);
                } else if (attrs[i] == android.R.attr.textColor) {
                    selectedMiddleDayTextColor = typedArray.getColor(i, 0);
                }
            }
            typedArray.recycle();

            typedArray = textView.getContext().getTheme().obtainStyledAttributes(resProvider.getSelectedEndDayStyle(), attrs);
            for (int i = 0; i < attrs.length; i++) {
                if (attrs[i] == android.R.attr.background) {
                    selectedEndDayBackground = typedArray.getResourceId(i, 0);
                } else if (attrs[i] == android.R.attr.textColor) {
                    selectedEndDayTextColor = typedArray.getColor(i, 0);
                }
            }
            typedArray.recycle();

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
                case CalendarDay.FIRST_SELECTED:
                    textView.setTextColor(selectedBeginningDayTextColor);
                    textView.setBackgroundResource(selectedBeginningDayBackground);
                    setFont(resProvider.getCustomFont());
                    break;
                case CalendarDay.LAST_SELECTED:
                    textView.setTextColor(selectedEndDayTextColor);
                    textView.setBackgroundResource(selectedEndDayBackground);
                    setFont(resProvider.getCustomFont());
                    break;
                case CalendarDay.SELECTED:
                    if (hasNoNeighbours(previousDay, nextDay)) {
                        textView.setTextColor(selectedDayTextColor);
                        textView.setBackgroundResource(selectedDayBackground);
                    } else if (isBeginning(previousDay)) {
                        if (resProvider.softLineBreaks()) {
                            textView.setTextColor(selectedBeginningDayTextColor);
                            textView.setBackgroundResource(selectedBeginningDayBackground);
                        } else {
                            textView.setTextColor(selectedMiddleDayTextColor);
                            textView.setBackgroundResource(selectedMiddleDayBackground);
                        }
                    } else if (isMiddle(previousDay, nextDay)) {
                        textView.setTextColor(selectedMiddleDayTextColor);
                        textView.setBackgroundResource(selectedMiddleDayBackground);
                    } else if (isEnd(nextDay)) {
                        if (resProvider.softLineBreaks()) {
                            textView.setTextColor(selectedEndDayTextColor);
                            textView.setBackgroundResource(selectedEndDayBackground);
                        } else {
                            textView.setTextColor(selectedMiddleDayTextColor);
                            textView.setBackgroundResource(selectedMiddleDayBackground);
                        }
                    }
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
                    textView.setTextColor(currentDayTextColor);
                    textView.setBackgroundResource(currentDayBackground);
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

    private boolean isFirstDate(@Nullable CalendarDay previousDay) {
        return previousDay != null && previousDay.getState() == CalendarDay.FIRST_SELECTED;
    }

    private boolean isLastDate(@Nullable CalendarDay previousDay) {
        return previousDay != null && previousDay.getState() == CalendarDay.LAST_SELECTED;
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
