package pl.rafman.scrollcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import pl.rafman.scrollcalendar.adapter.LegendItem;
import pl.rafman.scrollcalendar.adapter.ResProvider;
import pl.rafman.scrollcalendar.adapter.ScrollCalendarAdapter;
import pl.rafman.scrollcalendar.contract.DateWatcher;
import pl.rafman.scrollcalendar.contract.MonthScrollListener;
import pl.rafman.scrollcalendar.contract.OnDateClickListener;
import pl.rafman.scrollcalendar.values.Defaults;
import pl.rafman.scrollcalendar.values.Keys;

/**
 * Created by rafal.manka on 10/09/2017
 */
public class ScrollCalendar extends LinearLayoutCompat implements ResProvider {

    @ColorRes
    private int fontColor;
    @ColorRes
    private int backgroundColor;
    @ColorRes
    private int disabledTextColor;
    @ColorRes
    private int unavailableTextColor;
    @ColorRes
    private int selectedTextColor;
    @ColorRes
    private int todayTextColor;
    @DrawableRes
    private int unavailableBackground;
    @DrawableRes
    private int selectedBackground;
    @DrawableRes
    private int selectedBackgroundBeginning;
    @DrawableRes
    private int selectedBackgroundEnd;
    @DrawableRes
    private int selectedBackgroundMiddle;

    @DrawableRes
    private int currentDayBackground;
    @ColorRes
    private int disabledBackgroundColor;
    @Dimension
    private float fontSize;

    @Nullable
    private String customFont;

    private final LegendItem[] legend = new LegendItem[7];

    @Nullable
    private ScrollCalendarAdapter adapter;


    public ScrollCalendar(Context context) {
        super(context);
        init(context);
    }

    public ScrollCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initStyle(context, attrs);
        init(context);
    }

    public ScrollCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStyle(context, attrs);
        init(context);
    }

    private void init(@NonNull Context context) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.scrollcalendar_calendar, this);
        for (int i = 0; i < legend.length; i++) {
            legend[i] = new LegendItem(i + 1);
        }
    }

    private void initStyle(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.ScrollCalendar);
        currentDayBackground = typedArray.getResourceId(Keys.CURRENT_DAY_BG, Defaults.CURRENT_DAY_BG);
        fontColor = typedArray.getResourceId(Keys.FONT_COLOR, Defaults.FONT_COLOR);
        backgroundColor = typedArray.getResourceId(Keys.BACKGROUND_COLOR, Defaults.BACKGROUND_COLOR);
        disabledTextColor = typedArray.getResourceId(Keys.DISABLED_TEXT_COLOR, Defaults.DISABLED_TEXT_COLOR);
        disabledBackgroundColor = typedArray.getResourceId(Keys.DISABLED_BACKGROUND_COLOR, Defaults.DISABLED_BACKGROUND_COLOR);
        unavailableTextColor = typedArray.getResourceId(Keys.UNAVAILABLE_TEXT_COLOR, Defaults.UNAVAILABLE_TEXT_COLOR);
        selectedTextColor = typedArray.getResourceId(Keys.SELECTED_TEXT_COLOR, Defaults.SELECTED_TEXT_COLOR);
        unavailableBackground = typedArray.getResourceId(Keys.UNAVAILABLE_BACKGROUND, Defaults.UNAVAILABLE_BACKGROUND);
        selectedBackground = typedArray.getResourceId(Keys.SELECTED_BACKGROUND, Defaults.SELECTED_BACKGROUND);
        selectedBackgroundBeginning = typedArray.getResourceId(Keys.SELECTED_BACKGROUND_BEGINNING, Defaults.SELECTED_BACKGROUND_BEGINNING);
        selectedBackgroundMiddle = typedArray.getResourceId(Keys.SELECTED_BACKGROUND_MIDDLE, Defaults.SELECTED_BACKGROUND_MIDDLE);
        selectedBackgroundEnd = typedArray.getResourceId(Keys.SELECTED_BACKGROUND_END, Defaults.SELECTED_BACKGROUND_END);
        todayTextColor = typedArray.getResourceId(Keys.TODAY_TEXT_COLOR, Defaults.TODAY_TEXT_COLOR);
        fontSize = typedArray.getDimension(Keys.FONT_SIZE, getResources().getDimensionPixelSize(Defaults.FONT_SIZE));
        customFont = typedArray.getString(Keys.CUSTOM_FONT);
        typedArray.recycle();
    }

    public void setOnDateClickListener(@Nullable final OnDateClickListener calendarCallback) {
        getAdapter().setOnDateClickListener(calendarCallback);
    }

    public void setDateWatcher(@Nullable final DateWatcher dateWatcher) {
        getAdapter().setDateWatcher(dateWatcher);
    }

    public void setMonthScrollListener(@Nullable final MonthScrollListener monthScrollListener) {
        getAdapter().setMonthScrollListener(monthScrollListener);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // Legend
        LinearLayout legendHolder = findViewById(R.id.legend);
        for (LegendItem legendItem : legend) {
            legendHolder.addView(legendItem.layout(legendHolder, this));
        }
        refreshLegend();
        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), VERTICAL, false));
        recyclerView.setAdapter(getAdapter());
    }

    private void refreshLegend() {
        for (LegendItem legendItem : legend) {
            legendItem.display();
        }
    }

    @NonNull
    private ScrollCalendarAdapter getAdapter() {
        if (adapter == null) {
            adapter = new ScrollCalendarAdapter(this);
        }
        return adapter;
    }

    // Colors

    @ColorInt
    @Override
    public int defaultFontColor() {
        return ContextCompat.getColor(getContext(), fontColor);
    }

    @ColorInt
    @Override
    public int defaultBackgroundColor() {
        return ContextCompat.getColor(getContext(), backgroundColor);
    }

    @ColorInt
    @Override
    public int disabledTextColor() {
        return ContextCompat.getColor(getContext(), disabledTextColor);
    }

    @ColorInt
    @Override
    public int disabledBackgroundColor() {
        return ContextCompat.getColor(getContext(), disabledBackgroundColor);
    }

    @ColorInt
    @Override
    public int todayTextColor() {
        return ContextCompat.getColor(getContext(), todayTextColor);
    }

    @ColorInt
    @Override
    public int unavailableTextColor() {
        return ContextCompat.getColor(getContext(), unavailableTextColor);
    }

    @Override
    public int selectedTextColor() {
        return ContextCompat.getColor(getContext(), selectedTextColor);
    }

    // Drawables

    @Override
    @DrawableRes
    public int todayBackground() {
        return currentDayBackground;
    }

    @Override
    @DrawableRes
    public int unavailableBackgroundColor() {
        return unavailableBackground;
    }

    @Override
    public int selectedBackgroundColor() {
        return selectedBackground;
    }

    @Override
    public int selectedBeginningBackgroundColor() {
        return selectedBackgroundBeginning;
    }

    @Override
    public int selectedEndBackgroundColor() {
        return selectedBackgroundEnd;
    }

    @Override
    public int selectedMiddleBackgroundColor() {
        return selectedBackgroundMiddle;
    }
    @Dimension
    @Override
    public float fontSize() {
        return fontSize;
    }

    // Other

    @Override
    @Nullable
    public Typeface getCustomFont() {
        if (customFont == null) {
            return null;
        }
        try {
            return Typeface.createFromAsset(getContext().getAssets(), customFont);
        } catch (Exception e) {
            return null;
        }
    }


}
